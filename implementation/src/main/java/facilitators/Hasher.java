package facilitators;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is for calculating a hash value to specify a projectID which is then assigned to each
 * project/client. This ID is Required to identify each project/client.
 *
 * @see main.input.ProjectService
 * @see main.input.RESTHandler
 */
public class Hasher {

  private static Logger hasherLogger = LoggerFactory.getLogger(Hasher.class);
  private String hashValue;

  /**
   * The constructor does the actual calculation. It is called from the {@link main.input.ProjectService} class whenever a client opens the
   * index.html.
   * @param l this is the seedCounter in {@link Long}. It represents the nr of created projects and is necessary for calculating a different hash value than before
   */
  public Hasher(long l) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] rawHash = messageDigest.digest(byteArrayConverter(l));

      //The following code is taken from https://www.geeksforgeeks.org/sha-256-hash-in-java/
      BigInteger bigIntHash = new BigInteger(1, rawHash);
      String fullHashValue = bigIntHash.toString(16);
      //The code above is taken from https://www.geeksforgeeks.org/sha-256-hash-in-java/

      hashValue = fullHashValue.substring(0, 6);

    } catch (NoSuchAlgorithmException e) {
      hasherLogger.error("Invalid hashing algorithm." + '\n');
      e.printStackTrace();
    }

  }

  /**
   * The seedCounter gets converted into byte because {@link MessageDigest} needs a byte as parameter type.
   * @param l the seedCounter in {@link Long}
   * @return returns the seedCounter in byte
   */
  public static byte[] byteArrayConverter(long l) {
    byte b[] = new byte[8];
    ByteBuffer bb = ByteBuffer.wrap(b);
    bb.putLong(l);
    return b;
  }

  public String getHash() {
    return hashValue.toString();
  }
}

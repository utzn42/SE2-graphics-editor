package facilitators;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hasher {

  private static Logger hasherLogger = LoggerFactory.getLogger(Hasher.class);
  private String hashValue;

  public Hasher(long l) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] rawHash = messageDigest.digest(byteArrayConverter(l));
      BigInteger bigIntHash = new BigInteger(1, rawHash);
      String fullHashValue = bigIntHash.toString(16);
      hashValue = fullHashValue.substring(0, 6);

    } catch (NoSuchAlgorithmException e) {
      hasherLogger.error("Invalid hashing algorithm." + '\n');
      e.printStackTrace();
    }

  }

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

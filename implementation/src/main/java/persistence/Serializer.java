package persistence;

    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

/**
 * Serializer class taken from <a href="https://www.javaworld.com/article/2097430/java-se/serializing-java-objects-with-non-serializable-attributes.html">Javaworld</a>.
 *
 * @author Dustin Marx
 * @see <a href="https://www.javaworld.com/article/2097430/java-se/serializing-java-objects-with-non-serializable-attributes.html">Original Article</a>
 */
public class Serializer {

  public static final Logger serializerLogger = LoggerFactory.getLogger(Serializer.class);

  /**
   * Serialize the provided object to the file of the provided name.
   * @param objectToSerialize Object that is to be serialized to file; it is
   *     best that this object have an individually overridden toString()
   *     implementation as that is used by this method for writing our status.
   * @param fileName Name of file to which object is to be serialized.
   * @throws IllegalArgumentException Thrown if either provided parameter is null.
   * @throws IOException If an I/O error occurs.
   */
  public static <T> void serialize(final T objectToSerialize, final String fileName) throws IOException {
    if (fileName == null) {
      throw new IllegalArgumentException(
          "Name of file to which to serialize object to cannot be null.");
    }
    if (objectToSerialize == null) {
      throw new IllegalArgumentException("Object to be serialized cannot be null.");
    }
    try (FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(objectToSerialize);
      serializerLogger.info("Serialization of Object " + objectToSerialize + " completed.");
    } catch (IOException ioException) {
      serializerLogger.error("Serialization of Object " + objectToSerialize + " failed.");
      throw (ioException);
    }
  }

  /**
   * Provides an object deserialized from the file indicated by the provided
   * file name.
   *
   * @param <T> Type of object to be deserialized.
   * @param fileToDeserialize Name of file from which object is to be deserialized.
   * @param classBeingDeserialized Class definition of object to be deserialized
   *    from the file of the provided name/path; it is recommended that this
   *    class define its own toString() implementation as that will be used in
   *    this method's status output.
   * @return Object deserialized from provided filename as an instance of the
   *    provided class; may be null if something goes wrong with deserialization.
   * @throws IllegalArgumentException Thrown if either provided parameter is null.
   * @throws IOException If an I/O error occurs.
   * @throws ClassNotFoundException If the class being deserialized could not be found.
   */
  public static <T> T deserialize(final String fileToDeserialize, final Class <T> classBeingDeserialized) throws IOException, ClassNotFoundException {
    if (fileToDeserialize == null) {
      throw new IllegalArgumentException("Cannot deserialize from a null filename.");
    }
    if (classBeingDeserialized == null) {
      throw new IllegalArgumentException("Type of class to be deserialized cannot be null.");
    }
    T objectOut;
    try (FileInputStream fis = new FileInputStream(fileToDeserialize);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
      objectOut = (T) ois.readObject();
      serializerLogger.info("Deserialization of Object " + objectOut + " is completed.");
    } catch (IOException | ClassNotFoundException exception) {
      serializerLogger.error("Deserialization of Object failed.");
      throw (exception);
    }
    return objectOut;
  }

}
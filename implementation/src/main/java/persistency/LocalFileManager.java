package persistency;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalFileManager<T extends Serializable> implements FileManager<T> {

  private static Logger localFileManagerLogger = LoggerFactory.getLogger(LocalFileManager.class);
  private final String fileDirectoryPath;
  private Map<String, T> storedObjects;

  public LocalFileManager(String fileDirectoryPath) {
    localFileManagerLogger.info("Constructor called with path " + fileDirectoryPath);
    // fileDirectoryPath is the path where subfolders (for each ID) are placed
    this.fileDirectoryPath = fileDirectoryPath;

    try {
      FileInputStream in = new FileInputStream(fileDirectoryPath + "/projects.ser");
      ObjectInputStream oin = new ObjectInputStream(in);
      this.storedObjects = (Map<String, T>) (oin.readObject());
    } catch (Exception e) {
      localFileManagerLogger.error("No projects have been found! Initializing new HashMap...");
      storedObjects = new HashMap<String, T>();
    }
  }

  public Map<String, T> getStoredObjects() {
    return storedObjects;
  }

  @Override
  public void update(Object obj) {
    localFileManagerLogger.info("Observer.update() called!");
    if (obj instanceof Map) {
      storedObjects = (Map<String, T>) obj;
      try {
        FileOutputStream out = new FileOutputStream(fileDirectoryPath + "/projects.ser");
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(storedObjects);
        oout.close();
        out.close();
      } catch (Exception e) {
        localFileManagerLogger.error("Oops! Object is not instance of Map Type!");
      }
    }

    localFileManagerLogger.info("Map after put:" + storedObjects.toString());

  }
}
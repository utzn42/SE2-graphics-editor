package persistence;

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
  private long seedCounter;

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

    try {
      FileInputStream in = new FileInputStream(fileDirectoryPath + "/seedCounter.ser");
      ObjectInputStream oin = new ObjectInputStream(in);
      this.seedCounter = (long) (oin.readObject());
    } catch (Exception e) {
      localFileManagerLogger.error("No seed has been found! Initializing at zero...");
      seedCounter = 0;
    }
  }

  public Map<String, T> getStoredObjects() {
    return storedObjects;
  }

  public long getSeedCounter() {
    return seedCounter;
  }

  @Override
  public void update(Object obj) {
    localFileManagerLogger.info("PersistenceObserver.update() called!");
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
    if (obj instanceof Long) {
      seedCounter = ((Long) obj).longValue();
      try {
        FileOutputStream out = new FileOutputStream(fileDirectoryPath + "/seedCounter.ser");
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(seedCounter);
        oout.close();
        out.close();
      } catch (Exception e) {
        localFileManagerLogger.error("Oops! Object is not instance of Long Type!");
      }
    }

    localFileManagerLogger.info("Map after put:" + storedObjects.toString());

  }
}
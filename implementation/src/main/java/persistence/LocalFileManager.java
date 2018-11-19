package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to store the projects persistently on the server. Its part of the Observer Pattern and gets notified if something in the canvas of a project changes.
 * If the observer gets notified, the {@link LocalFileManager} updates the "project.ser" file and stores the new project or updates the canvas in an existing project.
 * @param <T> describes the type. In our case {@link canvas.Canvas}
 */
public class LocalFileManager<T extends Serializable> implements FileManager<T> {

  private static Logger localFileManagerLogger = LoggerFactory.getLogger(LocalFileManager.class);
  private final String fileDirectoryPath;
  private Map<String, T> storedObjects;
  private long seedCounter;

  /**
   * The constructor gets called when the program starts. It receives a path where the data should get stored.
   * It creates the directory if there is none. Then it assigns the seedCounter as well as the storedobject to the local variables.
   * @param fileDirectoryPath path where the file "project.ser" should get saved in {@link String}
   */
  public LocalFileManager(String fileDirectoryPath) {
    localFileManagerLogger.info("Constructor called with path " + fileDirectoryPath);
    // fileDirectoryPath is the path where subfolders (for each ID) are placed
    boolean temp = new File(fileDirectoryPath).mkdirs();
    this.fileDirectoryPath = fileDirectoryPath;

    try {
      FileInputStream in = new FileInputStream(fileDirectoryPath + "/projects.ser");
      ObjectInputStream oin = new ObjectInputStream(in);
      this.storedObjects = (Map<String, T>) (oin.readObject());
    } catch (Exception e) {
      localFileManagerLogger.info("No projects have been found! Initializing new HashMap...");
      storedObjects = new HashMap<>();
    }

    try {
      FileInputStream in = new FileInputStream(fileDirectoryPath + "/seedCounter.ser");
      ObjectInputStream oin = new ObjectInputStream(in);
      this.seedCounter = (long) (oin.readObject());
    } catch (Exception e) {
      localFileManagerLogger.info("No seed has been found! Initializing at zero...");
      seedCounter = 0;
    }
  }

  /**
   * Returns the stored objects. Gets called at the program start of {@link main.input.ProjectService}.
   * @return returns a {@link Map} with the stored objects.
   */
  public Map<String, T> getStoredObjects() {
    return storedObjects;
  }

  /**
   * Returns the counter of the projects/clients which already exists. Gets called at the program start of {@link main.input.ProjectService}
   * @return returns the seedCounter in {@link Long}
   */
  public long getSeedCounter() {
    return seedCounter;
  }

  /**
   * Writes new projects or existing projects with new canvas to the file "project.ser". Gets called whenever the Observer gets notified. If the parameter is a Map, the new Map gets serialized. If the parameter is the seedCounter, the seedCounter gets set to the new value.
   * @param obj is an {@link Object} - in our case either {@link Map} of projects, or {@link Long} with seedCounter
   */
  @Override
  public void update(Object obj) {
    localFileManagerLogger.info("PersistenceObserver.update() called!");
    if (obj instanceof Map) {
      try {
        storedObjects = (Map<String, T>) obj;
        FileOutputStream out = new FileOutputStream(fileDirectoryPath + "/projects.ser");
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(storedObjects);
        oout.close();
        out.close();
      } catch (Exception e) {
        localFileManagerLogger.error(e.getClass().getCanonicalName() +
            " in LocalFileManager.update(obj): " + e.getMessage());
      }
    } else if (obj instanceof Long) {
      try {
        seedCounter = (long) obj;
        FileOutputStream out = new FileOutputStream(fileDirectoryPath + "/seedCounter.ser");
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(seedCounter);
        oout.close();
        out.close();
      } catch (Exception e) {
        localFileManagerLogger.error(e.getClass().getCanonicalName() +
            " in LocalFileManager.update(obj): " + e.getMessage());
      }
    } else {
      localFileManagerLogger.error("Unexpected type: " + obj.getClass().getCanonicalName());
    }

    localFileManagerLogger.debug("Map after update:" + storedObjects.toString());

  }
}
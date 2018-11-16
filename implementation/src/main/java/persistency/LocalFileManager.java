package persistency;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class LocalFileManager<T extends Serializable> implements FileManager<T> {

  private final String fileDirectoryPath;
  private Map<String, T> storedObjects;

  public LocalFileManager(String fileDirectoryPath) {
    System.out.println("constructor called");
    // fileDirectoryPath is the path where subfolders (for each ID) are placed
    this.fileDirectoryPath = fileDirectoryPath;

    // TODO: Implement Constructor in LocalFileManager (Get Map from file system)
    storedObjects = new HashMap<String, T>();
  }

  public Map<String, T> getStoredObjects() {
    System.out.println("getStoredObjects called");
    return storedObjects;
  }

  @Override
  public void update(Observable o, Object arg) {
    System.out.println("update called");
    // TODO: Implement update(Observable, Object) in LocalFileManager
  }
}

package persistence;

import java.io.Serializable;
import java.util.Map;

public interface FileManager<T extends Serializable> extends PersistenceObserver {

  public Map<String, T> getStoredObjects();

  public long getSeedCounter();

}

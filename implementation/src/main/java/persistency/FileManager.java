package persistency;

import java.io.Serializable;
import java.util.Map;

public interface FileManager<T extends Serializable> extends Observer {

  public Map<String, T> getStoredObjects();

}

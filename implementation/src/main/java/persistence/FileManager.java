package persistence;

import java.io.Serializable;
import java.util.Map;
import observer.Observer;

public interface FileManager<T extends Serializable> extends Observer {

  Map<String, T> getStoredObjects();

  long getSeedCounter();

}

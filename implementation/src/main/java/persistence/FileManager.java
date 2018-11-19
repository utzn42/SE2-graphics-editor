package persistence;

import java.io.Serializable;
import java.util.Map;
import observer.Observer;

/**
 * This interface is for abstraction for {@link LocalFileManager}
 * @param <T> describes the type. In our case {@link canvas.Canvas}
 */
public interface FileManager<T extends Serializable> extends Observer {

  Map<String, T> getStoredObjects();

  long getSeedCounter();

}

package main.input;

import canvas.Canvas;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import org.springframework.stereotype.Service;
import persistency.FileManager;
import persistency.LocalFileManager;

@Service
public class ProjectService extends Observable {

  private Map<String, Canvas> projects = new HashMap<>();

  public ProjectService() {
    FileManager<Canvas> fileManager = new LocalFileManager<Canvas>("./projects");
    this.addObserver(fileManager);
    projects = fileManager.getStoredObjects();
    testUpdate();
  }

  public void testUpdate() {
    setChanged();
    notifyObservers(new Object());
  }

}

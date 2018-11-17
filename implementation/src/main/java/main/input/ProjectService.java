package main.input;

import canvas.Canvas;
import canvas.Layer;
import download.DownloadJPG;
import download.DownloadPNG;
import download.DownloadSVG;
import download.DownloadStrategy;
import facilitators.Hasher;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import org.apache.batik.transcoder.TranscoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import persistence.FileManager;
import persistence.LocalFileManager;
import persistence.PersistenceObserver;
import shapes.Shape;

@Service
public class ProjectService implements PersistenceSubject {

  //TODO: Utz -> Make seedCounter unique. Store int in file which is retrieved upon loading Projectservice.
  private static long seedCounter = 0;
  private static Logger projectServiceLogger = LoggerFactory.getLogger(ProjectService.class);
  private Map<String, Canvas> projects;
  private ArrayList<PersistenceObserver> observers;

  public ProjectService() {
    observers = new ArrayList<>();
    FileManager<Canvas> fileManager = new LocalFileManager<Canvas>("./projects");
    observers.add(fileManager);
    projects = fileManager.getStoredObjects();
    notifyObservers();
  }

  public String createID() {
    Hasher hash = new Hasher(++seedCounter);
    return hash.getHash();

  }

  public Canvas createCanvas(String projectID) {
    Canvas blankCanvas = new Canvas();
    projects.put(projectID, blankCanvas);
    notifyObservers();
    return blankCanvas;
  }

  public Canvas addLayer(String projectID) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("addLayer - (empty object)");

    canvas.getLayers().add(new Layer());
    projects.get(projectID).getLayers().add(new Layer());
    notifyObservers();

    return canvas;
  }

  public Canvas addShape(String projectID, int layerIndex, String shapeClass)
      throws Exception {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("addShape - Layer Index: " + layerIndex);
    projectServiceLogger.info("         - Shape Class: " + shapeClass);

    Shape newShape;
    try {
      newShape = (Shape) (Class.forName(shapeClass).newInstance());
      projects.get(projectID).getLayers().get(layerIndex).getShapes().add(newShape);
      projectServiceLogger.info("         - HTML: " + newShape.getHTML());
    } catch (ClassNotFoundException e) {
      projectServiceLogger.error("         - HTML: Failed to get Class");
      throw new ClassNotFoundException("Class " + shapeClass + " does not exist!");
    } catch (IllegalAccessException e) {
      projectServiceLogger.error("         - HTML: Could not access Class");
      throw new IllegalAccessException("layerIndex: " + layerIndex + ". No such layer found!");
    } catch (InstantiationException e) {
      projectServiceLogger.error("         - HTML: Could not instantiate Object for Class");
      throw new InstantiationException("Could not instantiate object of class " + shapeClass + ".");
    }

    notifyObservers();
    return canvas;
  }

  public Canvas editCanvas(String projectID, double width, double height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Height and width must both be positive!");
    }
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("editCanvas - Width: " + width);
    projectServiceLogger.info("           - Height: " + height);

    notifyObservers();
    return projects.get(projectID);

  }

  public Canvas editLayer(String projectID, int layerIndex, boolean isVisible) {
    if (projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("editLayer - Layer Index: " + layerIndex);
    projectServiceLogger.info("          - Visible: " + isVisible);
    projects.get(projectID).getLayers().get(layerIndex).setVisible(isVisible);

    notifyObservers();
    return projects.get(projectID);
  }

  public Canvas editShape(String projectID, int layerIndex, int shapeIndex, Shape shape) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("editShape - " + shape.getHTML());

    projects.get(projectID).getLayers().get(layerIndex).getShapes()
        .set(shapeIndex, shape);

    notifyObservers();
    return projects.get(projectID);
  }

  public Canvas transformShape(String projectID) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }

    // TODO: Transform Shape

    return projects.get(projectID);
  }

  public Canvas deleteLayer(String projectID, int layerIndex) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("deleteLayer - Layer Index: " + layerIndex);

    projects.get(projectID).getLayers().remove(layerIndex);

    notifyObservers();
    return projects.get(projectID);
  }

  public Canvas deleteShape(String projectID, int layerIndex, int shapeIndex) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("deleteShape - Layer Index: " + layerIndex);
    projectServiceLogger.info("            - Shape Index: " + shapeIndex);

    projects.get(projectID).getLayers().get(layerIndex).getShapes().remove(shapeIndex);

    return projects.get(projectID);
  }

  public ResponseEntity<Object> download(String projectID, String type)
      throws IOException, TranscoderException {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("Download in format: " + type);

    DownloadStrategy downloadStrategy;
    String mimeType;
    switch (type) {
      case "svg":
        downloadStrategy = new DownloadSVG();
        mimeType = "image/svg+xml";
        break;
      case "png":
        downloadStrategy = new DownloadPNG();
        mimeType = "image/png";
        break;
      case "jpg":
      case "jpeg":
        downloadStrategy = new DownloadJPG();
        mimeType = "image/jpeg";
        break;
      default:
        throw new IllegalArgumentException("Unknown file type");
    }

    URI fileURI = downloadStrategy.download(projects.get(projectID), projectID);
    File file = new File(fileURI);
    Path path = Paths.get(fileURI);
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
    InputStream in = getClass().getResourceAsStream(fileURI.getPath());

    //InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
    httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
    httpHeaders.add(HttpHeaders.EXPIRES, "0");

    return ResponseEntity.ok().headers(httpHeaders).contentLength(file.length()).contentType(
        MediaType.parseMediaType(mimeType)).body(resource);
  }


  /*public void testUpdate() {
    setChanged();
    notifyObservers(new Object());
  }*/

  public Map<String, Canvas> getProjects() {
    return projects;
  }

  @Override
  public void registerObserver(PersistenceObserver o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(PersistenceObserver o) {
    int i = observers.indexOf(o);
    if (i >= 0) {
      observers.remove(i);
    }
  }

  @Override
  public void notifyObservers() {
    for (PersistenceObserver o : observers) {
      o.update(projects);
    }
  }
}



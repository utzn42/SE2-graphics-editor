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
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import observer.Observer;
import observer.Subject;
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
import shapes.Shape;

/**
 * This class provides the logic which RESTHandler calls upon to deliver its functionality.
 *
 * @see RESTHandler
 */
@Service
public class ProjectService implements Subject {

  private static long seedCounter = 0;
  private static Logger projectServiceLogger = LoggerFactory.getLogger(ProjectService.class);
  private Map<String, Canvas> projects;
  private ArrayList<Observer> observers;

  /**
   * Creates a default ProjectService object. If previously stored projects or ID seeds are available, they are loaded into the object.
   * @see LocalFileManager
   */
  public ProjectService() {
    observers = new ArrayList<>();
    FileManager<Canvas> fileManager = new LocalFileManager<>("./projects");
    registerObserver(fileManager);
    projects = fileManager.getStoredObjects();
    seedCounter = fileManager.getSeedCounter();
  }

  /**
   * Creates a one-way hash of the ID seed.
   * @return A 6-letter substring of the SHA-256 hash of the ID seed.
   */
  public String createID() {
    String projectID;
    do {
      Hasher hash = new Hasher(++seedCounter);
      projectID = hash.getHash();
    } while (projects.containsKey(projectID));
    return projectID;
  }


  /**
   * Creates a default canvas measuring 200*200px.
   * @param projectID Is used to retrieve the canvas based on the ID.
   * @return A default, empty canvas.
   * @see Canvas
   */
  public Canvas createCanvas(String projectID) {
    if (projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " already exists!");
    }

    Canvas blankCanvas = new Canvas();
    projects.put(projectID, blankCanvas);

    notifyObservers();
    return blankCanvas;
  }


  /**
   * Adds a layer to the canvas which is specified by the project ID.
   * @param projectID Is used to retrieve the canvas based on the ID.
   * @return The canvas including the layer which was added.
   */
  public Canvas addLayer(String projectID) {
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("addLayer - (empty object)");

    projects.get(projectID).getLayers().add(new Layer());

    notifyObservers();
    return projects.get(projectID);
  }


  /**
   * Adds a shape to a specified layer within the canvas, which is specified by the project ID.
   *
   * @param projectID Is used to retrieve the canvas based on the ID.
   * @param layerIndex Is used to access the layer to which the shape shall be added.
   * @param shapeClass Specifies the type of shape which is to be added, in order to call default constructors.
   * @return A canvas including the added shape.
   * @throws Exception
   */
  public Canvas addShape(String projectID, int layerIndex, String shapeClass)
      throws Exception {
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

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
      projectServiceLogger.error("         - HTML: Could not call Class constructor");
      throw new IllegalAccessException("Could not call constructor for class " + shapeClass + "!");
    } catch (InstantiationException e) {
      projectServiceLogger.error("         - HTML: Could not instantiate Object for Class");
      throw new InstantiationException("Failed to instantiate object of class " + shapeClass + "!");
    }

    notifyObservers();
    return projects.get(projectID);
  }


  /**
   * Modifies the width/height of a given canvas.
   * @param projectID Is used to retrieve the canvas based on the ID.
   * @param width Specifies the new canvas width.
   * @param height Specifies the new canvas height.
   * @return The canvas featuring the updated measurements.
   */
  public Canvas editCanvas(String projectID, double width, double height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Height and width must both be positive!");
    }
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("editCanvas - Width: " + width);
    projectServiceLogger.info("           - Height: " + height);

    projects.get(projectID).setWidth(width);
    projects.get(projectID).setHeight(height);

    notifyObservers();
    return projects.get(projectID);

  }

  public Canvas editLayer(String projectID, int layerIndex, boolean isVisible) {
    if (!projects.containsKey(projectID)) {
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
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("editShape - " + shape.getHTML());

    projects.get(projectID).getLayers().get(layerIndex).getShapes()
        .set(shapeIndex, shape);

    notifyObservers();
    return projects.get(projectID);
  }

  public Canvas transformShape(String projectID) {
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    // TODO: Transform Shape

    return projects.get(projectID);
  }

  public Canvas deleteLayer(String projectID, int layerIndex) {
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("deleteLayer - Layer Index: " + layerIndex);

    projects.get(projectID).getLayers().remove(layerIndex);

    notifyObservers();
    return projects.get(projectID);
  }

  public Canvas deleteShape(String projectID, int layerIndex, int shapeIndex) {
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("deleteShape - Layer Index: " + layerIndex);
    projectServiceLogger.info("            - Shape Index: " + shapeIndex);

    projects.get(projectID).getLayers().get(layerIndex).getShapes().remove(shapeIndex);

    return projects.get(projectID);
  }

  public ResponseEntity<Object> download(String projectID, String type)
      throws IOException, TranscoderException {
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
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
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

    return ResponseEntity.ok().headers(httpHeaders).contentLength(file.length()).contentType(
        MediaType.parseMediaType(mimeType)).body(resource);
  }

  public Map<String, Canvas> getProjects() {
    return projects;
  }

  @Override
  public void registerObserver(Observer o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    int i = observers.indexOf(o);
    if (i >= 0) {
      observers.remove(i);
    }
  }

  @Override
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update(seedCounter);
      o.update(projects);
    }
  }
}



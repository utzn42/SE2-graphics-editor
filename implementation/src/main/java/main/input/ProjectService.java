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
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import org.apache.batik.transcoder.TranscoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import persistency.FileManager;
import persistency.LocalFileManager;
import shapes.Shape;

@Service
public class ProjectService extends Observable {

  //TODO: Utz -> Make seedCounter unique. Store int in file which is retrieved upon loading Projectservice.
  private static long seedCounter = 0;
  private static Logger projectServiceLogger = LoggerFactory.getLogger(ProjectService.class);
  private Map<String, Canvas> projects = new HashMap<>();

  public ProjectService() {
    FileManager<Canvas> fileManager = new LocalFileManager<Canvas>("./projects");
    this.addObserver(fileManager);
    projects = fileManager.getStoredObjects();
    testUpdate();
  }

  public String createID() {
    Hasher hash = new Hasher(++seedCounter);
    return hash.getHash();

  }

  public Canvas createCanvas(String projectID) {
    Canvas blankCanvas = new Canvas();
    projects.put(projectID, blankCanvas);
    return blankCanvas;
  }

  public Canvas addLayer(String projectID) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("addLayer - (empty object)");

    canvas.getLayers().add(new Layer());

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

    return canvas;
  }

  public Canvas editCanvas(String projectID, double width, double height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Height and width must both be positive!");
    }
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("editCanvas - Width: " + width);
    projectServiceLogger.info("           - Height: " + height);

    canvas.setWidth(width);
    canvas.setHeight(height);

    return canvas;
  }

  public Canvas editLayer(String projectID, int layerIndex, boolean isVisible) {
    if (projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("editLayer - Layer Index: " + layerIndex);
    projectServiceLogger.info("          - Visible: " + isVisible);
    canvas.getLayers().get(layerIndex).setVisible(isVisible);

    return canvas;
  }

  public Canvas editShape(String projectID, int layerIndex, int shapeIndex, Shape shape) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("editShape - " + shape.getHTML());

    canvas.getLayers().get(layerIndex).getShapes()
        .set(shapeIndex, shape);

    return canvas;
  }

  public Canvas transformShape(String projectID) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Transform Shape

    return canvas;
  }

  public Canvas deleteLayer(String projectID, int layerIndex) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("deleteLayer - Layer Index: " + layerIndex);

    canvas.getLayers().remove(layerIndex);

    return canvas;
  }

  public Canvas deleteShape(String projectID, int layerIndex, int shapeIndex) {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

    projectServiceLogger.info("deleteShape - Layer Index: " + layerIndex);
    projectServiceLogger.info("            - Shape Index: " + shapeIndex);

    canvas.getLayers().get(layerIndex).getShapes().remove(shapeIndex);

    return canvas;
  }

  public ResponseEntity<Object> download(String projectID, String type)
      throws IOException, TranscoderException {
    if (!projects.containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = projects.get(projectID);

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

    URI fileURI = downloadStrategy.download(canvas, projectID);
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


  public void testUpdate() {
    setChanged();
    notifyObservers(new Object());
  }

  public Map<String, Canvas> getProjects() {
    return projects;
  }
}

//TODO:Error handling



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
import messages.RequestAddLayer;
import messages.RequestAddShape;
import messages.RequestDeleteLayer;
import messages.RequestDeleteShape;
import messages.RequestEditCanvas;
import messages.RequestEditLayer;
import messages.RequestEditShape;
import messages.ServerResponse;
import org.apache.batik.transcoder.TranscoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shapes.Shape;

@RestController
public class RESTHandler {

  private static Logger restHandlerLogger = LoggerFactory.getLogger(RESTHandler.class);
  private static long seedCounter = 0;
  private static Map<String, Canvas> projects = new HashMap<>();

  static Map<String, Canvas> getProjects() {
    return projects;
  }

  //@Autowired
  //private ProjectService;

  @CrossOrigin()
  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ServerResponse createProject() {
    Hasher hash = new Hasher(++seedCounter);
    String id = hash.getHash();
    Canvas canvas = new Canvas();
    projects.put(id, canvas);
    ServerResponse response = new ServerResponse(id);
    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse addLayer(@PathVariable String projectID,
      @RequestBody RequestAddLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("addLayer - (empty object)");

    canvas.getLayers().add(new Layer());

    response.setCanvas(canvas);

    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse addShape(@PathVariable String projectID,
      @RequestBody RequestAddShape request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("addShape - Layer Index: " + request.getLayerIndex());
    restHandlerLogger.info("         - Shape Class: " + request.getShapeClass());

    Shape newShape;
    try {
      newShape = (Shape) (Class.forName(request.getShapeClass()).newInstance());
      canvas.getLayers().get(request.getLayerIndex()).getShapes().add(newShape);
      // For debugging and reference: Constructing an instance of the received shape class
      restHandlerLogger.info("         - HTML: " + newShape.getHTML());
    } catch (ClassNotFoundException e) {
      restHandlerLogger.error("         - HTML: Failed to get Class");
    } catch (IllegalAccessException e) {
      restHandlerLogger.error("         - HTML: Could not access Class");
    } catch (InstantiationException e) {
      restHandlerLogger.error("         - HTML: Could not instantiate Object for Class");
    }

    response.setCanvas(canvas);

    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editCanvas/{projectID}", method = RequestMethod.POST)
  public ServerResponse editCanvas(@PathVariable String projectID,
      @RequestBody RequestEditCanvas request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("editCanvas - Width: " + request.getWidth());
    restHandlerLogger.info("           - Height: " + request.getHeight());

    canvas.setWidth(request.getWidth());
    canvas.setHeight(request.getHeight());

    response.setCanvas(canvas);
    return response;
  }


  @CrossOrigin()
  @RequestMapping(value = "/editLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse editLayer(@PathVariable String projectID,
      @RequestBody RequestEditLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("editLayer - Layer Index: " + request.getLayerIndex());
    restHandlerLogger.info("          - Visible: " + request.isVisible());
    canvas.getLayers().get(request.getLayerIndex()).setVisible(request.isVisible());

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse editShape(@PathVariable String projectID,
      @RequestBody RequestEditShape request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("editShape - " + request.getShape().getHTML());

    canvas.getLayers().get(request.getLayerIndex()).getShapes()
        .set(request.getShapeIndex(), request.getShape());

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/transformShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse transformShape(@PathVariable String projectID,
      @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    // TODO: Transform Shape

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse deleteLayer(@PathVariable String projectID,
      @RequestBody RequestDeleteLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("deleteLayer - Layer Index: " + request.getLayerIndex());

    canvas.getLayers().remove(request.getLayerIndex());

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteShape/{projectID}")
  public ServerResponse deleteShape(@PathVariable String projectID,
      @RequestBody RequestDeleteShape request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("deleteShape - Layer Index: " + request.getLayerIndex());
    restHandlerLogger.info("            - Shape Index: " + request.getShapeIndex());

    canvas.getLayers().get(request.getLayerIndex()).getShapes().remove(request.getShapeIndex());

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(produces = {"image/svg+xml", "image/png",
      "image/jpeg"}, value = "/download/{projectID}/{type}", method = RequestMethod.GET)
  public ResponseEntity<Object> download(@PathVariable String projectID, @PathVariable String type)
      throws IOException, TranscoderException {
    //ServerResponse response = new ServerResponse(projectID);

    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restHandlerLogger.info("Download in format: " + type);

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
}

//TODO: Utz - save seedCounter to file and retrieve upon restart, move functionality to Projectservice

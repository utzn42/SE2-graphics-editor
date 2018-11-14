package main.input;

import canvas.Canvas;
import canvas.Layer;
import facilitators.Hasher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import messages.RequestAddLayer;
import messages.RequestAddShape;
import messages.RequestDeleteLayer;
import messages.RequestDeleteShape;
import messages.RequestEditCanvas;
import messages.RequestEditLayer;
import messages.RequestEditShape;
import messages.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shapes.Circle;
import shapes.Ellipse;
import shapes.Line;
import shapes.Polygon;
import shapes.RegularPolygon;
import shapes.Shape;
import shapes.Star;
import shapes.Text;

@RestController
public class RESTHandler {

  private static Logger restLogger = LoggerFactory.getLogger(RESTHandler.class);
  private static long seedCounter = 0;
  private Map<String, Canvas> projects = new HashMap<>();

  @CrossOrigin()
  @RequestMapping(value = "/test")
  public ServerResponse test() {
    List<Layer> layers = new ArrayList<>();
    List<Shape> shapes1 = new ArrayList<>();
    List<Shape> shapes2 = new ArrayList<>();
    List<Shape> shapes3 = new ArrayList<>();
    shapes1.add(new Circle());
    shapes2.add(new Ellipse());
    shapes3.add(new Line());
    shapes1.add(new Polygon());
    shapes2.add(new RegularPolygon());
    shapes3.add(new Star());
    shapes1.add(new Text());
    layers.add(new Layer(shapes1));
    layers.add(new Layer(shapes2));
    layers.add(new Layer(shapes3));
    Canvas canvas = new Canvas(layers);
    ServerResponse response = new ServerResponse("TestID");
    response.setCanvas(canvas);
    projects.put("TestID", canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/create")
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
  @RequestMapping(value = "/editCanvas/{projectID}", method = RequestMethod.POST)
  public ServerResponse editCanvas(@PathVariable String projectID,
      @RequestBody RequestEditCanvas request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    restLogger.info("editCanvas - Width: " + request.getWidth());
    restLogger.info("           - Height: " + request.getHeight());

    // TODO: Edit Canvas

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse addLayer(@PathVariable String projectID,
      @RequestBody RequestAddLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    restLogger.info("addLayer - (empty object)");

    // TODO: Add Layer to Canvas

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse editLayer(@PathVariable String projectID,
      @RequestBody RequestEditLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    restLogger.info("editLayer - Layer Index: " + request.getLayerIndex());
    restLogger.info("          - Visible: " + request.isVisible());

    // TODO: Edit Layer

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse deleteLayer(@PathVariable String projectID,
      @RequestBody RequestDeleteLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    restLogger.info("deleteLayer - Layer Index: " + request.getLayerIndex());

    // TODO: Delete Layer

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse addShape(@PathVariable String projectID, @RequestBody RequestAddShape request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    restLogger.info("addShape - Layer Index: " + request.getLayerIndex());
    restLogger.info("         - Shape Class: " + request.getShapeClass());
    Shape newShape;
    try {
      newShape = (Shape) (Class.forName(request.getShapeClass()).newInstance());
      canvas.getLayers().get(request.getLayerIndex()).getShapes().add(newShape);
      // For debugging and reference: Constructing an instance of the received shape class
      restLogger.info("         - HTML: " + newShape.getHTML());
    } catch (ClassNotFoundException e) {
      restLogger.error("         - HTML: Failed to get Class");
    } catch (IllegalAccessException e) {
      restLogger.error("         - HTML: Could not access Class");
    } catch (InstantiationException e) {
      restLogger.error("         - HTML: Could not instantiate Object for Class");
    }

    // TODO: Add Shape to Layer

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse editShape(@PathVariable String projectID,
      @RequestBody RequestEditShape request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    restLogger.info("editShape - " + request.getShape().getHTML());

    // TODO: Edit Shape

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/transformShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse transformShape(@PathVariable String projectID,
      @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Transform Shape

    response.setCanvas(canvas);
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteShape/{projectID}")
  public ServerResponse deleteShape(@PathVariable String projectID,
      @RequestBody RequestDeleteShape request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    restLogger.info("deleteShape - Layer Index: " + request.getLayerIndex());
    restLogger.info("            - Shape Index: " + request.getShapeIndex());

    // TODO: Delete Shape

    response.setCanvas(canvas);
    return response;
  }
}

//TODO: Subdivide responsibilities (RESTAdder, RESTEditor)
//TODO: Jackson annotation reference to parse JSON to and from POJO: https://www.baeldung.com/jackson-annotations

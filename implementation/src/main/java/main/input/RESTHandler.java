package main.input;

import canvas.Canvas;
import canvas.Layer;
import facilitators.Hasher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import messages.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    return response;
  }

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

  @RequestMapping(value = "/addLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse addLayer(@PathVariable String projectID, @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Add Layer to Canvas

    response.setCanvas(canvas);
    return response;
  }

  @RequestMapping(value = "/editLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse editLayer(@PathVariable String projectID, @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Edit Layer

    response.setCanvas(canvas);
    return response;
  }

  @RequestMapping(value = "/deleteLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse deleteLayer(@PathVariable String projectID, @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Delete Layer

    response.setCanvas(canvas);
    return response;
  }

  @RequestMapping(value = "/addShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse addShape(@PathVariable String projectID, @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Add Shape to Layer

    response.setCanvas(canvas);
    return response;
  }

  @RequestMapping(value = "/editShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse editShape(@PathVariable String projectID, @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Edit Shape

    response.setCanvas(canvas);
    return response;
  }

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

  @RequestMapping(value = "/deleteShape/{projectID}")
  public ServerResponse deleteShape(@PathVariable String projectID, @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!projects.containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = projects.get(projectID);

    // TODO: Delete Shape

    response.setCanvas(canvas);
    return response;
  }
}

//TODO: remove org.json.JSONObject dependency from project (no longer needed)
//TODO: Jackson annotation reference to parse JSON to and from POJO: https://www.baeldung.com/jackson-annotations

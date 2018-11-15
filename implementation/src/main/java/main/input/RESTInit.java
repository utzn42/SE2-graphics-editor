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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class RESTInit {

  private static Logger restInitLogger = LoggerFactory.getLogger(RESTInit.class);
  private static long seedCounter = 0;
  private static Map<String, Canvas> projects = new HashMap<>();

  static Map<String, Canvas> getProjects() {
    return projects;
  }

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
}

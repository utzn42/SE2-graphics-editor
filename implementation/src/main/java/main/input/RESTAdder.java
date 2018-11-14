package main.input;

import static main.input.RESTInit.getProjects;

import canvas.Canvas;
import canvas.Layer;
import messages.RequestAddShape;
import messages.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shapes.Shape;

@RestController
public class RESTAdder {

  private static Logger restAdderLogger = LoggerFactory.getLogger(RESTAdder.class);

  @CrossOrigin()
  @RequestMapping(value = "/addLayer/{projectID}")
  public ServerResponse addLayer(@PathVariable String projectID) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = getProjects().get(projectID);

    restAdderLogger.info("addLayer - (empty object)");

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
      response
          .addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
      return response;
    }
    Canvas canvas = getProjects().get(projectID);

    restAdderLogger.info("addShape - Layer Index: " + request.getLayerIndex());
    restAdderLogger.info("         - Shape Class: " + request.getShapeClass());

    Shape newShape;
    try {
      newShape = (Shape) (Class.forName(request.getShapeClass()).newInstance());
      canvas.getLayers().get(request.getLayerIndex()).getShapes().add(newShape);
      // For debugging and reference: Constructing an instance of the received shape class
      restAdderLogger.info("         - HTML: " + newShape.getHTML());
    } catch (ClassNotFoundException e) {
      restAdderLogger.error("         - HTML: Failed to get Class");
    } catch (IllegalAccessException e) {
      restAdderLogger.error("         - HTML: Could not access Class");
    } catch (InstantiationException e) {
      restAdderLogger.error("         - HTML: Could not instantiate Object for Class");
    }

    response.setCanvas(canvas);
    return response;
  }

}

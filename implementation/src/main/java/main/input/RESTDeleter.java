package main.input;

import static main.input.RESTInit.getProjects;

import canvas.Canvas;
import messages.RequestDeleteLayer;
import messages.RequestDeleteShape;
import messages.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTDeleter {

  private static Logger restDeleterLogger = LoggerFactory.getLogger(RESTDeleter.class);

  @CrossOrigin()
  @RequestMapping(value = "/deleteLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse deleteLayer(@PathVariable String projectID,
      @RequestBody RequestDeleteLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restDeleterLogger.info("deleteLayer - Layer Index: " + request.getLayerIndex());

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

    restDeleterLogger.info("deleteShape - Layer Index: " + request.getLayerIndex());
    restDeleterLogger.info("            - Shape Index: " + request.getShapeIndex());

    canvas.getLayers().get(request.getLayerIndex()).getShapes().remove(request.getShapeIndex());

    response.setCanvas(canvas);
    return response;
  }
}

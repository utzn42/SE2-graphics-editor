package main.input;

import static main.input.RESTInit.getProjects;

import canvas.Canvas;
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

@RestController
public class RESTEditor {

  private static Logger restEditorLogger = LoggerFactory.getLogger(RESTEditor.class);

  @CrossOrigin()
  @RequestMapping(value = "/editCanvas/{projectID}", method = RequestMethod.POST)
  public ServerResponse editCanvas(@PathVariable String projectID,
      @RequestBody RequestEditCanvas request) {
    ServerResponse response = new ServerResponse(projectID);
    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restEditorLogger.info("editCanvas - Width: " + request.getWidth());
    restEditorLogger.info("           - Height: " + request.getHeight());

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

    restEditorLogger.info("editLayer - Layer Index: " + request.getLayerIndex());
    restEditorLogger.info("          - Visible: " + request.isVisible());

    // TODO: Maybe move this to a separate class, add checks for illegal requests etc.
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

    restEditorLogger.info("editShape - " + request.getShape().getHTML());

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
}

package main.input;

import canvas.Canvas;
import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTHandler {

  private static Logger restHandlerLogger = LoggerFactory.getLogger(RESTHandler.class);

  private static Map<String, Canvas> projects = new HashMap<>();


  @Autowired
  private ProjectService projectService;

  @CrossOrigin()
  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ServerResponse createProject() {
    ServerResponse response = new ServerResponse();
    response.setProjectID(projectService.createID());
    response.setCanvas(projectService.createCanvas());
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse addLayer(@PathVariable String projectID,
      @RequestBody RequestAddLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.addLayer(projectID));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse addShape(@PathVariable String projectID,
      @RequestBody RequestAddShape request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(
        projectService.addShape(projectID, request.getLayerIndex(), request.getShapeClass()));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editCanvas/{projectID}", method = RequestMethod.POST)
  public ServerResponse editCanvas(@PathVariable String projectID,
      @RequestBody RequestEditCanvas request) {
    ServerResponse response = new ServerResponse(projectID);
    response
        .setCanvas(projectService.editCanvas(projectID, request.getWidth(), request.getHeight()));
    return response;
  }


  @CrossOrigin()
  @RequestMapping(value = "/editLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse editLayer(@PathVariable String projectID,
      @RequestBody RequestEditLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(
        projectService.editLayer(projectID, request.getLayerIndex(), request.isVisible()));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse editShape(@PathVariable String projectID,
      @RequestBody RequestEditShape request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService
        .editShape(projectID, request.getLayerIndex(), request.getShapeIndex(),
            request.getShape()));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/transformShape/{projectID}", method = RequestMethod.POST)
  public ServerResponse transformShape(@PathVariable String projectID,
      @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.transformShape(projectID));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteLayer/{projectID}", method = RequestMethod.POST)
  public ServerResponse deleteLayer(@PathVariable String projectID,
      @RequestBody RequestDeleteLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.deleteLayer(projectID, request.getLayerIndex()));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteShape/{projectID}")
  public ServerResponse deleteShape(@PathVariable String projectID,
      @RequestBody RequestDeleteShape request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(
        projectService.deleteShape(projectID, request.getLayerIndex(), request.getShapeIndex()));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(produces = {"image/svg+xml", "image/png",
      "image/jpeg"}, value = "/download/{projectID}/{type}", method = RequestMethod.GET)
  public ResponseEntity<Object> download(@PathVariable String projectID, @PathVariable String type)
      throws IOException, TranscoderException {
    return projectService.download(projectID, type);
  }
}

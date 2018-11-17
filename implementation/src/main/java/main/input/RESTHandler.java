package main.input;

import java.io.IOException;
import messages.ErrorResponse;
import messages.RequestAddLayer;
import messages.RequestAddShape;
import messages.RequestDeleteLayer;
import messages.RequestDeleteShape;
import messages.RequestEditCanvas;
import messages.RequestEditLayer;
import messages.RequestEditShape;
import messages.Response;
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

  @Autowired
  private ProjectService projectService;

  @CrossOrigin()
  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public Response createProject() {
    ServerResponse response = new ServerResponse();
    response.setProjectID(projectService.createID());
    response.setCanvas(projectService.createCanvas(response.getProjectID()));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addLayer/{projectID}", method = RequestMethod.POST)
  public Response addLayer(@PathVariable String projectID,
      @RequestBody RequestAddLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.addLayer(projectID));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/addShape/{projectID}", method = RequestMethod.POST)
  public Response addShape(@PathVariable String projectID,
      @RequestBody RequestAddShape request) {

    ServerResponse response = new ServerResponse(projectID);

    try {
      response.setCanvas(
          projectService.addShape(projectID, request.getLayerIndex(), request.getShapeClass()));
    } catch (ClassNotFoundException e) {
      return new ErrorResponse("Class " + request.getShapeClass() + " does not exist.",
          "/addShape/" + projectID);
    } catch (IllegalAccessException e) {
      return new ErrorResponse("layerIndex " + request.getLayerIndex() + " does not exist.",
          "/addShape/" + projectID);
    } catch (InstantiationException e) {
      return new ErrorResponse("Could not instantiate object of class  " + request.getShapeClass(),
          "/addShape/" + projectID);
    } catch (Exception e) {
      return new ErrorResponse("Unknown error.",
          "/addShape/" + projectID);
    }

    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editCanvas/{projectID}", method = RequestMethod.POST)
  public Response editCanvas(@PathVariable String projectID,
      @RequestBody RequestEditCanvas request) {

    ServerResponse response = new ServerResponse(projectID);

    try {
      response
          .setCanvas(projectService.editCanvas(projectID, request.getWidth(), request.getHeight()));
    } catch (IllegalArgumentException e) {
      return new ErrorResponse("Height and Width must both be positive!",
          "/editCanvas/" + projectID);
    } catch (IndexOutOfBoundsException e) {
      return new ErrorResponse("projectID does not exist!", "/editCanvas/" + projectID);
    }

    return response;
  }


  @CrossOrigin()
  @RequestMapping(value = "/editLayer/{projectID}", method = RequestMethod.POST)
  public Response editLayer(@PathVariable String projectID,
      @RequestBody RequestEditLayer request) {

    if (request.getLayerIndex() > projectService.getProjects().get(projectID).getLayers().size()) {
      return new ErrorResponse(
          "layerIndex out of bounds! Was: " + request.getLayerIndex() + " - Maximum layer index: "
              + (projectService.getProjects().get(projectID).getLayers().size() - 1),
          "/editlayer/" + projectID);
    }

    ServerResponse response = new ServerResponse(projectID);

    try {
      response.setCanvas(
          projectService.editLayer(projectID, request.getLayerIndex(), request.isVisible()));
    } catch (IndexOutOfBoundsException e) {
      return new ErrorResponse("projectID does not exist!", "/editLayer/" + projectID);
    }

    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/editShape/{projectID}", method = RequestMethod.POST)
  public Response editShape(@PathVariable String projectID,
      @RequestBody RequestEditShape request) {

    ServerResponse response = new ServerResponse(projectID);

    try {
      response.setCanvas(projectService
          .editShape(projectID, request.getLayerIndex(), request.getShapeIndex(),
              request.getShape()));
    } catch (IllegalArgumentException e) {
      return new ErrorResponse("projectID does not exist!", "/editShape/" + projectID);
    }

    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/transformShape/{projectID}", method = RequestMethod.POST)
  public Response transformShape(@PathVariable String projectID,
      @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.transformShape(projectID));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteLayer/{projectID}", method = RequestMethod.POST)
  public Response deleteLayer(@PathVariable String projectID,
      @RequestBody RequestDeleteLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.deleteLayer(projectID, request.getLayerIndex()));
    return response;
  }

  @CrossOrigin()
  @RequestMapping(value = "/deleteShape/{projectID}", method = RequestMethod.POST)
  public Response deleteShape(@PathVariable String projectID,
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
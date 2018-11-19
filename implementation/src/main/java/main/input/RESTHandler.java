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


/**
 * Primary class responsible for communication between the client and the server.
 */
@RestController
public class RESTHandler {

  private static Logger restHandlerLogger = LoggerFactory.getLogger(RESTHandler.class);

  @Autowired
  private ProjectService projectService;

  /**
   * Creates a project by subsequently getting a project ID and a default Canvas from the server.
   *
   * @return A server response including an ID and an empty canvas of default dimensions.
   * @see ServerResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public Response createProject() {
    ServerResponse response = new ServerResponse();
    response.setProjectID(projectService.createID());
    response.setCanvas(projectService.createCanvas(response.getProjectID()));
    return response;
  }

  /**
   * Adds a layer to the canvas specified by the project ID.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body transmitted by the client.
   * @return If successfully called, it returns a canvas which has an additional layer. Otherwise, a
   * basic ErrorResponse is returned.
   * @see RequestAddLayer
   * @see ServerResponse
   * @see ErrorResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/addLayer/{projectID}", method = RequestMethod.POST)
  public Response addLayer(@PathVariable String projectID,
      @RequestBody RequestAddLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    try {
      response.setCanvas(projectService.addLayer(projectID));
      return response;
    } catch (Exception e) {
      return new ErrorResponse(
          "Unexpected error while trying to add layer to canvas @ " + projectID,
          "/addLayer/" + projectID);
    }
  }

  /**
   * Adds a shape to a specified layer within the canvas, which is specified by the project ID.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body, which specifies the shape which is to be added as well as the corresponding layer.
   * @return If successfully called, it returns a canvas which has an additional shape in the corresponding layer. Otherwise, a basic ErrorResponse is returned.
   * @see RequestAddShape
   * @see ServerResponse
   * @see ErrorResponse
   */
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


  /**
   * Edits the dimensions of a canvas which is accessed via the corresponding project ID.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body, which specifies the desired height and width for the new canvas.
   * @return If successfully called, it returns a canvas which measures request.getWidth() in width and request.getHeight() in height.
   * @see RequestEditCanvas
   * @see ServerResponse
   * @see ErrorResponse
   */
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


  /**
   * Sets the visibility of a certain layer.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body, which specifies the layer which the user wishes to access and the desired visibility.
   * @return If successfully called, it returns the canvas where the visibility of the layer specified by request.getLayerIndex() has been edited.
   * @see RequestEditLayer
   * @see ServerResponse
   * @see ErrorResponse
   */
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


  /**
   * Modifies the properties of a shape within a certain layer.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body, which specifies the layer which the user wishes to access and the modified shape attributes.
   * @return If successfully called, it returns a canvas with an updated version of the shape specified in the request.
   * @see RequestEditShape
   * @see ServerResponse
   * @see ErrorResponse
   */
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


  /**
   * Modifies the visual representation of a shape within a certain layer using the SVG format's inbuilt transform parameters.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body, which specifies the layer which the user wishes to access and SVG transform parameters.
   * @return If successfully called, it returns a canvas with an transformed version of the shape specified in the request.
   * @see ServerResponse
   * @see ErrorResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/transformShape/{projectID}", method = RequestMethod.POST)
  public Response transformShape(@PathVariable String projectID,
      @RequestBody String request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.transformShape(projectID));
    return response;
  }


  /**
   * Deletes a layer from the canvas whiich is accessed via the project ID..
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body, which specifies the layer which the user wishes to delete.
   * @return If successfully called, it returns an updated canvas which is missing the layer specified in request.getLayerIndex().
   * @see RequestDeleteLayer
   * @see ServerResponse
   * @see ErrorResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/deleteLayer/{projectID}", method = RequestMethod.POST)
  public Response deleteLayer(@PathVariable String projectID,
      @RequestBody RequestDeleteLayer request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(projectService.deleteLayer(projectID, request.getLayerIndex()));
    return response;
  }


  /**
   * Deletes a shape within a certain layer of the canvas.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body, which specifies the layer which the user wishes to delete.
   * @return If successfully called, it returns a canvas with an updated layer which is missing the shape in request.getShapeIndex().
   * @see RequestDeleteShape
   * @see ServerResponse
   * @see ErrorResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/deleteShape/{projectID}", method = RequestMethod.POST)
  public Response deleteShape(@PathVariable String projectID,
      @RequestBody RequestDeleteShape request) {
    ServerResponse response = new ServerResponse(projectID);
    response.setCanvas(
        projectService.deleteShape(projectID, request.getLayerIndex(), request.getShapeIndex()));
    return response;
  }


  /**
   * Downloads the canvas in the desired file format.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param type Specifies the file format.
   * @return If successfully called, it returns a .svg/.png/.jpg file, which shows a representation of the most recent canvas.
   * @see ResponseEntity
   */
  @CrossOrigin()
  @RequestMapping(produces = {"image/svg+xml", "image/png",
      "image/jpeg"}, value = "/download/{projectID}/{type}", method = RequestMethod.GET)
  public ResponseEntity<Object> download(@PathVariable String projectID, @PathVariable String type)
      throws IOException, TranscoderException {
    return projectService.download(projectID, type);
  }
}
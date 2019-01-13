package main.input;

import download.DownloadFormat;
import messages.ErrorResponse;
import messages.RequestAddGroupLayer;
import messages.RequestAddShape;
import messages.RequestDeleteLayer;
import messages.RequestDeleteShape;
import messages.RequestEditCanvas;
import messages.RequestEditLayer;
import messages.RequestModifyShape;
import messages.RequestTransformElement;
import messages.Response;
import messages.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.Project;


/**
 * Primary class responsible for communication between the client and the server.
 */
@RestController
public class RESTHandler {

  private static Logger restHandlerLogger = LoggerFactory.getLogger(RESTHandler.class);

  private final ProjectService projectService;

  @Autowired
  public RESTHandler(ProjectService projectService) {
    this.projectService = projectService;
  }

  /**
   * Creates a project by subsequently getting a project ID and a default Canvas from the server.
   *
   * @return A server response including an ID and an empty canvas of default dimensions.
   * @see ServerResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public Response createProject() {

    try {

      Project project = projectService.createProject();
      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /create; returning ErrorResponse");
      return new ErrorResponse(e.getMessage(), "/create");

    }

  }

  /**
   * Returns the project with the requested project ID.
   *
   * @param projectID The project ID.
   * @return A server response including the ID and canvas of the requested project.
   */
  @CrossOrigin()
  @RequestMapping(value = "/{projectID}", method = RequestMethod.GET)
  public Response getProject(@PathVariable String projectID) {

    ServerResponse response = new ServerResponse();

    try {

      Project project = projectService.getProject(projectID);
      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "; returning ErrorResponse");
      return new ErrorResponse(e.getMessage(), "/" + projectID);

    }

  }

  /**
   * Adds a layer to the canvas specified by the project ID.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param request The HTTP POST request body transmitted by the client.
   * @return If successfully called, it returns a canvas which has an additional layer. Otherwise, a
   * basic ErrorResponse is returned.
   * @see RequestAddGroupLayer
   * @see ServerResponse
   * @see ErrorResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/{projectID}/addGroupLayer", method = RequestMethod.POST)
  public Response addGroupLayer(@PathVariable String projectID,
      @RequestBody RequestAddGroupLayer request) {

    try {

      Project project = projectService.getProject(projectID);
      projectService.addLayer(project);

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/addGroupLayer; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
           "/" + projectID + "/addGroupLayer");

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
  @RequestMapping(value = "/{projectID}/addShape", method = RequestMethod.POST)
  public Response addShape(@PathVariable String projectID,
      @RequestBody RequestAddShape request) {

    try {

      Project project = projectService.getProject(projectID);
      projectService.addShape(project, request);

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/addShape; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
          "/" + projectID + "/addShape");

    }

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
  @RequestMapping(value = "/{projectID}/editCanvas", method = RequestMethod.POST)
  public Response editCanvas(@PathVariable String projectID,
      @RequestBody RequestEditCanvas request) {

    try {

      Project project = projectService.getProject(projectID);
      projectService.editCanvas(project, request);

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/editCanvas; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
          "/" + projectID + "/editCanvas");

    }

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
  @RequestMapping(value = "/{projectID}/editLayer", method = RequestMethod.POST)
  public Response editLayer(@PathVariable String projectID,
      @RequestBody RequestEditLayer request) {

    try {

      Project project = projectService.getProject(projectID);
      projectService.editLayer(project, request.getLayerIndex(), request.isVisible());

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/editLayer; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
          "/" + projectID + "/editLayer");

    }

  }


  /**
   * Modifies the properties of a shape within a certain layer.
   *
   * @param projectID The project ID.
   * @param request The request body.
   * @return A server response including the ID and canvas of the modified project, or an error response if the modification failed.
   * @see RequestModifyShape
   * @see ServerResponse
   * @see ErrorResponse
   */
  @CrossOrigin()
  @RequestMapping(value = "/{projectID}/modifyShape", method = RequestMethod.POST)
  public Response editShape(@PathVariable String projectID,
      @RequestBody RequestModifyShape request) {

    try {

      Project project = projectService.getProject(projectID);
      projectService.modifyShape(project, request);

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/modifyShape; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
          "/" + projectID + "/modifyShape");

    }

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
  @RequestMapping(value = "/{projectID}/transformElement", method = RequestMethod.POST)
  public Response transformElement(@PathVariable String projectID,
      @RequestBody RequestTransformElement request) {

    // AddTransformation, RemoveTransformation, ClearTransformations
    try {

      Project project = projectService.getProject(projectID);
      projectService.transformElement(project, request);

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/transformElement; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
          "/" + projectID + "/transformElement");

    }

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
  @RequestMapping(value = "/{projectID}/deleteLayer", method = RequestMethod.POST)
  public Response deleteLayer(@PathVariable String projectID,
      @RequestBody RequestDeleteLayer request) {

    try {

      Project project = projectService.getProject(projectID);
      projectService.deleteLayer(project, request.getLayerIndex());

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/deleteLayer; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
          "/" + projectID + "/deleteLayer");

    }

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
  @RequestMapping(value = "/{projectID}/deleteShape", method = RequestMethod.POST)
  public Response deleteShape(@PathVariable String projectID,
      @RequestBody RequestDeleteShape request) {

    try {

      Project project = projectService.getProject(projectID);
      projectService.deleteShape(project, request.getLayerIndex(), request.getShapeIndex());

      return new ServerResponse(project.getProjectID(), project.getCanvas());

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/deleteShape; returning ErrorResponse");
      return new ErrorResponse(
          e.getMessage(),
          "/" + projectID + "/deleteShape");

    }

  }


  /**
   * Downloads the canvas in the desired file format.
   *
   * @param projectID The ID the client has been assigned in createProject().
   * @param format Specifies the file format.
   * @return If successfully called, it returns a .svg/.png/.jpg file, which shows a representation of the most recent canvas.
   * @see ResponseEntity
   */
  @CrossOrigin()
  @RequestMapping(produces = {"image/svg+xml", "image/png",
      "image/jpeg"}, value = "/{projectID}/download/{format}", method = RequestMethod.GET)
  public ResponseEntity<Object> download(@PathVariable String projectID, @PathVariable DownloadFormat format) {

    try {

      Project project = projectService.getProject(projectID);
      return projectService.getDownloadResponseForType(project, format);

    } catch (Exception e) {

      restHandlerLogger.error("Error in /" + projectID + "/download/" + format + "; returning ErrorResponse");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

  }
}
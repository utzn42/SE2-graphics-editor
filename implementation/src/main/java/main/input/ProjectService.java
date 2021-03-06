package main.input;

import canvas.Canvas;
import canvas.CanvasElement;
import canvas.CanvasLayer;
import download.CanvasToJPGConverter;
import download.CanvasToPNGConverter;
import download.CanvasToSVGConverter;
import download.CanvasToImageConverter;
import download.DownloadFormat;
import facilitators.Hasher;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import messages.RequestAddLayerGroup;
import messages.RequestAddShape;
import messages.RequestEditProject;
import messages.RequestEditElement;
import messages.RequestModifyShape;
import messages.RequestRemoveElement;
import messages.RequestTransformElement;
import messages.modifiers.shapes.ShapeModifier;
import observer.Observer;
import observer.Subject;
import org.apache.batik.transcoder.TranscoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import persistence.ProjectObserver;
import persistence.ProjectSerializer;
import project.LoadedProject;
import project.Project;
import shapes.Shape;
import shapes.transform.Transformation;

/**
 * This class provides the logic which {@link RESTHandler} calls upon to deliver its functionality.
 * It relies on the {@link ProjectSerializer} class for storing and fetching {@link Project Projects}.
 */
@Service
public class ProjectService implements Subject {

  //TODO: Maybe allow layers and shapes to be "moved" up and down

  private static Logger projectServiceLogger = LoggerFactory.getLogger(ProjectService.class);
  private Map<String, Project> projects;
  private List<Observer> observers;

  /**
   * Creates a default ProjectService object. If previously stored projects are available, they are loaded into the object.
   */
  public ProjectService() {
    observers = new ArrayList<>();
    registerObserver(new ProjectObserver());
    projects = ProjectSerializer.getProjects();
  }

  /**
   * Creates a one-way hash of the ID seed.
   *
   * @return A 6-letter substring of the SHA-256 hash of the ID seed.
   * @throws RuntimeException If creating a hash fails.
   */
  private String createID() {

    String operationToLog = "Create new ID";

    String projectID;
    do {
      long seed;
      try {
        seed = ProjectSerializer.getAndIncrementSeed();
      } catch (Exception e) {
        projectServiceLogger.error("Operation failed: " + operationToLog);
        throw new RuntimeException(e);
      }
      Hasher hash = new Hasher(seed);
      projectID = hash.getHash();
    } while (projects.containsKey(projectID));

    projectServiceLogger.info("Operation successful: " + operationToLog);
    return projectID;
  }


  /**
   * Creates a new Project.
   *
   * @return A new Project with a default empty Canvas.
   * @see Project
   */
  public Project createProject() {

    String operationToLog = "Create Project";

    String projectID = createID();

    Project newProject = new LoadedProject(projectID, new Canvas());

    putProject(newProject);
    projectServiceLogger.info("Operation successful: " + operationToLog);
    return newProject;

  }


  private void putProject(Project project) {

    projectServiceLogger.info("Notifying Observers that project " + project.getProjectID() + " has changed!");

    notifyObservers(project);
    projects = ProjectSerializer.getProjects();

  }


  /**
   * Returns the {@link Project} with the given ID.
   *
   * @param projectID The project ID.
   * @return The Project with the given ID.
   * @throws IllegalArgumentException If a Project with the given ID does not exist.
   */
  public Project getProject(String projectID) {

    String operationToLog = "Project " + projectID + ": Get Project";

    if (!projects.containsKey(projectID)) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("Operation successful: " + operationToLog);
    return projects.get(projectID);

  }


  /**
   * Adds a layer to the given {@link Project}.
   *
   * @param project The Project to add a layer to.
   * @param request The {@link RequestAddLayerGroup} object containing the values for the modification.
   */
  public void addLayerGroup(Project project, RequestAddLayerGroup request) {

    String operationToLog = "Project " + project.getProjectID() + ": Add Group Layer";

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    if (request.getPlaceIntoElementID().isPresent()) {
      projectCanvas.addGroupLayerIntoElement(request.getPlaceIntoElementID().get());
    }
    else if (request.getPlaceBeforeElementID().isPresent()) {
      projectCanvas.addGroupLayer(request.getPlaceBeforeElementID().get());
    } else {
      projectCanvas.addGroupLayer();
    }

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);

  }


  /**
   * Adds a {@link Shape} of the specified type to the specified {@link Project}.
   *
   * @param project The Project to add the Shape to.
   * @param request The {@link RequestAddShape} object containing the values for the modification.
   * @throws IllegalArgumentException If the shape type could not be resolved or the element ID
   * after which to insert the Shape does not exist.
   */
  public void addShape(Project project, RequestAddShape request)
      throws IllegalArgumentException {

    String operationToLog = "Project " + project.getProjectID() + ": Add new Shape of type "
        + request.getShapeType() +
        (request.getPlaceBeforeElementID().isPresent()
            ? " before Element " + request.getPlaceBeforeElementID().get()
            : " at default position");

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    if (request.getPlaceIntoElementID().isPresent()) {
      projectCanvas.addShapeIntoElement(request.getShapeType(), request.getPlaceIntoElementID().get());
    }
    else if (request.getPlaceBeforeElementID().isPresent()) {
      projectCanvas.addShape(request.getShapeType(), request.getPlaceBeforeElementID().get());
    } else {
      projectCanvas.addShape(request.getShapeType());
    }

    putProject(project);
    projectServiceLogger.info("Operation success: " + operationToLog);

  }


  /**
   * Modifies the width, height or both of the canvas of a Project. Also allows setting whether
   * to allow the HTML "transform" attribute on elements.
   *
   * @param project The Project to modify.
   * @param request The {@link RequestEditProject} object containing the values for the modification.
   * @throws IllegalArgumentException If the width or height is negative.
   */
  public void editProject(Project project, RequestEditProject request) {

    StringJoiner operationToLogJoiner = new StringJoiner(", ", "Project " + project.getProjectID() + ": Edit Canvas: ", "");
    request.getWidth().ifPresent(width -> operationToLogJoiner.add("width=" + width));
    request.getHeight().ifPresent(height -> operationToLogJoiner.add("height=" + height));
    request.doesAllowTransformAttribute().ifPresent(allowTransformAttribute -> operationToLogJoiner.add("allowTransformAttribute=" + allowTransformAttribute));
    String operationToLog = operationToLogJoiner.toString();

    if (request.getWidth().isPresent() && request.getWidth().get() < 0
        || request.getHeight().isPresent() && request.getHeight().get() < 0) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new IllegalArgumentException("Canvas width and height must both be positive!");
    }

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    request.getWidth().ifPresent(projectCanvas::setWidth);
    request.getHeight().ifPresent(projectCanvas::setHeight);
    request.doesAllowTransformAttribute().ifPresent(projectCanvas::setAllowTransformAttribute);

    putProject(project);
    projectServiceLogger.info("Operation success: " + operationToLog);

  }


  /**
   * Sets the visibility of a given Element.
   *
   * @param project The Project to edit.
   * @param request The {@link RequestEditElement} object containing the values for the modification.
   */
  public void editElement(Project project, RequestEditElement request) {

    String operationToLog = "Project " + project.getProjectID() + ": Set Element "
        + request.getElementID() + " " + (request.isVisible() ? "visible" : "invisible");

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    projectCanvas.findElementByID(request.getElementID()).setVisible(request.isVisible());

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);

  }


  /**
   * Modifies a shape within a given layer of the canvas.
   *
   * @param project The Project to modify.
   * @param request The {@link RequestModifyShape} object containing the values for the modification.
   */
  public void modifyShape(Project project, RequestModifyShape request) {

    String operationToLog = "Project " + project.getProjectID() + ": Modify Shape";

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    CanvasElement element = projectCanvas.findElementByID(request.getElementID());

    if (!(element instanceof CanvasLayer)) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new IllegalArgumentException("Element with ID " + request.getElementID() + " is not a single shape!");
    }

    ShapeModifier shapeModifier = request.getShapeModifier();
    Shape shapeToModify = ((CanvasLayer) element).getShape();
    shapeToModify = shapeModifier.apply(shapeToModify);
    element.setShape(shapeToModify);

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);
  }


  /**
   * Modifies the visual representation of a shape within a certain layer using the SVG format's inbuilt transform parameters.
   *
   * @param project The Project to modify.
   * @param request The {@link RequestTransformElement} object containing the values for the modification.
   */
  public void transformElement(Project project, RequestTransformElement request) {

    String operationToLog = "Project " + project.getProjectID() + ": Transform Element";

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    if (request.getClearAll().isPresent() && request.getClearAll().get()) {
      projectCanvas.clearTransformationsOnElement(request.getElementID());
    }

    if (request.getTransformation().isPresent()) {
      Transformation transformation = new Transformation();
      transformation.addTransformation(request.getTransformation().get());
      projectCanvas.transformElementByID(request.getElementID(), transformation);
    }

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);
  }


  /**
   * Deletes a layer from the specified canvas.
   *
   * @param project The Project to delete the layer from.
   * @param request The {@link RequestRemoveElement} object containing the values for the modification.
   */
  public void removeElement(Project project, RequestRemoveElement request) {

    String operationToLog = "Project " + project.getProjectID() + ": Remove Element " + request.getElementID();

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    projectCanvas.removeElementByID(request.getElementID());

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);

  }


  /**
   * Downloads a picture of the canvas in a format of choice.
   *
   * @param project Is used to retrieve the canvas based on the ID.
   * @param format Specifies the desired file format.
   * @return A SVG, JPG or PNG file, which represents the canvas at the time of convert.
   * @throws IllegalArgumentException If the download format could not be resolved.
   * @throws IOException If an I/O error occurs.
   * @throws TranscoderException If transcoding to the desired image format fails.
   */
  public ResponseEntity<Object> getDownloadResponseForType(Project project, DownloadFormat format)
      throws IOException, TranscoderException {

    String operationToLog = "Project " + project.getProjectID()
        + ": Create download response for format " + format;

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    CanvasToImageConverter canvasToImageConverter;
    String mimeType;
    switch (format) {
      case SVG:
        canvasToImageConverter = new CanvasToSVGConverter();
        mimeType = "image/svg+xml";
        break;
      case PNG:
        canvasToImageConverter = new CanvasToPNGConverter();
        mimeType = "image/png";
        break;
      case JPG:
        canvasToImageConverter = new CanvasToJPGConverter();
        mimeType = "image/jpeg";
        break;
      default:
        projectServiceLogger.error("Operation failed: " + operationToLog);
        throw new IllegalArgumentException("Unknown format");
    }

    try {

      URI fileURI = canvasToImageConverter.convert(projectCanvas, project.getProjectID());
      File file = new File(fileURI);
      ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

      ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(httpHeaders).contentLength(file.length()).contentType(
          MediaType.parseMediaType(mimeType)).body(resource);
      projectServiceLogger.info("Operation successful: " + operationToLog);

      return responseEntity;

    } catch (IOException | TranscoderException e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw e;
    }

  }


  /**
   * Returns a Map containing the Project IDs and respective Projects.
   *
   * @return A Map containing the Project IDs and respective Projects.
   */
  public Map<String, Project> getProjects() {
    return projects;
  }


  /**
   * Adds an observer to the list of active observers.
   *
   * @param o Specifies the observer which is to be added to the list.
   * @see Subject
   */
  @Override
  public void registerObserver(Observer o) {
    observers.add(o);
  }


  /**
   * Removes an observer from the list of active observers.
   *
   * @param o Passes the specific observer to the function in order to remove it.
   * @see Subject
   */
  @Override
  public void removeObserver(Observer o) {
    int i = observers.indexOf(o);
    if (i >= 0) {
      observers.remove(i);
    }
  }


  /**
   * Notifies the observers of a ProjectService object passing the given Object as a parameter.
   *
   * @param obj The Object passed to the Observer's update function.
   */
  @Override
  public void notifyObservers(Object obj) {
    for (Observer o : observers) {
      o.update(obj);
    }
  }

}
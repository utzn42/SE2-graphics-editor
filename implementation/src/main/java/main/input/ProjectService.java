package main.input;

import canvas.Canvas;
import canvas.CanvasElement;
import canvas.CanvasElementAggregate;
import canvas.CanvasLayer;
import canvas.Layer;
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
import shapes.ShapeType;

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
   */
  public void addLayer(Project project) {
    //TODO: main.input.ProjectService.addLayer(Project): Rework after integration of new layer structure

    String operationToLog = "Project " + project.getProjectID() + ": Add new Layer";

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    //TODO: implement addLayer
//    projectCanvas.getLayers().add(new CanvasLayer());
    projectServiceLogger.error("Operation failed: " + operationToLog);

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);

  }


  /**
   * Adds a {@link Shape} of the specified type to the specified {@link Project}.
   *
   * @param project The Project to add the Shape to.
   * @param layerIndex The index of the Layer to add the Shape to.
   * @param shapeType Specifies the type of shape which is to be added, in order to call default constructors.
   * @throws IllegalArgumentException If the shape type could not be resolved.
   * @throws IndexOutOfBoundsException If the layer index is out of bounds.
   */
  public void addShape(Project project, int layerIndex, ShapeType shapeType)
      throws IllegalArgumentException, IndexOutOfBoundsException {
    //TODO: main.input.ProjectService.addShape(Project, int, ShapeType): Rework after integration of new layer structure

    String operationToLog = "Project " + project.getProjectID() + ": Add new Shape of type "
        + shapeType + " to Layer " + layerIndex;

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    Shape shape;

    shape = projectCanvas.getShapeFactory().createShape(shapeType).getShape();

    /*switch (shapeType) {
      case CIRCLE:
        shape = projectCanvas.getShapeFactory().createCircle();
        break;
      case ELLIPSE:
        shape = projectCanvas.getShapeFactory().createEllipse();
        break;
      case LINE:
        shape = projectCanvas.getShapeFactory().createLine();
        break;
      case POLYGON:
        shape = projectCanvas.getShapeFactory().createPolygon();
        break;
      case REGULAR_POLYGON:
        shape = projectCanvas.getShapeFactory().createRegularPolygon();
        break;
      case STAR:
        shape = projectCanvas.getShapeFactory().createStar();
        break;
      case TEXT:
        shape = projectCanvas.getShapeFactory().createText();
        break;
      default:
        projectServiceLogger.error("Operation failed: " + operationToLog);
        throw new IllegalArgumentException("Unknown Shape type: " + shapeType);
    }*/



    try {
//      projectCanvas.addShape(shapeType);
    } catch (IndexOutOfBoundsException e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw e;
    }

    putProject(project);
    projectServiceLogger.info("Operation success: " + operationToLog);

  }


  /**
   * Modifies the width/height of the canvas of a Project.
   *
   * @param project The Project to modify.
   * @param width Specifies the new canvas width.
   * @param height Specifies the new canvas height.
   * @throws IllegalArgumentException If the width or height is negative.
   */
  public void editCanvas(Project project, double width, double height) {

    String operationToLog = "Project " + project.getProjectID() + ": Set Canvas measurements to "
        + width + "x" + height;

    if (width < 0 || height < 0) {
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

    projectCanvas.setWidth(width);
    projectCanvas.setHeight(height);

    putProject(project);
    projectServiceLogger.info("Operation success: " + operationToLog);

  }


  /**
   * Sets the visibility of a given layer.
   *
   * @param project The Project to edit.
   * @param layerIndex Is used to retrieve the desired layer from within the canvas.
   * @param isVisible Sets the layer (and therefore all of its shapes) to visible/invisible.
   * @throws IndexOutOfBoundsException If the layer index is out of bounds.
   */
  public void editLayer(Project project, int layerIndex, boolean isVisible) {
    //TODO: main.input.ProjectService.editLayer(Project, int, boolean): Rework after integration of new layer structure

    String operationToLog = "Project " + project.getProjectID() + ": Set Layer " + layerIndex + " "
        + (isVisible ? "visible" : "invisible");

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    try {
      projectCanvas.getLayers().get(layerIndex).setVisible(isVisible);
    } catch (IndexOutOfBoundsException e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw e;
    }

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);

  }


  /**
   * Modifies a shape within a given layer of the canvas.
   *
   * @param projectID Is used to retrieve the canvas based on the ID.
   * @param layerIndex Is used to retrieve the desired layer from within the canvas.
   * @param shapeIndex Is used to retrieve the desired shape from within the layer.
   * @param shape Passes the modified shape to the function
   */
  public void editShape(String projectID, int layerIndex, int shapeIndex, Shape shape) {
    //TODO: main.input.ProjectService.editShape(String, int, int, Shape): Rework after integration of new layer structure
    throw new IllegalArgumentException("This function is not yet implemented!");
    /*
    if (!projects.containsKey(projectID)) {
      throw new IndexOutOfBoundsException("Project ID " + projectID + " does not exist!");
    }

    projectServiceLogger.info("editShape - " + shape.getHTML());

    projects.get(projectID).getLayers().get(layerIndex).getShapes()
        .set(shapeIndex, shape);

    notifyObservers();
    return projects.get(projectID);
    */
  }


  /**
   * Modifies the visual representation of a shape within a certain layer using the SVG format's inbuilt transform parameters.
   *
   * @param projectID Is used to retrieve the canvas based on the ID.
   */
  public void transformShape(String projectID) {
    //TODO: main.input.ProjectService.transformShape(String): Rework after integration of new layer structure
    throw new IllegalArgumentException("This function is not yet implemented!");
  }


  /**
   * Deletes a layer from the specified canvas.
   *
   * @param project The Project to delete the layer from.
   * @param layerIndex Specifies the layer which the user wishes to delete.
   * @throws IndexOutOfBoundsException If the layer index is out of bounds.
   */
  public void deleteLayer(Project project, int layerIndex) {
    //TODO: main.input.ProjectService.deleteLayer(Project, int): Rework after integration of new layer structure

    String operationToLog = "Project " + project.getProjectID() + ": Delete Layer " + layerIndex;

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    try {
      projectCanvas.getLayers().remove(layerIndex);
    } catch (IndexOutOfBoundsException e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw e;
    }

    putProject(project);
    projectServiceLogger.info("Operation successful: " + operationToLog);

  }


  /**
   * Deletes a certain shape within a layer of the canvas.
   *
   * @param project The Project to delete the Shape from.
   * @param layerIndex Specifies the layer within which the user wishes to delete the shape.
   * @param shapeIndex Specifies the exact shape which the user wishes to delete.
   */
  public void deleteShape(Project project, int layerIndex, int shapeIndex) {
    //TODO: main.input.ProjectService.deleteShape(Project, int, int): Rework after integration of new layer structure

    String operationToLog = "Project " + project.getProjectID() + ": Delete Shape " + shapeIndex
        + " from Layer " + layerIndex;

    Canvas projectCanvas;
    try {
      projectCanvas = project.getCanvas();
    } catch (Exception e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw new RuntimeException(e);
    }

    try {
//      projectCanvas.getLayers().get(layerIndex).getShapes().remove(shapeIndex);
    } catch (IndexOutOfBoundsException e) {
      projectServiceLogger.error("Operation failed: " + operationToLog);
      throw e;
    }

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
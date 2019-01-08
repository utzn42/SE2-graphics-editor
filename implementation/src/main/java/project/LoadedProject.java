package project;

import canvas.Canvas;
import java.io.Serializable;

/**
 * Basic implementation of the {@link Project} interface.
 * Adds instance variables to hold the ID and canvas.
 */
public class LoadedProject implements Project {

  private static final long serialVersionUID = 1L;

  private String projectID;
  private Canvas canvas;

  /**
   * Creates a new LoadedProject with an empty Canvas.
   *
   * @param projectID The project ID.
   */
  public LoadedProject(String projectID) {
    this.projectID = projectID;
    canvas = new Canvas();
  }

  /**
   * Creates a new LoadedProject from the given parameters.
   *
   * @param projectID The project ID.
   * @param canvas The canvas for the project.
   */
  public LoadedProject(String projectID, Canvas canvas) {
    this.projectID = projectID;
    this.canvas = canvas;
  }

  @Override
  public String getProjectID() {
    //TODO: Implement project.LoadedProject.getProjectID()
    return null;
  }

  @Override
  public Canvas getCanvas() {
    //TODO: Implement project.LoadedProject.getCanvas()
    return null;
  }

}
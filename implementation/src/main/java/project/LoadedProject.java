package project;

import canvas.Canvas;

/**
 * Basic implementation of the {@link Project} interface.
 * Adds instance variables to hold the ID and canvas.
 */
public class LoadedProject implements Project {

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
    return projectID;
  }

  @Override
  public Canvas getCanvas() {
    return canvas;
  }

}
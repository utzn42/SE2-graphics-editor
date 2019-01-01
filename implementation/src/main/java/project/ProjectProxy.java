package project;

import canvas.Canvas;

/**
 * Proxy class for {@link LoadedProject} objects.
 * Can be used to fetch the project from storage only when needed.
 */
public class ProjectProxy implements Project {

  private String projectID;
  private LoadedProject project;

  /**
   * Returns the project's ID as normal
   *
   * @return the project's ID
   */
  @Override
  public String getProjectID() {
    //TODO: Implement project.ProjectProxy.getProjectID()
    return null;
  }

  /**
   * Fetches the project if it has not been loaded yet and returns the canvas as normal
   *
   * @return the working canvas
   */
  @Override
  public Canvas getCanvas() {
    //TODO: Implement project.ProjectProxy.getCanvas()
    return null;
  }

}

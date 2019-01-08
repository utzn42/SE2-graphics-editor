package project;

import canvas.Canvas;

/**
 * Proxy class for {@link LoadedProject} objects.
 * Can be used to fetch the project from storage only when needed.
 */
public class ProjectProxy implements Project {

  private static final long serialVersionUID = 1L;

  private String projectID;
  private LoadedProject project;

  /**
   * Creates a ProjectProxy with the given ID.
   *
   * @param projectID The project ID.
   */
  public ProjectProxy(String projectID) {
    this.projectID = projectID;
    project = null;
  }

  /**
   * Returns the project's ID as normal.
   *
   * @return The project's ID.
   */
  @Override
  public String getProjectID() {
    //TODO: Implement project.ProjectProxy.getProjectID()
    return null;
  }

  /**
   * Fetches the project if it has not been loaded yet and returns the canvas as normal.
   *
   * @return The working canvas.
   */
  @Override
  public Canvas getCanvas() {
    //TODO: Implement project.ProjectProxy.getCanvas()
    return null;
  }

}

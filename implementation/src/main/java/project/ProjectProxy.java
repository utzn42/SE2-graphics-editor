package project;

import canvas.Canvas;
import java.io.IOException;
import persistence.ProjectSerializer;

/**
 * Proxy class for {@link LoadedProject} objects.
 * Can be used to fetch the project from storage only when needed.
 */
public class ProjectProxy implements Project {

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
    return projectID;
  }

  /**
   * Fetches the project if it has not been loaded yet and returns the canvas as normal.
   *
   * @return The working canvas.
   */
  @Override
  public Canvas getCanvas() throws IOException, ClassNotFoundException {

    if (project == null) {
      Project project = ProjectSerializer.getProject(projectID);
      if (project instanceof LoadedProject) {
        this.project = (LoadedProject) project;
      } else {
        throw new IOException("Failed to get Project from local storage.");
      }
    }

    return project.getCanvas();

  }

}

package messages;

import canvas.Canvas;

/**
 * This class represents the server response. It always sends the whole canvas to the client. The {@link ServerResponse} extends the {@link Response}.
 * Every response also has a projectID to specify to whom the response belongs. The class gets called everytime before the Server responses to the client.
 *
 * @see Response
 * @see main.input.RESTHandler
 */
public class ServerResponse extends Response {

  private Canvas canvas;

  /**
   * Default constructor.
   */
  public ServerResponse() {
    projectID = null;
    canvas = null;
  }

  /**
   * Creates a new ServerResponse from the given project ID and {@link Canvas}.
   *
   * @param projectID The ID of the project.
   * @param canvas The canvas of the project.
   */
  public ServerResponse(String projectID, Canvas canvas) {
    this.projectID = projectID;
    this.canvas = canvas;
  }

  /**
   * Returns the projectID. Gets called in createProject method in {@link main.input.RESTHandler}.
   * @return returns projectID in {@link String}
   */
  public String getProjectID() {
    return projectID;
  }

  /**
   * Sets the ProjectID to a new ID.
   * @param projectID new projectID in {@link String}
   */
  public void setProjectID(String projectID) {
    this.projectID = projectID;
  }

  /**
   * Returns the canvas of the Response. Gets called whenever the JSON object is created to send it to the client.
   * @return returns a {@link Canvas}
   */
  public Canvas getCanvas() {
    return canvas;
  }

  /**
   * Changes the Canvas. Gets called whenever the canvas is updated (e.g.: layer edited/deleted, shape edited/deleted).
   * @param canvas new {@link Canvas} with new layers/shapes/parameters
   */
  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }
}

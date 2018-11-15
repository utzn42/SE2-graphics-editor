package messages;

import canvas.Canvas;
import java.util.ArrayList;
import java.util.List;

public class ServerResponse {

  private String projectID;
  private Canvas canvas;

  public ServerResponse() {
    projectID = null;
    canvas = null;
  }

  public ServerResponse(String projectID) {
    this.projectID = projectID;
    canvas = null;
  }

  public String getProjectID() {
    return projectID;
  }

  public void setProjectID(String projectID) {
    this.projectID = projectID;
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }
}

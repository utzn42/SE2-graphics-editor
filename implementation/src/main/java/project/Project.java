package project;

import canvas.Canvas;
import java.io.Serializable;

/**
 * Represents a project that has a unique ID and a working canvas.
 *
 * @see Canvas
 */
public interface Project extends Serializable {

  /**
   * Returns the project ID
   *
   * @return the project ID
   */
  String getProjectID();

  /**
   * Returns the working canvas
   *
   * @return the working canvas
   */
  Canvas getCanvas();

}

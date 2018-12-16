package project;

import canvas.Canvas;

/**
 * Represents a project that has a unique ID and a working canvas.
 *
 * @see Canvas
 */
public interface Project {

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

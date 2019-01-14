package project;

import canvas.Canvas;
import java.io.IOException;

/**
 * Represents a project that has a unique ID and a working canvas.
 *
 * @see Canvas
 */
public interface Project {

  /**
   * Returns the project ID.
   *
   * @return The project ID.
   */
  String getProjectID();

  /**
   * Returns the working canvas.
   *
   * @return The working canvas.
   * @throws IOException If an I/O error occurs while loading the canvas.
   * @throws ClassNotFoundException If the Canvas class is not found.
   */
  Canvas getCanvas() throws IOException, ClassNotFoundException;

}

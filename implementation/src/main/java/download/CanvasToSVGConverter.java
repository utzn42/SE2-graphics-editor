package download;

import canvas.Canvas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

/**
 * One of the possible convert options is to convert the canvas in SVG format. It implements
 * {@link CanvasToImageConverter} and is part of our Strategy Pattern.
 *
 * @see CanvasToImageConverter
 */
public class CanvasToSVGConverter implements CanvasToImageConverter {

  /**
   * Converts the {@link Canvas} into an SVG {@link File} and returns the {@link URI}.
   *
   * @param canvas the {@link Canvas} to convert
   * @param projectID the unique projectID to create the file name
   * @return returns an {@link URI} of the SVG {@link File}
   * @throws IOException If an I/O error occurs
   */
  @Override
  public URI convert(Canvas canvas, String projectID) throws IOException {

    File file = new File("./projects/" + projectID + "/" + projectID + ".svg");
    boolean dirsCreated = file.getParentFile().mkdirs();

    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(canvas.getHTML());
    writer.close();
    return file.toURI();
  }
}

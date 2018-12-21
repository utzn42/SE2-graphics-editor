package download;

import canvas.Canvas;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.apache.batik.transcoder.TranscoderException;

/**
 * Functional interface that declares a method to convert a {@link Canvas} into an image file.
 *
 * @see CanvasToJPGConverter
 * @see CanvasToPNGConverter
 * @see CanvasToSVGConverter
 */
public interface CanvasToImageConverter {

  /**
   * Declaration of the convert method. Needs the canvas and the projectid to convert the
   * svg container into a file. It needs the canvas to get the svg container and the projectid to
   * create a unique file name.
   *
   * @param canvas the {@link Canvas} to convert
   * @param projectID the unique projectID to create the file name
   * @return The {@link URI} of the created {@link File}
   * @throws IOException If an I/O error occurs
   * @throws TranscoderException If transcoding fails
   */
  URI convert(Canvas canvas, String projectID) throws IOException, TranscoderException;

}

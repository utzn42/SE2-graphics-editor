package download;

import canvas.Canvas;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.apache.batik.transcoder.TranscoderException;

/**
 * Interface which is part of the Strategy Pattern. Declares a download method which is then defined
 * by the explicit download classes.
 *
 * @see DownloadJPG
 * @see DownloadPNG
 * @see DownloadSVG
 */
public interface DownloadStrategy {

  /**
   * Declaration of the download method. Needs the canvas and the projectid to create convert the
   * svg container into a file. It needs the canvas to get the svg container and the projectid to
   * create a unique file name.
   *
   * @param canvas the {@link Canvas} which wants to get downloaded
   * @param projectID the unique projectID to create the file name
   * @return returns an {@link URI} of the specific {@link File}
   * @throws IOException If an I/O error occurs
   * @throws TranscoderException If transcoding fails
   */
  URI download(Canvas canvas, String projectID) throws IOException, TranscoderException;

}

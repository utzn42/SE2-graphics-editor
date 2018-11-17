package download;

import canvas.Canvas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import org.apache.batik.transcoder.TranscoderException;

/**
 * One of the possible download options is to download the canvas in SVG format. It implements {@link DownloadStrategy} and is part of our Strategy Pattern.
 * @see DownloadStrategy
 */
public class DownloadSVG implements DownloadStrategy {

  /**
   * Gets called when the user wants to download the canvas in SVG. In this case conversion is not necessary, just creating a {@link File} out of the SVG container and send the file to the client. It overrides the method of {@link DownloadStrategy}.
   * @param canvas the {@link Canvas} which wants to get downloaded
   * @param projectID the unique projectID to create the file name
   * @return returns an {@link URI} of the SVG {@link File}
   * @throws IOException If an I/O error occurs
   */
  @Override
  public URI download(Canvas canvas, String projectID) throws IOException {

    File file = new File ("./projects/" + projectID + "/" + projectID + ".svg");
    boolean dirsCreated = file.getParentFile().mkdirs();

    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(canvas.getHTML());
    writer.close();
    return file.toURI();
  }
}

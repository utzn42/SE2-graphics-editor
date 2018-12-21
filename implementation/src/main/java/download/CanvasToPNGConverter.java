package download;

import canvas.Canvas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * One of the possible convert options is to convert the canvas in PNG format. It implements
 * {@link CanvasToImageConverter} and is part of our Strategy Pattern.
 *
 * @see CanvasToImageConverter
 */
public class CanvasToPNGConverter implements CanvasToImageConverter {

  /**
   * Converts the {@link Canvas} into a PNG {@link File} and returns the {@link URI}.
   *
   * @param canvas the {@link Canvas} to convert
   * @param projectID the unique projectID to create the file name
   * @return returns an {@link URI} of the PNG {@link File}
   * @throws IOException If an I/O error occurs
   * @throws TranscoderException If transcoding to PNG fails
   */
  @Override
  public URI convert(Canvas canvas, String projectID) throws IOException, TranscoderException {

    PNGTranscoder t = new PNGTranscoder();

    File svgFile = new File("./projects/" + projectID + "/" + projectID + ".svg");
    boolean dirsCreated = svgFile.getParentFile().mkdirs();

    BufferedWriter writer = new BufferedWriter(new FileWriter(svgFile));
    writer.write(canvas.getHTML());
    writer.close();

    File pngFile = new File("./projects/" + projectID + "/" + projectID + ".png");
    TranscoderInput input = new TranscoderInput(svgFile.toURI().toString());
    OutputStream ostream = new FileOutputStream(pngFile.getPath());
    TranscoderOutput output = new TranscoderOutput(ostream);

    t.transcode(input, output);

    ostream.flush();
    ostream.close();

    return pngFile.toURI();
  }
}

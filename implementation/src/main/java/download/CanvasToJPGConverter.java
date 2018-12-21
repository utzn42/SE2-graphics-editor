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

/**
 * One of the possible convert options is to convert the canvas in JPG format. It implements
 * {@link CanvasToImageConverter} and is part of our Strategy Pattern.
 *
 * @see CanvasToImageConverter
 */
public class CanvasToJPGConverter implements CanvasToImageConverter {

  /**
   * Converts the {@link Canvas} into a JPG {@link File} and returns the {@link URI}.
   *
   * @param canvas the {@link Canvas} to convert
   * @param projectID the unique projectID to create the file name
   * @return returns an {@link URI} of the JPG {@link File}
   * @throws IOException If an I/O error occurs
   * @throws TranscoderException If transcoding to JPG fails
   */
  @Override
  public URI convert(Canvas canvas, String projectID) throws IOException, TranscoderException {

    JPEGTranscoder t = new JPEGTranscoder();
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
        (float) .8);

    File svgFile = new File("./projects/" + projectID + "/" + projectID + ".svg");
    boolean dirsCreated = svgFile.getParentFile().mkdirs();

    BufferedWriter writer = new BufferedWriter(new FileWriter(svgFile));
    writer.write(canvas.getHTML());
    writer.close();

    File jpgFile = new File("./projects/" + projectID + "/" + projectID + ".jpg");
    TranscoderInput input = new TranscoderInput(svgFile.toURI().toString());
    OutputStream ostream = new FileOutputStream(jpgFile.getPath());
    TranscoderOutput output = new TranscoderOutput(ostream);

    t.transcode(input, output);

    ostream.flush();
    ostream.close();

    return jpgFile.toURI();
  }
}

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

public class DownloadPNG implements DownloadStrategy {

  @Override
  public URI download(Canvas canvas, String projectID) throws IOException, TranscoderException {
    PNGTranscoder t = new PNGTranscoder();
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
        .8);

    File file = new File ("./" + projectID + "/" + projectID + ".svg");
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(canvas.getHTML());
    writer.close();

    String pngPath = "./" + projectID + "/" + projectID + ".png";
    String svgFile = file.toURI().toURL().toString();
    TranscoderInput input = new TranscoderInput(svgFile);
    OutputStream ostream = new FileOutputStream(pngPath);
    TranscoderOutput output = new TranscoderOutput(ostream);

    t.transcode(input, output);

    boolean deleted = file.delete();

    ostream.flush();
    ostream.close();

    return file.toURI();

  }
}

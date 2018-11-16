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

public class DownloadJPG implements DownloadStrategy {

  @Override
  public URI download(Canvas canvas, String projectID) throws IOException, TranscoderException {

    JPEGTranscoder t = new JPEGTranscoder();
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
        (float) .8);

    File svgFile = new File ("./projects/" + projectID + "/" + projectID + ".svg");
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

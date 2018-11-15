package download;

import canvas.Canvas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class DownloadPNG implements DownloadStrategy {

  @Override
  public void download(Canvas canvas) throws IOException, TranscoderException {
    PNGTranscoder t = new PNGTranscoder();
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
        new Float(.8));

    File file = new File ("file.svg");
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(canvas.getHTML());
    writer.close();

    String svgFile = file.toURL().toString();
    TranscoderInput input = new TranscoderInput(svgFile);
    OutputStream ostream = new FileOutputStream("out.png");
    TranscoderOutput output = new TranscoderOutput(ostream);

    t.transcode(input, output);

    file.delete();


    ostream.flush();
    ostream.close();

  }
}

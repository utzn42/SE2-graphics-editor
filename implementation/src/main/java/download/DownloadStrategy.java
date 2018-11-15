package download;

import canvas.Canvas;
import java.io.File;
import java.io.IOException;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderOutput;

public interface DownloadStrategy {

  void download(Canvas canvas) throws IOException, TranscoderException;

}

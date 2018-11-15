package download;

import canvas.Canvas;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderOutput;

public interface DownloadStrategy {

  URI download(Canvas canvas, String projectID) throws IOException, TranscoderException;

}

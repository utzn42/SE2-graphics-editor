package download;

import canvas.Canvas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DownloadSVG implements DownloadStrategy {

  @Override
  public void download(Canvas canvas) throws IOException {
    File file = new File ("out.svg");
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(canvas.getHTML());
    writer.close();
  }
}

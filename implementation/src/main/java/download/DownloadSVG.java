package download;

import canvas.Canvas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

public class DownloadSVG implements DownloadStrategy {

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

package main.input;

import static main.input.RESTInit.getProjects;

import canvas.Canvas;
import download.DownloadJPG;
import download.DownloadPNG;
import download.DownloadSVG;
import download.DownloadStrategy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.batik.transcoder.TranscoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTDownloader {

  private static Logger restDownloaderLogger = LoggerFactory.getLogger(RESTAdder.class);

  @CrossOrigin()
  @RequestMapping(produces={"image/svg+xml", "image/png", "image/jpeg"}, value = "/download/{projectID}/{type}", method = RequestMethod.GET)
  public ResponseEntity<Object> download(@PathVariable String projectID, @PathVariable String type) throws IOException, TranscoderException {
    //ServerResponse response = new ServerResponse(projectID);

    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restDownloaderLogger.info("download in format: " + type);

    DownloadStrategy downloadStrategy;
    String mimeType;
    switch (type) {
      case "svg":
        downloadStrategy = new DownloadSVG();
        mimeType = "image/svg+xml";
        break;
      case "png":
        downloadStrategy = new DownloadPNG();
        mimeType = "image/png";
        break;
      case "jpg":
      case "jpeg":
        downloadStrategy = new DownloadJPG();
        mimeType = "image/jpeg";
        break;
      default:
        throw new IllegalArgumentException("Unknown file type");
    }

    URI fileURI = downloadStrategy.download(canvas, projectID);
    File file = new File(fileURI);
    Path path = Paths.get(fileURI);
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
    InputStream in = getClass().getResourceAsStream(fileURI.getPath());

    //InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
    httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
    httpHeaders.add(HttpHeaders.EXPIRES, "0");

    return ResponseEntity.ok().headers(httpHeaders).contentLength(file.length()).contentType(
        MediaType.parseMediaType(mimeType)).body(resource);
  }

}

package main.input;

import static main.input.RESTInit.getProjects;

import canvas.Canvas;
import download.DownloadStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import messages.RequestAddShape;
import messages.RequestDownload;
import messages.ServerResponse;
import org.apache.batik.transcoder.TranscoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shapes.Shape;

@RestController
public class RESTDownloader {

  private static Logger restDownloaderLogger = LoggerFactory.getLogger(RESTAdder.class);

  @CrossOrigin()
  @RequestMapping(value = "/download/{projectID}", method = RequestMethod.GET)
  public ResponseEntity<Object> download(@PathVariable String projectID,
      @RequestBody RequestDownload request) throws FileNotFoundException {
    //ServerResponse response = new ServerResponse(projectID);

    if (!getProjects().containsKey(projectID)) {
      throw new IllegalArgumentException("Project ID " + projectID + " does not exist!");
    }
    Canvas canvas = getProjects().get(projectID);

    restDownloaderLogger.info("download in format: " + request.getFileType());

    DownloadStrategy downloadStrategy;
    File file = new File("out."+request.getFileType());
    try {
      downloadStrategy = (DownloadStrategy) (Class.forName(request.getFileType()).newInstance());
      downloadStrategy.download(canvas);

    } catch (ClassNotFoundException e) {
      restDownloaderLogger.error("         - HTML: Failed to get Class");
    } catch (IllegalAccessException e) {
      restDownloaderLogger.error("         - HTML: Could not access Class");
    } catch (InstantiationException e) {
      restDownloaderLogger.error("         - HTML: Could not instantiate Object for Class");
    } catch (IOException | TranscoderException e) {
      e.printStackTrace();
    }

    //InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Disposition", String.format("attachment; filename=canvas.svg", file.getName()));
    httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
    httpHeaders.add("Expires", "0");

    ResponseEntity<Object> response = ResponseEntity.ok().headers(httpHeaders).contentLength(file.length()).contentType(
        MediaType.ALL).body(new File("out."+request.getFileType()));

    return response;
  }

}

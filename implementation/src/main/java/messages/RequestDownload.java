package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if download in {@link main.input.RESTHandler} gets called.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestDownload {

  private final String fileType;

  /**
   * The constructor assigns the JSON Property to the class attribute fileType, which indicates the desired output format (SVG, PNG or JPG).
   * @param fileType the desired output file format in {@link String}
   */
  @JsonCreator
  RequestDownload(
      @JsonProperty("fileType") String fileType
  ) {
    this.fileType = fileType;
  }

  /**
   *
   * @return
   */
  public String getFileType() {
    return fileType;
  }
}

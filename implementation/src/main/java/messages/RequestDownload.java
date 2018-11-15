package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDownload {

  private final String fileType;

  @JsonCreator
  RequestDownload(
      @JsonProperty("fileType") String fileType
  ) {
    this.fileType = fileType;
  }

  public String getFileType() {
    return fileType;
  }
}

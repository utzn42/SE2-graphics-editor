package messages;

import java.sql.Timestamp;

public class ErrorResponse extends Response {

  private String timestamp;
  private int status = 500;
  private String error = "Internal Server Error";
  private String message;
  private String path;

  public ErrorResponse(String message, String path) {
    this.message = message;
    this.path = path;

    Timestamp ts = new Timestamp(System.currentTimeMillis());
    timestamp = ts.toString();
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}

package messages;

import java.sql.Timestamp;

/**
 * This class is for creating an error response. It gets called when the {@link main.input.RESTHandler} catches an {@link Exception} from the {@link main.input.ProjectService}.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class ErrorResponse extends Response {

  private String timestamp;
  private int status = 500;
  private String error = "Internal Server Error";
  private String message;
  private String path;

  /**
   * The constructor expects an error message in {@link String} and a path in {@link String}.
   * The constructor also creates a timestamp at the moment the object gets created.
   * @param message the error message in {@link String}
   * @param path the path in {@link String} (e.g.: "/addShape/projectID")
   */
  public ErrorResponse(String message, String path) {
    this.message = message;
    this.path = path;

    Timestamp ts = new Timestamp(System.currentTimeMillis());
    timestamp = ts.toString();
  }

  /**
   * Returns the timestamp of the error.
   * @return returns timestamp in {@link String}
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp.
   * @param timestamp timestamp in {@link String}
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Returns the status. (default: 500)
   * @return returns the status in {@link Integer}
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets the status.
   * @param status status in {@link Integer}
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * Returns the error in {@link String}. Gets called whenever the server returns an error to the client.
   * @return returns the error in {@link String}
   */
  public String getError() {
    return error;
  }

  /**
   * Changes the error to the one received as parameter
   * @param error the error message in {@link String}
   */
  public void setError(String error) {
    this.error = error;
  }

  /**
   * Returns the error message in {@link String}
   * @return returns error message in {@link String}
   */
  public String getMessage() {
    return message;
  }

  /**
   * Changes the error message to the one received as parameter.
   * @param message error message in {@link String}
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Returns the path in {@link String}. Gets called whenever the server returns an error to the client.
   * @return returns the path in {@link String}
   */
  public String getPath() {
    return path;
  }

  /**
   * Changes the path to the one received as parameter.
   * @param path the path in {@link String}
   */
  public void setPath(String path) {
    this.path = path;
  }
}

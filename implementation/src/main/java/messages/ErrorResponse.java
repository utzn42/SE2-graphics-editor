package messages;

import java.sql.Timestamp;

/**
 * A generic error response. It is returned to the client when the {@link main.input.RESTHandler} catches an {@link Exception}
 *
 * @see main.input.RESTHandler
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
   *
   * @param message The error message in {@link String}.
   * @param path The path in {@link String} (e.g.: "/addShape/projectID").
   */
  public ErrorResponse(String message, String path) {
    this.message = message;
    this.path = path;

    Timestamp ts = new Timestamp(System.currentTimeMillis());
    timestamp = ts.toString();
  }

  /**
   * Returns the timestamp of the error.
   *
   * @return Timestamp in {@link String}.
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp.
   *
   * @param timestamp Timestamp in {@link String}.
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Returns the HTML status code (default: 500).
   *
   * @return The HTML status code in {@link Integer}.
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets the HTML status code.
   *
   * @param status HTML status code in {@link Integer}.
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * Returns the HTML error name. Gets called whenever the server returns an error to the client.
   *
   * @return The HTML error name.
   */
  public String getError() {
    return error;
  }

  /**
   * Changes the error to the one received as parameter.
   *
   * @param error The HTML error name.
   */
  public void setError(String error) {
    this.error = error;
  }

  /**
   * Returns the error message in {@link String}.
   *
   * @return Error message in {@link String}.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Changes the error message to the one received as parameter.
   *
   * @param message Error message in {@link String}.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Returns the path in {@link String}. Gets called whenever the server returns an error to the client.
   *
   * @return The path in {@link String}.
   */
  public String getPath() {
    return path;
  }

  /**
   * Changes the path to the one received as parameter.
   *
   * @param path The path in {@link String}.
   */
  public void setPath(String path) {
    this.path = path;
  }
}

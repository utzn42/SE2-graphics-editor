package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

/**
 * Request parameter object for POST {projectID}/editProject.
 *
 * @see main.input.RESTHandler
 */
public class RequestEditProject {

  private final Optional<Double> width;
  private final Optional<Double> height;
  private final Optional<Boolean> allowTransformAttribute;

  /**
   * Creates a Request from the given parameters.
   *
   * @param width (Optional) The desired width for the project canvas.
   * @param height (Optional) The desired height for the project canvas.
   * @param allowTransformAttribute (Optional) <code>true</code> if the HTML "transform" attribute should be allowed for the project, <code>false</code> if it should be disallowed.
   */
  @JsonCreator()
  public RequestEditProject(
      @JsonProperty("width") Optional<Double> width,
      @JsonProperty("height") Optional<Double> height,
      @JsonProperty("allowTransformAttribute") Optional<Boolean> allowTransformAttribute
  ) {
    this.width = width;
    this.height = height;
    this.allowTransformAttribute = allowTransformAttribute;
  }

  /**
   * (Optional) Returns the desired width for the project canvas.
   *
   * @return (Optional) The desired width for the project canvas.
   */
  public Optional<Double> getWidth() {
    return width;
  }

  /**
   * (Optional) Returns the desired height for the project canvas.
   *
   * @return (Optional) The desired height for the project canvas.
   */
  public Optional<Double> getHeight() {
    return height;
  }

  /**
   * (Optional) Returns <code>true</code> if the HTML "transform" attribute should be allowed for the project.
   *
   * @return (Optional) <code>true</code> if the HTML "transform" attribute should be allowed for the project, <code>false</code> if it should be disallowed.
   */
  public Optional<Boolean> doesAllowTransformAttribute() {
    return allowTransformAttribute;
  }

}

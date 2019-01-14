package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import messages.creators.Creator;
import messages.creators.shapes.transform.atomic.AtomicTransformationCreator;
import shapes.transform.atomic.AtomicTransformation;

/**
 * Request parameter object for POST {projectID}/transformElement.
 *
 * @see main.input.RESTHandler
 */
public class RequestTransformElement {

  private final long elementID;
  private final Optional<Boolean> clearAll;
  private final Optional<AtomicTransformationCreator> transform;

  /**
   * Creates a Request from the given parameters.
   *
   * @param elementID The ID of the element to transform.
   * @param clearAll (Optional) <code>true</code> if all previous transformations should be cleared from the element.
   * @param transform (Optional) An {@link AtomicTransformationCreator} object specifying the transformation to apply to the element.
   */
  @JsonCreator
  public RequestTransformElement(
      @JsonProperty("elementID") long elementID,
      @JsonProperty("clearAll") Optional<Boolean> clearAll,
      @JsonProperty("transform") Optional<AtomicTransformationCreator> transform
  ) {
    this.elementID = elementID;
    this.clearAll = clearAll;
    this.transform = transform;
  }

  /**
   * Returns the ID of the element to transform.
   *
   * @return The ID of the element to transform.
   */
  public long getElementID() {
    return elementID;
  }

  /**
   * (Optional) Returns <code>true</code> if all previous transformations should be cleared from the element.
   *
   * @return (Optional) <code>true</code> if all previous transformations should be cleared from the element, <code>false</code> otherwise.
   */
  public Optional<Boolean> getClearAll() {
    return clearAll;
  }

  /**
   * (Optional) Returns the {@link AtomicTransformation} to apply to the element.
   *
   * @return (Optional) The AtomicTransformation to apply to the element.
   */
  public Optional<AtomicTransformation> getTransformation() {
    return transform.map(Creator::create);
  }

}

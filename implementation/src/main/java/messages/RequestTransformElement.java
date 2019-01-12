package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import messages.creators.Creator;
import messages.creators.shapes.transform.atomic.AtomicTransformationCreator;
import shapes.transform.atomic.AtomicTransformation;

public class RequestTransformElement {

  private final long elementID;
  private final Optional<Boolean> clearAll;
  private final Optional<AtomicTransformationCreator> transform;

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

  public long getElementID() {
    return elementID;
  }

  public Optional<Boolean> getClearAll() {
    return clearAll;
  }

  public Optional<AtomicTransformation> getTransformation() {
    return transform.map(Creator::create);
  }

}

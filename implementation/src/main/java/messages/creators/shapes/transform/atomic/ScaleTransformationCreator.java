package messages.creators.shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.Optional;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.ScaleTransformation;

/**
 * {@link messages.creators.Creator} for a {@link ScaleTransformation}.
 */
public class ScaleTransformationCreator extends AtomicTransformationCreator {

  private final Coordinate scale;

  /**
   * Constructs a {@link messages.creators.Creator} for a {@link ScaleTransformation} from the given parameters.
   *
   * @param origin (Optional) The origin for the transformation.
   * @param scale The x- and y-scale factors for the transformation.
   */
  @JsonCreator
  public ScaleTransformationCreator(
      @JsonProperty("origin") Optional<Coordinate> origin,
      @JsonProperty("scale") Coordinate scale
  ) {
    super(origin);
    this.scale = scale;
  }

  @Override
  public AtomicTransformation create() {
    AtomicTransformation transformation = new ScaleTransformation(scale);
    if (getOrigin().isPresent()) {
      return new OriginDecorator(transformation, getOrigin().get());
    }
    return transformation;
  }
}

package messages.creators.shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.Optional;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.UniformScaleTransformation;

/**
 * {@link messages.creators.Creator} for a {@link UniformScaleTransformation}.
 */
public class UniformScaleTransformationCreator extends AtomicTransformationCreator {

  private final double scale;

  /**
   * Constructs a {@link messages.creators.Creator} for a {@link UniformScaleTransformation} from the given parameters.
   *
   * @param origin (Optional) The origin for the transformation.
   * @param scale The scale factor for the transformation.
   */
  @JsonCreator
  public UniformScaleTransformationCreator(
      @JsonProperty("origin")Optional<Coordinate> origin,
      @JsonProperty("scale") double scale
  ) {
    super(origin);
    this.scale = scale;
  }

  @Override
  public AtomicTransformation create() {
    AtomicTransformation transformation = new UniformScaleTransformation(scale);
    if (getOrigin().isPresent()) {
      return new OriginDecorator(transformation, getOrigin().get());
    }
    return transformation;
  }
}

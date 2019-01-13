package messages.creators.shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.Optional;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.atomic.UniformScaleTransformation;

public class SkewTransformationCreator extends AtomicTransformationCreator {

  private final double skewAngle;
  private final String skewAxis;

  @JsonCreator
  public SkewTransformationCreator(
      @JsonProperty("origin")Optional<Coordinate> origin,
      @JsonProperty("skewAngle") double skewAngle,
      @JsonProperty("skewAxis") String skewAxis
  ) {
    super(origin);
    this.skewAngle = skewAngle;
    this.skewAxis = skewAxis;
  }

  @Override
  public AtomicTransformation create() {
    AtomicTransformation transformation = new SkewTransformation(skewAngle, skewAxis);
    if (getOrigin().isPresent()) {
      return new OriginDecorator(transformation, getOrigin().get());
    }
    return transformation;
  }
}

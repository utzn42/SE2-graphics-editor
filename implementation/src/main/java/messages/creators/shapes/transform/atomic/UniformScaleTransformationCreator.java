package messages.creators.shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.Optional;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.UniformScaleTransformation;

public class UniformScaleTransformationCreator extends AtomicTransformationCreator {

  private final double scale;

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

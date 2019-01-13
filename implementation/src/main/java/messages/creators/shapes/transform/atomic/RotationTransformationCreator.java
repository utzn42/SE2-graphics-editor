package messages.creators.shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.Optional;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.RotationTransformation;

public class RotationTransformationCreator extends AtomicTransformationCreator {

  private final double rotationAngle;

  @JsonCreator
  public RotationTransformationCreator(
      @JsonProperty("origin")Optional<Coordinate> origin,
      @JsonProperty("rotationAngle") double rotationAngle
  ) {
    super(origin);
    this.rotationAngle = rotationAngle;
  }

  @Override
  public AtomicTransformation create() {
    AtomicTransformation transformation = new RotationTransformation(rotationAngle);
    if (getOrigin().isPresent()) {
      return new OriginDecorator(transformation, getOrigin().get());
    }
    return transformation;
  }
}

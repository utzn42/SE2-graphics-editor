package messages.creators.shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import facilitators.Coordinate;
import java.util.Optional;
import messages.creators.Creator;
import shapes.transform.atomic.AtomicTransformation;

/**
 * {@link messages.creators.Creator} for an {@link AtomicTransformation}.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "transformationType")
@JsonSubTypes({
    @Type(value = RotationTransformationCreator.class, name = "ROTATE"),
    @Type(value = ScaleTransformationCreator.class, name = "SCALE"),
    @Type(value = SkewTransformationCreator.class, name = "SKEW"),
    @Type(value = TranslationTransformationCreator.class, name = "TRANSLATE"),
    @Type(value = UniformScaleTransformationCreator.class, name = "UNIFORM_SCALE")
})
public abstract class AtomicTransformationCreator implements Creator<AtomicTransformation> {

  private final Optional<Coordinate> origin;

  /**
   * Constructs a {@link messages.creators.Creator} for an {@link AtomicTransformation} from the given parameters.
   *
   * @param origin (Optional) The origin for the transformation.
   */
  @JsonCreator
  public AtomicTransformationCreator(
      @JsonProperty("origin") Optional<Coordinate> origin
  ) {
    this.origin = origin;
  }

  /**
   * (Optional) Returns the origin for the transformation.
   *
   * @return (Optional) The origin for the transformation.
   */
  public Optional<Coordinate> getOrigin() {
    return origin;
  }

}

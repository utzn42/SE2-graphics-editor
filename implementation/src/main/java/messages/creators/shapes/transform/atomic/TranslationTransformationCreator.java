package messages.creators.shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.Optional;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.TranslationTransformation;

/**
 * {@link messages.creators.Creator} for a {@link TranslationTransformation}.
 */
public class TranslationTransformationCreator extends AtomicTransformationCreator {

  private final Coordinate translation;

  /**
   * Constructs a {@link messages.creators.Creator} for a {@link TranslationTransformation} from the given parameters.
   *
   * @param origin (Optional) The origin for the transformation.
   * @param translation The x- and y-translation for the transformation.
   */
  @JsonCreator
  public TranslationTransformationCreator(
      @JsonProperty("origin") Optional<Coordinate> origin,
      @JsonProperty("translation") Coordinate translation
  ) {
    super(origin);
    this.translation = translation;
  }

  @Override
  public AtomicTransformation create() {
    AtomicTransformation transformation = new TranslationTransformation(translation);
    if (getOrigin().isPresent()) {
      return new OriginDecorator(transformation, getOrigin().get());
    }
    return transformation;
  }
}

package messages.modifiers.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import messages.modifiers.facilitators.ColourModifier;
import messages.modifiers.facilitators.CoordinateModifier;
import shapes.Shape;
import shapes.ShapeDecorator;
import shapes.Star;

/**
 * Holds all parameters that may be changed for a {@link shapes.Star}.
 */
public class StarModifier extends RegularPolygonModifier {

  private final Optional<Double> innerRadius;

  /**
   * Creates a Star object with the given values.
   *
   * @param fillColour (Optional) The fill colour of the Star.
   * @param strokeColour (Optional) The stroke colour of the Star.
   * @param strokeWidth (Optional) The stroke width of the Star.
   * @param opacity (Optional) The opacity of the Star. Range: 0-1.
   * @param center (Optional) The center of the Star.
   * @param radius (Optional) The radius of the Star.
   * @param edgeAmount (Optional) The number of edges of the Star.
   * @param innerRadius (Optional) The inner radius of the Star.
   */
  @JsonCreator
  public StarModifier(
      @JsonProperty("fillColour") Optional<ColourModifier> fillColour,
      @JsonProperty("strokeColour") Optional<ColourModifier> strokeColour,
      @JsonProperty("strokeWidth") Optional<Double> strokeWidth,
      @JsonProperty("opacity") Optional<Double> opacity,
      @JsonProperty("center") Optional<CoordinateModifier> center,
      @JsonProperty("radius") Optional<Double> radius,
      @JsonProperty("edgeAmount") Optional<Integer> edgeAmount,
      @JsonProperty("innerRadius") Optional<Double> innerRadius
  ) {
    super(fillColour, strokeColour, strokeWidth, opacity, center, radius, edgeAmount);
    this.innerRadius = innerRadius;
  }

  /**
   * (Optional) Returns the inner radius of the Star.
   *
   * @return (Optional) The inner radius of the Star.
   */
  public Optional<Double> getInnerRadius() {
    return innerRadius;
  }

  @Override
  public Shape apply(Shape objectToModify) {

    objectToModify = super.apply(objectToModify);

    Shape unpackedObject = objectToModify;
    while (unpackedObject instanceof ShapeDecorator) {
      unpackedObject = ((ShapeDecorator) unpackedObject).getShape();
    }

    if (unpackedObject instanceof Star) {
      Star star = (Star) unpackedObject;
      innerRadius.ifPresent(star::setInnerRadius);
    }

    return objectToModify;
  }
}

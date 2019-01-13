package messages.modifiers.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import messages.modifiers.facilitators.ColourModifier;
import messages.modifiers.facilitators.CoordinateModifier;
import shapes.Circle;
import shapes.Shape;
import shapes.ShapeDecorator;

/**
 * Holds all parameters that may be changed for a {@link shapes.Circle}.
 */
public class CircleModifier extends ShapeModifier {

  private final Optional<CoordinateModifier> center;
  private final Optional<Double> radius;

  /**
   * Creates a Circle object with the given values.
   *
   * @param fillColour (Optional) The fill colour of the Circle.
   * @param strokeColour (Optional) The stroke colour of the Circle.
   * @param strokeWidth (Optional) The stroke width of the Circle.
   * @param opacity (Optional) The opacity of the Circle. Range: 0-1.
   * @param center (Optional) The center of the Circle.
   * @param radius (Optional) The radius of the Circle.
   */
  @JsonCreator
  public CircleModifier(
      @JsonProperty("fillColour") Optional<ColourModifier> fillColour,
      @JsonProperty("strokeColour") Optional<ColourModifier> strokeColour,
      @JsonProperty("strokeWidth") Optional<Double> strokeWidth,
      @JsonProperty("opacity") Optional<Double> opacity,
      @JsonProperty("center") Optional<CoordinateModifier> center,
      @JsonProperty("radius") Optional<Double> radius
  ) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.center = center;
    this.radius = radius;
  }

  /**
   * (Optional) Returns the center of the Circle.
   *
   * @return (Optional) The center of the Circle.
   */
  public Optional<CoordinateModifier> getCenter() {
    return center;
  }

  /**
   * (Optional) Returns the radius of the Circle.
   *
   * @return (Optional) The radius of the Circle.
   */
  public Optional<Double> getRadius() {
    return radius;
  }

  @Override
  public Shape apply(Shape objectToModify) {

    objectToModify = super.apply(objectToModify);

    Shape unpackedObject = objectToModify;
    while (unpackedObject instanceof ShapeDecorator) {
      unpackedObject = ((ShapeDecorator) unpackedObject).getShape();
    }

    if (unpackedObject instanceof Circle) {
      Circle circle = (Circle) unpackedObject;
      center.ifPresent(newCenter -> circle.setCenter(newCenter.apply(circle.getCenter())));
      radius.ifPresent(circle::setRadius);
    }

    return objectToModify;

  }
}

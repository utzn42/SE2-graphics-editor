package messages.modifiers.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import messages.modifiers.facilitators.ColourModifier;
import messages.modifiers.facilitators.CoordinateModifier;
import shapes.Ellipse;
import shapes.Shape;
import shapes.ShapeDecorator;

/**
 * Holds all parameters that may be changed for a {@link shapes.Ellipse}.
 */
public class EllipseModifier extends ShapeModifier {

  private final Optional<CoordinateModifier> center;
  private final Optional<Double> radiusX;
  private final Optional<Double> radiusY;

  /**
   * Creates an Ellipse object with the given values.
   *
   * @param fillColour (Optional) The fill colour of the Ellipse.
   * @param strokeColour (Optional) The stroke colour of the Ellipse.
   * @param strokeWidth (Optional) The stroke width of the Ellipse.
   * @param opacity (Optional) The opacity of the Ellipse. Range: 0-1.
   * @param center (Optional) The center of the Ellipse.
   * @param radiusX (Optional) The x-radius of the Ellipse.
   * @param radiusY (Optional) The y-radius of the Ellipse.
   */
  @JsonCreator
  public EllipseModifier(
      @JsonProperty("fillColour") Optional<ColourModifier> fillColour,
      @JsonProperty("strokeColour") Optional<ColourModifier> strokeColour,
      @JsonProperty("strokeWidth") Optional<Double> strokeWidth,
      @JsonProperty("opacity") Optional<Double> opacity,
      @JsonProperty("center") Optional<CoordinateModifier> center,
      @JsonProperty("radiusX") Optional<Double> radiusX,
      @JsonProperty("radiusY") Optional<Double> radiusY
  ) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.center = center;
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }

  /**
   * (Optional) Returns the center of the Ellipse.
   *
   * @return (Optional) The center of the Ellipse.
   */
  public Optional<CoordinateModifier> getCenter() {
    return center;
  }

  /**
   * (Optional) Returns the x-radius of the Ellipse.
   *
   * @return (Optional) The x-radius of the Ellipse.
   */
  public Optional<Double> getRadiusX() {
    return radiusX;
  }

  /**
   * (Optional) Returns the y-radius of the Ellipse.
   *
   * @return (Optional) The y-radius of the Ellipse.
   */
  public Optional<Double> getRadiusY() {
    return radiusY;
  }

  @Override
  public Shape apply(Shape objectToModify) {

    objectToModify = super.apply(objectToModify);

    Shape unpackedObject = objectToModify;
    while (unpackedObject instanceof ShapeDecorator) {
      unpackedObject = ((ShapeDecorator) unpackedObject).getShape();
    }

    if (unpackedObject instanceof Ellipse) {
      Ellipse ellipse = (Ellipse) unpackedObject;
      center.ifPresent(newCenter -> ellipse.setCenter(newCenter.apply(ellipse.getCenter())));
      radiusX.ifPresent(ellipse::setRadiusX);
      radiusY.ifPresent(ellipse::setRadiusY);
    }
    return objectToModify;

  }
}

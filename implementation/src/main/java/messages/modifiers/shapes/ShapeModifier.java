package messages.modifiers.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Optional;
import messages.modifiers.Modifier;
import messages.modifiers.facilitators.ColourModifier;
import shapes.Shape;

/**
 * Holds all parameters that may be changed for a {@link shapes.Shape}.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    defaultImpl = ShapeModifier.class,
    property = "shapeType")
@JsonSubTypes({
    @Type(value = CircleModifier.class, name = "CIRCLE"),
    @Type(value = EllipseModifier.class, name = "ELLIPSE"),
    @Type(value = LineModifier.class, name = "LINE"),
    @Type(value = LineModifier.class, name = "POLYGON"),
    @Type(value = RegularPolygonModifier.class, name = "REGULAR_POLYGON"),
    @Type(value = StarModifier.class, name = "STAR"),
    @Type(value = TextModifier.class, name = "TEXT")
})
public class ShapeModifier implements Modifier<Shape> {

  private final Optional<ColourModifier> fillColour;
  private final Optional<ColourModifier> strokeColour;
  private final Optional<Double> strokeWidth;
  private final Optional<Double> opacity;

  /**
   * Creates a Shape object with the given values.
   *
   * @param fillColour (Optional) The fill colour of the Shape.
   * @param strokeColour (Optional) The stroke colour of the Shape.
   * @param strokeWidth (Optional) The stroke width of the Shape.
   * @param opacity (Optional) The opacity of the Shape. Range: 0-1.
   */
  @JsonCreator
  public ShapeModifier(
      @JsonProperty("fillColour") Optional<ColourModifier> fillColour,
      @JsonProperty("strokeColour") Optional<ColourModifier> strokeColour,
      @JsonProperty("strokeWidth") Optional<Double> strokeWidth,
      @JsonProperty("opacity") Optional<Double> opacity
  ) {
    this.fillColour = fillColour;
    this.strokeColour = strokeColour;
    this.strokeWidth = strokeWidth;
    this.opacity = opacity;
  }

  /**
   * (Optional) Returns the fill colour of the Shape.
   *
   * @return (Optional) The fill colour of the Shape.
   */
  public Optional<ColourModifier> getFillColour() {
    return fillColour;
  }

  /**
   * (Optional) Returns the stroke colour of the Shape.
   *
   * @return (Optional) The stroke colour of the Shape.
   */
  public Optional<ColourModifier> getStrokeColour() {
    return strokeColour;
  }

  /**
   * (Optional) Returns the stroke width of the Shape.
   *
   * @return (Optional) The stroke width of the Shape.
   */
  public Optional<Double> getStrokeWidth() {
    return strokeWidth;
  }

  /**
   * (Optional) Returns the opacity of the Shape. Range: 0-1.
   *
   * @return (Optional) The opacity of the Shape. Range: 0-1.
   */
  public Optional<Double> getOpacity() {
    return opacity;
  }

  @Override
  public Shape apply(Shape objectToModify) {

    fillColour.ifPresent(newFillColour -> objectToModify.setFillColour(newFillColour.apply(objectToModify.getFillColour())));
    strokeColour.ifPresent(newStrokeColour -> objectToModify.setStrokeColour(newStrokeColour.apply(objectToModify.getStrokeColour())));
    strokeWidth.ifPresent(objectToModify::setStrokeWidth);
    opacity.ifPresent(objectToModify::setOpacity);

    return objectToModify;
  }
}

package messages.modifiers.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import messages.modifiers.facilitators.ColourModifier;
import messages.modifiers.facilitators.CoordinateModifier;
import shapes.RegularPolygon;
import shapes.Shape;
import shapes.ShapeDecorator;

/**
 * Holds all parameters that may be changed for a {@link shapes.RegularPolygon}.
 */
public class RegularPolygonModifier extends CircleModifier {

  private final Optional<Integer> edgeAmount;

  /**
   * Creates a RegularPolygon object with the given values.
   *
   * @param fillColour (Optional) The fill colour of the RegularPolygon.
   * @param strokeColour (Optional) The stroke colour of the RegularPolygon.
   * @param strokeWidth (Optional) The stroke width of the RegularPolygon.
   * @param opacity (Optional) The opacity of the RegularPolygon. Range: 0-1.
   * @param center (Optional) The center of the RegularPolygon.
   * @param radius (Optional) The radius of the RegularPolygon.
   * @param edgeAmount (Optional) The number of edges of the RegularPolygon.
   */
  @JsonCreator
  public RegularPolygonModifier(
      @JsonProperty("fillColour") Optional<ColourModifier> fillColour,
      @JsonProperty("strokeColour") Optional<ColourModifier> strokeColour,
      @JsonProperty("strokeWidth") Optional<Double> strokeWidth,
      @JsonProperty("opacity") Optional<Double> opacity,
      @JsonProperty("center") Optional<CoordinateModifier> center,
      @JsonProperty("radius") Optional<Double> radius,
      @JsonProperty("edgeAmount") Optional<Integer> edgeAmount
  ) {
    super(fillColour, strokeColour, strokeWidth, opacity, center, radius);
    this.edgeAmount = edgeAmount;
  }

  /**
   * (Optional) Returns the number of edges of the RegularPolygon.
   *
   * @return (Optional) The number of edges of the RegularPolygon.
   */
  public Optional<Integer> getEdgeAmount() {
    return edgeAmount;
  }

  @Override
  public Shape apply(Shape objectToModify) {

    objectToModify = super.apply(objectToModify);

    Shape unpackedObject = objectToModify;
    while (unpackedObject instanceof ShapeDecorator) {
      unpackedObject = ((ShapeDecorator) unpackedObject).getShape();
    }

    if (unpackedObject instanceof RegularPolygon) {
      RegularPolygon regularPolygon = (RegularPolygon) unpackedObject;
      edgeAmount.ifPresent(regularPolygon::setEdgeAmount);
    }

    return objectToModify;
  }
}

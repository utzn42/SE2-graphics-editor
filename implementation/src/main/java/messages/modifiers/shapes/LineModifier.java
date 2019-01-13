package messages.modifiers.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.List;
import java.util.Optional;
import messages.modifiers.facilitators.ColourModifier;
import messages.modifiers.facilitators.CoordinateModifier;
import shapes.Line;
import shapes.Shape;
import shapes.ShapeDecorator;

/**
 * Holds all parameters that may be changed for a {@link shapes.Line} or {@link shapes.Polygon}.
 */
public class LineModifier extends ShapeModifier {

  private final Optional<List<CoordinateModifier>> coordinates;

  /**
   * Creates a Line or Polygon object with the given values.
   *
   * @param fillColour (Optional) The fill colour of the Line or Polygon.
   * @param strokeColour (Optional) The stroke colour of the Line or Polygon.
   * @param strokeWidth (Optional) The stroke width of the Line or Polygon.
   * @param opacity (Optional) The opacity of the Line or Polygon. Range: 0-1.
   * @param coordinates (Optional) The coordinates of the Line or Polygon.
   */
  @JsonCreator
  public LineModifier(
      @JsonProperty("fillColour") Optional<ColourModifier> fillColour,
      @JsonProperty("strokeColour") Optional<ColourModifier> strokeColour,
      @JsonProperty("strokeWidth") Optional<Double> strokeWidth,
      @JsonProperty("opacity") Optional<Double> opacity,
      @JsonProperty("coordinates") Optional<List<CoordinateModifier>> coordinates
  ) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.coordinates = coordinates;
  }

  /**
   * (Optional) Returns the coordinates of the Line or Polygon.
   *
   * @return (Optional) The coordinates of the Line or Polygon.
   */
  public Optional<List<CoordinateModifier>> getCoordinates() {
    return coordinates;
  }

  @Override
  public Shape apply(Shape objectToModify) {

    objectToModify = super.apply(objectToModify);

    Shape unpackedObject = objectToModify;
    while (unpackedObject instanceof ShapeDecorator) {
      unpackedObject = ((ShapeDecorator) unpackedObject).getShape();
    }

    if (unpackedObject instanceof Line) {
      Line line = (Line) unpackedObject;
      coordinates.ifPresent(newCoordinates -> {
        for (int i = 0; i < newCoordinates.size() || i < line.getCoordinates().size(); ++i) {
          if (i < line.getCoordinates().size() && i < newCoordinates.size()) {
            line.getCoordinates().set(i, newCoordinates.get(i).apply(line.getCoordinates().get(i)));
          } else if (i < newCoordinates.size()) {
            line.getCoordinates().add(newCoordinates.get(i).apply(new Coordinate(0, 0)));
          } else {
            line.getCoordinates().remove(i--);
          }
        }
      });
    }
    return objectToModify;

  }
}

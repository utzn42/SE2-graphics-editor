package messages.modifiers.facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Coordinate;
import java.util.Optional;
import messages.modifiers.Modifier;

/**
 * Holds all parameters that may be changed for a {@link facilitators.Coordinate}.
 */
public class CoordinateModifier implements Modifier<Coordinate> {

  private final Optional<Double> x;
  private final Optional<Double> y;

  /**
   * Creates a Coordinate object with the given values.
   *
   * @param x (Optional) The x-value of the Coordinate.
   * @param y (Optional) The y-value of the Coordinate.
   */
  @JsonCreator
  public CoordinateModifier(
      @JsonProperty("x") Optional<Double> x,
      @JsonProperty("y") Optional<Double> y
  ) {
    this.x = x;
    this.y = y;
  }

  /**
   * (Optional) Returns the x-value of the Coordinate.
   *
   * @return (Optional) The x-value of the Coordinate.
   */
  public Optional<Double> getX() {
    return x;
  }

  /**
   * (Optional) Returns the y-value of the Coordinate.
   *
   * @return (Optional) The y-value of the Coordinate.
   */
  public Optional<Double> getY() {
    return y;
  }

  @Override
  public Coordinate apply(Coordinate objectToModify) {
    double x = objectToModify.getX();
    double y = objectToModify.getY();
    if (this.x.isPresent()) {
      x = this.x.get();
    }
    if (this.y.isPresent()) {
      y = this.y.get();
    }
    return new Coordinate(x, y);
  }
}

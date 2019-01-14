package facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * This class represents a point. Every {@link shapes.Shape} has at least one point. It consists out of x and y.
 *
 * @see shapes.Shape
 */
public class Coordinate implements Serializable {

  private static final long serialVersionUID = 1;

  private final double X;
  private final double Y;

  /**
   * The constructor assigns the parameters x and y to the json object. It expects x and y in {@link Double}.
   * @param x value of x in {@link Double}
   * @param y value of y {@link Double}
   */
  @JsonCreator
  public Coordinate(
      @JsonProperty("x") double x,
      @JsonProperty("y") double y
  ) {
    this.X = x;
    this.Y = y;
  }

  /**
   * Returns the x value in {@link Double}. Gets called whenever the "getHTML" method gets called for a shape.
   * @return returns the x value in {@link Double}
   */
  public double getX() {
    return X;
  }

  /**
   * Returns the y value in {@link Double}. Gets called whenever the "getHTML" method gets called for a shape.
   * @return returns the y value in {@link Double}
   */
  public double getY() {
    return Y;
  }

}

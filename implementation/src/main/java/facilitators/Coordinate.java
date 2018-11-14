package facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {

  private final double X;
  private final double Y;

  @JsonCreator
  public Coordinate(
      @JsonProperty("x") double x,
      @JsonProperty("y") double y
  ) {
    this.X = x;
    this.Y = y;
  }

  public double getX() {
    return X;
  }

  public double getY() {
    return Y;
  }

}

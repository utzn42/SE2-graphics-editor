package shapes;

import facilitators.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class Line extends Shape {

  private List<Coordinate> coordinates;

  public Line() {
    this.coordinates = new ArrayList<>();
    this.coordinates.add(new Coordinate(0, 0));
    this.coordinates.add(new Coordinate(100, 100));
  }

  public Line(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  public List<Coordinate> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  protected String getHTMLAttributes() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Coordinate coordinate : coordinates) {
      stringBuilder.append(coordinate.getX())
          .append(",")
          .append(coordinate.getY())
          .append(" ");
    }
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    String points = stringBuilder.toString();
    return super.getHTMLAttributes() + " points=\"" + points + "\"";
  }

  @Override
  public String getHTML() {
    return "<polyline " + getHTMLAttributes() + "></polyline>";
  }
}

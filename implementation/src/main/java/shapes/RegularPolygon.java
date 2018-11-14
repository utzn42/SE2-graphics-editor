package shapes;

import facilitators.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class RegularPolygon extends Circle {

  private int edgeAmount;

  public RegularPolygon() {
    edgeAmount = 5;
  }

  public RegularPolygon(int edgeAmount) {
    this.edgeAmount = edgeAmount;
  }

  public int getEdgeAmount() {
    return edgeAmount;
  }

  public void setEdgeAmount(int edgeAmount) {
    this.edgeAmount = edgeAmount;
  }

  private List<Coordinate> getPolygonCoordinates() {
    List<Coordinate> coordinates = new ArrayList<>();
    for (int i = 0; i < edgeAmount; ++i) {
      float x = (float) (getRadius() * Math.sin((Math.PI + (2 * i * Math.PI)) / edgeAmount) + getCenter()
          .getX());
      float y = (float) (getRadius() * Math.cos((Math.PI + (2 * i * Math.PI)) / edgeAmount) + getCenter()
          .getY());
      coordinates.add(new Coordinate(x, y));
    }
    return coordinates;
  }

  @Override
  public String getHTML() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Coordinate coordinate : getPolygonCoordinates()) {
      stringBuilder.append(coordinate.getX())
          .append(",")
          .append(coordinate.getY())
          .append(" ");
    }
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    String points = stringBuilder.toString();
    return "<polygon" +
        " points=\"" + points + "\"" +
        " fill=\"" + getFillColour() + "\"" +
        " stroke=\"" + getStrokeColour() + "\"" +
        " stroke-width=\"" + getStrokeWidth() + "\"" +
        " opacity=\"" + getOpacity() + "\"" +
        "></polygon>";
  }
}

package shapes;

import facilitators.Coordinate;

public class Polygon extends Line {

  public Polygon() {
    getCoordinates().add(new Coordinate(0, 100));
  }

  @Override
  public String getHTML() {
    return super.getHTML().replaceAll("polyline", "polygon");
  }

}

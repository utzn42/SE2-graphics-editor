package shapes;

import facilitators.Coordinate;
import java.util.ArrayList;
import java.util.List;
import shapes.transform.Rotatable;
import shapes.transform.Rotator;
import shapes.transform.Scalable;
import shapes.transform.Scaler;
import shapes.transform.ShapeTransformer;
import shapes.transform.Skewable;
import shapes.transform.Skewer;
import shapes.transform.Translatable;
import shapes.transform.Translater;

/**
 * Represents a Line on the canvas. This class extends {@link Shape} by an array of {@link
 * Coordinate Coordinates} (the Line's vertices).
 *
 * @see Shape
 * @see Polygon
 */
public class Line extends Shape implements Translatable, Rotatable, Scalable, Skewable {

  private List<Coordinate> coordinates;

  /**
   * Creates a new Line with default attributes.
   */
  public Line() {
    this.coordinates = new ArrayList<>();
    this.coordinates.add(new Coordinate(0, 0));
    this.coordinates.add(new Coordinate(100, 100));
  }

  /**
   * Creates a new Line with the specified attributes and default {@link Shape} attributes.
   *
   * @param coordinates A {@link List} of the {@link Coordinate Coordinates} of the Line's
   * vertices.
   */
  public Line(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  /**
   * Returns a {@link List} of the {@link Coordinate Coordinates} of the Line's vertices.
   *
   * @return A {@link List} of the {@link Coordinate Coordinates} of the Line's vertices.
   */
  public List<Coordinate> getCoordinates() {
    return coordinates;
  }

  /**
   * Sets the Line's vertices. This operation overwrites the already existing vertices with the new
   * ones.
   *
   * @param coordinates A {@link List} of the {@link Coordinate Coordinates} of the Line's new
   * vertices.
   */
  public void setCoordinates(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  /**
   * Applies a transformation, given as a {@link ShapeTransformer}, to the Line. The Line class is
   * non-transformable, but all transformations should still be applicable.
   *
   * @param transformer The transformation to apply to the Line.
   */
  @Override
  public void applyTransformation(ShapeTransformer transformer) {
    if (transformer.getTranslation() != null) {
      translate(transformer.getTranslation());
    }
    if (transformer.getRotation() != null) {
      rotate(transformer.getRotation());
    }
    if (transformer.getScale() != null) {
      scale(transformer.getScale());
    }
    if (transformer.getSkew() != null) {
      skew(transformer.getSkew());
    }
  }

  /**
   * Returns a String representation of the Line's attributes as chained HTML attributes.
   *
   * @return A String representation of the Line's attributes as chained HTML attributes.
   * @see Shape#getHTMLAttributes()
   */
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

  /**
   * Returns a String representation of the Line as an SVG object in HTML.
   *
   * @return A String representation of the Line as an SVG object in HTML.
   */
  @Override
  public String getHTML() {
    return "<polyline " + getHTMLAttributes() + "></polyline>";
  }

  /**
   * Helper function. Translates and returns a single {@link Coordinate} using a {@link Translater}.
   *
   * @param coordinate The {@link Coordinate} to translate.
   * @param translater The translation to apply to the Coordinate.
   * @return The translated {@link Coordinate}.
   */
  private static Coordinate translateCoordinate(Coordinate coordinate, Translater translater) {
    double newX = coordinate.getX() + translater.getTranslation().getX();
    double newY = coordinate.getY() + translater.getTranslation().getY();
    return new Coordinate(newX, newY);
  }

  /**
   * Translates the Line using a {@link Translater}.
   *
   * @param translater The translation to apply to the Line.
   */
  @Override
  public void translate(Translater translater) {

    List<Coordinate> newCoordinates = new ArrayList<>();
    coordinates.forEach(
        coordinate -> newCoordinates.add(translateCoordinate(coordinate, translater))
    );
  }

  /**
   * Rotates the Line using a {@link Rotator}.
   *
   * @param rotator The rotation to apply to the Line.
   */
  @Override
  public void rotate(Rotator rotator) {
    //TODO: Implement shapes.Line#rotate(Rotator)
  }

  /**
   * Scales the Line using a {@link Scaler}.
   *
   * @param scaler The scaler to apply to the Line.
   */
  @Override
  public void scale(Scaler scaler) {
    //TODO: Implement shapes.Line#scale(Scaler)
  }

  /**
   * Skews the Line using a {@link Skewer}.
   *
   * @param skewer The skew to apply to the Line.
   */
  @Override
  public void skew(Skewer skewer) {
    //TODO: Implement shapes.Line#skew(Skewer)
  }

  /**
   * Returns the center of the Line as a {@link Coordinate}.
   *
   * @return The center of the Line as a {@link Coordinate}.
   */
  @Override
  public Coordinate getCenter() {
    if (coordinates.size() == 0) {
      return new Coordinate(0, 0);
    }
    Double minX = null;
    Double maxX = null;
    Double minY = null;
    Double maxY = null;
    for (Coordinate coordinate : coordinates) {
      double currentX = coordinate.getX();
      double currentY = coordinate.getY();
      if (minX == null) {
        minX = currentX;
        maxX = currentX;
        minY = currentY;
        maxY = currentY;
      }
      else {
        if (currentX < minX) {
          minX = currentX;
        }
        if (currentX > maxX) {
          maxX = currentX;
        }
        if (currentY < minY) {
          minY = currentY;
        }
        if (currentY > maxY) {
          maxY = currentY;
        }
      }
    }
    return new Coordinate((minX + maxX) / 2, (minY + maxY) / 2);
  }
}

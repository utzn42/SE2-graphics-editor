package shapes;

import facilitators.Coordinate;
import facilitators.CoordinateMath;
import java.util.ArrayList;
import java.util.List;
import shapes.transform.Rotatable;
import shapes.transform.UniformScalable;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.Scalable;
import shapes.transform.atomic.ScaleTransformation;
import shapes.transform.Skewable;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.Translatable;
import shapes.transform.atomic.TranslationTransformation;
import shapes.transform.atomic.UniformScaleTransformation;

/**
 * Represents a Line on the canvas. This class extends {@link Shape} by an array of {@link
 * Coordinate Coordinates} (the Line's vertices).
 *
 * @see Shape
 * @see Polygon
 */
public class Line extends Shape implements Translatable, Rotatable, UniformScalable, Scalable, Skewable {

  private static final long serialVersionUID = 1L;
  private static final ShapeType shapeType = ShapeType.LINE;

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
   * Translates the Line using a {@link TranslationTransformation}.
   *
   * @param transformation The transformation to apply to the Line.
   */
  @Override
  public void translate(TranslationTransformation transformation) {

    List<Coordinate> newCoordinates = new ArrayList<>();
    coordinates.forEach(
        coordinate -> newCoordinates.add(CoordinateMath.translateByCoordinate(coordinate,
            transformation.getTranslation()))
    );

    coordinates = newCoordinates;

  }

  /**
   * Rotates the Line using a {@link RotationTransformation}.
   *
   * @param transformation The transformation to apply to the Line.
   */
  @Override
  public void rotate(RotationTransformation transformation) {

    List<Coordinate> newCoordinates = new ArrayList<>();
    coordinates.forEach(
        coordinate -> newCoordinates.add(CoordinateMath.rotateAroundCoordinate(coordinate,
            transformation.getRotationAngle(), new Coordinate(0, 0)))
    );

    coordinates = newCoordinates;

  }

  /**
   * Scales the Line using a {@link UniformScaleTransformation}.
   *
   * @param transformation The transformation to apply to the Line.
   */
  @Override
  public void uniformScale(UniformScaleTransformation transformation) {

    List<Coordinate> newCoordinates = new ArrayList<>();
    coordinates.forEach(
        coordinate -> newCoordinates.add(CoordinateMath.scaleVector(coordinate,
            new Coordinate(transformation.getScale(), transformation.getScale()),
            new Coordinate(0, 0)))
    );

    coordinates = newCoordinates;

    setStrokeWidth(getStrokeWidth() * transformation.getScale());

  }

  /**
   * Scales the Line using a {@link ScaleTransformation}.
   *
   * @param transformation The transformation to apply to the Line.
   */
  @Override
  public void scale(ScaleTransformation transformation) {

    List<Coordinate> newCoordinates = new ArrayList<>();
    coordinates.forEach(
        coordinate -> newCoordinates.add(CoordinateMath.scaleVector(coordinate,
            transformation.getScale(), new Coordinate(0, 0)))
    );

    coordinates = newCoordinates;

  }

  /**
   * Skews the Line using a {@link SkewTransformation}.
   *
   * @param transformation The transformation to apply to the Line.
   */
  @Override
  public void skew(SkewTransformation transformation) {

    List<Coordinate> newCoordinates = new ArrayList<>();
    if (transformation.getSkewAxis().toUpperCase().equals("X")) {
      coordinates.forEach(
          coordinate -> newCoordinates.add(CoordinateMath.skewX(coordinate,
              transformation.getSkewAngle(), new Coordinate(0, 0)))
      );
    } else {
      coordinates.forEach(
          coordinate -> newCoordinates.add(CoordinateMath.skewY(coordinate,
              transformation.getSkewAngle(), new Coordinate(0, 0)))
      );
    }

    coordinates = newCoordinates;

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

  @Override
  public ShapeType getShapeType() {
    return shapeType;
  }

}

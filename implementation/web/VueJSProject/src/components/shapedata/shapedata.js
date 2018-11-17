/**
 * Describes attributes that all Shapes share.
 */
class Shape {

  constructor(fillColour, strokeColour, strokeWidth, opacity) {
    this.fillColour = {
      label: "Fill Colour",
      type: "colour",
      value: fillColour
    };
    this.strokeColour = {
      label: "Stroke Colour",
      type: "colour",
      value: strokeColour
    };
    this.strokeWidth = {
      label: "Stroke Width",
      type: "simple",
      value: strokeWidth
    };
    this.opacity = {
      label: "Opacity",
      type: "range:0-1",
      value: opacity
    };
  }

}


/**
 * Describes attributes of a Circle.
 * Extends {@link Shape}.
 */
export class Circle extends Shape {

  constructor(center, radius, fillColour, strokeColour, strokeWidth, opacity) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.center = {
      label: "Center",
      type: "coordinate",
      value: center
    };
    this.radius = {
      label: "Radius",
      type: "simple",
      value: radius
    };
  }

}


/**
 * Describes attributes of a RegularPolygon.
 * Extends {@link Circle}.
 */
export class RegularPolygon extends Circle {

  constructor(edgeAmount, center, radius, fillColour, strokeColour, strokeWidth, opacity) {
    super(center, radius, fillColour, strokeColour, strokeWidth, opacity);
    this.edgeAmount = {
      label: "Number of Edges",
      type: "simple",
      value: edgeAmount
    };
  }

}


/**
 * Describes attributes of a Star.
 * Extends {@link RegularPolygon}.
 */
export class Star extends RegularPolygon {

  constructor(innerRadius, edgeAmount, center, radius, fillColour, strokeColour, strokeWidth, opacity) {
    super(edgeAmount, center, radius, fillColour, strokeColour, strokeWidth, opacity);
    this.radius.label = "Outer Radius";
    this.edgeAmount.label = "Number of Outer Corners";
    this.innerRadius = {
      label: "Inner Radius",
      type: "simple",
      value: innerRadius
    };
  }

}


/**
 * Describes attributes of an Ellipse.
 * Extends {@link Shape}.
 */
export class Ellipse extends Shape {

  constructor(center, radiusX, radiusY, fillColour, strokeColour, strokeWidth, opacity) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.center = {
      label: "Center",
      type: "coordinate",
      value: center
    };
    this.radiusX = {
      label: "Radius X",
      type: "simple",
      value: radiusX
    };
    this.radiusY = {
      label: "Radius Y",
      type: "simple",
      value: radiusY
    };
  }

}


/**
 * Describes attributes of a Line.
 * Extends {@link Shape}.
 */
export class Line extends Shape {

  constructor(coordinates, fillColour, strokeColour, strokeWidth, opacity) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.coordinates = {
      label: "Points",
      type: "coordinateArray",
      value: coordinates
    };
  }

}


/**
 * Describes attributes of a Polygon.
 * Extends {@link Line}.
 */
export class Polygon extends Line {

  constructor(coordinates, fillColour, strokeColour, strokeWidth, opacity) {
    super(coordinates, fillColour, strokeColour, strokeWidth, opacity);
  }

}


/**
 * Describes attributes of a Text field.
 * Extends {@link Shape}.
 */
export class Text extends Shape {

  constructor(center, displayText, font, fontSize, fillColour, strokeColour, strokeWidth, opacity) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.center = {
      label: "Center",
      type: "coordinate",
      value: center
    };
    this.displayText = {
      label: "Text",
      type: "simple",
      value: displayText
    };
    this.font = {
      label: "Font",
      type: "simple",
      value: font
    };
    this.fontSize = {
      label: "Font Size",
      type: "simple",
      value: fontSize
    };
  }

}

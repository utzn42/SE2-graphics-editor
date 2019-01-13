
export class CanvasElement {

  constructor(id, visible) {
    this.id = id;
    this.visible = visible;
  }

}

export class Layer extends CanvasElement {

  constructor(id, visible, shape) {
    super(id, visible);
    this.shape = shape;
  }

}

export class LayerGroup extends CanvasElement {

  constructor(id, visible, elements) {
    super(id, visible);
    this.elements = elements;
  }

}

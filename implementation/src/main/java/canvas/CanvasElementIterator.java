package canvas;

import facilitators.Aggregate;
import facilitators.Iterator;

/**
 * An {@link Iterator} over {@link CanvasElement CanvasElements}
 */
public class CanvasElementIterator implements Iterator<CanvasElement> {

  private Aggregate<CanvasElement> aggregate;

  public CanvasElementIterator(Aggregate<CanvasElement> aggregate) {
    this.aggregate = aggregate;
  }

  @Override
  public CanvasElement first() {
    //TODO: Implement canvas.CanvasElementIterator#first()
    return null;
  }

  @Override
  public CanvasElement next() {
    //TODO: Implement canvas.CanvasElementIterator#next()
    return null;
  }

  @Override
  public boolean isDone() {
    //TODO: Implement canvas.CanvasElementIterator#isDone()
    return false;
  }

  @Override
  public CanvasElement currentItem() {
    //TODO: Implement canvas.CanvasElementIterator#currentItem()
    return null;
  }

}

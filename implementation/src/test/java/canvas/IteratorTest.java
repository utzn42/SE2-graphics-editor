package canvas;

import facilitators.Iterator;
import org.junit.jupiter.api.Test;

class IteratorTest {

  @Test
  void nullIteratorTest() {

  }

  @Test
  void listIteratorTest() {

  }

  @Test
  void canvasElementIteratorTest() {
    long testID = 0;
    CanvasElement testCollection = new CanvasElementAggregate(testID++);
    CanvasElement coll1 = new CanvasElementAggregate(testID++);
    CanvasElement layerElement1 = new CanvasLayer(testID++);
    CanvasElement layerElement2 = new CanvasLayer(testID++);
    CanvasElement layerElement3 = new CanvasLayer(testID++);

    testCollection.addItem(coll1);
    testCollection.addItem(layerElement1);
    testCollection.addItem(layerElement2);
    testCollection.getItem(0).addItem(layerElement3);

    Iterator<CanvasElement> testIterator = testCollection.createIterator();
    System.out.println(testIterator.hasNext());
    CanvasElement coll1Copy = testIterator.next();
    CanvasElement testIterItem = (CanvasElement) testIterator.get();
    testIterator.remove();
  }

}
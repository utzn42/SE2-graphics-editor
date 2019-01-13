package canvas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import facilitators.Iterator;
import facilitators.ListIterator;
import facilitators.NullIterator;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class IteratorTest {

  private static final Logger iteratorTestLogger = LoggerFactory
      .getLogger(IteratorTest.class);

  @Test
  void nullIteratorTest() {
    NullIterator<CanvasElement> nullIterTest = new NullIterator<>();
    CanvasElement ceTest = new CanvasLayer(0);
    assertFalse(nullIterTest.hasNext());
    assertEquals(null, nullIterTest.next());

    Throwable exception =
        assertThrows(UnsupportedOperationException.class, () -> nullIterTest.set(ceTest));
    Assertions.assertEquals(
        "Can't set a NullIterator!",
        exception.getMessage());

    assertEquals(null, nullIterTest.get());

    exception =
        assertThrows(UnsupportedOperationException.class, () -> nullIterTest.remove());
    Assertions.assertEquals(
        "Can't remove a NullIterator!",
        exception.getMessage());
  }

  @Test
  void listIteratorTest() {
    List<CanvasElement> testList = new ArrayList<>();
    CanvasElement coll1 = new CanvasElementAggregate(0);
    CanvasElement layerElement1 = new CanvasLayer(1);
    CanvasElement layerElement2 = new CanvasLayer(2);
    CanvasElement layerElement3 = new CanvasLayer(3);
    testList.add(coll1);
    testList.add(layerElement1);
    testList.add(layerElement2);
    testList.get(0).addItem(layerElement3);
    ListIterator iter = new ListIterator(testList);

    assertTrue(iter.hasNext());
    assertEquals(coll1, iter.get());
    iter.next();

    assertTrue(iter.hasNext());
    assertEquals(layerElement1, iter.get());
    iter.set(layerElement3);
    assertEquals(layerElement3, iter.get());
    iter.next();

    iter.remove();
    assertFalse(iter.hasNext());
  }

  @Test
  void canvasElementIteratorTest() {
    iteratorTestLogger.info("Starting iterator tests...");
    long testID = 0;
    CanvasElement testCollection = new CanvasElementAggregate(testID++);
    CanvasElement coll1 = new CanvasElementAggregate(testID++);
    CanvasElement layerElement1 = new CanvasLayer(testID++);
    CanvasElement layerElement2 = new CanvasLayer(testID++);
    CanvasElement layerElement3 = new CanvasLayer(testID++);
    iteratorTestLogger.info("Successfully created test objects with ascending test ID.");

    iteratorTestLogger.info("Adding items to test collection...");
    testCollection.addItem(coll1);
    testCollection.addItem(layerElement1);
    testCollection.addItem(layerElement2);
    testCollection.getItem(0).addItem(layerElement3);
    iteratorTestLogger.info("...complete!");

    iteratorTestLogger.info("Verifying results...");
    Iterator<CanvasElement> testIterator = testCollection.createIterator();
    assertTrue(testIterator.hasNext());
    assertEquals(testCollection.getItem(0), testIterator.next());
    assertEquals(layerElement3, testIterator.next());
    assertTrue(testIterator.hasNext());
    assertEquals(layerElement1, testIterator.next());
    assertTrue(testIterator.hasNext());
    assertEquals(layerElement2, testIterator.next());
    assertFalse(testIterator.hasNext());
    assertEquals(null, testIterator.next());
    testCollection.resetIterator();

    testIterator = testCollection.createIterator();
    assertEquals(coll1, testIterator.next());
    CanvasElement shouldbeID4 = testIterator.get();
    assertEquals(layerElement3, testIterator.next());
    testIterator.remove();
    testCollection.resetIterator();
    testIterator = testCollection.createIterator();
  }
}
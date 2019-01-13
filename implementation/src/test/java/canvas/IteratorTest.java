package canvas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import facilitators.Iterator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class IteratorTest {

  private static final Logger iteratorTestLogger = LoggerFactory
      .getLogger(IteratorTest.class);

  @AfterAll
  static void successMessage() {
    iteratorTestLogger.info("IteratorTest passed successfully!" + '\n');
  }

  @Test
  void canvasElementIteratorTest() {
    long testID = 0;
    CanvasElementAggregate testCollection = new CanvasElementAggregate(testID++);
    CanvasElementAggregate coll1 = new CanvasElementAggregate(testID++);
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
    assertNull(testIterator.next());
    iteratorTestLogger.info("Success!");

    iteratorTestLogger.info("Verifying iterator.set() and insert() methods...");
    testIterator = testCollection.createIterator();
    assertEquals(coll1, testIterator.next());
    CanvasElement shouldbeID1 = testIterator.currentItem();
    assertEquals(layerElement3, testIterator.next());
    testIterator.remove();

    testIterator = testCollection.createIterator();
    assertEquals(coll1, testIterator.next());
    testIterator.set(shouldbeID1);
    assertEquals(shouldbeID1, coll1);
    CanvasElement layerElement5 = new CanvasLayer(testID++);
    testIterator.insert(layerElement5);
    testIterator = testCollection.createIterator();
    testIterator.next();
    testIterator.insert(layerElement5);

    assertEquals(layerElement5, testCollection.createIterator().next());
    assertEquals(layerElement5, testCollection.getItem(0));
    iteratorTestLogger.info("Success!");
  }

}
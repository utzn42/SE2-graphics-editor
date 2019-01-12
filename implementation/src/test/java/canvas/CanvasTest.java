package canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import facilitators.Iterator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CanvasTest {

  private static final Logger canvasTestLogger = LoggerFactory
      .getLogger(CanvasTest.class);


  @Test
  void canvasElementAggregateTest() {
    canvasTestLogger.info("Starting CanvasElementAggregate tests...");

    CanvasElementAggregate canvasTest = new CanvasElementAggregate();
    canvasTestLogger.info("Successfully created test object.");

    CanvasElementAggregate aggregateElementTest = new CanvasElementAggregate();
    CanvasLayer layerElementTest1 = new CanvasLayer();
    CanvasLayer layerElementTest2 = new CanvasLayer();
    CanvasLayer layerElementTest3 = new CanvasLayer();
    canvasTest.addItem(aggregateElementTest);
    canvasTest.addItem(layerElementTest1);
    canvasTest.addItem(layerElementTest2);
    canvasTest.addItem(layerElementTest3);

    assertEquals(aggregateElementTest, canvasTest.getItem(0));
    assertEquals(layerElementTest1, canvasTest.getItem(1));
    assertEquals(layerElementTest2, canvasTest.getItem(2));
    assertEquals(layerElementTest3, canvasTest.getItem(3));

    canvasTestLogger
        .info("Successfully added both CanvasLayer and CanvasElementAggregate objects.");

    Throwable exception =
        assertThrows(IndexOutOfBoundsException.class, () -> canvasTest.getItem(4));
    assertEquals(
        "Index: 4, Size: 4",
        exception.getMessage());
    canvasTestLogger.info("Successfully provoked exception while exceeding index limits.");

    canvasTest.deleteItem(3);
    assertFalse(canvasTest.deleteItem(3));
    canvasTest.deleteItem(layerElementTest2);
    assertFalse(canvasTest.deleteItem(layerElementTest2));
    assertEquals(aggregateElementTest, canvasTest.getItem(0));
    assertEquals(layerElementTest1, canvasTest.getItem(1));
    exception =
        assertThrows(IndexOutOfBoundsException.class, () -> canvasTest.getItem(2));
    assertEquals(
        "Index: 2, Size: 2",
        exception.getMessage());

    canvasTestLogger
        .info("Successfully invoked both deleteItem() methods and passed size constraint checks.");
    canvasTestLogger.info("Starting iterator tests...");

    CanvasElementAggregate level2Agg = new CanvasElementAggregate();
    CanvasLayer level2Layer = new CanvasLayer();
    CanvasLayer level3Layer = new CanvasLayer();

    canvasTest.getItem(0).addItem(level2Agg);
    canvasTest.getItem(0).addItem(level2Layer);
    canvasTest.getItem(0).getItem(0).addItem(level3Layer);
    canvasTestLogger.info("Successfully set up tree structure for iterator tests.");

    Iterator<CanvasElement> testIterator = canvasTest.createIterator();
    assertEquals(testIterator, canvasTest.createIterator());
    canvasTestLogger.info(
        "Successfully created iterator, and received correct object upon second method call.");


  }

}
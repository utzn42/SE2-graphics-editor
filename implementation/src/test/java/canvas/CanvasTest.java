package canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import facilitators.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shapes.Circle;
import shapes.Line;
import shapes.Polygon;
import shapes.ShapeFactory;
import shapes.ShapeType;
import shapes.TransformableShapeFactory;
import shapes.transform.Transformation;
import shapes.transform.atomic.TranslationTransformation;

class CanvasTest {

  private static final Logger canvasTestLogger = LoggerFactory
      .getLogger(CanvasTest.class);

  @AfterAll
  static void testSuccess() {
    canvasTestLogger.info("CanvasTest has been passed successfully!" + '\n');
  }

  @Test
  void canvasLayerTest() {
    canvasTestLogger.info("Starting tests for CanvasLayer...");
    CanvasLayer testLayer1 = new CanvasLayer(0);
    Circle testCircle = new Circle();
    CanvasLayer testLayer2 = new CanvasLayer(1, testCircle);
    canvasTestLogger.info("Successfully created test objects.");

    assertEquals(0, testLayer1.getId());
    assertEquals(null, testLayer1.getShape());

    assertEquals(1, testLayer2.getId());
    assertEquals(testCircle, testLayer2.getShape());

    canvasTestLogger.info("Successfully read expected parameters from test objects.");

    Polygon testPolygon = new Polygon();
    testLayer2.setShape(testPolygon);

    assertEquals(testPolygon, testLayer2.getShape());

    assertEquals(
        "<polygon fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" points=\"0.0,0.0 100.0,100.0 0.0,100.0\"></polygon>",
        testLayer2.getHTML());

    canvasTestLogger.info("Successfully changed shape and read corresponding HTML output.");

    canvasTestLogger.info("Attempting to apply transformations...");
    Transformation translate = new Transformation();
    translate.addTransformation(new TranslationTransformation(100, 0));
    testLayer2.transform(translate);
    assertEquals(
        "<polygon fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" points=\"100.0,0.0 200.0,100.0 100.0,100.0\"></polygon>",
        testLayer2.getHTML());
    canvasTestLogger.info("Success applying translation to non-transformable shape.");

    ShapeFactory testFac = new TransformableShapeFactory();

    CanvasLayer testLayer3 = testFac.createShape(666, ShapeType.POLYGON);
    testLayer3.transform(translate);
    assertEquals(
        "<polygon fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" points=\"100.0,0.0 200.0,100.0 100.0,100.0\"></polygon>",
        testLayer2.getHTML());
    canvasTestLogger.info("Success applying translation to transformable shape.");
    canvasTestLogger.info("canvaslayerTest passed successfully." + '\n');
  }

  @Test
  void canvasElementAggregateTest() {
    canvasTestLogger.info("Starting tests for CanvasElementAggregate...");
    long testID = 0;
    CanvasElementAggregate testCEA = new CanvasElementAggregate(testID++);
    assertEquals(0, testCEA.getId());
    canvasTestLogger.info("Successfully created test object.");

    CanvasLayer testLayer1 = new CanvasLayer(testID++);
    CanvasLayer testLayer2 = new CanvasLayer(testID++);

    testCEA.addItem(testLayer1);
    testCEA.addItem(testLayer2, 0);
    assertEquals(testLayer2, testCEA.getItem(0));
    assertEquals(testLayer1, testCEA.getItem(1));
    canvasTestLogger.info("addItem() functions inserted objects at correct positions.");

    CanvasElementAggregate testInsertCEA = new CanvasElementAggregate(testID++);
    CanvasLayer testLayer3 = new CanvasLayer(testID++);
    CanvasLayer testLayer4 = new CanvasLayer(testID++);
    testCEA.addItem(testLayer3);
    testCEA.addItem(testLayer4);
    testCEA.addAll(testInsertCEA);
    assertEquals(testLayer3, testCEA.getItem(2));
    assertEquals(testLayer4, testCEA.getItem(3));
    canvasTestLogger.info("Successfully called addAll() function.");

    CanvasLayer newTestLayer4 = new CanvasLayer(testID++);
    testCEA.setItem(3, newTestLayer4);
    assertEquals(newTestLayer4, testCEA.getItem(3));
    canvasTestLogger.info("Successfully replaced an item at desired index.");

    testCEA.deleteItem(3);
    testCEA.deleteItem(testLayer3);
    testCEA.deleteItem(3);
    testCEA.deleteItem(testLayer3);
    assertEquals(2, testCEA.size());
    assertEquals(testLayer2, testCEA.getItem(0));
    assertEquals(testLayer1, testCEA.getItem(1));
    canvasTestLogger.info("Successfully called deleteItem() methods and retrieved correct size.");

    Iterator<CanvasElement> testIter = testCEA.createIterator();
    assertEquals(testIter.next(), testCEA.createIterator().next());
    canvasTestLogger.info("Successfully called createIterator.");

    testCEA.getItem(0).setShape(new Circle());
    testCEA.getItem(1).setShape(new Polygon());
    testCEA.getItem(1).setVisible(false);
    assertEquals(
        "<circle fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"50.0\" cy=\"50.0\" r=\"50.0\"></circle>",
        testCEA.getHTML());
    canvasTestLogger.info("Successfully got HTML output.");

    Throwable exception =
        assertThrows(UnsupportedOperationException.class, () -> testCEA.setShape(new Line()));
    assertEquals(
        "Can't set shape in a CanvasElementAggregate!",
        exception.getMessage());
    canvasTestLogger
        .info("Successfully provoked an exception while attempting to set shape for aggregate.");

    Transformation translate = new Transformation();
    translate.addTransformation(new TranslationTransformation(100, 0));
    testCEA.transform(translate);
    assertEquals(
        "<circle fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"150.0\" cy=\"50.0\" r=\"50.0\"></circle>",
        testCEA.getHTML());
    canvasTestLogger.info("Successfully transformed shapes within test object.");
    canvasTestLogger.info("canvasElementAggregate tests have been passed successfully!" + '\n');
  }

  @Test
  void remainingCanvasElementTest() {
    canvasTestLogger.info("Testing remaining CanvasElement methods...");
    CanvasElement testObjects = new CanvasLayer(0, false);
    testObjects.setVisible(true);
    canvasTestLogger.info("Success!" + '\n');
  }

  @Test
  void canvasTest() {
    canvasTestLogger.info("Running Canvas tests...");
    Canvas testCanvas = new Canvas();
    assertEquals(200, testCanvas.getWidth());
    assertEquals(200, testCanvas.getHeight());
    testCanvas.setWidth(500);
    testCanvas.setHeight(500);
    assertEquals(500, testCanvas.getWidth());
    assertEquals(500, testCanvas.getHeight());
    List elements = new ArrayList<>();
    assertEquals(elements, testCanvas.getCanvasElements());
    assertEquals(true, testCanvas.doesAllowTransformAttribute());
    canvasTestLogger.info("Test object passed basic tests.");
  }
}
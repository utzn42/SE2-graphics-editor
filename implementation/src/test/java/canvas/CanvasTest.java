package canvas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import shapes.NonTransformableShapeFactory;
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

    canvasTestLogger.info("Testing transformations and clearing them...");
    CanvasLayer testLayer5 = new TransformableShapeFactory()
        .createShape(testID++, ShapeType.CIRCLE);
    testLayer5.clearTransformations();
    assertEquals(
        "<circle fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"50.0\" cy=\"50.0\" r=\"50.0\"></circle>",
        testLayer5.getHTML());
    testCEA.clearTransformations();
    assertEquals(
        "<circle fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"150.0\" cy=\"50.0\" r=\"50.0\"></circle>",
        testCEA.getHTML());
    canvasTestLogger.info("Success!");

    exception =
        assertThrows(UnsupportedOperationException.class,
            () -> testCEA.getItem(0).addItem(new CanvasLayer(666)));
    assertEquals(
        "Cant add an item to a Canvas!",
        exception.getMessage());

  }

  @Test
  void remainingCanvasElementTest() {
    canvasTestLogger.info("Testing remaining CanvasElement methods...");
    CanvasElement testObjects = new CanvasLayer(0, false);
    testObjects.setVisible(true);
    canvasTestLogger.info("Success!" + '\n');
    Throwable exception =
        assertThrows(UnsupportedOperationException.class,
            () -> testObjects.asList());
    assertEquals(
        "Can't view List of a non Aggregate object!",
        exception.getMessage());
  }

  @Test
  void canvasTest() {
    int testID = 0;
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

    testCanvas.setAllowTransformAttribute(false);
    assertFalse(testCanvas.doesAllowTransformAttribute());
    testCanvas.setAllowTransformAttribute(true);
    assertTrue(testCanvas.doesAllowTransformAttribute());
    ShapeFactory transFac = testCanvas.getShapeFactory();
    canvasTestLogger.info("transformAttribute getters and setters successfully called.");
    canvasTestLogger.info("Successfully read internal shapeFactory: " + transFac.toString());

    CanvasLayer testLayer1 = new TransformableShapeFactory()
        .createShape(testID++, ShapeType.CIRCLE);
    testCanvas.addElement(testLayer1);
    assertEquals(testLayer1, testCanvas.findElementByID(0));
    assertEquals(testLayer1, testCanvas.getCanvasElements().get(0));
    canvasTestLogger.info("Successfully added and read a CanvasLayer element.");

    CanvasElementAggregate testagg1 = new CanvasElementAggregate(testID++);
    CanvasLayer testLayer2 = new TransformableShapeFactory().createShape(testID++, ShapeType.STAR);
    CanvasLayer testlayer3 = new NonTransformableShapeFactory()
        .createShape(testID++, ShapeType.POLYGON);
    testagg1.addItem(testLayer2);
    testagg1.addItem(testlayer3);
    testCanvas.addElement(testagg1);
    assertEquals(testLayer2, testCanvas.findElementByID(2));
    assertNull(testCanvas.findElementByID(4));
    assertEquals(testagg1, testCanvas.getCanvasElements().get(1));
    canvasTestLogger.info("Successfully added and read objects.");

    testCanvas.setAllowTransformAttribute(false);
    assertFalse(testCanvas.doesAllowTransformAttribute());
    testCanvas.setAllowTransformAttribute(true);
    assertTrue(testCanvas.doesAllowTransformAttribute());
    canvasTestLogger.info("Successfully transformed canvas elements.");

    assertEquals(
        "<svg width=\"500.0\" height=\"500.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"><circle fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"50.0\" cy=\"50.0\" r=\"50.0\"></circle><polygon fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"50.0\" cy=\"50.0\" r=\"50.0\" points=\"79.3892593383789,90.45085144042969 73.77641296386719,57.725425720214844 97.55282592773438,34.54914855957031 64.69463348388672,29.774574279785156 50.0,0.0 35.30537033081055,29.774574279785156 2.447174072265625,34.54914855957031 26.223587036132812,57.725425720214844 20.610736846923828,90.45085144042969 50.0,75.0\"></polygon><polygon fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" points=\"0.0,0.0 100.0,100.0 0.0,100.0\"></polygon></svg>",
        testCanvas.getHTML());
    canvasTestLogger.info("Successfully read HTML output.");

    testCanvas.addElement(new CanvasLayer(testID++), 3);
    Throwable exception =
        assertThrows(IllegalArgumentException.class,
            () -> testCanvas.addElement(new CanvasLayer(9), 10));
    assertEquals(
        "Cannot add before element: 10; Element does not exist!",
        exception.getMessage());
    testCanvas.addElement(new CanvasLayer(testID++), 0);
    canvasTestLogger.info("Successfully inserted elements.");

    CanvasLayer testLayer4 = new CanvasLayer(testID++);
    testCanvas.addElementIntoElement(testLayer4, 1);
    assertEquals(testLayer4, testCanvas.getCanvasElements().get(2).asList().get(3));
    testCanvas.addShape(ShapeType.TEXT);
    testCanvas.addShape(ShapeType.TEXT, 6);
    testCanvas.addShapeIntoElement(ShapeType.TEXT, 1);
    testCanvas.addGroupLayer();
    testCanvas.addGroupLayer(6);
    testCanvas.addGroupLayerIntoElement(1);
    testCanvas.removeElementByID(1);
    testCanvas.removeElementByID(2);
    testCanvas.removeElementByID(3);
    testCanvas.removeElementByID(4);
    testCanvas.removeElementByID(5);
    testCanvas.removeElementByID(6);
    testCanvas.removeElementByID(7);
    testCanvas.removeElementByID(8);
    testCanvas.removeElementByID(9);
    testCanvas.removeElementByID(10);
    canvasTestLogger.info("Additions and removals returned expected results.");

    Transformation translate = new Transformation();
    translate.addTransformation(new TranslationTransformation(100, 100));
    testCanvas.transformElementByID(0, translate);
    testCanvas.clearTransformationsOnElement(0);
    assertEquals(2, testCanvas.getCanvasElements().size());
    canvasTestLogger.info("Done setting and clearing transformations.");

    CanvasLayer testLayer5 = new CanvasLayer(testID++);
    testCanvas.updateElementByID(0, testLayer5);
    assertEquals(testLayer5, testCanvas.getCanvasElements().get(0));
    CanvasElementAggregate testAgg3 = new CanvasElementAggregate(testID++);
    testAgg3.addItem(new CanvasLayer(testID++));
    testCanvas.addElement(testAgg3);
    CanvasElementAggregate testAgg2 = new CanvasElementAggregate(testID++);
    CanvasLayer testLayer6 = new TransformableShapeFactory()
        .createShape(testID++, ShapeType.ELLIPSE);
    testAgg2.addItem(testLayer6);
    testCanvas.addElement(testAgg2, 7);
    testCanvas.removeElementByID(11);
    assertNull(testCanvas.findElementByID(11));

  }
}
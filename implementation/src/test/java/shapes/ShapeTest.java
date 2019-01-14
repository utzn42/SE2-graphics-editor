package shapes;

import canvas.CanvasElementAggregate;
import facilitators.Coordinate;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shapes.transform.atomic.ScaleTransformation;
import shapes.transform.atomic.TranslationTransformation;
import shapes.transform.atomic.UniformScaleTransformation;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShapeTest {
    private static final Logger shapeTestLogger = LoggerFactory
            .getLogger(ShapeTest.class);
    long testID = 0;
    CanvasElementAggregate testAgg = new CanvasElementAggregate(testID++);

    @Test
    void factoryTest() {
        shapeTestLogger.info("Starting factory test...");
        NonTransformableShapeFactory nonTransTestFac = new NonTransformableShapeFactory();
        testAgg.addItem(nonTransTestFac.createShape(testID++, ShapeType.CIRCLE));
        testAgg.addItem(nonTransTestFac.createShape(testID++, ShapeType.ELLIPSE));
        testAgg.addItem(nonTransTestFac.createShape(testID++, ShapeType.LINE));
        testAgg.addItem(nonTransTestFac.createShape(testID++, ShapeType.POLYGON));
        testAgg.addItem(nonTransTestFac.createShape(testID++, ShapeType.REGULAR_POLYGON));
        testAgg.addItem(nonTransTestFac.createShape(testID++, ShapeType.STAR));
        testAgg.addItem(nonTransTestFac.createShape(testID++, ShapeType.TEXT));

        TransformableShapeFactory transTestFac = new TransformableShapeFactory();
        testAgg.addItem(transTestFac.createShape(testID++, ShapeType.CIRCLE));
        testAgg.addItem(transTestFac.createShape(testID++, ShapeType.ELLIPSE));
        testAgg.addItem(transTestFac.createShape(testID++, ShapeType.LINE));
        testAgg.addItem(transTestFac.createShape(testID++, ShapeType.POLYGON));
        testAgg.addItem(transTestFac.createShape(testID++, ShapeType.REGULAR_POLYGON));
        testAgg.addItem(transTestFac.createShape(testID++, ShapeType.STAR));
        testAgg.addItem(transTestFac.createShape(testID++, ShapeType.TEXT));

        assertEquals(14, testAgg.size());
        shapeTestLogger.info("Success!");
    }

    @Test
    void circleTest() {
        shapeTestLogger.info("Starting circle tests...");
        Circle testCircle = new Circle(new Coordinate(50, 50), 50);
        testCircle.setCenter(testCircle.getCenter());
        assertEquals(50, testCircle.getCenter().getX());
        assertEquals(50, testCircle.getCenter().getX());

        testCircle.setRadius(testCircle.getRadius());
        assertEquals(50, testCircle.getRadius());

        assertEquals("<circle fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"50.0\" cy=\"50.0\" r=\"50.0\"></circle>", testCircle.getHTML());

        TranslationTransformation translate = new TranslationTransformation(new Coordinate(50, 50));
        testCircle.translate(translate);

        UniformScaleTransformation scale = new UniformScaleTransformation(1.5);
        testCircle.uniformScale(scale);

        assertEquals("<circle fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.5\" opacity=\"1.0\" cx=\"150.0\" cy=\"150.0\" r=\"75.0\"></circle>", testCircle.getHTML());
        assertEquals(ShapeType.CIRCLE, testCircle.getShapeType());
        shapeTestLogger.info("Circle tests complete!");
    }

    @Test
    void ellipseTest() {
        shapeTestLogger.info("Starting ellipse tests...");
        Ellipse testEllipse = new Ellipse(new Coordinate(50, 50), 100, 50);
        testEllipse.setCenter(testEllipse.getCenter());
        assertEquals(50, testEllipse.getCenter().getX());
        assertEquals(50, testEllipse.getCenter().getX());

        testEllipse.setRadiusX(testEllipse.getRadiusX());
        assertEquals(100, testEllipse.getRadiusX());

        testEllipse.setRadiusY(testEllipse.getRadiusY());
        assertEquals(50, testEllipse.getRadiusY());

        assertEquals("<ellipse fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"50.0\" cy=\"50.0\" rx=\"100.0\" ry=\"50.0\"></ellipse>", testEllipse.getHTML());

        TranslationTransformation translate = new TranslationTransformation(new Coordinate(50, 50));
        testEllipse.translate(translate);

        UniformScaleTransformation scale = new UniformScaleTransformation(1.5);
        testEllipse.uniformScale(scale);

        ScaleTransformation scale1 = new ScaleTransformation(1, 2);
        testEllipse.scale(scale1);

        assertEquals("<ellipse fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.5\" opacity=\"1.0\" cx=\"150.0\" cy=\"300.0\" rx=\"150.0\" ry=\"150.0\"></ellipse>", testEllipse.getHTML());
        assertEquals(ShapeType.ELLIPSE, testEllipse.getShapeType());
        shapeTestLogger.info("Ellipse tests complete!");
    }
}
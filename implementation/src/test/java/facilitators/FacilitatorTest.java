package facilitators;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FacilitatorTest {

  private static final Logger facilitatorTestLogger = LoggerFactory
      .getLogger(FacilitatorTest.class);

  @AfterAll
  static void successMessage() {
    facilitatorTestLogger.info("FacilitatorTest passed successfully!");
  }

  @Test
  void colourTest() {
    facilitatorTestLogger.info("Starting colourTest()...");
    byte one = 0;
    byte two = 0;
    byte three = 0;
    RGBColour rgbcolour = new RGBColour(one, two, three);
    float opacity = (float) 1.0;
    Colour testColour = new Colour("#000000", opacity);
    assertEquals(rgbcolour.toString(), testColour.getRgbColour().toString());
    testColour = new Colour(rgbcolour, opacity);
    facilitatorTestLogger.info("Successfully created test object.");

    assertEquals(rgbcolour, testColour.getRgbColour());
    assertEquals(opacity, testColour.getOpacity());
    testColour.setOpacity(opacity = (float) 0.5);
    testColour.setRgbColour(
        rgbcolour = new RGBColour(one = (byte) 255, two = (byte) 255, three = (byte) 255));
    assertEquals(rgbcolour, testColour.getRgbColour());
    assertEquals(opacity, testColour.getOpacity());
    assertEquals((byte) 255, rgbcolour.getRed());
    assertEquals((byte) 255, rgbcolour.getGreen());
    assertEquals((byte) 255, rgbcolour.getBlue());
    facilitatorTestLogger.info("Successfully called getters and setters.");
  }

  @Test
  void coordinateTest() {
    facilitatorTestLogger.info("Starting coordinate test...");
    Coordinate testCoord = new Coordinate(0, 0);
    assertEquals(0, testCoord.getX());
    assertEquals(0, testCoord.getY());
    facilitatorTestLogger.info("Success!");
  }

  @Test
  void coordinateMathTest() {
    facilitatorTestLogger.info("Starting coordinate math tests...");
    Coordinate testCoord = new Coordinate(100, 100);
    Coordinate translator = new Coordinate(-100, -100);
    assertEquals(new Coordinate(0, 0).getX(),
        CoordinateMath.translateByCoordinate(testCoord, translator).getX());
    assertEquals(new Coordinate(0, 0).getY(),
        CoordinateMath.translateByCoordinate(testCoord, translator).getY());
    facilitatorTestLogger.info("Translation successful!");

    Coordinate testCoord2 = new Coordinate(100, 0);
    testCoord2 = CoordinateMath
        .rotateAroundCoordinate(testCoord2, 45, new Coordinate(0, 0));
    assertTrue(abs((100 / Math.sqrt(2) - testCoord2.getX())) < 0.0000000001);
    assertTrue(abs((100 / Math.sqrt(2) - testCoord2.getY())) < 0.0000000001);
    facilitatorTestLogger.info("Rotation successful!");

    testCoord2 = CoordinateMath
        .scaleVector(testCoord2, new Coordinate(sqrt(2), sqrt(2)), new Coordinate(0, 0));
    assertTrue(abs((100 - testCoord2.getX()))
        < 0.0000000001);
    assertTrue(abs((100 - testCoord2.getY()))
        < 0.0000000001);
    facilitatorTestLogger.info("Scaling successful!");

    testCoord2 = CoordinateMath.skewX(testCoord2, 90, new Coordinate(0, 0));
    testCoord2 = CoordinateMath.skewY(testCoord2, 90, new Coordinate(0, 0));

    testCoord2 = CoordinateMath.skewX(testCoord2, 45, new Coordinate(0, 0));
    testCoord2 = CoordinateMath.skewY(testCoord2, 45, new Coordinate(0, 0));
    assertTrue(abs((200 - testCoord2.getX()))
        < 0.0000000001);
    assertTrue(abs((300 - testCoord2.getY()))
        < 0.0000000001);
    facilitatorTestLogger.info("Skewing successful!");

  }

  @Test
  void hasherTest() {
    facilitatorTestLogger.info("Starting Hasher test...");
    assertEquals("af5570", new Hasher(0).getHash());
    assertEquals("7dea36", new Hasher(0, "MD5").getHash());
    facilitatorTestLogger.info("Done testing Hasher!");
  }
}
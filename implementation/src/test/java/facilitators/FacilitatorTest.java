package facilitators;

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
    facilitatorTestLogger.info("Successfully called getters and setters.");
  }

  @Test
  void coordinateTest() {

  }

  @Test
  void coordinateMathTest() {

  }

  @Test
  void hasherTest() {

  }

  @Test
  void rgbColourTest() {

  }

}
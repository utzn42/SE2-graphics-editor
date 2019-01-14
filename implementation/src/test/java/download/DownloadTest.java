package download;

import canvas.Canvas;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DownloadTest {

    private static final Logger downloadTestLogger = LoggerFactory
            .getLogger(DownloadTest.class);

    @Test
    void canvasToJPGConverterTest() {
        downloadTestLogger.info("Starting JPG download tests...");
        CanvasToJPGConverter c2jpg = new CanvasToJPGConverter();
        try {
            c2jpg.convert(new Canvas(), "testID");
            downloadTestLogger.info("Success!");
        } catch (Exception e) {
            fail("Couldn't convert to JPG file! " + e.getMessage());
        }
    }

    @Test
    void canvasToPNGConverterTest() {
        downloadTestLogger.info("Starting PNG download tests...");
        CanvasToPNGConverter c2png = new CanvasToPNGConverter();
        try {
            c2png.convert(new Canvas(), "testID");
            downloadTestLogger.info("Success!");
        } catch (Exception e) {
            fail("Couldn't convert to PNG file! " + e.getMessage());
        }
    }

    @Test
    void canvasToSVGConverterTest() {
        downloadTestLogger.info("Starting SVG download tests...");
        CanvasToSVGConverter c2svg = new CanvasToSVGConverter();
        try {
            c2svg.convert(new Canvas(), "testID");
            downloadTestLogger.info("Success!");
        } catch (Exception e) {
            fail("Couldn't convert to SVG file! " + e.getMessage());
        }
    }

    @Test
    void downloadFormatTest() {
        assertEquals("JPG", DownloadFormat.JPG.toString());
        assertEquals("PNG", DownloadFormat.PNG.toString());
        assertEquals("SVG", DownloadFormat.SVG.toString());
    }

}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import facilitators.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

class CanvasElementAggregateTest {

  Iterator<CanvasElement> iterator = null;
  private List<CanvasElement> elements;

  @Test
  void addItem() {
    CanvasElementAggregate testItem = new CanvasElementAggregate(0);
    CanvasLayer simpleItem = new CanvasLayer(0);
    CanvasElementAggregate complexItem = new CanvasElementAggregate(0);

    testItem.addItem(simpleItem);
    assertEquals(simpleItem, testItem.getItem(0));

    testItem.addItem(complexItem);
    assertEquals(complexItem, testItem.getItem(1));


  }

  @Test
  void getItem() {
  }

  @Test
  void deleteItem() {
  }

  @Test
  void deleteItem1() {
  }

  @Test
  void createIterator() {
  }

  @Test
  void transform() {
    fail("Not yet implemented");
  }
}
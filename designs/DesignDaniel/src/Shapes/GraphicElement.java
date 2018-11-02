package Shapes;

import Base.Point;

import java.util.List;

public interface GraphicElement {

    void move(Point point);
    String getHTML();
    List<String> getModifiers();
    void modify(String modifier);

}

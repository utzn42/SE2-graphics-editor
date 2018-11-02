package Shapes;

import Base.Point;

import java.util.List;

public abstract class RegularPolygon extends RadialElement {

    private int numberOfEdges;

    public String getHTML() {
        return null;
    }

    public List<String> getModifiers() {
        return null;
    }

    public void modify(String modifier) {

    }

    RegularPolygon(List<String> regularPolygonList){

    }

    @Override
    public void move(Point point) {
        super.move(point);
    }
}

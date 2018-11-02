package Shapes;

import Base.Point;

import java.util.List;

public class Star extends RegularPolygon {

    private double innerRadius;

    public Star(List<String> starList) {
        super(starList);
    }

    @Override
    public void move(Point point) {
        super.move(point);
    }

    @Override
    public void modify(String modifier) {
        super.modify(modifier);
    }

    @Override
    public String getHTML() {
        return super.getHTML();
    }

    @Override
    public List<String> getModifiers() {
        return super.getModifiers();
    }


}

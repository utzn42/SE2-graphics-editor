package Base;

import java.util.List;

public class Canvas {

    private List<Layer> layers;
    private Point size;

    public Canvas(){

    }

    public List<Layer> getLayers() {
        return layers;
    }

    public Layer addShape(String shapeType, List<String> attributes){
        return null;
    }

    public void removeLayer(int nr){

    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point point){
        this.size=point;
    }

    public String getHTML(){
        return null;
    }

}

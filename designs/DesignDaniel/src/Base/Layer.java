package Base;

import Shapes.GraphicElement;

import java.util.List;

public class Layer {

    private GraphicElement element;
    private boolean layerVisible;

    public Layer(String shapeType, List<String> attributes){

    }

    public GraphicElement getElement() {
        return element;
    }

    public String getHTML(){
        return null;
    }

    public void toggleVisibility(){

    }

}

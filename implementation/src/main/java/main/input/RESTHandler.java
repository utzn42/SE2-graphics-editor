package main.input;

import canvas.Canvas;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import shapes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RESTHandler {

    private static Logger restLogger = LoggerFactory.getLogger(RESTHandler.class);
    private Map<String, Canvas> projects = new HashMap<>();
    private long seedCounter = 0;

    @RequestMapping(value = "/test")
    public List<Shape> test() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle());
        shapes.add(new Ellipse());
        shapes.add(new Line());
        shapes.add(new Polygon());
        shapes.add(new RegularPolygon());
        shapes.add(new Star());
        shapes.add(new Text());
        return shapes;
    }

    @RequestMapping(value = "/create")
    public JSONObject createProject() {
        return null;
    }

    @RequestMapping(value = "/addShape/{projectID}", method = RequestMethod.GET)
    public JSONObject addShape(@PathVariable String projectID, @RequestBody JSONObject add_json) {

        System.out.println(add_json.toString());
        return null;
    }

    @RequestMapping(value = "/editShape/{projectID}", method = RequestMethod.POST)
    public JSONObject editShape(@PathVariable String projectID, @RequestBody JSONObject edit_json) {
        return null;
    }

    @RequestMapping(value = "/transformShape/{projectID}", method = RequestMethod.POST)
    public JSONObject transformShape(@PathVariable String projectID, @RequestBody JSONObject transform_json) {
        return null;
    }

    @RequestMapping(value = "/deleteShape/{projectID}")
    public JSONObject deleteShape(@PathVariable String projectID, @RequestBody JSONObject transform_json) {
        return null;
    }
}

//TODO: Utz: Generate SHA Hash from seed
//TODO: Jackson annotation reference to parse JSON to and from POJO: https://www.baeldung.com/jackson-annotations

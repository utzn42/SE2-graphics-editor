package main.input;

import canvas.Canvas;
import canvas.Layer;
import facilitators.Hasher;
import messages.ServerResponse;
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
    private static long seedCounter = 0;

    @RequestMapping(value = "/test")
    public ServerResponse test() {
        List<Layer> layers = new ArrayList<>();
        List<Shape> shapes1 = new ArrayList<>();
        List<Shape> shapes2 = new ArrayList<>();
        List<Shape> shapes3 = new ArrayList<>();
        shapes1.add(new Circle());
        shapes2.add(new Ellipse());
        shapes3.add(new Line());
        shapes1.add(new Polygon());
        shapes2.add(new RegularPolygon());
        shapes3.add(new Star());
        shapes1.add(new Text());
        layers.add(new Layer(shapes1));
        layers.add(new Layer(shapes2));
        layers.add(new Layer(shapes3));
        Canvas canvas = new Canvas(layers);
        ServerResponse response = new ServerResponse("TestID");
        response.setCanvas(canvas);
        return response;
    }

    @RequestMapping(value = "/create")
    public ServerResponse createProject() {
        Hasher hash = new Hasher(++seedCounter);
        String id = hash.getHash();
        Canvas canvas = new Canvas();
        projects.put(id, canvas);
        ServerResponse response = new ServerResponse(id);
        response.setCanvas(canvas);
        return response;
    }

    @RequestMapping(value = "/addShape/{projectID}", method = RequestMethod.POST)
    public ServerResponse addShape(@PathVariable String projectID, @RequestBody String request) {
        ServerResponse response = new ServerResponse(projectID);
        if (!projects.containsKey(projectID)) {
            response.addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
            return response;
        }
        Canvas canvas = projects.get(projectID);

        // TODO: Add Shape

        response.setCanvas(canvas);
        return response;
    }

    @RequestMapping(value = "/editShape/{projectID}", method = RequestMethod.POST)
    public ServerResponse editShape(@PathVariable String projectID, @RequestBody String request) {
        ServerResponse response = new ServerResponse(projectID);
        if (!projects.containsKey(projectID)) {
            response.addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
            return response;
        }
        Canvas canvas = projects.get(projectID);

        // TODO: Edit Shape

        response.setCanvas(canvas);
        return response;
    }

    @RequestMapping(value = "/transformShape/{projectID}", method = RequestMethod.POST)
    public ServerResponse transformShape(@PathVariable String projectID, @RequestBody String request) {
        ServerResponse response = new ServerResponse(projectID);
        if (!projects.containsKey(projectID)) {
            response.addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
            return response;
        }
        Canvas canvas = projects.get(projectID);

        // TODO: Transform Shape

        response.setCanvas(canvas);
        return response;
    }

    @RequestMapping(value = "/deleteShape/{projectID}")
    public ServerResponse deleteShape(@PathVariable String projectID, @RequestBody String request) {
        ServerResponse response = new ServerResponse(projectID);
        if (!projects.containsKey(projectID)) {
            response.addError(new IllegalArgumentException("Project ID " + projectID + " does not exist!"));
            return response;
        }
        Canvas canvas = projects.get(projectID);

        // TODO: Delete Shape

        response.setCanvas(canvas);
        return response;
    }
}

//TODO: remove org.json.JSONObject dependency from project (no longer needed)
//TODO: Jackson annotation reference to parse JSON to and from POJO: https://www.baeldung.com/jackson-annotations

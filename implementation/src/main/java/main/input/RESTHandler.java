package main.input;

import canvas.Canvas;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RESTHandler {

    private static Logger restLogger = LoggerFactory.getLogger(RESTHandler.class);
    private Map<String, Canvas> projects = new HashMap<>();
    private long seedCounter = 0;

    @RequestMapping(value = "/create/")
    public String createProject() {
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
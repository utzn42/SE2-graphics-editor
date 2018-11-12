package main.input;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTHandler {

    private static Logger restLogger = LoggerFactory.getLogger(RESTHandler.class);

    @RequestMapping(value = "/addShape/", method = RequestMethod.GET)
    public JSONObject addShape(@RequestBody JSONObject add_json) {

        System.out.println(add_json.toString());
        return null;
    }

    @RequestMapping(value = "/editShape/", method = RequestMethod.POST)
    public JSONObject editShape(@RequestBody JSONObject edit_json) {
        return null;
    }

    @RequestMapping(value = "/transformShape/", method = RequestMethod.POST)
    public JSONObject transformShape(@RequestBody JSONObject transform_json) {
        return null;
    }

    @RequestMapping(value = "deleteShape")
    public JSONObject deleteShape(@RequestBody JSONObject transform_json) {
        return null;
    }
}

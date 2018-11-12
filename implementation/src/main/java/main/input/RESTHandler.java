package main.input;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shapes.Circle;

@RestController
public class RESTHandler {

    private static Logger restLogger = LoggerFactory.getLogger(RESTHandler.class);

    @RequestMapping(value = "/addShape/", method = RequestMethod.GET)
    public String addShape(@RequestBody JSONObject add_json) {

        System.out.println(add_json.toString());
        return new Circle().getHTML();
    }

    @RequestMapping(value = "/editShape/", method = RequestMethod.POST)
    public String editShape(@RequestBody JSONObject edit_json) {
        return "Under construction...";
    }

    @RequestMapping(value = "/transformShape/", method = RequestMethod.POST)
    public String transformShape(@RequestBody JSONObject transform_json) {
        return "Under construction...";
    }

    @RequestMapping(value = "deleteShape")
    public String deleteShape() {
        return "Under construction...";
    }
}

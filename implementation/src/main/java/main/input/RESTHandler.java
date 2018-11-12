package main.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTHandler {

    private static Logger restLogger = LoggerFactory.getLogger(RESTHandler.class);

    @RequestMapping("/addShape/")
    public String addShape() {
        return "Under construction...";
    }

    @RequestMapping("/editShape/")
    public String editShape() {
        return "Under construction...";
    }

    @RequestMapping("/moveShape/")
    public String moveShape() {
        return "Under construction...";
    }

    @RequestMapping("/deleteShape/")
    public String deleteShape() {
        return "Under construction...";
    }
}

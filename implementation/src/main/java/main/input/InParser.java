package main.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InParser {

    static Logger logger = LoggerFactory.getLogger(InParser.class);

    @RequestMapping("/parseJS/")
    public String parseJavaScript() {
        return "Under construction...";
    }
}

package ch.chiodoni.web.crossorigin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JSONController {

    public static final Logger LOGGER = LoggerFactory.getLogger(JSONController.class);

    public static final String PAYLOAD = "{\"payload\": \"hello\"}";

    
    @RequestMapping(value = "json/", method = RequestMethod.GET)
    @ResponseBody
    public String JSON(@RequestParam(required = false) String origin, HttpServletResponse response, HttpServletRequest request) {
        if (StringUtils.hasText(origin)) {
            LOGGER.info("Adding COSR header for {}", origin);
            
            //TODO: CORS: here we open it to every origin
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        }
        LOGGER.info("JSON called");
        response.setContentType("application/json");
        return PAYLOAD;
    }

    @RequestMapping(value = "jsonp/", method = RequestMethod.GET)
    @ResponseBody
    public String JSONP(@RequestParam String callback, HttpServletResponse response) {
        LOGGER.info("JSONP called, callback is {}", callback);
        response.setContentType("application/javascript");
        return callback + "(" + PAYLOAD + ")";
    }
}

package ch.chiodoni.web.crossorigin.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PostmessageController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CookieController.class);

    public static final String PAYLOAD = "{\"payload\": \"hello\"}";


    @RequestMapping(value = "postmessage/", method = RequestMethod.GET)
    @ResponseBody
    public String postmessage(@RequestParam(required = false) String origin, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Postmessage called");

        return "<script type='text/javascript'> window.parent.postMessage("+PAYLOAD+",'"+origin+"');</script>";
    }

}

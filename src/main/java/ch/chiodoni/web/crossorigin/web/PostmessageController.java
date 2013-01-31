package ch.chiodoni.web.crossorigin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Controller
public class PostmessageController {

    private static final String CONTENT_TYPE = "image/gif";

    public static final Logger LOGGER = LoggerFactory.getLogger(CookieController.class);

    public static final String PAYLOAD = "{\"payload\": \"hello\"}";

    private static final String RESOURCES_BLANK_GIF = "/resources/blank.gif";

    private byte[] buffer = new byte[1024];

    private int size = 0;

    @RequestMapping(value = "postmessage/", method = RequestMethod.GET)
    @ResponseBody
    public String postmessage(@RequestParam(required = false) String origin, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Postmessage called");
        return "<script type='text/javascript'> window.parent.postMessage("+PAYLOAD+",'"+origin+"');</script>";
    }

}

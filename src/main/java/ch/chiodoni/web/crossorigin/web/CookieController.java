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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Controller
public class CookieController {

    private static final String CONTENT_TYPE = "image/gif";

    public static final Logger LOGGER = LoggerFactory.getLogger(CookieController.class);

    public static final String PAYLOAD = "{\"payload\": \"hello\"}";

    private static final String RESOURCES_BLANK_GIF = "/resources/blank.gif";

    private byte[] buffer = new byte[1024];

    private int size = 0;

    public CookieController() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(RESOURCES_BLANK_GIF);
        size = in.read(buffer);
        in.close();
    }

    @RequestMapping(value = "cookie/", method = RequestMethod.GET)
    @ResponseBody
    public void cookie(@RequestParam(required = false) String domain, HttpServletResponse response) throws IOException {
        Cookie cookie = null;
        if (StringUtils.hasText(domain)) {
            LOGGER.info("Cookie called, domain is {}", domain);
            cookie = new Cookie("MYDATA", PAYLOAD);
            //TODO: set the domanin part of the origin
            cookie.setDomain("TODO");
        } else {
            cookie = new Cookie("DATA", PAYLOAD);
            LOGGER.info("Cookie called");
        }

        cookie.setPath("/");
        response.addCookie(cookie);
        response.setContentType(CONTENT_TYPE);

        OutputStream out = response.getOutputStream();
        out.write(buffer, 0, size);
        out.close();
    }

}

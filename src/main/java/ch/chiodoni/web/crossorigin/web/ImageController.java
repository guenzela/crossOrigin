package ch.chiodoni.web.crossorigin.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageController {
    private static final String CONTENT_TYPE = "image/gif";

    private static final String RESOURCES_BLANK_GIF = "/resources/blank.gif";

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    private byte[] buffer = new byte[1024];

    private int size = 0;
    
    public ImageController() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(RESOURCES_BLANK_GIF);
        size = in.read(buffer);
        in.close();
    }

    @RequestMapping(value = "/image/", method = RequestMethod.GET)
    public void image(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	
        LOGGER.info("Loading image");
        OutputStream out = response.getOutputStream();
        out.write(buffer, 0, size);
        out.close();
        
        response.setContentType(CONTENT_TYPE);
    }

    @RequestMapping(value = "/image/redirect/", method = RequestMethod.GET)
    public String imageredirect() {
        LOGGER.info("Redirecting...");
        return "redirect:../";
    }
}


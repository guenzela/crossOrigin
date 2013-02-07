package ch.chiodoni.web.crossorigin.web;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeadersController {

	@Autowired
	private HeadersHolder headersHolder;
	
	private ObjectMapper mapper = new ObjectMapper();
	
    @RequestMapping(value = "/headers/", method = RequestMethod.GET)
    @ResponseBody
    public HeadersHolder getHeaders() {
        return headersHolder;
    }
	
    @RequestMapping(value = "/headers/", method = RequestMethod.POST)
    @ResponseBody
    public HeadersHolder setHeaders(@RequestParam(required=true) String headers) throws JsonParseException, JsonMappingException, IOException {
    	//headers: JSON array of headerName and headerValue
    	List<Header> headerList = mapper.readValue(headers, new TypeReference<List<Header>>(){});
    	headersHolder.add(headerList);

        return headersHolder;
    }

    
    @RequestMapping(value = "/headers/reset/", method = RequestMethod.GET)
    @ResponseBody
    public HeadersHolder reset() {
    	headersHolder.reset();
        return headersHolder;
    }

}

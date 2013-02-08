package ch.chiodoni.web.crossorigin.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/invalidate-session")
	public String invalidateSession(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/server/";
	}
}

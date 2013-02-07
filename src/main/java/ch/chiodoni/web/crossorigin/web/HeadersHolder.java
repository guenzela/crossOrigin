package ch.chiodoni.web.crossorigin.web;

import java.util.ArrayList;
import java.util.List;

public class HeadersHolder {

	private List<Header> headers = new ArrayList<Header>(); 

	public HeadersHolder() {
	}
	
	public void reset() {
		headers.clear();
	}
	
	public void add(List<Header> headers) {
		this.headers.addAll(headers);
	}

	public List<Header> getHeaders() {
		return headers;
	}
	
}

package sample.webapp.web;

import javax.ws.rs.FormParam;

public class BlogPost {

	@FormParam("id")
	public Long id;

	@FormParam("title")
	public String title;

	@FormParam("content")
	public String content;

}

package org.smd.springBootRestAPI.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopicsController {

	@RequestMapping("/")
	
	public List<Topic> list() {
		
		return null;
	}
}

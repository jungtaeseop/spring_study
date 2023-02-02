package com.bootstrap.collapse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollapseController {

	@GetMapping("/")
	public String collapseView() {
		return "collapse/collapse";
	}
}

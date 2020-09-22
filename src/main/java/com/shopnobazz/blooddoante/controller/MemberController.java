package com.shopnobazz.blooddoante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/member")
public class MemberController {
@RequestMapping("/all")
public String viewmember(Model model) {
	
	return "member";
	
}

}

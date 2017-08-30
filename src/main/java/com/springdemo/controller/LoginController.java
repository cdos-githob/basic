package com.springdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value = "loging", method = RequestMethod.GET)
    public String check(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	ss.setMaxInactiveInterval(3);
    	ss.getId();
    	System.out.println(ss.getId());
    	request.getCookies();
        return "login";
    }
}

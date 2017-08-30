package com.springdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.basic.entity.Subtask;
import com.basic.entity.Task;
import com.basic.entity.Testcase;
import com.basic.entity.User;
import com.basic.service.MenuService;
import com.basic.service.SubtaskService;
import com.basic.service.TaskService;
import com.basic.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
    /**
     * ªÒ»°≤Àµ•
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getmenu", produces = "application/json; charset=utf-8")
    public String userlogin(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	JSONObject jsobj = JSONObject.fromObject(ss.getAttribute(User.USER_USER));
    	User user = (User)JSONObject.toBean(jsobj,User.class);
    	if(user == null){
    		return "";
    	}
    	JSONObject menu = menuService.getMenu(user);
    	
    	return menu.toString();
    }
    
}

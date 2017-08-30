package com.springdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.basic.service.SubtaskService;
import com.basic.service.TaskService;
import com.basic.service.TestcaseService;
import com.basic.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class DemoController {
	
	private static final String USER = "USER";
	
	@Autowired
	private TestcaseService testcaseService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private SubtaskService subtaskService;
	@Autowired
	private UserService userService;
	
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
        return "index";
    }
    
    /**
     * 登录
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userlogin", produces = "application/json; charset=utf-8")
    public String userlogin(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	String username = request.getParameter("username");
    	String passwd = request.getParameter("password");
    	
    	List<User>list = userService.get(username);
    	System.out.println(list);
    	if(list.size() > 0){
    		User user = list.get(0);
    		JSONObject jsonuser = JSONObject.fromObject(user);
    		ss.setAttribute(USER, jsonuser.toString());
    	}
    	ss.setMaxInactiveInterval(1000);
    	return "SUCCESS";
    }
    
    /**
     * 退出登录
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout", produces = "application/json; charset=utf-8")
    public String logout(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	ss.invalidate();
    	return "SUCCESS";
    }
    
    /**
     * 获取登录用户
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getuser", produces = "application/json; charset=utf-8")
    public String getUser(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	JSONObject jsobj = JSONObject.fromObject(ss.getAttribute(USER));
    	User user = (User)JSONObject.toBean(jsobj,User.class);
    	if(user == null){
    		return "";
    	}
    	if(user.getChname() != null){
    		return user.getChname();
    	}else{
    		return user.getUsername();
    	}
    }
    
    @RequestMapping(value = "/createtask", method = RequestMethod.GET)
    public String createtask(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	
//    	Testcase tc = testcaseService.get(10);
//    	System.out.println(tc);
//    	System.out.println(tc.getId());
//    	System.out.println(tc.getName());
    	String taskid = request.getParameter("taskid");
    	System.out.println("taskid:" + taskid);
        return "createtask";
    }
    
    @ResponseBody 
    @RequestMapping(value = "/testcase/all", produces = "application/json; charset=utf-8")
    public String getAllTestcase(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	
    	List<Testcase> list = testcaseService.findAll();
    	
    	ArrayList<Testcase>hdlist= new ArrayList<Testcase>();
    	for(Testcase tc : list){
    		if(tc.getPid()==0){
    			hdlist.add(tc);
    		}
    	}
    	
    	JSONArray testcase = new JSONArray();
    	for(Testcase head : hdlist){
    		JSONObject jshead = JSONObject.fromObject(head);
    		jshead.accumulate("item", head.getName());
    		testcase.add(jshead);
    		for(Testcase item : list){
    			if(item.getPid() == head.getId()){
    				JSONObject jsitem = JSONObject.fromObject(item);
    				jsitem.accumulate("item", head.getName());
    				testcase.add(jsitem);
    			}
    		}
    	}
    	
    	System.out.println(testcase.toString());
    	
    	System.out.println(list.size());
        return testcase.toString();
    }
    
    /**
     * 写入task
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savetask", produces = "application/json; charset=utf-8")
    public String saveTask(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	String data = request.getParameter("data").toString();
    	JSONObject jsobj = JSONObject.fromObject(data);
    	Task task = (Task)JSONObject.toBean(jsobj,Task.class);
    	int id = taskService.save(task);
    	return String.valueOf(id);
    }
    
    /**
     * 更新task表
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatetask", produces = "application/json; charset=utf-8")
    public String updateTask(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	String data = request.getParameter("data").toString();
    	System.out.println(data);
    	JSONObject jsobj = JSONObject.fromObject(data);
    	Task task = (Task)JSONObject.toBean(jsobj,Task.class);
    	taskService.saveOrUpdate(task);
    	return "SUCCESS";
    }
    
    /**
     * 更新subtask表
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatesubtask", produces = "application/json; charset=utf-8")
    public String updateSubTask(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	String data = request.getParameter("data").toString();
    	System.out.println(data);
    	JSONObject jsobj = JSONObject.fromObject(data);
    	Subtask task = (Subtask)JSONObject.toBean(jsobj,Subtask.class);
    	int count =  task.getTestnum();
    	for(int i=1; i<=count; i++){
    		Subtask temp = task.clone();
    		temp.setTestnum(i);
    		subtaskService.saveOrUpdate(temp);
    	}
    	
    	return "SUCCESS";
    }
    
    /**
     * 更新subtask表
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatesubtaskitem", produces = "application/json; charset=utf-8")
    public String updateSubTaskitem(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	HttpSession ss = request.getSession();
    	String data = request.getParameter("data").toString();
    	System.out.println(data);
    	JSONObject jsobj = JSONObject.fromObject(data);
    	jsobj.remove("time");
    	Subtask task = (Subtask)JSONObject.toBean(jsobj,Subtask.class);
    	subtaskService.saveOrUpdate(task);
    	
    	return "SUCCESS";
    }
    
    /**
     * 获取执行测试任务页面显示信息
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getexetask", produces = "application/json; charset=utf-8")
    public String getExetask(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	
    	HttpSession ss = request.getSession();
    	JSONObject jsobj = JSONObject.fromObject(ss.getAttribute(USER));
    	User user = (User)JSONObject.toBean(jsobj,User.class);
    	int ptaskid = Integer.valueOf((String) request.getParameter("ptaskid"));
    	Object obj = subtaskService.getDisplayInfo(user.getId(),ptaskid);
		ArrayList<Object[]> list = (ArrayList<Object[]>)obj;
		
		//过滤caseid
		ArrayList<Object>templist = new ArrayList<Object>();
		JSONArray headarray = new JSONArray();
		JSONArray itemarray = new JSONArray();
		for(Object[]tcase : list){
			Object caseid = tcase[12];
			if(!templist.contains(caseid)){
				templist.add(caseid);
				JSONObject head = new JSONObject();
				head.accumulate("pname", tcase[0]);
				head.accumulate("name", tcase[3]);
				head.accumulate("casenum", tcase[4]);
				head.accumulate("step", tcase[5]);
				head.accumulate("result", tcase[6]);
				head.accumulate("comment", tcase[7]);
				head.accumulate("caseid", tcase[12]);
				headarray.add(head);
			}
		}
    	return headarray.toString();
    }
    
    /**
     * 获取执行测试任务页面显示信息
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getexetaskitem", produces = "application/json; charset=utf-8")
    public String getExetaskitem(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
    	
    	String data = request.getParameter("data").toString();
    	JSONObject par = JSONObject.fromObject(data);
    	int operatorid = par.getInt("operatorid");
    	int caseid = par.getInt("caseid");
    	int ptaskid = par.getInt("ptaskid");
    	
    	HttpSession ss = request.getSession();
    	JSONObject jsobj = JSONObject.fromObject(ss.getAttribute(USER));
    	User user = (User)JSONObject.toBean(jsobj,User.class);
    	
    	List<Subtask>stlist = subtaskService.getItemSubtask(user.getId(), caseid, ptaskid);
    	
    	JSONArray taskarray = new JSONArray();
    	for(Subtask item : stlist){
    		JSONObject jitem = JSONObject.fromObject(item);
    		jitem.accumulate("testno", "第"+item.getTestnum()+"次");
    		taskarray.add(jitem);
    	}
    	
    	System.out.println(taskarray.toString());
        return taskarray.toString();
		
    }
    
    
    
    @RequestMapping(value = "exetask", method = RequestMethod.GET)
    public String exetask(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
        return "exetask";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
        return "login";
    }
    
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String download(HttpServletRequest request,
    		HttpServletResponse response, Object handler) {
        return "download";
    }
    
    @RequestMapping(value="/id/{spittleId}", method=RequestMethod.GET)
    public ModelAndView  spittle(
        @PathVariable("spittleId") long spittleId, 
        Model model) {
      model.addAttribute(spittleId);
      ModelAndView mv = new ModelAndView("hello");
      return new ModelAndView("hello", "message", spittleId);
    }
    
    public static HttpSession getSession() { 
        HttpSession session = null; 
        try { 
            session = getRequest().getSession(); 
        } catch (Exception e) {} 
            return session; 
    } 
        
    public static HttpServletRequest getRequest() { 
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
        return attrs.getRequest(); 
    }
    
    public static void main(String[] args){
    	System.out.println(-1>>>1);
    }
}

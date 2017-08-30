package com.basic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basic.dao.MenuDao;
import com.basic.entity.Task;
import com.basic.entity.User;
import com.basic.menu.Menu;
import com.basic.service.TaskService;

import net.sf.json.JSONObject;

@Repository("menuDao")
@Transactional
public class MenuDaoImpl implements MenuDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private TaskService taskService;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	public JSONObject getMenu(User user) {
		// TODO Auto-generated method stub
		List<Task>list = taskService.findAll(user);
		
		Menu downloaditem = new Menu();
		downloaditem.setIcon("icon-nav");
		downloaditem.setMenuname("测试工具下载");
		downloaditem.setUrl("download");
		
		switch(user.getRole()){
			case User.ROLE_ROOT:
				Menu rootmenu = new Menu();
				rootmenu.setIcon("icon-sys");
				rootmenu.setMenuname("任务管理");
				ArrayList<Menu> rootmenus = new ArrayList<Menu>();
				
				Menu rootitem = new Menu();
				rootitem.setIcon("icon-nav");
				rootitem.setMenuname("测试任务创建");
				rootitem.setUrl("createtask?userid=" + user.getId());
				
				rootmenus.add(rootitem);
				rootmenus.add(downloaditem);
				
				rootmenu.setMenus(rootmenus);
				
				ArrayList<Menu> rootmm = new ArrayList<Menu>();
				rootmm.add(rootmenu);
				
				JSONObject rootjson = new JSONObject();
				rootjson.accumulate("menus", rootmm);
				return rootjson;
			case User.ROLE_OPERATOR:
				Menu opmenu = new Menu();
				opmenu.setIcon("icon-sys");
				opmenu.setMenuname("测试任务执行");
				ArrayList<Menu> opmenus = new ArrayList<Menu>();
				for(Task task : list){
					Menu opitem = new Menu();
					opitem.setIcon("icon-nav");
					opitem.setMenuname(task.getName());
					opitem.setUrl("exetask?taskid=" + task.getTaskid());
					opmenus.add(opitem);
				}
				
				opmenus.add(downloaditem);
				opmenu.setMenus(opmenus);
				
				ArrayList<Menu> opmm = new ArrayList<Menu>();
				opmm.add(opmenu);
				
				JSONObject opjson = new JSONObject();
				opjson.accumulate("menus", opmm);
				return opjson;
			default:
				break;
		}
		
		return null;
	}

}

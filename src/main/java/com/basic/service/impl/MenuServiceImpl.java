package com.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.MenuDao;
import com.basic.entity.User;
import com.basic.service.MenuService;

import net.sf.json.JSONObject;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuDao menuDao;

	public JSONObject getMenu(User user) {
		// TODO Auto-generated method stub
		return menuDao.getMenu(user);
	}
}

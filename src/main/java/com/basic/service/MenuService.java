package com.basic.service;

import com.basic.entity.User;

import net.sf.json.JSONObject;

public interface MenuService {
	
	JSONObject getMenu(User user);
}

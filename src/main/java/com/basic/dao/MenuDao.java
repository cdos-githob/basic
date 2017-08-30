package com.basic.dao;

import com.basic.entity.User;

import net.sf.json.JSONObject;

public interface MenuDao {
	JSONObject getMenu(User user);
}

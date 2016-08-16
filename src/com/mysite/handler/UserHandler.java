package com.mysite.handler;

import java.util.List;

import com.mysite.model.User;

public class UserHandler {
 public List<User> getUsers() {
	 	return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

private List<User> users;
}


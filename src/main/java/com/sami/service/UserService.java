package com.sami.service;

import java.util.List;

import com.sami.entity.User;

public interface UserService {

	public void save(User user);

	public List<User> getAllUser();

	public void update(User user);

	public void delete(Long id);

	public User getUser(Long id);
}

package com.karsmaras.management.services;

import java.util.List;

import com.karsmaras.management.dto.UserDto;
import com.karsmaras.management.entity.User;

public interface UserService {

	User createUser(UserDto dto);

	User getUser(int id) throws Exception;

	User updateUser(User user) throws Exception;

	Boolean deleteUser(int id);

	Boolean validateUser(UserDto dto);

	List<User> getUsers();

	List<User> getFriends(int id) throws Exception;

	Boolean addFriend(int id, User user) throws Exception;

	Boolean removeFriend(int id, User user) throws Exception;

	Boolean updatePassword(int id, String oldPass, String newPass) throws Exception;

}

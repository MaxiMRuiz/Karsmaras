package com.karsmaras.management.services;

import com.karsmaras.management.dto.UserDto;
import com.karsmaras.management.entity.User;

public interface UserService {

	User createUser(UserDto dto);

	User getUser(int id);

}

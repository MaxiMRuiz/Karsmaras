package com.karsmaras.management.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.karsmaras.management.constants.Constants;
import com.karsmaras.management.dto.UserDto;
import com.karsmaras.management.entity.User;
import com.karsmaras.management.repository.UserRepository;
import com.karsmaras.management.services.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("UserRepository")
	UserRepository userRepository;

	public User createUser(UserDto dto) {
		User user = new User(dto);
		user.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
		return userRepository.save(user);
	}

	public User getUser(int id) throws Exception {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new Exception(Constants.USER_DOESNT_EXISTS);
		}
	}

	public User updateUser(User userToUpdate) throws Exception {
		Optional<User> opt = userRepository.findById(userToUpdate.getId());
		if(opt.isPresent()) {
			return userRepository.save(opt.get().copy(userToUpdate));
		}
		throw new Exception("User Not Found");
	}

	public Boolean deleteUser(int id) {

		userRepository.deleteById(id);
		return true;
	}

	public Boolean validateUser(UserDto dto) {
		User user = userRepository.findByUsername(dto.getUsername());
		return (user.getPassword().equals(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes())));
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public List<User> getFriends(int id) throws Exception {
		Optional<User> opt = userRepository.findById(id);
		List<User> listFriends = new ArrayList<>();
		if (opt.isPresent()) {
			User user = opt.get();
			for (User friend : user.getFriends()) {
				listFriends.add(friend);
			}
			return listFriends;

		} else {
			throw new Exception(Constants.USER_DOESNT_EXISTS);
		}
	}

	public Boolean addFriend(int id, User user) throws Exception {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			User current = opt.get();
			current.addFriend(user);
			return userRepository.save(current) != null;

		} else {
			throw new Exception(Constants.USER_DOESNT_EXISTS);
		}
	}

	public Boolean removeFriend(int id, User user) throws Exception {
		Optional<User> opt = userRepository.findById(id);
		Optional<User> optFriend = userRepository.findById(user.getId());
		if (opt.isPresent() && optFriend.isPresent()) {
			User current = opt.get();
			current.removeFriend(optFriend.get());
			return userRepository.save(current) != null;

		} else {
			throw new Exception(Constants.USER_DOESNT_EXISTS);
		}
	}

	public Boolean updatePassword(int id, String oldPass, String newPass) throws Exception {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			User current = opt.get();
			if (current.getPassword().equals(DigestUtils.md5DigestAsHex(oldPass.getBytes()))) {
				current.setPassword(DigestUtils.md5DigestAsHex(newPass.getBytes()));
				return userRepository.save(current) != null;
			} else {
				throw new Exception(Constants.INVALID_PASSWORD);
			}

		} else {
			throw new Exception(Constants.USER_DOESNT_EXISTS);
		}
	}
}

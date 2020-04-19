package com.races.portal.services;

import com.races.portal.dto.LoginDto;
import com.races.portal.dto.LoginResponse;

public interface AuthService {

	public LoginResponse login(LoginDto dto) throws Exception;

}

package com.races.auth.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.races.auth.constants.Constants;
import com.races.auth.model.dto.CreateUserDto;
import com.races.auth.model.dto.ResponseUserDto;
import com.races.auth.model.dto.UpdateRoleDto;
import com.races.auth.model.dto.UpdateUserDto;
import com.races.auth.model.entity.LdapUser;
import com.races.auth.services.LdapUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for user services
 * 
 * @author Maximino Mañanes Ruiz
 */
@Api(tags = { "User LDAP Services" })
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Log LOGGER = LogFactory.getLog(UserController.class);

	@Autowired
	@Qualifier("LdapUserService")
	LdapUserService ldapUserService;

	/**
	 * Service to find a single user on the LDAP if exists
	 * 
	 * @param request
	 * @param response
	 * @param commonname
	 * @return
	 */
	@ApiOperation(value = "Service to find a single user on the LDAP if exists.", response = LdapUser.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 404, message = "User not found") })
	@RequestMapping(value = "/getUser/{commonname}", method = RequestMethod.GET)
	public ResponseEntity<ResponseUserDto> getSingleUser(final HttpServletRequest request,
			final HttpServletResponse response,
			@ApiParam(name = Constants.COMMONNAME, required = true) @PathVariable(name = Constants.COMMONNAME) String commonname) {

		try {
			LOGGER.info("Searching User " + commonname);
			ResponseUserDto user = new ResponseUserDto(ldapUserService.searchUser(commonname));
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Service to get a list of All Users on the Ldap. If parameter "company" is
	 * present, return only a list of users of this company.
	 * 
	 * @param request
	 * @param response
	 * @param company
	 * @return
	 */
	@ApiOperation(value = " Service to get a list of All Users on the Ldap. If parameter company is present, return only a list of users of this company.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 404, message = "Company not found") })
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDto>> getAllUsers(final HttpServletRequest request,
			final HttpServletResponse response) {

		try {
			List<ResponseUserDto> result;

			LOGGER.info("Searching All Users");
			result = ldapUserService.searchAll();

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Service to create a new user
	 * 
	 * @param request
	 * @param response
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "Service to create a new user.", response = LdapUser.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Role doesn't exists") })
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<ResponseUserDto> createUser(final HttpServletRequest request,
			final HttpServletResponse response,
			@ApiParam(name = "User data", required = true) @RequestBody CreateUserDto dto) {

		try {
			if (!dto.valid()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			ResponseUserDto result = new ResponseUserDto(ldapUserService.createUser(dto));
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Service to update user data
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "Service to update user data.", response = LdapUser.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 404, message = "User Not Found") })
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public ResponseEntity<ResponseUserDto> updateUser(final HttpServletRequest request,
			final HttpServletResponse response,
			@ApiParam(name = "User data", required = true) @RequestBody UpdateUserDto user) {

		try {
			LOGGER.info("Updating User " + user.getUserCode());
			ResponseUserDto result = ldapUserService.updateUser(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Service to remove user from the LDAP
	 * 
	 * @param request
	 * @param response
	 * @param commonname
	 * @return
	 */
	@ApiOperation(value = "Service to remove user from the LDAP", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 404, message = "User Not Found") })
	@RequestMapping(value = "/removeUser/{commonname}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeUser(final HttpServletRequest request, final HttpServletResponse response,
			@ApiParam(name = Constants.COMMONNAME, required = true) @PathVariable(name = Constants.COMMONNAME) String commonname) {

		try {
			LOGGER.info("Removing User " + commonname);
			Boolean result = ldapUserService.removeUser(commonname);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Service to authenticate user/password
	 * 
	 * @param request
	 * @param response
	 * @param auth
	 * @return
	 */
	@ApiOperation(value = "Service to authenticate user/password", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 403, message = "Forbidden") })
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public ResponseEntity<String> validate(final HttpServletRequest request, final HttpServletResponse response) {

		String credentials = request.getHeader(Constants.HEADER_PASS);
		try {
			credentials = new String(Base64.getDecoder().decode(credentials.substring(6)), "UTF-8");

			String[] credent = credentials.split(":");
			String user = credent[0];
			String password = credent[1];

			LOGGER.info("Check credentials for User " + user);
			ldapUserService.authenticate(user, password);
			return new ResponseEntity<>(Constants.STATUS_OK, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(Constants.STATUS_KO, HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Service to change the status of the indicated user. "enable" attribute can be
	 * only "TRUE" or "FALSE"
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "Service to change the status of the indicated user. \"enable\" attribute can be only \"TRUE\" or \"FALSE\"", response = LdapUser.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 404, message = "User Not Found") })
	@RequestMapping(value = "/enable", method = RequestMethod.PUT)
	public ResponseEntity<ResponseUserDto> setEnable(final HttpServletRequest request,
			final HttpServletResponse response,
			@ApiParam(name = "User Data", required = true) @RequestBody LdapUser user) {

		try {
			LOGGER.info("Update status for user " + user.getUserCode());
			return new ResponseEntity<>(ldapUserService.setEnable(user), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Service to update password
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "Service to update password", response = LdapUser.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 406, message = Constants.UPDATE_PASS_ERR_MSG) })
	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
	public ResponseEntity<Object> updatePass(final HttpServletRequest request, final HttpServletResponse response) {

		String credentials = request.getHeader(Constants.HEADER_PASS);
		try {
			credentials = new String(Base64.getDecoder().decode(credentials.substring(6)), "UTF-8");

			String[] credent = credentials.split(":");
			String user = credent[0];
			String password = credent[1];
			String newPassword = credent[2];
			LOGGER.info("Update password for user " + user);
			String result = ldapUserService.updatePassword(user, password, newPassword);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			HttpStatus status;
			if (e.getMessage().equals(Constants.UPDATE_PASS_ERR_MSG)) {
				status = HttpStatus.NOT_ACCEPTABLE;
			} else {
				status = HttpStatus.FORBIDDEN;
			}
			return new ResponseEntity<>(status);
		}
	}

	/**
	 * 
	 * Service to update a user's role
	 * 
	 * @param request
	 * @param response
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "Service to update a user's role", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 400, message = "Attributes not found"),
			@ApiResponse(code = 404, message = "User Not Found") })
	@RequestMapping(value = "/updateRole", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateRole(final HttpServletRequest request, final HttpServletResponse response,
			@ApiParam(name = "userCode && roleNew", required = true) @RequestBody UpdateRoleDto dto) {

		try {
			String user = dto.getUserCode();
			String role = dto.getNewRole();

			if (user == null || role == null) {
				LOGGER.error("Attributes userCode and newRole not found");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			LOGGER.info("Set role " + role + " to user " + user);
			Boolean result = ldapUserService.updateRole(user, role);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Service to recover the user's password
	 * 
	 * @param request
	 * @param response
	 * @param commonname
	 * @return
	 */
	@ApiOperation(value = "Service to recover the user's password", response = LdapUser.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.STATUS_OK),
			@ApiResponse(code = 404, message = "User Not Found") })
	@RequestMapping(value = "/recoverPassword/{commonname}", method = RequestMethod.POST)
	public ResponseEntity<Object> recoverPass(final HttpServletRequest request, final HttpServletResponse response,
			@ApiParam(name = Constants.COMMONNAME, required = true) @PathVariable(name = Constants.COMMONNAME) String commonname) {

		try {
			LOGGER.info("Recover password for user " + commonname);
			String result = ldapUserService.recoverPassword(commonname);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}

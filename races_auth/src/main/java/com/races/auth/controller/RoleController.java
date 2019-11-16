package com.races.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.races.auth.model.entity.LdapRole;
import com.races.auth.services.LdapRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for Roles services
 * 
 * @author Maximino Mañanes Ruiz
 */
@Api(tags = { "Role LDAP Services" })
@RestController
@RequestMapping("/role")
public class RoleController {

	private static final Log LOGGER = LogFactory.getLog(RoleController.class);

	@Autowired
	@Qualifier("LdapRoleService")
	LdapRoleService ldapRoleService;
	
	/**
	 * List of Roles in the LDAP
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "List of Roles in the LDAP", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<LdapRole>> getAllRoles(final HttpServletRequest request,
			final HttpServletResponse response) {

		LOGGER.info("Searching All roles.");
		return new ResponseEntity<>(ldapRoleService.searchAllRoles(), HttpStatus.OK);

	}

}

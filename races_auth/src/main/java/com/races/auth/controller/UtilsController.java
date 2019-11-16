package com.races.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Utils Controller. Utils services for Authentication Service like status service
 * @author Maximino Mañanes Ruiz
 */
@RestController
public class UtilsController {

	/**
	 * Status service 
	 * @return
	 */
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String statusView() {
		return "Ok";
	}
	
}

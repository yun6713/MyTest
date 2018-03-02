package com.bonc.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.domain.User;
import com.bonc.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(tags = "用户管理", description = "对系统内的用户信息进行管理")
@RequestMapping(value = "/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "查询并展示所有用户")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	String showAll() {
		List<User> users = userService.queryAll();
		StringBuffer result = new StringBuffer();
		for (User user : users)
			result.append(user.toString()).append("\n");
		return result.toString();
	}

	@ApiOperation(value = "查询并展示特定用户")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	String showUser() {
		return "/user";
	}

	@ApiOperation(value = "打开注册页面")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	String showPage() {
		return "/index1";
	}

	@ApiOperation(value = "添加用户行为")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userName", value = "注册用户名", paramType = "form", required = true),
			@ApiImplicitParam(name = "userEmail", value = "注册邮箱", paramType = "form", required = true),
			@ApiImplicitParam(name = "password", value = "用户密码", paramType = "form"),
			@ApiImplicitParam(name = "disable", dataType = "Boolean", value = "是否禁用", paramType = "form", required = true),
			@ApiImplicitParam(name = "deletable", dataType = "Boolean", value = "是否可删除", paramType = "form", required = true) })
	@RequestMapping(value = "/userRegistion", method = RequestMethod.POST)
	@ResponseBody
	String userRegistion(@RequestParam String userName, 
			@RequestParam String userEmail,
			@RequestParam(required = false, defaultValue = "") String password, 
			@RequestParam Boolean disable,
			@RequestParam Boolean deletable) {
		User user = new User();
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setPassword(password);
		user.setDisable(disable);
		user.setDeletable(deletable);
		user.setLoginId(String.valueOf(new Random().nextInt(12345)));
		String pwd = user.getPassword();
		user.setPassword(pwd);
		user.setTeamId(1L);
		userService.add(user);

		List<User> users = userService.queryAll();
		StringBuffer result = new StringBuffer();
		for (User user1 : users)
			result.append(user1.toString()).append("\n");
		return result.toString();
	}
}

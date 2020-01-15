package com.good.frame.controller;

import com.good.frame.bean.User;
import com.good.frame.bean.WebResult;
import com.good.frame.config.MyConfig;
import com.good.frame.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
public class UserController {

    @Autowired
    MyConfig myConfig;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/addUser.do")
    public WebResult addUser(String userName, String password) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("345", new User("小明", "567567"));
        List<User> users = new ArrayList<>();
        users.add(new User("李小龙", myConfig.getUserName()));
        users.add(new User("小李", "134134"));
        userMap.put("userList", users);
        //map、list等对象随便放
        if (userName == null && password == null) {
            return new WebResult().error("参数错误！");
        }
        User user = new User(userName, password);
        boolean isSuccess = userService.addUser(user);
        if (isSuccess)
            return new WebResult<>(userMap).success();
        else
            return new WebResult<>(userMap).error("添加失败！");
    }
}

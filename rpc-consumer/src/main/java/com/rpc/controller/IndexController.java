package com.rpc.controller;

import com.rpc.api.InfoUserService;
import com.rpc.entity.InfoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    InfoUserService userService;

    @RequestMapping("getById")
    @ResponseBody
    public InfoUser getById(String id){
        logger.info("根据ID查询用户信息:{}",id);
        return userService.getInfoUserById(id);
    }
}

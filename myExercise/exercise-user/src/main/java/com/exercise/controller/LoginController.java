package com.exercise.controller;

import com.exercise.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王能顺
 * @Date: 2021-1-4 15:12:13
 *
 * 用户登录注册的控制层
 */
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController {

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "login",method= RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        log.info("logback 访问:登录接口");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        Map<Object, Object> param = new HashMap<Object, Object>();
        param.put("userName", userName);
        param.put("passWord", passWord);
//        User user = userService.loginUser(param);
        User user = new User();
        user.setId(1L);
        user.setUserName("admin");
        user.setPassWord("123456");
        Map<Object, Object> result = new HashMap<Object, Object>();
        if(user != null) {
            result.put("resultCode", 200);
            result.put("msg", "登录成功");
            setSession(request, user);
        }else {
            result.put("resultCode", 500);
            result.put("msg", "用户名或者密码错误");
        }
        return result;
    }

    public void setSession(HttpServletRequest request, User loginUser){
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("loginUser", loginUser);
        session.setMaxInactiveInterval(60 * 20); //单位秒
    }

}

package com.tuling.controller;

import com.tuling.entity.SysUsers;
import com.tuling.service.SysUsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {
    @Autowired
    private SysUsersService sysUsersService;


    @RequestMapping("loginShrio")
    public String loginShrio(String loginId, String password, Model model, HttpServletRequest request){
        //通过shiro 提供的验证方式登录
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //把用户提交的用户名和密码封装到shiro提供的一个对象中
        UsernamePasswordToken token = new UsernamePasswordToken(loginId,password);
        //提交认证
        try {
            subject.login(token);

            SysUsers sysUsers = new SysUsers();
            sysUsers.setLoginId(loginId);
            sysUsers.setPassword(password);
            SysUsers user = sysUsersService.findeByloginId(sysUsers);

            //把用户存到session中
            request.getSession().setAttribute("user",user);
            //登录成功
            return "redirect:/index.html";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名不存在!");
            return "Index";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误!");
            return "Index";
        }
    }

    //注册
    @RequestMapping("registered")
    @ResponseBody
    public String registered(SysUsers sysUsers){
        return sysUsersService.insertSysUsers(sysUsers)+"";
    }

    //注销
    @RequestMapping("cancellation")
    public String cancellation(HttpServletRequest request){
        //注销session
        request.getSession().invalidate();
        return "Index";
    }
}

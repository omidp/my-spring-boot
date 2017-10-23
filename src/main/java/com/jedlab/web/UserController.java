package com.jedlab.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jedlab.dao.UserDao;
import com.jedlab.domain.User;

@Controller
public class UserController
{

    @Autowired
    UserDao dao;
    
    @RequestMapping(value="/userList", method=RequestMethod.GET)
    public String index(Model model)
    {
        model.addAttribute("userList", dao.findAll());        
        return "userList";
    }
    
    
    @RequestMapping(value="/createUser", method=RequestMethod.GET)
    public String createUser(Model model)
    {
        User u = new User();
        u.setUsername("omid");
        u.setPasswd("123");
        dao.save(u);
        return "userList";
    }

}

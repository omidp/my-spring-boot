package com;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController
{

    @RequestMapping("/")
    public String index(Map<String, Object> model)
    {
        model.put("message", "Hello");
        return "hello";
    }

}

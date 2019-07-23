package com.cwh.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLOutput;

/**
 * @author Cwh
 * CreateTime 2019/7/23 11:43
 */
@Controller
public class HelloWorld {

    @Autowired
    private UserService userService;


    public HelloWorld(){
        System.out.println("Hello World...");
    }
}

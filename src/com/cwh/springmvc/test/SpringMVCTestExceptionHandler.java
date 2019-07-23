package com.cwh.springmvc.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Cwh
 * CreateTime 2019/7/23 9:27
 */
@ControllerAdvice
public class SpringMVCTestExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception ex){
        System.out.println("---->出异常了："+ex);
        ModelAndView modelAndView =new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }

}

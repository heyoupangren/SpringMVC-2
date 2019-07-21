package com.cwh.springmvc.test;

import com.cwh.springmvc.crud.dao.EmployeeDao;
import com.cwh.springmvc.crud.entites.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * @author Cwh
 * CreateTime 2019/7/20 12:36
 */
@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/i18n")
    public String testI18n(Locale locale){
        String val =messageSource.getMessage("i18n.user",null,locale);
        System.out.println(val);
        return "i18n";
    }

    @RequestMapping("/testResponseEntity")
   public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        byte [] body =null;
        ServletContext servletContext =session.getServletContext();
        InputStream in =servletContext.getResourceAsStream("/files/123.txt");
        body =new byte[in.available()];
        in.read(body);

        HttpHeaders headers =new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=123.txt");

        HttpStatus statusCode =HttpStatus.OK;

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body,headers,statusCode);
       return response;
   }

    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println(body);
        return "HelloWorld"+new Date();
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        return employeeDao.getAll();
    }
    @RequestMapping("/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee") Employee employee){
        System.out.println("save: "+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}

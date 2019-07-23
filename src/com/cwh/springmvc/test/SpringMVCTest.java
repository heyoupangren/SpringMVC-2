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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

/*
    @Autowired
    private ResourceBundleMessageSource messageSource;
*/

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i") Integer i){
        String[] vals = new String[10];
        System.out.println(vals[i]);
        return "success";
    }

    @RequestMapping(value = "/testDefaultHandlerExceptionResolver",method = RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver(){
        System.out.println("testDefaultHandlerExceptionResolver...");
        return "success";
    }

    /**
     * @ResponseStatus 加在自定义错误上可以自定义错误提示页面，
     * 加在方法上程序能正常运行，但是页面显示的还是定制的错误提示页面
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "测试")
    @RequestMapping("/testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(@RequestParam("i") Integer i){
       if(i==13){
           throw new UsernameNotMatchPasswordException();
       }
        System.out.println("testResponseStatusExceptionResolver....");
        return "success";
    }

    /**
     *异常
     */
/*    @ExceptionHandler({RuntimeException.class})
    public ModelAndView handleArithmeticException2(Exception ex){
        System.out.println("【出异常了】："+ex);
        ModelAndView modelAndView =new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }*/


    /**
     *1.在@ExceptionHandler 方法的入参中可以加入Exception 类型的参数，该参数即对应发生的异常对象
     * 2.@ExceptionHandler 方法的入参中不能传入Map。若希望把异常信息传到页面上，需要使用ModelAndView 作为返回值
     * 3.@ExceptionHandler 方法标记的异常有优先级问题
     * 4.@ControllerAdvice:如果在当前Handler 中找不到@ExceptionHandler 方法来处理当前方法出现的异常，
     * 则将去@ControllerAdvice 标记的类中查找@ExceptionHandler 标记的的方法来处理异常
     */
    /**
     *异常
     */
/*    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception ex){
        System.out.println("出异常了："+ex);
        ModelAndView modelAndView =new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }*/

    @RequestMapping("/testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(@RequestParam("i") Integer i){
        System.out.println("result :"+(10/i));
        return "success";
    }

    /**
     *文件上传
     */
    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc,
                                 @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("desc :"+desc);
        System.out.println("OriginalFileName :"+file.getOriginalFilename());
        System.out.println("InputStream :"+file.getInputStream());
        return "success";
    }

    /**
     *国际化标准转化
     */
/*    @RequestMapping("/i18n")
    public String testI18n(Locale locale){
        String val =messageSource.getMessage("i18n.user",null,locale);
        System.out.println(val);
        return "i18n";
    }*/

    /**
     *文件下载
     */
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

    /**
     *文件的读取
     */
    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println(body);
        return "HelloWorld"+new Date();
    }

    //返回Json对象
    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        return employeeDao.getAll();
    }

    //将字符串转化为bean对象
    @RequestMapping("/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee") Employee employee){
        System.out.println("save: "+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}

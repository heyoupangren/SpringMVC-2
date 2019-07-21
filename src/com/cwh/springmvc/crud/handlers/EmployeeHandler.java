package com.cwh.springmvc.crud.handlers;

import com.cwh.springmvc.crud.dao.DepartmentDao;
import com.cwh.springmvc.crud.dao.EmployeeDao;
import com.cwh.springmvc.crud.entites.Employee;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Cwh
 * CreateTime 2019/7/20 10:26
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     *  @InitBinder 方法 不能有返回值，它必须声明为void
     *   @InitBinder 方法的参数通常为WebDataBinder
     */
 /*   @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields("lastName");
    }
*/
    @ModelAttribute
    public  void getEmployee(@RequestParam(value = "id",required = false) Integer id,Map<String,Object> map){
        if(id !=null){
            map.put("employee",employeeDao.get(id));
        }

    }
    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String,Object>map){
        map.put("employee",employeeDao.get(id));
        map.put("departments",departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    public String save(Employee employee, BindingResult result){
        if (result.getErrorCount()>0){
            System.out.println("出错了!!!");
            for (FieldError error :result.getFieldErrors()){
                System.out.println(error.getField()+":"+error.getDefaultMessage());
            }
        }
        System.out.println("save: "+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    public String input(Map<String,Object>map){
        map.put("departments",departmentDao.getDepartments());
        map.put("employee",new Employee());
        return "input";
    }

    @RequestMapping("/emps")
    public String list(Map<String,Object> map){
        map.put("employees",employeeDao.getAll());
        return "list";
    }
}

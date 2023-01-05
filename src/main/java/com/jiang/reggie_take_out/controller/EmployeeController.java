package com.jiang.reggie_take_out.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiang.reggie_take_out.common.R;
import com.jiang.reggie_take_out.entity.Employee;
import com.jiang.reggie_take_out.service.EmployeeService;
import com.jiang.reggie_take_out.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee ){
        
        return employeeService.login(request,employee);
        
    }
    
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        
        return employeeService.logout(request);
        
    }
    
    @PostMapping()
    public R<String> save(@RequestBody Employee employee,HttpServletRequest request) {
        employeeService.saveEmp(request,employee);
        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page pageInfo = employeeService.EmpPage(page, pageSize, name);
        return  R.success(pageInfo);
    }
    
    @PutMapping
    public R<String> updateStatus(HttpServletRequest request,@RequestBody Employee employee){
        employeeService.updateStatus(request,employee);
        
        return R.success("用户状态更新成功");
    }
    
    
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }
    
}
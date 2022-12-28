package com.jiang.reggie_take_out.controller;


import com.jiang.reggie_take_out.common.R;
import com.jiang.reggie_take_out.entity.Employee;
import com.jiang.reggie_take_out.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

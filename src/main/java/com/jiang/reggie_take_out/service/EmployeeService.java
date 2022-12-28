package com.jiang.reggie_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.reggie_take_out.common.R;
import com.jiang.reggie_take_out.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {
    
    R<Employee> login(HttpServletRequest request,Employee employee);
    
    R<String> logout(HttpServletRequest request);
    
}

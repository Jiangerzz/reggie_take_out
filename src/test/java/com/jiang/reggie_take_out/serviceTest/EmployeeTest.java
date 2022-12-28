package com.jiang.reggie_take_out.serviceTest;


import com.jiang.reggie_take_out.common.R;
import com.jiang.reggie_take_out.entity.Employee;
import com.jiang.reggie_take_out.service.EmployeeService;
import com.jiang.reggie_take_out.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@SpringBootTest
@Transactional
public class EmployeeTest {
    
    @Resource
    private EmployeeService employeeService;
    
    private MockHttpServletRequest request;
    
    private Employee employee;

    @BeforeEach
    public void setUp(){
        request = new MockHttpServletRequest();
        employee = new Employee();
    }
    
    
    
    @Test
    public void testLogin(){
        employee.setUsername("admin");
        employee.setPassword("123456");
        R<Employee> result = employeeService.login(request, employee);
        System.out.println(result);
        System.out.println("=========================");
        Employee emp = (Employee) request.getSession().getAttribute("emp");
        System.out.println(emp);
    }
    
    @Test
    public void testLogout(){
        employee.setUsername("admin");
        employee.setPassword("123456");
        request.getSession().setAttribute("emp",employee);
        System.out.println((Employee) request.getSession().getAttribute("emp"));
        System.out.println("=======去除emp后========");
        System.out.println(employeeService.logout(request));
        System.out.println((Employee) request.getSession().getAttribute("emp"));
    }
}

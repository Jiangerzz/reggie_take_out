package com.jiang.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.reggie_take_out.common.R;
import com.jiang.reggie_take_out.entity.Employee;
import com.jiang.reggie_take_out.mapper.EmployeeMapper;
import com.jiang.reggie_take_out.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;


@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    
    private EmployeeMapper employeeMapper;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper){
        this.employeeMapper = employeeMapper;
    }
    
    @Override
    public R<Employee> login(HttpServletRequest request,Employee employee) {
        
        String password = employee.getPassword(); //获取密码
        password = DigestUtils.md5DigestAsHex(password.getBytes()); // m5加密
        
        //根据username查数据库
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeMapper.selectOne(wrapper);
        
        //如果emp为空即没有查询到，则直接返回登陆失败结果。
        if(emp == null){
            return R.error("登陆失败");
        }
        
        //密码不对，则返回登陆失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误");
        }
        
        //查看员工状态，如果为禁用，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return R.error("账户已被禁用");
        }
        
        //将emp信息存储session
        request.getSession().setAttribute("emp",emp.getId());
        return R.success(emp);
    }

    @Override
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("emp");
        return R.success("退出成功");
    }

    @Override
    public void saveEmp(HttpServletRequest request,Employee employee) {
//        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        Employee emp = (Employee)request.getSession().getAttribute("emp");
//        log.info("emp{}",emp);
//        employee.setCreateUser(emp.getId());
//        employee.setUpdateUser(emp.getId());
        employeeMapper.insert(employee);
    }

    @Override
    public Page EmpPage(int page, int pageSize, String name) {
        
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        wrapper.orderByDesc(Employee::getUpdateTime);
        page(pageInfo,wrapper);
        return pageInfo;
    }

    @Override
    public void updateStatus(HttpServletRequest request, Employee employee) {
        //获取当前用户id
//        Employee emp = (Employee) request.getSession().getAttribute("emp");
//        Long userId = emp.getId();
//        
//        employee.setUpdateUser(userId);
//        employee.setUpdateTime(LocalDateTime.now());

        this.updateById(employee);
    }
}

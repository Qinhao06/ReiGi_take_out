package com.qh.reigi.service.impl;


import com.qh.reigi.common.R;
import com.qh.reigi.entity.Employee;
import com.qh.reigi.dao.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.qh.reigi.common.Constant.*;
import static com.qh.reigi.utils.Utils.MD5Process;

@Service
public class EmployeeServiceImpl implements com.qh.reigi.service.EmployeeService {


    Employee employee;

    Employee employeeGetByQuery;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Override
    public boolean checkUsername() {
        Employee employeeMapperEmployeeByUsername = employeeMapper.getEmployeeByUsername(employee.getUsername());
        if(employeeMapperEmployeeByUsername != null && employeeMapperEmployeeByUsername.getUsername().
                equals(employee.getUsername())){
            employeeGetByQuery = employeeMapperEmployeeByUsername;
            return true;
        }

        return false;
    }

    @Override
    public boolean checkPassword() {
        return employeeGetByQuery.getPassword().equals(MD5Process(employee.getPassword()));
    }

    @Override
    public boolean checkStatus() {
        return employeeGetByQuery.getStatus() == INITIAL_STATUS;
    }

    /**
     * 登录检查
     * @param request
     * @param employee
     * @return
     */
    @Override
    public R<Employee> checkLogin(HttpServletRequest request, Employee employee) {
        setEmployee(employee);
        if(!checkUsername()){
            return R.error("用户名不存在");
        }
        if(!checkPassword()){
            return R.error("密码错误");

        }
        if(!checkStatus()){
            return R.error("这不是合法用户");
        }
        request.getSession().setAttribute(EMPLOYEE_SESSION,employeeGetByQuery);
        return R.success(employeeGetByQuery);
    }


    /**
     * 添加用户
     *
     * @param request
     * @param employee
     * @return
     */
    @Override
    public R<String> addEmployee(HttpServletRequest request, Employee employee) {

        employee.setPassword(MD5Process(INITIAL_PASSWORD));
        employee.setStatus(INITIAL_STATUS);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Employee parentEmployeeId = (Employee) request.getSession().getAttribute(EMPLOYEE_SESSION);
        employee.setCreateUser(parentEmployeeId.getId());
        employee.setUpdateUser(parentEmployeeId.getId());
        employeeMapper.addEmployee(employee);
        return R.success("员工添加成功");
    }

    @Override
    public R<String> updateEmployee(HttpServletRequest request, Employee employee) {
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(employee.getId());
        employeeMapper.updateEmployee(employee);
        return R.success("修改成功");
    }

    @Override
    public R<String> changeStatus(HttpServletRequest request, Long id, Integer status) {
        if(!employee.getUsername().equals(ADMIN_USERNAME)){
            return R.error("没有权限");
        }
        if(Objects.equals(id, ADMIN_ID)){
            return R.error("不能修改管理员状态");
        }
        employeeMapper.changeStatus(id,status);
        return R.success("成功修改账号状态");
    }

    @Override
    public R<Employee> getEmployeeById(Long id) {
        Employee employeeById = employeeMapper.getEmployeeById(id);
        return employeeById != null ? R.success(employeeById) : R.error("用户不存在");
    }


}

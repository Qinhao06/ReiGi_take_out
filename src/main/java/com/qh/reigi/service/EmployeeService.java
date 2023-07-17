package com.qh.reigi.service;

import com.qh.reigi.common.R;
import com.qh.reigi.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {

     void setEmployee(Employee employee);

     boolean checkUsername();

     boolean checkPassword();

     boolean checkStatus();

    R<Employee> checkLogin(HttpServletRequest request, Employee employee);

    R<String> addEmployee(HttpServletRequest request, Employee employee);

    R<String> updateEmployee(HttpServletRequest request, Employee employee);

    R<String> changeStatus(HttpServletRequest request, Long id, Integer status);

    R<Employee> getEmployeeById(Long id);



}

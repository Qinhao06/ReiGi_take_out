package com.qh.reigi.dao.mapper;

import com.qh.reigi.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmployeeMapper {

    void changeStatus(@Param("id") Long id, @Param("status") Integer status);

    void addEmployee(@Param("employee") Employee employee);


    Employee getEmployeeByUsername(@Param("username") String username);

    Employee  getEmployeeById(@Param("id") Long id);

    void updateEmployee(@Param("employee") Employee employee);

}

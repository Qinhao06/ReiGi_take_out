package com.qh.reigi.controller;


import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.entity.Employee;
import com.qh.reigi.service.EmployeeService;
import com.qh.reigi.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    PageService pageService;

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee) {
        return employeeService.checkLogin(request, employee);
    }


    @PostMapping("/save")
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee) {

        try {
            employeeService.addEmployee(request, employee);
        }catch (Exception e){
            return R.error(e.getMessage());
        }


        return R.success("新增员工异常");
    }


    @GetMapping("/page")
    public R<PageInfo<Employee>> page(@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return R.success(pageService.getPage(pageNum, pageSize, "employee", "id"));
    }

    @PutMapping("/forbidden")
    public R<String> changeStatus(HttpServletRequest request, @RequestBody Employee employee){
        return employeeService.changeStatus(request, employee.getId(), employee.getStatus());
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable("id") Long id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update")
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        return employeeService.updateEmployee(request, employee);
    }
}


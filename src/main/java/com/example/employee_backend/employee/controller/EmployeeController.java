package com.example.employee_backend.employee.controller;

import com.example.employee_backend.employee.dao.Employee;
import com.example.employee_backend.employee.dao.IEmployeeRepository;
import com.example.employee_backend.employee.dto.EmployeeDto;
import com.example.employee_backend.employee.service.IEmployeeService;
import com.example.employee_backend.util.PagingObject;
import com.example.employee_backend.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("employee")
@CrossOrigin(origins = "*")

public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IEmployeeRepository iRepository;
    @PostMapping("create")
    public ResponseObject createEmployee(@RequestBody EmployeeDto employeeDto){
        try {
            return iEmployeeService.createEmployee(employeeDto);
        }catch (Exception exception){
            return new ResponseObject(exception);
        }
    }
    @GetMapping("all")
    public ResponseObject findAllByEmployee(@RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "10") Integer size){
        try {
            PagingObject pagingObject = new PagingObject(start,size);
            return iEmployeeService.findAllByEmployee(pagingObject);
        }catch (Exception exception){
            return new ResponseObject(exception);
        }
    }
    @GetMapping("id")
    public ResponseObject getEmployeeById(@RequestHeader String id){

        try {
            return iEmployeeService.getEmployeeById(UUID.fromString(id));
        }catch (Exception exception){
            return new ResponseObject(exception);
        }
    }
    @PutMapping("update")
    public ResponseObject updateEmployee(@RequestBody Employee employee){
        try {
            return iEmployeeService.updateEmployee(employee);
        }catch (Exception exception){
            return new ResponseObject(exception);
        }
    }
    @DeleteMapping("delete")
    public ResponseObject deleteEmployee(@RequestHeader UUID id){
        try {
            return iEmployeeService.deleteEmployee(id);
        }catch (Exception exception) {
            return new ResponseObject(exception);
        }
    }

    @GetMapping("api/v1/employees/")
    public List<Employee> getAllEmployes(){
        return iRepository.findAll();
    }
}

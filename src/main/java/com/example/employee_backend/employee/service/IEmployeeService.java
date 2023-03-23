package com.example.employee_backend.employee.service;

import com.example.employee_backend.employee.dao.Employee;
import com.example.employee_backend.employee.dto.EmployeeDto;
import com.example.employee_backend.util.PagingObject;
import com.example.employee_backend.util.ResponseObject;

import java.util.UUID;

public interface IEmployeeService {
    ResponseObject createEmployee(EmployeeDto employeeDto);
    ResponseObject getEmployeeById(UUID id);
    ResponseObject updateEmployee(Employee employee);
    ResponseObject findAllByEmployee(PagingObject  pagingObject);
    ResponseObject deleteEmployee(UUID id);
}

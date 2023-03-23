package com.example.employee_backend.employee.service;

import com.example.employee_backend.employee.dao.Employee;
import com.example.employee_backend.employee.dao.IEmployeeRepository;
import com.example.employee_backend.employee.dto.EmployeeDto;
import com.example.employee_backend.util.PagingObject;
import com.example.employee_backend.util.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public ResponseObject createEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            return new ResponseObject(employeeRepository.save(employee));
        }catch (Exception exception){
            throw  new RuntimeException(exception);
        }
    }

    @Override
    public ResponseObject getEmployeeById(UUID id) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()){
                return new ResponseObject(optionalEmployee.get());
            }else {
                throw new Exception("Employee not found");
            }
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }

    }

    @Override
    public ResponseObject updateEmployee(Employee employee) {
        try {
            return new ResponseObject(employeeRepository.save(employee));
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ResponseObject findAllByEmployee(PagingObject pagingObject) {
        try {
            Integer page = (pagingObject.getStart()/ pagingObject.getSize());
            Pageable pageable = PageRequest.of(page, pagingObject.getSize());

            Page<Employee> employeePage = employeeRepository.findAll(pageable);
            ResponseObject responseObject = new ResponseObject(employeePage.getContent());
            responseObject.setCount(employeePage.getTotalElements());
            return responseObject;
        }catch (Exception exception){
            throw  new RuntimeException(exception);
        }
    }

    @Override
    public ResponseObject deleteEmployee(UUID id) {
        try {
                 employeeRepository.deleteById(id);
                 ResponseObject responseObject = new ResponseObject("deleted successfully");
                return responseObject;

        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}

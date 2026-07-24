package com.vysaloon.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vysaloon.backend.entity.Employee;
import com.vysaloon.backend.repository.EmployeeRepository;
import com.vysaloon.backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
    return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

    Employee employee = getEmployeeById(id);

    employeeRepository.delete(employee);
}

    @Override
    public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
}

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

    Employee existingEmployee = getEmployeeById(id);

    existingEmployee.setName(employee.getName());
    existingEmployee.setPhone(employee.getPhone());
    existingEmployee.setSpecialization(employee.getSpecialization());
    existingEmployee.setExperience(employee.getExperience());
    existingEmployee.setWorkingHours(employee.getWorkingHours());
    existingEmployee.setActive(employee.getActive());

    return employeeRepository.save(existingEmployee);
}

}
package com.maxmayev.application.backend.service;

import com.maxmayev.application.backend.domain.Employee;
import com.maxmayev.application.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessingEmployeeServiceImpl implements ProcessingEmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public ProcessingEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void add(Employee employee) {
        employeeRepository.save(employee);
    }
}

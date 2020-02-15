package com.maxmayev.application.backend.service;

import com.maxmayev.application.backend.domain.Employee;
import com.maxmayev.application.backend.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BackendServiceImpl implements BackendService {

    @Autowired
    EmployeeRepository employeeRepository;

    public BackendServiceImpl() {
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }
}

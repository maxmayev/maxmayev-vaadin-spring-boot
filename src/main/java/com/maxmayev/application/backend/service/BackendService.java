package com.maxmayev.application.backend.service;

import com.maxmayev.application.backend.domain.Employee;

import java.util.List;

public interface BackendService {
    List<Employee> getEmployees();
}

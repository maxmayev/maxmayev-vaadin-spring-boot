package com.maxmayev.application.backend.repository;

import com.maxmayev.application.backend.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}

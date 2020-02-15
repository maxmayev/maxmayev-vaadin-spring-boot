package com.maxmayev.application.backend.dto;

import com.maxmayev.application.backend.domain.Employee;
import com.maxmayev.application.backend.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class EmployeeDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String title;

    public Employee toEmployee() {
        return new Employee(firstname, lastname, email, title);
    }
}

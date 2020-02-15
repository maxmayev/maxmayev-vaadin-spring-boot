package com.maxmayev.application.backend.service;

import com.maxmayev.application.backend.domain.User;
import com.maxmayev.application.backend.dto.Registration;

public interface RegistrationService {
    public void register(Registration form);
}

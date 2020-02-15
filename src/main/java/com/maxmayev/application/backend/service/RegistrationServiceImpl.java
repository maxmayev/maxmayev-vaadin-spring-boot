package com.maxmayev.application.backend.service;

import com.maxmayev.application.backend.domain.User;
import com.maxmayev.application.backend.dto.Registration;
import com.maxmayev.application.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    UserRepository userRepository;
    PasswordEncoder encoder;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void register(Registration form) {
            userRepository.save(form.toUser(encoder));
    }
}

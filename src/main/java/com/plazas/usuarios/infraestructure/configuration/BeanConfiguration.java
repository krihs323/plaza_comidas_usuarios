package com.plazas.usuarios.infraestructure.configuration;

import com.plazas.usuarios.domain.api.IAuthenticationServicePort;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
import com.plazas.usuarios.domain.usercase.AuthenticationUserCase;
import com.plazas.usuarios.domain.usercase.UserUserCase;
import com.plazas.usuarios.infraestructure.output.jpa.adapter.UserJpaAdapter;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IUserRepository;
import com.plazas.usuarios.infraestructure.security.JwtService;
import com.plazas.usuarios.infraestructure.security.adapter.AuthenticationJpaAdapter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final HttpServletRequest httpServletRequest;

    public BeanConfiguration(IUserRepository userRepository, UserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, HttpServletRequest httpServletRequest) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.httpServletRequest = httpServletRequest;
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper, passwordEncoder, httpServletRequest, jwtService);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUserCase(userPersistencePort());
    }

    @Bean
    public IAuthenticationPersistencePort authenticationPersistencePort(){
        return new AuthenticationJpaAdapter(jwtService, userEntityMapper, authenticationManager);
    }

    @Bean
    public IAuthenticationServicePort authenticationServicePort(){
        return new AuthenticationUserCase(authenticationPersistencePort(), userPersistencePort());
    }

}

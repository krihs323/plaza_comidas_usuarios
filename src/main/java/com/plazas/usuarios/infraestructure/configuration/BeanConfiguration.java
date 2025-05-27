package com.plazas.usuarios.infraestructure.configuration;

import com.plazas.usuarios.domain.api.IAuthenticationServicePort;
import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
import com.plazas.usuarios.domain.usercase.AuthenticationUserCase;
import com.plazas.usuarios.domain.usercase.OwnerUserCase;
import com.plazas.usuarios.infraestructure.output.jpa.adapter.OwnerJpaAdapter;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.OwnerEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IOwnerRepository;
import com.plazas.usuarios.infraestructure.security.JwtService;
import com.plazas.usuarios.infraestructure.security.adapter.AuthenticationJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@RequiredArgsConstructor
public class BeanConfiguration {

    private final IOwnerRepository ownerRepository;
    private final OwnerEntityMapper ownerEntityMapper;

    private final PasswordEncoder passwordEncoder;

    //inyeccion de servicios para autenticacion
    //private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public BeanConfiguration(IOwnerRepository ownerRepository, OwnerEntityMapper ownerEntityMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Bean
    public IOwnerPersistencePort ownerPersistencePort(){
        return new OwnerJpaAdapter(ownerRepository, ownerEntityMapper);
    }

    @Bean
    public IOwnerServicePort ownerServicePort(){
        return new OwnerUserCase(ownerPersistencePort(), passwordEncoder);
    }

    //Autenticacion
    @Bean
    public IAuthenticationPersistencePort authenticationPersistencePort(){
        return new AuthenticationJpaAdapter(jwtService, ownerEntityMapper);
    }

    @Bean
    public IAuthenticationServicePort authenticationServicePort(){
        return new AuthenticationUserCase(authenticationManager,  ownerServicePort(), authenticationPersistencePort());
    }



}

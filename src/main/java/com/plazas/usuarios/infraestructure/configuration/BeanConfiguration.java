package com.plazas.usuarios.infraestructure.configuration;

import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
import com.plazas.usuarios.domain.usercase.OwnerUserCase;
import com.plazas.usuarios.infraestructure.output.jpa.adapter.OwnerJpaAdapter;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.OwnerEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@RequiredArgsConstructor
public class BeanConfiguration {

    private final IOwnerRepository ownerRepository;
    private final OwnerEntityMapper ownerEntityMapper;

    private final PasswordEncoder passwordEncoder;

    public BeanConfiguration(IOwnerRepository ownerRepository, OwnerEntityMapper ownerEntityMapper, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public IOwnerPersistencePort ownerPersistencePort(){
        return new OwnerJpaAdapter(ownerRepository, ownerEntityMapper);
    }

    @Bean
    public IOwnerServicePort ownerServicePort(){
        return new OwnerUserCase(ownerPersistencePort(), passwordEncoder);
    }
}

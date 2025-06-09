package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
import com.plazas.usuarios.infraestructure.output.jpa.entity.UserEntity;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IUserRepository;
import com.plazas.usuarios.infraestructure.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest httpServletRequest;
    private final JwtService jwtService;

    public UserJpaAdapter(IUserRepository userRepository, UserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder, HttpServletRequest httpServletRequest, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
        this.httpServletRequest = httpServletRequest;
        this.jwtService = jwtService;
    }


    @Override
    public void save(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public Optional<User> getRolFromUser(Long id) {
        Optional<UserEntity> userFound = userRepository.findById(id);
        if(userFound.isPresent()){
            User user =  userEntityMapper.toUser(userFound.get());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userFound = userRepository.findByEmail(email);
        if (userFound.isPresent()){
            return Optional.of(userEntityMapper.toUser(userFound.orElseThrow()));
        }
        return Optional.empty();
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public User getUseAuth() {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String jwtAuthorizationHeader = null;
        if (authorizationHeader.startsWith("Bearer ")) {
            jwtAuthorizationHeader = authorizationHeader.substring(7);
        }
        return jwtService.extractUser(jwtAuthorizationHeader);
    }

}

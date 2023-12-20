package com.a1st.banking.services.implementations;

import com.a1st.banking.Config.JwtUtils;
import com.a1st.banking.Validators.ObjectsValidator;
import com.a1st.banking.dto.AccountDto;
import com.a1st.banking.dto.AuthenticationResponse;
import com.a1st.banking.dto.UserDto;
import com.a1st.banking.models.Account;
import com.a1st.banking.models.Role;
import com.a1st.banking.models.User;
import com.a1st.banking.repositories.RoleRepository;
import com.a1st.banking.repositories.UserRepository;
import com.a1st.banking.services.AccountService;
import com.a1st.banking.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private final UserRepository userRepo;
    private final ObjectsValidator<UserDto> validator;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepo;

    @Override
    public Long save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepo.save(user);
        return saveUser.getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream()
                .map(UserDto::fromEntity) // u -> UserDto.fromEntity(u)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public UserDto findById(Long id) {
        return userRepo.findById(id).map(UserDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("No User was found with the provided ID" + id));
    }

    @Override
    public void delete(Long id) {
        // todo check before delete
        userRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Long validateAccount(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        if (user.getAccount() == null) {
            // create a bank account
            AccountDto account = AccountDto.builder()
                    .user(UserDto.fromEntity(user))
                    .build();
            var savedAccount = accountService.save(account);
            user.setAccount(
                    Account.builder()
                            .id(savedAccount)
                            .build()
            );
        }

        user.setActive(true);
        userRepo.save(user);
        return user.getId();

    }

    @Override
    public Long invalidateAccount(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation!"));
        user.setActive(false);
        userRepo.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String email = dto.getEmail();
        String roleName = email.endsWith("@a1st.hu") ? ROLE_ADMIN : ROLE_USER;
        user.setRole(findOrCreateRole(roleName));

//        user.setRole(
//                findOrCreateRole(ROLE_USER)
//        );
        var savedUser = userRepo.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstName() + " " + savedUser.getLastName());
        String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepo.findByName(roleName)
                .orElse(null);
        if (role == null) {
            return roleRepo.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }
}

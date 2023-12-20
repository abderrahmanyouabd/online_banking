package com.a1st.banking.Controllers;

import com.a1st.banking.Config.JwtUtils;
import com.a1st.banking.dto.AuthenticationRequest;
import com.a1st.banking.dto.AuthenticationResponse;
import com.a1st.banking.dto.UserDto;
import com.a1st.banking.models.User;
import com.a1st.banking.repositories.UserRepository;
import com.a1st.banking.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PUT})
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "authentication")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepo;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final User user = userRepo.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFirstName() + " " + user.getLastName());
        final String token = jwtUtils.generateToken(user, claims);
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(token)
                        .build()
        );
    }
}

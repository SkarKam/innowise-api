package com.innowise.kaminski.innowiseapi.controllers;

import com.innowise.kaminski.innowiseapi.models.Account;
import com.innowise.kaminski.innowiseapi.models.DTOs.LoginDTO;
import com.innowise.kaminski.innowiseapi.models.DTOs.RegisterDTO;
import com.innowise.kaminski.innowiseapi.repositories.UserRepository;
import com.innowise.kaminski.innowiseapi.utils.JWTUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JWTUtil jwtUtil;

    @Value("${security.jwt.secret-key}")
    private String SECRET;

    @Value("${security.jwt.expiration-time}")
    private Long EXPIRATIONTIME;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTUtil jwtUtil) {
        //this.authenticationProvider = authenticationProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        try {
            Account account = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new BadCredentialsException("Invalid username"));

            if(!bCryptPasswordEncoder.matches(loginDTO.getPassword(), account.getPassword())) {
                throw new BadCredentialsException("Invalid password or username");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.generateToken(loginDTO.getUsername());

            return ResponseEntity.ok("Bearer " + token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!");
        }
        String password = bCryptPasswordEncoder.encode(registerDTO.getPassword());

        Account account = new Account(registerDTO.getUsername(), password);

        userRepository.save(account);

        return ResponseEntity.ok("User registered successfully!");
    }
}

package com.victornsto.restwithspringbootandjava.controller;

import com.victornsto.restwithspringbootandjava.controller.docs.AuthControllerDocs;
import com.victornsto.restwithspringbootandjava.dto.v1.security.AccountCredentialsDto;
import com.victornsto.restwithspringbootandjava.dto.v1.security.TokenDto;
import com.victornsto.restwithspringbootandjava.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {
    private final AuthService authService;

    @PostMapping("/signin")
    @Override
    public ResponseEntity<?> singIn(@RequestBody AccountCredentialsDto credentials) {
        if (validationCredentials(credentials)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        ResponseEntity<TokenDto> token = authService.singIn(credentials);
        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid client request");
        return token;
    }

    @PutMapping("/refresh/{username}")
    @Override
    public ResponseEntity<?> refreshToken(@PathVariable String username,
                                          @RequestHeader("Authorization") String refreshToken) {
        if (parametersAreInvalid(username, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        ResponseEntity<TokenDto> token = authService.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid client request");
        return token;
    }

    @PostMapping(value = "/createUser",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public AccountCredentialsDto create(@RequestBody AccountCredentialsDto user) {
        return authService.create(user);
    }

    private boolean parametersAreInvalid(String username, String refreshToken) {
        return StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken);
    }

    private static boolean validationCredentials(AccountCredentialsDto credentials) {
        return credentials == null ||
                StringUtils.isEmpty(credentials.getUsername()) ||
                StringUtils.isEmpty(credentials.getPassword());
    }

}

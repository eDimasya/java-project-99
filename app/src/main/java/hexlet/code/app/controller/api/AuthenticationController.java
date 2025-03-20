package hexlet.code.app.controller.api;

import hexlet.code.app.dto.user.AuthRequest;
import hexlet.code.app.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public String create(@RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authentication);
        return jwtUtils.generateToken(request.getUsername());
    }
}

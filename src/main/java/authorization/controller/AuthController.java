package authorization.controller;

import authorization.dto.AuthMessage;
import authorization.dto.CreateUserDto;
import authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto dto){
        System.out.println("inside method");
        AuthMessage user = userService.createUser(dto);
        return ResponseEntity.status(user.status()).body(user.message());
    }
}

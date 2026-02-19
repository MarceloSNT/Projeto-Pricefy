package project.pricefy.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.pricefy.dto.request.UsersRequestDto;
import project.pricefy.dto.response.UsersResponseDto;
import project.pricefy.model.UserModel;
import project.pricefy.repository.UserRepository;
import project.pricefy.service.UserService;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("pricefy/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersResponseDto> register(@Valid @RequestBody UsersRequestDto usersRequestDto) {
        UsersResponseDto user = userService.createUser(usersRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}

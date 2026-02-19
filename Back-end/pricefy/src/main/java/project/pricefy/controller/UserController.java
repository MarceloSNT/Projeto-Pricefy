package project.pricefy.controller;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.pricefy.dto.response.UsersResponseDto;
import project.pricefy.service.UserService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("pricefy/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/findEmail/{dsEmail}")
    public Optional<UsersResponseDto> findByEmail(@PathVariable String dsEmail) {
        return userService.ListByDsEmail(dsEmail);
    }

    @GetMapping("/findUsername/{username}")
    public Optional<UsersResponseDto> findByUsername(@PathVariable String username) {
        return userService.ListByUsername(username);
    }
}

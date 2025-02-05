package com.challenge.techforb.User.UserEntity;

import com.challenge.techforb.User.UserEntity.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/techforb/user")
@RequiredArgsConstructor
@CrossOrigin //agregar el value cuando tenga la ruta
public class UserController {

    private final UserService userService;
    @GetMapping(value = "{id}") //authenticated
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
        UserDTO userDTO = userService.getUser(id);
        if(userDTO==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userDTO);
    }
}

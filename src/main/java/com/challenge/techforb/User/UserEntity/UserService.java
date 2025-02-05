package com.challenge.techforb.User.UserEntity;

import com.challenge.techforb.User.UserEntity.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUser(Integer id){
        UserEntity user=userRepository.findById(id).orElse(null);
        if(user!=null){
            return UserDTO.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        }
        return null;
    }
}

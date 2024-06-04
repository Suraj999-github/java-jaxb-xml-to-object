package crud.demo.auth.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import crud.demo.auth.dtos.UserDto;
import crud.demo.auth.models.Role;
import crud.demo.auth.models.User;
import crud.demo.auth.repositories.RoleRepository;
import crud.demo.auth.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail()); 
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        //userRepository.save(user);
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role==null){
            role=checkRoleExists();
        }
        user.setRoles(Arrays.asList(role));  
        //As we have list of roles field in user checking any role in db exists or not if not creating by a private function and saving it in db
        userRepository.save(user); 
        // now saving user to db.

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> user_dto = new ArrayList<>();
        for(User user:users){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            user_dto.add(userDto);
        }
        return user_dto;
    }

    private Role checkRoleExists(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}

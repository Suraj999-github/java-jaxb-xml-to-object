package crud.demo.auth.services;

import java.util.List;

import crud.demo.auth.dtos.UserDto;
import crud.demo.auth.models.User;

public interface UserService {
	   void saveUser(UserDto userDto);

	    User findByEmail(String email);

	    List<UserDto>findAllUsers();
}

package crud.demo.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import crud.demo.auth.dtos.UserDto;
import crud.demo.auth.models.User;
import crud.demo.auth.services.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;

	// handler methods for handling login request.
	// @GetMapping("/login")
	@RequestMapping(value = { "/", "login" }, method = RequestMethod.GET)
	public String login() {
		return "views/auth/login";
	}

	@GetMapping("/user/register")
	public String showRegistrationForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto); // model object is used to store data that is entered from form.
		return "views/auth/register";
	}
	
    @PostMapping("/user/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,Model model){ 
    	//Model attribute is used to extract model object which is a form data.

        User existingUser = userService.findByEmail(userDto.getEmail()); //checking if entered email already exists or not.

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"there is already an account existed with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/views/auth/register"; // if any form has errors it will be redirected to register page only.
        }

        userService.saveUser(userDto);
        return "redirect:/user/register?success"; // @Valid from jakarta.validation will enable the validation fields of dto objectsto be enabled.
    }
    //handler methods for getting list of users.
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "views/auth/users";
    }
}

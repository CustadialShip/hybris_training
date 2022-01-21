package org.training.web.controllers;

import de.hybris.platform.jalo.user.User;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController{
    private static final String MESSAGE = "message";
    private static final String ERROR = "error";
    public static final String USER = "user";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String getUser(@RequestParam String id, Model model){
        UserModel userModel = userService.getUserForUID(id);
        if (userModel != null){
            model.addAttribute(USER, userModel);
        } else {
            model.addAttribute(MESSAGE, ERROR);
        }
        return USER;
    }
}

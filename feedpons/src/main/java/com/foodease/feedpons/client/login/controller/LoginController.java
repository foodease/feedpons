package com.foodease.feedpons.client.login.controller;

import com.foodease.feedpons.client.login.model.User;
import com.foodease.feedpons.client.login.repository.RoleRepository;
import com.foodease.feedpons.client.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }


    @GetMapping(value = "/default_home")
    public String defaultAfterLogin(HttpServletRequest request) {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (role.equals("[Client]")) {
            return "redirect:/client/home";
        } else if (role.equals("[Donor]")) {
            return "redirect:/donor/home";
        } else {
            return "redirect:/restaurant/home";
        }

    }


    @GetMapping(value = "/registration/client")
    public ModelAndView clientRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/client/client_registration");
        return modelAndView;
    }

    @GetMapping(value = "/registration/donor")
    public ModelAndView donorRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/donor/donor_registration");
        return modelAndView;
    }

    @GetMapping(value = "/registration/restaurant")
    public ModelAndView restaurantRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/restaurant/restaurant_registration");
        return modelAndView;
    }


    @PostMapping(value = "/registration/client")
    public ModelAndView createNewClient(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/client/client_registration");
        } else {
            userService.saveUser(user, "Client");
            modelAndView.addObject("successMessage", "Client has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/client/client_registration");

        }
        return modelAndView;
    }


    @PostMapping(value = "/registration/donor")
    public ModelAndView createNewDonor(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/donor/donor_registration");
        } else {
            userService.saveUser(user, "Donor");
            modelAndView.addObject("successMessage", "Donor has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("donor/donor_registration");

        }
        return modelAndView;

    }

    @PostMapping(value = "/registration/restaurant")
    public ModelAndView createNewRestaurant(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/restaurant/restaurant_registration");
        } else {
            userService.saveUser(user, "Restaurant");
            modelAndView.addObject("successMessage", "Restaurant has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/restaurant/restaurant_registration");

        }
        return modelAndView;
    }

    @GetMapping(value = "/client/home")
    public ModelAndView clientHome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + " | " + user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("tokenCount", "Tokens Available: 30");
        modelAndView.setViewName("/client/client_home");
        return modelAndView;
    }

    @GetMapping(value = "/donor/home")
    public ModelAndView donorHome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<User> restaurant = userService.findUsersByRole(3);
        System.out.println("🔥🔥🔥🔥" + restaurant);
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("restaurant", restaurant);
        modelAndView.setViewName("/donor/donor_home");
        return modelAndView;
    }

    @PostMapping(value="/donor/home")
    public ModelAndView getVal(@RequestParam("qr") String qr){
        String menu = "";
        if (qr.equals("457983476897438")){
            menu = "Double Whooper";
        } else if (qr.equals("60360348605910")){
            menu = "Footlong Sub";
        } else{
            menu = "Invalid Code";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        List<User> restaurant = userService.findUsersByRole(3);
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("restaurant", restaurant);
        modelAndView.addObject("menu", "Meal: "+menu);
        modelAndView.setViewName("/donor/donor_home");
        System.out.println(menu);
        return modelAndView;
    }

    @GetMapping(value = "/restaurant/home")
    public ModelAndView restaurantHome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + " | " + user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Restaurants");
        modelAndView.setViewName("/restaurant/restaurant_home");
        return modelAndView;
    }


}

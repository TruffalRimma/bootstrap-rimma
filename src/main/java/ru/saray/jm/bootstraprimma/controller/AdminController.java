package ru.saray.jm.bootstraprimma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.saray.jm.bootstraprimma.model.Role;
import ru.saray.jm.bootstraprimma.model.User;
import ru.saray.jm.bootstraprimma.repository.RoleRepository;
import ru.saray.jm.bootstraprimma.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String adminPage(Model model, Authentication authentication) {
        // for navbar and user-information page
        UserDetails user = (UserDetails) authentication.getPrincipal();
        User admin = userService.loadUserByUsername(user.getUsername());
        model.addAttribute("admin", admin);

        //All users
        model.addAttribute("users", userService.getUsers());

        //for newUser form
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "admin";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(User user, HttpServletRequest request) {
        Set<Role> roles = new HashSet<>();
        String[] roles1 = request.getParameterValues("roles2");
        for (String role : roles1) {
            if (role.equals("adminOption2")) {
                roles.add(roleRepository.findById(2L).get());
            } else if (role.equals("userOption2")) {
                roles.add(roleRepository.findById(1L).get());
            }
        }
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("update") User user, HttpServletRequest request) {
        Set<Role> roles = new HashSet<>();
        String[] roles1 = request.getParameterValues("roles3");
        for (String role : roles1) {
            if (role.equals("adminOption")) {
                roles.add(roleRepository.findById(2L).get());
            } else if (role.equals("userOption")) {
                roles.add(roleRepository.findById(1L).get());
            }
        }
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.getUserByID(id);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute("delete") User user, HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id1"));
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
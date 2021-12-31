package com.maitri.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.maitri.model.JwtRequest;
import com.maitri.model.User;
import com.maitri.service.JwtService;
import com.maitri.service.UserService;



@Controller
public class UserController {
	 @Autowired
	    private UserService userService;
	 @Autowired
	 private JwtController jwtController;
@Autowired
private JwtService jwtService;
	    @PostConstruct
	    public void initRoleAndUser() {
	        userService.initRoleAndUser();
	    }

	    @PostMapping({"/registerNewUser"})
	    public User registerNewUser(@RequestBody User user) {
	        return userService.registerNewUser(user);
	    }

	    @GetMapping({"/forAdmin"})
	    @PreAuthorize("hasRole('Admin')")
	    public String forAdmin(){
	        return "This URL is only accessible to the admin";
	    }

	   
	    @GetMapping({"/forUser"})
	    @PreAuthorize("hasRole('User')")
	    public String forUser(){
	        return "This URL is only accessible to the user";
	    }
	    @GetMapping("/signup")
	    public String showIndexPage(Model model) {
	    	model.addAttribute("command", new User());
	    	return "index.jsp";
	    }
	    @RequestMapping(value = "/register", method = RequestMethod.POST)
		public String saveUser(@ModelAttribute("adduser") User user) throws Exception {
			userService.registerNewUser(user);

			return "redirect:/login";
			
		}
	    @GetMapping("/login")
	    public String login(Model model) {
	    	jwtController.jwtToken="";
	    	model.addAttribute("command", new JwtRequest());
	    	
	    	return "login.jsp";
	    }
	    
	    @RequestMapping("/readuser")
	   
	    public String showReadContactPage(Model model) {
	        model.addAttribute("users1", userService.findAll());
	        return "readuser.jsp";
	    }
	    @RequestMapping(value = "/delete/{id}")
	    @PreAuthorize("hasRole('Admin')")
	    public  String deleteContact(@PathVariable String id) {
  
	        userService.deleteByUsername(id);
	 
	        return "redirect:/readuser";
	    }
	    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	    @PreAuthorize("hasRole('Admin')")
	    public  String updateContact(@PathVariable String id, @ModelAttribute("adduser") User user) throws Exception {
	       User u1= userService.updateUser(id, user);
	    
	       return "redirect:/readuser";
	    }
	    @RequestMapping(value = "/updateuser/{id}"  )
	    @PreAuthorize("hasRole('Admin')")
	    public String showUpdateContactPage(@PathVariable String id,Model model,HttpServletResponse response) {
	        model.addAttribute("id", id);
	      
	       model.addAttribute("command", userService.findById(id).orElse(null));
	    //  response.addHeader("Authorization", jwtAuthenticationController.jwtToken);
	      
	        return "updateuser.jsp";
	    }
}

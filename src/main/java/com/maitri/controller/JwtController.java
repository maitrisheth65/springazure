package com.maitri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maitri.model.JwtRequest;
import com.maitri.model.JwtResponse;
import com.maitri.service.JwtService;

@Controller
@CrossOrigin
public class JwtController {

	 @Autowired
	    private JwtService jwtService;
	 public String jwtToken;
	    @PostMapping("/authenticate")
	    public String createJwtToken(@ModelAttribute("jwt") JwtRequest jwtRequest) throws Exception {
	   
	        JwtResponse jwtResponse= jwtService.createJwtToken(jwtRequest);
	        jwtToken=jwtResponse.getJwtToken();
			   
			   
			 jwtToken ="Bearer " +jwtResponse.getJwtToken();
		
	        return "redirect:/readuser";
	    }
}

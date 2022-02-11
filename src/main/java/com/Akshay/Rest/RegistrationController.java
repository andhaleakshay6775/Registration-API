package com.Akshay.Rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.Akshay.Service.RegistrationService;
import com.Akshay.bindding.User;

@RestController
public class RegistrationController {
     
	  @Autowired
	  RegistrationService regService;
	  @GetMapping("/emailcheck/{email}")
	public String checkEmail(@PathVariable String email) {
		boolean uniqueEmail = regService.uniqueEmail(email);
		if(uniqueEmail) {
			return "UNIQUE";
	
		}
		return "DPLICATE";
	}
	
	  
	  @GetMapping("/countries")
	  public Map<Integer, String> getCountries(){
		
		  Map<Integer, String> countries = regService.getCountries();
		     
		  
		  return countries;
		  
	  }
	  @GetMapping("/states/{countryId}")
	  public Map<Integer, String> getStates(@PathVariable Integer countryId){
		return regService.getStates(countryId);
		  
	  }
	  
	  @GetMapping("/cities/{stateId}")
	  public Map<Integer, String> getCities(@PathVariable Integer stateId){
		return regService.getStates(stateId);
		  
	  }
	  @PostMapping("/saveuser")
	  public String saveUser(@RequestBody User user) {
		  
		  boolean registerUser = regService.registerUser(user);
		  
		  if(registerUser) {
			  
		return "SUCCESS";	
		}else {
			return "FAIL";
		}
		  
	  }
}

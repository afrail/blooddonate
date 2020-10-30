package com.shopnobazz.blooddoante.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopnobazz.blooddoante.Repository.AddressRepository;
import com.shopnobazz.blooddoante.Repository.UserRepository;
import com.shopnobazz.blooddoante.domain.Address;
import com.shopnobazz.blooddoante.domain.User;
import com.shopnobazz.blooddoante.service.AddressService;
import com.shopnobazz.blooddoante.service.UserService;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserService userService;
	@Autowired
	AddressService addressService;
	@Autowired
	UserRepository userRepository;


	@RequestMapping("/home")
	public String addAddress(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		// Address address=addressRepository.findByUser(user);
		// Address address=new Address();
		model.addAttribute("user", user);
		// model.addAttribute("address",address);
		// model.addAttribute("classAcctiveProfile",true);

		return "profile2";
	}

	@RequestMapping("/updateaddress")
	public String updateAddress(@RequestParam("id") Long id, Model model) {

		Address address = addressService.find(id);
		model.addAttribute("address", address);
		return "profile";

	}

	@RequestMapping(value = "/addNewAddress", method = RequestMethod.POST)
	public String addNewShippingAddress(@ModelAttribute("address") Address address, Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
//		addressRepository.findByUser(user);
		 // user.getAddress().getId();
		System.out.println("==========================");
		System.out.println(user.getId());
		System.out.println(address.getId());
         addressService.createAddress(address,user);
		//addressRepository.save(address);

		model.addAttribute("user", user);

		return "profile2";
	}
	
	@RequestMapping("/infoupdate")
	public String updatepro(Principal principal, Model model) {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "profile1";

	}
	
	@RequestMapping(value="/infoupdate",method = RequestMethod.POST)
	public String updateprofile(@ModelAttribute("user") User user,Model model,Principal principal) {
		User user1 = userService.findByUsername(principal.getName());
		     user1.setUsername(user.getUsername());
	        user1.setEmail(user.getEmail());
	        user1.setFirstName(user.getFirstName());
	        user1.setLastName(user.getLastName());
	        user1.setPhone(user.getPhone());
	        user1.setBloodGroup(user.getBloodGroup());
	        user1.setEnabled(true);
	        user1.setPassword(user.getPassword());
		userRepository.save(user1);
		model.addAttribute("user", user1);
		
		
		return "profile2";	
	}

}

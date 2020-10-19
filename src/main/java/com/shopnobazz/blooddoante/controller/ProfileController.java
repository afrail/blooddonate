package com.shopnobazz.blooddoante.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopnobazz.blooddoante.Repository.AddressRepository;
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

}

package com.shopnobazz.blooddoante.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String addAddress(
            Model model, Principal principal
    ){
        User user = userService.findByUsername(principal.getName());
        List<Address> address=addressRepository.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("address",address);
        model.addAttribute("classAcctiveProfile",true);
        return "profile2";
    }
    
    @RequestMapping(value = "/addNewAddress", method = RequestMethod.POST)
    public String addNewShippingAddress(
            @ModelAttribute("address") Address address,
            Principal principal, Model model
    ){
        User user = userService.findByUsername(principal.getName());
        
        
        addressService.createAddress(address, user);

        model.addAttribute("user", user);
//        model.addAttribute("userPaymentList", user.getUserPaymentList());
//        model.addAttribute("userShippingList", user.getUserShippingList());
//        model.addAttribute("listOfCreditCards", true);
//        model.addAttribute("classActiveShipping", true);
//        model.addAttribute("listOfShippingAddresses", true);
       // model.addAttribute("orderList", user.getOrderList());

        return "profile";
    }
    
}

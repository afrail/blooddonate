package com.shopnobazz.blooddoante.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.shopnobazz.blooddoante.Repository.PasswordResetTokenRepository;
import com.shopnobazz.blooddoante.Repository.UserRepository;
import com.shopnobazz.blooddoante.domain.Address;
import com.shopnobazz.blooddoante.domain.User;
import com.shopnobazz.blooddoante.domainsecurity.PasswordResetToken;
import com.shopnobazz.blooddoante.domainsecurity.Role;
import com.shopnobazz.blooddoante.domainsecurity.UserRole;
import com.shopnobazz.blooddoante.service.AddressService;
import com.shopnobazz.blooddoante.service.UserService;
import com.shopnobazz.blooddoante.utility.MailConstructor;
import com.shopnobazz.blooddoante.utility.SecurityUtility;
@Controller
public class HomeController {
    @Autowired
    AddressService addressService; 
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
    {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }
    

	

	  @RequestMapping(value = "/register", method = RequestMethod.POST)
	    public String newUserPost(
	            HttpServletRequest request,
	            //@ModelAttribute("email") String userEmail,
	            @ModelAttribute("user") User user,
	            Model model
	    ) throws Exception {
	      
	        if(userService.findByUsername(user.getUsername()) != null) {
	            model.addAttribute("usernameExists", true);

	            return "login";
	        }
	        model.addAttribute("classActiveNewAccount", true);
	        model.addAttribute("email", user.getEmail());
	        model.addAttribute("username", user.getUsername());


	        if(userService.findByEmail(user.getEmail()) != null) {
	            model.addAttribute("emailExists", true);

	            return "login";
	        }

	        User user1 = new User();
	        user1.setUsername(user.getUsername());
	        user1.setEmail(user.getEmail());
	        user1.setFirstName(user.getFirstName());
	        user1.setLastName(user.getLastName());
	        user1.setPhone(user.getPhone());
	        user1.setBloodGroup(user.getBloodGroup());
	        String password = user.getPassword();
	        //String password = SecurityUtility.randomPassword();
	        String encryptedPassword =SecurityUtility.passwordEncoder().encode(password); 
	        user1.setPassword(encryptedPassword);
	        Role role = new Role();
	        role.setRoleId(1);
	        role.setName("ROLE_USER");
	        Set<UserRole> userRoles = new HashSet<>();
	        userRoles.add(new UserRole(user1, role));
	        userService.createUser(user1, userRoles);
	        String token = UUID.randomUUID().toString();
	        userService.createPasswordResetTokenForUser(user1, token);
	        
           
	        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();


	        SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user,password,encryptedPassword);

	        mailSender.send(email);

	        model.addAttribute("emailSent", "true");
	        

	       
	        return "login";
	    }
	  @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
	    {
		  PasswordResetToken token = passwordResetTokenRepository.findByToken(confirmationToken);

	        if(token != null)
	        {
	            User user1 = userRepository.findByEmail(token.getUser().getEmail());
	            user1.setEnabled(true);
	            userRepository.save(user1);
	            modelAndView.setViewName("accountVerified");
	        }
	        else
	        {
	            modelAndView.addObject("message","The link is invalid or broken!");
	            //modelAndView.setViewName(" accountVerifi failed ");
	        }

	        return modelAndView;
	    } 
	  
	 
	
}

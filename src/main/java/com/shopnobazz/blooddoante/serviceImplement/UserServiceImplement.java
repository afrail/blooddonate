package com.shopnobazz.blooddoante.serviceImplement;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopnobazz.blooddoante.Repository.PasswordResetTokenRepository;
import com.shopnobazz.blooddoante.Repository.RoleRepository;
import com.shopnobazz.blooddoante.Repository.UserRepository;
import com.shopnobazz.blooddoante.domain.Address;
import com.shopnobazz.blooddoante.domain.User;
import com.shopnobazz.blooddoante.domainsecurity.PasswordResetToken;
import com.shopnobazz.blooddoante.domainsecurity.UserRole;
import com.shopnobazz.blooddoante.service.UserService;
@Service
public class UserServiceImplement implements UserService{
@Autowired
UserRepository userRepository;

@Autowired
RoleRepository roleRepository;

@Autowired
PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		 User localUser = userRepository.findByUsername(user.getUsername());

	        if(localUser != null) {
	           // LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
	        } else {
	            for (UserRole ur : userRoles) {
	                roleRepository.save(ur.getRole());
	            }

	            user.getUserRoles().addAll(userRoles);
	            user.setEnabled(true);
	            Address address = new Address();
	            user.setAddress(address);
	            address.setUser(user);
	            
	            localUser = userRepository.save(user);
	            
//	            MultipartFile userImage = user.getUsrImage();
//
//	            try {
//	                byte[] bytes = userImage.getBytes();
//	                String name = user.getId() + ".png";
//	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/user/" + name)));
//	                stream.write(bytes);
//	                stream.close();
//
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }

	            
	            
	            
	            
	            
	            
	            
	            
	            
	        }

	        return localUser;
	}
	 @Override
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

	    @Override
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
		@Override
		public void createPasswordResetTokenForUser(User user, String token) {
			 final PasswordResetToken myToken = new PasswordResetToken(token, user);
		        passwordResetTokenRepository.save(myToken);
			
		}
		@Override
		public String updateinofo(User user) {
			
			userRepository.save(user);
			return null;
		}
		

}

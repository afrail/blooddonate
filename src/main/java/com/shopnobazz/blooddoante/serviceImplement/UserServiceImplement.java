package com.shopnobazz.blooddoante.serviceImplement;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopnobazz.blooddoante.Repository.PasswordResetTokenRepository;
import com.shopnobazz.blooddoante.Repository.RoleRepository;
import com.shopnobazz.blooddoante.Repository.UserRepository;
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
	             
	            localUser = userRepository.save(user);
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

}

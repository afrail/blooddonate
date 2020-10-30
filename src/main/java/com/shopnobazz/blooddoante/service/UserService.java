package com.shopnobazz.blooddoante.service;

import java.util.Set;

import com.shopnobazz.blooddoante.domain.User;
import com.shopnobazz.blooddoante.domainsecurity.UserRole;



public interface UserService {
	User findByUsername(String username);
	void createPasswordResetTokenForUser(final User user, final String token);
    User findByEmail(String email);
    User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public String updateinofo(User user);
}

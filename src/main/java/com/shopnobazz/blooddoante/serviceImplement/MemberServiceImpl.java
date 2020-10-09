package com.shopnobazz.blooddoante.serviceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopnobazz.blooddoante.Repository.UserRepository;
import com.shopnobazz.blooddoante.domain.User;
import com.shopnobazz.blooddoante.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {
@Autowired
UserRepository userRepository;
	@Override
	public List<User> findAll() {
		List<User> userList= (List<User>) userRepository.findAll();
		List<User> acctiveUser = new ArrayList<>();
		for(User user : userList) {
			
				acctiveUser.add(user);
			
			
		}
		return acctiveUser;
	}

}

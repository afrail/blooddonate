package com.shopnobazz.blooddoante.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopnobazz.blooddoante.Repository.AddressRepository;
import com.shopnobazz.blooddoante.domain.Address;
import com.shopnobazz.blooddoante.domain.User;
import com.shopnobazz.blooddoante.service.AddressService;
@Service

public class AddressServiceImpl implements AddressService{
@Autowired 
AddressRepository addressRepository;
	@Override
	public Address createAddress(Address address,User user
			) {
		user.setAddress(address);
		address.setUser(user);
		
		return addressRepository.save(address) ;
	}
	@Override
	public Address find(Long id) {
		return addressRepository.findById(id).get();
	}

	
		


}

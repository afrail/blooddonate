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
	public Address createAddress(Address address,
			User user) {
		
		Address address1= new Address();
		address1.setDistric(address.getDistric());
		address1.setDivision(address.getDivision());
		address1.setOthers(address.getOthers());
		address1.setUpazila(address.getUpazila());
		address1.setType(address.getType());
		address1.setUser(user);
		
		return addressRepository.save(address1) ;
	}

	
		


}

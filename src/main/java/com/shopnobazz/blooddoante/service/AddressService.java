package com.shopnobazz.blooddoante.service;

import com.shopnobazz.blooddoante.domain.Address;
import com.shopnobazz.blooddoante.domain.User;

public interface AddressService {
  
Address createAddress(Address address,User user);
Address find(Long id);


}

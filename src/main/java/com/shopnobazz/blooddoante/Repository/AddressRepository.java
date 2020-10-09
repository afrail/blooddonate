package com.shopnobazz.blooddoante.Repository;

import org.springframework.data.repository.CrudRepository;

import com.shopnobazz.blooddoante.domain.Address;
import com.shopnobazz.blooddoante.domain.User;

public interface AddressRepository extends CrudRepository<Address, Long>{
Address findByUser(User user);

}

package com.shopnobazz.blooddoante.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shopnobazz.blooddoante.domain.Address;
import com.shopnobazz.blooddoante.domain.User;


public interface AddressRepository extends CrudRepository<Address, Long>{
List<Address> findByUser(User user);

}

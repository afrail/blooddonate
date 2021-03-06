package com.shopnobazz.blooddoante.Repository;

import org.springframework.data.repository.CrudRepository;
import com.shopnobazz.blooddoante.domainsecurity.Role;
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}


package com.k001.selfgroth.registrationactivate.persistence.dao;

import com.k001.selfgroth.registrationactivate.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}

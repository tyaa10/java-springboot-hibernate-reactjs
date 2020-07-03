package org.tyaa.demo.springboot.simplespa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tyaa.demo.springboot.simplespa.entity.Role;

@Repository
public interface RoleHibernateDAO extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}

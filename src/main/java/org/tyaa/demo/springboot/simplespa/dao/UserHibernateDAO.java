package org.tyaa.demo.springboot.simplespa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tyaa.demo.springboot.simplespa.entity.User;

@Repository
public interface UserHibernateDAO extends JpaRepository<User, Long> {
    User findUserByName(String name);
}

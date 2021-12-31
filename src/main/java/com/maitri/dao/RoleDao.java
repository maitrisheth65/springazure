package com.maitri.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maitri.model.Role;
@EnableJpaRepositories
@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}

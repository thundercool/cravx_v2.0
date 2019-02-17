package com.astrika.abg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.astrika.abg.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query("select role from Role role where role.roleId=?1 ")
	Role findById(Long roleId);
	
	@Query("select role from Role role where role.name like %:roleName% ")
	Role findByRolesName(@Param("roleName") String roleName);

	@Query("select role from Role role where role.name=?1")
	Role findByName(String name);
}

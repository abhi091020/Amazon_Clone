package com.example.amazonreplica.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.amazonreplica.dto.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	Admin getByAdminEmail(String email);

	Admin getByToken(String token);

}

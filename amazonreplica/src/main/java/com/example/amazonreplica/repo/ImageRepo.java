package com.example.amazonreplica.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.amazonreplica.dto.Image;

public interface ImageRepo extends JpaRepository<Image, Integer>{
	
	Image getById(Integer id);

}

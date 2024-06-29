package com.example.amazonreplica.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.amazonreplica.dto.Image;
import com.example.amazonreplica.repo.ImageRepo;

@Service
public class FileService {
	
	@Autowired
	private ImageRepo iRepo;

	public List<Image> uploadFiles(String path,MultipartFile[] files) {
		
		List<Image> paths = new ArrayList<Image>();
		for (int i = 0; i < files.length; i++) {
			if (files[0].getSize() == 0) {
				continue;
			}
			String name=files[i].getOriginalFilename();
			String randomId = UUID.randomUUID().toString();
			System.out.println("jiiii");
			String filePath = randomId.concat(name.substring(name.lastIndexOf(".")));
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			
			try {
				long l =Files.copy(files[i].getInputStream(),Paths.get(path+filePath));
				System.out.println(l);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image j = new Image();
			j.setPath("images/"+filePath);
			paths.add(j);
		}
		return paths;
		
	}

	public boolean remove(Integer id) {
		// TODO Auto-generated method stub
		
		
			File f = new File(iRepo.getById(id).getPath());
			System.out.println("done");
		     if(f.exists()) {
		    	 f.delete();
		     }
		return true;
		
	}
}

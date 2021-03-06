package com.aloespirita.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aloespirita.upload.UploadConfig;


@Service
public class UploadService {

	@Autowired
	private UploadConfig uploadConfig;

	public void uploadThumbnail(MultipartFile file) throws IOException {
		StringBuilder path = new StringBuilder(uploadConfig.getSource());
		path.append(File.separator).append(StringUtils.cleanPath(file.getOriginalFilename()));

		Path source = Paths.get(path.toString());

		Files.copy(file.getInputStream(), source, StandardCopyOption.REPLACE_EXISTING);

	}
	
	public byte[] downloadThumbnail(String originalName) throws IOException {
		StringBuilder path = new StringBuilder(uploadConfig.getSource());
		path.append(File.separator).append(originalName);
		Path source = Paths.get(path.toString());

		return Files.readAllBytes(source);
	}
}

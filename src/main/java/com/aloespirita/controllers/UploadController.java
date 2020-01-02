package com.aloespirita.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aloespirita.services.UploadService;
import com.aloespirita.upload.UploadConfig;

@RestController
public class UploadController {
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private UploadConfig uploadConfig;
	
	private Logger logger = LoggerFactory.getLogger(UploadController.class);

	@GetMapping("/upload/{originalName}")
	public ResponseEntity<?> getThumbnail(@PathVariable String originalName) throws IOException {
		byte[] thumbnail = this.uploadService
				.downloadThumbnail(originalName);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(thumbnail);
	}
	
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> upload(@RequestParam("thumb") MultipartFile thumb){
		try {
			this.uploadService.uploadThumbnail(thumb);
			String virtualAccess = uploadConfig.getRootVirtualAddress()
					.concat(thumb.getOriginalFilename());
			return new ResponseEntity<String>(virtualAccess, HttpStatus.OK);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}

package com.learning.app2.rest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.app2.model.FileUpload;
import com.learning.app2.restcall.App2FileRestCall;

@RestController
@RequestMapping("/api/app/file")
public class App2FileController {

	@Autowired
	private App2FileRestCall restCall;

	@PostMapping("/upload")
	public ResponseEntity<ByteArrayResource> fileUpload(@RequestParam("file") MultipartFile multipartFile)
			throws IOException {
		FileUpload fileUpload1 = new FileUpload();
		fileUpload1.setFileName(multipartFile.getOriginalFilename());
		fileUpload1.setFileType(multipartFile.getContentType());
		fileUpload1.setFileData(multipartFile.getBytes());
		FileUpload fileUpload = restCall.uploadFile(fileUpload1);
		if (fileUpload == null) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileUpload.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUpload.getFileName() + "\"")
				.body(new ByteArrayResource(fileUpload.getFileData()));

	}

	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> fileDownload() {
		FileUpload fileUpload = restCall.downloadFile();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileUpload.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUpload.getFileName() + "\"")
				.body(new ByteArrayResource(fileUpload.getFileData()));

	}

	@GetMapping("/downloadRest")
	public ResponseEntity<ByteArrayResource> fileDownlaodRest() {
		FileUpload fileUpload = restCall.downloadByte();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileUpload.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUpload.getFileName() + "\"")
				.body(new ByteArrayResource(fileUpload.getFileData()));

	}

}

package com.learning.app2.restcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.learning.app2.model.FileUpload;
import com.learning.app2.util.App2Constants;
import com.learning.app2.util.App2ServiceUrls;

@Component
public class App2FileRestCall {

	@Autowired
	private App2ServiceUrls app2ServiceUrls;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * This Method for pass MultiPartFile and return byte[]
	 * 
	 * @param multipartFile
	 * @return
	 */
	public byte[] uploadFileMulipartParam(MultipartFile multipartFile) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(app2ServiceUrls.getBaseUrl()).append(App2Constants.APP1_FILE_URL)
				.append(App2Constants.BACK_SLASH).append("upload");
		String url = String.valueOf(urlBuilder);
		HttpEntity<MultipartFile> request = new HttpEntity<>(multipartFile);
		byte[] fileData = restTemplate.postForObject(url, request, byte[].class);
		if (fileData != null) {
			return fileData;
		}
		return new byte[0];
	}

	/**
	 * Rest Call will returns Image
	 * 
	 * @return
	 */
	public FileUpload downloadByte() {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(app2ServiceUrls.getBaseUrl()).append(App2Constants.APP1_FILE_URL)
				.append(App2Constants.BACK_SLASH).append("download");
		String url = String.valueOf(urlBuilder);
		byte[] fileUpload = restTemplate.getForObject(url, byte[].class);
		if (fileUpload != null) {
			FileUpload fileData = new FileUpload();
			fileData.setFileData(fileUpload);
			fileData.setFileType("application/jpg");
			fileData.setFileName("PCM");
			return fileData;
		}
		return null;
	}

	/**
	 * This Rest call loads data and return FileUpload
	 * 
	 * @return FileUpload
	 */
	public FileUpload downloadFile() {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(app2ServiceUrls.getBaseUrl()).append(App2Constants.APP1_FILE_URL)
				.append(App2Constants.BACK_SLASH).append("fileRestCall");
		String url = String.valueOf(urlBuilder);
		ResponseEntity<FileUpload> response = restTemplate.getForEntity(url, FileUpload.class);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			return response.getBody();
		}
		return null;
	}

	/**
	 * This method is for Fileupload
	 * 
	 * @param fileUpload
	 * @return FileUpload Data
	 */
	public FileUpload uploadFile(FileUpload fileUpload1) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(app2ServiceUrls.getBaseUrl()).append(App2Constants.APP1_FILE_URL)
				.append(App2Constants.BACK_SLASH).append("uploadFile");
		String url = String.valueOf(urlBuilder);
		HttpEntity<FileUpload> request = new HttpEntity<>(fileUpload1);
		FileUpload fileUpload = restTemplate.postForObject(url, request, FileUpload.class);
		if (fileUpload != null) {
			return fileUpload;
		}
		return null;
	}

}
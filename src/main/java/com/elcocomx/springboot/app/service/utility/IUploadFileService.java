package com.elcocomx.springboot.app.service.utility;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource load(String filename) throws IOException;

	public String copy(MultipartFile file)  throws IOException;

	public boolean delete(String filename);
	
}

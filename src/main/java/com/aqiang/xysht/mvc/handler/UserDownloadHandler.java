package com.aqiang.xysht.mvc.handler;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aqiang.xysht.entities.ParameterKey;
import com.aqiang.xysht.service.ParameterService;

@RequestMapping("nonLogin")
@Controller
public class UserDownloadHandler {
	@Autowired
	private ParameterService parameterService;

	@RequestMapping("download_android")
	public ResponseEntity<byte[]> downloadAndroidClient(HttpServletResponse response) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "xysht.apk");
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(getDictionaryFile()), headers,
				HttpStatus.CREATED);
	}

	private File getDictionaryFile() {
		String parameter = parameterService.getParameter(ParameterKey.FILE_ROOT_DIR);
		return new File(parameter + "files/app/xysht.apk");
	}
}

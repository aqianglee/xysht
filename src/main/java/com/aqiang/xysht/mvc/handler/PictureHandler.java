package com.aqiang.xysht.mvc.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aqiang.xysht.entities.ParameterKey;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.PictureService;

@Controller
public class PictureHandler {

	@Autowired
	private PictureService pictureService;
	@Autowired
	private ParameterService parameterService;

	@RequestMapping("showPicture")
	public void showPicture(HttpServletRequest request, HttpServletResponse response, Integer pictureId) {
		response.setContentType("image/*");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(parameterService.getParameter(ParameterKey.FILE_ROOT_DIR)
					+ pictureService.findEntity(pictureId).getPath());
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
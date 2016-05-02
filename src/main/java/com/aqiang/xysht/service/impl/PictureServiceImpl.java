package com.aqiang.xysht.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.ParameterKey;
import com.aqiang.xysht.entities.Picture;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.PictureService;

@Transactional
@Service
public class PictureServiceImpl extends BaseServiceImpl<Picture> implements PictureService {
	@Autowired
	private ParameterService parameterService;

	@Resource(name = "pictureDao")
	@Override
	public void setDao(BaseDao<Picture> dao) {
		this.dao = dao;
	}

	@Override
	public void deletePicture(Picture picture) {
		String dir = parameterService.getParameter(ParameterKey.FILE_ROOT_DIR);
		String path = picture.getPath();
		java.io.File f = new java.io.File(dir + path);
		if (f.isFile()) {
			f.delete();
		}
		deleteEntity(picture.getId());
	}

	@Override
	public String getPostfixName(String fileName) {
		String[] strings = fileName.split("\\.");
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		if (strings.length == 1) {
			return "";
		}
		return strings[strings.length - 1];
	}

	@Override
	public Picture savePicture(MultipartFile file) {
		String dir = parameterService.getParameter(ParameterKey.FILE_ROOT_DIR);
		String path = "files/pictures/";
		File file2 = new File(dir + path);
		if (!file2.isDirectory()) {
			file2.mkdirs();
		}
		String fileName = UUID.randomUUID().toString() + "." + getPostfixName(file.getOriginalFilename());
		File f = new File(dir + path + fileName);
		FileOutputStream out;
		try {
			out = new FileOutputStream(f);
			out.write(file.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Picture picture = new Picture();
		picture.setPath(path + fileName);
		saveEntitiy(picture);
		return picture;
	}

	@Override
	public Picture initPictureContext(Picture picture) {
		if (picture != null && StringUtils.isNotBlank(picture.getPath())) {
			try {
				String dir = parameterService.getParameter(ParameterKey.FILE_ROOT_DIR);
				String path = picture.getPath();
				FileInputStream in = new FileInputStream(new java.io.File(dir + path));
				byte[] buffer = new byte[in.available()];
				in.read(buffer, 0, in.available());
				BASE64Encoder encoder = new BASE64Encoder();
				picture.setContext(encoder.encode(buffer));
				in.close();
				return picture;
			} catch (IOException e) {
				return null;
			}
		}
		return null;
	}
}

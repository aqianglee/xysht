package com.aqiang.xysht.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		String pathname = dir + path + fileName;
		File f = new File(pathname);
		FileOutputStream out;
		try {
			out = new FileOutputStream(f);
			out.write(file.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Picture picture = new Picture();
		picture.setPath(pathname);
		saveEntitiy(picture);
		return picture;
	}
}

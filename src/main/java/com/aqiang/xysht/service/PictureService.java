package com.aqiang.xysht.service;

import org.springframework.web.multipart.MultipartFile;

import com.aqiang.xysht.entities.Picture;

public interface PictureService extends BaseService<Picture> {
	public void deletePicture(Picture picture);

	public String getPostfixName(String fileName);

	public Picture savePicture(MultipartFile file);

	public Picture initPictureContext(Picture picture);
}

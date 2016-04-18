package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Supermarket;

public interface ClassfyService extends BaseService<Classfy> {

	public List<Classfy> getAllClassfies(Supermarket supermarket);

	public List<Classfy> getClassfiesByParent(Supermarket supermarket, Classfy classfy);

	public Classfy updateClassfy(Classfy classfy);

	public void validateClassfy(Classfy classfy, List<ErrorMessage> messages);

	public void delete(Classfy classfy);
}

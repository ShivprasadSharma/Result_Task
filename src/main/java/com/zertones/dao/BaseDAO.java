package com.zertones.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.web.multipart.MultipartFile;

import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;

public interface BaseDAO
{
	public void setSessionFactory(SessionFactory sf);

	String uploadImgOnCloud(MultipartFile image) throws IOException;

	public List<ComClientNameDTO> getGroupMemberByGroupId(Integer gid);
	// public Criteria getCriteriaForSelect(@SuppressWarnings("rawtypes") Class
	// modelClass);

	// public BaseModel save(BaseModel baseDAO);

}

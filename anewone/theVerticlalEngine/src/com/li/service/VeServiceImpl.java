package com.li.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.dao.VeDao;
import com.li.pojo.FilmModel;


@Service
public class VeServiceImpl implements VeService {

	@Autowired
	private VeDao veDao;
	public Map selectFilmModelListByQuery(String keyword,String score,String sort_fname,String page) throws Exception {
		
		return veDao.selectFilmModelListByQuery(keyword,score,sort_fname,page);
		
	}
	public String getName(String name,String password)throws Exception{
		return veDao.getNameBySQL(name,password);
	}
}

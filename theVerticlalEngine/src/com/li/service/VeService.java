package com.li.service;

import java.util.List;
import java.util.Map;

import com.li.pojo.FilmModel;
import com.li.pojo.UserModel;

public interface VeService {
	
	public Map selectFilmModelListByQuery(String keyword,String score,String sort_fname,String page) throws Exception ;

	
}

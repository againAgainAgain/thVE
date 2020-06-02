package com.li.service;

import java.util.List;
import java.util.Map;

import com.li.pojo.FilmModel;

public interface VeService {
	
	public Map selectFilmModelListByQuery(String keyword,String score,String sort_fname,String page) throws Exception ;

	public String getName(String name,String password)throws Exception;
}

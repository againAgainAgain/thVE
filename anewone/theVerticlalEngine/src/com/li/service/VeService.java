package com.li.service;

import java.util.List;

import com.li.pojo.FilmModel;

public interface VeService {
	
	public List<FilmModel> selectFilmModelListByQuery(String keyword) throws Exception ;

}

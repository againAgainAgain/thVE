package com.li.dao;

import java.util.List;

import com.li.pojo.FilmModel;

public interface VeDao {

	public List<FilmModel> selectFilmModelListByQuery(String keyword,String score,String sort_fname,String page) throws Exception ;
}

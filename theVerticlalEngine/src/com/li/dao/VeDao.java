package com.li.dao;

import java.util.List;
import java.util.Map;

import com.li.pojo.FilmModel;

public interface VeDao {

	public Map selectFilmModelListByQuery(String keyword,String score,String sort_fname,String page) throws Exception ;	//为什么要返回Map?因为要返回的有电影的list和查询结果的总条数

	
}

package com.li.dao;

import java.util.List;
import java.util.Map;

import com.li.pojo.FilmModel;

public interface VeDao {

	public Map selectFilmModelListByQuery(String keyword,String score,String sort_fname,String page) throws Exception ;	//ΪʲôҪ����Map?��ΪҪ���ص��е�Ӱ��list�Ͳ�ѯ�����������

	
}

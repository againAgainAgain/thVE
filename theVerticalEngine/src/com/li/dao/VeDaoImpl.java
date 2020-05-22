package com.li.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.li.pojo.FilmModel;

@Repository
public class VeDaoImpl implements VeDao {
	//������
	@Autowired
	private HttpSolrClient httpSolrClient;
	//final String solrUrl="http://localhost:8080/solr/";
	//final String solrCore="collection1";
	//private HttpSolrClient httpSolrClient=new HttpSolrClient.Builder(solrUrl+solrCore).withConnectionTimeout(10000).withSocketTimeout(60000).build();
	//HttpSolrServer server = new HttpSolrServer("http://localhost:8088/solr/core1");
	
	//ͨ������1��������ѯ�����Ӱ�����
	public List<FilmModel> selectFilmModelListByQuery(String keyword) throws Exception{
		SolrQuery solrQuery = new SolrQuery();
		if (keyword != null && !"".equals(keyword)) {
			solrQuery.setQuery(keyword);
		} else {
			solrQuery.setQuery("*:*");
		}
//		if (null != catalog_name && !"".equals(catalog_name)) {
//			solrQuery.set("fq", "book_catalog_name:" + catalog_name);
//		}
//		if (null != price && !"".equals(price)) {
//			String[] p = price.split("-");
//			solrQuery.set("fq", "book_price:[" + p[0] + " TO " + p[1] + "]");
//		}
//		if ("1".equals(sort)) {
//			solrQuery.addSort("book_price", ORDER.desc);
//		} else {
//			solrQuery.addSort("book_price", ORDER.asc);
//		}

		
		//�ؼ��ʣ�ָ��һ������field
		solrQuery.set("df","film_keyword");
		//ָ��������Щ����
		solrQuery.set("fl", "id,film_name,film_score");
		
		//ִ�в�ѯ
		final QueryResponse response = httpSolrClient.query(solrQuery);
		//�ĵ������
		SolrDocumentList docs = response.getResults();
		 long numFound = docs.getNumFound();
		 System.out.println("��������"+numFound);
//
//		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();


		List<FilmModel> filmModels = new ArrayList<FilmModel>();
		int i=0;

		for (SolrDocument doc : docs) {
			FilmModel filmModel = new FilmModel();

			filmModel.setFid((String) doc.get("id"));
			filmModel.setName((String)doc.get("film_name"));
			filmModel.setScore((Float) doc.get("film_score"));
			filmModel.setKeyword((String) doc.get("film_keyword"));
			//Map<String, List<String>> map = highlighting.get((String) doc.get("id"));
			//List<String> list = map.get("film_name");

			//filmModel.setName(list.get(0));
			filmModels.add(filmModel);
			System.out.println("��"+i+"��");
			System.out.println((String) doc.get("id"));
			System.out.println((String) doc.get("film_name"));
			System.out.println((String) doc.get("film_score"));
		}
		return filmModels;
	}
}
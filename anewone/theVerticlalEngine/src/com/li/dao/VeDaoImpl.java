package com.li.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
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
	//索引库
	@Autowired
	private HttpSolrClient httpSolrClient;
	
	//final String solrUrl="http://localhost:8761/solr/";
	//final String solrCore="collection1";
	//private HttpSolrClient httpSolrClient=new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
	//HttpSolrServer server = new HttpSolrServer("http://localhost:8088/solr/core1");
	//HttpSolrClient.Builder builder=new HttpSolrClient.Builder("http://localhost:8761/solr/collection1");
	//SolrClient client=builder.build();
	
	//通过上面1个条件查询对象电影结果集
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

		//solrQuery.setQuery("*:*");
		//关键词：指定一个搜索field
		solrQuery.set("df","comment");
		//指定返回哪些内容
		solrQuery.set("fl", "id,fname,score,src,href,comment,c_src");
		//solrQuery.set("fl", "id,fname");
		
		//设置高亮开关
		 solrQuery.setHighlight(true);
		 //需要高亮的域
		 solrQuery.addHighlightField("comment");
		 //高亮的样式
		 solrQuery.setHighlightSimplePre("<span style='color.red'>");
		 solrQuery.setHighlightSimplePost("</span>");
		 
		
//		//执行查询
		//QueryResponse response = client.query(solrCore,solrQuery);
		QueryResponse response = httpSolrClient.query(solrQuery);
		//文档结果集
		SolrDocumentList docs = response.getResults();
		 long numFound = docs.getNumFound();
		 System.out.println("总条数："+numFound);
//
		
		 //取出高亮
		 Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();


		List<FilmModel> filmModels = new ArrayList<FilmModel>();
		int i=0;

		for (SolrDocument doc : docs) {
			FilmModel filmModel = new FilmModel();
			
			filmModel.setKey_word(keyword); //这样写会存很多次，但是。。。
			String id=(String) doc.get("id");
			filmModel.setId(id);
			filmModel.setFname((String)doc.get("fname"));
			//System.out.println((float) doc.get("score"));
			filmModel.setScore((Float) doc.get("score"));
			//filmModel.setComment((String) doc.get("comment"));
			filmModel.setSrc((String)doc.get("src"));
			filmModel.setHref((String)doc.get("href"));
			filmModel.setC_src((String)doc.get("c_src"));
			Map<String, List<String>> map = highlighting.get(id);
			
			System.out.println("\n第"+i+"个");
			i=i+1;
			System.out.println((String) doc.get("id"));
			System.out.println((String) doc.get("fname"));
			System.out.println((Float) doc.get("score"));
			System.out.println((String) doc.get("src"));
			System.out.println((String) doc.get("href"));
			System.out.println((String) doc.get("c_src"));
			System.out.println((String) doc.get("c_src"));
			
			List<String> list = map.get("comment");
			if(list!=null && list.size()>0) {
				filmModel.setComment(list.get(0));
				System.out.println(list.get(0));
			}
			filmModels.add(filmModel);
			
		}
		return filmModels;
	}
}

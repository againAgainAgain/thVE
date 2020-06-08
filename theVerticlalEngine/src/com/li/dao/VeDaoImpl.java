package com.li.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.li.pojo.FilmModel;

@Repository
public class VeDaoImpl implements VeDao {
	//������
	@Autowired
	private HttpSolrClient httpSolrClient;
	
	//final String solrUrl="http://localhost:8761/solr/";
	//final String solrCore="collection1";
	//private HttpSolrClient httpSolrClient=new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
	//HttpSolrServer server = new HttpSolrServer("http://localhost:8088/solr/core1");
	//HttpSolrClient.Builder builder=new HttpSolrClient.Builder("http://localhost:8761/solr/collection1");
	//SolrClient client=builder.build();
	
	//ͨ������1��������ѯ�����Ӱ�����
	public Map selectFilmModelListByQuery(String keyword,String score,String sort_fname,String page) throws Exception{
		SolrQuery solrQuery = new SolrQuery();
		if (keyword != null && !"".equals(keyword)) {
			solrQuery.setQuery(keyword);
		} else {
			solrQuery.setQuery("*:*");
		}
//		if (null != catalog_name && !"".equals(catalog_name)) {
//			solrQuery.set("fq", "book_catalog_name:" + catalog_name);
//		}
		//���ݼ۸������
		if (null != score && !"".equals(score)) {
			String[] s = score.split("-");
			solrQuery.set("fq", "score:[" + s[0] + " TO " + s[1] + "]");
		}
//		if ("1".equals(sort)) {
//			solrQuery.addSort("book_price", ORDER.desc);
//		} else {
//			solrQuery.addSort("book_price", ORDER.asc);
//		}
		if(sort_fname!=null && !sort_fname.equals(""))//����Ҫ�ж�sort_fname�Ƿ�Ϊ��
			if(sort_fname.equals("1"))	//ֻ�ж��Ƿ�Ϊ1�Ļ������û�����Ҫ���������޷�����������򣻵��ж�Ϊ0������������Ҳ���ǲ�����
				solrQuery.addSort("fname",ORDER.desc);
		
		if(page!=null && !page.equals("")) {
			//��������page����ҳ��ֵ
			int s=(Integer.parseInt(page)-1)*10;	//����ҳ�Ļ�s=(3-1)*10
			solrQuery.setStart(s);
			solrQuery.setRows(s+10);
		}
		else {
			solrQuery.setStart(0);
			solrQuery.setRows(10);
		}
			

		//solrQuery.setQuery("*:*");
		
		//�ؼ��ʣ�ָ��һ������field
		solrQuery.set("df","comment");
		//ָ��������Щ����
		//solrQuery.set("fl", "id,fname,score,src,href,comment,c_src");��ָ��score���ܷ�����ȷ��ֵ��ԭ����
		//solrQuery.set("fl", "id,fname");
		
		//���ø�������
		 solrQuery.setHighlight(true);
		 //��Ҫ��������
		 solrQuery.addHighlightField("comment");
		 //��������ʽ
		 solrQuery.setHighlightSimplePre("<span style='color:red'>");
		 solrQuery.setHighlightSimplePost("</span>");
		 
		
//		//ִ�в�ѯ
		//QueryResponse response = client.query(solrCore,solrQuery);
		QueryResponse response = httpSolrClient.query(solrQuery);
		//�ĵ������
		SolrDocumentList docs = response.getResults();
		 long numFound = docs.getNumFound();
		 System.out.println("��������"+numFound);
//
		
		 //ȡ������
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

		Map result=new HashMap();
		List<FilmModel> filmModels = new ArrayList<FilmModel>();
		int i=0;
		
		for (SolrDocument doc : docs) {
			FilmModel filmModel = new FilmModel();
			
			filmModel.setKey_word(keyword); //����д���ܶ�Σ����ǡ�����
			String id=(String) doc.get("id");
			filmModel.setId(id);
			filmModel.setFname((String)doc.get("fname"));
			//System.out.println((float) doc.get("score"));
			filmModel.setScore((float) doc.get("score"));
			//filmModel.setComment((String) doc.get("comment"));
			filmModel.setSrc((String)doc.get("src"));
			filmModel.setHref((String)doc.get("href"));
			filmModel.setC_src((String)doc.get("c_src"));
			
			
//			System.out.println("\n��"+i+"��");
//			i=i+1;
//			System.out.println((String) doc.get("id"));
//			System.out.println((String) doc.get("fname"));
//			System.out.println( doc.get("score"));
//			System.out.println((String) doc.get("src"));
//			System.out.println((String) doc.get("href"));
//			System.out.println((String) doc.get("c_src"));
//			System.out.println((String) doc.get("c_src"));
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("comment");
			if(list!=null && list.size()>0) {
				filmModel.setComment(list.get(0));
				System.out.println(list.get(0));
			}
			filmModels.add(filmModel);
			
		}
		System.out.println("���������"+filmModels.size());
		result.put("filmModels", filmModels);
		result.put("count", numFound);	//������
		return result;
	}
	
	
}

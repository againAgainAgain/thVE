package com.li.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.li.pojo.FilmModel;
import com.li.pojo.UserModel;
import com.li.service.VeService;

@Controller
public class VeController {

	@Autowired
	private VeService veService;
	
	//自动匹配参数
	@RequestMapping("search.action")
	public String search(String keyword,String score,String sort_fname,String page,String uname,Model model)throws Exception{
		System.out.println("keyword+score+sort_fname+page"+keyword+score+sort_fname+page+uname);
		Map result=veService.selectFilmModelListByQuery(keyword,score,sort_fname,page);
		List<FilmModel> filmModels = (List<FilmModel>)result.get("filmModels");
		long count=(long)result.get("count");
		model.addAttribute("filmModels", filmModels);
		model.addAttribute("keyword", keyword);
		model.addAttribute("score", score);
		model.addAttribute("sort_fname", sort_fname);
		model.addAttribute("page", page);
		model.addAttribute("count", count);
		System.out.println("count"+count);	//由于已经返回回来的是具体页的10条了，所以count=10
		long num=count/10+1;	
		System.out.println("num"+num);
		model.addAttribute("num",num);
		model.addAttribute("uname", uname);
        return "film_list";
    }
	
}

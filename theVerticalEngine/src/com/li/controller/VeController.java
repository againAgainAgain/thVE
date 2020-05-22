package com.li.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.li.pojo.FilmModel;
import com.li.service.VeService;

@Controller
public class VeController {

	@Autowired
	private VeService veService;
	
	//自动匹配参数
	@RequestMapping("search.action")
	public String search(String keyword,Model model)throws Exception{
		System.out.println("keyword"+keyword);
		List<FilmModel> filmModels = veService.selectFilmModelListByQuery(keyword);
		model.addAttribute("filmModels", filmModels);
        return "film_list";
    }
}

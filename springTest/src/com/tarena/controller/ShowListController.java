package com.tarena.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.entity.Worker;
import com.tarena.service.ShowListService;

@Controller
@RequestMapping("/show")
public class ShowListController {
	@Resource
	ShowListService showListService;
	@RequestMapping("/show.do")
	public String showList(ModelMap map){
		List<Worker> list=showListService.showList();
		map.addAttribute("list",list);
		return "ShowList";
	}
}

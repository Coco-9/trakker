package com.example.trakker;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.trakker.item.LocalDTO;
import com.example.trakker.model.review.dto.ReviewDTO;
import com.example.trakker.service.admin.AdminService;
import com.example.trakker.service.planner.PlannerService;
import com.example.trakker.service.review.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

	@Autowired
	AdminService adminService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	PlannerService plannerService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		int memberTotalCount = adminService.memberCount();
		int localTotalCount = plannerService.localCount();

		List<ReviewDTO> review = reviewService.main_list();
		List<LocalDTO> local = plannerService.localList();


		model.addAttribute("memberTotalCount" , memberTotalCount);
		model.addAttribute("localTotalCount" , localTotalCount);

		model.addAttribute("review", review);
		model.addAttribute("local", local);

		return "home";
	}


}

package org.ecommerce.web.admin.controllers;

import org.ecommerce.config.web.services.OrderService;
import org.ecommerce.config.web.services.ProductService;
import org.ecommerce.web.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author sergio
 */
@Controller("AdminHomeController")
@ComponentScan(basePackages={"org.ecommerce.config"})

public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/admin/home")
	public String show(Model model) {
		// add new feedbacks
		model.addAttribute("feedbacks", productService.getNewFeedbacks());
		// add new orders
		model.addAttribute("newOrders", orderService.getNewOrders());
		// add total profit
		model.addAttribute("totalProfit", orderService.getTotalProfit());
		return "admin/dashboard/index";
	}
}

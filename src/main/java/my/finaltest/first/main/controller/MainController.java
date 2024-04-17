package my.finaltest.first.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	/*
	 * @RequestMapping(value="/") public String main() { logger.info("메인 페이지...");
	 * return "mainTopDiv"; }
	 */

}

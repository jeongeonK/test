package my.finaltest.first.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import my.finaltest.first.member.service.MemberService;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberService memService;
	
	public String file_root = "C:/upload/";
	
	@RequestMapping(value="/")
	public String main() {
		logger.info("로그인 페이지...");
		return "login";
	}
	
	// 회원가입
	@RequestMapping(value="/join.go")
	public String joingo() {
		logger.info("회원가입 페이지...");
		return "joinForm";
	}
	// 아이디 중복 체크
	@RequestMapping(value="/dupliChk")
	@ResponseBody
	public Map<String, Object> dupliChk(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int cnt = memService.dupliChk(id);
		
		if (cnt == 1) {
			map.put("use", 0);
			logger.info("["+id+"] 이미 존재하는 아이디 입니다.");
		} else {
			map.put("use", 1);
			logger.info("["+id+"] 사용 가능한 아이디 입니다.");
		}
		
		return map;
	}
	// 회원가입
	@RequestMapping(value="join.do", method=RequestMethod.POST)
	public String join(Model model, @RequestParam Map<String,String> param) {
		String page = "joinForm";
		String msg = "회원가입에 실패 했습니다.";
		
		int row = memService.join(param);
		
		if (row == 1) {
			page = "login";
			msg = "회원가입에 성공 했습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		return page;
	}
	
	// 로그인
	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, String id, String pw) {
		String page="login";
		
		String loginId = memService.login(id, pw);
		
		if (loginId != null) {
			session.setAttribute("loginId", loginId);
			//page = "redirect:/list"; 동기 방식
			page= "list";
			//page= "list_externalPage";
			logger.info("["+loginId+"] 로그인 성공");
		} else {
			model.addAttribute("아이디 또는 비밀번호를 다시 확인해 주세요.");
		}
		
		return page;
	}

}

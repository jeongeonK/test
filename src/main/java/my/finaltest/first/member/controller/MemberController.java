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
		logger.info("�α��� ������...");
		return "login";
	}
	
	// ȸ������
	@RequestMapping(value="/join.go")
	public String joingo() {
		logger.info("ȸ������ ������...");
		return "joinForm";
	}
	// ���̵� �ߺ� üũ
	@RequestMapping(value="/dupliChk")
	@ResponseBody
	public Map<String, Object> dupliChk(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int cnt = memService.dupliChk(id);
		
		if (cnt == 1) {
			map.put("use", 0);
			logger.info("["+id+"] �̹� �����ϴ� ���̵� �Դϴ�.");
		} else {
			map.put("use", 1);
			logger.info("["+id+"] ��� ������ ���̵� �Դϴ�.");
		}
		
		return map;
	}
	// ȸ������
	@RequestMapping(value="join.do", method=RequestMethod.POST)
	public String join(Model model, @RequestParam Map<String,String> param) {
		String page = "joinForm";
		String msg = "ȸ�����Կ� ���� �߽��ϴ�.";
		
		int row = memService.join(param);
		
		if (row == 1) {
			page = "login";
			msg = "ȸ�����Կ� ���� �߽��ϴ�.";
		}
		
		model.addAttribute("msg", msg);
		
		return page;
	}
	
	// �α���
	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, String id, String pw) {
		String page="login";
		
		String loginId = memService.login(id, pw);
		
		if (loginId != null) {
			session.setAttribute("loginId", loginId);
			//page = "redirect:/list"; ���� ���
			page= "list";
			//page= "list_externalPage";
			logger.info("["+loginId+"] �α��� ����");
		} else {
			model.addAttribute("���̵� �Ǵ� ��й�ȣ�� �ٽ� Ȯ���� �ּ���.");
		}
		
		return page;
	}

}

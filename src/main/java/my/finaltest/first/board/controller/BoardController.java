package my.finaltest.first.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import my.finaltest.first.board.dto.BoardDTO;
import my.finaltest.first.board.service.BoardService;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired BoardService boardService;
	
	public String file_root = "C:/upload/";
	
	// �Խñ� ����Ʈ
	// ���� ���
	@RequestMapping(value="/list")
	public String list(HttpSession session, Model model) {
		logger.info("�Խ��� ������...");
		String page ="login";
		
		if (session.getAttribute("loginId") != null) {
			page = "list";
			String loginId = (String) session.getAttribute("loginId");
			//page = "list_externalPage";
			List<BoardDTO> list = boardService.listOri();
			model.addAttribute("loginId", "[ "+loginId+" ] ��");
			/* model.addAttribute("list", list); */
		} else {
			model.addAttribute("msg", "�α����� �ʿ��� �ý��� �Դϴ�...");
		}
		
		return page;
	}
	// AJAX �񵿱� ���
	@RequestMapping(value="/list.ajax")
	@ResponseBody
	public Map<String, Object> listAjax() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BoardDTO> list = boardService.listOri();
		
		map.put("list", list);
			
		return map;
	}
	// AJAX �񵿱� ���, ������ �ܺ� ���̺귯��
	@RequestMapping(value = "/list.ajax.external")
	@ResponseBody
	public Map<String, Object> listAjaxExternal(String currPage, String pagePerNum) {
		logger.info("��û ������: " + currPage + " | ������ �� �Ǽ�: " + pagePerNum);

		int currentPage = Integer.parseInt(currPage);
		int pagePerNumber = Integer.parseInt(pagePerNum);

		Map<String, Object> map = boardService.list(pagePerNumber, currentPage);

		return map;
	}
	
	
	// �Խñ� �ۼ�
	@RequestMapping(value="/write.go")
	public String writego(Model model, HttpSession session) {
		logger.info("�� �ۼ� ������...");
		
		model.addAttribute("loginId", (String) session.getAttribute("loginId"));
		
		return "writeForm";
	}
	@RequestMapping(value="/write.do")
	public String writedo(Model model, HttpSession session, @RequestParam Map<String,String> param, MultipartFile[] photos) {
		String page = "writeForm";
		
		int row = boardService.write(param, photos);
		if (row == 1) {
			page = "list";
		}
		
		return page;
	}
	
	
	// �Խñ� �� ����
	@RequestMapping(value="/detail")
	public String detail(Model model, HttpSession session, String idx) {
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			page = "detail";
			boardService.detail(idx, model);
		} else {
			model.addAttribute("msg", "�α����� �ʿ��� �ý��� �Դϴ�...");
		}
		
		return page;
	}
	
	// �Խñ� �� ��ǻ�Ϳ� ����� ���� ����
	@RequestMapping(value="/del.ajax")
	@ResponseBody
	public Map<String, Object> del(@RequestParam(value="delArr[]") List<String> delArr) {
		int cnt = boardService.del(delArr);
		logger.info(cnt+"�� ���� ���� �Ǿ����ϴ�.");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		
		return map;
	}

}

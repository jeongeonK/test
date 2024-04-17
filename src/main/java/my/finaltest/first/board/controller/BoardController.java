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
	
	// 게시글 리스트
	// 동기 방식
	@RequestMapping(value="/list")
	public String list(HttpSession session, Model model) {
		logger.info("게시판 페이지...");
		String page ="login";
		
		if (session.getAttribute("loginId") != null) {
			page = "list";
			String loginId = (String) session.getAttribute("loginId");
			//page = "list_externalPage";
			List<BoardDTO> list = boardService.listOri();
			model.addAttribute("loginId", "[ "+loginId+" ] 님");
			/* model.addAttribute("list", list); */
		} else {
			model.addAttribute("msg", "로그인이 필요한 시스템 입니다...");
		}
		
		return page;
	}
	// AJAX 비동기 방식
	@RequestMapping(value="/list.ajax")
	@ResponseBody
	public Map<String, Object> listAjax() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BoardDTO> list = boardService.listOri();
		
		map.put("list", list);
			
		return map;
	}
	// AJAX 비동기 방식, 페이지 외부 라이브러리
	@RequestMapping(value = "/list.ajax.external")
	@ResponseBody
	public Map<String, Object> listAjaxExternal(String currPage, String pagePerNum) {
		logger.info("요청 페이지: " + currPage + " | 페이지 당 건수: " + pagePerNum);

		int currentPage = Integer.parseInt(currPage);
		int pagePerNumber = Integer.parseInt(pagePerNum);

		Map<String, Object> map = boardService.list(pagePerNumber, currentPage);

		return map;
	}
	
	
	// 게시글 작성
	@RequestMapping(value="/write.go")
	public String writego(Model model, HttpSession session) {
		logger.info("글 작성 페이지...");
		
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
	
	
	// 게시글 상세 보기
	@RequestMapping(value="/detail")
	public String detail(Model model, HttpSession session, String idx) {
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			page = "detail";
			boardService.detail(idx, model);
		} else {
			model.addAttribute("msg", "로그인이 필요한 시스템 입니다...");
		}
		
		return page;
	}
	
	// 게시글 및 컴퓨터에 저장된 파일 삭제
	@RequestMapping(value="/del.ajax")
	@ResponseBody
	public Map<String, Object> del(@RequestParam(value="delArr[]") List<String> delArr) {
		int cnt = boardService.del(delArr);
		logger.info(cnt+"개 글이 삭제 되었습니다.");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		
		return map;
	}

}

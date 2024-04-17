package my.finaltest.first.board.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import my.finaltest.first.board.dao.BoardDAO;
import my.finaltest.first.board.dto.BoardDTO;

@Service
public class BoardService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired BoardDAO boardDao;
	
	public String file_root = "C:/upload/";

	public List<BoardDTO> listOri() {
		return boardDao.listOri();
	}
	
	public Map<String, Object> list(int pagePerNumber, int currentPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int start = (currentPage-1) * pagePerNumber;
		
		List<BoardDTO> list = boardDao.list(pagePerNumber, start);
		map.put("list", list);
		map.put("currPage", currentPage);
		map.put("totalPages", boardDao.allCount(pagePerNumber));
		
		return map;
	}

	
	
	
	public void detail(String idx, Model model) {
		boardDao.upHit(idx);
		BoardDTO dto = boardDao.detail(idx);
		model.addAttribute("item", dto);
		List<BoardDTO> photos = boardDao.detailFile(idx);
		model.addAttribute("photos", photos);
	}

	
	
	public int write(Map<String, String> param, MultipartFile[] photos) {
		
		BoardDTO dto = new BoardDTO();
		dto.setSubject(param.get("subject"));
		dto.setUser_name(param.get("user_name"));
		dto.setContent(param.get("content"));
		
		int row = boardDao.write(dto);
		
		int idx = dto.getIdx();
		
		if (row == 1) {
			fileSave(idx, photos);
		}
		
		return row;
	}
	private void fileSave(int idx, MultipartFile[] photos) {
		if (photos.length > 0) {
			for (MultipartFile photo : photos) {
				String ori_filename = photo.getOriginalFilename();
				logger.info("파일 이름 => "+ori_filename);
				
				String ext = ori_filename.substring(ori_filename.lastIndexOf("."));
				String new_filename = System.currentTimeMillis()+ext;
				logger.info("변경된 파일 이름 => "+new_filename);
				
				try {
					byte[] bytes = photo.getBytes();
					Path path = Paths.get(file_root+new_filename);
					Files.write(path, bytes);
					boardDao.savePhoto(ori_filename, new_filename, idx);
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int del(List<String> delArr) {
		int cnt = 0;
		
		for (String idx : delArr) {
			// 글의 파일 명 추출
			List<String> filenames = boardDao.getFileName(idx);
			
			// 글 삭제
			int row = boardDao.del(idx);
			if (row != 0) {
				cnt++;
			}
			
			// 파일 삭제
			if (filenames.size() != 0) {
				for (String fileName : filenames) {
					Path path = Paths.get(file_root + fileName);
					try {
						Files.deleteIfExists(path);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return cnt;
	}


}

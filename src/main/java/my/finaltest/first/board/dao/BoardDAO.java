package my.finaltest.first.board.dao;

import java.util.List;
import java.util.Map;

import my.finaltest.first.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> listOri();
	List<BoardDTO> list(int pagePerNumber, int start);
	int allCount(int pagePerNumber);

	
	void upHit(String idx);
	BoardDTO detail(String idx);
	List<BoardDTO> detailFile(String idx);
	
	
	int write(BoardDTO dto);
	void savePhoto(String ori_filename, String new_filename, int idx);
	
	
	List<String> getFileName(String idx);
	int del(String idx);



}

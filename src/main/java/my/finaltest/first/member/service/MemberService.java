package my.finaltest.first.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.finaltest.first.member.dao.MemberDAO;

@Service
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberDAO memDao;

	public int dupliChk(String id) {
		return memDao.dupliChk(id);
	}

	public int join(Map<String, String> param) {
		int row = -1;
		row = memDao.join(param);
		
		if (row == 1) {
			memDao.adminMem(param.get("id"), param.get("admin"));
		}
		
		return row;
	}

	public String login(String id, String pw) {
		return memDao.login(id, pw);
	}

}

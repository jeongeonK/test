package my.finaltest.first.member.dao;

import java.util.Map;

public interface MemberDAO {

	int dupliChk(String id);

	void adminMem(String string, String string2);
	int join(Map<String, String> param);

	String login(String id, String pw);


}

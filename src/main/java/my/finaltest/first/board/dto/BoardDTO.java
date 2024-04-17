package my.finaltest.first.board.dto;

public class BoardDTO {

	// bbs 테이블
	private int idx;
	private String user_name;
	private String subject;
	private int bHit;
	private String content;
	private String reg_date;
	
	// photo 테이블
	private int file_idx;
	private String ori_filename;
	private String new_filename;
	// private int idx;
	
	// bbs photo join 이미지 개수
	private int img_cnt;
	
	
	

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date.toString();
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getFile_idx() {
		return file_idx;
	}

	public void setFile_idx(int file_idx) {
		this.file_idx = file_idx;
	}

	public String getOri_filename() {
		return ori_filename;
	}

	public void setOri_filename(String ori_filename) {
		this.ori_filename = ori_filename;
	}

	public String getNew_filename() {
		return new_filename;
	}

	public void setNew_filename(String new_filename) {
		this.new_filename = new_filename;
	}

	public int getImg_cnt() {
		return img_cnt;
	}

	public void setImg_cnt(int img_cnt) {
		this.img_cnt = img_cnt;
	}
}

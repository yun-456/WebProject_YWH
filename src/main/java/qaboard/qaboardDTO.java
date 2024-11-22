package qaboard;

import java.sql.Date;

public class qaboardDTO {
	private String idx; // 게시물 일련번호
	private String id; // 작성자 ID
	private String title; // 게시물 제목
	private String content; // 게시물 내용
	private Date postdate; // 작성일
	private int visitcount; // 조회수
	private Integer parentIdx; // 부모 질문 일련번호 (답변 게시물일 경우)
	private int depth; // 게시물 깊이 (질문은 0, 답변은 1 이상의 값)
	private char anser_status; // 답변 상태

	// Getter and Setter methods
	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public int getVisitcount() {
		return visitcount;
	}

	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}

	public Integer getParentIdx() {
		return parentIdx;
	}

	public void setParentIdx(Integer parentIdx) {
		this.parentIdx = parentIdx;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public char getAnser_status() {
		return anser_status;
	}

	public void setAnser_status(char anser_status) {
		this.anser_status = anser_status;
	}
}

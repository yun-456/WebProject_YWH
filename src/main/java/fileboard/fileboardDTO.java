package fileboard;

import java.sql.Date;

public class fileboardDTO {
    private String idx; // 게시물 일련번호
    private String id;  // 작성자 아이디
    private String title;  // 제목
    private String content;  // 내용
    private Date postdate;  // 작성일
    private String ofile;  // 원본 파일명
    private String sfile;  // 서버에 저장된 파일명
    private int downcount;  // 다운로드 수
    private int visitcount;  // 조회수
    private String name;  // 작성자 이름 (user_info 테이블과 조인 시 필요)

    // Getter와 Setter 메서드 정의
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

    public String getOfile() {
        return ofile;
    }

    public void setOfile(String ofile) {
        this.ofile = ofile;
    }

    public String getSfile() {
        return sfile;
    }

    public void setSfile(String sfile) {
        this.sfile = sfile;
    }

    public int getDowncount() {
        return downcount;
    }

    public void setDowncount(int downcount) {
        this.downcount = downcount;
    }

    public int getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package notice;

public class NoticeDTO {
    private int    no      ;
    private String id      ;
    private String title   ;
    private String content ;
    private String passwd  ;
    private String wdate   ;
	public NoticeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeDTO(int no, String id, String title, String content, String passwd, String wdate) {
		super();
		this.no = no;
		this.id = id;
		this.title = title;
		this.content = content;
		this.passwd = passwd;
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "NoticeDTO [no=" + no + ", id=" + id + ", title=" + title + ", content=" + content + ", passwd=" + passwd
				+ ", wdate=" + wdate + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
    
    

}

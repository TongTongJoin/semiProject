package net.board.action;

public class ActionForward {
	private boolean isRedirect;
	private String path; //jsp 페이지 path 정보 담을 예정
	
	public ActionForward() {
		this.isRedirect = false;
		this.path = null;
	}
	
	public boolean isRedirect() { // get 이 필요없어서 직접 쳐봤다라는 느낌
		return isRedirect;
	}
	
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}

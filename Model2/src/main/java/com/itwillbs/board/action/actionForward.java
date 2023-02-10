package com.itwillbs.board.action;

public class actionForward {

//		이동 => 이동정보 : (주소값필요 , 이동방식) => 이동정보를 저장하는 자바파일,,,필요,,,^^,,,,ㅎㅎ 
//		String path = "주소정보";
//		boolean isRedirect = true;
//		true이면 response.sendRedirect로 이동
//		false이면 dispatcher.forward로 이동하게끔.. 해보자.....
	
		private String path;
		private boolean isRedirect;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public boolean isRedirect() {
			return isRedirect;
		}
		public void setRedirect(boolean isRedirect) {
			this.isRedirect = isRedirect;
		}		
}

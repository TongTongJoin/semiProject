package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.DB.BoardDAO;
import net.board.DB.BoardDTO;

public class BoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		
		//먼저 연동시킬 페이지의 전송방식을 확인. post 면 인코딩.
		request.setCharacterEncoding("UTF-8");
		String saveFolder = request.getSession().getServletContext().getRealPath("/boardupload"); 
																										// application 객체는  jsp 에서만 활용가능해서 request 를 통해 우회해야한다..! 절대위치값.
		System.out.println(saveFolder);																									// 매개변수로 들어간 문자열값을 알아서 절대경로에 추가시켜준다
		int fileSize = 5 * 1024 * 1024; // 5MB
		//이제 전송된 데이터를 받으면 되는데... 파일첨부는 어떻게 할 것인가. 일단 Tomcat 은 첨부파일을 가져오는 기능이 없다. 그래서 고맙게도 만들어준 누군가에 의해.. 추가기능을 활용
			// http://servlets.com/cos/ 추가기능은 WEB-INF/lib 에 넣으면 된다
			// 이제 태그 확인. enctype 이 MultipartRequest 로 되어있음을 확인. 그러면 메소드 활용
		
		/*(말그대로 데이터를 받아오고 있는곳, 
			받아온 첨부파일을 저장할 곳, 꼭 DB에 저장하지 않아도 된다. 이게 더 심지어 속도가 빠르다. 물론 회사마다 다르다
			첨부용량제한,
			인코딩,
			동일명 파일을 식별해주는 객체
			)*/
			// 일단 예제에서는 서버 저장소 활용
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy()); 
		// 이제 위에서 받아온 브라우저상의 입력데이터를 DTO 에 set 해주면 된다. 굳이 비유하면 지금은 반쪽짜리 DAO 이다.
		
		boardDTO.setBOARD_NAME(multi.getParameter("BOARD_NAME"));//무려 getParam 이 MultiPartRequest 에 들어있다..! 기존의 방식대로 적용가능. multi 는 첨부파일을 가져와야하는 기능이 추가적으로 필요하기 때문이다
		boardDTO.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
		boardDTO.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
		boardDTO.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
		boardDTO.setBOARD_FILE(multi.getFilesystemName((String) multi.getFileNames().nextElement())); //모든 첨부파일의 이름을 가져오겠다는 의도
		String testname = multi.getFilesystemName((String) multi.getFileNames().nextElement());
		System.out.println(testname);		
		// 이제 ConnectionPool 스킴을 활용해서 연결하면된다. 다행히도 Tomcat 7부터 connectionpool 이 내장되어있다 
		boolean result = boardDAO.boardInsert(boardDTO);
		
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		
		if(result) {
			return forward;
		}else {
			return null;
		}
		
		// 이제 DB 에 전달이 되었는데... 변경사항이 반영된 페이지를 반환시키는 공통 기능이 필요하다. 처리 후 응답 페이지 표기..는 sendRedirect(갱신된 페이지의 path) 로 처리하면 된다
		// 그 처리방식이 바로 forward.setRedirect(true); 이다....!! 이에 이어서 setPath 를 잡아주면 controller 단에서는 이 두 값을 인식하여 페이지 갱신을 해준다!!
		
	}


}

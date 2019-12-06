package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo") // 즉 임의로 지정한 bo 라는 확장자를 지닌 JSP 요청과의 연동을 하겠다는 의미.
// 그럼 다음 step 은.. 요청받은 bo 페이지라는 의미.
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청 parsing
		String requestURI = request.getRequestURI();
		System.out.println(requestURI); // 기능한번당 체크 한번 하자 // 출력값은 /chap18_Board/BoardWrite.bo
		String contextPath = request.getContextPath();
		System.out.println(contextPath);// 출력값은 /chap18_Board
		// 매개변수 한개만 있어도 충분하지만.. 일단 넣자. 그리고 contextPath.length() 가 요청 시작 index 가 많다. 즉 그
		// String 을 다 거른다는 뜻이다
		String command = requestURI.substring(contextPath.length(), requestURI.length());
		System.out.println(command);
		
		Action action = null; // implement 한 객체가 본 기능을 바라볼 수 있게 하기 위해서이다. 즉 오버라이딩을 위함.
		ActionForward forward = null;

		// request 로 가져온 .bo 라는 얘들 중에 걔 값(주소)이 "/BoardWrite.bo" 일 경우
		if (command.equals("/BoardWrite.bo")) {
			// 그럼 다음 step은.. 이 요청을 받으면 게시글을 작성할 수 있는 양식을 응답해주어야 한다. 양식은 일단 만들어놓은 것을
			// WebContent/board 에 저장
			forward = new ActionForward(); // 여기에 비로소 인스턴스 생성.
			forward.setPath("./board/qna_board_write.jsp");
			forward.setRedirect(false); // 일단 false 로 지정
		} else if (command.equals("/BoardAddAction.bo")) {
			//UI 를 예상하고 접근하면 쉽다. 뭔가 추가기능 실행을 위한 버튼이 있을 것이고 정보를 서버에 전달해 저장시키고 나서 응답으로 클라이언트 측에 보내줄 페이지에 무슨 내용을 담아야 하는가
			action = new BoardAddAction();
			try {
				forward = action.execute(request, response); // 직접 path 를 잡을 건지 or 인터페이스를 응용해서 DB 연동을 할 것인지. 결국 기능의 큰 틀은 이 둘중 하나이다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardList.bo")) { // 재접속 기능은 밑에 있으니까.. 갱신된 페이지를 만들기 위한 정보를 DB에서 읽어오기만 하면 된다.
			action = new BoardListAction();
			try {
				forward = action.execute(request, response); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			
		}
		if (forward != null) {
			// 이건 의도하지 않은 가능성을 배제하기 위함
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath()); // 다시가라 라는 의미이다.. 갱신된 페이지 호출이라는 기능을 하나의 공통된 프로세스로 정립하기 위함이다
			} else {
				// false 로 지정했기 때문에 여기로 오게끔 지정. 여기서 바로 처리되겠다는 의도
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); 
				// Path 주소를 getRequestDispatcher 로 담아서 RequestDispatcher 참조변수로 설정.. 이 객체에서 forward 메소드를 사용
					// 이 한줄로 끝나는게 놀랍다.
				dispatcher.forward(request, response);
			}
		} else {
			System.out.println("요청을 확인하세요");
		}
	}
}

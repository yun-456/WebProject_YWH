package model.mvcboard1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//이 컨트롤러(서블릿)의 매핑은 web.xml에서 정의한다.
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 게시판에서 목록 혹은 특정 메뉴를 클릭해서 진입하므로 get방식의 요청임
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DAO 인스턴스생성 : 생성과 동시에 DBCP를 오라클에 연결된다.
		MVCBoardDAO dao = new MVCBoardDAO();

		// 뷰에 전달할 매개변수 저장용 맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		// 검색을 위해 검색어를 입력했다면 파라미터로 전달된 값을 Map에 저장.
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");

		// 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
		if (searchWord != null) {
			// 검색어가 있는 경우 Map에 파라미터를 저장한다.
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		// 게시물의 갯수 카운트를 위한 메서드 호출
		int totalCount = dao.selectCount(map);
		// 결과를 Map에 저장
		List<MVCBoardDTO> boardLists = dao.selectList(map);
		if (boardLists.isEmpty()) {
		    System.out.println("게시물 목록이 비어 있습니다.");
		} else {
		    System.out.println("게시물 목록 조회 성공: " + boardLists.size() + "개의 게시물");
		}
		// DB연결해제
		dao.close();
		// 목록에 출력할 레코드를 인출하기 위한 메서드 호출
		map.put("totalCount", totalCount);

		// 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		// View로 포워드 한다.
		req.getRequestDispatcher("/MVCBoard1/List.jsp").forward(req, resp);

		/*
		 * request영역은 포워드 된 페이지까지 데이터를 공유할 수 있으므로 서블릿에서 처리한 내용은 request영역을 통해 JSP쪽으로
		 * 공유된다.
		 */
	}
}

package qaboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

//수정하기
@WebServlet("/qaboard/qaedit.do")
public class qaEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 수정페이지로 진입하기
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 로그인 확인 : 로그인 전이라면 로그인 페이지로 이동시킨다.
        HttpSession session = req.getSession();
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../login_info/LoginForm.jsp");
            return;
        }

        // 게시물 얻어오기 : '열람'에서 사용했던 메서드를 그대로 호출
        String idx = req.getParameter("idx");
        qaboardDAO dao = new qaboardDAO();
        qaboardDTO dto = dao.selectView(idx);

        // 작성자 본인 확인 : DTO에 저장된 id와 로그인 아이디를 비교
        if (dto == null || !dto.getId().equals(session.getAttribute("UserId").toString())) {
            // 작성자 본인이 아니라면 경고창을 띄운 후 뒤로 이동한다.
            JSFunction.alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
            return;
        }

        // 작성자 본인이라면 request영역에 DTO를 저장 후 포워드한다.
        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/qaboard/qaEdit.jsp").forward(req, resp);
    }

    // 수정 처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 로그인 확인
        HttpSession session = req.getSession();
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../login_info/LoginForm.jsp");
            return;
        }

        // 작성자 본인 확인 : 수정폼의 hidden속성으로 추가한 내용으로 비교
        String userId = req.getParameter("id");
        if (userId == null || !userId.equals(session.getAttribute("UserId").toString())) {
            JSFunction.alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
            return;
        }

        // 수정할 게시물의 일련번호
        String idx = req.getParameter("idx");

        // 사용자가 수정한 내용
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        // DTO에 저장
        qaboardDTO dto = new qaboardDTO();
        dto.setIdx(idx);
        dto.setId(session.getAttribute("UserId").toString());
        dto.setTitle(title);
        dto.setContent(content);

        // DB에 수정 내용 반영
        qaboardDAO dao = new qaboardDAO();
        int result = dao.updatePost(dto);
        dao.close();

        // 성공 or 실패?
        if (result == 1) { // 수정 성공
            // 수정에 성공하면 '열람'페이지로 이동해서 수정된 내용을 확인한다.
            resp.sendRedirect("../qaboard/qaview.do?idx=" + idx);
        } else { // 수정 실패 : 경고창을 띄운다.
            JSFunction.alertLocation(resp, "수정에 실패했습니다.", "../qaboard/qaedit.do?idx=" + idx);
        }
    }
}

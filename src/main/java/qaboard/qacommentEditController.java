package qaboard;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/qaboard/qacommentEdit.do")
public class qacommentEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 댓글 수정 페이지로 진입
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../login_info/LoginForm.jsp");
            return;
        }

        String commentId = req.getParameter("commentId");
        if (commentId == null || commentId.isEmpty()) {
            JSFunction.alertBack(resp, "유효하지 않은 댓글 번호입니다.");
            return;
        }

        qacommentDAO dao = new qacommentDAO();
        qacommentDTO dto = dao.getCommentById(commentId);
        dao.close();

        if (dto == null || !dto.getUserId().equals(session.getAttribute("UserId").toString())) {
            JSFunction.alertBack(resp, "본인만 댓글을 수정할 수 있습니다.");
            return;
        }

        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/qaboard/qacommentEdit.jsp").forward(req, resp);
    }

    // 댓글 수정 처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../login_info/LoginForm.jsp");
            return;
        }

        String commentId = req.getParameter("commentId");
        String content = req.getParameter("content");
        if (content == null || content.trim().isEmpty()) {
            JSFunction.alertBack(resp, "댓글 내용을 입력해주세요.");
            return;
        }

        qacommentDAO dao = new qacommentDAO();
        qacommentDTO dto = dao.getCommentById(commentId);

        if (dto == null || !dto.getUserId().equals(session.getAttribute("UserId").toString())) {
            JSFunction.alertBack(resp, "본인만 댓글을 수정할 수 있습니다.");
            dao.close();
            return;
        }

        dto.setContent(content);
        int result = dao.updateComment(dto);
        dao.close();

        if (result == 1) {
            resp.sendRedirect("../qaboard/qaview.do?idx=" + dto.getBoardIdx());
        } else {
            JSFunction.alertBack(resp, "댓글 수정에 실패했습니다.");
        }
    }
}

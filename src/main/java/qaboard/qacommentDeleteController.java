package qaboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/qaboard/qacommentdelete.do")
public class qacommentDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        // 로그인 확인
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../login_info/LoginForm.jsp");
            return;
        }

        // 댓글 번호 받아오기
        String commentIdStr = req.getParameter("commentId");
        if (commentIdStr == null || commentIdStr.isEmpty()) {
            JSFunction.alertBack(resp, "유효하지 않은 댓글 번호입니다.");
            return;
        }

        int commentId = Integer.parseInt(commentIdStr);
        String userId = session.getAttribute("UserId").toString();

        // DAO를 통한 댓글 조회 및 삭제
        qacommentDAO dao = new qacommentDAO();
        qacommentDTO dto = dao.getCommentById(commentIdStr);

        // 작성자 본인 확인
        if (dto == null || !dto.getUserId().equals(userId)) {
            JSFunction.alertBack(resp, "본인만 댓글을 삭제할 수 있습니다.");
            dao.close();
            return;
        }

        int result = dao.deleteComment(commentId);
        dao.close();

        // 삭제 처리 결과
        if (result == 1) {
            JSFunction.alertLocation(resp, "댓글이 삭제되었습니다.", "../qaboard/qaview.do?idx=" + dto.getBoardIdx());
        } else {
            JSFunction.alertBack(resp, "댓글 삭제 중 오류가 발생했습니다.");
        }
    }
}

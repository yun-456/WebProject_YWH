package qaboard;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/qaboard/qacomment.do")
public class qacommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 댓글 작성 처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        // 로그인 확인
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../login_info/LoginForm.jsp");
            return;
        }

        // 게시물 번호 받아오기
        String boardIdxStr = req.getParameter("boardIdx");
        if (boardIdxStr == null || boardIdxStr.isEmpty()) {
            JSFunction.alertBack(resp, "유효하지 않은 게시물 번호입니다.");
            return;
        }

        int boardIdx = Integer.parseInt(boardIdxStr);
        String userId = session.getAttribute("UserId").toString();
        String content = req.getParameter("content");

        if (content == null || content.trim().isEmpty()) {
            JSFunction.alertBack(resp, "댓글 내용을 입력해주세요.");
            return;
        }

        // DTO 객체 생성 및 데이터 설정
        qacommentDTO dto = new qacommentDTO();
        dto.setBoardIdx(boardIdx);
        dto.setUserId(userId);
        dto.setContent(content);

        // DAO를 통한 댓글 삽입
        qacommentDAO dao = new qacommentDAO();
        int result = dao.insertComment(dto);
        dao.close();

        // 댓글 작성 결과 처리
        if (result == 1) {
            // 게시물 보기 페이지로 리다이렉트
            resp.sendRedirect("../qaboard/qaview.do?idx=" + boardIdx);
        } else {
            JSFunction.alertBack(resp, "댓글 작성에 실패했습니다.");
        }
    }
}

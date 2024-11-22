package qaboard;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/qaboard/qaview.do")
public class qaViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idx = req.getParameter("idx");
        if (idx == null || idx.isEmpty()) {
            resp.sendRedirect("../qaboard/qalistPage.do");
            return;
        }

        // 쿠키 배열에서 해당 게시물 조회 쿠키가 있는지 확인
        Cookie[] cookies = req.getCookies();
        boolean hasViewed = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("viewed_" + idx)) {
                    hasViewed = true;
                    break;
                }
            }
        }

        // 조회수가 증가되지 않은 경우에만 증가 처리
        qaboardDAO dao = new qaboardDAO();
        if (!hasViewed) {
            dao.updateVisitCount(idx);
            // 새로운 쿠키 생성 (게시물 ID를 이용하여 쿠키 이름 설정)
            Cookie newCookie = new Cookie("viewed_" + idx, "true");
            newCookie.setMaxAge(60 * 60 * 24); // 쿠키 유효기간을 1일로 설정
            newCookie.setPath(req.getContextPath()); // 경로 설정
            resp.addCookie(newCookie); // 클라이언트에 쿠키 전송
        }

        // 게시물 불러오기
        qaboardDTO dto = dao.selectView(idx);

        // 댓글 목록 가져오기
        qacommentDAO commentDAO = new qacommentDAO();
        List<qacommentDTO> commentList = commentDAO.selectComments(Integer.parseInt(idx));
        commentDAO.close();
        dao.close();

        // 게시물 및 댓글 목록을 request 영역에 저장
        req.setAttribute("dto", dto);
        req.setAttribute("commentList", commentList);

        req.getRequestDispatcher("/qaboard/qaView.jsp").forward(req, resp);
    }
}

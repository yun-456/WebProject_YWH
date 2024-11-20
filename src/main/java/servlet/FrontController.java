package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.one")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // request 내장객체를 통해 현재 요청된 URL을 얻어온다. 전체 경로명에서 Host를 제외한 나머지 경로를 얻어올 수 있다.
        String uri = req.getRequestURI();
        // URL에서 마지막 /의 인덱스를 얻어온다.
        int lastSlash = uri.lastIndexOf("/");
        // 앞에서 얻은 인덱스를 통해 URL을 잘라낸다. 즉 마지막의 요청명만 남는다.
        String commandStr = uri.substring(lastSlash);

        // 자유게시판 요청 처리
        if (commandStr.equals("/freeboard.one")) {
            freeboardFunc(req);
        }

        // 요청명에 관련된 값들을 request 영역에 저장한다.
        req.setAttribute("uri", uri);
        req.setAttribute("commandStr", commandStr);
        // View로 포워드한다.
        req.getRequestDispatcher("/12Servlet/FrontController.jsp").forward(req, resp);
    }

    // 자유게시판 페이지 처리 메서드 정의
    void freeboardFunc(HttpServletRequest req) {
        req.setAttribute("resultValue", "<h4>자유게시판</h4>");
    }
}

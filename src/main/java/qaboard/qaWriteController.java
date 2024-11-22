package qaboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

/*
 * 글쓰기를 위한 컨트롤러는 페이지로 진입하기 위한 doGet()과 쓰기처리를 위한 doPost()를
 * 한꺼번에 정의한다.
 */
public class qaWriteController extends HttpServlet{
   private static final long serialVersionUID =1L;
   
   //글쓰기 페이지에 진입하는것은 버튼을 클릭하여 이동하게 되므로 get방식의 요청이다.
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
         throws ServletException, IOException {
      //로그인 확인을 위해 request내장객체로 session내장객체를 얻어온다.
      HttpSession session = req.getSession();
      
      //session영역에 회원인증에 관련된 속성이 없다면 로그인 페이지로 이동한다.
      if(session.getAttribute("UserId") == null) {
         JSFunction.alertLocation(resp, "로그인 후 이용해주세요.",
               "../login_info/LoginForm.jsp");
         //Java코드가 더이상 실행되지 않도록 차단
         return;
      }
      
      //로그인이 완료된 상태라면 쓰기페이지를 포워드한다.
      req.getRequestDispatcher("/qaboard/qaWrite.jsp").forward(req, resp);
   }
   
   //글쓰기 처리
   @Override
   protected void doPost(HttpServletRequest req, 
         HttpServletResponse resp) 
               throws ServletException, IOException {
      //로그인 확인(세션은 일정 시간이 지나면 자동으로 해제되므로 확인 필요함)
      HttpSession session = req.getSession();
      if(session.getAttribute("UserId") == null) {
         JSFunction.alertLocation(resp, "로그인 후 이용해주세요.",
               "../login_info/LoginForm.jsp");
         return;
      }
      
      // 폼값을 DTO에 저장
      qaboardDTO dto = new qaboardDTO();
      //작성자 아이디는 session 영역에 저장된 데이터를 이용한다.
      dto.setId(session.getAttribute("UserId").toString());
      //제목과 내용등은 사용자가 전송한 폼값을 받은 후 저장한다.
      dto.setTitle(req.getParameter("title"));
      dto.setContent(req.getParameter("content"));
      
      //DAO를 통해 DB에 게시 내용 저장(insert 쿼리문 실행)
      qaboardDAO dao = new qaboardDAO();
      //입력에 성공하면 1, 실패하면 0을 반환한다.
      int result = dao.insertWrite(dto);
      
      dao.close();
      
      //성공 or 실패?
      if (result == 1) { //글쓰기 성공
         //게시판 목록으로 이동
         resp.sendRedirect("../qaboard/qalistPage.do");
      }
      else { // 글쓰기 실패
         //글쓰기 페이지로 다시 돌아간다.
         JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "../qaboard/qawrite.do");
      }
   }
}
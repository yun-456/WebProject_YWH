package fileboard;

import java.io.File;
import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

//게시물 삭제 처리
@WebServlet("/fileboard/filedelete.do")
public class fileDeleteController extends HttpServlet {
   private static final long serialVersionUID = 1L; 
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      if(session.getAttribute("UserId")==null) {
         //session영역에 인증에 관련된 속성이 있는지 확인 후 경고창 띄움
         JSFunction.alertLocation(resp, "로그인 후 이용해주세요", "../login_info/LoginForm.jsp");
      return ;
   }
   //게시물 얻어오기 : 알람에서 사용한 메서드를 그대로 사용한다.
   String idx = req.getParameter("idx");
   fileboardDAO dao = new fileboardDAO();
   fileboardDTO dto = dao.selectView(idx);
   /*
   작성자 본인 확인 : DTO 저장된 아이디와 session영역에 저장된 아이디를
   비교하여 본인이 아니라면 경고창을 띄운다.
    */
   if(!dto.getId().equals(session.getAttribute("UserId")
         .toString())) {
      JSFunction.alertBack(resp, "작성자 본인만 삭제할 수 있습니다.");
      return;
   }
   //게시물 삭제
   int result = dao.deletePost(idx);
   dao.close();
   if(result == 1) {
      //게시물 삭제 성공 시 첨부파일도 삭제
      String saveFileName = dto.getSfile();
      //서버에 저장된 파일명으로 파일을 삭제한다.
      FileUtil.deleteFile(req, "/Uploads", saveFileName);
   }
   //삭제가 완료되면 목록으로 이동한다.
   JSFunction.alertLocation(resp, "삭제되었습니다.", "../fileboard/filelistPage.do");
   }
   //파일 삭제
   public static void deleteFile(HttpServletRequest req,
         String directory, String filename) {
      //디렉토리의 물리적 경로 얻어오기
      String sDirectory = req.getServletContext()
            .getRealPath(directory);
      //경로와 파일명을 이용해서 File 객체 생성
      File file = new File(sDirectory + File.separator + filename);
      //해당 경로에 파일이 존재하면 즉시 삭제한다.
      if (file.exists()) {
         file.delete();
         
      }
   }
   

}

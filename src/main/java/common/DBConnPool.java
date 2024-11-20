package common;

import java.sql.Connection;

/*
JNDI(Java Naming and Directory Interface)
: 디렉토리 서비스에서 제공하는 데이터 및 객체를 찾아서 참조(Lookup)하는
API로 쉽게말하면 외부에 있는 객체를 이름으로 찾아오기 위한 기술.

DBCP(DataBase Connection Pool : 커넥션풀)
: DB와 연결된 커넥션 객체를 인스턴스를 미리 만들어 풀(Pool)에 저장해 두었다가
필요할때 가져다 쓰고 반납하는 기술을 말한다. DB의 부하를 줄이고 자원을
효율적으로 관리할 수 있다. (워터파크의 유수풀과 비슷하다)
*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
   //멤버변수
   public Connection con;
   public Statement stmt;
   public PreparedStatement psmt;
   public ResultSet rs;
   
   //기본 생성자
   public DBConnPool() {
      try {
         //1.Context 인스턴스를 생성한다. Tomcat웹서버라 생각하면된다.
         Context initCtx = new InitialContext();
         /*
          2. 앞에서 생성한 인스턴스를 통해 JDNI서비스 구조의 초기 Root디렉토리를
          얻어온다. 해당 디렉토리의 이름은 아래와 같이 이미 지정되어 있으므로
          그대로 사용하면 된다. 
          */
         Context ctx = (Context)initCtx.lookup("java:comp/env");
         /*
          3. server.xml에 등록한 네이밍을 Lookup 하여 DataSource를 얻어온다.
          즉 DB연결을 위한 정보를 가지고 있다. 
          */
         DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
         //4. 커넥션풀에 생성해 둔 객체를 가져다가 사용한다.
         con = source.getConnection();
         
         System.out.println("DB 커넥션 풀 연결 성공");
      }
      catch (Exception e) {
         System.out.println("DB 커넥션 풀 연결 실패");
         e.printStackTrace();
      }
   }
   
   public void close()   {
      try {
         if (rs != null) rs.close();
         if (stmt != null) stmt.close();
         if (psmt != null) psmt.close();
         if (con != null) con.close();
         
         System.out.println("DB 커넥션 풀 자원 반납");
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}

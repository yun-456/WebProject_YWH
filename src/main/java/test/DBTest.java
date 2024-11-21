package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {
    public static void main(String[] args) {
        try {
            // Oracle JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");
            
            // 데이터베이스 연결 정보 설정
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String id = "webproject_db";
            String pwd = "1234";
            
            // 데이터베이스 연결 시도
            Connection con = DriverManager.getConnection(url, id, pwd);
            
            // Statement 객체 생성
            Statement stmt = con.createStatement();
            
            // SQL 쿼리 실행
            String query = "SELECT * FROM MVCBOARD1";
            ResultSet rs = stmt.executeQuery(query);
            
            // 결과 출력
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("ID"));
                System.out.println("Title: " + rs.getString("TITLE"));
                System.out.println("Content: " + rs.getString("CONTENT"));
                System.out.println("==========");
            }
            
            // 자원 반납
            rs.close();
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

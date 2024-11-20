package model.mvcboard1;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

//DBCP(커넥션풀)을 통해 Oracle에 연결하기 위해 상속을 받아 정의
public class MVCBoardDAO extends DBConnPool {

    // 기본생성자를 통해 부모클래스의 기본생성자 호출(생략가능)
    public MVCBoardDAO() {
        super();
    }

    // 게시물의 갯수를 카운트
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM mvcboard1";

        // 매개변수로 전달된 검색어가 있는 경우에만 where절을 동적으로 추가
        if (map.get("searchWord") != null && !map.get("searchWord").toString().isEmpty()) {
            query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            // Statement 인스턴스 생성(정적 쿼리문 실행)
            stmt = con.createStatement();
            // 쿼리문을 실행한 후 결과를 ResultSet으로 반환받는다.
            rs = stmt.executeQuery(query);
            // count()함수는 조건에 상관없이 항상 결과가 인출되므로
            // if문과 같은 조건절 없이 바로 next()함수를 실행할 수 있다.
            if (rs.next()) {
                // 반환된 결과를 저장한다.
                totalCount = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }
        return totalCount;
    }

    // 게시판 목록에 출력할 레코드를 인출하기 위한 메서드 정의
    public List<MVCBoardDTO> selectList(Map<String, Object> map) {
        List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();

        String query = "SELECT * FROM mvcboard1";
        
        if (map.get("searchWord") != null && !map.get("searchWord").toString().isEmpty()) {
            query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
        }
        query += " ORDER BY idx DESC";

        try {
            // PreparedStatement 인스턴스 생성
            psmt = con.prepareStatement(query);
            // 쿼리문 실행 및 결과반환(ResultSet)
            rs = psmt.executeQuery();

            while (rs.next()) {
                MVCBoardDTO dto = new MVCBoardDTO();

                // 데이터 추출 및 DTO에 저장
                dto.setIdx(rs.getString("idx"));
                dto.setId(rs.getString("id"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setOfile(rs.getString("ofile"));
                dto.setSfile(rs.getString("sfile"));
                dto.setDowncount(rs.getInt("downcount"));
                dto.setVisitcount(rs.getInt("visitcount"));

                board.add(dto);
            }
        } catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }
}
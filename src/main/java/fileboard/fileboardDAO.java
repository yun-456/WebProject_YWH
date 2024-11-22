package fileboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

//DBCP(커넥션풀)을 통해 Oracle에 연결하기 위해 상속을 받아 정의
public class fileboardDAO extends DBConnPool {

	// 기본생성자를 통해 부모클래스의 기본생성자 호출(생략가능)
	public fileboardDAO() {
		super();
	}

	// 게시물의 갯수를 카운트
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		// 오라클의 그룹함수는 count()를 사용해서 쿼리문 작성
		String query = "SELECT COUNT(*) FROM fileboard";
		// 매개변수로 전달된 검색어가 있는 경우에만 where절을 동적으로 추가
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			// Statement 인스턴스 생성(정적쿼리문 실행)
			stmt = con.createStatement();
			// 쿼리문을 실행한 후 결과를 ResultSet으로 반환받는다.
			rs = stmt.executeQuery(query);
			// count()함수는 조건에 상관없이 항상 결과가 인출되므로
			// if문과 같은 조건절 없이 바로 next()함수를 실행할 수 있다.
			rs.next();
			// 반환된 결과를 저장한다.
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}

	// 게시판 목록에 출력할 레코드를 인출하기 위한 메서드 정의
	public List<fileboardDTO> selectList(Map<String, Object> map) {
		// 오라클에서 인출한 레코드를 저장하기 위한 List 생성
		List<fileboardDTO> board = new Vector<fileboardDTO>();

		// 레코드 인출을 위한 쿼리문 작성
		String query = "SELECT * FROM fileboard";
		// 검색어 입력 여부에 따라서 where절은 조건부로 추가됨.
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		// 일련번호의 내림차순으로 정렬한 후 인출한다.
		query += " ORDER BY idx DESC ";
		// 게시판은 항상 최근에 작성한 게시물이 상단에 노출되어야 한다.

		try {
			// PreparedStatement 인스턴스 생성
			psmt = con.prepareStatement(query);
			// 쿼리문 실행 및 결과반환(ResultSet)
			rs = psmt.executeQuery();
			// ResultSet의 크기만큼 즉, 인출된 레코드의 갯수만큼 반복

			while (rs.next()) {
				// 하나의 레코드를 저장하기 위해 DTO인스턴스 생성
				fileboardDTO dto = new fileboardDTO();

				/*
				 * ResultSet 인스턴스에서 데이터를 추출할때 멤버변수의 타입에따라 getString(), getInt(), getDate()로 구분하여
				 * 호출한다. 이 데이터를 DTO의 setter를 이용해서 저장한다.
				 */
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setVisitcount(rs.getInt(9));

				// 레코드가 저장된 DTO를 List에 갯수만큼 추가한다.
				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		// 마지막으로 인출한 레코드를 저장한 List를 반환한다.
		return board;
	}

	// 글쓰기 처리를 위한 쿼리문 실행
	public int insertWrite(fileboardDTO dto) {
		int result = 0;
		try {
			/*
			 * default값이 있는 3개의 컬럼을 제외한 나머지 컬럼에 대해서만 insert쿼리문을 작성. 일련번호 idx의 경우에는 시퀀스를 사용
			 */
			String query = "INSERT INTO fileboard ( " + " idx, id, title, content, ofile, sfile)" + " VALUES ( "
					+ " seq_fileboard_num.NEXTVAL,?,?,?,?,?)";
			// 쿼리문을 인수로 preparedStatement 인스턴스 생성
			psmt = con.prepareStatement(query);
			// 인스턴스를 통해 인파라미터 설정
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			// 쿼리문 실행. insert쿼리의 경우 입력된 행의 갯수가 반환됨.
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	// 게시물 열람
	public fileboardDTO selectView(String idx) {
		// 인출한 레코드를 저장하기 위해 DTO 생성
		fileboardDTO dto = new fileboardDTO();
		// 내부조인(Inner join)을 이용해서 쿼리문 작성. member테이블의 name컬럼까지 포함.
		String query = "SELECT Bo.*, Ui.name FROM fileboard Bo " + "INNER JOIN user_info Ui ON Bo.id = Ui.user_id "
				+ "WHERE idx = ?"; // 쿼리문 템플릿 준비

		try {
			psmt = con.prepareStatement(query); // 쿼리문 준비
			psmt.setString(1, idx); // 인파라미터 설정
			rs = psmt.executeQuery(); // 쿼리문 실행

			// 하나의 게시물을 인출하므로 if 문으로 조건에 맞는 게시물이 있는지 확인
			if (rs.next()) {
				// 결과를 DTO 객체에 저장(member 테이블의 name 까지 저장한다)
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setVisitcount(rs.getInt(9));
				dto.setName(rs.getString(10));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto; // 결과 반환
	}

	// 주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
	public void updateVisitCount(String idx) {
		// visitcount 컬럼은 number타입이므로 산술연산이 가능함.
		// 1을 더한 결과를 컬럼에 재반영하는 형식으로 update 쿼리문 작성.
		String query = "UPDATE fileboard SET " + " visitcount=visitcount+1" + " WHERE idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			// 쿼리문 실행
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}

	public void downCountPlus(String idx) {
		String sql = "UPDATE fileboard SET " + " downcount=downcount+1 " + " WHERE idx=? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("다운로드 수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}

	public int deletePost(String idx) {
		int result = 0;
		try {
			String query = "DELETE FROM fileboard WHERE idx=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	// 게시글 데이터를 받아 DB에 저장되어 있던 내용을 갱신합니다(파일 업로드 지원).
	public int updatePost(fileboardDTO dto) {
		int result = 0;
		try {
			// 쿼리문 템플릿 준비. 회원제이므로 일련번호와 아이디까지 조건에 추가.
			String query = "UPDATE fileboard" + " SET title=?, content=?, ofile=?, sfile=? " + " WHERE idx=? and id=?";

			// 쿼리문 준비 및 인파라미터 설정
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			psmt.setString(5, dto.getIdx());
			psmt.setString(6, dto.getId());

			// 쿼리문 실행
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	public List<fileboardDTO> selectListPage(Map<String, Object> map) {
		List<fileboardDTO> board = new Vector<fileboardDTO>();
		String query = " SELECT * FROM (" + " SELECT Tb.*, ROWNUM rNUM FROM (" + "   SELECT * FROM fileboard ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		query += "   ORDER BY idx DESC " + "   ) Tb " + " ) " + " WHERE rNum BETWEEN ? AND ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();

			while (rs.next()) {
				fileboardDTO dto = new fileboardDTO();

				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setVisitcount(rs.getInt(9));

				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
}

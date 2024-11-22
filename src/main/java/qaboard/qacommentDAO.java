package qaboard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;

public class qacommentDAO extends DBConnPool {

	public qacommentDAO() {
		super();
	}

	// 댓글 추가
	public int insertComment(qacommentDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO qacomment (comment_id, board_idx, user_id, content, post_date) "
					+ "VALUES (seq_qacomment.NEXTVAL, ?, ?, ?, SYSDATE)";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, dto.getBoardIdx());
			psmt.setString(2, dto.getUserId());
			psmt.setString(3, dto.getContent());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 추가 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	// 댓글 목록 가져오기
	public List<qacommentDTO> selectComments(int boardIdx) {
		List<qacommentDTO> comments = new ArrayList<>();
		try {
			String query = "SELECT * FROM qacomment WHERE board_idx=? ORDER BY comment_id DESC";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, boardIdx);
			rs = psmt.executeQuery();
			while (rs.next()) {
				qacommentDTO dto = new qacommentDTO();
				dto.setCommentId(rs.getInt("comment_id"));
				dto.setBoardIdx(rs.getInt("board_idx"));
				dto.setUserId(rs.getString("user_id"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getDate("post_date"));
				comments.add(dto);
			}
		} catch (Exception e) {
			System.out.println("댓글 목록 조회 중 예외 발생");
			e.printStackTrace();
		}
		return comments;
	}

	// 댓글 삭제
	public int deleteComment(int commentId) {
		int result = 0;
		try {
			String query = "DELETE FROM qacomment WHERE comment_id=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, commentId);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	// 댓글 수정
	public int updateComment(qacommentDTO dto) {
		int result = 0;
		try {
			String query = "UPDATE qacomment SET content=? WHERE comment_id=? AND user_id=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getContent());
			psmt.setInt(2, dto.getCommentId());
			psmt.setString(3, dto.getUserId());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	// 특정 댓글 가져오기
	// 댓글 조회 메서드 추가
	public qacommentDTO getCommentById(String commentId) {
		qacommentDTO dto = null;
		try {
			String query = "SELECT * FROM qacomment WHERE comment_id=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, commentId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new qacommentDTO();
				dto.setCommentId(rs.getInt("comment_id"));
				dto.setBoardIdx(rs.getInt("board_idx"));
				dto.setUserId(rs.getString("user_id"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getDate("post_date"));
			}
		} catch (Exception e) {
			System.out.println("댓글 조회 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}

}

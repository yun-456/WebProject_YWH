package membership;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect {

    public MemberDAO(String drv, String url, String id, String pw) {
        super(drv, url, id, pw);
    }

    public MemberDAO(ServletContext application) {
        super(application);
    }

    public MemberDTO getMemberDTO(String uid, String upass) {
        MemberDTO dto = null;
        String query = "SELECT * FROM user_info WHERE user_id = ? AND password = ? AND status = 'ACTIVE'";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, uid);
            psmt.setString(2, upass);
            rs = psmt.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setId(rs.getString("user_id"));
                dto.setPass(rs.getString("password"));
                dto.setName(rs.getString("name"));
                dto.setPhone(rs.getString("phone"));
                dto.setGender(rs.getString("gender"));
                dto.setStatus(rs.getString("status"));
                dto.setEmail(rs.getString("email"));
            } else {
                System.out.println("로그인 실패: 사용자 정보를 찾을 수 없습니다. ID: " + uid);
            }
        } catch (Exception e) {
            System.out.println("로그인 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        return dto;
    }

    public boolean registerMember(MemberDTO memberDTO) {
        boolean isRegistered = false;
        String query = "INSERT INTO user_info (user_id, password, name, birth_date, gender, phone, email, status) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, 'ACTIVE')";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, memberDTO.getId());
            psmt.setString(2, memberDTO.getPass());
            psmt.setString(3, memberDTO.getName());
            psmt.setString(4, memberDTO.getBirthDate());
            psmt.setString(5, memberDTO.getGender());
            psmt.setString(6, memberDTO.getPhone());
            psmt.setString(7, memberDTO.getEmail());

            int result = psmt.executeUpdate();
            isRegistered = result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    public boolean updateMember(MemberDTO memberDTO) {
        boolean isUpdated = false;
        String query = "UPDATE user_info SET password = ?, name = ?, phone = ?, birth_date = ?, gender = ?, email = ?, updated_at = SYSDATE WHERE user_id = ?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, memberDTO.getPass());
            psmt.setString(2, memberDTO.getName());
            psmt.setString(3, memberDTO.getPhone());
            psmt.setString(4, memberDTO.getBirthDate());
            psmt.setString(5, memberDTO.getGender());
            psmt.setString(6, memberDTO.getEmail());
            psmt.setString(7, memberDTO.getId());

            int result = psmt.executeUpdate();
            isUpdated = result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public MemberDTO UserUpdate(String userId) {
        MemberDTO dto = null;
        String query = "SELECT * FROM user_info WHERE user_id = ? AND status = 'ACTIVE'";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setId(rs.getString("user_id"));
                dto.setPass(rs.getString("password"));
                dto.setName(rs.getString("name"));
                dto.setPhone(rs.getString("phone"));
                dto.setBirthDate(rs.getString("birth_date"));
                dto.setGender(rs.getString("gender"));
                dto.setStatus(rs.getString("status"));
                dto.setUpdatedAt(rs.getString("updated_at"));
                dto.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}

package membership;

public class MemberDTO {
    private String id;
    private String pass;
    private String name;
    private String phone;
    private String birthDate;
    private String gender;
    private String status;
    private String updatedAt;
    private String email;

    // 기본 생성자
    public MemberDTO() {
    }

    // 모든 필드를 초기화하는 생성자
    public MemberDTO(String id, String pass, String name, String phone, String birthDate, String gender, String status, String updatedAt, String email) {
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.status = status;
        this.updatedAt = updatedAt;
        this.email = email;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        if ("M".equalsIgnoreCase(gender) || "F".equalsIgnoreCase(gender)) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("성별은 'M' 또는 'F'이어야 합니다.");
        }
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

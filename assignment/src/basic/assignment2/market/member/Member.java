package basic.assignment2.market.member;

public class Member {
    // private으로 선언하여 외부에서 직접 접근 방지
    private long memerId;   // 회원 고유 ID (식별자)
    private String name;    // 회원 이름
    private Grade grade;    // 회원 등급 (BASIC, VIP)

    public Member(long memerId, String name, Grade grade) {
        this.memerId = memerId;
        this.name = name;
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public long getMemerId() {
        return memerId;
    }

    public String getName() {
        return name;
    }
}

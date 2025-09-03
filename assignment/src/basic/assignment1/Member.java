package basic.assignment1;

public class Member {
    // 이전 답변과 동일
    private long memberId;
    private String name;
    private Grade grade;

    public Member(long memberId, String name, Grade grade) {
        this.memberId = memberId;
        this.name = name;
        this.grade = grade;
    }

    public long getMemberId() { return memberId; }
    public String getName() { return name; }
    public Grade getGrade() { return grade; }
}

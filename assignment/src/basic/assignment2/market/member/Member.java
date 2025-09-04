package basic.assignment2.market.member;

public class Member {
    private long memerId;
    private String name;
    private Grade grade;

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

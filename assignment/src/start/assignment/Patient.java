package start.assignment;

/*
환자 한 명의 정보(ID, 이름, 성별, 나이, 혈액형 정보)를 저장하기 위한 객체입니다.
모든 필드는 private으로 선언하여 외부로부터 데이터를 보호합니다.
*/
public class Patient {
    private String pNum;
    private String pName;
    private String pSex;
    private int pAge;
    private String pBloodType;

    public Patient(String pNum, String pName, String pSex, int pAge, String pBloodType) {
        this.pNum = pNum;
        this.pName = pName;
        this.pSex = pSex;
        this.pAge = pAge;
        this.pBloodType = pBloodType;
    }

    public String getpNum() {
        return pNum;
    }

    public String getpName() {
        return pName;
    }

    public String getpSex() {
        return pSex;
    }

    public int getpAge() {
        return pAge;
    }

    public String getpBloodType() {
        return pBloodType;
    }
}

package mid1.assignment5.com.importer.service;

public class DataSource {
    private final String[] data;

    public DataSource(String[] data) {
        this.data = data;
    }

    public String[] read() {
        return this.data;
    }

    public void close() {
        System.out.println("데이터 소스를 닫습니다.");
    }
}

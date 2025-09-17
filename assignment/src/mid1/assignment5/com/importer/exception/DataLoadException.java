package mid1.assignment5.com.importer.exception;

public class DataLoadException extends FileException {
    // 데이터 소스를 열거나 읽을 수 없을 경우
    public DataLoadException(String message) {
        super(message);
    }
}

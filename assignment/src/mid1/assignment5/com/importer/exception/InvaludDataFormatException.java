package mid1.assignment5.com.importer.exception;

public class InvaludDataFormatException extends FileException{
    // 필드 개수 부족, 숫자 형식 오류
    public InvaludDataFormatException(String message) {
        super(message);
    }
}

package mid1.assignment5.com.importer.service;

import mid1.assignment5.com.importer.data.User;
import mid1.assignment5.com.importer.data.UserRepository;
import mid1.assignment5.com.importer.exception.DataLoadException;
import mid1.assignment5.com.importer.exception.InvaludDataFormatException;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void importUsers(DataSource dataSource) throws DataLoadException {
        try {
            isDataSourceNull(dataSource); //dataSource가 null 이면 throw
            String[] readDate = dataSource.read();
            for (String line : readDate) {
                try {
                    User user = parseUser(line);
                    repository.addUser(user);
                } catch(InvaludDataFormatException e) {
                    System.out.println("[경고] 데이터 형식이 잘못되어 해당 줄을 건너뜁니다. 입력: '" + e.getMessage() + "'");
                }
            }
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }

    private User parseUser(String line) throws InvaludDataFormatException {
        String[] userInfo = line.split(",");
        isWrongUserLine(userInfo, line);
        return new User(userInfo[0], userInfo[1], Integer.parseInt(userInfo[2]));
    }


    private void isDataSourceNull(DataSource dataSource) {
        if (dataSource == null) {
            throw new DataLoadException("데이터 소스가 " + dataSource + "입니다.");
        }
    }

    private void isWrongUserLine(String[] userInfo, String line) {
        // 필드 개수 문제
        if (userInfo.length != 3) {
            throw new InvaludDataFormatException(line);
        } else if (isNotNum(userInfo[2])) {
            throw new InvaludDataFormatException(line);
        }
    }

    private boolean isNotNum(String line) {
        for (char c : line.toCharArray()) {
            if (!isDigit(c)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isDigit(char c) {
        return Character.isDigit(c);
    }
}

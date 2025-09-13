package mid1.assignment2.com.dataparser.domain;

import java.util.Objects;

public class UserData {
    private final String id;
    private final String name;
    private final Integer age;
    private final Boolean isActive;

    public UserData(String id, String name, Integer age, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        UserData userData = (UserData) object;
        return Objects.equals(id, userData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

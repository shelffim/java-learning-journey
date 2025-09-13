package mid1.assignment2.com.dataparser.service;

import mid1.assignment2.com.dataparser.domain.UserData;

public class DataProcessorService {
    UserData[] userData;

    public void parseAndAddUsers(String csvData) {
        String[] splitLineCsvData = csvData.split("\n");
        userData = new UserData[splitLineCsvData.length];

        String[][] splitCommaCsvData = new String[splitLineCsvData.length][splitLineCsvData[0].length()];
        for (int i = 0; i < splitCommaCsvData.length; i++) {
            splitCommaCsvData[i] = splitLineCsvData[i].split(",");
        }

        for (int i = 0; i < splitCommaCsvData.length; i++) {
            String id = splitCommaCsvData[i][0].trim();
            String name = splitCommaCsvData[i][1].trim();
            Integer age = Integer.parseInt(splitCommaCsvData[i][2].trim());
            Boolean isActive = Boolean.parseBoolean(splitCommaCsvData[i][3].trim());

            userData[i] = new UserData(id, name, age, isActive);
        }
    }

    public double calculateActiveUserAverageAge() {
        double sum = 0.0;
        int count = 0;
        double average = 0.0;
        for (int i = 0; i < userData.length; i++) {
            if (userData[i].getActive()) {
                sum += userData[i].getAge();
                count++;
            }
        }
        average = sum / count;
        return average;
    }
}

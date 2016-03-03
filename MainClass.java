package com_;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Properties;

public class MainClass {
    public static void main(String... args) throws IOException {

        String fs = System.getProperty("file.separator");
        String pathToFile = "C:" + fs + "Users" + fs + "Viktor" + fs + "Desktop" + fs + "parameters.txt";

        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int index = 0;
        String[] tempArray = new String[index];
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            index++;
            tempArray = Arrays.copyOf(tempArray, index);
            tempArray[index - 1] = line;
        }
        bufferedReader.close();

        //showArrayOfLines(tempArray);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Properties properties = new Properties();

        InputStream resourceAsStream = MainClass.class.getClassLoader().getResourceAsStream("user_config_data_set.properties");
        properties.load(resourceAsStream);

        for (int i = 0; i < tempArray.length; i++) {
            if (properties.get(tempArray[i]) != null) {
                tempArray[i] = tempArray[i] + " " + properties.get(tempArray[i]);
            } else {
                tempArray[i] = tempArray[i] + " NOT AVAILABLE";
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String userName = System.getProperty("user.name");

        File outputFile = new File(userName);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        //FileWriter fileWriter = new FileWriter(outputFile, true);
        FileWriter fileWriter = new FileWriter(outputFile);

        for (String value : tempArray) {
            fileWriter.append(value + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static void showArrayOfLines(String[] arraysOfLines) {
        for (String value : arraysOfLines) {
            System.out.println(value);
        }
    }
}

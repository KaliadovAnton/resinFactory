package com.anton.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ResinRepository {
    public String getTypeOfResin(String resinName) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/resin.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String name = properties.getProperty(resinName);
        fis.close();
        return name;
    }

    public double getMinValue(String resinType) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/resin.properties");
        Properties properties = new Properties();
        properties.load(fis);
        double value = Double.parseDouble(properties.getProperty(resinType + "Min"));
        fis.close();
        return value;
    }

    public double getMaxValue(String resinType) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/resin.properties");
        Properties properties = new Properties();
        properties.load(fis);
        double value = Double.parseDouble(properties.getProperty(resinType + "Max"));
        fis.close();
        return value;
    }
}

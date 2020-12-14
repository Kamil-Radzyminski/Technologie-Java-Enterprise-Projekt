package com.kamilradzyminski.projekt.utitles;

import com.kamilradzyminski.projekt.domain.Person;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvToXmlParser {
    public static void parserXml() {

        StringBuilder xml = new StringBuilder();
        ArrayList<Person> personList = new ArrayList<>();

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/main/resources/PersonOne_1.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(Person.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        personList = (ArrayList<Person>) csvToBean.parse();

        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\">\n\n");

        for (int i = 0; i < personList.size(); i++) {
            xml.append("\t<bean id=\"");
            xml.append(personList.get(i).getId());
            xml.append("\" class=\"com.kamilradzyminski.projekt.domain.Person\"");
            xml.append(">\n");

            //id
            xml.append("\t\t<constructor-arg name=\"id\"");
            xml.append(" value=\"");
            xml.append(personList.get(i).getId()).append("\"");
            xml.append("/>\n");

            //firstname
            xml.append("\t\t<constructor-arg name=\"firstName\"");
            xml.append(" value=\"");
            xml.append(personList.get(i).getFirstName()).append("\"");
            xml.append("/>\n");

            //lastname
            xml.append("\t\t<constructor-arg name=\"lastName\"");
            xml.append(" value=\"");
            xml.append(personList.get(i).getLastName()).append("\"");
            xml.append("/>\n");

            //email
            xml.append("\t\t<constructor-arg name=\"email\"");
            xml.append(" value=\"");
            xml.append(personList.get(i).getEmail()).append("\"");
            xml.append("/>\n");

            //gender
            xml.append("\t\t<constructor-arg name=\"gender\"");
            xml.append(" value=\"");
            xml.append(personList.get(i).getGender()).append("\"");
            xml.append("/>\n");

            //creditCardType
            xml.append("\t\t<constructor-arg name=\"creditCardType\"");
            xml.append(" value=\"");
            xml.append(personList.get(i).getCreditCardType()).append("\"");
            xml.append("/>\n");

            //creditCardNumber
            xml.append("\t\t<constructor-arg name=\"creditCardNumber\"");
            xml.append(" value=\"");
            xml.append(personList.get(i).getCreditCardNumber()).append("\"");
            xml.append("/>\n");

            xml.append("\t</bean>\n");
        }

        xml.append("</beans>");
        //System.out.println(xml.toString());

        File file = new File("src/main/resources/beans.xml");
        try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
            write.write(xml.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> loadList(MultipartFile file) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            return importList(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static ArrayList<Person> loadList() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/PersonOne_1.csv"));
            return importList(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static ArrayList<Person> importList(Reader reader) {

        ArrayList<Person> personList = new ArrayList<>();

        try (reader) {
            CsvToBean csvToBean = new CsvToBeanBuilder<>(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return (ArrayList<Person>) csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void exportList(List<Person> personList, String path) {
        Path file = Paths.get(path);

        try (var writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(personList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

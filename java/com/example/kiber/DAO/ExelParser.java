package com.example.kiber.DAO;

import com.example.kiber.Model.city;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;


@Component
public class ExelParser {
    public static ArrayList<city> parse(String fileName) {
        ArrayList<city> arrayListCity = new ArrayList<>();
        InputStream inputStream = null;
        XSSFWorkbook workbook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        it.next();
        int problem = 0;
        while (it.hasNext()) {
            city obj = new city();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("HH:mm:ss");
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            int i = 0;
            while (cells.hasNext()) {
                Cell cell = cells.next();
                if(i >= 5) break;
                switch (i) {
                    case 0:
                        try {
                            obj.setDay((int)cell.getNumericCellValue());
                        } catch (Exception e) {
                            System.out.println(arrayListCity.get(arrayListCity.size()-1));
                            obj.setDay((int)cell.getNumericCellValue());
                        }
                        break;
                    case 1:
                        obj.setTime(formatForDateNow.format(cell.getDateCellValue().getTime()));
                        break;
                    case 2:
                        try {
                            obj.setTemperature((int) cell.getNumericCellValue());
                        } catch (Exception e){
                            double a = arrayListCity.stream().collect(Collectors.averagingInt(city::getTemperature));
                            obj.setTemperature((int)a);
                        }
                        break;
                    case 3:
                        try {
                            obj.setDirection(cell.getStringCellValue());
                        } catch (Exception e) {
                            problem++;
                            double a = arrayListCity.stream().collect(Collectors.averagingInt(city::getSpeed));
                            try {
                                obj.setSpeed((int) a);
                                obj.setDirection(arrayListCity.get(arrayListCity.size()-1).getDirection());
                                // На случай когда мы в самом начале словили пропуск
                            } catch (Exception e1) {
                                continue;
                            }
                        }
                        break;
                    case 4:
                        try{
                            if((int)cell.getNumericCellValue() < 0) obj.setSpeed((int)cell.getNumericCellValue() * -1);
                            else obj.setSpeed((int)cell.getNumericCellValue());
                            if (problem != 0){
                                // если пропуск в самом начале
                                if ((arrayListCity.size() - problem) == 0) ; else problem /= 2;
                                while(problem != 0){
                                    arrayListCity.get(arrayListCity.size()-problem).setDirection(obj.getDirection());
                                    problem--;
                                }
                            }
                        } catch (Exception e){
                            try{
                                obj.setSpeed(arrayListCity.get(arrayListCity.size()-1).getSpeed());
                            } catch (Exception e2) {
                                obj.setSpeed(0);
                            }
                        }
                        break;
                }
                i++;
            }
            arrayListCity.add(obj);
        }
        return arrayListCity;
    }
}


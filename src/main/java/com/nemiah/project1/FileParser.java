/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author nemiah
 */
public class FileParser {

    private final String enemyList = "resources/names.csv";
    private final File enemyFile = new File(enemyList);

    public FileParser() {
        
    }

    //Check if File exists
    private boolean checkFile(File file) {
        return file.exists();
    }

    //Load Enemy Namelist //List of names in names.csv given by ChatGPT
    public ArrayList loadEnemyList() {
        //Create Arraylist, Check for file existence 
        ArrayList<String> enemyNames = new ArrayList();
        if (!checkFile(enemyFile)) {
            return enemyError();
        }
        //Load names.csv to list
        try (CSVParser parser = new CSVParser(new FileReader(enemyFile), CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                enemyNames.add(record.get(0));
            }
        } catch (IOException e) {
            return enemyError();
        }

        return enemyNames;
    }

    //On Case Enemy File is Missing, Return blank names list
    private ArrayList enemyError() {
        ArrayList<String> enemyError = new ArrayList();
        enemyError.add("");
        return enemyError;
    }

}

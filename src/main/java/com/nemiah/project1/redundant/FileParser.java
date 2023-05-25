package com.nemiah.project1.redundant;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author nemiah
 */
public class FileParser {

    private final Player player;
    private final Pet pet;
    private final String playerSave = "saves/playersave.csv";
    private final File playerFile = new File(playerSave);
    private final String petSave = "saves/petsave.csv";
    private final File petFile = new File(petSave);
    private final String enemyList = "resources/names.csv";
    private final File enemyFile = new File(enemyList);

    public FileParser(Player player, Pet pet) {
        this.player = player;
        this.pet = pet;
    }

    public FileParser() {
        this.player = new Player();
        this.pet = new Pet();
    }

    //Make Save File Directory
    private void makeDir() {
        playerFile.getParentFile().mkdirs();
    }

    //Check if File exists
    private boolean checkFile(File file) {
        return file.exists();
    }

    //Write to Save Files
    public void writeSave() {
        writePlayerSave();
        writePetSave();
    }

    //Parse playersave to Player object
    public Player loadPlayerSave() {
        //If file doesn't exist, write new one
        if (!checkFile(playerFile)) {
            writePlayerSave();
        }

        //Load Player Object from playerFile
        Player loadPlayer = new Player();
        try (CSVParser parser = new CSVParser(new FileReader(playerSave), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                //("name","dungeonLevel","food")
                String name = record.get("name");
                int dungeonLevel = Integer.parseInt(record.get("dungeonLevel"));
                int food = Integer.parseInt(record.get("food"));

                loadPlayer = new Player(name, dungeonLevel, food);
            }
        } catch (IOException e) {

        }

        return loadPlayer;
    }

    //Write New Player Save
    private void writePlayerSave() {
        //Make Folder if playersave doesn't exist
        if (!checkFile(playerFile)) {
            makeDir();
        }
        //Write new Player Save with Header - name,dungeonLevel,food
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(playerFile), CSVFormat.DEFAULT.withHeader("name", "dungeonLevel", "food"))) {
            printer.printRecord(player.getName(), Integer.toString(player.getDungeonLevel()), Integer.toString(player.getFood()));
            printer.flush();
        } catch (IOException e) {

        }
    }

    //Parse petsave to Pet object
    public Pet loadPetSave() {
        //If file doesn't exist, write new one
        if (!checkFile(petFile)) {
            writePetSave();
        }
        //Load Pet Object from petFile
        try (CSVParser parser = new CSVParser(new FileReader(petSave), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                //("name","health","attack","defense","specialAttack","specialDefense","luck","level","exp","hunger","mood")
                String name = record.get("name");
                int health = Integer.parseInt(record.get("health"));
                int attack = Integer.parseInt(record.get("attack"));
                int defense = Integer.parseInt(record.get("defense"));
                int specialAttack = Integer.parseInt(record.get("specialAttack"));
                int specialDefense = Integer.parseInt(record.get("specialDefense"));
                int luck = Integer.parseInt(record.get("luck"));
                int level = Integer.parseInt(record.get("level"));
                int exp = Integer.parseInt(record.get("exp"));
                int hunger = Integer.parseInt(record.get("hunger"));
                int mood = Integer.parseInt(record.get("mood"));

                Pet loadPet = new Pet(name, health, attack, defense, specialAttack, specialDefense, luck, level, exp, hunger, mood);
                return loadPet;
            }
        } catch (IOException e) {
        }
        return null;
    }

    //Write new Pet Save
    private void writePetSave() {
        //If file doesn't exist, create save Folder
        if (!checkFile(petFile)) {
            makeDir();
        }

        //Write Pet CSV with Header - name,health,attack,defense,specialAttack,specialDefense,luck,level,exp,hunger,mood
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(petFile), CSVFormat.DEFAULT.withHeader("name", "health", "attack", "defense", "specialAttack", "specialDefense", "luck", "level", "exp", "hunger", "mood"))) {
            printer.printRecord(pet.getName(), pet.getHealth(), pet.getAttack(), pet.getDefense(), pet.getSpecialAttack(), pet.getSpecialDefense(), pet.getLuck(),
                    pet.getLevel(), pet.getExp(), pet.getHunger(), pet.getMood());
            printer.flush();
        } catch (IOException e) {

        }
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

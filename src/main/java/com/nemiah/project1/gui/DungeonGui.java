/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.State;
import com.nemiah.project1.data.Commands;
import com.nemiah.project1.data.DungeonData;
import com.nemiah.project1.entitiesbase.Enemy;
import com.nemiah.project1.entitiesbase.EntityBase;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author nemiah
 */
public class DungeonGui extends BaseGui {

    DungeonData dungeonData;

    //Dungeon Variables
    Enemy enemy;

    //JLabels
    JLabel petName, petHealth, petAttack, petDefense, petSPAttack,
            petSPDefense, petLuck, petLevel;
    
    //Dungeon Gui
    JLabel playerActionLabel, battleResultLabel, specialAttackCharge;
    JLabel enemyName, enemyHealth, enemyAction;
    JButton attackButton, defendButton, specialAttackButton;

    public DungeonGui() {
        super(State.DUNGEON);
        dungeonData = new DungeonData();
        dungeonData.initializeRound();
        //Show Panel
        startPanel();
    }
    
    @Override
    protected void initialize() {
        //Manage Layout
        MigLayout layout = new MigLayout("insets 10 75 10 25", "", "");
        panel.setLayout(layout);
        
        //Title
        JLabel roomTitle = setTitle("DUNGEON");
        panel.add(roomTitle, "cell 0 0, left, wrap, span");
        
        //Render Enemy
        initializeEnemy();
        
        //Enemy Action
        enemyAction = setGenericLabel("");
        panel.add(enemyAction,"wrap, span");
        //Player Action
        playerActionLabel = setGenericLabel(" ");
        panel.add(playerActionLabel, "wrap, span");
        
        //Buttons
        //Player Attack
        attackButton = setGenericButton("Attack");
        attackButton.addActionListener((ActionEvent e) -> {
            buttonAction(Commands.ATTACK);
        });
        
        defendButton = setGenericButton("Defend");
        defendButton.addActionListener((ActionEvent e) -> {
           buttonAction(Commands.DEFEND);
        });
        specialAttackButton = setGenericButton("Special Attack");
        specialAttackButton.addActionListener((ActionEvent e) -> {
            specialAction();
        });
        
        //Add Special Attack Charge
        specialAttackCharge = setGenericLabel("Special Attack Charge : " + dungeonData.getSpecialCounter() + "/5");
        panel.add(specialAttackCharge,"cell 0 19, left, span");
        //Add buttons to Panel
        panel.add(attackButton,"cell 0 20, left");
        panel.add(defendButton,"");
        panel.add(specialAttackButton, "span");
        
        //Render Pet
        initializePetStats();
        
        //Battle Result
        battleResultLabel = setGenericLabel(" ");
        panel.add(battleResultLabel, "wrap, span");
    }

    private void buttonAction(Commands command){
        playerActionLabel.setText(dungeonData.parseInput(command));
        updateEnemy();
        updatePetStats();
        updateActions();
    }
    
    private void specialAction(){
        if (dungeonData.checkSpecial()){
            buttonAction(Commands.SPECIAL);
        } else {
            playerActionLabel.setText(dungeonData.parseInput(Commands.SPECIAL));
        }
    }
    
    private void updateActions(){
        String battleResult = "";
        if (dungeonData.checkConditions() == -1){
            battleResult = dungeonData.getDungeonPet().getName() + " can no longer fight.";
            endDungeon();
        } else if (dungeonData.checkConditions() == 1){
            battleResult = dungeonData.getCurEnemy().getName() + " has been slain.";
        }
        dungeonData.resetRound();
        battleResultLabel.setText(battleResult);
        specialAttackCharge.setText("Special Attack Charge : " + dungeonData.getSpecialCounter() + "/" + dungeonData.getSpecialCounterLimit());
        
        //If reached Dungeon End.
        if (dungeonData.getEndDungeon()){
            endDungeon();
        }
    }
    
    private void endDungeon(){
        battleResultLabel.setText("End Reached. Ending Dungeon.");
        
        panel.remove(attackButton);
        panel.remove(defendButton);
        panel.remove(specialAttackButton);
        panel.repaint();
        JButton quitButton = setGenericButton("Quit");
        quitButton.addActionListener((ActionEvent e) -> {
            dungeonData.parseInput(Commands.QUIT);
        });
        panel.add(quitButton, "span");
    }
    
    private String printEnemyAction(){
        return dungeonData.getEnemyInput();
    }
    
    private void updatePetStats() {
        petName.setText(dungeonData.getDungeonPet().getName());
        petHealth.setText("Health : " + dungeonData.getDungeonPet().getHealth());
        petAttack.setText("Attack : " + dungeonData.getDungeonPet().getAttack());
        petDefense.setText("Defense : " + dungeonData.getDungeonPet().getDefense());
        petSPAttack.setText("SP Atk : " + dungeonData.getDungeonPet().getSpecialAttack());
        petSPDefense.setText("SP Def : " + dungeonData.getDungeonPet().getSpecialDefense());
        petLuck.setText("Luck : " + dungeonData.getDungeonPet().getLuck());
        petLevel.setText("Level : " + dungeonData.getDungeonPet().getLevel());
    }
    
    private void initializePetStats(){
        //Initialize Pet Stats
        petName = setGenericLabel("");
        petHealth = setGenericLabel("");
        petAttack = setGenericLabel("");
        petDefense = setGenericLabel("");
        petSPAttack = setGenericLabel("");
        petSPDefense = setGenericLabel("");
        petLuck = setGenericLabel("");
        petLevel = setGenericLabel("");
        updatePetStats();

        //Stats
        panel.add(petName, "cell 0 22, left, wrap");
        panel.add(petHealth, "left");
        panel.add(petAttack, "left");
        panel.add(petDefense, "left, wrap");
        panel.add(petSPAttack, "left");
        panel.add(petSPDefense, "left");
        panel.add(petLuck, "left, wrap");
        panel.add(petLevel, "left");
    }
    
    private void updateEnemy(){
        enemy = dungeonData.getCurEnemy();
        enemyName.setText(enemy.getName());
        enemyHealth.setText("Health : " + enemy.getHealth());
        enemyAction.setText(printEnemyAction());
    }
    
    private void initializeEnemy(){
        enemy = dungeonData.getCurEnemy();
        enemyName = setGenericLabel(enemy.getName());
        enemyHealth = setGenericLabel("Health : " + enemy.getHealth());
        panel.add(enemyName, "cell 0 10, wrap, span");
        panel.add(enemyHealth, "wrap, span");
    }
}

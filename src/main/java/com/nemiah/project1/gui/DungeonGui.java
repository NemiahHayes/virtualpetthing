/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.gui;

import com.nemiah.project1.State;
import com.nemiah.project1.data.DungeonData;
import com.nemiah.project1.entitiesbase.Enemy;
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
    boolean analyze;

    //JLabels
    JLabel petName, petHealth, petAttack, petDefense, petSPAttack,
            petSPDefense, petLuck, petLevel, petHunger, petMood;

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
        renderEnemy();
        
        //Enemy Action
        JLabel enemyAction = setGenericLabel("");
        enemyAction.setText(printEnemyAction());
        panel.add(enemyAction,"wrap, span");
        //Player Action
        JLabel playerAction = new JLabel();
        //Buttons
        JButton attackButton = setGenericButton("Attack");
        JButton defendButton = setGenericButton("Defend");
        JButton specialAttackButton = setGenericButton("Special Attack");
        panel.add(attackButton,"cell 0 18, left");
        panel.add(defendButton,"");
        panel.add(specialAttackButton,"span");
        //Render Pet
        renderPetStats();
    }
    
    private String printEnemyAction(){
        return dungeonData.getEnemyInput();
    }
    
    private void renderPetStats(){
        //Pet Stats
        petName = setGenericLabel(dungeonData.getPet().getName());
        petHealth = setGenericLabel("Health : " + dungeonData.getPet().getHealth());
        petAttack = setGenericLabel("Attack : " + dungeonData.getPet().getAttack());
        petDefense = setGenericLabel("Defense : " + dungeonData.getPet().getDefense());
        petSPAttack = setGenericLabel("SP Atk : " + dungeonData.getPet().getSpecialAttack());
        petSPDefense = setGenericLabel("SP Def : " + dungeonData.getPet().getSpecialDefense());
        petLuck = setGenericLabel("Luck : " + dungeonData.getPet().getLuck());
        petLevel = setGenericLabel("Level : " + dungeonData.getPet().getLevel());

        //Stats
        panel.add(petName, "cell 0 20, left, wrap");
        panel.add(petHealth, "left");
        panel.add(petAttack, "left");
        panel.add(petDefense, "left, wrap");
        panel.add(petSPAttack, "left");
        panel.add(petSPDefense, "left");
        panel.add(petLuck, "left, wrap");
        panel.add(petLevel, "left");
    }
    
    
    
    private void renderEnemy(){
        Enemy enemy = pullEnemy();
        
        JLabel enemyName = setGenericLabel(enemy.getName());
        panel.add(enemyName, "cell 0 10, wrap, span");
        
        analyze = dungeonData.getAnalyze();
        if (analyze){
            
        }
    }
    
    private Enemy pullEnemy(){
        return dungeonData.getCurEnemy();
    }
    
}

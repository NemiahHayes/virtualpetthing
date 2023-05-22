/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.Entities.Pet;
import com.nemiah.project1.Entities.Player;
import java.util.EnumSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.schema.spi.SchemaManagementException;

/**
 *
 * @author nemiah
 */
public class DBMain {

    private SessionFactory sessionFactory;
    private final StandardServiceRegistry registry;
    private Player player;
    private Pet pet;

    public DBMain(Player player, Pet pet) {
        this.player = player;
        this.pet = pet;
        registry = initRegistry();
        initializeDB();
    }

    //Getter and Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private StandardServiceRegistry initRegistry() {
        // Create the StandardServiceRegistry
        return new StandardServiceRegistryBuilder().configure().build();
    }

    //Initalize DB -- Credit to ChatGPT
    private void initializeDB() {
        try {
            // Create the MetadataSources
            MetadataSources metadataSources = initMetadata();
            Metadata metadata = metadataSources.buildMetadata();
            
            //Initialize SessionFactory
            sessionFactory = metadata.buildSessionFactory();

            // Create the schema exporter
            SchemaExport schemaExport = initSchemaExport();

            //Create export only if table doesn't exist
            if (!tableExists()) {
                schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
            }
            addEntities(player, pet);
        } catch (SchemaManagementException e) {
            e.printStackTrace();
        } finally {
            // Shutdown the registry
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    //Create SchemaExport
    private SchemaExport initSchemaExport() {
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setHaltOnError(true);

        return schemaExport;
    }

    //Create Metadata
    private MetadataSources initMetadata() {
        MetadataSources metadataSources = new MetadataSources(registry);
        // Add the entity classes
        metadataSources.addAnnotatedClass(Player.class);
        metadataSources.addAnnotatedClass(Pet.class);
        return metadataSources;
    }

    //Check if Tabes Exist
    private boolean tableExists() {
        try (Session session = sessionFactory.openSession()) {
            session.createNativeQuery("SELECT 1 FROM PLAYERS LIMIT 1").uniqueResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Add Players to DB
    private void addEntities(Player player, Pet pet) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean valid = false;
        try {
            tx = session.beginTransaction();
            session.save(player);
            session.save(pet);
            tx.commit();
            valid = true;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error : " + ex);
            ex.printStackTrace();
        }
        sessionFactory.close();
    }

    private void endSession() {
        sessionFactory.close();
    }

}

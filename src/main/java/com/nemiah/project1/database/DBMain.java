/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nemiah.project1.database;

import com.nemiah.project1.entitiesbase.Pet;
import com.nemiah.project1.entitiesbase.Player;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
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
    
    public DBMain() {
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
    
    public Pet getPet(){
        return pet;
    }
    
    public void setPet(Pet pet){
        this.pet = pet;
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

    //Add Entities to DB
    public void addEntities(Player player, Pet pet) {
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
    }
    
    //Query All Players
    public List<Player> queryAllPlayers(){
        try(Session session = sessionFactory.openSession()){
            //Call Query using Criteria Builder 
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
            Root<Player> root = criteriaQuery.from(Player.class);
            criteriaQuery.select(root);
            Query<Player> query = session.createQuery(criteriaQuery);
            return query.list();
        } catch(Exception e){
            System.out.println("Error : " + e);
            return null;
        }
    }
    
    //Query Player by Name
    public Player queryPlayerName(String playerName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
            Root<Player> root = query.from(Player.class);

            query.select(root);
            query.where(criteriaBuilder.equal(root.get("name"), playerName));

            List<Player> players = session.createQuery(query).getResultList();
            if (!players.isEmpty()) {
                return players.get(0);
            } else {
                return null;
            }
        } catch (Exception ex){
            System.out.println("Error : " + ex);
            return null;
        }
    }
    
    //Query Pet by id
    public Pet queryPetId(int petId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Pet> query = criteriaBuilder.createQuery(Pet.class);
            Root<Pet> root = query.from(Pet.class);

            query.select(root);
            query.where(criteriaBuilder.equal(root.get("id"), petId));

            return session.createQuery(query).uniqueResult();
        } catch(Exception ex){
            System.out.println("Error : " + ex);
            return null;
        }
    }

    private void endSession() {
        sessionFactory.close();
    }

}

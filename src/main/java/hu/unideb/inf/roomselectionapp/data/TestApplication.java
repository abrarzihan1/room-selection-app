package hu.unideb.inf.roomselectionapp.data;

import java.sql.SQLException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.SortedMap;

public class TestApplication {
    public static void main(String[] args)  {
        try{

        startDatabase();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();



        Teacher teacher = new Teacher("Adamko","adamko369@gmail.com","Computer Science");
        Room room = new Room("ik-sq22",60,"Ik-201");


        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.persist(room);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();



        System.out.println("Teacher Added Succesfully");

    } catch (SQLException e){
        e.printStackTrace();}



    }
    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp","-web","-ifNotExists");
    }
}

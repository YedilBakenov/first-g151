package com.main.first_project_g151.db;

import com.main.first_project_g151.model.Fighter;
import org.springframework.data.domain.PageRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private static Connection connection;

    private static String userName  = "postgres";
    private static String password  = "postgres";
    private static String url  = "jdbc:postgresql://localhost:5432/g151";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static List<Fighter> getAllFighters(){

        List<Fighter> fighters = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fighters");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Fighter fighter = new Fighter();

                fighter.setId(resultSet.getInt("id"));
                fighter.setAge(resultSet.getInt("age"));
                fighter.setStyle(resultSet.getString("style"));
                fighter.setFullName(resultSet.getString("full_name"));
                fighter.setIsActive(resultSet.getBoolean("is_active"));
                fighter.setCountFights(resultSet.getInt("count_fights"));

                fighters.add(fighter);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return fighters;
    }


    public static Fighter getFighterById(int id){

        Fighter fighter = new Fighter();

        try {
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM fighters WHERE id=?");
              statement.setInt(1, id);

              ResultSet resultSet  = statement.executeQuery();

              if(resultSet.next()){
                  fighter.setId(resultSet.getInt("id"));
                  fighter.setAge(resultSet.getInt("age"));
                  fighter.setStyle(resultSet.getString("style"));
                  fighter.setFullName(resultSet.getString("full_name"));
                  fighter.setIsActive(resultSet.getBoolean("is_active"));
                  fighter.setCountFights(resultSet.getInt("count_fights"));
              }

              statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return fighter;

    }

}

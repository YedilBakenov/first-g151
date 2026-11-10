package com.main.first_project_g151.db;

import com.main.first_project_g151.model.Fighter;

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

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fighters ORDER BY id asc");

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

    public static void updateFighter(Fighter fighter){

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE fighters SET full_name=?, age=?, style=?, " +
                    "count_fights=?, is_active=? WHERE id=?");

            statement.setString(1, fighter.getFullName());
            statement.setInt(2, fighter.getAge());
            statement.setString(3, fighter.getStyle());
            statement.setInt(4, fighter.getCountFights());
            statement.setBoolean(5, fighter.getIsActive());
            statement.setInt(6, fighter.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addFighter(Fighter fighter){

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO fighters (full_name, age, style, count_fights, is_active) " +
                    "VALUES (?, ?, ?, ?, ?)");

            statement.setString(1, fighter.getFullName());
            statement.setInt(2, fighter.getAge());
            statement.setString(3, fighter.getStyle());
            statement.setInt(4, fighter.getCountFights());
            statement.setBoolean(5, fighter.getIsActive());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteFighter(int id){

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM fighters WHERE id=?");

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

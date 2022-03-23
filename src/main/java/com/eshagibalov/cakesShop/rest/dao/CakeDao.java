package com.eshagibalov.cakesShop.rest.dao;

import com.eshagibalov.cakesShop.goods.AvailabilityOfCake;
import com.eshagibalov.cakesShop.rest.dto.cake.Cake;
import com.eshagibalov.cakesShop.rest.dto.cake.CakeMoreInfo;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CakeDao {
    private static Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cake getCakeById(Long id) {
        Cake cake = new Cake();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM cake WHERE id=" + id;
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                setParameters(cake, resultSet);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return cake;
    }

    public List<Cake> getAllCakes() {
        List<Cake> cakes = new ArrayList<Cake>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM cake";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Cake cake = new Cake();
                setParameters(cake, resultSet);
                cakes.add(cake);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return cakes;
    }

    public void updateCake(int id, CakeMoreInfo cakeMoreInfo) {
        try {
            Statement statement = connection.createStatement();

            String shelfLife = "shelf-life";
            String SQL = "UPDATE cake " +
                    "SET name='" + cakeMoreInfo.getName() + "'," + "calories='" + cakeMoreInfo.getCalories() + "'," + "image='" + cakeMoreInfo.getImage() + "'," +
                    "price='" + cakeMoreInfo.getPrice() + "'," + "weight='" + cakeMoreInfo.getWeight() + "'," + "compositions='" + cakeMoreInfo.getComposition() + "'," +
                    "shelf_life='" + cakeMoreInfo.getShelfLife() + "'," + "availability_of_cake='" + cakeMoreInfo.getAvailabilityOfCake() + "'WHERE id=" + id;
            statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCakeById(int id) {
        try {
            Statement statement=connection.createStatement();
            String SQL= "DELETE FROM cake WHERE id="+id;
            statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCake(CakeMoreInfo cake) {
        try {
            Statement statement=connection.createStatement();

            String SQL="INSERT INTO cake(name, calories, image, price, weight, compositions, shelf_life) VALUES('" + cake.getName() +
                    "','" + cake.getCalories() +"','"
                    +cake.getImage()+"','"+cake.getPrice()+"','"+cake.getWeight()+"','"
                    +cake.getComposition()+"','"+cake.getShelfLife()+"', ,'"+cake.getAvailabilityOfCake()+"')";
            statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void setParameters(Cake cake, ResultSet resultSet) throws SQLException {
        cake.setId(resultSet.getLong("id"));
        cake.setName(resultSet.getString("name"));
        cake.setCalories(resultSet.getBigDecimal("calories"));
        cake.setImage(resultSet.getString("image"));
        cake.setPrice(resultSet.getBigDecimal("price"));
        cake.setWeight(resultSet.getBigDecimal("weight"));
        cake.setAvailabilityOfCake(AvailabilityOfCake.values()[resultSet.getInt("availability-of-cake")]);
    }
}
package org.example;

import java.sql.*;


public class App
{

    public void restaurants(String location, Statement S)throws SQLException{
        String query = String.format("SELECT restaurant_name from restaurant where restaurant_location = '%S'", location);
                System.out.println(query);
        ResultSet rs = S.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
    }
    public void loadMenu(String food, Statement S)throws SQLException{
        String query = String.format("select food_item from menu where restaurant_id = (select restaurant_id from restaurant where restaurant_name = '%S' )", food);
        ResultSet rs = S.executeQuery((query));
        while(rs.next()){
            System.out.println(rs.getString(1));
        }

    }
    public static void main( String[] args ) throws ClassNotFoundException, SQLException{
        App a = new App();
        String url = "jdbc:mysql://localhost:3306/food_delivery";
        String uname = "root";
        String pass = "pass@word1";
//        String Query = "select cust_name from customer where cust_id = 2";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = null;
        try {
            con = DriverManager.getConnection(url,uname,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement S = con.createStatement();
//       ResultSet rs = S.executeQuery(Query);
//       rs.next();
//       String name = rs.getString("cust_name");
//       System.out.println(name);
            a.loadMenu("rest 1", S);
            a.restaurants("kaloor",S);
       S.close();
       con.close();

    }
}

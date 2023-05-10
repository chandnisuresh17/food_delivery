package org.example;

import java.sql.*;
import java.util.Scanner;

import static java.lang.String.format;


public class App
{

    public void restaurants(String location, Statement S)throws SQLException{
        String query = format("SELECT restaurant_name from restaurant where restaurant_location = '%S'", location);
                System.out.println(query);
        ResultSet rs = S.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
    }
    public void loadMenu(String food, Statement S)throws SQLException {
        String query = format("select food_item from menu where restaurant_id = (select restaurant_id from restaurant where restaurant_name = '%S' )", food);
        ResultSet rs = S.executeQuery((query));
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
        public void placeOrder(int user1 ,Statement S)throws SQLException {

            String query = String.format("select * from menu join restaurant on menu.restaurant_id = restaurant.restaurant_id");
            ResultSet rs = S.executeQuery(query);
            int index = 1;
            while(rs.next()){
                System.out.println(index + ") " + rs.getString(1) + rs.getString(2)+ rs.getString(5));
                index++;
            }
            Scanner sc = new Scanner(System.in);
            int choice = 0;
            choice = sc.nextInt();
            while(true) {
                if (choice > 0 && choice < index) {
                    break;
                }
                else{
                    System.out.println("enter valid choice");
                }
            }

//            String query1 = format("Insert into orders (cust_id,restaurant_id,menu_id) values (%s,%s,%s)",user1);
            ResultSet rs1 = S.executeQuery(query);
            int insertInd = 1;
            System.out.println(choice);
            while(rs1.next()) {
                if (insertInd == choice) {
                    String query1 = String.format("insert into orders (restaurant_id,menu_id, cust_id) values (%s,%s,%s)", rs1.getInt(4), rs1.getInt(1), user1);
                    System.out.println(query1);
                    int result = S.executeUpdate(query1);
                    break;
                }
                insertInd++;

            }
            }
            public void orderHistory(int user_id, Statement S) throws SQLException{
                String query2 = String.format("select * from menu join restaurant on menu.restaurant_id = restaurant.restaurant_id\n" +
                        " where menu.restaurant_id IN(SELECT restaurant_id FROM orders WHERE cust_id=%s)\n" +
                        " AND menu.menu_id IN(select menu_id FROM orders where cust_id=%s)\n", user_id, user_id);
                ResultSet resultSet = S.executeQuery(query2);
                while (resultSet.next()){
                    System.out.println(resultSet.getString(2)+ " " + resultSet.getString(5));
                }
            }


//            Scanner sc = new Scanner(System.in);
////
////            if (rs == rs ) {
////                rs == 1;
////                System.out.println("rest_1");
////            }
//
//            ResultSet rs = S.executeQuery(query);
//            int index = 1;
//            while (rs.next()) {
//
//                System.out.println(index + ")" + rs.getString(1));
//                index++;
//
//
//            }
//            int choice = sc.nextInt();
//            int selected_restaurant = 0;
//            ResultSet r = S.executeQuery(query);
//            int index1 = 1;
//            while (r.next()) {
//                if (index1 == choice) {
//                    selected_restaurant = r.getInt(2);
//                    break;
//                }
//                index1++;
//
//
//            }
////            System.out.println(selected_restaurant);
//            String query1 = format("select * from menu join restaurant_id = %s", selected_restaurant);
////            Scanner sc1 = new Scanner(System.in);
//            ResultSet rs1 = S.executeQuery(query1);
//            int index2 = 1;
//            while (rs1.next()) {
//
//                System.out.println(index2 + ")" + rs1.getString(1));
//                index2++;
//
//
//            }
////            int choice1 = sc1.nextInt();
////            int selected_food = 0;
////            ResultSet rs2 = S.executeQuery(query);
////            int index3 = 1;
////            while(rs2.next()){
////                if(index3==choice1){
////                    selected_food = r.getInt(2);
////                    break;
////                }
////                index3++;
////
////
////            }
////
////
////            System.out.println(selected_food);
////
////
////        }




    public static void main( String[] args ) throws ClassNotFoundException, SQLException{
        App a = new App();
        String url = "jdbc:mysql://localhost:3306/food_delivery";
        String uname = "root";
        String pass = "pass@word1";
//        String Query = "select cust_name from customer where cust_id = 2";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = null;
        con = DriverManager.getConnection(url,uname,pass);

        Statement S = con.createStatement();
//       ResultSet rs = S.executeQuery(Query);
//       rs.next();
//       String name = rs.getString("cust_name");
//       System.out.println(name);
            a.loadMenu("rest1", S);
            a.restaurants("kaloor",S);
            a.placeOrder(1,S);
            a.orderHistory(1,S);
       S.close();
       con.close();

    }
}

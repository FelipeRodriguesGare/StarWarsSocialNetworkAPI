package com.starwars.StarWarsAPI.data_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private Connection conn;

    public Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://plantair.cic1xdixwlvq.sa-east-1.rds.amazonaws.com:3306/plantair";
            String user = "admin";
            String password = "brubru2022";
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro na Conexão" + ex.getMessage());;
        }
        return null;
    }

    public void desconectar(){
        try {
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

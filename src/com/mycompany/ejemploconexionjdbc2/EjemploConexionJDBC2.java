/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemploconexionjdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploConexionJDBC2 {

    public static void main(String[] args) {

        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/prueba";
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            
            // Insertar datos en la tabla USUARIO
            statement.executeUpdate("INSERT INTO USUARIO(USERNAME, USERPASSWORD) VALUES('samuel@gmail.com', '123456789')");
            statement.executeUpdate("INSERT INTO USUARIO(USERNAME, USERPASSWORD) VALUES('lina@gmail.com', '123456789')");
             
            // Consultar datos de la tabla USUARIO
            rs = statement.executeQuery("SELECT * FROM USUARIO");
            
            // Imprimir resultados
            while (rs.next()) {
                System.out.println(rs.getInt("userid") + ": " + rs.getString("username"));
            }

            // Actualizar datos en la tabla USUARIO
            statement.executeUpdate("UPDATE USUARIO SET USERPASSWORD = 'nueva_contraseña' WHERE USERNAME = 'samuel@gmail.com'");
            
            // Eliminar datos en la tabla USUARIO
            statement.executeUpdate("DELETE FROM USUARIO WHERE USERNAME = 'samuel@gmail.com'");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

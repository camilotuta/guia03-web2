/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tutaa
 */
public class Conexion {

    Connection con;

    public Conexion() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/gestion", "tuta", "root");
        } catch (Exception e) {
            System.err.println("Conexion error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/gestion", "tuta", "root");
            }
        } catch (Exception e) {
            System.err.println("getConnection error: " + e.getMessage());
            e.printStackTrace();
        }
        return con;

    }

}

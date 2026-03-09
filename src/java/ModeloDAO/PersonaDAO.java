package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Persona;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class PersonaDAO implements CRUD {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Persona p = new Persona();

    @Override
    public List listar() {
        ArrayList<Persona> lista = new ArrayList<>();
        String sql = "select * from persona";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rsLocal = stmt.executeQuery()) {
            while (rsLocal.next()) {
                Persona per = new Persona();
                per.setId(rsLocal.getInt("id"));
                try {
                    per.setDni(rsLocal.getInt("DNI"));
                } catch (SQLException ignore) {
                }
                try {
                    per.setNombre(rsLocal.getString("Nombres"));
                } catch (SQLException ignore) {
                }
                lista.add(per);
            }
        } catch (Exception e) {
            System.out.println("Error listar PersonaDAO: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Persona list(int id) {
        Persona per = new Persona();
        String sql = "select * from persona where id = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rsLocal = stmt.executeQuery()) {
                if (rsLocal.next()) {
                    per.setId(rsLocal.getInt("id"));
                    try {
                        per.setDni(rsLocal.getInt("DNI"));
                    } catch (SQLException ignore) {
                    }
                    try {
                        per.setNombre(rsLocal.getString("Nombres"));
                    } catch (SQLException ignore) {
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error list PersonaDAO: " + e.getMessage());
        }
        return per;
    }

    @Override
    public boolean add(Persona per) {
        String sql = "insert into persona(DNI, Nombres) values(?,?)";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, per.getDni());
            stmt.setString(2, per.getNombre());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Error add PersonaDAO: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(Persona per) {
        String sql = "update persona set DNI = ?, Nombres = ? where id = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, per.getDni());
            stmt.setString(2, per.getNombre());
            stmt.setInt(3, per.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Error edit PersonaDAO: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "delete from persona where id = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Error eliminar PersonaDAO: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}

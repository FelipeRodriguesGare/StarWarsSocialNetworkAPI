package com.starwars.StarWarsAPI.data_base;

import com.starwars.StarWarsAPI.model.Item;
import com.starwars.StarWarsAPI.model.Rebelde;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("mysql")
public class RebeldesDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ResultSet getAll() {
        ConnectionDB conexao = new ConnectionDB();
        conn = conexao.conectar();
        try {
            String selecionar = "SELECT * FROM plantair.Rebeldes";
            pstmt = conn.prepareStatement(selecionar);
            rs = pstmt.executeQuery();
            conexao.desconectar();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(RebeldesDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexao.desconectar();
        }
        return null;

    }

    public void writeToDB(Rebelde rebelde) {
        ConnectionDB conexao = new ConnectionDB();
        conn = conexao.conectar();
        try {
            insertRebelde(rebelde);
            insertItems(rebelde);

            conexao.desconectar();
        } catch (SQLException e) {
            conexao.desconectar();
        }
    }

    private void insertItems(Rebelde rebelde) throws SQLException {
         for (Map.Entry<String, Item> entry : rebelde.getInventario().getItems().entrySet()){
            String inserir = "INSERT INTO plantair.Inventario (idRebelde, item, quantidade) " +
                    "VALUES (?,?,?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setString(1, String.valueOf(rebelde.getId()));
            pstmt.setString(2, entry.getKey());
            pstmt.setInt(3, entry.getValue().getQuantidade());
            pstmt.execute();
         }
    }

    private void insertRebelde(Rebelde rebelde) throws SQLException {
        String inserir = "INSERT INTO plantair.Rebeldes (" +
                "idRebelde, " +
                "username, " +
                "senha, " +
                "nameRebelde, " +
                "avatar, " +
                "idade, " +
                "genero, " +
                "traidor," +
                "latitude, " +
                "longitude," +
                "nomeDaGalaxia) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(inserir);
        pstmt.setString(1, String.valueOf(rebelde.getId()));
        pstmt.setString(2, rebelde.getUsername());
        pstmt.setString(3, rebelde.getSenha());
        pstmt.setString(4, rebelde.getNome());
        pstmt.setString(5, rebelde.getAvatar());
        pstmt.setInt(6, rebelde.getIdade());
        pstmt.setString(7, rebelde.getGenero());
        pstmt.setBoolean(8, rebelde.getTraidor());
        pstmt.setLong(9, rebelde.getLocalizacao().getLatitude());
        pstmt.setLong(10, rebelde.getLocalizacao().getLongitude());
        pstmt.setString(11, rebelde.getLocalizacao().getNomeDaGalaxia());
        pstmt.execute();
    }
}

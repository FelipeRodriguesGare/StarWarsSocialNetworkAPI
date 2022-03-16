package com.starwars.StarWarsAPI.data_base;

import com.starwars.StarWarsAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("mysql")
public class RebeldesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class RebeldeRowMapper implements RowMapper<Rebelde> {

        @Override
        public Rebelde mapRow(ResultSet rs, int rowNum) throws SQLException {
            Localizacao localizacao = new Localizacao(
                    rs.getLong("latitude"),
                    rs.getLong("longitude"),
                    rs.getString("nomeDaGalaxia")
            );

            Inventario inventario = new Inventario();

            Rebelde rebelde = new Rebelde(
                    UUID.fromString(rs.getString("idRebelde")),
                    rs.getString("username"),
                    rs.getString("senha"),
                    rs.getString("nameRebelde"),
                    rs.getString("avatar"),
                    rs.getInt("idade"),
                    rs.getString("genero"),
                    rs.getBoolean("traidor"),
                    localizacao,
                    inventario,
                    rs.getInt("contReportTraidor")
            );
            return rebelde;
        }
    }

    public Rebelde login(LoginRequest loginRequest) throws Exception {
        String sql = "SELECT * FROM plantair.Rebeldes WHERE username= ? AND senha = ?";
        Rebelde rebelde = jdbcTemplate.queryForObject(sql, new RebeldeRowMapper(), new Object[] {loginRequest.getUsername(),loginRequest.getSenha()});
        return updateInventario(Objects.requireNonNull(rebelde));
    }

    private Rebelde updateInventario(Rebelde rebelde){
        String sql = String.format("SELECT * FROM plantair.Inventario WHERE idRebelde = \"%s\"", String.valueOf(rebelde.getId()));
        jdbcTemplate.query(sql, new RowMapper<Rebelde>(){
            @Override
            public Rebelde mapRow(ResultSet rs, int rowNum) throws SQLException {
                rebelde.getInventario().getItems().get(rs.getString("item")).setQuantidade(rs.getInt("quantidade"));
                return rebelde;
            }
        });
        return rebelde;
    }

    public List<Rebelde> getAll() {
        final String sql ="SELECT * FROM plantair.Rebeldes";
        List<Rebelde> rebeldes = jdbcTemplate.query(sql, new RebeldeRowMapper());
        List<Rebelde> rebeldesAtualizados = new ArrayList<>();
        for(Rebelde rebelde : rebeldes){
            rebeldesAtualizados.add(updateInventario(rebelde));
        }
        return rebeldesAtualizados;
    }

    public Rebelde getRebeldeByID(UUID id){
        final String sql ="SELECT * FROM plantair.Rebeldes WHERE idRebelde = ?";
        return updateInventario(Objects.requireNonNull(jdbcTemplate.queryForObject(sql, new RebeldeRowMapper(), String.valueOf(id))));
    }

    public void removeRebelde(UUID id){
        jdbcTemplate.update("DELETE FROM plantair.Rebeldes WHERE idRebelde = ?", String.valueOf(id));
    }

    public void updateRebelde(Rebelde rebelde){
        final String sql =  "UPDATE plantair.Rebeldes SET " +
                "username = ?, " +
                "senha = ?, " +
                "nameRebelde = ?, " +
                "avatar = ?, " +
                "idade = ?, " +
                "genero = ?, " +
                "traidor = ?, " +
                "latitude = ?, " +
                "longitude = ?, " +
                "nomeDaGalaxia = ?," +
                "contReportTraidor = ? " +
                "WHERE idRebelde = ?";

        jdbcTemplate.update(sql, new Object[] {
                rebelde.getUsername(),
                rebelde.getSenha(),
                rebelde.getNome(),
                rebelde.getAvatar(),
                rebelde.getIdade(),
                rebelde.getGenero(),
                rebelde.getTraidor(),
                rebelde.getLocalizacao().getLatitude(),
                rebelde.getLocalizacao().getLongitude(),
                rebelde.getLocalizacao().getNomeDaGalaxia(),
                rebelde.getContReportTraidor(),
                String.valueOf(rebelde.getId())
        });
    }

    public void updateInventarioDB(Rebelde rebelde){
        for(Map.Entry<String, Item> entry : rebelde.getInventario().getItems().entrySet()) {
            String sql = "UPDATE plantair.Inventario SET quantidade = ? WHERE idRebelde = ? AND item = ?";
            jdbcTemplate.update(sql, new Object[] {entry.getValue().getQuantidade(), String.valueOf(rebelde.getId()),entry.getKey()});
        }
    }

    public void writeToDB(Rebelde rebelde) {
        String sql = "INSERT INTO plantair.Rebeldes (" +
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
                "nomeDaGalaxia," +
                "contReportTraidor) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                String.valueOf(rebelde.getId()),
                rebelde.getUsername(),
                rebelde.getSenha(),
                rebelde.getNome(),
                rebelde.getAvatar(),
                rebelde.getIdade(),
                rebelde.getGenero(),
                rebelde.getTraidor(),
                rebelde.getLocalizacao().getLatitude(),
                rebelde.getLocalizacao().getLongitude(),
                rebelde.getLocalizacao().getNomeDaGalaxia(),
                rebelde.getContReportTraidor()
        });
        for (Map.Entry<String, Item> entry : rebelde.getInventario().getItems().entrySet()) {
            String sqlIventario = "INSERT INTO plantair.Inventario (idRebelde, item, quantidade) " +
                    "VALUES (?,?,?)";

            jdbcTemplate.update(sqlIventario, new Object[]{
                String.valueOf(rebelde.getId()),
                entry.getKey(),
                entry.getValue().getQuantidade()
            });
        }
    }
}

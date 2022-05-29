package DAO;

import Entity.Loja;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LojasDAOimpl implements LojasDAO {
    private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3307/lojasdb?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "123456";
    private Connection con;

    public LojasDAOimpl() {
        try {
            Class.forName(JDBC_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Loja e) {
        String sql = "INSERT INTO lojas (id, nome, data) ";
        sql += " VALUES (0, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, e.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(e.getData()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Loja> consultar(String nome) {
        List<Loja> lista = new ArrayList<>();
        String sql = "SELECT * FROM lojas WHERE nome LIKE '%" + nome + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Loja loja = new Loja();
                loja.setNome(rs.getString("nome"));
                loja.setData(rs.getDate("data").toLocalDate());
                lista.add(loja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
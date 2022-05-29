package DAO;

import Entity.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresDAOimpl implements FornecedoresDAO {
    private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3307/fornecedorbd?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "123456";
    private Connection con;

    public FornecedoresDAOimpl() {
        try {
            Class.forName(JDBC_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Fornecedor e) {
        String sql = "INSERT INTO fornecedor (id, nome, data) ";
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
    public List<Fornecedor> consultar(String nome) {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor WHERE nome LIKE '%" + nome + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setData(rs.getDate("data").toLocalDate());
                lista.add(fornecedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
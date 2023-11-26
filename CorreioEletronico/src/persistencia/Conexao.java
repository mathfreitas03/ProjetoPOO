package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String url = "jdbc:postgresql://localhost:5432/Sistema";
    private static final String usuario = "postgres";
    private static final String senha = "adm";
    private static Connection conexao = null;

    private Conexao(){}

    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        if(conexao==null){
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        return conexao;
    }

    public static void fecharConexao() throws SQLException{
        try {
            if(conexao!=null){
            conexao.close();
        }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
}

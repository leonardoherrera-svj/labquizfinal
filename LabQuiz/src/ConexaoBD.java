/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Leonardo
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
       private static String host = "labquiz-labquiz.c.aivencloud.com";
    private static String porta = "23062";
    private static String db = "defaultdb";
    private static String usuario = "avnadmin";
    
    private static final String SENHA = "AVNS_djnpykzn4z6ZHPLRX2x";
    public static Connection obterConexao() throws Exception {
        String url = String.format(
            "jdbc:mysql://%s:%s/%s?sslMode=REQUIRED",
            host,
            porta,
            db
        );

        return DriverManager.getConnection(url, usuario, SENHA);
    }
    
}

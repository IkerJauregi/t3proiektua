package dambi.pojoak.DatuBasea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Konekzioa {
    private String host, port, database, user, password; 

    public Konekzioa(String host, String port, String database, String user, String password){
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }
    /**
     * Funtzioa. Datu basearekin konektatzeko ibiliko den funtzioa da, datuak
     * ez dira funtzioaren barnean zehazturik egongo parametro bezala jasoko bai dira.
     */
    public Connection connectDatabase() {
        String url = "";
        Connection connection = null;
        try {
            // PostgresSQLko driverra erregistratuko da.
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Errorea PostgreSQL driverra erregistratzean: " + ex);
            }
            url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            // Datu basearekin konektatuko da.
            connection = DriverManager.getConnection(url,user, password);           
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) { 
            System.out.println("Errorea PostgreSQLko datu basearekin konektatzean (" + url + "): " + sqle);
        }
        return connection;
    }
    public ArrayList <String> irakurriTaula(String taula, Konekzioa konekzioa){
        String sql = "SELECT * FROM public.\"" + taula +"\"";
        ArrayList <String> modelo = new ArrayList <String> ();
        Statement st;
        try{
            st = konekzioa.connectDatabase().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.add(rs.getString(1)+";"+rs.getString(2)+";"+rs.getString(3)+";"+rs.getString(4));
            }
        } catch(Exception ex){
            System.out.println("Exception : "+ex);
        }
        return modelo;
    }
    public int insertTaula(String taula, Konekzioa konekzioa){
        String sql = "INSERT INTO public.\"" + taula +"\" VALUES('c','a','a','a');";
        try{
            java.sql.Statement st = konekzioa.connectDatabase().createStatement();
            st.execute(sql);
            st.close();
            return 1;
        } catch(Exception ex){
            System.out.println("Exception : "+ex);
            return 0;
        }
    }

}

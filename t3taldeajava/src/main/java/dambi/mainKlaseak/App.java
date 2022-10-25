package dambi.mainKlaseak;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dambi.pojoak.DatuBasea.Konekzioa;

/**
 * Gure aplikazioaren Main klasea
 *
 */
public class App 
{
    private static String host, port, database, user, password;
    public static void main( String[] args )
    {
        datuBasearen_datuakZehaztu();
        menua();


        
    }
    public static void datuBasearen_datuakZehaztu(){
        host = "localhost";
        port = "5432";
        database = "Cookpad";
        user = "postgres";
        password = "admin";
    }

    public static void menua(){
        
        System.out.println("---------------------------------------------------------");
        System.out.println("-------------  ONGI ETORRI GURE APLIKAZIORA  ------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("1) Datu Baseko datuak exportatu fitxategira."); //tabla bat eskatu, ondoren beste menua erakutsi eta bertan fitxategiaren formatua aukeratzean, fitxategiko datuak datu baseara igo(fitxategia adieraztean beste menu bat erakutsi create, new, delete zer nahi duen jakiteko).
        System.out.println("2) Datu Baseko datuak inportatu fitxategira."); //tabla bat eskatu eta duen datuak bistaratu
        System.out.println("3) Datuak ikusi."); //tabla bat eskatu eta duen datuak bistaratu
        System.out.println("0) Irten");
        System.out.println("---------------------------------------------------------");
    }
    public static void menuaFitxategia(){
        
        System.out.println("---------------------------------------------------------");
        System.out.println("-------------  Zer formatuarekin nahi duzu?  ------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("1) XML formatua.");
        System.out.println("2) JSON formatua.");
        System.out.println("3) CSV formatua.");
        System.out.println("0) Irten");
        System.out.println("---------------------------------------------------------");
    }

    public static void test(){
        ArrayList <String> modelo = new ArrayList <String> ();
        Konekzioa konekzioa = new Konekzioa(host, port, database, user, password);
        modelo = konekzioa.irakurriTaula("Erabiltzailea", konekzioa);
        for(int j = 0; j < modelo.size(); j++){
            System.out.println(modelo.get(j));
        }
        int emaitza = konekzioa.insertTaula("Erabiltzailea", konekzioa);
        if(emaitza == 0){
            System.out.println("Ezin izan da datuak txertatu taulara.");
        }
        else{
            System.out.println("Eginda");
            modelo = konekzioa.irakurriTaula("Erabiltzailea", konekzioa);
            for(int j = 0; j < modelo.size(); j++){
                System.out.println(modelo.get(j));
            }
        }
    }

    
}

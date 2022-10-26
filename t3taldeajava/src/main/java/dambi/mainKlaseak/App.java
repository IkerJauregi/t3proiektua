package dambi.mainKlaseak;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import dambi.pojoak.DatuBasea.Konekzioa;

/**
 * Gure aplikazioaren Main klasea
 *
 */
public class App {
    private static String host, port, database, user, password;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int aukera;
        datuBasearen_datuakZehaztu();
        do {
            menua();
            System.out.println("Zer egin nahi duzu? Sartu aukera:");
            aukera = in.nextInt();
            menukoAukeraEgin(aukera);
        } while (aukera != 0);
        System.out.println("Eskerrik asko gure aplikazioa erabiltzeagatik. Agur!\nSartu tekla bat irtetzeko.");
        in.close();
    }

    public static void datuBasearen_datuakZehaztu() {
        host = "localhost";
        port = "5432";
        database = "Odoo_Ethazi";
        user = "postgres";
        password = "admin";
    }

    public static void menua() {

        System.out.println("---------------------------------------------------------");
        System.out.println("-------------  ONGI ETORRI GURE APLIKAZIORA  ------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("1) Datuak ikusi."); 
        // tabla bat eskatu eta duen datuak bistaratu
        System.out.println("2) Datu Baseko taula batean erregistro berria sortu."); 
        // tabla bat eskatu, eta INSERT INTO egiteko beharrezkoak diren datuak eskatu.
        System.out.println("3) Datu Baseko taula batean erregistro bat aldatu."); 
        // tabla bat eskatu, erregistroaren ida eskatu, aurkitu erregistroa eta SET egiteko beharrezkoak diren datuak eskatu.
        System.out.println("4) Datu Baseko taula batean erregistro bat ezabatu."); 
        // tabla bat eskatu, erregistroaren ida eskatu, aurkitu erregistroa eta DELETE egiteko beharrezkoak diren datuak eskatu.
        System.out.println("5) Datu Baseko datuak exportatu fitxategira."); 
        // tabla bat eskatu, ondoren beste menua erakutsi eta bertan fitxategiaren formatua aukeratzean, fitxategiko datuak
        // datu baseara igo(fitxategia adieraztean beste menu bat erakutsi create, new, delete zer egin nahi duen jakiteko).
        System.out.println("6) Datu Baseko datuak inportatu fitxategira."); 
        // tabla bat eskatu eta duen datuak bistaratu
        System.out.println("0) Irten");
        // menutik irtetzeko
        System.out.println("---------------------------------------------------------");
    }

    public static int menuaFitxategia() {
        int aukera = 0;
        System.out.println("---------------------------------------------------------");
        System.out.println("-------------  Zer formatuarekin nahi duzu?  ------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("1) XML formatua."); 
        // Fitxategia XML formatuan inportatu edo exportatzeko
        System.out.println("2) JSON formatua."); 
        // Fitxategia JSON formatuan inportatu edo exportatzeko
        System.out.println("3) CSV formatua."); 
        // Fitxategia CSV formatuan inportatu edo exportatzeko
        System.out.println("0) Irten");
        // menutik irtetzeko
        System.out.println("---------------------------------------------------------");
        System.out.println("Zer egin nahi duzu? Sartu aukera: ");
        aukera = in.nextInt();
        return aukera;
    }

    public static void menukoAukeraEgin(int aukera) {
        String taula;
        int aukeraFitx = 0;
        Konekzioa konekzioa = new Konekzioa(host, port, database, user, password);
        switch (aukera) {
            case 1:// datuak ikusi
                System.out.println("Sar ezazu zer taula nahi duzun bistaratu: ");
                taula = in.next();
                if(taula.toLowerCase().equals("produktuak")){
                    Crud_Produktuak(0, taula, konekzioa);
                }
                else{
                    System.out.println("Oraindik inplementatu gabe");
                }
                break;
            case 2:// Datu Baseko taula batean erregistro berria sortu.
                System.out.println("Sar ezazu zer taulatara nahi duzun erregistro berria txertatu: ");
                taula = in.next();
                if(taula.toLowerCase().equals("produktuak")){
                    Crud_Produktuak(1, taula, konekzioa);
                }
                else{
                    System.out.println("Oraindik inplementatu gabe");
                }
                break;
            case 3:// Datu Baseko taula batean erregistro bat aldatu.
                System.out.println("Sar ezazu zer taulan nahi duzun erregistro bat aldatu: ");
                taula = in.next();
                if(taula.toLowerCase().equals("produktuak")){
                    Crud_Produktuak(2, taula, konekzioa);
                }
                else{
                    System.out.println("Oraindik inplementatu gabe");
                }
                break;
            case 4:// Datu Baseko taula batean erregistro bat ezabatu.
                System.out.println("Sar ezazu zer taulatik nahi duzun erregistro bat ezabatu: ");
                taula = in.next();
                if(taula.toLowerCase().equals("produktuak")){
                    Crud_Produktuak(3, taula, konekzioa);
                }
                else{
                    System.out.println("Oraindik inplementatu gabe");
                }
                break;
            case 5:// Datu Baseko datuak exportatu fitxategira.
                System.out.println("Sar ezazu zer taula nahi duzun aldatu: ");
                taula = in.next();
                taula = "public." + taula;
                aukeraFitx = menuaFitxategia();
                switch (aukeraFitx) {
                    case 1:
                        // XML
                        break;
                    case 2:
                        // JSON
                        break;
                    case 3:
                        // CSV
                        break;
                }
                break;
            case 6:// Datu Baseko datuak inportatu fitxategira.
                System.out.println("Sar ezazu zer taula nahi duzun aldatu: ");
                taula = in.next();
                taula = "public." + taula;
                aukeraFitx = menuaFitxategia();
                switch (aukeraFitx) {
                    case 1:
                        // XML
                        break;
                    case 2:
                        // JSON
                        break;
                    case 3:
                        // CSV
                        break;
                }
                break;
        }
    }

    public static void zerrendaBistaratu(ArrayList<String> zerrenda) {
        for (int j = 0; j < zerrenda.size(); j++) {
            System.out.println(zerrenda.get(j));
        }
    }

//CRUD PRODUKTUAK------------------------------------------------------------------------------------------------------------
    public static void Crud_Produktuak(int ekintza, String taula, Konekzioa konekzioa) {
        if (ekintza == 0) { // SELECT
            ArrayList<String> produktuak = selectProduktuak(taula, konekzioa);
            zerrendaBistaratu(produktuak);
        } else {
            if (ekintza == 1) { // INSERT
                int emaitza= 0;
                System.out.println("Sartu Id Berria:");
                int id = in.nextInt();
                System.out.println("Sartu Produktuaren izen Berria:");
                String produktua = in.next();
                System.out.println("Sartu prezioa Berria:");
                Double prezioa = in.nextDouble();
                System.out.println("Sartu kantitatea Berria:");
                int kantitatea = in.nextInt();
                emaitza = insertProduktuak(taula, konekzioa, id, produktua, prezioa, kantitatea);
                if(emaitza == 0){
                    System.out.println("Ez da txertatu erregistroa taulan.");
                }
                else{
                    System.out.println("Erregistro berria txertaturik.");
                }
            } else {
                if (ekintza == 2) { // UPDATE
                    int emaitza= 0;
                    System.out.println("Sartu aldatu nahi duzun Produktuaren id:");
                    int id = in.nextInt();
                    System.out.println("Sartu Produktuaren izen Berria:");
                    String produktua = in.next();
                    System.out.println("Sartu prezioa Berria:");
                    Double prezioa = in.nextDouble();
                    System.out.println("Sartu kantitatea Berria:");
                    int kantitatea = in.nextInt();
                    emaitza = updateProduktuak(taula, konekzioa, id, produktua, prezioa, kantitatea);
                    if(emaitza == 0){
                        System.out.println("Ez da aldatu erregistroa taulan.");
                    }
                    else{
                        System.out.println("Erregistroa aldaturik.");
                    }
                } else { // DELETE
                    int emaitza= 0;
                    System.out.println("Sartu ezabatu nahi duzun Produktuaren id:");
                    int id = in.nextInt();
                    
                    emaitza = deleteProduktuak(taula, konekzioa, id);
                    if(emaitza == 0){
                        System.out.println("Ez da ezabatu erregistroa taulan.");
                    }
                    else{
                        System.out.println("Erregistroa ezabaturik.");
                    }
                }
            }
        }
    }
    
    public static ArrayList<String> selectProduktuak(String taula, Konekzioa konekzioa) {
        String sql = "SELECT * FROM public.\"" + taula + "\"";
        ArrayList<String> modelo = new ArrayList<String>();
        Statement st;
        try {
            st = konekzioa.connectDatabase().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.add(rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3) + ";" + rs.getString(4));
            }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
        return modelo;
    }

    public static int insertProduktuak(String taula, Konekzioa konekzioa, int id, String produktua, Double prezioa, int kantitatea) {
        String sql = "INSERT INTO public.\"" + taula + "\" VALUES('" + id +"','" + produktua +"','" + prezioa +"','" + kantitatea + "');";
        try {
            java.sql.Statement st = konekzioa.connectDatabase().createStatement();
            st.execute(sql);
            st.close();
            return 1;
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            return 0;
        }
    }

    public static int updateProduktuak(String taula, Konekzioa konekzioa, int id, String produktua, Double prezioa, int kantitatea){
        String sql = "UPDATE public.\"" + taula + "\" SET Produktuak.Produktua = " + produktua + ", Produktuak.Prezioa =" + prezioa + ", Produktuak.Kantitatea =" + kantitatea + " where Produktuak.id =" + id +";";
        try {
            java.sql.Statement st = konekzioa.connectDatabase().createStatement();
            st.execute(sql);
            st.close();
            return 1;
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            return 0;
        }
    }

    public static int deleteProduktuak(String taula, Konekzioa konekzioa, int id){
        String sql = "DELETE FROM public.\"" + taula + "\" where Produktuak.Id = '" + id +"';";
        try {
            java.sql.Statement st = konekzioa.connectDatabase().createStatement();
            st.execute(sql);
            st.close();
            return 1;
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            return 0;
        }
    }
        //CRUD PRODUKTUAK------------------------------------------------------------------------------------------------------------
}
//LO QUE HAY QUE HACER:---------------------------------------------------------
    //1) Hacer que el delete y el update funcionen
    //2) Pasar a objeto de produktua, en plan poner los crud en el pojo de produktua y jugar con los objetos en vez de con variables.
    //3) Hacer que se puedan exportar e importar los productos mediante ficheros

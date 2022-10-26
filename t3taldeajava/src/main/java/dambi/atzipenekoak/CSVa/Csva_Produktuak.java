package dambi.atzipenekoak.CSVa;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dambi.pojoak.Produktuak.Produktua;
import dambi.pojoak.Produktuak.Produktuak;

public class Csva_Produktuak {
    public String strFileIn;
    public String strFileOut;

    /**
     * Konstruktorea. Parametro bakarra jasotzen badu,
     * sarrera fitxategiaren izena jaso dugula suposatuko da.
     */
    public Csva_Produktuak(String strFile) {
        strFileIn = strFile;
    }
    /**
     * Konstruktorea. Aldiz, bi parametro jasotzen badira, suposatuko da 
     * lehenengoa sarrera fitxategiaren izena izango dela eta bigarrena 
     * irteerako fitxategiarena.
     */
    public Csva_Produktuak(String strFileIn, String strFileOut) {
        this.strFileIn = strFileIn;
        this.strFileOut = strFileOut;
    }
    /**
    * Funtzioa. Objektuen fitxategia CSV formatuan irakurriko duen funtzioa. Bukaeran
    * irakurritako objektuen zerrenda itzuliko du.
    */
    public Produktuak irakurri() {
        Produktuak produktuak = null;

        try (BufferedReader inputStream = new BufferedReader(new FileReader(strFileIn))) {
            String banatzailea = ";";
            String l;
            produktuak = new Produktuak();
            while ((l = inputStream.readLine()) != null) {
                String[] eremuak = l.split(banatzailea);
                if (!eremuak[0].equals("PRODUKTUA")) {
                    Produktua produktua = new Produktua();
                    produktua.setId(produktuak.getProduktuak() == null ? 0 : produktuak.getProduktuak().size());
                    produktua.setProduktua(eremuak[0]);
                    produktua.setPrezioa(Double.parseDouble(eremuak[1]));
                    produktua.setKantitatea(Integer.parseInt(eremuak[2]));
                    produktuak.add(produktua);
                }
            }
            // System.out.println(mendiak.getMendiak());
        } catch (FileNotFoundException e) {
            System.out.println("Ez da " + strFileIn + " fitxategia aurkitu.");

        } catch (IOException e) {
            System.out.println("IOsalbuespena gertatu da.");
        }
        return produktuak;
    }
    /**
     * Funtzioa. Objektuen fitxategia CSV formatuan idatziko duen funtzioa. Bukaeran idatzi
     * dituen objektuen kopurua itzuliko du.
     */
    public int idatzi(Produktuak mendiak) {
        int mendiKopurua=0;        

        try (PrintWriter outputStream = new PrintWriter(new FileWriter(strFileOut))) {
            for (Produktua m : mendiak.getProduktuak()) {
                if (mendiKopurua==0){
                    outputStream.println("MENDIA;ALTUERA;PROBINTZIA");
                }
                mendiKopurua++;
                
                outputStream.println(m.getId()+";"+m.getProduktua()+";"+m.getPrezioa()+";"+m.getKantitatea());
            }
        } catch (IOException e) {
            System.out.println(strFileOut + " fitxategiarekin arazoren bat egon da.");
        }
        return mendiKopurua;
    }
}
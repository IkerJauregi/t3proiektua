package dambi.atzipenekoak.JSONa;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

import dambi.pojoak.Produktuak.Produktua;
import dambi.pojoak.Produktuak.Produktuak;

public class Jsona_Produktuak {
    public String strFileIn;
    public String strFileOut;

    /**
     * Konstruktorea. Parametro bakarra jasotzen badu,
     * sarrera fitxategiaren izena jaso dugula suposatuko da.
     */
    public Jsona_Produktuak(String strFile) {
        strFileIn = strFile;
    }
    /**
     * Konstruktorea. Aldiz, bi parametro jasotzen badira, suposatuko da 
     * lehenengoa sarrera fitxategiaren izena izango dela eta bigarrena 
     * irteerako fitxategiarena.
     */
    public Jsona_Produktuak(String strFileIn, String strFileOut) {
        this.strFileIn = strFileIn;
        this.strFileOut = strFileOut;
    }
    /**
    * Funtzioa. Objektuen fitxategia JSON formatuan irakurriko duen funtzioa. Bukaeran
    * irakurritako objektuen zerrenda itzuliko du.
    */
    public Produktuak irakurri() {
        Produktuak mendiak = null;
        try {
            JsonReader reader = Json.createReader(new FileReader(strFileIn));
            JsonStructure jsonst = reader.read();
            JsonArray jsonarray = jsonst.asJsonArray();
            mendiak = new Produktuak();
            for (int i = 0; i < jsonarray.size(); i++) {
                JsonObject jsonobj = jsonarray.getJsonObject(i);
                Produktua mendia = new Produktua();
                mendia.setId(jsonobj.getInt("id"));
                mendia.setProduktua(jsonobj.getString("produktua"));
                mendia.setPrezioaS(jsonobj.getString("prezioa"));
                mendia.setKantitatea(jsonobj.getInt("kantitatea"));
                mendiak.add(mendia);
            }
            
        } catch (Exception e) {
            System.out.println("Arazoak " + strFileIn + " fitxategia irakurtzerakoan.");
        }
        return mendiak;
    }
    /**
     * Funtzioa. Objektuen fitxategia JSON formatuan idatziko duen funtzioa. Bukaeran idatzi
     * dituen objektuen kopurua itzuliko du.
     */
    public int idatzi(Produktuak mendiak) {
        int mendiKopurua = 0;
        JsonArray model = null;
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Produktua m : mendiak.getProduktuak()) {
            jab.add(Json.createObjectBuilder()
                    .add("id", m.getId())
                    .add("produktua", m.getProduktua())
                    .add("prezioa", m.getPrezioa())
                    .add("kantitatea", m.getKantitatea())
                    .build());
            mendiKopurua++;
        }
        model=jab.build();

        try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(strFileOut))) {
            jsonWriter.writeArray(model);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Fitxategia sortzerakoan arazoak.");
        }
        return mendiKopurua;

    }
}

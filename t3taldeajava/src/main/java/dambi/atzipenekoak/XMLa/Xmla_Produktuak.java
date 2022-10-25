package dambi.atzipenekoak.XMLa;
import java.io.File;

import dambi.pojoak.Produktuak.Produktuak;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Xmla_Produktuak {
    public String strFileIn;
    public String strFileOut;

    /**
     * Konstruktorea. Parametro bakarra jasotzen badu,
     * sarrera fitxategiaren izena jaso dugula suposatuko da.
     */
    public Xmla_Produktuak(String strFile) {
        strFileIn = strFile;
    }
    /**
     * Konstruktorea. Aldiz, bi parametro jasotzen badira, suposatuko da 
     * lehenengoa sarrera fitxategiaren izena izango dela eta bigarrena 
     * irteerako fitxategiarena.
     */
    public Xmla_Produktuak(String strFileIn, String strFileOut) {
        this.strFileIn = strFileIn;
        this.strFileOut = strFileOut;
    }
    /**
     * Funtzioa. Objektuen fitxategia XML formatuan irakurriko duen funtzioa. Bukaeran
     * irakurritako objektuen zerrenda itzuliko du.
     */
    public Produktuak irakurri() { 
        Produktuak produktuak = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Produktuak.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            produktuak = (Produktuak) jaxbUnmarshaller.unmarshal(new File(strFileIn));
        } catch (Exception ex) {
            System.out.println("Arazoren bat " + strFileIn + " irakurtzerakoan.");
        }
        return produktuak;
    }
    /**
     * Funtzioa. Objektuen fitxategia XML formatuan idatziko duen funtzioa. Bukaeran idatzi
     * dituen objektuen kopurua itzuliko du.
     */
    public int idatzi(Produktuak produktuak) {
        int produktuKopurua = 0;
        try {

            /* init jaxb marshaler */
            JAXBContext jaxbContext = JAXBContext.newInstance(Produktuak.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            /* set this flag to true to format the output */
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            /* marshaling of java objects in xml (output to file and standard output) */
            jaxbMarshaller.marshal(produktuak, new File(strFileOut));
            // jaxbMarshaller.marshal(mendiak, System.out);
            produktuKopurua = produktuak.getProduktuak().size();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return produktuKopurua;
    }
}


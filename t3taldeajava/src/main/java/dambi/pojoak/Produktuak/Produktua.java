package dambi.pojoak.Produktuak;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "produktua", "prezioa", "kantitatea" })
@XmlRootElement(name = "Produktua")

public class Produktua {
    int id = 0;
    String produktua;
    Double prezioa;
    int kantitatea;

    public int getId(){
        return id;
    }
 
    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getProduktua() {
        return produktua;
    }

    @XmlElement(name = "Produktua")
    public void setProduktua(String produktua) {
        this.produktua = produktua;
    }

    public Double getPrezioa() {
        return prezioa;
    }

    @XmlElement(name = "prezioa")
    public void setPrezioa(Double prezioa) {
        this.prezioa = prezioa;
    }
    @XmlElement(name = "prezioa")
    public void setPrezioaS(String prezioa) {
        this.prezioa = Double.parseDouble(prezioa);
    }

    public int getKantitatea() {
        return kantitatea;
    }

    @XmlElement(name = "kantitatea")
    public void setKantitatea(int kantitatea) {
        this.kantitatea = kantitatea;
    }


}

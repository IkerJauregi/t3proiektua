package dambi.pojoak.Produktuak;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Produktuak")

public class Produktuak {
    List<Produktua> produktuak;

    public List<Produktua> getProduktuak() {
        return produktuak;
    }
    @XmlElement(name = "Produktua")
    public void setMendiak(List<Produktua> produktuak) {
        this.produktuak = produktuak;
    }

    public void add(Produktua produktua) {
        if (this.produktuak == null) {
            this.produktuak = new ArrayList<Produktua>();
        }
        this.produktuak.add(produktua);

    }
}

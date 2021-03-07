/**
 * Clasa care extinde Produs cu atribute specifice
 * unui obiect de mobilier.
 */
public class Mobila extends Produs {
    private String tip; // caracteristica obiect de mobila
    private String material; // din ce este confectionat

    public Mobila(MobilaBuilder mobilaBuilder) {
        super(mobilaBuilder.getNume(), mobilaBuilder.getPretMinim(), mobilaBuilder.getAn());
        this.tip = mobilaBuilder.getTip();
        this.material = mobilaBuilder.getMaterial();
    }

    /**
     * Am suprascris toString pentru a afisa un mesaj la
     * aplicarea comenzii listaProduse.
     * @return Un mesaj sub forma unui string.
     */
    @Override
    public String toString() {
        return "Mobila cu id: " + getId() + ", nume: " + getNume() +
                ", pret vanzare: " + getPretVanzare() + ", pret minim: "
                + getPretMinim() + ", an: " + getAn() + ", tip: " +
                tip + " si material: " + material;
    }
}

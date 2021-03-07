/**
 * Clasa care extinde Produs cu atribute specifice
 * unui tablou.
 */
public class Tablou extends Produs {
    private String numePictor; // pictorul care a creat tabloul
    private TipCuloare culoare; // tipul culorii folosite pentru pictare

    /**
     * Constructorul ce primeste ca param un tablouBuilder
     * @param tablouBuilder Un obiect de tipul TablouBuilder
     */
    public Tablou(TablouBuilder tablouBuilder) {
        super(tablouBuilder.getNume(), tablouBuilder.getPretMinim(), tablouBuilder.getAn());
        this.numePictor = tablouBuilder.getNumePictor();
        this.culoare = tablouBuilder.getCuloare();
    }

    /**
     * Am suprascris toString pentru a afisa un mesaj la
     * aplicarea comenzii listaProduse.
     * @return Un mesaj sub forma unui string.
     */
    @Override
    public String toString() {
        return "Tablou cu id: " + getId() + ", nume: " + getNume() +
                ", pret vanzare: " + getPretVanzare() + ", pret minim: "
                + getPretMinim() + ", an: " + getAn() + ", nume pictor: " +
                numePictor + " si culoare: " + culoare;
    }
}

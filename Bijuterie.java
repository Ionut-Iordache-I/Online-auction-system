/**
 * Clasa care extinde Produs cu atribute specifice
 * unei bijuterii.
 */
public class Bijuterie extends Produs {
    private String material; // din ce este confectionata bijuteria
    private boolean piatraPretioasa; // calitatea bijuteriei de a fi sau nu pretioasa

    /**
     * Constructorul ce primeste ca param un bijuterieBuilder
     * @param bijuterieBuilder Un obiect de tipul BijuterieBuilder
     */
    public Bijuterie(BijuterieBuilder bijuterieBuilder) {
        super(bijuterieBuilder.getNume(), bijuterieBuilder.getPretMinim(), bijuterieBuilder.getAn());
        this.material = bijuterieBuilder.getMaterial();
        this.piatraPretioasa = bijuterieBuilder.isPiatraPretioasa();
    }

    /**
     * Am suprascris toString pentru a afisa un mesaj la
     * aplicarea comenzii listaProduse.
     * @return Un mesaj sub forma unui string.
     */
    @Override
    public String toString() {
        return "Bijuterie cu id: " + getId() + ", nume: " + getNume() +
                ", pret vanzare: " + getPretVanzare() + ", pret minim: "
                + getPretMinim() + ", an: " + getAn() + ", material: " +
                material + " si proprietate de piatra pretioasa: " + piatraPretioasa;
    }
}

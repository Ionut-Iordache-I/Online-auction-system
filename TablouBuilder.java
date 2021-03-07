/**
 * Clasa builder pentru un produs de tipul tablou.
 */
public class TablouBuilder {
    private String nume;
    private double pretMinim;
    private int an;
    private String numePictor;
    private TipCuloare culoare;

    /**
     * Constructorul ce primeste dret param. numele tabloului.
     * @param nume
     */
    public TablouBuilder(String nume) {
        this.nume = nume;
    }

    public TablouBuilder withPretMinim(double pretMinim) {
        this.pretMinim = pretMinim;
        return this;
    }

    public TablouBuilder withAn(int an) {
        this.an = an;
        return this;
    }

    public TablouBuilder withNumePictor(String numePictor) {
        this.numePictor = numePictor;
        return this;
    }

    public TablouBuilder withCuloare(TipCuloare culoare) {
        this.culoare = culoare;
        return this;
    }

    public String getNume() {
        return nume;
    }

    public double getPretMinim() {
        return pretMinim;
    }

    public int getAn() {
        return an;
    }

    public String getNumePictor() {
        return numePictor;
    }

    public TipCuloare getCuloare() {
        return culoare;
    }

    /**
     * Metoda build specifica care construieste obiectul.
     * @return Intoarce un obiect de tipul Tablou.
     */
    public Tablou build() {
        return new Tablou(this);
    }
}

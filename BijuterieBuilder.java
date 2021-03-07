/**
 * Clasa builder pentru bijuterie.
 */
public class BijuterieBuilder {
    // parametrii din clasa bijuterie
    private String nume;
    private double pretMinim;
    private int an;
    private String material;
    private boolean piatraPretioasa;

    /**
     * Constructor din builder cu param. drept numele bijuteriei.
     * @param nume String
     */
    public BijuterieBuilder(String nume) {
        this.nume = nume;
    }

    public BijuterieBuilder withPretMinim(double pretMinim) {
        this.pretMinim = pretMinim;
        return this;
    }

    public BijuterieBuilder withAn(int an) {
        this.an = an;
        return this;
    }

    public BijuterieBuilder withMaterial(String material) {
        this.material = material;
        return this;
    }

    public BijuterieBuilder withPiatraPretioasa(boolean piatraPretioasa) {
        this.piatraPretioasa = piatraPretioasa;
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

    public String getMaterial() {
        return material;
    }

    public boolean isPiatraPretioasa() {
        return piatraPretioasa;
    }

    /**
     * Metoda build specifica care construieste obiectul.
     * @return Intoarce un obiect de tipul Bijuterie.
     */
    public Bijuterie build() {
        return new Bijuterie(this);
    }
}

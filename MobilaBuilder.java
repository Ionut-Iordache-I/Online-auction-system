/**
 * Clasa builder pentru un obiect de mobilier.
 */
public class MobilaBuilder {
    private String nume;
    private double pretMinim;
    private int an;
    private String tip;
    private String material;

    /**
     * Constructorul ce primeste drept param. numele obiectului
     * de mobilier.
     * @param nume String
     */
    public MobilaBuilder(String nume) {
        this.nume = nume;
    }

    public MobilaBuilder withPretMinim(double pretMinim) {
        this.pretMinim = pretMinim;
        return this;
    }

    public MobilaBuilder withAn(int an) {
        this.an = an;
        return this;
    }

    public MobilaBuilder withTip(String tip) {
        this.tip = tip;
        return this;
    }

    public MobilaBuilder withMaterial(String material) {
        this.material = material;
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

    public String getTip() {
        return tip;
    }

    public String getMaterial() {
        return material;
    }

    /**
     * Metoda build specifica care construieste obiectul.
     * @return Intoarce un obiect de tipul Mobila.
     */
    public Mobila build() {
        return new Mobila(this);
    }
}

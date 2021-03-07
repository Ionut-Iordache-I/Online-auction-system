/**
 * Clasa care extinde Client cu atributele specifice
 * unei persoane juridice.
 */
public class PersoanaJuridica extends Client {
    private TipCompanie companie; // tipul de companie asociat
    private double capitalSocial; // capitalul pe care il are compania

    /**
     * Constructorul unui obiect de tipul PersoanaFizica.
     * Apeleaza construcotrul din clasa de baza.
     * @param nume Denumire companie.
     * @param adresa Adresa companie.
     * @param companie Tipul companiei.
     * @param capitalSocial Capitalul pe care il are compania.
     */
    public PersoanaJuridica(final String nume, final String adresa,
                            final TipCompanie companie, final double capitalSocial) {
        super(nume, adresa);
        this.companie = companie;
        this.capitalSocial = capitalSocial;
    }

    /**
     * Am suprascris toString pentru a afisa un mesaj la
     * aplicarea comenzii listaClienti.
     * @return Un mesaj sub forma unui string.
     */
    @Override
    public String toString() {
        return "Persoana juridica cu id: " + getId() + ", nume: "
                + getNume() + ", adresa: " + getAdresa() + ", numar de participari: "
                + getNrParticipari() + ", licitatii castigate: " + getNrLicitatiiCastigate()
                + ", companie: " + companie + " si capital: " + capitalSocial;
    }
}

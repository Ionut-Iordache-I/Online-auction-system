/**
 * Clasa care extinde Client cu atributele specifice
 * unei persoane fizice.
 */
public class PersoanaFizica extends Client {
    private String dataNastere; // data de nastere a unei persoane

    /**
     * Constructorul unui obiect de tipul PersoanaFizica.
     * Apeleaza construcotrul din clasa de baza.
     * @param nume Numele persoanei.
     * @param adresa Adresa persoanei.
     * @param dataNastere Data de nastere a persoanei.
     */
    public PersoanaFizica(final String nume, final String adresa,
                          final String dataNastere) {
        super(nume, adresa);
        this.dataNastere = dataNastere;
    }

    /**
     * Am suprascris toString pentru a afisa un mesaj la
     * aplicarea comenzii listaClienti.
     * @return Un mesaj sub forma unui string.
     */
    @Override
    public String toString() {
        return "Persoana fizica cu id: " + getId() + ", nume: "
                + getNume() + ", adresa: " + getAdresa() + ", numar de participari: "
                + getNrParticipari() + ", licitatii castigate: " + getNrLicitatiiCastigate()
                + " si data nastere: " + dataNastere;
    }
}

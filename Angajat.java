import java.util.ArrayList;

/**
 * Clasa ce descrie un angajat.
 */
public abstract class Angajat {
    private int idAngajat; // identificatorul unui angajat
    private static int nrAngajati = 1; // contor angajati creati

    /**
     * Constructorul unui angajat. Tot aici incrementez id-ul cu 1 si
     * astfel obtin unul unic la fiecare creere a unui angajat.
     */
    public Angajat() {
        this.idAngajat = nrAngajati++;
    }

    /**
     * Metoda abstracta.
     * @param clienti Lista de clienti.
     */
    public abstract void setClienti(ArrayList<Client> clienti);
}

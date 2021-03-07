import java.util.ArrayList;

/**
 * Clasa care extinde Angajat si poate adauga produse pentru licitatie
 */
public class Administrator extends Angajat {
    /**
     * Constructorul unui Administrator. Apeleaza constructorul clasei parinte.
     */
    public Administrator() {
        super();
    }

    /**
     * Suprascrie metoda abstracta.
     * @param clienti Lista de clienti.
     */
    @Override
    public void setClienti(ArrayList<Client> clienti) {
        //nu face nimic pentru administrator
    }

    /**
     * Metoda adauga un produs in lista de produse pentru licitatie din casa.
     * @param produs Produsul adaugat in lista de produse din casa.
     */
    public synchronized void adaugaProdus(final Produs produs) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // adaug un produs in lista de produse din casa
        CasaLicitatii.getInstance().getProduseVanzare().add(produs);
    }

}

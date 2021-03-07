import java.util.ArrayList;

/**
 * Clasa cu atribute si metode specifice unei licitatii.
 */
public class Licitatie {
    private int id; // identificator licitatie
    // numar necesar de participanti pentru a incepe licitatia
    private int nrParticipanti;
    // identificatorul produsului pentru care are loc licitatia
    private int idProdus;
    // pasi pentru o licitatie
    private int nrPasiMaxim;
    // clienti care doresc sa participe la licitatie
    private int cereriClienti = 0;
    // contor licitatii create
    private static int nrLicitatii = 1;
    // o licitatie poate avea mai multi observatori
    private ArrayList<Obervator> obervators;

    /**
     * Constructorul unei licitatii. Tot aici incrementez id-ul cu 1 si
     * astfel obtin unul unic la fiecare creere a unei licitatii.
     * @param nrParticipanti Numarul participantilor pentru o licitatie.
     * @param idProdus Identificatorul produsului pentru care are loc licitatia
     * @param nrPasiMaxim Pasii in care se desfasoara licitatia.
     */
    public Licitatie(int nrParticipanti, int idProdus, int nrPasiMaxim) {
        this.id = nrLicitatii++;
        this.nrParticipanti = nrParticipanti;
        this.idProdus = idProdus;
        this.nrPasiMaxim = nrPasiMaxim;
        this.obervators = new ArrayList<>();
    }

    /**
     * Getter pentru nr de clienti ce vor sa participe la o licitatie
     * @return Numarul clientilor ce doresc sa participe la licitatie
     */
    public int getCereriClienti() {
        return cereriClienti;
    }

    /**
     *  Metoda incrementeaza cereriClienti adaugand astfel
     *  un nou doritor pentru licitatia curenta.
     */
    public void incrementCereriClienti() {
        cereriClienti++;
    }

    /**
     * Adaug un observator in lista de observatori
     * @param client Observator adaugat in lista.
     */
    public void addObservator(Client client) {
        obervators.add(client);
    }

    /**
     * Getter pentru id-ul licitatiei.
     * @return Identificatorul licitatiei.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pentru numarul necesar de participanti la licitatie.
     * @return Intoarce numarul obligatoriu de participanti la licitatie.
     */
    public int getNrParticipanti() {
        return nrParticipanti;
    }

    /**
     * Getter pentru id-ul produsului pentru care are loc licitaia.
     * @return Intoarce id-ul produsului pentru care se realizeaza licitatia.
     */
    public int getIdProdus() {
        return idProdus;
    }

    /**
     * Getter pentru numarul de pasi al unei licitatii.
     * @return Intoarce un int reprezentand nr. de pasi ai licitatiei.
     */
    public int getNrPasiMaxim() {
        return nrPasiMaxim;
    }

    /**
     * La incheierea licitatiei observatorii sunt actualizati.
     */
    public void incheieLicitatie() {
        CasaLicitatii casa = CasaLicitatii.getInstance();
        // nume produs dupa id licitatiei
        String nume = casa.getProdusByLicitatieId(this.id).getNume();
        // actualizez toti observatorii, afisand mesaj
        for (Obervator obervator : obervators) {
            obervator.actualizeaza(nume);
        }
    }

    /**
     * Am suprascris toString pentru a afisa un mesaj la
     * aplicarea comenzii listaLicitatii.
     * @return Un mesaj sub forma unui string.
     */
    @Override
    public String toString() {
        return "Id: " + id + ", Numar Participanti: " +
                nrParticipanti + ", Id produs: " + idProdus;
    }
}

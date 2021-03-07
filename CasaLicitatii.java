import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Clasa cu atribute si metode specifice casei de licitatii.
 */
public class CasaLicitatii {
    private static CasaLicitatii instantaUnica;
    // lista de produse disponibile in casa de licitatii
    private ArrayList<Produs> produseVanzare;
    // lista de clienti din casa de licitatii
    private ArrayList<Client> clientiSistem;
    // lista de licitatii din casa
    private ArrayList<Licitatie> licitatiiActive;
    // lista de brokeri din casa de licitatii
    private ArrayList<Broker> listaBrokeri;
    // mapa cu cheia reprezentand id-ul licitatiei si
    // cu val. reprezentand pret maxim la licitatie
    private HashMap<Integer, Double> mapaPreturiMaxime;

    /**
     * Constructor pentru singleton design pattern.
     */
    private CasaLicitatii() {
        produseVanzare = new ArrayList<>();
        clientiSistem = new ArrayList<>();
        licitatiiActive = new ArrayList<>();
        listaBrokeri = new ArrayList<>();
        mapaPreturiMaxime = new HashMap<>();
    }

    /**
     * Metoda statica care controleaza accesul
     * la instanta clasei CasaLicitatii
     * @return Intoarce instanta unica a clasei CasaLicitatii
     */
    public static CasaLicitatii getInstance() {
        if (instantaUnica == null)
            instantaUnica = new CasaLicitatii();
        return instantaUnica;
    }

    /**
     * Getter pentru lista de produse din casa de licitatii.
     * @return Intoarce lista de obiecte de tipul Produs
     * din casa de licitatii.
     */
    public ArrayList<Produs> getProduseVanzare() {
        return produseVanzare;
    }

    /**
     * Getter pentru lista de licitatii din casa de licitatii.
     * @return Intoarce lista de obiecte de tipul Licitatie
     * din casa de licitatii.
     */
    public ArrayList<Licitatie> getLicitatiiActive() {
        return licitatiiActive;
    }

    /**
     * Getter pentru lista de brokeri din casa de licitatii.
     * @return Intoarce lista de obiecte de tipul Broker
     * din casa de licitatii.
     */
    public ArrayList<Broker> getListaBrokeri() {
        return listaBrokeri;
    }

    /**
     * Metoda adauga clientul dat ca param. in lista de clienti.
     * @param client Un obiect de tipul Client.
     */
    public void adaugaClient(Client client) {
        clientiSistem.add(client);
    }

    /**
     * Metoda adauga licitatia data ca param. in lista de licitatii.
     * @param licitatie Un obiect de tipul Licitatie.
     */
    public void adaugaLicitatie(Licitatie licitatie) {
        licitatiiActive.add(licitatie);
    }

    /**
     * Metoda adauga brokerul data ca param. in lista de brokeri.
     * @param broker Un obiect de tipul Broker.
     */
    public void adaugaBroker(Broker broker) {
        listaBrokeri.add(broker);
    }

    /**
     * Metoda afiseaza la output licitatiile casei
     * la selectarea comenzii listaLicitatii.
     */
    public void afisareLicitatii() {
        for (Licitatie licitatie : licitatiiActive) {
            System.out.println(licitatie.toString());
        }
    }

    /**
     * Metoda afiseaza la output produsele casei
     * la selectarea comenzii listaProduse.
     */
    public void afisareProduse() {
        for (Produs produs : produseVanzare) {
            System.out.println(produs.toString());
        }
    }

    /**
     * Metoda afiseaza la output clientii casei
     * la selectarea comenzii listaClienti.
     */
    public void afisareClienti() {
        for (Client client : clientiSistem) {
            System.out.println(client.toString());
        }
    }

    /**
     * Metoda care gaseste licitatia care se
     * efectueaza pentru produsul al carui idProdus il dau
     * ca param.
     * @param idProdus Identificatorul unic al unui produs.
     * @return Licitatia care se efectueaza pentru produsul cu
     * idProdus-ul dat ca param.
     */
    public Licitatie getLicitatieByProdusId(int idProdus) {
        for (Licitatie licitatie : licitatiiActive) {
            if (licitatie.getIdProdus() == idProdus) {
                return licitatie;
            }
        }
        return null;
    }

    /**
     * Metoda care returneaza produsul pe care il are
     * o licitatie.
     * @param idLicitatie Id-ul unei licitatii.
     * @return Intoarce un Produs.
     */
    public Produs getProdusByLicitatieId(int idLicitatie) {
        int idProdus = 0;
        for (Licitatie licitatie : licitatiiActive) {
            if (licitatie.getId() == idLicitatie) {
                idProdus = licitatie.getIdProdus();
                break;
            }
        }
        // returnez produsul din lista dupa idProdus
        for (Produs produs : produseVanzare) {
            if (produs.getId() == idProdus) {
                return produs;
            }
        }
        return null;
    }

    /**
     * Asociere broker random pentru client si pornire licitatie
     * la indeplinirea conditiei.
     * @param produs Produsul pentru care se doreste licitatia
     * @param client Clientul cu interes pentru produs.
     */
    public void solicitareProdus(Produs produs, Client client) {
        // determin random un broker din lista de brokeri
        Random rand = new Random();
        Broker randomBroker = listaBrokeri.get(rand.nextInt(listaBrokeri.size()));
        // adaug in lista de clienti a broker-ului, clientul acestuia
        randomBroker.addClient(client);
        // adaug clientului, brokerul pe care l-am generat
        client.addBroker(randomBroker);
        // retin licitatia pentru produsul primit ca param.
        Licitatie licitatie = this.getLicitatieByProdusId(produs.getId());
        if (licitatie == null) {
            System.out.println("Produsul nu este scos la licitatie," +
                    "nu se afla in lista de licitatii !");
        } else {
            // adaug observator licitatiei
            licitatie.addObservator(client);
            // incrementez noua cerere pentru licitatie
            licitatie.incrementCereriClienti();
        }
        // verificare conditie impusa
        if (licitatie.getCereriClienti() == licitatie.getNrParticipanti()) {
            // start licitatie
            StartLicititatie sarcina = new StartLicititatie(licitatie);
            Thread t = new Thread(sarcina);
            t.start();
        }
    }

    /**
     * Metoda care gaseste clientul in lista de clienti
     * dupa id.
     * @param idClient Identificatorul clientului.
     * @return Intoarce clientul dupa id sau null daca nu se
     * afla in lista.
     */
    public Client getClientById(int idClient) {
        for (Client client : clientiSistem) {
            if (client.getId() == idClient) {
                return client;
            }
        }
        return null;
    }

    /**
     * Metoda care primeste pretul licitatiei si il
     * actualizeaza in mapa de preturi maxime daca este
     * mai mare decat pretul precedent.
     * @param idLicitatie Licitatia pentru care avem un pret.
     * @param pret Pretul acordat la aceasta licitatie.
     */
    public void primestePret(int idLicitatie, double pret) {
        // trebuie sa salvez pe licitatie pretul
        // daca inca nu am licitatia in mapa o adaug
        if (!mapaPreturiMaxime.containsKey(idLicitatie)) {
            mapaPreturiMaxime.put(idLicitatie, pret);
        } else {
            // daca am pretul primit ca param. mai mare decat
            // cel deja cunoscut pentru licitatie o sa il modific
            // cu cel mai mare
            if (mapaPreturiMaxime.get(idLicitatie) < pret) {
                mapaPreturiMaxime.put(idLicitatie, pret);
            }
        }
    }

    /**
     * Getter pentru pret maxim la licitatie dupa id-ul licitatiei
     * @param idLicitatie Identificatorul unei licitatii.
     * @return Intoarce pretul maxim al licitatiei care are id-ul
     * specificat ca param.
     */
    public double getPretMaximFinalLicitatie(int idLicitatie) {
        return mapaPreturiMaxime.get(idLicitatie);
    }

    /**
     * Metoda comunica pretul la pasul curent folosind
     * id-ul licitatiei.
     * @param idLicitatie Identificatorul licitatiei careia
     * ii trimitem pretul.
     */
    public void trimitePretMaximPas(int idLicitatie) {
        for (Broker broker : listaBrokeri) {
            // sunt instintati brokerii de pretul maxim acordat la acel pas
            broker.primestePretCasa(getProdusByLicitatieId(idLicitatie), mapaPreturiMaxime.get(idLicitatie));
        }
    }
}

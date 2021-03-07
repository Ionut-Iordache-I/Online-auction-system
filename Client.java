import java.util.HashMap;
import java.util.Map;

/**
 * Clasa cu atribute si metode specifice unui client care interactoneaza
 * cu casa de licitatii si broker-ul ulterior alocat acestuia.
 */
public class Client implements Comparable<Client>, Obervator {
    private int id; // identificatorul clientului
    private String nume; // numele clientului
    private String adresa; // adresa clientului
    // numarul de licitatii la care a participat clientul, initial 0
    private int nrParticipari = 0;
    // numarul de licitatii castigate de catre client, initial 0
    private int nrLicitatiiCastigate = 0;
    // mapa ce descrie licitatiile la care doreste sa participe clientul,
    // la cheie este produsul si la valoare suma maxima pe care o poate oferi pt. produs
    private HashMap<Produs, Double> mapaLicitatii;
    // broker-ul clientului
    private Broker broker = null;
    // contor clienti creati
    private static int nrCLienti = 1;
    // pret ce descrie suma de licitat la un moment dat
    private double price;
    // mapa ce retine la cheie produsul si la val. pretul max
    // asociat pentru acesta la un anumit pas
    private HashMap<Produs, Double> mapaLicitatiePretMaxPas;
    /**
     * Constructorul unui client. Tot aici incrementez id-ul cu 1 si
     * astfel obtin unul unic la fiecare creere a unui client.
     * @param nume Numele clientului.
     * @param adresa Adresa clientului.
     */
    public Client(final String nume, final String adresa) {
        this.id = nrCLienti++;
        this.nume = nume;
        this.adresa = adresa;
        this.nrParticipari = 0;
        this.nrLicitatiiCastigate = 0;
        this.mapaLicitatii = new HashMap<>();
        this.price = nume.charAt(0); // suma de licitat folosind codul ASCII al
                                    // primei litere din nume client
        this.mapaLicitatiePretMaxPas = new HashMap<>();
    }

    /**
     * Getter pentru pret de licitat(suma de licitat)
     * @return Intoarce suma de licitat actuala
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter pentru identificatorul clientului.
     * @return Intoarce id-ul clientului.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pentru numele clientului.
     * @return Intoarce numele clientului.
     */
    public String getNume() {
        return nume;
    }

    /**
     * Getter pentru adresa clientului.
     * @return Intoarce adresa clientului.
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Getter pentru numarul de licitatii la care participa clientul
     * @return Int reprezentand nr. de licitatii la care participa clientul.
     */
    public int getNrParticipari() {
        return nrParticipari;
    }

    /**
     * Getter pentru nr. de licitatii castigate de client.
     * @return Intoarce nr. de licitatii castigate de client.
     */
    public int getNrLicitatiiCastigate() {
        return nrLicitatiiCastigate;
    }

    /**
     * Getter pentru mapa ce contine licitatiile la care
     * clientul doreste sa participe avand cheie produsul respectiv
     * si ca valoare suma pe care acesta este dispus sa o plateasca
     * pentru produs.
     * @return Intoarce mapa cu licitatiile dorite de client.
     */
    public HashMap<Produs, Double> getMapaLicitatii() {
        return mapaLicitatii;
    }

    /**
     * Getter pentru broker-ul clientului.
     * @return Intoarce broker-ul clientului.
     */
    public Broker getBroker() {
        return broker;
    }

    /**
     * Verific daca am broker pentru client si daca nu il adaug.
     * @param br Broker-ul pentru client.
     */
    public void addBroker(Broker br) {
        if (this.broker == null) {
            this.broker = br;
        }
    }

    /**
     * Metoda care efectueaza cautarea unui produs in casa de licitatii
     * si pune in mapa de licitatii a clientului produsul si pretul maxim
     * asociat acestuia, solicitand totodata casa.
     * @param id Identificatorul produsului dat ca param.
     * @param pretMax Pretul pe care clientul il poate plati pentru produs.
     */
    public void solicitareProdus(int id, double pretMax) {
        CasaLicitatii casa = CasaLicitatii.getInstance();
        Produs produs1 = null; // in care voi retine produsul din casa
                                // daca exista in aceasta
        for (Produs produs : casa.getProduseVanzare()) {
            if (produs.getId() == id) {
                produs1 = produs;
                break;
            }
        }
        if (produs1 == null) {
            System.out.println("Produsul nu exista in lista din casa de licitatii!");
        } else {
            // adaug in mapa de licitatii produsul dupa id
            // si pretul maxim pe care clientul il poate plati pt. acesta
            mapaLicitatii.put(produs1, pretMax);
            // trimite solicitare catre casa de licitatii
            casa.solicitareProdus(produs1, this);
        }
    }

    /**
     * Metoda determina un mod de a calcula pretul acordat
     * unui produs la un anumit pas.
     * @param produs Produsul pentru care se doreste pretul.
     */
    public void trimitePret(Produs produs) {
        // modalitate de a calcula pretul
        double pretMaximClient = 0.0;
        for (Map.Entry<Produs, Double> produsDoubleEntry : mapaLicitatii.entrySet()) {
            if (produsDoubleEntry.getKey().getId() == produs.getId()) {
                // pretul maxim pe care il poate da un client pe produs
                pretMaximClient = produsDoubleEntry.getValue();
                break;
            }
        }

        Double pretAnterior = mapaLicitatiePretMaxPas.get(produs); // suma acordata pas anterior
        if (pretAnterior == null) {
            if (price + this.nume.length() <= pretMaximClient) {
                price += this.nume.length();
            } else {
                price = pretMaximClient;
            }

        } else {
            if (pretAnterior + this.nume.length() <= pretMaximClient) {
                price = pretAnterior + this.nume.length();
            } else {
                price = pretMaximClient;
            }
        }
        // transmit broker-ului pretul acordat pentru produs
        broker.primestePretClient(produs, price);
    }

    /**
     * Metoda actualizeaza mapa cu pretul produsului la pasul curent
     * @param produs Produsul pentru care se liciteaza.
     * @param pret Pretul produsului la un anumit pas.
     */
    public void primestePret(Produs produs, double pret) {
        this.mapaLicitatiePretMaxPas.put(produs, pret);
    }

    /**
     * Am suprascris metoda.
     * @param o Un obiect de tipul client.
     * @return Intoarce un int.
     */
    @Override
    public int compareTo(Client o) {
        return o.nrLicitatiiCastigate - this.nrLicitatiiCastigate;
    }

    /**
     * Am suprascris metoda din interfata pentru a afisa un mesaj
     * la actualizarea unui client la final.
     * @param numeProdus Numele produsului din licitatie.
     */
    @Override
    public void actualizeaza(String numeProdus) {
        System.out.println("Licitatia pentru produsul " + numeProdus + " s-a incheiat.");
    }
}

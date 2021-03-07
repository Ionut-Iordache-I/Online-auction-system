import java.util.ArrayList;

/**
 * Clasa ce descrie un broker ce ulterior va gestiona
 * relatia dintre casa si client, cu lista de clienti ai acestuia
 */
public class Broker extends Angajat {
    private ArrayList<Client> clienti; // lista de clienti ai broker-ului

    /**
     * Constructorul pentru broker ce apeleaza
     * super() si initializeaza lista de clienti
     * a brokerului.
     */
    public Broker() {
        super();
        clienti = new ArrayList<>();
    }

    /**
     * Getter pentru lista de clienti ai broker-ului.
     * @return Lista de clienti ai broker-ului.
     */
    public ArrayList<Client> getClienti() {
        return clienti;
    }

    /**
     * Suprascrierea metodei pentru a seta lista de clienti
     * a brokerului conform celei date ca parametru.
     * @param clienti Lista de clienti.
     */
    @Override
    public void setClienti(ArrayList<Client> clienti) {
        this.clienti = clienti;
    }

    /**
     * Metoda adauga un client in lista de clienti ai broker-ului.
     * @param client Un obiect de tipul client ce va fi adaugat in lista.
     */
    public void addClient(Client client) {
        this.clienti.add(client);
    }

    public double comisionareClient(Client client, Produs produs) {
        Client clientComisionat = null;
        for (Client client1 : clienti) {
            if (client1.getId() == client.getId()) {
                clientComisionat = client1;
                break;
            }
        }
        if (clientComisionat == null) {
            System.out.println("Nu am gasit clientul " +
                    "cautat pentru aplicarea comisionului");
        }
        else {
            if (clientComisionat instanceof PersoanaFizica &&
                    clientComisionat.getNrParticipari() < 5) {
                // comision 20% din pretul maxim disponibil pentru produs
                return 0.2 * clientComisionat.getMapaLicitatii().get(produs);
            }
            else if (clientComisionat instanceof PersoanaFizica &&
                    clientComisionat.getNrParticipari() >= 5) {
                // comision 15% din pretul maxim disponibil pentru produs
                return 0.15 * clientComisionat.getMapaLicitatii().get(produs);
            }
            else if (clientComisionat instanceof PersoanaJuridica &&
                    clientComisionat.getNrParticipari() < 25) {
                // comision 25% din pretul maxim disponibil pentru produs
                return 0.25 * clientComisionat.getMapaLicitatii().get(produs);
            }
            else if (clientComisionat instanceof PersoanaJuridica &&
                    clientComisionat.getNrParticipari() >= 25) {
                // comision 10% din pretul maxim disponibil pentru produs
                return 0.1 * clientComisionat.getMapaLicitatii().get(produs);
            }
        }
        return 0.0;
    }

    /**
     * Solicitare catre client pentru pretul produsului.
     * @param produs Pretul pentru produs.
     */
    public void solicitaPret(Produs produs) {
        for (Client client : clienti) {
            // verificare daca mapa contine produsul cautat
            if (client.getMapaLicitatii().containsKey(produs)) {
                client.trimitePret(produs);
            }
        }
    }

    /**
     * Metoda primeste pretul clientului si il transmite casei.
     * @param produs Produsul pentru care se liciteaza.
     * @param pret Pretul acordat acestui produs.
     */
    public void primestePretClient(Produs produs, double pret) {
        CasaLicitatii casa = CasaLicitatii.getInstance();
        // apelez metoda din casa de licitatii
        casa.primestePret(casa.getLicitatieByProdusId(produs.getId()).getId(), pret);
    }

    /**
     * Metoda primeste pretul produsului de la casa.
     * @param produs Produsul de la licitatie.
     * @param pret Pretul produsului .
     */
    public void primestePretCasa(Produs produs, double pret) {
        for (Client client : clienti) {
            if (client.getMapaLicitatii().containsKey(produs)) {
                client.primestePret(produs, pret);
            }
        }
    }

}

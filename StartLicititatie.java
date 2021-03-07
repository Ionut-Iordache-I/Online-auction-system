import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Sarcina ce implementeaza Runnable si contine metoda run() care
 * va constitui mecanismul de licitare.
 */
public class StartLicititatie implements Runnable {

    private Licitatie licitatie;

    /**
     * Constructorul ce actualizeaza atributul licitatie.
     * @param licitatie Un obiect de tipul licitatie.
     */
    public StartLicititatie(Licitatie licitatie) {
        this.licitatie = licitatie;
    }

    /**
     * Suprascriu metoda run().
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            CasaLicitatii casa = CasaLicitatii.getInstance();
            // lista de brokeri din casa de licitatii
            ArrayList<Broker> brokeri = casa.getListaBrokeri();
            // produsul pentru care se liciteaza
            Produs produs = casa.getProdusByLicitatieId(licitatie.getId());
            // la apelarea metodei afisez mesajul initial
            System.out.println("Licitatie pentru produsul: " + produs.getNume());

            for (int i = 0; i < licitatie.getNrPasiMaxim(); i++) {
                for (Broker broker : brokeri) {
                    // broker-ul solicita pretul produsului
                    broker.solicitaPret(casa.getProdusByLicitatieId(licitatie.getId()));

                }
                // casa instinteaza ulterior brokerul care va instinta
                // la randul sau cientul de pretul maxim de la un pas
                casa.trimitePretMaximPas(licitatie.getId());
            }

            // pretul cu care se vinde produsul
            double pretvanzare =casa.getPretMaximFinalLicitatie(licitatie.getId());
            // verific condtitia impusa
            if (pretvanzare < produs.getPretMinim()) {
                System.out.println("Produsul nu se vinde! Pretul oferit nu atinge minimul!");
            }
            else {
                // actualizez pret vanzare
                produs.setPretVanzare(pretvanzare);
                // lista cu clientii castigatori la licitatie
                ArrayList<Client> clientiCastigatori = new ArrayList<>();
                // parcurg lista de brokeri
                for (Broker broker : brokeri) {
                    // parcurg clientii brokerului
                    for (Client client : broker.getClienti()) {
                        if (client.getMapaLicitatii().containsKey(produs)) {
                            // se plateste brokerului comision daca clientul
                            // liciteaza pentru produs
                            System.out.println("Clientul " + client.getNume() + " plateste "
                                    + broker.comisionareClient(client, produs) + " brokerului ca si comision");
                            // verific daca am clientul care are suma cu care se vinde
                            // produsul si il adaug in lista de posibili castigatori
                            if (client.getPrice() == pretvanzare) {
                                clientiCastigatori.add(client);
                            }
                        }
                    }
                }
                licitatie.incheieLicitatie();
                    Collections.sort(clientiCastigatori);
                    Client clientCastigator = clientiCastigatori.get(0);
                    System.out.println("Clientul care a castigat licitatia produsului "
                            + produs.getNume() + " este " + clientCastigator.getNume());
                }
                // elimin licitatia din lista de licitatii active
                casa.getLicitatiiActive().remove(licitatie);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

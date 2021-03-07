import exceptions.WrongNumberParametersExcepion;

import java.util.Scanner;

/**
 * Clasa care implementeaza consola cu comenzile pentru testarea programului.
 */
public class Main {
    public static void main(String[] args) {
        CasaLicitatii casaLicitatii = CasaLicitatii.getInstance(); // instanta unica pentru casa
        Scanner scan = new Scanner(System.in);
        AngajatFactory factory = new AngajatFactory();
        // creare administrator folosind factory
        Angajat admin = factory.creeazaAngajat("administrator");

        afisare();
        String user = scan.nextLine();
        afisare1(user);

        boolean exit = false;
        while (!exit) {
            String data = scan.nextLine();
            String[] dataSet = data.split(" ");
            try {
                switch (dataSet[0]) {
                    case "addClient":
                        if (dataSet.length < 5 || dataSet.length > 6) {
                            throw new WrongNumberParametersExcepion();
                        }
                        adaugaClient(casaLicitatii, dataSet);
                        break;

                    case "listaClienti":
                        casaLicitatii.afisareClienti();

                    case "addBroker":
                        casaLicitatii.adaugaBroker(new Broker());
                        break;

                    case "addProdus":
                        if (dataSet.length != 7) {
                            throw new WrongNumberParametersExcepion();
                        }
                        adaugaProdus((Administrator) admin, dataSet);
                        break;

                    case "listaProduse":
                        casaLicitatii.afisareProduse();
                        break;

                    case "addLicitatie":
                        if (dataSet.length != 4) {
                            throw new WrongNumberParametersExcepion();
                        }
                        int nrParticipanti = Integer.parseInt(dataSet[1]);
                        int idProdus = Integer.parseInt(dataSet[2]);
                        int nrPasiMaxim = Integer.parseInt(dataSet[3]);
                        Licitatie licitatie = new Licitatie(nrParticipanti, idProdus, nrPasiMaxim);
                        casaLicitatii.adaugaLicitatie(licitatie);
                        break;

                    case "listaLicitatii":
                        casaLicitatii.afisareLicitatii();
                        break;

                    case "solicitaProdus":
                        String id1 = dataSet[1];
                        Client client = casaLicitatii.getClientById(Integer.parseInt(id1));
                        String id1Produs = dataSet[2];
                        String pretMax = dataSet[3];
                        // clientul efectueaza solicitarea pentru produs
                        client.solicitareProdus(Integer.parseInt(id1Produs), Double.parseDouble(pretMax));
                        break;
                    // opreste programul
                    case "exit":
                        exit = true;
                        break;
                    // opreste programul
                    case "quit":
                        exit = true;
                        break;
                    default:
                        break;
                }
            } catch (WrongNumberParametersExcepion | ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void afisare1(String user) {
        if (user.equals("1")) {
            System.out.println("Comenzi disponibile client:");
            System.out.println("listaProduse");
            System.out.println("exit");
            System.out.println("quit");
        }
        else if (user.equals("2")) {
            System.out.println("Comenzi disponibile administrator:");
            System.out.println("addClient");
            System.out.println("addBroker");
            System.out.println("addProdus");
            System.out.println("addLicitatie");
            System.out.println("listaClienti");
            System.out.println("listaProduse");
            System.out.println("listaLicitatii");
            System.out.println("solicitaProdus");
            System.out.println("exit");
            System.out.println("quit");
        }
    }

    private static void adaugaClient(CasaLicitatii casaLicitatii, String[] dataSet) {
        String tipClient = dataSet[1];
        String nume = dataSet[2];
        String adresa = dataSet[3];
        if (tipClient.equals("fizic")) {
            String dataNastere = dataSet[4];
            Client client = new PersoanaFizica(nume, adresa, dataNastere);
            casaLicitatii.adaugaClient(client);
        }
        else if (tipClient.equals("juridic")) {
            TipCompanie companie = TipCompanie.valueOf(dataSet[4]);
            double capitalSocial = Double.parseDouble(dataSet[5]);
            Client client = new PersoanaJuridica(nume, adresa, companie, capitalSocial);
            casaLicitatii.adaugaClient(client);
        }
        else {
            System.out.println("Format pentru client introdus gresit !");
        }
    }

    private static void adaugaProdus(Administrator admin, String[] dataSet) {
        Produs produsFinal;
        String tipProdus = dataSet[1];
        String nume1 = dataSet[2];
        double pretMinim = Double.parseDouble(dataSet[3]);
        int an = Integer.parseInt(dataSet[4]);
        if (tipProdus.equals("tablou")) {
            String numePictor = dataSet[5];
            TipCuloare culoare = TipCuloare.valueOf(dataSet[6]);
            produsFinal = new TablouBuilder(nume1)
                                .withPretMinim(pretMinim)
                                .withAn(an)
                                .withNumePictor(numePictor)
                                .withCuloare(culoare)
                                .build();
            admin.adaugaProdus(produsFinal);
        }
        else if (tipProdus.equals("mobila")) {
            String tip = dataSet[5];
            String material = dataSet[6];
            produsFinal = new MobilaBuilder(nume1)
                                .withPretMinim(pretMinim)
                                .withAn(an)
                                .withTip(tip)
                                .withMaterial(material)
                                .build();
            admin.adaugaProdus(produsFinal);
        }
        else if (tipProdus.equals("bijuterie")) {
            String material1 = dataSet[5];
            boolean piatraPretioasa = Boolean.parseBoolean(dataSet[6]);
            produsFinal = new BijuterieBuilder(nume1)
                                .withPretMinim(pretMinim)
                                .withAn(an)
                                .withMaterial(material1)
                                .withPiatraPretioasa(piatraPretioasa)
                                .build();
            admin.adaugaProdus(produsFinal);
        } else {
            System.out.println("Tip produs necorespunzator !");
        }
    }

    public static void afisare() {
        System.out.println("Specificati tipul de utilizator:");
        System.out.println("1 -> Client");
        System.out.println("2 -> Administrator");
    }
}
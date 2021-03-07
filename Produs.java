/**
 * Clasa cu atribute si metode specifice unui produs care va fi licitat.
 */
public class Produs {
    private int id = 0; // identificatorul unui produs
    private String nume; // numele produsului
    // pretul la care este vandut produsul, intitial are valoarea 0
    private double pretVanzare = 0;
    // pretul minim pentru care se poate vinde un produs,
    // cunoscut la adaugarea produsului in casa de licitatii
    private double pretMinim;
    // anul de fabricatie al produsului
    private int an;
    // contor produse create
    private static int nrProduse = 1;

    /**
     * Constructorul unui produs. Tot aici incrementez id-ul cu 1 si
     * astfel obtin unul unic la fiecare creere a unui produs.
     * @param nume Numele unui produs.
     * @param pretMinim Pretul minim pentru care se vinde produsul.
     * @param an Anul de fabricatie al produsului.
     */
    public Produs(String nume, double pretMinim, int an) {
        this.id = nrProduse++;
        this.nume = nume;
        this.pretMinim = pretMinim;
        this.an = an;
    }

    /**
     * Getter pentru id-ul produsului.
     * @return Intoarce id-ul produsului.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter pentru numele produsului.
     * @return Intoarce numele produsului.
     */
    public String getNume() {
        return this.nume;
    }

    /**
     * Getter pentru pretul cu care se vinde produsul.
     * @return Intoarce pretul cu care se vinde produsul,
     * daca produsul nu s-a vandut intoarce 0.
     */
    public double getPretVanzare() {
        return this.pretVanzare;
    }

    /**
     * Getter pentru pretul minim cu care s-ar putea vinde produsl.
     * @return Intoarce pretul minim pentru care se poate vinde produsul
     */
    public double getPretMinim() {
        return pretMinim;
    }

    /**
     * Getter pentru anul de fabricatie al produsului.
     * @return Intoarce un int reprezentand anul fabricatiei produsului.
     */
    public int getAn() {
        return an;
    }

    /**
     * Metoda care actualizeaza pretul de vanzare al produsului.
     * @param pretVanzare Pretul cu care se vinde produsul.
     */
    public void setPretVanzare(double pretVanzare) {
        this.pretVanzare = pretVanzare;
    }
}

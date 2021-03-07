/**
 * Clasa care contine metoda de creare a angajatilor dupa tip.
 */
public class AngajatFactory {
    /**
     * Generez un subtip de angajat pe baza tip-ului
     * primit ca param. si verificat in switch.
     * @param tip Un string reprezentand numele angajatului dorit
     * @return Un angajat de tipul specificat.
     */
    public Angajat creeazaAngajat(String tip) {
        switch (tip.toLowerCase()) {
            case "administrator":
                return new Administrator();
            case "broker":
                return new Broker();
            default:
                return null;
        }
    }

}

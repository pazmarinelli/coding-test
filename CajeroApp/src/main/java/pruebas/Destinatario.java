package pruebas;

public class Destinatario extends Banco {

    String k = null;
    
    public Destinatario(char dest) {
        switch (dest) {
            case 'A':
                k = "Madre";
                break;
            case 'B':
                k = "Padre";
                break;
            case 'C':
                k = "Hermano";
                break;
            case 'D':
                k = "Hermana";
                break;
            default:
                break;
        }
    }

    public String mostrarNombre(){
        return k;
    }
}
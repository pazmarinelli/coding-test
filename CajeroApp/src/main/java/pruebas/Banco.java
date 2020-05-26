
package pruebas;

import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        cuentaBanco cb = new cuentaBanco("Federico", "PM1612");
        cb.mostrarMenu();
    }
}

class cuentaBanco{
    double saldo;
    String nombreCliente;
    String idCliente;
    String historial;
    
    cuentaBanco(String nombre, String id){
        this.nombreCliente = nombre;
        this.idCliente = id;
    }
    
    void deposito(double cantidad){
        if(cantidad>0){
            saldo = saldo + cantidad;
            System.out.println("\nHa depositado $" + cantidad + " en su cuenta");
        }
    }
    
    void retiro(double cantidad){
        if(cantidad>0 && cantidad<=saldo){
            saldo = saldo - cantidad;
            System.out.println("\nHa retirado $" + cantidad + " de su cuenta");
        }
        else{
            System.out.println("\nSaldo en cuenta insuficiente");
        }
    }
    
    
    void transferencia(double cantidad, char dest){
        if (dest == 'A' || dest == 'B' || dest == 'C' || dest == 'D'){
            if(cantidad<=saldo){
                Destinatario desti = new Destinatario(dest);
                String nombre = desti.mostrarNombre();
                saldo = saldo - cantidad;
                System.out.println("\nSe le ha transferido la cantidad de $" + cantidad + " a la cuenta de " + nombre);
            }
            else{
                System.out.println("\nSaldo en cuenta insuficiente");
            }
        }   
    }

    
    String mostrarMenu(){
        var opcion='\0';
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido " + nombreCliente + "!");
        System.out.println("ID: " + idCliente);
        
        do{
            System.out.println("\n");
            System.out.println("*********************************************");
            System.out.println("Elija una opción:");
            System.out.println("A. Ver estado de cuenta\nB. Depositar dinero\nC. Retirar dinero\nD. Realizar transferencia\nE. Salir");
            System.out.println("*********************************************");
            System.out.println("\n");
            opcion = scanner.next().charAt(0);
            
            switch(opcion){
                case 'A':
                    System.out.println("---------------------------------");
                    System.out.println("Saldo = $" + saldo);
                    System.out.println("---------------------------------");
                    break;
                case 'B':
                    System.out.println("---------------------------------");
                    System.out.println("Ingrese monto a depositar:");
                    System.out.println("---------------------------------");
                    double cantidad = scanner.nextDouble();
                    deposito(cantidad);
                    break;
                case 'C':
                    System.out.println("---------------------------------");
                    System.out.println("Ingrese monto a retirar:");
                    System.out.println("---------------------------------");
                    double cantidad2 = scanner.nextDouble();
                    retiro(cantidad2);
                    break;
                case 'D':
                    System.out.println("---------------------------------");
                    System.out.println("Seleccione el destinatario:\nA.Madre\nB.Padre\nC.Hermano\nD.Hermana");
                    var dest = scanner.next().charAt(0);
                    System.out.println("---------------------------------");
                    System.out.println("Seleccione el importe a transferir:");
                    double cant = scanner.nextDouble();
                    transferencia(cant,dest);
                    break;
                case 'E':
                    System.out.println("*************************************");
                    break;
                default:
                    System.out.println("Opción invalida!. Por favor, intente nuevamente (tenga en cuenta que el programa es sensible a mayúsculas y minúsculas)");
                    break;
            }
        }while(opcion != 'E');
        System.out.println("¡Gracias por utilizar nuestros servicios!");
        return null;
    }

}

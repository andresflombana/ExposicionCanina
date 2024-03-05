package Exposicion;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.Comparator;
import mundo.Perro;
/**
 *
 * @author Andrés Lombana - Johan Serrano
 */
public class GestorExposicionCanina {
    private ArrayList<Perro> perros; // Lista para almacenar los perros de la exposición
    private Scanner lector; // Scanner para entrada de usuario
    
    /**
     * Constructor de la clase GestorExposicionCanina.
     * Inicializa la lista de perros y el Scanner.
    */
    public GestorExposicionCanina() {
        perros = new ArrayList<>();
        lector = new Scanner(System.in);
    }
     /**
     * Método principal para ejecutar el programa.
     */
    public static void main(String[] args) {
        GestorExposicionCanina exposicion = new GestorExposicionCanina();
        exposicion.imprimirFechaYHora();
        exposicion.mostrarMenu();
    }
    /**
     * Método para imprimir la fecha y hora en la que se ejecuta el programa.
     */
    public void imprimirFechaYHora() {
        Date fechaHoraActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(formato.format(fechaHoraActual));
        System.out.println("");
    }
    /**
     * Método para mostrar el menú de opciones.
    */
    public void mostrarMenu() {
        boolean activo = true;
        do {
            System.out.println("_____ Exposicion Canina _____\n"
                    + "1. Registrar un nuevo perro\n"
                    + "2. Modificar la información de un perro\n"
                    + "3. Eliminar a un perro\n"
                    + "4. Mostrar lista de perros registrados\n"
                    + "5. Enlistar información de los perros\n"
                    + "6. Mostrar información de un perro específico\n"
                    + "7. Ver perro ganador de la exposición\n"
                    + "8. Ver perro con el menor puntaje\n"
                    + "9. Ver perro más viejo\n"
                    + "10. Terminar programa\n");
        try {
            int opcion = lector.nextInt();
            lector.nextLine(); // Consumir el salto de línea que sigue al número.

            switch (opcion) {
                case 1:
                    try {
                        registrarPerro();
                    } catch (NombreDuplicadoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    modificarPerro();
                    break;
                case 3:
                    eliminarPerro();
                    break;
                case 4:
                    mostrarPerros();
                    break;
                case 5:
                    enlistarInfoPerro();
                    break;
                case 6:
                    mostrarInfoPerro();
                    break;
                case 7:
                    buscarGanador();
                    break;
                case 8:
                    buscarMenorPuntaje();
                    break;
                case 9:
                    buscarPerroMasViejo();
                    break;
                case 10:
                    activo = false;
                    System.out.println("Has salido del programa");
                    break;
                default:
                    System.out.println("Esa opcion no existe");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un numero valido.");
            lector.nextLine(); 
        }
    } while (activo);
}        
    /**
    * Método para registrar un nuevo perro.
    *
    * @throws NombreDuplicadoException si el nombre del perro ya existe en la lista
    */
public void registrarPerro() throws NombreDuplicadoException {
   // Solicitar al usuario que ingrese el nombre del perro
   System.out.println("Ingrese el nombre del perro:");
   String nombre = lector.nextLine();

   // Verificar que el nombre no esté duplicado
   for (Perro perro : perros) {
       if (perro.getNombre().equalsIgnoreCase(nombre)) {
           throw new NombreDuplicadoException();
       }
   }

   // Solicitar al usuario que ingrese la raza del perro
   System.out.println("Ingrese la raza del perro:");
   String raza = lector.nextLine();

   // Solicitar al usuario que ingrese la edad del perro
   System.out.println("Ingrese la edad del perro:");
   int edad = lector.nextInt();
   lector.nextLine(); // Consumir el salto de línea que sigue al número

   // Solicitar al usuario que ingrese los puntos del perro en la exposición
    int puntos;
    do {
    System.out.println("Ingrese los puntos del perro de 1 a 10");
     puntos = lector.nextInt();
    if (puntos < 1 || puntos > 10) {
    System.out.println("La puntos debe estar entre 1 a 10. Inténtelo de otra vez.");
    }else{
    }
    } while (puntos < 1 || puntos > 10);
   lector.nextLine(); // Consumir el salto de línea que sigue al número

   // Solicitar al usuario que ingrese el nombre del archivo de la foto del perro
   System.out.println("Ingrese el nombre del archivo de la foto del perro (Ej: max.jpg):");
   String foto = lector.nextLine();

   // Crear y añadir el nuevo perro a la lista
   Perro nuevoPerro = new Perro(nombre, raza, edad, puntos, foto);
   perros.add(nuevoPerro);

   System.out.println("El perro ha sido registrado con éxito!");

}
    /**
     * Método para modificar la información de un perro .
     */
public void modificarPerro() {
    // Verificar si hay perros registrados en el sistema
    if (perros.isEmpty()) {
        System.out.println("No hay perros registrados en el sistema.");
        return;
    }

    // Solicitar al usuario que seleccione el perro a modificar
    System.out.println("Ingrese el nombre del perro que desea modificar:");
    String nombrePerroModificar = lector.nextLine();

    // Buscar el perro en la lista de perros
    for (Perro perro : perros) {
        // Verificar si el nombre del perro coincide con el nombre ingresado por el usuario
        if (perro.getNombre().equalsIgnoreCase(nombrePerroModificar)) {
            boolean activo2 = true;
            do {
                // Mostrar el menú de opciones para modificar el perro
                System.out.println("¿Qué dato desea modificar?\n"
                        + "1. Nombre: " + perro.getNombre() + "\n"
                        + "2. Raza: " + perro.getRaza() + "\n"
                        + "3. Edad: " + perro.getEdad() + "\n"
                        + "4. Puntos: " + perro.getPuntos() + "\n"
                        + "5. Foto: " + perro.getFoto() + "\n"
                        + "6. Salir\n"
                        + "_");
            try{ 
                int opcion2 = lector.nextInt();
                lector.nextLine(); // Consumir el salto de línea que sigue al número

                // Realizar la acción correspondiente según la opción seleccionada por el usuario
                switch (opcion2) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre del perro:");
                        String nuevoNombre = lector.nextLine();
                        perro.setNombre(nuevoNombre);
                        System.out.println("Dato cambiado con éxito.");
                        break;

                    case 2:
                        System.out.println("Ingrese la nueva raza:");
                        String nuevaRaza = lector.nextLine();
                        perro.setRaza(nuevaRaza);
                        System.out.println("Dato cambiado con éxito.");
                        break;

                    case 3:
                        System.out.println("Ingrese la nueva edad:");
                        int nuevaEdad = lector.nextInt();
                        perro.setEdad(nuevaEdad);
                        System.out.println("Dato cambiado con éxito.");
                        break;

                    case 4:
                        System.out.println("Ingrese el nuevo puntaje:");
                        int nuevoPuntaje = lector.nextInt();
                        perro.setPuntos(nuevoPuntaje);
                        System.out.println("Dato cambiado con éxito.");
                        break;

                    case 5:
                        System.out.println("Ingrese el nuevo nombre del archivo de la foto del perro (Ej: Max.jpg):");
                        String nuevaFoto = lector.nextLine();
                        perro.setFoto(nuevaFoto);
                        System.out.println("Dato cambiado con éxito.");
                        break;

                    case 6:
                        activo2 = false; // Salir del bucle
                        System.out.println("Usted salió de ´modificar perro´.");
                        break;

                    default:
                        System.out.println("Esa opción no existe");
                        break;
                }
                }catch(InputMismatchException e){
                    System.out.println("Ingrese un numero válido");
                    }
            } while (activo2);

            // Salir del bucle de búsqueda una vez que se haya modificado el perro
            return;
            }
            
    }

    // Si el perro no se encontró en la lista
    System.out.println("No se encontró ningún perro con ese nombre.");
}
    /**
     * Método para eliminar a un perro del sistema.
     */
public void eliminarPerro() {
   // Solicitar al usuario que ingrese el nombre del perro a eliminar
   System.out.println("Ingrese el nombre del perro que desea eliminar:");
   String nombreEliminar = lector.nextLine();

   // Buscar el perro en la lista de perros
   for (Perro perro : perros) {
       // Verificar si el nombre del perro coincide con el nombre ingresado por el usuario
       if (perro.getNombre().equalsIgnoreCase(nombreEliminar)) {
           // Solicitar al usuario confirmar la eliminación del perro
           System.out.println("¿Está seguro que desea eliminar el perro?\n1. Si\n2. No");
           int opcion = lector.nextInt();
           lector.nextLine(); // Consumir el salto de línea que sigue al número

           // Realizar la acción correspondiente según la opción seleccionada por el usuario
           if (opcion == 1) {
               // Eliminar el perro de la lista
               perros.remove(perro);
               System.out.println("Perro eliminado satisfactoriamente.");
           } else if (opcion == 2) {
               System.out.println("El perro no se eliminó.");
           }
           // Salir del bucle una vez que se ha encontrado y eliminado el perro
           break;
       } else {
           System.out.println("No se encontró ningún perro con ese nombre.");
       }
   }
}
     /**
     * Método para mostrar la lista de los perros registrados en la exposición.
     */
public void mostrarPerros() {
       // Verificar si hay perros registrados en la exposición
        if (perros.isEmpty()) {
        System.out.println("No hay perros registrados en la exposición.");
        return;
        }
        // Mostrar todos los perros registrados
        for (Perro p : perros) {
        System.out.println("Nombre: " + p.getNombre());
        System.out.println("Raza: " + p.getRaza());
        System.out.println("Edad: " + p.getEdad());
        System.out.println("Puntos: " + p.getPuntos());
        System.out.println("Foto: " + p.getFoto());
        System.out.println("-------------------");
         }
}
     /**
     * Método para enlistar la información de los perros.
     */
    public void enlistarInfoPerro() {
       // Verificar si hay perros registrados en la exposición
        if (perros.isEmpty()) {
        System.out.println("No hay perros registrados en la exposición.");
        return;
        }
        // Solicitar al usuario que ingrese la categoría de información que desea ver
        System.out.println("Seleccione la categoría que desea ver:\n"
                + "1. Nombre\n"
                + "2. Raza\n"
                + "3. Edad\n"
                + "4. Puntos\n"
                + "5. Foto\n");
        int categoria = lector.nextInt();
        lector.nextLine(); 

        // Mostrar la información de acuerdo a la categoría seleccionada
        switch (categoria) {
            case 1:
                mostrarNombresPerros();
                break;
            case 2:
                mostrarRazasPerros();
                break;
            case 3:
                mostrarEdadesPerros();
                break;
            case 4:
                mostrarPuntosPerros();
                break;
            case 5:
                mostrarFotosPerros();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private void mostrarNombresPerros() {
        // Método para mostrar los nombres de los perros
        Collections.sort(perros, Comparator.comparing(Perro::getNombre));
        System.out.println("Nombres de los perros:");
        for (Perro perro : perros) {
            System.out.println(perro.getNombre());
        }
    }

    private void mostrarRazasPerros() {
        // Método para mostrar las razas de los perros
        Collections.sort(perros, Comparator.comparing(Perro::getRaza));
        System.out.println("Razas de los perros:");
        for (Perro perro : perros) {
            System.out.println(perro.getRaza());
        }
    }

    private void mostrarEdadesPerros() {
        // Método para mostrar las edades de los perros
        Collections.sort(perros, Comparator.comparingInt(Perro::getEdad));
        System.out.println("Edades de los perros:");
        for (Perro perro : perros) {
            System.out.println(perro.getEdad());
        }
    }

    private void mostrarPuntosPerros() {
        // Método para mostrar los puntos de los perros
        Collections.sort(perros, Comparator.comparingInt(Perro::getPuntos).reversed());
        System.out.println("Puntos de los perros:");
        for (Perro perro : perros) {
            System.out.println(perro.getPuntos());
        }
    }

    private void mostrarFotosPerros() {
        // Método para mostrar las fotos de los perros
        Collections.sort(perros, Comparator.comparing(Perro::getFoto));
        System.out.println("Fotos de los perros:");
        for (Perro perro : perros) {
            System.out.println(perro.getFoto());
        }
}
     /**
     * Método para mostrar la información de un perro específico.
     */
private void mostrarInfoPerro() {
  // Verificar si hay perros registrados en la exposición
  if (perros.isEmpty()) {
      System.out.println("No hay perros registrados en la exposición.");
      return;
  }

  // Solicitar al usuario que ingrese el nombre del perro a buscar
  System.out.println("Ingrese el nombre del perro:");
  String nombrePerro = lector.nextLine();

  // Buscar el perro en la lista de perros
  for (Perro perro : perros) {
      if (perro.getNombre().equals(nombrePerro)) {
          // Mostrar los datos detallados del perro encontrado
          System.out.println("_INFORMACION DEL PERRO");
          System.out.println("Nombre: " + perro.getNombre());
          System.out.println("Raza: " + perro.getRaza());
          System.out.println("Edad: " + perro.getEdad());
          System.out.println("Puntos: " + perro.getPuntos());
          System.out.println("Foto: " + perro.getFoto());
          System.out.println("-------------------");
          return;
      }
  }
  // Si el perro no se encontró en la lista, informar al usuario
  System.out.println("No se encontró ningún perro con ese nombre.");
}

     /**
     * Método para buscar el perro ganador de la exposición (el que tiene un mayor puntaje asignado).
     */
public void buscarGanador() {
  // Verificar si hay perros registrados en la exposición
  if (perros.isEmpty()) {
      System.out.println("No hay perros registrados en la exposición.");
      return;
  }

  // Inicializar las variables necesarias para encontrar al perro ganador
  String perroGanador = "";
  int puntajeMaximo = -1; // Inicializamos el puntaje máximo con un valor mínimo

  // Recorrer la lista de perros para encontrar el perro con el puntaje máximo
  for (Perro perro : perros) {
      if (perro.getPuntos() > puntajeMaximo) {
          puntajeMaximo = perro.getPuntos();
          perroGanador = perro.getNombre();
      }
  }

  // Mostrar el nombre del perro ganador y su puntaje
  if (!perroGanador.isEmpty()) {
      System.out.println("El perro ganador de la exposición es: " + perroGanador + " con " + puntajeMaximo + " puntos.");
  } else {
      System.out.println("No se encontró un perro ganador en la exposición.");
  }
}
     /**
     * Método para buscar el perro con el menor puntaje.
     */
public void buscarMenorPuntaje() {
 // Verificar si hay perros registrados en la exposición
 if (perros.isEmpty()) {
     System.out.println("No hay perros registrados en la exposición.");
     return;
 }

 // Inicializar las variables necesarias para encontrar al perro con el menor puntaje
 String perroMenorPuntaje = "";
 int puntajeMinimo = Integer.MAX_VALUE; // Inicializamos el puntaje mínimo con el valor máximo posible

 // Recorrer la lista de perros para encontrar el perro con el puntaje mínimo
 for (Perro perro : perros) {
     if (perro.getPuntos() < puntajeMinimo) {
         puntajeMinimo = perro.getPuntos();
         perroMenorPuntaje = perro.getNombre();
     }
 }

 // Mostrar el nombre del perro con menor puntaje y su puntaje
 if (!perroMenorPuntaje.isEmpty()) {
     System.out.println("El perro con menor puntaje de la exposición es: " + perroMenorPuntaje + " con " + puntajeMinimo + " puntos.");
 } else {
     System.out.println("No se encontró un perro con menor puntaje en la exposición.");
 }
}
     /**
     * Método para buscar el perro más viejo de todos (con mayor edad).
     */
public void buscarPerroMasViejo() {
   // Verificar si hay perros registrados en la exposición
   if (perros.isEmpty()) {
       System.out.println("No hay perros registrados en la exposición.");
       return;
   }

   // Inicializar las variables necesarias para encontrar al perro más viejo
   String perroMasViejo = "";
   int edadMasVieja = -1; // Inicializamos la edad máxima con un valor mínimo

   // Recorrer la lista de perros para encontrar el perro más viejo
   for (Perro perro : perros) {
       if (perro.getEdad() > edadMasVieja) {
           edadMasVieja = perro.getEdad();
           perroMasViejo = perro.getNombre();
       }
   }

   // Mostrar el nombre del perro más viejo y su edad
   if (!perroMasViejo.isEmpty()) {
       System.out.println("El perro más viejo de la exposición es: " + perroMasViejo + " con " + edadMasVieja + " años.");
   } else {
       System.out.println("No se encontró un perro más viejo en la exposición.");
   }
 }
}
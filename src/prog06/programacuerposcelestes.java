/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog06;


import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;



/**
 *
 * @author jfunez
 */
public class programacuerposcelestes {
    
static BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
static List<CuerpoCeleste> cuerposCelestes = new ArrayList<>();
static File f =new File("cuerposCelestes.dat");
static CuerpoCeleste cceleste = new CuerpoCeleste();

    
    public static void main(String[] args) {     
        
   
       
    

//  Mostramos menu      
        mostrarMenu();
       
    

    }

    
    
     private static void mostrarMenu() {

        
        boolean enabledmostrarMenu = true;
        
        String busqueda = null;
       
          while (enabledmostrarMenu == true) { 
        System.out.println("Menu de Cuerpo Celeste:");
        System.out.println("\n");
        System.out.println("Selecciona una opcion.");
        System.out.println("          Opcion 1 Añadir cuerpo celeste:");
        System.out.println("          Opcion 2 Listar cuerpo celestes:");
        System.out.println("          Opcion 3 Buscar cuerpo celeste por código:");
        System.out.println("          Opcion 4 Buscar cuerpo celeste por tipo:");
        System.out.println("          Opcion 5 Borrar cuerpo celeste:");
        System.out.println("          Opcion 6 Borrar fichero de cuerpos celestes.:\n");
        System.out.println("          Opcion 7 Salir.");
            try {
                    System.out.print("Introduzca su opcion[1-6][7 Salir]: ");

                    int opt = Integer.parseInt(dato.readLine());
                    

                    switch (opt) {

                        case 1:
                            addCuerpo();
                            break;

                        case 2:
                            listarCuerpos();
                            break;

                        case 3:
                            busqueda = "Codigo";
                            buscarCuerpo(busqueda);
                            break;

                        case 4:
                             busqueda = "otro";
                            buscarCuerpo(busqueda);                      
                            break;

                        case 5:
                            eliminarCuerpo();
                            break;

                        case 6:
                            eliminarFichero();
                            break;

                        case 7:
                            System.out.println("Adios");
                            enabledmostrarMenu = false;
                            
                            break;

                        default:
                            System.err.println("Opcion invalida");

                    }
               

            } catch (InputMismatchException err) {
                System.err.println("Dato invalido");
                
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
       }

    }
    
    
    public static void addCuerpo(){
        
     // Aqui aamaceno en el bufer lo que introducimos por teclado a traves de la clase InputStreamReader y BufferedReader
        
        try{
        
        System.out.println("Añade el codigo");
        
        int codigoCuerpo = Integer.parseInt(dato.readLine());
        
        System.out.println("Añade el nombre");
        
        String nombre = dato.readLine();
          
        System.out.println("Añade el tipo");
        
        String tipoCuerpo = dato.readLine();
        
        System.out.println("Añade el diametro");
          
       int diametro = Integer.parseInt(dato.readLine());
        
        // Si no se ha construido el arraylist lo inicializamos
       
       
            if(cuerposCelestes.isEmpty()){
                    cuerposCelestes = new ArrayList<CuerpoCeleste>();
                }
            
            
        // vuelvo los datos que previamente tenia en el buffer en el arraylist
        cuerposCelestes.add(new CuerpoCeleste(codigoCuerpo,nombre,tipoCuerpo,diametro));
               
        // llamo al metodo que vuelca el arralist en el fichero
                escribirArchivo();
        
                
        // mensajes informativos sin mas
                System.out.println("Cuerpo celeste "+cuerposCelestes.size()+" añadido");
         
        
        // agarra la excepcion en caso de necesitarse
        
        }catch(Exception err){
            System.err.println(err.getMessage());
        }
    }
    
    
    
    //Metodo que genera el fichero para volcar los arraylist
    
     public static void escribirArchivo(){
         
        
         
         try{
            //Crea un archivo .dat en caso de que no exista
            if(!f.exists()) {
             crearArchivo();
            }
            
        
            if(f.canWrite()){ //Comprobamos si tenemos privilegios 
                
                    // creamos un archivo de salida de flujo para escribir el fichero f 
                //abrimos un hilo de flujo 
            FileOutputStream fos = new FileOutputStream(f);
            
            
            // creamos un objeto de salida de flujo para el archivo generado mas arriba
                //abrimos un hilo de flujo
                
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            
            // Serializamos de esta manera el arraylist 
            oos.writeObject(cuerposCelestes);
            oos.close(); //cerramos el flujo de salida de objeto
            fos.close(); //cerramos el flujo de salida del fichero
            }else{
                System.out.println("No hay privilegios de lectura");
            }
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

     
       private static void listarCuerpos() {
                  
       
           // comprobamos si el fichero existe y si tiene algun dato
        if(!f.exists()){
            System.err.println("NO EXISTEN DATOS");
        } else{
            
            
            //llamamos al metodo abrir
            abrir();
            
            if(cuerposCelestes != null){
                int con=1;
                for(CuerpoCeleste p:cuerposCelestes){ //Iteramos los datos del arraylist y los mostramos por pantalla
                    System.out.println("Registro nº "+con+" - "+p.toString());
                    con++;
                }
            }else{
                System.out.println("No existen cuerpos dados de alta.");
            }
        }
    }
     
      private static void abrir(){
          
        try{
            // comprobamos si el fichero existe y si tiene algun dato
            if(!f.exists()){
                crearArchivo();
            }else{
                if(f.canRead()){ // Comprobamos si ademas podemos leerlo
                    
                    // Recogemos el flujo de entrada del fichero
                    // creamos un hilo
                    FileInputStream fis = new FileInputStream(f);
                    
                    // Recogemos el flujo de entrada del fichero para crear un objeto de entrada de flujo
                    // creamos un hilo
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    
                    // Deserializamos el objeto del fichero y lo escribimos en el arraylist
                    cuerposCelestes = (ArrayList<CuerpoCeleste>)ois.readObject();
                    ois.close(); //cerramos hilo x2
                    fis.close();
                }else{
                    System.err.println("FICHERO VACÍO");
                }
            }
        }catch(IOException | ClassNotFoundException ex){
            System.err.println("Error: "+ex.getMessage());
        }
    }
      
      private static void crearArchivo(){
          
          File f=new File("cuerposCelestes.dat");
        try{
            f.createNewFile();
            System.out.println("Fichero creado");
        }catch(Exception ex){
            System.err.println("ERROR: "+ex.getMessage());
        }
    }
      
      
      
      private static void eliminarCuerpo() {
        try{
            
            System.out.println("Introduzca el codigo de cuerpo que desea eliminar");
            
            int ni= Integer.parseInt(dato.readLine());
            abrir();
            int i=0;
            boolean encontrado=false;
            //Iteramos el array y si existe lo borramos
            for(CuerpoCeleste p:cuerposCelestes){
                if(p.getCodigoCuerpo() == (ni))
                {
                    encontrado=true;
                    System.out.println("Registro nº"+i+" - "+p.toString());
                    System.out.println("Está seguro que desea eliminarlo (S/N)");
                    String res;
                    do{
                        res=dato.readLine().toUpperCase();
                        
                        if(!res.equals("N")&&!res.equals("S")) {
                            System.err.println("Sólo 'S' para borrar y 'N' para mantenerlo");
                        }
                        if(res.equals("S")){
                            //borramos el registro
                            cuerposCelestes.remove(i);
                            //Volvemos a serializar el objecto y los escribimos
                            escribirArchivo();
                            System.out.printf("REGISTRO ELIMINADO\n",i);
                        }
                    }while(!res.equals("S") && !res.equals("N"));
                }
                i++;
            }
            if(!encontrado) {
                System.err.println("REGISTRO NO ENCONTRADO");
            }
        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }
      
      //Buscamos y ademas le pasamos un parametro
        private static void buscarCuerpo(String busqueda){
        try{
            String res;
            boolean repetir, encontrado;
            
            
            do{
                repetir=false;
                //Si pasamamos parametro buscamos por codigo sino por tipo
                if (busqueda == "Codigo"){
                System.out.println("Introduzca el codigo del planeta que desea buscar");
         
                
                int ni=Integer.parseInt(dato.readLine());
                
                abrir();
                
                int i=1;
                encontrado=false;
                
                //Iteramos el array 
                for(CuerpoCeleste p:cuerposCelestes){
                    // Si el codigo del objeto se corresponde con la busqueda muestra el resultado
                    if(p.getCodigoCuerpo() == ni){
                        encontrado=true;
                        System.out.println("nº"+i+" - "+p.toString());
                    }
                    i++;
                }
                
                }else{
                    // mas de lo mismo pero se discrimina por el tipo
                        System.out.println("Introduzca el tipo del planeta que deseas buscar");

                        String tipoCuerpo=dato.readLine();
                        abrir();//abrir() carga en el ArryList personas, todos los clientes dle fichero.

                        int i=1;
                        encontrado=false;
                        
                      
                        for(CuerpoCeleste p:cuerposCelestes){
                            if( p.getTipoCuerpo().equals(tipoCuerpo) ){
                                encontrado=true;
                                System.out.println("nº"+i+" - "+p.toString());
                            }
                            i++;
                    }
                }
                
                if(!encontrado){
                    
                   
                    System.err.println("REGISTRO NO ENCONTRADO");
                System.out.println("Desea buscar otro registro (S/N)");
                }
                do{
                    
                    res=dato.readLine().toUpperCase();
                    System.out.println("Desea buscar otro registro (S/N)");
                    
                    if(!res.equals("N")&&!res.equals("S")){
                        System.err.println("Sólo 'S' para buscar otro y 'N' para salir");
                    }
                    
                    if(res.equals("S")){
                        repetir=true;
                    }
                    
                }while(!res.equals("S") && !res.equals("N"));
            }while(repetir);
            
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        
    }
      
      
      
      
      
      
      
      
      
         public static void eliminarFichero() {
              
    //Codiciones para que esto funcione bien
         String res;
         boolean borrado=false;
         try{
            System.out.println("Está seguro que desea eliminar el fichero (S/N)");
            do{
                res=dato.readLine().toUpperCase();
                if(!res.equals("N")&&!res.equals("S")) {
                    System.err.println("Sólo 'S' para borrar y 'N' para mantenerlo");
                }
                if(res.equals("S")){
                    
                    //Borramos el fichero
                    borrado=f.delete();
                    
                    if(borrado){
                        System.out.println("FICHERO DE DATOS ELIMINADO");
                        cuerposCelestes.clear();
                    }else{
                        System.err.println("No ha sido posible eliminar el fichero");
                    }
                }
            }while(!res.equals("S") && !res.equals("N"));
         }catch(FileNotFoundException fnf){
             System.err.println("Fichero inexistente: "+fnf.getMessage());
         }catch(Exception ex){
             ex.getMessage();
         }
    }
     
     
     
    //Creamos un metodo privado estatico para invocar el menu al cual requiere de un objeto
   
}
        
    


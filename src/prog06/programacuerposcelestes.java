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
private static List<CuerpoCeleste> cuerposCelestes = new ArrayList<>();


    
    public static void main(String[] args) {     
        
   
       
    CuerpoCeleste cceleste = new CuerpoCeleste();

//  Mostramos menu      
        mostrarMenu(cceleste);
       
    

    } 
    
    


    
    
     private static void mostrarMenu(CuerpoCeleste obj) {

        

        boolean enabledmostrarMenu = true;
        boolean enabledmostrarMenu2 = true;
        Scanner sca = new Scanner(System.in);
        String busqueda = null;
       
        do {
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

                while (enabledmostrarMenu == true) {

                  

                    System.out.print("Introduzca su opcion[1-6][7 Salir]: ");

                    int opt;
                    opt = sca.nextInt();

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
                            enabledmostrarMenu2 = false;
                            break;

                        default:
                            System.err.println("Opcion invalida");

                    }
                }

            } catch (InputMismatchException err) {
                System.err.println("Dato invalido");
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        } while (enabledmostrarMenu2);

    }
    
    
    public static void addCuerpo(){
        
     
        
        try{
        
        System.out.println("Añade el codigo");
        
        int codigoCuerpo = Integer.parseInt(dato.readLine());
        
        System.out.println("Añade el nombre");
        
        String nombre = dato.readLine();
          
        System.out.println("Añade el tipo");
        
        String tipoCuerpo = dato.readLine();
        
        System.out.println("Añade el diametro");
          
       int diametro = Integer.parseInt(dato.readLine());
        
        
            if(cuerposCelestes.isEmpty()){
                    cuerposCelestes = new ArrayList<CuerpoCeleste>();
                }
            
            
            
                cuerposCelestes.add(new CuerpoCeleste(codigoCuerpo,nombre,tipoCuerpo,diametro));
               
            
                escribirArchivo();
            
                System.out.println("Cliente "+cuerposCelestes.size()+" añadido");
            
        }catch(Exception err){
            System.err.println(err.getMessage());
        }
    }
    
    
    
     public static void escribirArchivo(){
        try{
            
            File f=new File("cuerposCelestes.dat");
            
            if(!f.exists()) {
              f = new File("cuerposCelestes.dat");
            }
            FileOutputStream fos = new FileOutputStream(f);
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(cuerposCelestes);
            oos.close();
            fos.close();
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

     
       private static void listarCuerpos() {
           
       File f=new File("cuerposCelestes.dat");
       
       
        if(!f.exists()){
            System.err.println("NO EXISTEN DATOS");
        } else{
            abrir();//abrir() carga en el ArryList personas, todos los clientes dle fichero.
            if(cuerposCelestes != null){
                int con=1;
                for(CuerpoCeleste p:cuerposCelestes){
                    System.out.println("Registro nº "+con+" - "+p.toString());
                    con++;
                }
            }else{
                System.out.println("No existen clientes dados de alta.");
            }
        }
    }
     
      private static void abrir(){
          
          File f=new File("cuerposCelestes.dat");
        try{
            
            if(!f.exists()){
                crearArchivo();
            }else{
                if(f.canRead()){
                    FileInputStream fis = new FileInputStream(f);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    cuerposCelestes = (ArrayList<CuerpoCeleste>)ois.readObject();
                    ois.close();
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
            System.out.println("Introduzca el codigo de planeta que desea eliminar");
            
            int ni= Integer.parseInt(dato.readLine());
            abrir();
            int i=0;
            boolean encontrado=false;
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
                            cuerposCelestes.remove(i);//se elimina del array y posteriormente se guarda en el fichero ya sin ese cliente.
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
      
      
        private static void buscarCuerpo(String busqueda){
        try{
            String res;
            boolean repetir, encontrado;
            
            
            do{
                repetir=false;
                if (busqueda == "Codigo"){
                System.out.println("Introduzca el codigo del planeta que desea buscar");
         
                
                int ni=Integer.parseInt(dato.readLine());
                
                abrir();//abrir() carga en el ArryList personas, todos los clientes dle fichero.
                
                int i=0;
                encontrado=false;
                
                for(CuerpoCeleste p:cuerposCelestes){
                    if(p.getCodigoCuerpo() == ni){
                        encontrado=true;
                        System.out.println("Registro nº"+i+" - "+p.toString());
                    }
                    i++;
                }
                
                }else{
                        System.out.println("Introduzca el tipo del planeta que deseas buscar");

                        String tipoCuerpo=dato.readLine();
                        abrir();//abrir() carga en el ArryList personas, todos los clientes dle fichero.

                        int i=0;
                        encontrado=false;
                        
                      
                        for(CuerpoCeleste p:cuerposCelestes){
                            if( p.getTipoCuerpo().equals(tipoCuerpo) ){
                                encontrado=true;
                                System.out.println("Registro nº"+i+" - "+p.toString());
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
              
         File f=new File("cuerposCelestes.dat");
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
        
    


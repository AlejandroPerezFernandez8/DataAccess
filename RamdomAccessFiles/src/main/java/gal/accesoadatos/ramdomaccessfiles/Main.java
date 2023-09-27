/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.accesoadatos.ramdomaccessfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alejandro.perezferna
 */
public class Main {
    static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        try{
            crearficheroRAF(new File("./src/main/resources/ficheroRAF.dat")); //CREACION DEL RAF         
                Menu(); //MAIN MENU
        }catch (FileNotFoundException FNFE){
            System.out.println("Error en la creación del fichero");
        } catch (IOException ex) {
            System.out.println("Error IO");
        }
    }

    private static void crearficheroRAF(File file) throws FileNotFoundException, IOException  {
        String[] nombres = {"alejandro","ralphy","laura","samuel","gabriel"};
        int[]    edades  = {10,20,30,40,50};
        double[] salario = {1000.0,1000.0,1000.0,1000.0,1220.0};
        StringBuffer buffer;
        
        if (file.exists()){file.delete();} //SI EL FICHERO EXISTE LO BORRA
        
        try(
                RandomAccessFile raf = new RandomAccessFile(file,"rw")
                
           ){
            for (int i = 0; i < nombres.length; i++) {
                //Identificador
                raf.writeInt(i+1); 
                //Nombre
                buffer = new StringBuffer(nombres[i]);
                buffer.setLength(10);
                raf.writeChars(buffer.toString());
                //edad
                raf.writeInt(edades[i]);
                //salario
                raf.writeDouble(salario[i]);
            }
        } 
    }

    
    private static void leerFicheroRAF(File file) throws FileNotFoundException, IOException {
        int id,edad;
        double salario;
        char nombre[] = new char[10];
        System.out.println("-------------------REGISTROS--------------------------");
        try (
            RandomAccessFile raf = new RandomAccessFile(file, "r")
            ){
            //TAMAÑO REGISTRO 36
            //POSICIONARNOS FORMULA (IDENTFICADOR -1)* TAMAÑO DE REGISTRO
            //TAMAÑO DEL FICHERO raft.lenght
            //numero de registros raft.lenght / tamaño registro
            
            raf.seek(0);//SE POSICIONA EN LA POSICION 0

            //Mientras el puntero no llegue al final del fichero
            while (raf.getFilePointer() != raf.length()) {                
                //Empiezo a leer
                id = raf.readInt();
                
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = raf.readChar();
                }
                
                String nombreFinal = new String(nombre);
                edad = raf.readInt();
                salario = raf.readDouble();
                
                System.out.println(id+" " + nombreFinal+ " " + edad + " " + salario);
                System.out.println("-----------------------------------------------------");
            }
        } 
    }

    
    private static void leerRegistroRAF(File file,int id) throws FileNotFoundException, IOException{
        char[] nombre = new char[10];
        int edad,identificador;
        double salario;
        boolean salir = false;
        int tamaño_registro = 36;
        
        //SI el ID = -1 se le pide al usuario el id
        //Si el ID != -1 busca directamente por el id pasado como parametro
        
        
        try (
                RandomAccessFile raf = new RandomAccessFile(file,"r")
            ){
            int condicional_id = id;
            
            //SABER EL ID MAXIMO
            raf.seek(raf.length() - 36);
            int id_maximo =raf.readInt();
            
            
            if (id == -1){
                //PEDIR EL ID
                 do {            
                    try {
                        System.out.println("Por favor introduce la ID que quieres leer");
                        id = Integer.parseInt(teclado.nextLine());
                        if (id > id_maximo){
                            System.out.println("Ese registro no existe");
                        }else{salir =true;}
                    } catch (NumberFormatException NFE) {
                        System.out.println("Debes introducir un numero entero");
                    }
                } while (salir == false);
            }
            //LEER EL REGISTRO
            if(condicional_id == -1){System.out.println("-----------------------REGISTRO---------------------");}
            
            raf.seek( (id-1)*tamaño_registro);
            identificador = raf.readInt();
            for (int i = 0; i < nombre.length; i++) {
                nombre[i] = raf.readChar();
            }
            String nombreFinal = new String(nombre);
            edad = raf.readInt();
            salario = raf.readDouble();
            
            System.out.println(identificador + " " + nombreFinal + " " + edad + " " + salario );
            
            if(condicional_id == -1){System.out.println("----------------------------------------------------");}
        } 
    }
    
    
    private static void modificarRegistro(File file) throws FileNotFoundException{
        int id = 0;
        double salario; 
        boolean salir = false;
        int tamaño_registro = 36;
        String nombre_nuevo;
        StringBuffer buffer;
        
        
           try (
                RandomAccessFile raf = new RandomAccessFile(file,"rw")
            ){
            
            //SABER EL ID MAXIMO
            raf.seek(raf.length() - 36);
            int id_maximo =raf.readInt();
            
            //PEDIR EL ID
             do {            
                try {
                    System.out.println("Por favor introduce la ID que quieres modificar");
                    id = Integer.parseInt(teclado.nextLine());
                    if (id > id_maximo){
                        System.out.println("Ese registro no existe");
                    }else{salir =true;}
                } catch (NumberFormatException NFE) {
                    System.out.println("Debes introducir un numero entero");
                }
            } while (salir == false);
             
            
            //PEDIR TODOS LOS NUEVOS DATOS DEL REGISTRO
            System.out.println("----INTRODUZCA LOS NUEVOS DATOS PARA MODIFICAR EL REGISTRO----");
            System.out.println("Nombre:");
            nombre_nuevo = teclado.nextLine();
            System.out.println("Salario:");
            salario = Double.parseDouble(teclado.nextLine());
            
            //MOSTRAR REGISTRO ANTES DE MODFICAR
            System.out.println("-----------------------REGISTRO ANTES DE MODIFICAR---------------------");
            leerRegistroRAF(new File("./src/main/resources/ficheroRAF.dat"),id);
            System.out.println("----------------------------------------------------------------------");
            
            //ESCRIBIR LOS NUEVOS DATOS
            raf.seek( ((id-1)*tamaño_registro)+4 );
            //Nombre
            buffer = new StringBuffer(nombre_nuevo);
            buffer.setLength(10);
            raf.writeChars(buffer.toString());
            //SALTAMOS LA EDAD
            raf.skipBytes(4);
            //SALARIO
            raf.writeDouble(salario);
            
            //REGISTRO DESPUES DE MODIFICAR
            System.out.println("-----------------------REGISTRO DESPUES DE MODIFICAR---------------------");
            leerRegistroRAF(new File("./src/main/resources/ficheroRAF.dat"),id);
            System.out.println("----------------------------------------------------------------------");
            
           } catch (IOException ex) {
               System.out.println("IO EXCEPTION");
        }
    }
    
    
    private static void InsertarRegistro(File file) throws FileNotFoundException, IOException{
        String nombre;
        int edad,id_maximo;
        double salario;
        StringBuffer buffer;
        
        try (
                RandomAccessFile raf = new RandomAccessFile(file, "rw")
            ){
            //Pedir datos
            System.out.println("Escriba el nombre");
            nombre = teclado.nextLine();
            System.out.println("Escriba la edad");
            edad = Integer.parseInt(teclado.nextLine());
            System.out.println("Escriba el salario");
            salario = Double.parseDouble(teclado.nextLine());
            
            //Escribir datos
                //Vamos a leer cual es el ultimo id para poder sumarle 1 para el siguiente registro
            id_maximo = (int) (raf.length() / 36);
            
            raf.seek(raf.length());//nos vamos a la ultima posicion para empezar a escribir
            
            raf.writeInt(id_maximo + 1); //ID
            //Nombre
            buffer = new StringBuffer(nombre);   
            buffer.setLength(10);
            raf.writeChars(buffer.toString());
            raf.writeInt(edad);//EDAD
            raf.writeDouble(salario);//SALARIO

        }catch(NumberFormatException nfe){
            System.out.println("Error de formateo numerico;");
        }
    }
    
 
    private static void BorrarRegistro(File file, File tmp) throws FileNotFoundException, IOException{
        int registro_borrar,id;
        char [] nombre = new char[10];
        StringBuffer buffer;
        
        if (tmp.exists()){tmp.delete();}
        
        try (
             RandomAccessFile raf = new RandomAccessFile(file, "rw");
             RandomAccessFile raf_temp = new RandomAccessFile(tmp,"rw")
            ){
           
            do {
            System.out.println("¿Que registro desesa borrar?");
            registro_borrar = Integer.parseInt(teclado.nextLine());
            }while(registro_borrar > (raf.length() / 36) || registro_borrar < 1);
            
            
            raf.seek(0);
            //VAMOS A LEER EL RAF HASTA EL ID INDICADO
            while (raf.getFilePointer() != raf.length()){
                
               
                
                id = raf.readInt(); //Leemos el id donde estamos
                
                if (id > registro_borrar){
                    //SI EL ID ES MAYOR QUE EL ID A BORRAR SE COPIARÁ TODO PERO AL ID LE RESTAREMOS 1
                    id--;
                }else if (id == registro_borrar){
                    raf.skipBytes(32);
                    continue;
                }
                //ESCRIBIMOS
                raf_temp.writeInt(id); //ID
                
                for (int i = 0; i < nombre.length; i++) { //NOMBRE
                    nombre[i]=raf.readChar();
                }
                buffer =  new StringBuffer(new String(nombre));
                buffer.setLength(10);
                raf_temp.writeChars(buffer.toString());
                
                raf_temp.writeInt(raf.readInt());//EDAD
                raf_temp.writeDouble(raf.readDouble());//SALARIO
            }
            
            leerFicheroRAF(new File("./src/main/resources/rafTMP.dat"));
            
        } catch (NumberFormatException nfe) {
            System.out.println("Error de formato numerico");
        }
        
        file.delete();
        tmp.renameTo(new File("./src/main/resources/ficheroRAF.dat"));   
    }
    
    
    private  static void Menu(){
        boolean salir = false;
        int eleccion;
            do {      
                try {
                    System.out.println("================MENU=================");
                    System.out.println("[1]Mostrar todos los registros");
                    System.out.println("[2]Mostrar registro por ID");
                    System.out.println("[3]Modificar registro");
                    System.out.println("[4]Borrar registro");
                    System.out.println("[5]Insertar Registro");
                    System.out.println("[6]Salir");

                    eleccion = Integer.parseInt(teclado.nextLine());

                    switch (eleccion) {
                        case 1 -> {leerFicheroRAF(new File("./src/main/resources/ficheroRAF.dat"));}
                        case 2 ->{leerRegistroRAF(new File("./src/main/resources/ficheroRAF.dat"),-1);}
                        case 3-> {modificarRegistro(new File("./src/main/resources/ficheroRAF.dat"));}
                        case 4 ->{BorrarRegistro(new File("./src/main/resources/ficheroRAF.dat"),new File("./src/main/resources/rafTMP.dat"));}
                        case 5 ->{InsertarRegistro(new File("./src/main/resources/ficheroRAF.dat"));}
                        case 6 ->{salir = true;}
                    }   
                } catch(NumberFormatException NFE){
                     System.out.println("ERROR se deben introducir numeros");
                }catch(InputMismatchException IME){
                     System.out.println("ERROR debes introducir los datos que se piden");
                } catch (IOException ex) {
                    System.out.println("IO EXCEPTION");
            }
            }while (salir == false);
                 
                
                
                
                
    }

    
}


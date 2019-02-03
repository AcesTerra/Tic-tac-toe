package supercat;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Alfredo Emmanuel Garcia Falcon and Sarahí Rodríguez
 */
public class SuperCat {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    static String[] currentTable = new String[9];
    
    public static void main(String[] args) {
        
        tree ticTacToe = new tree();
        node root = ticTacToe.addRoot();
        ticTacToe.generateSons(root);
        
        play(root);
    }
    
    //Method that prints father's sons
    public static void printSons(node father){
        //If father has sons
        if(!father.sons.isEmpty()){
            //Print all sons
            for(int i=0;i<father.sons.size();i++){
                //Print its mm
                System.out.println(i+" "+father.sons.get(i).mm);
                //Prints son table
                System.out.println(father.sons.get(i));
            }
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.println("¿Cuál es tu proximo movimiento?");
            int j=sc.nextInt();
            //It calls function sending selected son as paremeter
            printSons(father.sons.get(j));
        }
        else{
            if(father.mm==1)System.out.println("Ganó 'x'");
            else if (father.mm==-1)System.out.println("Ganó 'o'");
            else System.out.println("Fue un empate");
        }  
    }
    
    public static void play(node father)
    {
        System.out.println("Player 1:\n1 - User\n2 - Computer");
        int player1 = sc.nextInt();
        System.out.println("Player 2:\n1 - User\n2 - Computer");
        int player2 = sc.nextInt();
        if(player1 != player2)
        {
            if(player1 == 2)
            {
                playerVsPC(father, "x");
            }
            if(player1 == 1)
            {
                playerVsPC(father, "o");
            }
        }else if(player1==1 && player2==1){
            playerVsPlayer(father);
        }else {
            pcVsPC(father);
        }
    }
    
    public static void playerVsPlayer(node father){
        if(!father.sons.isEmpty()){
            
            System.out.println(father);
            System.out.println("Ingresa la posición donde quieres poner");
            int j=sc.nextInt();
            //int p = position(j);
            //currentTable[j] = "f";
            //It calls function sending selected son as paremeter
            playerVsPlayer(father.sons.get(j));
        }
        else{
            System.out.println(father);
            if(father.mm==1)System.out.println("Ganó 'x'");
            else if (father.mm==-1)System.out.println("Ganó 'o'");
            else System.out.println("Fue un empate");
        }  
    }
    
    public static void playerVsPC(node father, String computer)
    {
        if(computer.equals("x"))
        {
            int rd = (int)(Math.random() * (father.sons.size()));
            System.out.println(father.sons.get(rd));
            gameplay(father.sons.get(rd),1);
        }
        if(computer.equals("o"))
        {
            System.out.println(father);
            System.out.println("Ingresa la posición donde quieres poner x");
            int first = sc.nextInt();
            System.out.println(father.sons.get(first));
            gameplay(father.sons.get(first),2);
        }
    }
    
    public static void gameplay(node father, int nextPlayer)
    {   
        int mm;

        if(nextPlayer==1)
        {
            mm=1;
            System.out.println("Ingresa donde quieres poner tu o");
            int n = sc.nextInt();
            System.out.println(father.sons.get(n));
            if(father.sons.get(n).sons.isEmpty())
            {
               // System.out.println("Terminado");
                if(father.sons.get(n).mm==1)System.out.println("Ganó 'x'");
                else if (father.sons.get(n).mm==-1)System.out.println("Ganó 'o'");
                else System.out.println("Fue un empate");
            }
            else{
                ArrayList<Integer> aux = new ArrayList();
                int cont=0;
                for (node currentSon : father.sons.get(n).sons) {
                    if(currentSon.mm==mm){
                        aux.add(cont);
                    }cont++;
                }
                if(aux.isEmpty()){
                   cont=0;
                   for (node currentSon : father.sons.get(n).sons) {
                        if(currentSon.mm==0){
                            aux.add(cont);
                        }cont++;
                    } 
                }
                if(aux.isEmpty()){
                    cont=0;
                    for (node currentSon : father.sons) {
                        aux.add(cont);
                        cont++;
                    }
                }
                int ns = (int)(Math.random() * (aux.size()));
                //father.sons.get(n).sons.get(aux.get(ns));
                System.out.println(father.sons.get(n).sons.get(aux.get(ns)));
                if(!father.sons.get(n).sons.get(aux.get(ns)).sons.isEmpty())
                {
                    gameplay(father.sons.get(n).sons.get(aux.get(ns)), 1);
                }
                else{
                    //System.out.println("Terminado");
                    if(father.sons.get(n).mm==1)System.out.println("Ganó 'x'");
                    else if (father.sons.get(n).mm==-1)System.out.println("Ganó 'o'");
                    else System.out.println("Fue un empate");
                }
            }
        }
        else
        {
            mm=-1;
            ArrayList <Integer> aux = new ArrayList();
            int cont=0;
            for (node currentSon : father.sons) {
                if(currentSon.mm==mm){
                    aux.add(cont);
                }cont++;
            }
            if(aux.isEmpty()){
               cont=0;
               for (node currentSon : father.sons) {
                    if(currentSon.mm==0){
                        aux.add(cont);
                    }cont++;
                } 
            }
            if(aux.isEmpty()){
                cont=0;
                for (node currentSon : father.sons) {
                    aux.add(cont);
                    cont++;
                }
            }
            int ns = (int)(Math.random() * (aux.size()));
            System.out.println(father.sons.get(aux.get(ns)));
            if(!father.sons.get(aux.get(ns)).sons.isEmpty())
            {
                System.out.println("Ingresa donde quieres poner tu x");
                int n = sc.nextInt();
                System.out.println(father.sons.get(aux.get(ns)).sons.get(n));
                if(!father.sons.get(aux.get(ns)).sons.get(n).sons.isEmpty()){
                    gameplay(father.sons.get(aux.get(ns)).sons.get(n), 2);
                }else{
                    System.out.println("Terminado");
                    if(father.sons.get(aux.get(ns)).mm==1)System.out.println("Ganó 'x'");
                    else if (father.sons.get(aux.get(ns)).mm==-1)System.out.println("Ganó 'o'");
                    else System.out.println("Fue un empate");
                }
            }
            else{
                System.out.println("Terminado");
                if(father.sons.get(aux.get(ns)).mm==1)System.out.println("Ganó 'x'");
                else if (father.sons.get(aux.get(ns)).mm==-1)System.out.println("Ganó 'o'");
                else System.out.println("Fue un empate");
            }
        }
    }
    
    public static void pcVsPC(node father)
    {
        int rd = (int)(Math.random() * (father.sons.size()));
        System.out.println(father.sons.get(rd));
        gameplay2(father.sons.get(rd));
    }
    
  public static void gameplay2(node father){
        int mm;

        mm = -1;
        ArrayList<Integer> array = new ArrayList();
        int cont = 0;
        for (node currentSon : father.sons) {
            if (currentSon.mm == mm) {
                array.add(cont);
            }
            cont++;
        }
        if (array.isEmpty()) {
            cont = 0;
            for (node currentSon : father.sons) {
                if (currentSon.mm == 0) {
                    array.add(cont);
                }
                cont++;
            }
        }
        if (array.isEmpty()) {
            cont = 0;
            for (node currentSon : father.sons) {
                array.add(cont);
                cont++;
            }
        }
        int n = (int) (Math.random() * (array.size()));
        System.out.println(father.sons.get(array.get(n)));

        mm = 1;
        if (father.sons.get(array.get(n)).sons.isEmpty()) {
            if (father.sons.get(array.get(n)).mm == 1) {
                System.out.println("Ganó 'x'");
            } else if (father.sons.get(array.get(n)).mm == -1) {
                System.out.println("Ganó 'o'");
            } else {
                System.out.println("Fue un empate");
            }
        } else {
            ArrayList<Integer> aux = new ArrayList();
            cont = 0;
            for (node currentSon : father.sons.get(array.get(n)).sons) {
                if (currentSon.mm == mm) {
                    aux.add(cont);
                }
                cont++;
            }
            if (aux.isEmpty()) {
                cont = 0;
                for (node currentSon : father.sons.get(array.get(n)).sons) {
                    if (currentSon.mm == 0) {
                        aux.add(cont);
                    }
                    cont++;
                }
            }
            if (aux.isEmpty()) {
                cont = 0;
                for (node currentSon : father.sons) {
                    aux.add(cont);
                    cont++;
                }
            }
            int ns = (int) (Math.random() * (aux.size()));
            //father.sons.get(n).sons.get(aux.get(ns));
            System.out.println(father.sons.get(array.get(n)).sons.get(aux.get(ns)));
            if (!father.sons.get(array.get(n)).sons.get(aux.get(ns)).sons.isEmpty()) {
                gameplay2(father.sons.get(array.get(n)).sons.get(aux.get(ns)));
            } else {
                //System.out.println("Terminado");
                if (father.sons.get(array.get(n)).mm == 1) {
                    System.out.println("Ganó 'x'");
                } else if (father.sons.get(array.get(n)).mm == -1) {
                    System.out.println("Ganó 'o'");
                } else {
                    System.out.println("Fue un empate");
                }
            }
        }
    }
  
    public static int position(int pos)
    {
        int box = 0;
        for(int i = 0; i < currentTable.length; i++)
        {
            if(currentTable[i] == null)
            {
                box++;
            }
            if(i == pos)
                return box-1;
        }
        return 0;
    }
}

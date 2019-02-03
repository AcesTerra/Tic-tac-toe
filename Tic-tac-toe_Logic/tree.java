package supercat;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alfredo Emmanuel Garcia Falcon and Sarahí Rodríguez
 */
public class tree {
    node root;
    public int size=0;
   
    public tree() {
        this.root = null;
    }


    public node addRoot(){
        node newNode = new node();
        this.root = newNode;
        return newNode;
    }
    /*
    public node addNode(node padre, int valor){
        node nuevo = new node(valor);
        padre.hijos.add(nuevo);
        nuevo.padre = padre;
        return nuevo;
    }*/
    
    //Method to generate sons of node sent (root)
    public void generateSons(node actual)
    {   
        //Get level of actual root
        int level = getLevel(actual);
        //Get player depending on level you are
        String currentPlayer = getPlayer(level);

        //Cycle that go over 9 possible positions on table
        for (int i = 0; i < 9; i++) {
            //If the space in table is empty
            if (actual.table[i].equals(" ")) {
                //Generate a new son node, which is sent father's table and an "x" or "o" is added depending on position
                node newSon = new node(actual.table, i, currentPlayer);
                //Father is assigned to new node
                newSon.father = actual;
                //Father node adds the new node to its sons list
                actual.sons.add(newSon);
                //Increase size of nodes in the tree
                size++;
                //If none player has won
                if (!isItTheEnd(newSon, currentPlayer)) {
                    //Generate sons of new son
                    generateSons(newSon);
                }
                //Auxiliar variable to decide the mm value of actual
                int value = 0;
                //Cycle that go over all sons of actual node
                for (node aux : actual.sons) {
                    //If actual player is "x" the mm value wanted should be the greater
                    if (currentPlayer.equals("x")) {
                        if (value < aux.mm) {
                            //Greatest value is assigned to variable
                            value = aux.mm;
                        }
                    //If actual player is "o" the mm value wanted should be the smaller
                    } else if (currentPlayer.equals("o")) {
                        if (value > aux.mm) {
                            //Least value is assigned to variable
                            value = aux.mm;
                        }
                    }
                }
                //When the mm of every son has been checked, it assigns to actual node the mm value
                actual.mm = value;

            }
        }    
    }
    
    //Method that checks if there is no more son nodes
    //data -> It is the actual player (Needed for mm)
    public boolean isItTheEnd (node actual,String data){
        
        //SIf someone won it returns true
        if(win(actual,data)){
            return true;
        }
        //If table is full it returns true
        if(lleno(actual)){
            return true;
        }
        return false;
        
    }
    
    //Method that checks if table is full
    public boolean lleno(node actual){
        //Cycle that goes over all the table
        for(int i=0; i<9; i++){
            //If there is an empty space it returns false
            if(actual.table[i].equals(" ")){
                return false;
            }
        }
        //If don't, it means it is a tie
        actual.mm=0;
        return true;
    }
    
    //Method that checks if someone has won
    public boolean win(node actual, String data){
        //Auxiliar array
        String[] table = new String[9];
        //Copy table of actual node
        table = Arrays.copyOf(actual.table,9);
        int counter = 0;
        //Cycle that goes over all the table
        for(int i = 0; i <9; i++){
            if(i<3){
                //Revisa verticalmente las filas Ej: Si el dato en la posición 0, en la 3 y en la 6 son iguales.
                //It checks the columns. If data in position 0, 3 and 6 are equals
                //It would check 0,3,6 ; 1,4,7 ; 2,5,8
               if(table[i].equals(data) && table[i+3].equals(data) && table[i+6].equals(data)){
                   //If it is "x" its mm is 1
                   if(data.equals("x")){
                       actual.mm=1;
                   //Else is "o" and its mm is -1
                   }else{
                       actual.mm=-1;
                   }
                //It means that someone has won
                return true;
                }
            }
            //It checks if it has passed to another line of table
            if(i %3 == 0){
                //If it is true it start again the counter
                counter = 0;
            }
            //If data of table is equal to data it increase counter
            if (table[i].equals(data)) counter++;
            //It checks rows
            if(counter==3){
                //If it is "x" its mm is 1
                if(data.equals("x")){
                       actual.mm=1;
                //Else is "o" and its mm is -1
                }else{
                       actual.mm=-1;
                   }
                return true;
            }
        }
        //It checks if winner with diagonal
        if(table[2].equals(data) && table[4].equals(data) && table[6].equals(data)){
            if(data.equals("x")){
               actual.mm=1;
            }else{
               actual.mm=-1;
            }
            return true;
        }
        //It checks if winner with diagonal
        if(table[0].equals(data) && table[4].equals(data) && table[8].equals(data)){
            if(data.equals("x")){
                actual.mm=1;
           }else{
               actual.mm=-1;
           }
            return true;
        }
        return false;
    }
    
    public int getLevel(node actualNode)
    {
        if(actualNode.father == null)
            return 0;
        return 1 + getLevel(actualNode.father);
    }
    
    public String getPlayer(int level)
    {
        if(level%2 == 0)
        {
            return "x";
        }
        else
        {
            return "o";
        }
    }
    
}

package cat;

import java.util.ArrayList;

/**
 *
 * @author Alfredo Emmanuel Garcia Falcon and Sarahí Rodríguez
 */
public class node {
    public int mm;
    public node father;
    public ArrayList<node> sons = new ArrayList();
    public String[] table = {" "," "," "," "," "," "," "," "," "};

    public node(String fatherTable[], int position, String player) {
        this.mm = 0;
        this.father = null;
        this.table = fatherTable.clone();
        this.table[position] = player;
    }
    
    public node()
    {
        this.mm = 0;
        this.father = null;
    }
    
    @Override
    public String toString()
    {   

        String showTable = ((table[0]) + "|" + table[1] + "|" + table[2] + "\n" +
                            table[3] + "|" + table[4] + "|" + table[5] + "\n" +
                            table[6] + "|" + table[7] + "|" + table[8] + "\n");
        return showTable;
    }
}

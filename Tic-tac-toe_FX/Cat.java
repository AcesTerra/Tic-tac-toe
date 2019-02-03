package cat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alfredo Emmanuel Garcia Falcon and Sarahí Rodríguez
 */
public class Cat extends Application {
    
    boolean turnX = true; //Create turn
    String[] tableState = new String [9]; //Create the tile of game
    Tile table[][] = new Tile[3][3];
    int player1;
    int player2;
    int dificulty;
    static node currentNode = new node();
    ArrayList<Integer> registry = new ArrayList();
    boolean firstMove = true;
    
    private class Tile extends StackPane{
        private Text text = new Text();
        
                
        public Tile()
        {
            text.setFont(Font.font(72));
            Rectangle border = new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            getChildren().addAll(border, text);
            
            this.setOnMouseClicked(event ->{
                if(event.getButton()==MouseButton.PRIMARY)
                {
                    if(!turnX) return;
                    drawX(this);
                    turnX = false;
                    refreshState(this);
                }
                
                else if(event.getButton()==MouseButton.SECONDARY)
                {
                    if(turnX) return;
                    drawO(this);
                    turnX = true;
                    refreshState(this);
                }
            });
        }
        
        public void drawX(Tile trueTile)
        {
            trueTile.text.setText("x");
            int count = 0;
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    tableState[count] = table[i][j].text.getText();
                    count++;
                }
            }
        }

        public void drawO(Tile trueTile)
        {
            trueTile.text.setText("o");
            int count = 0;
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    tableState[count] = table[i][j].text.getText();
                    count++;
                }
            }
        }

        public void refreshState(Tile actual)
        {
            for(int i = 0; i < 9; i++)
                System.out.println("TableState " + i + ": " + tableState[i]);
            //System.out.println(player1);
            //System.out.println(player2);
            //System.out.println(dificulty);
            if(dificulty == 1) //If easy dificulty was selected
            {
                //System.out.println("Hard dificulty");
                if(player1 == 1 && player2 == 1)
                {
                    if(!currentNode.sons.isEmpty())
                    {
                        //System.out.println("Player vs. Player");
                        //if(table[0][0] == actual)
                        //    System.out.println("Este es el index");
                        int count = 0;
                        int indexEntered = 0;
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(table[i][j] == actual)
                                    indexEntered = count;
                                count++;
                            }
                        }
                        System.out.println(indexEntered);
                        int realPosition = position(indexEntered);
                        System.out.println(realPosition);
                        currentNode = currentNode.sons.get(realPosition);
                        System.out.println(currentNode);
                        if(currentNode.sons.isEmpty())
                        {
                            System.out.println(currentNode);
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                    }
                    /*else
                    {
                        System.out.println(currentNode);
                        if(currentNode.mm==1)System.out.println("Ganó 'x'");
                        else if (currentNode.mm==-1)System.out.println("Ganó 'o'");
                        else System.out.println("Fue un empate");
                    }*/
                }
                if(player1 == 1 && player2 == 2)
                {
                    //System.out.println("Player vs. PC");
                    if(!currentNode.sons.isEmpty())
                    {
                        int mm;
                        int count = 0;
                        int indexEntered = 0;
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(table[i][j] == actual)
                                    indexEntered = count;
                                count++;
                            }
                        }
                        System.out.println(indexEntered);
                        int realPosition = position(indexEntered);
                        System.out.println(realPosition);
                        currentNode = currentNode.sons.get(realPosition);
                        if(currentNode.sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        for(int i = 0; i < 9; i++)
                        {
                            if((currentNode.table[i].equals("x") || currentNode.table[i].equals("o")) && !registry.contains(i))
                            {    
                                registry.add(i);
                                //drawX(actual);
                                table[i/3][i%3].text.setText("x");
                            }
                        }
                        System.out.println(currentNode);
                        mm=-1;
                        ArrayList <Integer> aux = new ArrayList();
                        int cont=0;
                        for (node currentSon : currentNode.sons) {
                            if(currentSon.mm==mm || currentSon.mm!=mm){
                                aux.add(cont);
                            }cont++;
                        }
                        if(aux.isEmpty()){
                           cont=0;
                           for (node currentSon : currentNode.sons) {
                                if(currentSon.mm==0){
                                    aux.add(cont);
                                }cont++;
                            } 
                        }
                        if(aux.isEmpty()){
                            cont=0;
                            for (node currentSon : currentNode.sons) {
                                aux.add(cont);
                                cont++;
                            }
                        }
                        int ns = (int)(Math.random() * (aux.size()));
                        if(currentNode.sons.get(aux.get(ns)).sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        System.out.println(currentNode.sons.get(aux.get(ns)));
                        for(int i = 0; i < 9; i++)
                        {
                            if((currentNode.sons.get(aux.get(ns)).table[i].equals("x") || currentNode.sons.get(aux.get(ns)).table[i].equals("o")) && !registry.contains(i))
                            {    
                                registry.add(i);
                                //drawO(actual);
                                table[i/3][i%3].text.setText("o");
                                turnX = true;
                            }
                        }
                        if(currentNode.sons.get(aux.get(ns)).sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.sons.get(aux.get(ns)).mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.sons.get(aux.get(ns)).mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        else
                        {
                            currentNode = currentNode.sons.get(aux.get(ns));
                        }
                    }
                    else
                    {
                        System.out.println("Terminado");
                        if(currentNode.mm==1)
                        {
                            System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else if (currentNode.mm==-1)
                        {
                            System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else
                        {
                            System.out.println("Fue un empate");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Fue un empate");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        }
                    }
                }
                if(player1 == 2 && player2 == 1)
                {
                    //System.out.println("PC vs. Player");
                    if(!currentNode.sons.isEmpty())
                    {
                        int mm;
                        mm=1;
                        int count = 0;
                        int indexEntered = 0;
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(table[i][j] == actual)
                                    indexEntered = count;
                                count++;
                            }
                        }
                        System.out.println(indexEntered);
                        int realPosition = position(indexEntered);
                        System.out.println(realPosition);
                        //currentNode = currentNode.sons.get(realPosition);
                        System.out.println(currentNode.sons.get(realPosition));
                        if(currentNode.sons.get(realPosition).sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        else
                        {
                            for(int i = 0; i < 9; i++)
                            {
                                if((currentNode.sons.get(realPosition).table[i].equals("x") || currentNode.sons.get(realPosition).table[i].equals("o")) && !registry.contains(i))
                                {    
                                    registry.add(i);
                                    //drawX(actual);
                                    table[i/3][i%3].text.setText("o");
                                }
                            }
                            ArrayList<Integer> aux = new ArrayList();
                            int cont=0;
                            for (node currentSon : currentNode.sons.get(realPosition).sons) {
                                if(currentSon.mm==mm || currentSon.mm!=mm){
                                    aux.add(cont);
                                }cont++;
                            }
                            if(aux.isEmpty()){
                               cont=0;
                               for (node currentSon : currentNode.sons.get(realPosition).sons) {
                                    if(currentSon.mm==0){
                                        aux.add(cont);
                                    }cont++;
                                } 
                            }
                            if(aux.isEmpty()){
                                cont=0;
                                for (node currentSon : currentNode.sons) {
                                    aux.add(cont);
                                    cont++;
                                }
                            }
                            int ns = (int)(Math.random() * (aux.size()));
                            //father.sons.get(n).sons.get(aux.get(ns));
                            System.out.println(currentNode.sons.get(realPosition).sons.get(aux.get(ns)));
                            for(int i = 0; i < 9; i++)
                            {
                                if((currentNode.sons.get(realPosition).sons.get(aux.get(ns)).table[i].equals("x") || currentNode.sons.get(realPosition).sons.get(aux.get(ns)).table[i].equals("o")) && !registry.contains(i))
                                {    
                                    registry.add(i);
                                    //drawO(actual);
                                    table[i/3][i%3].text.setText("x");
                                    turnX = false;
                                }
                            }
                            if(!currentNode.sons.get(realPosition).sons.get(aux.get(ns)).sons.isEmpty())
                            {
                                //gameplay(father.sons.get(n).sons.get(aux.get(ns)), 1);
                                currentNode = currentNode.sons.get(realPosition).sons.get(aux.get(ns));
                            }
                            else{
                                //System.out.println("Terminado");
                                if(currentNode.sons.get(realPosition).mm==1)
                                {
                                    System.out.println("Ganó 'x'");
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Result");
                                    alert.setContentText("Ganó 'x'");
                                    //System.exit(0);
                                    alert.showAndWait();
                                    System.exit(0);
                                }
                                else if (currentNode.sons.get(realPosition).mm==-1)
                                {
                                    System.out.println("Ganó 'o'");
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Result");
                                    alert.setContentText("Ganó 'o'");
                                    //System.exit(0);
                                    alert.showAndWait();
                                    System.exit(0);
                                }
                                else
                                {
                                    System.out.println("Fue un empate");
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Result");
                                    alert.setContentText("Fue un empate");
                                    //System.exit(0);
                                    alert.showAndWait();
                                    System.exit(0);
                                }
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Terminado");
                        if(currentNode.mm==1)
                        {
                            System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else if (currentNode.mm==-1)
                        {
                            System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else
                        {
                            System.out.println("Fue un empate");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Fue un empate");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        }
                    }
                }
                if(player1 == 2 && player2 == 2)
                {
                    int mm;
                    mm = -1;
                    ArrayList<Integer> array = new ArrayList();
                    int cont = 0;
                    for (node currentSon : currentNode.sons) {
                        if (currentSon.mm == mm) {
                            array.add(cont);
                        }
                        cont++;
                    }
                    if (array.isEmpty()) {
                        cont = 0;
                        for (node currentSon : currentNode.sons) {
                            if (currentSon.mm == 0) {
                                array.add(cont);
                            }
                            cont++;
                        }
                    }
                    if (array.isEmpty()) {
                        cont = 0;
                        for (node currentSon : currentNode.sons) {
                            array.add(cont);
                            cont++;
                        }
                    }
                    int n = (int) (Math.random() * (array.size()));
                    System.out.println(currentNode.sons.get(array.get(n)));
                    for(int i = 0; i < 9; i++)
                    {
                        if((currentNode.sons.get(array.get(n)).table[i].equals("x") || currentNode.sons.get(array.get(n)).table[i].equals("o")) && !registry.contains(i))
                        {    
                            registry.add(i);
                            //drawX(actual);
                            table[i/3][i%3].text.setText("o");
                            turnX = false;
                        }
                    }

                    mm = 1;
                    if (currentNode.sons.get(array.get(n)).sons.isEmpty()) {
                        if (currentNode.sons.get(array.get(n)).mm == 1) {
                            System.out.println("Ganó 'x'");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Ganó 'x'");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        } else if (currentNode.sons.get(array.get(n)).mm == -1) {
                            System.out.println("Ganó 'o'");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Ganó 'o'");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        } else {
                            System.out.println("Fue un empate");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Fue un empate");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        }
                    } else {
                        ArrayList<Integer> aux = new ArrayList();
                        cont = 0;
                        for (node currentSon : currentNode.sons.get(array.get(n)).sons) {
                            if (currentSon.mm == mm) {
                                aux.add(cont);
                            }
                            cont++;
                        }
                        if (aux.isEmpty()) {
                            cont = 0;
                            for (node currentSon : currentNode.sons.get(array.get(n)).sons) {
                                if (currentSon.mm == 0) {
                                    aux.add(cont);
                                }
                                cont++;
                            }
                        }
                        if (aux.isEmpty()) {
                            cont = 0;
                            for (node currentSon : currentNode.sons) {
                                aux.add(cont);
                                cont++;
                            }
                        }
                        int ns = (int) (Math.random() * (aux.size()));
                        //father.sons.get(n).sons.get(aux.get(ns));
                        System.out.println(currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)));
                        for(int i = 0; i < 9; i++)
                        {
                            if((currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)).table[i].equals("x") || currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)).table[i].equals("o")) && !registry.contains(i))
                            {    
                                registry.add(i);
                                //drawX(actual);
                                table[i/3][i%3].text.setText("x");
                                turnX = false;
                            }
                        }
                        if (!currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)).sons.isEmpty()) {
                            currentNode = currentNode.sons.get(array.get(n)).sons.get(aux.get(ns));
                        } else {
                            //System.out.println("Terminado");
                            if (currentNode.sons.get(array.get(n)).mm == 1) {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            } else if (currentNode.sons.get(array.get(n)).mm == -1) {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            } else {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                    }
                    refreshState(actual);
                }
            }
            else //If hard dificulty was selected
            {
                //System.out.println("Hard dificulty");
                if(player1 == 1 && player2 == 1)
                {
                    if(!currentNode.sons.isEmpty())
                    {
                        //System.out.println("Player vs. Player");
                        //if(table[0][0] == actual)
                        //    System.out.println("Este es el index");
                        int count = 0;
                        int indexEntered = 0;
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(table[i][j] == actual)
                                    indexEntered = count;
                                count++;
                            }
                        }
                        System.out.println(indexEntered);
                        int realPosition = position(indexEntered);
                        System.out.println(realPosition);
                        currentNode = currentNode.sons.get(realPosition);
                        System.out.println(currentNode);
                        if(currentNode.sons.isEmpty())
                        {
                            System.out.println(currentNode);
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                    }
                    /*else
                    {
                        System.out.println(currentNode);
                        if(currentNode.mm==1)System.out.println("Ganó 'x'");
                        else if (currentNode.mm==-1)System.out.println("Ganó 'o'");
                        else System.out.println("Fue un empate");
                    }*/
                }
                if(player1 == 1 && player2 == 2)
                {
                    //System.out.println("Player vs. PC");
                    if(!currentNode.sons.isEmpty())
                    {
                        int mm;
                        int count = 0;
                        int indexEntered = 0;
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(table[i][j] == actual)
                                    indexEntered = count;
                                count++;
                            }
                        }
                        System.out.println(indexEntered);
                        int realPosition = position(indexEntered);
                        System.out.println(realPosition);
                        currentNode = currentNode.sons.get(realPosition);
                        if(currentNode.sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        for(int i = 0; i < 9; i++)
                        {
                            if((currentNode.table[i].equals("x") || currentNode.table[i].equals("o")) && !registry.contains(i))
                            {    
                                registry.add(i);
                                //drawX(actual);
                                table[i/3][i%3].text.setText("x");
                            }
                        }
                        System.out.println(currentNode);
                        mm=-1;
                        ArrayList <Integer> aux = new ArrayList();
                        int cont=0;
                        for (node currentSon : currentNode.sons) {
                            if(currentSon.mm==mm){
                                aux.add(cont);
                            }cont++;
                        }
                        if(aux.isEmpty()){
                           cont=0;
                           for (node currentSon : currentNode.sons) {
                                if(currentSon.mm==0){
                                    aux.add(cont);
                                }cont++;
                            } 
                        }
                        if(aux.isEmpty()){
                            cont=0;
                            for (node currentSon : currentNode.sons) {
                                aux.add(cont);
                                cont++;
                            }
                        }
                        int ns = (int)(Math.random() * (aux.size()));
                        if(currentNode.sons.get(aux.get(ns)).sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        System.out.println(currentNode.sons.get(aux.get(ns)));
                        for(int i = 0; i < 9; i++)
                        {
                            if((currentNode.sons.get(aux.get(ns)).table[i].equals("x") || currentNode.sons.get(aux.get(ns)).table[i].equals("o")) && !registry.contains(i))
                            {    
                                registry.add(i);
                                //drawO(actual);
                                table[i/3][i%3].text.setText("o");
                                turnX = true;
                            }
                        }
                        if(currentNode.sons.get(aux.get(ns)).sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.sons.get(aux.get(ns)).mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.sons.get(aux.get(ns)).mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        else
                        {
                            currentNode = currentNode.sons.get(aux.get(ns));
                        }
                    }
                    else
                    {
                        System.out.println("Terminado");
                        if(currentNode.mm==1)
                        {
                            System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else if (currentNode.mm==-1)
                        {
                            System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else
                        {
                            System.out.println("Fue un empate");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Fue un empate");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        }
                    }
                }
                if(player1 == 2 && player2 == 1)
                {
                    //System.out.println("PC vs. Player");
                    if(!currentNode.sons.isEmpty())
                    {
                        int mm;
                        mm=1;
                        int count = 0;
                        int indexEntered = 0;
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(table[i][j] == actual)
                                    indexEntered = count;
                                count++;
                            }
                        }
                        System.out.println(indexEntered);
                        int realPosition = position(indexEntered);
                        System.out.println(realPosition);
                        //currentNode = currentNode.sons.get(realPosition);
                        System.out.println(currentNode.sons.get(realPosition));
                        if(currentNode.sons.get(realPosition).sons.isEmpty())
                        {
                            System.out.println("Terminado");
                            if(currentNode.mm==1)
                            {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else if (currentNode.mm==-1)
                            {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                        else
                        {
                            for(int i = 0; i < 9; i++)
                            {
                                if((currentNode.sons.get(realPosition).table[i].equals("x") || currentNode.sons.get(realPosition).table[i].equals("o")) && !registry.contains(i))
                                {    
                                    registry.add(i);
                                    //drawX(actual);
                                    table[i/3][i%3].text.setText("o");
                                }
                            }
                            ArrayList<Integer> aux = new ArrayList();
                            int cont=0;
                            for (node currentSon : currentNode.sons.get(realPosition).sons) {
                                if(currentSon.mm==mm){
                                    aux.add(cont);
                                }cont++;
                            }
                            if(aux.isEmpty()){
                               cont=0;
                               for (node currentSon : currentNode.sons.get(realPosition).sons) {
                                    if(currentSon.mm==0){
                                        aux.add(cont);
                                    }cont++;
                                } 
                            }
                            if(aux.isEmpty()){
                                cont=0;
                                for (node currentSon : currentNode.sons) {
                                    aux.add(cont);
                                    cont++;
                                }
                            }
                            int ns = (int)(Math.random() * (aux.size()));
                            //father.sons.get(n).sons.get(aux.get(ns));
                            System.out.println(currentNode.sons.get(realPosition).sons.get(aux.get(ns)));
                            for(int i = 0; i < 9; i++)
                            {
                                if((currentNode.sons.get(realPosition).sons.get(aux.get(ns)).table[i].equals("x") || currentNode.sons.get(realPosition).sons.get(aux.get(ns)).table[i].equals("o")) && !registry.contains(i))
                                {    
                                    registry.add(i);
                                    //drawO(actual);
                                    table[i/3][i%3].text.setText("x");
                                    turnX = false;
                                }
                            }
                            if(!currentNode.sons.get(realPosition).sons.get(aux.get(ns)).sons.isEmpty())
                            {
                                //gameplay(father.sons.get(n).sons.get(aux.get(ns)), 1);
                                currentNode = currentNode.sons.get(realPosition).sons.get(aux.get(ns));
                            }
                            else{
                                //System.out.println("Terminado");
                                if(currentNode.sons.get(realPosition).mm==1)
                                {
                                    System.out.println("Ganó 'x'");
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Result");
                                    alert.setContentText("Ganó 'x'");
                                    //System.exit(0);
                                    alert.showAndWait();
                                    System.exit(0);
                                }
                                else if (currentNode.sons.get(realPosition).mm==-1)
                                {
                                    System.out.println("Ganó 'o'");
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Result");
                                    alert.setContentText("Ganó 'o'");
                                    //System.exit(0);
                                    alert.showAndWait();
                                    System.exit(0);
                                }
                                else
                                {
                                    System.out.println("Fue un empate");
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Result");
                                    alert.setContentText("Fue un empate");
                                    //System.exit(0);
                                    alert.showAndWait();
                                    System.exit(0);
                                }
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Terminado");
                        if(currentNode.mm==1)
                        {
                            System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else if (currentNode.mm==-1)
                        {
                            System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                        }
                        else
                        {
                            System.out.println("Fue un empate");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Fue un empate");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        }
                    }
                }
                if(player1 == 2 && player2 == 2)
                {
                    int mm;
                    mm = -1;
                    ArrayList<Integer> array = new ArrayList();
                    int cont = 0;
                    for (node currentSon : currentNode.sons) {
                        if (currentSon.mm == mm) {
                            array.add(cont);
                        }
                        cont++;
                    }
                    if (array.isEmpty()) {
                        cont = 0;
                        for (node currentSon : currentNode.sons) {
                            if (currentSon.mm == 0) {
                                array.add(cont);
                            }
                            cont++;
                        }
                    }
                    if (array.isEmpty()) {
                        cont = 0;
                        for (node currentSon : currentNode.sons) {
                            array.add(cont);
                            cont++;
                        }
                    }
                    int n = (int) (Math.random() * (array.size()));
                    System.out.println(currentNode.sons.get(array.get(n)));
                    for(int i = 0; i < 9; i++)
                    {
                        if((currentNode.sons.get(array.get(n)).table[i].equals("x") || currentNode.sons.get(array.get(n)).table[i].equals("o")) && !registry.contains(i))
                        {    
                            registry.add(i);
                            //drawX(actual);
                            table[i/3][i%3].text.setText("o");
                            turnX = false;
                        }
                    }

                    mm = 1;
                    if (currentNode.sons.get(array.get(n)).sons.isEmpty()) {
                        if (currentNode.sons.get(array.get(n)).mm == 1) {
                            System.out.println("Ganó 'x'");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Ganó 'x'");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        } else if (currentNode.sons.get(array.get(n)).mm == -1) {
                            System.out.println("Ganó 'o'");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Ganó 'o'");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        } else {
                            System.out.println("Fue un empate");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setContentText("Fue un empate");
                            //System.exit(0);
                            alert.showAndWait();
                            System.exit(0);
                        }
                    } else {
                        ArrayList<Integer> aux = new ArrayList();
                        cont = 0;
                        for (node currentSon : currentNode.sons.get(array.get(n)).sons) {
                            if (currentSon.mm == mm) {
                                aux.add(cont);
                            }
                            cont++;
                        }
                        if (aux.isEmpty()) {
                            cont = 0;
                            for (node currentSon : currentNode.sons.get(array.get(n)).sons) {
                                if (currentSon.mm == 0) {
                                    aux.add(cont);
                                }
                                cont++;
                            }
                        }
                        if (aux.isEmpty()) {
                            cont = 0;
                            for (node currentSon : currentNode.sons) {
                                aux.add(cont);
                                cont++;
                            }
                        }
                        int ns = (int) (Math.random() * (aux.size()));
                        //father.sons.get(n).sons.get(aux.get(ns));
                        System.out.println(currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)));
                        for(int i = 0; i < 9; i++)
                        {
                            if((currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)).table[i].equals("x") || currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)).table[i].equals("o")) && !registry.contains(i))
                            {    
                                registry.add(i);
                                //drawX(actual);
                                table[i/3][i%3].text.setText("x");
                                turnX = false;
                            }
                        }
                        if (!currentNode.sons.get(array.get(n)).sons.get(aux.get(ns)).sons.isEmpty()) {
                            currentNode = currentNode.sons.get(array.get(n)).sons.get(aux.get(ns));
                        } else {
                            //System.out.println("Terminado");
                            if (currentNode.sons.get(array.get(n)).mm == 1) {
                                System.out.println("Ganó 'x'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'x'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            } else if (currentNode.sons.get(array.get(n)).mm == -1) {
                                System.out.println("Ganó 'o'");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Ganó 'o'");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            } else {
                                System.out.println("Fue un empate");
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Result");
                                alert.setContentText("Fue un empate");
                                //System.exit(0);
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                    }
                    refreshState(actual);
                }
            }
        }
        
        public int position(int pos)
        {
            int box = 0;
            for(int i = 0; i < tableState.length; i++)
            {
                if(tableState[i].equals(""))
                {
                    box++;
                }
                if(i == pos)
                    return box;
            }
            return 0;
        }
    }
    
    private Pane root = new Pane();
    
    private Parent createContent()
    {
        root.setPrefSize(600, 600);
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                Tile tile = new Tile();
                tile.setTranslateX(j*200);
                tile.setTranslateY(i*200);
                root.getChildren().add(tile);
                table[i][j] = tile;
            }
        }
        return root;
    }
    
    @Override
    public void start(Stage primaryStage) {
        Label lblTitle = new Label("Tic-tac-toe");
        Label lblPlayers = new Label("Players");
        Label lblPlayer1 = new Label("Player 1");
        Label lblPlayer2 = new Label("Player 2");
        Label lblDificulty = new Label("Dificulty");
        final ToggleGroup tgRadioButtonPlayer1= new ToggleGroup(); //Creates a grupo to contain all radio buttons of curency 1
        RadioButton rbPlayer1User = new RadioButton("User");
        rbPlayer1User.setToggleGroup(tgRadioButtonPlayer1); //Assign the radio button to the group so you can only choose one option
        rbPlayer1User.setSelected(true); //Set seleceted the radio button
        RadioButton rbPlayer1PC = new RadioButton("PC");
        rbPlayer1PC.setToggleGroup(tgRadioButtonPlayer1);
        final ToggleGroup tgRadioButtonPlayer2= new ToggleGroup(); //Creates a grupo to contain all radio buttons of curency 1
        RadioButton rbPlayer2User = new RadioButton("User");
        rbPlayer2User.setToggleGroup(tgRadioButtonPlayer2); //Assign the radio button to the group so you can only choose one option
        rbPlayer2User.setSelected(true); //Set seleceted the radio button
        RadioButton rbPlayer2PC = new RadioButton("PC");
        rbPlayer2PC.setToggleGroup(tgRadioButtonPlayer2);
        final ToggleGroup tgRadioButtonDificulty= new ToggleGroup(); //Creates a grupo to contain all radio buttons of curency 1
        RadioButton rbDificultyEasy = new RadioButton("Easy");
        rbDificultyEasy.setToggleGroup(tgRadioButtonDificulty); //Assign the radio button to the group so you can only choose one option
        rbDificultyEasy.setSelected(true); //Set seleceted the radio button
        RadioButton rbDificultyHard = new RadioButton("Hard");
        rbDificultyHard.setToggleGroup(tgRadioButtonDificulty);
        Button btnPlay = new Button();
        btnPlay.setText("Play");
        Stage options = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(lblTitle, 0, 0);
        grid.add(lblPlayers, 0, 1);
        grid.add(lblPlayer1, 0, 2);
        grid.add(lblPlayer2, 1, 2);
        grid.add(rbPlayer1User, 0, 3);
        grid.add(rbPlayer1PC, 0, 4);
        grid.add(rbPlayer2User, 1, 3);
        grid.add(rbPlayer2PC, 1, 4);
        grid.add(lblDificulty, 0, 5);
        grid.add(rbDificultyEasy, 0, 6);
        grid.add(rbDificultyHard, 0, 7);
        grid.add(btnPlay, 0, 8);
        
        Scene scene = new Scene(grid, 200, 310);
        options.setTitle("Options");
        options.setScene(scene);
        options.show();
        
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("");
                if(rbPlayer1User.isSelected() == true)
                {
                    player1 = 1;
                }
                if(rbPlayer1PC.isSelected() == true)
                {
                    player1 = 2;
                }
                if(rbPlayer2User.isSelected() == true)
                {
                    player2 = 1;
                }
                if(rbPlayer2PC.isSelected() == true)
                {
                    player2 = 2;
                }
                if(rbDificultyEasy.isSelected() == true)
                {
                    dificulty = 1;
                }
                if(rbDificultyHard.isSelected() == true)
                {
                    dificulty = 2;
                }
                options.close();
                primaryStage.show();
                if(firstMove == true && player1 == 2)
                {
                    int rd = (int)(Math.random() * (currentNode.sons.size()));
                    System.out.println(currentNode.sons.get(rd));
                    currentNode = currentNode.sons.get(rd);
                    for(int i = 0; i < 9; i++)
                    {
                        if((currentNode.table[i].equals("x") || currentNode.table[i].equals("o")) && !registry.contains(i))
                        {    
                            registry.add(i);
                            //drawX(actual);
                            table[i/3][i%3].text.setText("x");
                            turnX = false;
                        }
                    }
                    //gameplay(currentNode.sons.get(rd),1);
                }
            }
        });
        /*
        rbPlayer1PC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("");
                if(rbPlayer1PC.isSelected() == true && rbPlayer2PC.isSelected() == true)
                {
                    rbPlayer2User.setSelected(true);
                }
            }
        });
        
        rbPlayer2PC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("");
                if(rbPlayer1PC.isSelected() == true && rbPlayer2PC.isSelected() == true)
                {
                    rbPlayer1User.setSelected(true);
                }
            }
        });*/
        
        primaryStage.setTitle("Tic-tac-toe");
        primaryStage.setScene(new Scene(createContent()));
        //primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("Ecole que dijo");
        tree ticTacToe = new tree();
        node root = ticTacToe.addRoot();
        ticTacToe.generateSons(root);
        currentNode = root;
        System.out.println(currentNode);
        //System.out.println("Ecole que dijo");
        launch(args);
    }
}

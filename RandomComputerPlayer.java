package Project2;
import java.util.Random;

public class RandomComputerPlayer extends Player{
    private boolean playerX=false;
    private int size;
    private int rows;
    private int cols;

    public RandomComputerPlayer(boolean playerX,int size){
        super(playerX,size);
        rows=size;
        cols=size;
    }

    @Override
    public int[] inputPlay(String[][] boardArray){
        Random rand = new Random();
        int randomCol=0;
        int randomRow=0;
        boolean chosenSpot=false;
        while(!chosenSpot){
            randomRow=rand.nextInt(rows);
            randomCol=rand.nextInt(cols);//chooses a random place and makes sure it is empty
            if(boardArray[randomRow][randomCol].equals(" ")){
                // if(playerX){
                //     boardArray[randomRow][randomCol]="X";
                // }
                // else{
                //     boardArray[randomRow][randomCol]="O";
                // }
                chosenSpot=true;
            }
        }
        System.out.println("Placing at "+randomRow+", "+randomCol);
        int[] indexes = {randomRow,randomCol};
        return indexes;
    }

}
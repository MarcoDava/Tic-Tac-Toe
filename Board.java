package Project2;

public class Board{
    private int size;
    private int rows;
    private int cols;
    private String[][] boardArray;
    Board(int size){//constructor sets values of the variables
        this.size=size;
        rows=size;
        cols=size;
        boardArray = new String[rows][cols];
    }

    private int inputCol;
    private int inputRow;
   
    
    String[][] getNewBoard(){
        System.out.println(rows);//creates an new 2d array of empty values
        for(int i=0;i<rows;i++){
            for(int j=0; j<cols;j++){
                boardArray[i][j]=" ";
            }
        }
        return boardArray;
    }

    void printBoard(String[][] boardArray){//prints array by iterating through all the indexes
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.print(boardArray[j][i]);
                if(j!=rows-1){
                    System.out.print(" | ");
                }
            }
            System.out.println("");
            if(i<rows-1){
                for(int j=0;j<cols;j++){
                    System.out.print("---");
                }
                System.out.println("");
            }
        }
        System.out.println("\n");
        
    }
    String[][] updateArray(String[][] boardArray,int[] input,boolean playerX){//updates values of the array
        inputRow=input[0];
        inputCol=input[1];//sets the input col and input row using the array indexes
        if(playerX){
            boardArray[inputRow][inputCol]="X";//replaces the old value with X
        }
        else{//same as above except for O
            boardArray[inputRow][inputCol]="O";
        }
        return boardArray;
    }
}
package Project2;



public class WinConditions extends Board{
    private int size;
    private int rows;
    private int cols;
    private int winAmount;
    WinConditions(int size,int winAmount){
        super(size);
        rows=size;
        cols=size;
        this.winAmount=winAmount;//sets winamount
    }
    private int inputCol;
    private int inputRow;
    
    public int checkBoard(String[][] boardArray,int[] input,boolean playerX){
        
        
        String playerSymbol;//sets symbols based on whether it is player x or not
        if(playerX){
            playerSymbol="X";
        }
        else{
            playerSymbol="O";
        }
    
        inputRow=input[0];
        inputCol=input[1];
        int countRows=0;
        int countCols=0;
        int countDiag1=0;//these are used to check all the comditions
        int countDiag2=0;
        for(int i=0;i<cols;i++){
            if(boardArray[inputRow][i].equals(playerSymbol)){//compares the index and player symbol if they are the same
                countRows++;
                if(countRows>=winAmount){//if there are multiple in a row, it will count as a win and return that the player won
                    if(playerX){
                        return 1;
                    }
                    else{
                        return 2;
                    }
                }
            }
            else{
                countRows=0;
            }
            if(boardArray[i][inputCol].equals(playerSymbol)){//same as above
                countCols++;
                if(countCols>=winAmount){
                    if(playerX){
                        return 1;//1 means playerx wins
                    }
                    else{
                        return 2;//2 means player0 wins
                    }
                }
            }
            else{
                countCols=0;
            }
        }
        int startingRow=0;//starting row is for diagnals because their checking is a lot more complicated
        int startingCol=0;
        int count=0;
        if(inputRow>=inputCol){//uses arithmetic to determine the starting row. 
            startingRow=inputRow-inputCol;//this is used to find the left most index of the diagnol that goes from right to left and goes downwards.
            //this is so the diaganol starts at the beginning and ends at the end
            startingCol=0;
            for(int i=startingRow;i<rows;i++){
                if(boardArray[i][i].equals(playerSymbol)){
                    count++;
                    if(count>countDiag1){
                        countDiag1=count;
                    }
                }
                else{
                    count=0;
                }
            }
        }
        
        else{
            startingRow=0;//same as above
            startingCol=inputCol-inputRow;
            count=0;
            for(int i=startingCol;i<cols;i++){
                if(boardArray[i][i].equals(playerSymbol)){
                    count++;
                    if(count>countDiag1){
                        countDiag1=count;
                    }
                }
                else{
                    count=0;//if it equals something that is not playersymbol, it goes back to 0
                }
            }
        }
        startingCol=inputCol;
        startingRow=inputRow;//the other diaganol is a lot more complicated. This however tries to achive the same thing. it starts at the bottom most index of the other diagnol and goes 
        while(startingCol>0&&startingRow<rows-1){
            startingCol--;
            startingRow++;
        }
        count=0;
        int changeCol=0;
        for(int i=startingRow;i>=0;i--){
            if(boardArray[i][changeCol].equals(playerSymbol)){
                count++;
                if(count>countDiag2){
                    countDiag2=count;
                }
            }
            else{
                count=0;
            }
            changeCol++;
        }
        if(countDiag1>=winAmount||countDiag2>=winAmount){
            if(playerX){
                return 1;
            }
            else{
                return 2;
            }
        }
        else{
            if(boardFull(boardArray)){
                return 3;//means game tied
            }
            else{
                return 0;//only occurs when board is not full
            }
        }
    }
    boolean boardFull(String[][] boardArray) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (boardArray[i][j].equals(" ")) { //checks if there is a single empty space, if it does find an empty space, then the board is not full
                    return false; 
                }
            }
        }
        return true; 
    }
}
package Project2;
import java.util.Scanner;

public class Game {
    private static int size=0;
    private static int winAmount=0;
    public Game(int size, int winAmount){//sets size of the board
        this.size=size;
        this.winAmount=winAmount;
    }

    public static void main(String[] args) {//main will just call run Game so it has a very small amount of clutter
        boolean replay=true;
        Game game=new Game(3,3);//sets size to be 3x3

        while(replay){
            game.runGame();//calls all the functions using this function
            Scanner reader=new Scanner(System.in);//for userinput
            System.out.println("\n\nDo you want to play again?(yes/y)");
            String userInput=reader.nextLine();
            if(!userInput.toLowerCase().equals("y")&&!userInput.toLowerCase().equals("yes")){//compares if the userinput if they want to restart or not
                replay=false;
            }
        }
    }
    public void runGame(){
        int mode = 0;
        
        WinConditions gameCheck=new WinConditions(size,winAmount);//decalres objects
        Board board = new Board(size);
        Scanner reader = new Scanner(System.in);//calling these objects allow for the connection between all the classes
        
        Player player1 = null;
        Player player2 = null; // Declare player2 as a reference of a common parent class or interface

        boolean playerX = true;
        boolean pass = false;

        while (!pass) {//to make sure user input is correct
            System.out.print("Enter 1 for player vs player, 2 for player vs computer, 3 for computer vs computer:  ");
            try {
                mode = reader.nextInt(); //read the input here
                
                if (mode>3||mode<1){
                    throw new Exception("Must be a number between 1 to 3");//if the user input does not give the correct number it will throw exception
                }
                pass = true;
            } 
            catch (Exception e) {
                System.out.println("Please enter the mode properly");
                reader.next(); //clear the invalid input
            }
        }
        if (mode == 1){
            player1 = new HumanPlayer(true,size);//this is why player object was called in the start instead of the specialized classes. 
            player2 = new HumanPlayer(false,size);//sets size and gives the playerX value
        }
        else if (mode == 2) {
            player1 = new HumanPlayer(true,size);
            player2 = new RandomComputerPlayer(false,size);
        } else {
            player1= new RandomComputerPlayer(true,size);
            player2 = new RandomComputerPlayer(false,size);
        }

        String[][] boardArray = board.getNewBoard();//gives the new array
        board.printBoard(boardArray);//prints an empty board

        int gameConditions = 0;
        while (gameConditions == 0) {//if game conditions is 0, it means game is undecided
            
            int[] indexes = new int[2];//sets new indexes array to allow for user input

            if (playerX) {
                indexes = player1.inputPlay(boardArray);//inputs play using the indexes array
                board.updateArray(boardArray, indexes, playerX);//updates array using indexes
                //board.printBoard(boardArray);//DEBUGGING
                gameConditions = gameCheck.checkBoard(boardArray,indexes,playerX);//checks if they have won
                playerX = false;
            } else {
                indexes = player2.inputPlay(boardArray);
                board.updateArray(boardArray, indexes, playerX);
                gameConditions = gameCheck.checkBoard(boardArray,indexes,playerX);
                playerX = true;//these two statements will work regardless of if the player is human or not

            }
            board.printBoard(boardArray);
            try {
               Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
            
        }
        
        if (gameConditions == 1) {
            System.out.println("Player X won");
        } else if (gameConditions == 2) {
            System.out.println("Player O won");
        } else {
            System.out.println("Game tied");
        }//declares winner
    }
}

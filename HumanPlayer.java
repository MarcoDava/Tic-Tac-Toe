package Project2;
import java.util.Scanner;

public class HumanPlayer extends Player{
    private int size;
    private boolean playerX=false; 
    private int rows;
    private int cols;

    public HumanPlayer(boolean playerX,int size){
        super(playerX,size);
        rows=size;
        cols=size;
    }



    @Override
    public int[] inputPlay(String[][] boardArray){
        boolean chosenSpot=false;
        int inputCol=0;
        int inputRow=0;
        Scanner reader = new Scanner(System.in);        
        while (!chosenSpot) {
            System.out.print("Input where you want to place your mark with two indexes separated by a comma: ");
            String input = reader.nextLine(); //read the entire line

            try {
                String[] inputs = input.replaceAll("[()]","").split(","); //split by comma and remove brackets
                if(!input.matches("\\(.{1,2},.{1,2}\\)")){//will check if they inputted the right values
                    throw new IllegalArgumentException("Invalid input format. Please enter in between 2 brackets.");
                }
                if (inputs.length != 2) {//makes sure the split is only 2 numbers
                    throw new IllegalArgumentException("Invalid input format. Please enter two numbers separated by a comma.");
                }

                inputRow = Integer.parseInt(inputs[0].trim());//sets the new integer values
                inputCol = Integer.parseInt(inputs[1].trim());

                if (inputRow < 0 || inputRow >= rows || inputCol < 0 || inputCol >= cols) {//makes sure the input is within the bounds
                    System.out.println("Invalid coordinates. Please choose a valid spot on the board.");
                } else if (!boardArray[inputRow][inputCol].equals(" ")) {//if spot is not empty, it will move on
                    System.out.println("Spot already taken. Please choose a different spot.");
                } else {
                    chosenSpot = true; // Valid input and spot is free
                }
            } catch (NumberFormatException e) {//if they do not put numbers, it will go to this catch
                System.out.println("Invalid input. Please enter numbers only.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        int[] indexes={inputRow,inputCol};
        return indexes;
    
    }
}
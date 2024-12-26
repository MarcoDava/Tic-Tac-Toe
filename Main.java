package Project2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean replay=true;
        boolean pass=false;
        int size=0;
        int winAmount=0;
        Scanner reader = new Scanner (System.in);

        while (!pass) {
            System.out.print("Input how big you want the board and the winAmount ");
            String input = reader.nextLine(); //read the entire line

            try {
                String[] inputs = input.split(","); //split by comma and remove brackets

                if (inputs.length != 2) {//makes sure the split is only 2 numbers
                    throw new IllegalArgumentException("Invalid input format. Please enter two numbers separated by a comma.");
                }

                size = Integer.parseInt(inputs[0].trim());//sets the new integer values
                winAmount = Integer.parseInt(inputs[1].trim());

                if (size < 3 || size >= 20 || winAmount < 3 || winAmount >= 20) {//makes sure the input is within the bounds
                    System.out.println("Must be between 3 and 20");
                } else {
                    pass = true; // Valid input and spot is free
                }
            } catch (NumberFormatException e) {//if they do not put numbers, it will go to this catch
                System.out.println("Invalid input. Please enter numbers only.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        Game game=new Game(size,winAmount);//sets size to be whatever user input is
        while(replay){
            game.runGame();//calls all the functions using this function
            System.out.println("\n\nDo you want to play again?(yes/y)");
            String userInput=reader.nextLine();
            if(!userInput.toLowerCase().equals("y")&&!userInput.toLowerCase().equals("yes")){//compares if the userinput if they want to restart or not
                replay=false;
            }
        }
    }
}

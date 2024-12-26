package Project2;

public abstract class Player{
    private boolean playerX;
    private int size;
    Player(boolean playerX,int size){//super class of random computer player and human player
        this.playerX=playerX;
        this.size=size;
    }
    public abstract int[] inputPlay(String[][] boardArray);//sets abstract method
}
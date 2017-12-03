

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Nim {
    private int[] nimBoard;
    private Scanner input = new Scanner(System.in);
    private int pvpTurn;
    private int numPlayers;
    
    public Nim(){
        createNimBoard(); //constructor to create the nim stacks
    }
    
    
    
    public void createNimBoard(){ //Initialized the Nim Board with a random stack of number and random number of tokens on each stack
        Random rand = new Random();
        int numStack = rand.nextInt(8) + 3;
        nimBoard = new int[numStack];
        for(int i = 0; i < numStack; i++){
            int numToken = rand.nextInt(10) + 1;
            nimBoard[i] = numToken;
        }
    }
    
    public void PvP(){ //Player vs Player
        System.out.println("Enter number of players: "); //Get number of people will participate
        int numberOfPlayers = input.nextInt();
        System.out.println("\nGame Start!"); 
        int countTurn =0, player;
        do{
            display(); //display the Nim stacks
            player = (countTurn%numberOfPlayers) +1; // calculate which player's turn and stored it to player variable
            System.out.println("\nPlayer " + player + "'s turn:");
            humanMove(); // get move from user(s)
            countTurn++; //number of turn
        }while(!checkEndGame());//check if total token in the stacks is smaller than or equal 0
        System.out.println("\nPlayer " + player + " lost!"); // print out who lost
    }
    
    public void PvC(){ //Player vs Computer
        System.out.println("\nGame Start!"); 
        Random rand = new Random();
        int goFirst = rand.nextInt(2); //choose who goes first.
        //int goFirst=0;
        if(goFirst == 1){ // if goFirst = 1 means player goes first
            System.out.println("\nPlayer goes first!");
            do{
                display(); //display stacks
                humanMove(); //get move from player
                if(checkEndGame()){ //check after each move, if end game is true then print the result and get out of the loop
                    System.out.println("Player lost!");
                    break;
                }
                display();
                compMove(); //calculate move for computer
                if(checkEndGame()){
                    System.out.println("Computer lost!");
                    break;
                }
            }while(true);
        }else{ //if goFirst != 1 means computer goes first
            System.out.println("\nComputer goes first!");
            do{
                display();
                compMove();
                if(checkEndGame()){
                    System.out.println("Computer lost!");
                    break;
                }
                display();
                humanMove();
                if(checkEndGame()){
                    System.out.println("Player lost!");
                    break;
                }
            }while(true);
        }
    }
    
    public boolean checkEndGame(){ // if total tokens from stacks is 0, game is over
        //System.out.println("Total token: " + totalToken());
        if(totalToken()<=0) //call the totalToken function below
            return true; //true means there is no token left
        return false;
    }
    
    public void display(){ //display the Nim's stacks
        for(int i = 0 ; i < nimBoard.length; i++){
            System.out.print(i+1 + ": ");
        for(int j = 0; j < nimBoard[i]; j ++){
            System.out.print("0");
        }
        System.out.println();
        }
    }
    
    public void humanMove(){//get move from players
        int heap,token;
        do{
        System.out.print("\nChoose heap number: ");
        heap = input.nextInt(); //get which heap
        System.out.print("Choose token number: ");
        token = input.nextInt();//get how many token
        if((heap > nimBoard.length) || (token > nimBoard[heap -1]) || token <= 0) //it'll be considered invalid if player choose heap out of bound/ number of token larger than token in that stack/ negative token
            System.out.println("Invalid move! Try again");
        }while(heap > nimBoard.length || token > nimBoard[heap -1] || token <= 0);//if input is invalid, start over again
        //nimBoard[heap-1] -= token; //after validate input, perform the action
        removeToken(heap-1,token);
    }
    
    public int[] compMove(){ //Computer's move
        boolean winningMoveAvailable = false; //bool var to check if there's a winning move
        Random rand = new Random();
        int heapTakenIndex=0; //var of which heap will have token taken
        int numTokenTakeOff=0; //var of how many token will be taken
        for(int i = 0; i < nimBoard.length; i++){ //loop goes through each stack
            int tempNimSumEach = nimSum() ^ nimBoard[i]; //calculate NimSum of each stack
            if(tempNimSumEach < nimBoard[i]){ //Find the first heap where the individual nim-sum is smaller than the heap size
                numTokenTakeOff = nimBoard[i] - tempNimSumEach; //then subtract that heap'size with nimSum of that stack counters
                heapTakenIndex = i; //get the index of the stack will be taken
                winningMoveAvailable = true;//there is a move to win
                break;
            }
        }
        
        //System.out.println(numTokenTakeOff);
        if(winningMoveAvailable){ //Since win move is available
            //System.out.println("Not random"); 
            if(totalToken() == nimBoard[heapTakenIndex] + 1)
                numTokenTakeOff = nimBoard[heapTakenIndex];

            if(totalToken() == nimBoard[heapTakenIndex] && (nimBoard[heapTakenIndex] != 1)){//Check the case where nimBoard = {0,0,0,6}
                numTokenTakeOff = nimBoard[heapTakenIndex] - 1; //just leave one token
            }
            
        }else{//if computer in losing position, it'll choose a valid random move
            do{
                heapTakenIndex = rand.nextInt(nimBoard.length);
            }while(nimBoard[heapTakenIndex] <= 0);//choose valid random move
            numTokenTakeOff = rand.nextInt(nimBoard[heapTakenIndex]) +1;
        }
        //nimBoard[heapTakenIndex] -= numTokenTakeOff; //perform the action
        removeToken(heapTakenIndex,numTokenTakeOff); //perform the action
        System.out.println("Computer took " + numTokenTakeOff + " from heap " + (heapTakenIndex+1)); //print what action computer took
        int[] temp = {heapTakenIndex,numTokenTakeOff};
        return temp;
    }
    
    public int nimSum(){ //calculate total nim sum from stacks
        int nimSum = 0;
        for(int i = 0; i < nimBoard.length; i++){
            nimSum ^= nimBoard[i];
        }
        return nimSum;
    }
    
    public int totalToken(){ //calculate total token from stacks
        int total =0;
        for(int i = 0; i < nimBoard.length;i++){
            total += nimBoard[i];
        }
        return total;
    }
    
    public int getBoardSize(){
        return nimBoard.length;
    }
    
    public int getNumToken(int i){
        return nimBoard[i];
    }
    
    public void removeToken(int i, int j){
        nimBoard[i] -=j;
    }
    
    public int getPvPTurn(int countTurn){
        //System.out.println(numPlayers);
        pvpTurn = (countTurn % numPlayers) +1;
        countTurn++;
        return pvpTurn;
    }
    
    public void setNumPlayer(int numPlayers){
        this.numPlayers = numPlayers;
    }
    
    public int getNumPlayer(){
        return numPlayers;
    }
}


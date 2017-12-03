import java.util.Scanner;

public class runGame {

	public static void main(String[] args) {
        System.out.println("Welcome to the Nim Game! Please choose a game mode!");
        int choice;
        Nim newGame = new Nim();
        do{
            choice = menu();
            switch(choice){
                case 1:
                    newGame.PvP();
                    break;
                case 2:
                    newGame.PvC();
                    break;
                case 3:
                    System.out.println("Thank you for using");
                    break;
                default:
                    System.out.println("Invalid Selection");
            }
        }while(choice>3 || choice < 1);
    }
    
    public static int menu(){
        Scanner aScanner = new Scanner(System.in);
        System.out.println("\n1. PvP");
        System.out.println("2. PvC");
        System.out.println("3. QUIT");
        System.out.print("Enter your choice----> ");
        return aScanner.nextInt();
    }
}
	


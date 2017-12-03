import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class PvP extends JFrame {

	private JPanel contentPane;
        private Nim pvpGame = new Nim();
	private boolean[][] counter = new boolean[11][11];
        private JButton[][] buttons = new JButton[11][11];
        private int countTurn;
	/**
	 * Launch the application.
	 */
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PvP frame = new PvP("Hello");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public PvP(String mystring) {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 700, 700);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            generateTokens();
        }   
        
        void generateTokens(){
            
            pvpGame.display();
            for (int h=0; h<11; h++) {
                for (int g=0; g<11 ;g++) {
                    counter[h][g] = false;
                }
            }
       
//            Random rand= new Random();
//            int columns=rand.nextInt(8)+3;
            for (int j=0; j<pvpGame.getBoardSize();j++) {
                for (int i=0; i<pvpGame.getNumToken(j);i++) {
                    JButton token= new JButton("x"+j+",y"+i);
                    token.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int countTokenRemoved = 0;
                            int heapRemoved = 0;
                            for(int j=0; j<11; j++) {
                                for (int i=0; i<11; i++) {
                                    if (e.getSource()==buttons[j][i]){
                                        heapRemoved = j;
                                        System.out.println(j + " " + i);
                                        for (int z=i; z<counter[j].length; z++) {
                                            if (counter[j][z]==true) {
	            				buttons[j][z].hide();
	            				counter[j][z]=false;
                                                countTokenRemoved++;
                                                
//                                                JLabel player = new JLabel("It is Player " + pvpGame.getPvPTurn(game.getNumPlayer()) + " 's Turn");
//                                                player.setBounds(500, 50, 300, 100);
//                                                contentPane.add(player);
                                                
                                            }
                                        }
                                    }
                                }
                            }
                            pvpGame.removeToken(heapRemoved, countTokenRemoved);
                            System.out.println();
                            pvpGame.display();
                            countTurn++;
                            if(pvpGame.checkEndGame()){ //checkEndGame is true means there is no token left 
                                System.out.println("Done");
                                //display a message box say player x won
                                //terminate the pvp panel and hide it
                                JOptionPane.showMessageDialog(null, "Player X won", "InfoBox: " + "Congratulation", JOptionPane.INFORMATION_MESSAGE);
//                                contentPane.add(lblNewLabel);
                                contentPane.setVisible(false);
                                MenuGUI mg = new MenuGUI();
                                mg.getFrame().show();
                            }else{
                                System.out.println(" Not Done Yet ");
                            }
                        }
                        });
                        token.setBounds(j*50, 575-(i*50), 50, 50);
                        getContentPane().add(token);  
                        buttons[j][i]=token;
                        counter[j][i]=true;
			}
                }
        }
}

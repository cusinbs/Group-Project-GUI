import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PvC extends JFrame {

	private JPanel contentPane;
        private Nim pvcGame = new Nim();
	private boolean[][] counter = new boolean[11][11];
        private JButton[][] buttons = new JButton[11][11];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PvC frame = new PvC();
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
	public PvC() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 700, 700);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            generateTokens();
	}
        
        void removeButtons(int heap, int token){
            for(int i=pvcGame.getNumToken(heap) - token; i<pvcGame.getNumToken(heap); i++) {
                System.out.println(heap + " " + i);
                buttons[heap][i].hide();
                counter[heap][i]=false;
            }
        }
        
        void generateTokens(){
            pvcGame.display();
            for (int h=0; h<11; h++) {
                for (int g=0; g<11 ;g++) {
                    counter[h][g] = false;
                }
            }
       
//            Random rand= new Random();
//            int columns=rand.nextInt(8)+3;
            for (int j=0; j<pvcGame.getBoardSize();j++) {
                for (int i=0; i<pvcGame.getNumToken(j);i++) {
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
                            pvcGame.removeToken(heapRemoved, countTokenRemoved);
                            int[] tempResult = pvcGame.compMove();
                            pvcGame.display();
                            int token = tempResult[1];
                            int heap = tempResult[0];
//                            System.out.println(tempResult[0] + " " + tempResult[1] + " " + pvpGame.getNumToken(tempResult[0]));
                            removeButtons(token,heap);
                                
                            
                            System.out.println();
                            
                            if(pvcGame.checkEndGame()){ //checkEndGame is true means there is no token left 
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

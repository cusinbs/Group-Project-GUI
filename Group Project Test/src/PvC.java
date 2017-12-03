import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class PvC extends JFrame {

	private JPanel contentPane;
        private Nim pvcGame = new Nim();
        private JButton[][] buttons = new JButton[10][10];
        private int playerTurn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
            public void run() {
		try {
                    PvC frame = new PvC();
                    frame.setVisible(true);
		}catch (Exception e) {
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
            whoGoestFirst();
	}
        
        private void whoGoestFirst(){
            Random rand = new Random();
            int goFirst = rand.nextInt(2); //choose who goes first.
            if(goFirst == 0){ //comp goes first if goFirst = 0
                if(pvcGame.totalToken() > 0){
                    JOptionPane.showMessageDialog(null, "Computer goes first", "InfoBox: " + "Notification", JOptionPane.INFORMATION_MESSAGE);
                    compMoveGUI();
                }
            }else{
                    JOptionPane.showMessageDialog(null, "Player goes first", "InfoBox: " + "Notification", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        private void removeButtons(int heap, int token){
            for(int i = 0; i < token; i++){
                buttons[heap][pvcGame.getNumToken(heap)+i].hide();
            }
        }
        
        private void endingGame(){
            if(pvcGame.checkEndGame()){ //checkEndGame is true means there is no token left 
                if(playerTurn == 0){ //0 is computer took last token, 1 is player took last token
                    JOptionPane.showMessageDialog(null, "Player won!", "InfoBox: " + "Congratulation", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Computer won!", "InfoBox: " + "Congratulation", JOptionPane.INFORMATION_MESSAGE);
                }
                contentPane.setVisible(false);
                MenuGUI mg = new MenuGUI();
                mg.getFrame().show();
            }
        }
        
        private void compMoveGUI(){
            playerTurn = 0;
            int[] tempResult = new int[2];
            tempResult = pvcGame.compMove();
            int token = tempResult[1];
            int heap = tempResult[0];
            removeButtons(heap,token);
        }
        
        private void generateTokens(){
            pvcGame.display();
            for (int j=0; j<pvcGame.getBoardSize();j++) {
                for (int i=0; i<pvcGame.getNumToken(j);i++) {
                    JButton token= new JButton(j+""+i);
                    token.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int countTokenRemoved = 0;
                            int heapRemoved = 0;
                            for(int j=0; j<pvcGame.getBoardSize(); j++) {
                                for (int i=0; i<pvcGame.getNumToken(j); i++) {
                                    if (e.getSource()==buttons[j][i]){
                                        heapRemoved = j;
                                        for (int z=i; z<pvcGame.getNumToken(heapRemoved); z++) {
                                            buttons[j][z].hide();
                                            countTokenRemoved++;
                                        }
                                    }
                                }
                            }
                            pvcGame.removeToken(heapRemoved, countTokenRemoved);
                            playerTurn = 1;
                            if(pvcGame.totalToken() > 0){
                                compMoveGUI();
                            }
                            pvcGame.display();
                            endingGame();
                        }
                        });
                        token.setBounds(j*50, 575-(i*50), 50, 50);
                        getContentPane().add(token);  
                        buttons[j][i]=token;
			}
                }
        }
}

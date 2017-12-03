import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class PvPStart extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
        private int numPlayers;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PvPStart frame = new PvPStart();
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
	public PvPStart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouHaveSelected = new JLabel("You have selected PVP!");
		lblYouHaveSelected.setBounds(143, 6, 161, 16);
		contentPane.add(lblYouHaveSelected);
		
		JLabel lblNewLabel = new JLabel("Please input number of players");
		lblNewLabel.setBounds(6, 52, 211, 35);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(240, 52, 150, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("GO!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.getText();
				PvP open = new PvP("Hello");
				open.show();
				dispose();
				try{
                                    setNumPlayer(Integer.parseInt(textField.getText()));
                                    System.out.println("PVP START: " + numPlayers);
                                }catch(NumberFormatException ex){
                                    JOptionPane.showMessageDialog(null, "Invalid input!", "InfoBox: " + "Warning!", JOptionPane.INFORMATION_MESSAGE);
                                    textField.setText("");
                                }
			}
		});
		btnNewButton.setBounds(143, 169, 161, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(143, 210, 161, 29);
		contentPane.add(btnNewButton_1);
	}
        
        public int getNumPlayer(){
            return numPlayers;
        }
        
        public void setNumPlayer(int numPlayer){
            this.numPlayers = numPlayer;
        }
}

package a01027727.a3;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("A3 by Eric Lau A01027727");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][grow]", "[][][][][]"));
		
		textField = new JTextField();
		contentPane.add(textField, "cell 5 2,growx");
		
		textField.setFont(new Font("Courier New", Font.BOLD, 24));
		textField.setForeground(Color.RED);
		textField.setColumns(10);
		
		JButton btnQuickPick = new JButton("Quick Pick");
		btnQuickPick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RandomNumberGenerator randomNumbers = new RandomNumberGenerator();
				
				textField.setText(randomNumbers.getRandomNumbers());
				
			}
		});
		contentPane.add(btnQuickPick, "cell 5 4");
	}

}

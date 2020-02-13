package a01027727.b1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtWordtotal;
	private String input;
	private JTextField txtMinwordlength;
	private JTextField txtMaxWordLength;
	private JTextField txtAverageWordLength;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500, 600);
		setTitle("Letter and Word Counter by Eric Lau A01027727");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][grow][][][][]"));
		
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 1,growx");
		textField.setColumns(10);
		
		JLabel lblLetterCount = new JLabel("Letter Count:");
		contentPane.add(lblLetterCount, "cell 0 3");
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, "flowx,cell 1 3,grow");
		
		JButton btnCountLetters = new JButton("Count Text");
		btnCountLetters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				input = textField.getText();
				textArea.setText(CountEachLetter.output(input));
				txtWordtotal.setText(CountEachLetter.countWords(input));
				txtMinwordlength.setText(CountEachLetter.countMinimumWordLength(input));
				txtMaxWordLength.setText(CountEachLetter.countMaximumWordLength(input));
				txtAverageWordLength.setText(CountEachLetter.countAverageWordLength(input));
				
			}
		});
		contentPane.add(btnCountLetters, "cell 1 2");
		
		txtWordtotal = new JTextField();
		
		JLabel lblMinWordLength = new JLabel("Min Word Length:");
		contentPane.add(lblMinWordLength, "cell 0 4,alignx trailing");
		
		txtMinwordlength = new JTextField();
		contentPane.add(txtMinwordlength, "cell 1 4,growx");
		txtMinwordlength.setColumns(10);
		
		JLabel lblMaxwordLength = new JLabel("Max Word Length:");
		contentPane.add(lblMaxwordLength, "cell 0 5,alignx trailing");
		
		txtMaxWordLength = new JTextField();
		contentPane.add(txtMaxWordLength, "cell 1 5,growx");
		txtMaxWordLength.setColumns(10);
		
		JLabel lblWordTotal = new JLabel("Word Count:");
		contentPane.add(lblWordTotal, "cell 0 6,alignx trailing");
		contentPane.add(txtWordtotal, "cell 1 6,growx");
		txtWordtotal.setColumns(10);
		
		JLabel lblAverageWordLength = new JLabel("Average Word Length:");
		contentPane.add(lblAverageWordLength, "cell 0 7,alignx trailing");
		
		txtAverageWordLength = new JTextField();
		contentPane.add(txtAverageWordLength, "cell 1 7,growx");
		txtAverageWordLength.setColumns(10);

	}

}

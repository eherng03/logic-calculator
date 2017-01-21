import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField result;
	private JComboBox cBOperatorA;
	private JComboBox cBOperatorB;
	private JComboBox cBOperation;
	private JComboBox cBNotA;
	private JComboBox cBNotB;
	private LogicCalculator calculator;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		calculator = new LogicCalculator();
		initializeFrame();
		initializeComboBoxes();
		initializeLabel("A", 41, 22, 46, 14);
		initializeLabel("B", 210, 22, 46, 14);
		initializeLabel("Operation", 86, 22, 114, 14);
		initializeLabel("Result", 266, 22, 61, 14);
		initializeLabel("If the operation does not exist you can create it", 41, 86, 239, 29);
		
		result = new JTextField();
		result.setBounds(416, 41, 86, 20);
		frame.getContentPane().add(result);
		result.setColumns(10);
		
		JButton btnOperate = new JButton("Operate");
		btnOperate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultValue = calculator.operate(cBNotA.getSelectedItem(), cBOperatorA.getSelectedItem(), cBOperation.getSelectedItem(), cBNotB.getSelectedItem(), cBOperatorB.getSelectedItem());
				result.setText(Integer.toString(resultValue));
			}
		});
		btnOperate.setBounds(416, 72, 89, 23);
		frame.getContentPane().add(btnOperate);
		
	}
	
//---------------------------------------------------------------------------------------------------
//					Initializations
//---------------------------------------------------------------------------------------------------
	private void initializeLabel(String string, int i, int j, int k, int l) {
		JLabel lbl = new JLabel(string);
		lbl.setBounds(126, 23, k, l);
		frame.getContentPane().add(lbl);
	}

	private void initializeComboBoxes() {
		
		cBOperatorA = new JComboBox();
		cBOperatorA.setBounds(106, 41, 35, 20);
		cBOperatorA.addItem(0);
		cBOperatorA.addItem(1);
		frame.getContentPane().add(cBOperatorA);
		
		cBOperatorB = new JComboBox();
		cBOperatorB.setBounds(371, 41, 35, 20);
		cBOperatorB.addItem(0);
		cBOperatorB.addItem(1);
		frame.getContentPane().add(cBOperatorB);
		
		cBOperation = new JComboBox();
		cBOperation.setBounds(151, 41, 130, 20);
		cBOperation.addItem(" ");
		cBOperation.addItem("OR");
		frame.getContentPane().add(cBOperation);
		
		cBNotA = new JComboBox();
		cBNotA.setBounds(26, 41, 70, 20);
		cBNotA.addItem(" ");
		cBNotA.addItem("NOT");
		frame.getContentPane().add(cBNotA);
		
		cBNotB = new JComboBox();
		cBNotB.setBounds(291, 41, 70, 20);
		cBNotB.addItem(" ");
		cBNotB.addItem("NOT");
		frame.getContentPane().add(cBNotB);
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 541, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
}

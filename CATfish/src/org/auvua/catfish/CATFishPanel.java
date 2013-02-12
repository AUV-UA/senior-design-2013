package org.auvua.catfish;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class CATFishPanel implements ActionListener {

	public JFrame frmCatfish;
	private JTextField panelA0_t;
	private JTextField panelA1_t;
	private JTextField panelA2_t;
	private JTextField panelA3_t;
	private JTextField panelA4_t;
	private JTextField panelA5_t;
	private JTextField panelHeading_t;
	private JTextField panelPitch_t;
	private JTextField panelRoll_t;
	private JTextField panelAccX_t;
	private JTextField panelAccY_t;
	private JTextField panelAccZ_t;
	private JComboBox<String> panelPortJoy_cb;
	private JComboBox<String> panelPortArd_cb;
	private JComboBox<String> panelPortComp_cb;
	private JComboBox<String> panelPortMotors_cb;
	private JComboBox<String> panelBaudJoy_cb;
	private JComboBox<String> panelBaudArd_cb;
	private JComboBox<String> panelBaudComp_cb;
	private JComboBox<String> panelBaudMotors_cb;
	private JButton panelConnJoy_b;
	private JButton panelConnArd_B;
	private JButton panelConnComp_b;
	private JButton panelConnMotors_b;
	private JCheckBox panelStatusJoy_chk;
	private JCheckBox panelStatusArd_chk;
	private JCheckBox panelStatusComp_chk;
	private JCheckBox panelStatusMotors_chk;
	public JEditorPane panelLog_ta;
	private JButton panelClsOutput_b;

	/**
	 * Create the application.
	 */
	public CATFishPanel() {
		initialize();
		
		//set global logging output stream to textarea
		Logger.getGlobal().addHandler(new Handler() {
		      public void publish(LogRecord logRecord) {
		    	  StringBuilder msg = new StringBuilder();
		    	  msg.append(logRecord.getLevel() + ": ");
		    	  msg.append(logRecord.getMessage());
		    	  msg.append(" [<span style=\"color:blue;\">" + logRecord.getSourceClassName() + ":");
		    	  msg.append(logRecord.getSourceMethodName() + "</span>]<br/>");
		    	  System.out.println(panelLog_ta.getText().substring(0, panelLog_ta.getText().indexOf("</body>")) + msg.toString() + "</body></html>");
		    	  panelLog_ta.setText(panelLog_ta.getText().substring(0, panelLog_ta.getText().indexOf("</body>")) + msg.toString() + "</body></html>");
		      }

			@Override
			public void close() throws SecurityException {
			}

			@Override
			public void flush() {
			}
		});
		
		Logger.getGlobal().log(Level.INFO, "Testing LOGGER. If you can read me, it works.");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCatfish = new JFrame();
		frmCatfish.setResizable(false);
		frmCatfish.setTitle("CATfish Interface Panel");
		frmCatfish.setBounds(100, 100, 958, 476);
		frmCatfish.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UIManager.put("nimbusBase", new Color(230, 230, 230));
		UIManager.put("nimbusBlueGrey", new Color(190, 190, 190));
		UIManager.put("control", new Color(240, 240, 240));
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{11, 357, 17, 203, 15, 165, 15, 0, 8, 0};
		gridBagLayout.rowHeights = new int[]{0, 166, 11, 68, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmCatfish.getContentPane().setLayout(gridBagLayout);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.gridheight = 4;
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 0;
		frmCatfish.getContentPane().add(separator_3, gbc_separator_3);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 0;
		frmCatfish.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{43};
		gbl_panel.rowHeights = new int[]{14, 10, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblArduino = new JLabel("Arduino");
		lblArduino.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblArduino = new GridBagConstraints();
		gbc_lblArduino.insets = new Insets(0, 0, 5, 0);
		gbc_lblArduino.anchor = GridBagConstraints.NORTH;
		gbc_lblArduino.gridx = 0;
		gbc_lblArduino.gridy = 0;
		panel.add(lblArduino, gbc_lblArduino);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 1;
		panel.add(separator_1, gbc_separator_1);
		
		JLabel lblDigitalOutputs = new JLabel("Digital Outputs");
		GridBagConstraints gbc_lblDigitalOutputs = new GridBagConstraints();
		gbc_lblDigitalOutputs.insets = new Insets(0, 0, 5, 0);
		gbc_lblDigitalOutputs.gridx = 0;
		gbc_lblDigitalOutputs.gridy = 2;
		panel.add(lblDigitalOutputs, gbc_lblDigitalOutputs);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{35, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JToggleButton panelDO0_tb = new JToggleButton("0");
		GridBagConstraints gbc_panelDO0_tb = new GridBagConstraints();
		gbc_panelDO0_tb.insets = new Insets(0, 0, 5, 5);
		gbc_panelDO0_tb.gridx = 0;
		gbc_panelDO0_tb.gridy = 0;
		panel_1.add(panelDO0_tb, gbc_panelDO0_tb);
		
		JToggleButton panelDO1_tb = new JToggleButton("1");
		GridBagConstraints gbc_panelDO1_tb = new GridBagConstraints();
		gbc_panelDO1_tb.insets = new Insets(0, 0, 5, 5);
		gbc_panelDO1_tb.gridx = 1;
		gbc_panelDO1_tb.gridy = 0;
		panel_1.add(panelDO1_tb, gbc_panelDO1_tb);
		
		JToggleButton panelDO2_tb = new JToggleButton("2");
		GridBagConstraints gbc_panelDO2_tb = new GridBagConstraints();
		gbc_panelDO2_tb.insets = new Insets(0, 0, 5, 5);
		gbc_panelDO2_tb.gridx = 2;
		gbc_panelDO2_tb.gridy = 0;
		panel_1.add(panelDO2_tb, gbc_panelDO2_tb);
		
		JToggleButton panelDO3_tb = new JToggleButton("3");
		GridBagConstraints gbc_panelDO3_tb = new GridBagConstraints();
		gbc_panelDO3_tb.insets = new Insets(0, 0, 5, 5);
		gbc_panelDO3_tb.gridx = 3;
		gbc_panelDO3_tb.gridy = 0;
		panel_1.add(panelDO3_tb, gbc_panelDO3_tb);
		
		JToggleButton panelDO4_tb = new JToggleButton("4");
		GridBagConstraints gbc_panelDO4_tb = new GridBagConstraints();
		gbc_panelDO4_tb.insets = new Insets(0, 0, 5, 5);
		gbc_panelDO4_tb.gridx = 4;
		gbc_panelDO4_tb.gridy = 0;
		panel_1.add(panelDO4_tb, gbc_panelDO4_tb);
		
		JToggleButton panelDO5_tb = new JToggleButton("5");
		GridBagConstraints gbc_panelDO5_tb = new GridBagConstraints();
		gbc_panelDO5_tb.insets = new Insets(0, 0, 0, 5);
		gbc_panelDO5_tb.gridx = 0;
		gbc_panelDO5_tb.gridy = 1;
		panel_1.add(panelDO5_tb, gbc_panelDO5_tb);
		
		JToggleButton panelDO6_tb = new JToggleButton("6");
		GridBagConstraints gbc_panelDO6_tb = new GridBagConstraints();
		gbc_panelDO6_tb.insets = new Insets(0, 0, 0, 5);
		gbc_panelDO6_tb.gridx = 1;
		gbc_panelDO6_tb.gridy = 1;
		panel_1.add(panelDO6_tb, gbc_panelDO6_tb);
		
		JToggleButton panelDO7_tb = new JToggleButton("7");
		GridBagConstraints gbc_panelDO7_tb = new GridBagConstraints();
		gbc_panelDO7_tb.insets = new Insets(0, 0, 0, 5);
		gbc_panelDO7_tb.gridx = 2;
		gbc_panelDO7_tb.gridy = 1;
		panel_1.add(panelDO7_tb, gbc_panelDO7_tb);
		
		JToggleButton panelDO8_tb = new JToggleButton("8");
		GridBagConstraints gbc_panelDO8_tb = new GridBagConstraints();
		gbc_panelDO8_tb.insets = new Insets(0, 0, 0, 5);
		gbc_panelDO8_tb.gridx = 3;
		gbc_panelDO8_tb.gridy = 1;
		panel_1.add(panelDO8_tb, gbc_panelDO8_tb);
		
		JToggleButton panelDO9_tb = new JToggleButton("9");
		GridBagConstraints gbc_panelDO9_tb = new GridBagConstraints();
		gbc_panelDO9_tb.insets = new Insets(0, 0, 0, 5);
		gbc_panelDO9_tb.gridx = 4;
		gbc_panelDO9_tb.gridy = 1;
		panel_1.add(panelDO9_tb, gbc_panelDO9_tb);
		
		JLabel lblDigitalInputs = new JLabel("Digital Inputs");
		GridBagConstraints gbc_lblDigitalInputs = new GridBagConstraints();
		gbc_lblDigitalInputs.insets = new Insets(0, 0, 5, 0);
		gbc_lblDigitalInputs.gridx = 0;
		gbc_lblDigitalInputs.gridy = 4;
		panel.add(lblDigitalInputs, gbc_lblDigitalInputs);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 5;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel panelDI10_lbl = new JLabel("10");
		panelDI10_lbl.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_panelDI10_lbl = new GridBagConstraints();
		gbc_panelDI10_lbl.insets = new Insets(0, 0, 0, 5);
		gbc_panelDI10_lbl.gridx = 0;
		gbc_panelDI10_lbl.gridy = 0;
		panel_2.add(panelDI10_lbl, gbc_panelDI10_lbl);
		
		JLabel panelDI11_lbl = new JLabel("11");
		panelDI11_lbl.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_panelDI11_lbl = new GridBagConstraints();
		gbc_panelDI11_lbl.insets = new Insets(0, 0, 0, 5);
		gbc_panelDI11_lbl.gridx = 1;
		gbc_panelDI11_lbl.gridy = 0;
		panel_2.add(panelDI11_lbl, gbc_panelDI11_lbl);
		
		JLabel panelDI12_lbl = new JLabel("12");
		panelDI12_lbl.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_panelDI12_lbl = new GridBagConstraints();
		gbc_panelDI12_lbl.insets = new Insets(0, 0, 0, 5);
		gbc_panelDI12_lbl.gridx = 2;
		gbc_panelDI12_lbl.gridy = 0;
		panel_2.add(panelDI12_lbl, gbc_panelDI12_lbl);
		
		JLabel panelDI13_lbl = new JLabel("13");
		panelDI13_lbl.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_panelDI13_lbl = new GridBagConstraints();
		gbc_panelDI13_lbl.gridx = 3;
		gbc_panelDI13_lbl.gridy = 0;
		panel_2.add(panelDI13_lbl, gbc_panelDI13_lbl);
		
		JLabel lblAnalogInputs = new JLabel("Analog Inputs");
		GridBagConstraints gbc_lblAnalogInputs = new GridBagConstraints();
		gbc_lblAnalogInputs.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnalogInputs.gridx = 0;
		gbc_lblAnalogInputs.gridy = 6;
		panel.add(lblAnalogInputs, gbc_lblAnalogInputs);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 7;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblA = new JLabel("A0:");
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.anchor = GridBagConstraints.EAST;
		gbc_lblA.gridx = 0;
		gbc_lblA.gridy = 0;
		panel_3.add(lblA, gbc_lblA);
		
		panelA0_t = new JTextField();
		panelA0_t.setEditable(false);
		GridBagConstraints gbc_panelA0_t = new GridBagConstraints();
		gbc_panelA0_t.insets = new Insets(0, 0, 5, 5);
		gbc_panelA0_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelA0_t.gridx = 1;
		gbc_panelA0_t.gridy = 0;
		panel_3.add(panelA0_t, gbc_panelA0_t);
		panelA0_t.setColumns(10);
		
		JLabel lblA_2 = new JLabel("A1:");
		GridBagConstraints gbc_lblA_2 = new GridBagConstraints();
		gbc_lblA_2.anchor = GridBagConstraints.EAST;
		gbc_lblA_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblA_2.gridx = 2;
		gbc_lblA_2.gridy = 0;
		panel_3.add(lblA_2, gbc_lblA_2);
		
		panelA1_t = new JTextField();
		panelA1_t.setEditable(false);
		GridBagConstraints gbc_panelA1_t = new GridBagConstraints();
		gbc_panelA1_t.insets = new Insets(0, 0, 5, 0);
		gbc_panelA1_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelA1_t.gridx = 3;
		gbc_panelA1_t.gridy = 0;
		panel_3.add(panelA1_t, gbc_panelA1_t);
		panelA1_t.setColumns(10);
		
		JLabel lblA_1 = new JLabel("A2:");
		GridBagConstraints gbc_lblA_1 = new GridBagConstraints();
		gbc_lblA_1.anchor = GridBagConstraints.EAST;
		gbc_lblA_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblA_1.gridx = 0;
		gbc_lblA_1.gridy = 1;
		panel_3.add(lblA_1, gbc_lblA_1);
		
		panelA2_t = new JTextField();
		panelA2_t.setEditable(false);
		GridBagConstraints gbc_panelA2_t = new GridBagConstraints();
		gbc_panelA2_t.insets = new Insets(0, 0, 5, 5);
		gbc_panelA2_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelA2_t.gridx = 1;
		gbc_panelA2_t.gridy = 1;
		panel_3.add(panelA2_t, gbc_panelA2_t);
		panelA2_t.setColumns(10);
		
		JLabel lblA_3 = new JLabel("A3:");
		GridBagConstraints gbc_lblA_3 = new GridBagConstraints();
		gbc_lblA_3.anchor = GridBagConstraints.EAST;
		gbc_lblA_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblA_3.gridx = 2;
		gbc_lblA_3.gridy = 1;
		panel_3.add(lblA_3, gbc_lblA_3);
		
		panelA3_t = new JTextField();
		panelA3_t.setEditable(false);
		GridBagConstraints gbc_panelA3_t = new GridBagConstraints();
		gbc_panelA3_t.insets = new Insets(0, 0, 5, 0);
		gbc_panelA3_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelA3_t.gridx = 3;
		gbc_panelA3_t.gridy = 1;
		panel_3.add(panelA3_t, gbc_panelA3_t);
		panelA3_t.setColumns(10);
		
		JLabel lblA_4 = new JLabel("A4:");
		GridBagConstraints gbc_lblA_4 = new GridBagConstraints();
		gbc_lblA_4.anchor = GridBagConstraints.EAST;
		gbc_lblA_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblA_4.gridx = 0;
		gbc_lblA_4.gridy = 2;
		panel_3.add(lblA_4, gbc_lblA_4);
		
		panelA4_t = new JTextField();
		panelA4_t.setEditable(false);
		GridBagConstraints gbc_panelA4_t = new GridBagConstraints();
		gbc_panelA4_t.insets = new Insets(0, 0, 0, 5);
		gbc_panelA4_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelA4_t.gridx = 1;
		gbc_panelA4_t.gridy = 2;
		panel_3.add(panelA4_t, gbc_panelA4_t);
		panelA4_t.setColumns(10);
		
		JLabel lblA_5 = new JLabel("A5:");
		GridBagConstraints gbc_lblA_5 = new GridBagConstraints();
		gbc_lblA_5.anchor = GridBagConstraints.EAST;
		gbc_lblA_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblA_5.gridx = 2;
		gbc_lblA_5.gridy = 2;
		panel_3.add(lblA_5, gbc_lblA_5);
		
		panelA5_t = new JTextField();
		panelA5_t.setEditable(false);
		GridBagConstraints gbc_panelA5_t = new GridBagConstraints();
		gbc_panelA5_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelA5_t.gridx = 3;
		gbc_panelA5_t.gridy = 2;
		panel_3.add(panelA5_t, gbc_panelA5_t);
		panelA5_t.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 4;
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 4;
		gbc_separator.gridy = 0;
		frmCatfish.getContentPane().add(separator, gbc_separator);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridheight = 4;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 5;
		gbc_panel_4.gridy = 0;
		frmCatfish.getContentPane().add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{44, 84, 0};
		gbl_panel_4.rowHeights = new int[]{14, 10, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblCompass = new JLabel("Compass");
		lblCompass.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblCompass = new GridBagConstraints();
		gbc_lblCompass.gridwidth = 2;
		gbc_lblCompass.insets = new Insets(0, 0, 5, 0);
		gbc_lblCompass.gridx = 0;
		gbc_lblCompass.gridy = 0;
		panel_4.add(lblCompass, gbc_lblCompass);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 1;
		panel_4.add(separator_2, gbc_separator_2);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 2;
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 2;
		panel_4.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{60, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblHeading = new JLabel("Heading:");
		GridBagConstraints gbc_lblHeading = new GridBagConstraints();
		gbc_lblHeading.anchor = GridBagConstraints.EAST;
		gbc_lblHeading.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeading.gridx = 0;
		gbc_lblHeading.gridy = 0;
		panel_5.add(lblHeading, gbc_lblHeading);
		
		panelHeading_t = new JTextField();
		panelHeading_t.setEditable(false);
		GridBagConstraints gbc_panelHeading_t = new GridBagConstraints();
		gbc_panelHeading_t.insets = new Insets(0, 0, 5, 0);
		gbc_panelHeading_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelHeading_t.gridx = 1;
		gbc_panelHeading_t.gridy = 0;
		panel_5.add(panelHeading_t, gbc_panelHeading_t);
		panelHeading_t.setColumns(10);
		
		JLabel lblPitch = new JLabel("Pitch:");
		GridBagConstraints gbc_lblPitch = new GridBagConstraints();
		gbc_lblPitch.anchor = GridBagConstraints.EAST;
		gbc_lblPitch.insets = new Insets(0, 0, 5, 5);
		gbc_lblPitch.gridx = 0;
		gbc_lblPitch.gridy = 1;
		panel_5.add(lblPitch, gbc_lblPitch);
		
		panelPitch_t = new JTextField();
		panelPitch_t.setEditable(false);
		GridBagConstraints gbc_panelPitch_t = new GridBagConstraints();
		gbc_panelPitch_t.insets = new Insets(0, 0, 5, 0);
		gbc_panelPitch_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPitch_t.gridx = 1;
		gbc_panelPitch_t.gridy = 1;
		panel_5.add(panelPitch_t, gbc_panelPitch_t);
		panelPitch_t.setColumns(10);
		
		JLabel lblRoll = new JLabel("Roll:");
		GridBagConstraints gbc_lblRoll = new GridBagConstraints();
		gbc_lblRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll.anchor = GridBagConstraints.EAST;
		gbc_lblRoll.gridx = 0;
		gbc_lblRoll.gridy = 2;
		panel_5.add(lblRoll, gbc_lblRoll);
		
		panelRoll_t = new JTextField();
		panelRoll_t.setEditable(false);
		GridBagConstraints gbc_panelRoll_t = new GridBagConstraints();
		gbc_panelRoll_t.insets = new Insets(0, 0, 5, 0);
		gbc_panelRoll_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelRoll_t.gridx = 1;
		gbc_panelRoll_t.gridy = 2;
		panel_5.add(panelRoll_t, gbc_panelRoll_t);
		panelRoll_t.setColumns(10);
		
		JLabel lblAccelX = new JLabel("Accel X:");
		GridBagConstraints gbc_lblAccelX = new GridBagConstraints();
		gbc_lblAccelX.anchor = GridBagConstraints.EAST;
		gbc_lblAccelX.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccelX.gridx = 0;
		gbc_lblAccelX.gridy = 3;
		panel_5.add(lblAccelX, gbc_lblAccelX);
		
		panelAccX_t = new JTextField();
		panelAccX_t.setEditable(false);
		GridBagConstraints gbc_panelAccX_t = new GridBagConstraints();
		gbc_panelAccX_t.insets = new Insets(0, 0, 5, 0);
		gbc_panelAccX_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelAccX_t.gridx = 1;
		gbc_panelAccX_t.gridy = 3;
		panel_5.add(panelAccX_t, gbc_panelAccX_t);
		panelAccX_t.setColumns(10);
		
		JLabel lblAccelY = new JLabel("Accel Y:");
		GridBagConstraints gbc_lblAccelY = new GridBagConstraints();
		gbc_lblAccelY.anchor = GridBagConstraints.EAST;
		gbc_lblAccelY.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccelY.gridx = 0;
		gbc_lblAccelY.gridy = 4;
		panel_5.add(lblAccelY, gbc_lblAccelY);
		
		panelAccY_t = new JTextField();
		panelAccY_t.setEditable(false);
		GridBagConstraints gbc_panelAccY_t = new GridBagConstraints();
		gbc_panelAccY_t.insets = new Insets(0, 0, 5, 0);
		gbc_panelAccY_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelAccY_t.gridx = 1;
		gbc_panelAccY_t.gridy = 4;
		panel_5.add(panelAccY_t, gbc_panelAccY_t);
		panelAccY_t.setColumns(10);
		
		JLabel lblAccelZ = new JLabel("Accel Z:");
		GridBagConstraints gbc_lblAccelZ = new GridBagConstraints();
		gbc_lblAccelZ.anchor = GridBagConstraints.EAST;
		gbc_lblAccelZ.insets = new Insets(0, 0, 0, 5);
		gbc_lblAccelZ.gridx = 0;
		gbc_lblAccelZ.gridy = 5;
		panel_5.add(lblAccelZ, gbc_lblAccelZ);
		
		panelAccZ_t = new JTextField();
		panelAccZ_t.setEditable(false);
		GridBagConstraints gbc_panelAccZ_t = new GridBagConstraints();
		gbc_panelAccZ_t.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelAccZ_t.gridx = 1;
		gbc_panelAccZ_t.gridy = 5;
		panel_5.add(panelAccZ_t, gbc_panelAccZ_t);
		panelAccZ_t.setColumns(10);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(SystemColor.desktop);
		separator_4.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.gridheight = 4;
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.fill = GridBagConstraints.VERTICAL;
		gbc_separator_4.gridx = 6;
		gbc_separator_4.gridy = 0;
		frmCatfish.getContentPane().add(separator_4, gbc_separator_4);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridheight = 2;
		gbc_panel_9.insets = new Insets(0, 0, 5, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 1;
		gbc_panel_9.gridy = 0;
		frmCatfish.getContentPane().add(panel_9, gbc_panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{42, 74, 79, 0, 32, 0};
		gbl_panel_9.rowHeights = new int[]{0, 10, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);
		
		JLabel lblPortsAndConnections = new JLabel("Ports and Connections");
		lblPortsAndConnections.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPortsAndConnections = new GridBagConstraints();
		gbc_lblPortsAndConnections.gridwidth = 5;
		gbc_lblPortsAndConnections.insets = new Insets(0, 0, 5, 0);
		gbc_lblPortsAndConnections.gridx = 0;
		gbc_lblPortsAndConnections.gridy = 0;
		panel_9.add(lblPortsAndConnections, gbc_lblPortsAndConnections);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_8 = new GridBagConstraints();
		gbc_separator_8.gridwidth = 5;
		gbc_separator_8.insets = new Insets(0, 0, 5, 0);
		gbc_separator_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_8.gridx = 0;
		gbc_separator_8.gridy = 1;
		panel_9.add(separator_8, gbc_separator_8);
		
		JLabel lblDevice = new JLabel("Device");
		lblDevice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDevice.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblDevice = new GridBagConstraints();
		gbc_lblDevice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDevice.insets = new Insets(0, 0, 5, 5);
		gbc_lblDevice.gridx = 0;
		gbc_lblDevice.gridy = 2;
		panel_9.add(lblDevice, gbc_lblDevice);
		
		JLabel lblPort_3 = new JLabel("Port");
		lblPort_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblPort_3 = new GridBagConstraints();
		gbc_lblPort_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPort_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort_3.gridx = 1;
		gbc_lblPort_3.gridy = 2;
		panel_9.add(lblPort_3, gbc_lblPort_3);
		
		JLabel lblBaud = new JLabel("Baud");
		lblBaud.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaud.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblBaud = new GridBagConstraints();
		gbc_lblBaud.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBaud.insets = new Insets(0, 0, 5, 5);
		gbc_lblBaud.gridx = 2;
		gbc_lblBaud.gridy = 2;
		panel_9.add(lblBaud, gbc_lblBaud);
		
		JLabel lblConnect = new JLabel("Connect");
		lblConnect.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnect.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblConnect = new GridBagConstraints();
		gbc_lblConnect.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConnect.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnect.gridx = 3;
		gbc_lblConnect.gridy = 2;
		panel_9.add(lblConnect, gbc_lblConnect);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.gridx = 4;
		gbc_lblStatus.gridy = 2;
		panel_9.add(lblStatus, gbc_lblStatus);
		
		JLabel lblJoystick_1 = new JLabel("Joystick");
		GridBagConstraints gbc_lblJoystick_1 = new GridBagConstraints();
		gbc_lblJoystick_1.anchor = GridBagConstraints.EAST;
		gbc_lblJoystick_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblJoystick_1.gridx = 0;
		gbc_lblJoystick_1.gridy = 3;
		panel_9.add(lblJoystick_1, gbc_lblJoystick_1);
		
		panelPortJoy_cb = new JComboBox<String>();
		GridBagConstraints gbc_panelPortJoy_cb = new GridBagConstraints();
		gbc_panelPortJoy_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelPortJoy_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPortJoy_cb.gridx = 1;
		gbc_panelPortJoy_cb.gridy = 3;
		panel_9.add(panelPortJoy_cb, gbc_panelPortJoy_cb);
		
		panelBaudJoy_cb = new JComboBox<String>();
		panelBaudJoy_cb.setModel(new DefaultComboBoxModel<String>(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		panelBaudJoy_cb.setSelectedIndex(2);
		GridBagConstraints gbc_panelBaudJoy_cb = new GridBagConstraints();
		gbc_panelBaudJoy_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelBaudJoy_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBaudJoy_cb.gridx = 2;
		gbc_panelBaudJoy_cb.gridy = 3;
		panel_9.add(panelBaudJoy_cb, gbc_panelBaudJoy_cb);
		
		panelConnJoy_b = new JButton("Connect");
		GridBagConstraints gbc_panelConnJoy_b = new GridBagConstraints();
		gbc_panelConnJoy_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelConnJoy_b.insets = new Insets(0, 0, 5, 5);
		gbc_panelConnJoy_b.gridx = 3;
		gbc_panelConnJoy_b.gridy = 3;
		panel_9.add(panelConnJoy_b, gbc_panelConnJoy_b);
		
		panelStatusJoy_chk = new JCheckBox("");
		panelStatusJoy_chk.setEnabled(false);
		GridBagConstraints gbc_panelStatusJoy_chk = new GridBagConstraints();
		gbc_panelStatusJoy_chk.insets = new Insets(0, 0, 5, 0);
		gbc_panelStatusJoy_chk.gridx = 4;
		gbc_panelStatusJoy_chk.gridy = 3;
		panel_9.add(panelStatusJoy_chk, gbc_panelStatusJoy_chk);
		
		JLabel lblArduino_1 = new JLabel("Arduino");
		GridBagConstraints gbc_lblArduino_1 = new GridBagConstraints();
		gbc_lblArduino_1.anchor = GridBagConstraints.EAST;
		gbc_lblArduino_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblArduino_1.gridx = 0;
		gbc_lblArduino_1.gridy = 4;
		panel_9.add(lblArduino_1, gbc_lblArduino_1);
		
		panelPortArd_cb = new JComboBox<String>();
		GridBagConstraints gbc_panelPortArd_cb = new GridBagConstraints();
		gbc_panelPortArd_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelPortArd_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPortArd_cb.gridx = 1;
		gbc_panelPortArd_cb.gridy = 4;
		panel_9.add(panelPortArd_cb, gbc_panelPortArd_cb);
		
		panelBaudArd_cb = new JComboBox<String>();
		panelBaudArd_cb.setModel(new DefaultComboBoxModel<String>(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		panelBaudArd_cb.setSelectedIndex(2);
		GridBagConstraints gbc_panelBaudArd_cb = new GridBagConstraints();
		gbc_panelBaudArd_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelBaudArd_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBaudArd_cb.gridx = 2;
		gbc_panelBaudArd_cb.gridy = 4;
		panel_9.add(panelBaudArd_cb, gbc_panelBaudArd_cb);
		
		panelConnArd_B = new JButton("Connect");
		GridBagConstraints gbc_panelConnArd_B = new GridBagConstraints();
		gbc_panelConnArd_B.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelConnArd_B.insets = new Insets(0, 0, 5, 5);
		gbc_panelConnArd_B.gridx = 3;
		gbc_panelConnArd_B.gridy = 4;
		panel_9.add(panelConnArd_B, gbc_panelConnArd_B);
		
		panelStatusArd_chk = new JCheckBox("");
		panelStatusArd_chk.setEnabled(false);
		GridBagConstraints gbc_panelStatusArd_chk = new GridBagConstraints();
		gbc_panelStatusArd_chk.insets = new Insets(0, 0, 5, 0);
		gbc_panelStatusArd_chk.gridx = 4;
		gbc_panelStatusArd_chk.gridy = 4;
		panel_9.add(panelStatusArd_chk, gbc_panelStatusArd_chk);
		
		JLabel lblCompass_1 = new JLabel("Compass");
		GridBagConstraints gbc_lblCompass_1 = new GridBagConstraints();
		gbc_lblCompass_1.anchor = GridBagConstraints.EAST;
		gbc_lblCompass_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompass_1.gridx = 0;
		gbc_lblCompass_1.gridy = 5;
		panel_9.add(lblCompass_1, gbc_lblCompass_1);
		
		panelPortComp_cb = new JComboBox<String>();
		GridBagConstraints gbc_panelPortComp_cb = new GridBagConstraints();
		gbc_panelPortComp_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelPortComp_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPortComp_cb.gridx = 1;
		gbc_panelPortComp_cb.gridy = 5;
		panel_9.add(panelPortComp_cb, gbc_panelPortComp_cb);
		
		panelBaudComp_cb = new JComboBox<String>();
		panelBaudComp_cb.setModel(new DefaultComboBoxModel<String>(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		panelBaudComp_cb.setSelectedIndex(4);
		GridBagConstraints gbc_panelBaudComp_cb = new GridBagConstraints();
		gbc_panelBaudComp_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelBaudComp_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBaudComp_cb.gridx = 2;
		gbc_panelBaudComp_cb.gridy = 5;
		panel_9.add(panelBaudComp_cb, gbc_panelBaudComp_cb);
		
		panelConnComp_b = new JButton("Connect");
		GridBagConstraints gbc_panelConnComp_b = new GridBagConstraints();
		gbc_panelConnComp_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelConnComp_b.insets = new Insets(0, 0, 5, 5);
		gbc_panelConnComp_b.gridx = 3;
		gbc_panelConnComp_b.gridy = 5;
		panel_9.add(panelConnComp_b, gbc_panelConnComp_b);
		
		panelStatusComp_chk = new JCheckBox("");
		panelStatusComp_chk.setEnabled(false);
		GridBagConstraints gbc_panelStatusComp_chk = new GridBagConstraints();
		gbc_panelStatusComp_chk.insets = new Insets(0, 0, 5, 0);
		gbc_panelStatusComp_chk.gridx = 4;
		gbc_panelStatusComp_chk.gridy = 5;
		panel_9.add(panelStatusComp_chk, gbc_panelStatusComp_chk);
		
		JLabel lblMotors_1 = new JLabel("Motors");
		GridBagConstraints gbc_lblMotors_1 = new GridBagConstraints();
		gbc_lblMotors_1.anchor = GridBagConstraints.EAST;
		gbc_lblMotors_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotors_1.gridx = 0;
		gbc_lblMotors_1.gridy = 6;
		panel_9.add(lblMotors_1, gbc_lblMotors_1);
		
		panelPortMotors_cb = new JComboBox<String>();
		GridBagConstraints gbc_panelPortMotors_cb = new GridBagConstraints();
		gbc_panelPortMotors_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelPortMotors_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPortMotors_cb.gridx = 1;
		gbc_panelPortMotors_cb.gridy = 6;
		panel_9.add(panelPortMotors_cb, gbc_panelPortMotors_cb);
		
		panelBaudMotors_cb = new JComboBox<String>();
		panelBaudMotors_cb.setModel(new DefaultComboBoxModel<String>(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		panelBaudMotors_cb.setSelectedIndex(2);
		GridBagConstraints gbc_panelBaudMotors_cb = new GridBagConstraints();
		gbc_panelBaudMotors_cb.insets = new Insets(0, 0, 5, 5);
		gbc_panelBaudMotors_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBaudMotors_cb.gridx = 2;
		gbc_panelBaudMotors_cb.gridy = 6;
		panel_9.add(panelBaudMotors_cb, gbc_panelBaudMotors_cb);
		
		panelConnMotors_b = new JButton("Connect");
		GridBagConstraints gbc_panelConnMotors_b = new GridBagConstraints();
		gbc_panelConnMotors_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelConnMotors_b.insets = new Insets(0, 0, 5, 5);
		gbc_panelConnMotors_b.gridx = 3;
		gbc_panelConnMotors_b.gridy = 6;
		panel_9.add(panelConnMotors_b, gbc_panelConnMotors_b);
		
		panelStatusMotors_chk = new JCheckBox("");
		panelStatusMotors_chk.setEnabled(false);
		GridBagConstraints gbc_panelStatusMotors_chk = new GridBagConstraints();
		gbc_panelStatusMotors_chk.insets = new Insets(0, 0, 5, 0);
		gbc_panelStatusMotors_chk.gridx = 4;
		gbc_panelStatusMotors_chk.gridy = 6;
		panel_9.add(panelStatusMotors_chk, gbc_panelStatusMotors_chk);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.gridwidth = 5;
		gbc_panel_10.insets = new Insets(0, 0, 0, 5);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 7;
		panel_9.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 94, 76, 0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0, 0};
		gbl_panel_10.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.gridheight = 2;
		gbc_horizontalGlue.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 0;
		panel_10.add(horizontalGlue, gbc_horizontalGlue);
		
		JButton panelRefreshPorts_b = new JButton("Refresh Ports");
		GridBagConstraints gbc_panelRefreshPorts_b = new GridBagConstraints();
		gbc_panelRefreshPorts_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelRefreshPorts_b.insets = new Insets(0, 0, 5, 5);
		gbc_panelRefreshPorts_b.gridx = 1;
		gbc_panelRefreshPorts_b.gridy = 0;
		panel_10.add(panelRefreshPorts_b, gbc_panelRefreshPorts_b);
		
		JButton panelConnAll_b = new JButton("Connect All");
		GridBagConstraints gbc_panelConnAll_b = new GridBagConstraints();
		gbc_panelConnAll_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelConnAll_b.insets = new Insets(0, 0, 5, 5);
		gbc_panelConnAll_b.gridx = 2;
		gbc_panelConnAll_b.gridy = 0;
		panel_10.add(panelConnAll_b, gbc_panelConnAll_b);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.gridheight = 2;
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue_1.gridx = 3;
		gbc_horizontalGlue_1.gridy = 0;
		panel_10.add(horizontalGlue_1, gbc_horizontalGlue_1);
		
		JButton panelSaveConfig_b = new JButton("Save Config");
		GridBagConstraints gbc_panelSaveConfig_b = new GridBagConstraints();
		gbc_panelSaveConfig_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelSaveConfig_b.insets = new Insets(0, 0, 0, 5);
		gbc_panelSaveConfig_b.gridx = 1;
		gbc_panelSaveConfig_b.gridy = 1;
		panel_10.add(panelSaveConfig_b, gbc_panelSaveConfig_b);
		
		JButton panelLoadConfig_b = new JButton("Load Config");
		GridBagConstraints gbc_panelLoadConfig_b = new GridBagConstraints();
		gbc_panelLoadConfig_b.insets = new Insets(0, 0, 0, 5);
		gbc_panelLoadConfig_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelLoadConfig_b.gridx = 2;
		gbc_panelLoadConfig_b.gridy = 1;
		panel_10.add(panelLoadConfig_b, gbc_panelLoadConfig_b);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setForeground(SystemColor.desktop);
		separator_9.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_9 = new GridBagConstraints();
		gbc_separator_9.fill = GridBagConstraints.VERTICAL;
		gbc_separator_9.gridheight = 4;
		gbc_separator_9.insets = new Insets(0, 0, 5, 5);
		gbc_separator_9.gridx = 2;
		gbc_separator_9.gridy = 0;
		frmCatfish.getContentPane().add(separator_9, gbc_separator_9);
		
		JSeparator separator_10 = new JSeparator();
		GridBagConstraints gbc_separator_10 = new GridBagConstraints();
		gbc_separator_10.gridheight = 4;
		gbc_separator_10.insets = new Insets(0, 0, 5, 0);
		gbc_separator_10.gridx = 8;
		gbc_separator_10.gridy = 0;
		frmCatfish.getContentPane().add(separator_10, gbc_separator_10);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 1;
		gbc_separator_6.gridy = 2;
		frmCatfish.getContentPane().add(separator_6, gbc_separator_6);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 3;
		frmCatfish.getContentPane().add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{43, 0};
		gbl_panel_6.rowHeights = new int[]{0, 10, 0, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblJoystick = new JLabel("Joystick");
		lblJoystick.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblJoystick = new GridBagConstraints();
		gbc_lblJoystick.insets = new Insets(0, 0, 5, 0);
		gbc_lblJoystick.gridx = 0;
		gbc_lblJoystick.gridy = 0;
		panel_6.add(lblJoystick, gbc_lblJoystick);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_5.gridx = 0;
		gbc_separator_5.gridy = 1;
		panel_6.add(separator_5, gbc_separator_5);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 2;
		panel_6.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0};
		gbl_panel_7.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_2.gridx = 0;
		gbc_horizontalGlue_2.gridy = 0;
		panel_7.add(horizontalGlue_2, gbc_horizontalGlue_2);
		
		JButton panelViewJoyInputs_b = new JButton("View Inputs");
		GridBagConstraints gbc_panelViewJoyInputs_b = new GridBagConstraints();
		gbc_panelViewJoyInputs_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelViewJoyInputs_b.insets = new Insets(0, 0, 0, 5);
		gbc_panelViewJoyInputs_b.gridx = 1;
		gbc_panelViewJoyInputs_b.gridy = 0;
		panel_7.add(panelViewJoyInputs_b, gbc_panelViewJoyInputs_b);
		
		JButton panelConfigJoy_b = new JButton("Configure...");
		GridBagConstraints gbc_panelConfigJoy_b = new GridBagConstraints();
		gbc_panelConfigJoy_b.insets = new Insets(0, 0, 0, 5);
		gbc_panelConfigJoy_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelConfigJoy_b.gridx = 2;
		gbc_panelConfigJoy_b.gridy = 0;
		panel_7.add(panelConfigJoy_b, gbc_panelConfigJoy_b);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_3 = new GridBagConstraints();
		gbc_horizontalGlue_3.gridx = 3;
		gbc_horizontalGlue_3.gridy = 0;
		panel_7.add(horizontalGlue_3, gbc_horizontalGlue_3);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 5);
		gbc_panel_8.gridheight = 4;
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 7;
		gbc_panel_8.gridy = 0;
		frmCatfish.getContentPane().add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 10, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_8.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JLabel lblMotors = new JLabel("Motors");
		lblMotors.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblMotors = new GridBagConstraints();
		gbc_lblMotors.insets = new Insets(0, 0, 5, 0);
		gbc_lblMotors.gridwidth = 2;
		gbc_lblMotors.gridx = 0;
		gbc_lblMotors.gridy = 0;
		panel_8.add(lblMotors, gbc_lblMotors);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_7 = new GridBagConstraints();
		gbc_separator_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_7.gridwidth = 2;
		gbc_separator_7.insets = new Insets(0, 0, 5, 0);
		gbc_separator_7.gridx = 0;
		gbc_separator_7.gridy = 1;
		panel_8.add(separator_7, gbc_separator_7);
		
		JLabel lblVerticalLeft = new JLabel("Vertical Left");
		GridBagConstraints gbc_lblVerticalLeft = new GridBagConstraints();
		gbc_lblVerticalLeft.anchor = GridBagConstraints.WEST;
		gbc_lblVerticalLeft.gridwidth = 2;
		gbc_lblVerticalLeft.insets = new Insets(0, 0, 5, 0);
		gbc_lblVerticalLeft.gridx = 0;
		gbc_lblVerticalLeft.gridy = 2;
		panel_8.add(lblVerticalLeft, gbc_lblVerticalLeft);
		
		JProgressBar panelVL_pb = new JProgressBar();
		panelVL_pb.setValue(50);
		GridBagConstraints gbc_panelVL_pb = new GridBagConstraints();
		gbc_panelVL_pb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelVL_pb.gridwidth = 2;
		gbc_panelVL_pb.insets = new Insets(0, 0, 5, 0);
		gbc_panelVL_pb.gridx = 0;
		gbc_panelVL_pb.gridy = 3;
		panel_8.add(panelVL_pb, gbc_panelVL_pb);
		
		JLabel lblVerticalRight = new JLabel("Vertical Right");
		GridBagConstraints gbc_lblVerticalRight = new GridBagConstraints();
		gbc_lblVerticalRight.anchor = GridBagConstraints.WEST;
		gbc_lblVerticalRight.insets = new Insets(0, 0, 5, 0);
		gbc_lblVerticalRight.gridwidth = 2;
		gbc_lblVerticalRight.gridx = 0;
		gbc_lblVerticalRight.gridy = 4;
		panel_8.add(lblVerticalRight, gbc_lblVerticalRight);
		
		JProgressBar panelVR_pb = new JProgressBar();
		panelVR_pb.setValue(50);
		GridBagConstraints gbc_panelVR_pb = new GridBagConstraints();
		gbc_panelVR_pb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelVR_pb.gridwidth = 2;
		gbc_panelVR_pb.insets = new Insets(0, 0, 5, 0);
		gbc_panelVR_pb.gridx = 0;
		gbc_panelVR_pb.gridy = 5;
		panel_8.add(panelVR_pb, gbc_panelVR_pb);
		
		JLabel lblPortForward = new JLabel("Port Forward");
		GridBagConstraints gbc_lblPortForward = new GridBagConstraints();
		gbc_lblPortForward.anchor = GridBagConstraints.WEST;
		gbc_lblPortForward.gridwidth = 2;
		gbc_lblPortForward.insets = new Insets(0, 0, 5, 0);
		gbc_lblPortForward.gridx = 0;
		gbc_lblPortForward.gridy = 6;
		panel_8.add(lblPortForward, gbc_lblPortForward);
		
		JProgressBar panelPF_pb = new JProgressBar();
		panelPF_pb.setValue(50);
		GridBagConstraints gbc_panelPF_pb = new GridBagConstraints();
		gbc_panelPF_pb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPF_pb.gridwidth = 2;
		gbc_panelPF_pb.insets = new Insets(0, 0, 5, 0);
		gbc_panelPF_pb.gridx = 0;
		gbc_panelPF_pb.gridy = 7;
		panel_8.add(panelPF_pb, gbc_panelPF_pb);
		
		JLabel lblStarboardFront = new JLabel("Starboard Front");
		GridBagConstraints gbc_lblStarboardFront = new GridBagConstraints();
		gbc_lblStarboardFront.anchor = GridBagConstraints.WEST;
		gbc_lblStarboardFront.insets = new Insets(0, 0, 5, 0);
		gbc_lblStarboardFront.gridwidth = 2;
		gbc_lblStarboardFront.gridx = 0;
		gbc_lblStarboardFront.gridy = 8;
		panel_8.add(lblStarboardFront, gbc_lblStarboardFront);
		
		JProgressBar panelSF_pb = new JProgressBar();
		panelSF_pb.setValue(50);
		GridBagConstraints gbc_panelSF_pb = new GridBagConstraints();
		gbc_panelSF_pb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelSF_pb.gridwidth = 2;
		gbc_panelSF_pb.insets = new Insets(0, 0, 5, 0);
		gbc_panelSF_pb.gridx = 0;
		gbc_panelSF_pb.gridy = 9;
		panel_8.add(panelSF_pb, gbc_panelSF_pb);
		
		JLabel lblPortAft = new JLabel("Port Aft");
		GridBagConstraints gbc_lblPortAft = new GridBagConstraints();
		gbc_lblPortAft.anchor = GridBagConstraints.WEST;
		gbc_lblPortAft.gridwidth = 2;
		gbc_lblPortAft.insets = new Insets(0, 0, 5, 0);
		gbc_lblPortAft.gridx = 0;
		gbc_lblPortAft.gridy = 10;
		panel_8.add(lblPortAft, gbc_lblPortAft);
		
		JProgressBar panelPA_pb = new JProgressBar();
		panelPA_pb.setValue(50);
		GridBagConstraints gbc_panelPA_pb = new GridBagConstraints();
		gbc_panelPA_pb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPA_pb.gridwidth = 2;
		gbc_panelPA_pb.insets = new Insets(0, 0, 5, 0);
		gbc_panelPA_pb.gridx = 0;
		gbc_panelPA_pb.gridy = 11;
		panel_8.add(panelPA_pb, gbc_panelPA_pb);
		
		JLabel lblStarboardAft = new JLabel("Starboard Aft");
		GridBagConstraints gbc_lblStarboardAft = new GridBagConstraints();
		gbc_lblStarboardAft.anchor = GridBagConstraints.WEST;
		gbc_lblStarboardAft.gridwidth = 2;
		gbc_lblStarboardAft.insets = new Insets(0, 0, 5, 0);
		gbc_lblStarboardAft.gridx = 0;
		gbc_lblStarboardAft.gridy = 12;
		panel_8.add(lblStarboardAft, gbc_lblStarboardAft);
		
		JProgressBar panelSA_pb = new JProgressBar();
		panelSA_pb.setValue(50);
		GridBagConstraints gbc_panelSA_pb = new GridBagConstraints();
		gbc_panelSA_pb.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelSA_pb.gridwidth = 2;
		gbc_panelSA_pb.gridx = 0;
		gbc_panelSA_pb.gridy = 13;
		panel_8.add(panelSA_pb, gbc_panelSA_pb);
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.gridwidth = 7;
		gbc_panel_11.insets = new Insets(0, 0, 0, 5);
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 1;
		gbc_panel_11.gridy = 4;
		frmCatfish.getContentPane().add(panel_11, gbc_panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{0, 134, 0};
		gbl_panel_11.rowHeights = new int[]{0, 0, 0};
		gbl_panel_11.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);
		
		JLabel lblLoggerOutput = new JLabel("Logger Output");
		GridBagConstraints gbc_lblLoggerOutput = new GridBagConstraints();
		gbc_lblLoggerOutput.anchor = GridBagConstraints.WEST;
		gbc_lblLoggerOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoggerOutput.gridx = 0;
		gbc_lblLoggerOutput.gridy = 0;
		panel_11.add(lblLoggerOutput, gbc_lblLoggerOutput);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_11.add(scrollPane, gbc_scrollPane);
		
		panelLog_ta = new JEditorPane();
		panelLog_ta.setContentType("text/html");
		panelLog_ta.setText("<html><body> </body></html>");
		scrollPane.setViewportView(panelLog_ta);
		panelLog_ta.setFont(new Font("Consolas", Font.PLAIN, 10));
		panelLog_ta.setEditable(false);
		
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 1;
		gbc_panel_12.gridy = 1;
		panel_11.add(panel_12, gbc_panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{0, 0};
		gbl_panel_12.rowHeights = new int[]{0, 0};
		gbl_panel_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);
		
		panelClsOutput_b = new JButton("Clear Output");
		GridBagConstraints gbc_panelClsOutput_b = new GridBagConstraints();
		gbc_panelClsOutput_b.gridx = 0;
		gbc_panelClsOutput_b.gridy = 0;
		panel_12.add(panelClsOutput_b, gbc_panelClsOutput_b);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

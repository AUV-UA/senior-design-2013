package org.auvua.agent;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class AgentPanel implements ActionListener {

	/* Global Java logger reference */
	public static final Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private AgentModel model;
	
	public JFrame frmAuvAgent;
	public JEditorPane panelLog_ta;
	private JButton start_mission;
	private JButton stop_mission;
	private JList filelist;

	/**
	 * Create the application.
	 */
	public AgentPanel() {
		initialize();
		
		// set global logging output stream to textarea
		LOGGER.addHandler(new Handler() {
			public void publish(LogRecord logRecord) {
				StringBuilder msg = new StringBuilder();
				Date time = new Date(System.currentTimeMillis());
				msg.append("<span style=\"color:green;\">" + time.toString()
						+ "</span> - ");
				msg.append(logRecord.getLevel() + ": ");
				msg.append(logRecord.getMessage());
				msg.append(" [<span style=\"color:blue;\">"
						+ logRecord.getSourceClassName() + ":");
				msg.append(logRecord.getSourceMethodName() + "</span>]<br/>");
				panelLog_ta.setText(panelLog_ta.getText().substring(0,
						panelLog_ta.getText().indexOf("</body>"))
						+ msg.toString() + "</body></html>");
				panelLog_ta.scrollToReference("</html>");
			}

			@Override
			public void close() throws SecurityException {
			}

			@Override
			public void flush() {
			}
		});
		
		LOGGER.info("Panel initialized.");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAuvAgent = new JFrame();
		frmAuvAgent.setResizable(false);
		frmAuvAgent.setTitle("AUV Agent");
		frmAuvAgent.setBounds(100, 100, 1000, 300);
		frmAuvAgent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frmAuvAgent.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel.setMinimumSize(new Dimension(800, 125));
		frmAuvAgent.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 0;
		panel.add(separator_2, gbc_separator_2);
		
		JLabel lblMissionFile = new JLabel("Mission File:");
		GridBagConstraints gbc_lblMissionFile = new GridBagConstraints();
		gbc_lblMissionFile.anchor = GridBagConstraints.WEST;
		gbc_lblMissionFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMissionFile.gridx = 1;
		gbc_lblMissionFile.gridy = 1;
		panel.add(lblMissionFile, gbc_lblMissionFile);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		start_mission = new JButton("START MISSION");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 0;
		start_mission.addActionListener(this);
		panel_2.add(start_mission, gbc_btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		stop_mission = new JButton("STOP MISSION");
		GridBagConstraints gbc_btnStopMission = new GridBagConstraints();
		gbc_btnStopMission.fill = GridBagConstraints.BOTH;
		gbc_btnStopMission.gridx = 0;
		gbc_btnStopMission.gridy = 0;
		stop_mission.addActionListener(this);
		panel_3.add(stop_mission, gbc_btnStopMission);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 0, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 2;
		panel.add(separator_1, gbc_separator_1);
		
		filelist = new JList();
		GridBagConstraints gbc_filelist = new GridBagConstraints();
		gbc_filelist.insets = new Insets(0, 0, 0, 5);
		gbc_filelist.fill = GridBagConstraints.BOTH;
		gbc_filelist.gridx = 1;
		gbc_filelist.gridy = 2;
		DefaultListModel lm = new DefaultListModel();
		File path = new File("./missions");
		File[] files = path.listFiles();
		for(File file : files) {
			lm.addElement(file.getName());
		}
		filelist.setModel(lm);
		panel.add(filelist, gbc_filelist);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frmAuvAgent.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Mission Logger");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		panel_1.add(separator, gbc_separator);
		
		panelLog_ta = new JEditorPane();
		panelLog_ta.setEditable(false);
		panelLog_ta.setText("<html><body> </body></html>");
		panelLog_ta.setContentType("text/html");
		GridBagConstraints gbc_panelLog_ta = new GridBagConstraints();
		gbc_panelLog_ta.insets = new Insets(0, 0, 0, 5);
		gbc_panelLog_ta.fill = GridBagConstraints.BOTH;
		gbc_panelLog_ta.gridx = 1;
		gbc_panelLog_ta.gridy = 1;
		JScrollPane jsp = new JScrollPane(panelLog_ta);
		panel_1.add(jsp, gbc_panelLog_ta);
		
		JButton btnNewButton = new JButton("Clear Output");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);
	}
	
	public void setModel(AgentModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(start_mission)) {
			if(filelist.getSelectedIndex() >= 0) {
				String file = (String) filelist.getSelectedValue();
				model.startMission("./missions/" + file);
			}
		} else if(e.getSource().equals(stop_mission)) {
			model.stopMission();
		}
	}
}

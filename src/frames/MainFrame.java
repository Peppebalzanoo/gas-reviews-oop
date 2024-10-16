package frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.Font;
import javax.swing.JComboBox;
import controller.Controller;
import utilities.ScaleImage;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;



public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField searchBarTextField;
	@SuppressWarnings("serial")
	

	
	private DefaultTableModel df = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Immagine", "Nome", "Indirizzo", "Media valutazioni"
			}
		) {
        @Override
        public Class getColumnClass(int column) {
            Class clazz = String.class;
            switch (column) {
                case 0:
                    clazz = Icon.class;
                    break;
            }
            return clazz;
        }
	};
	private DefaultTableModel dfReview = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Recensione", "Valutazione"
			}
		);
	
	private DefaultTableModel dfReviewInReview = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Recensione", "Valutazione"
			}
		);
	
	private  DefaultTableModel dfApprovedReview = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Recensione", "Valutazione", "Approvata da"
			}
		);
	private  DefaultTableModel dfRejectedReview = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Recensione", "Recensione", "Rifiutata da", "Motivazione"
			}
		);
	
	private  DefaultTableModel dfReviewInReviewAdmin = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Username", "Recensione", "Valutazione", "New column"
			}
		);
	
	private  DefaultTableModel dfApprovedReviewAdmin = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Username", "Recensione", "Valutazione"
			}
		);
	
	private  DefaultTableModel dfRejectedReviewAdmin = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Username", "Recensione", "Valutazione", "Motivazione"
			}
		);
	
	
	private JTable resaltTable;
	private JTable reviewTabel;
	private JPanel structurePanel;
	private JLabel insertPhotoLabel;
	private JLabel nameStructureLabel;
	private JPanel profile;
	private ScaleImage scale = new ScaleImage();
	private JLayeredPane layeredPane;
	private JComboBox countryComboBox;
	private JComboBox typesComboBox;
	private JComboBox priceComboBox;
	private JComboBox starComboBox;
	private Integer selectedStructure = 1;
	private JPanel moderatorPanel;
	private JTabbedPane profileTabbedPane;
	private JLabel insertAddrLabel;
	private JLabel insertPriceLabel;
	private JLabel insertStar;
	private JLabel structureReferenceLabel;
	private JComboBox selectRating;
	private JTextArea writeReviewTextArea;
	private JLabel specificationLabel;
	private JLabel typesLable;
	private JPanel homePanel;
	private JTable reviewsApprovedTable;
	private JTable reviewInReviewTable;
	private JTable reviewsRejectedTable;
	private JTable reviewsInReviewAdminTable;
	private JTable reviewsApprovedAdminTable;
	private JTable reviewsRejectedAdminTable;
	private JPanel adminPanel;
	private JButton accomodationBtn;
	private JButton restaurantBtn;
	private JButton attractionBtn;
	private ImageIcon logoIcon;
	private ImageIcon resIcon;
	private ImageIcon resIconSelected;
	private ImageIcon attIcon;
	private ImageIcon attIconSelected;
	private ImageIcon accIcon;
	private ImageIcon accIconSelected;
	private JLabel adminInfoLabel2;
	private JLabel insertUsernameLabelProfile;
	
	
	
    public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

	
	
	public void setColumnSize(JTable tabel, int colonna, int grandezza) {
		tabel.getColumnModel().getColumn(colonna).setWidth(grandezza);
		tabel.getColumnModel().getColumn(colonna).setMinWidth(grandezza);
		tabel.getColumnModel().getColumn(colonna).setMaxWidth(grandezza);
	}
	
	
	public void insertInLabel(ImageIcon photoTab, JLabel jl) {
		ImageIcon img = scale.scaleImg(photoTab, jl.getWidth(), jl.getHeight());
		jl.setIcon(img);
	}
	
	public void updateLayeredPane(JLayeredPane layeredPane, JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	
	
	public DefaultTableModel getModel() {
		return df;
	}
	
	
	public MainFrame(Controller ctrl) {
		setTitle("GAS Reviews");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imgs\\logoIcon.png"));
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {

		        	ctrl.programClosing();
		          
		    }
		});
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(1211, 820);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(getSize());
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setPreferredSize(new Dimension(10, 50));
		contentPane.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton logoHeaderBtn = new JButton("");
		logoHeaderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoHeaderBtn.setContentAreaFilled(false);
		logoHeaderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLayeredPane(layeredPane, homePanel);
				
			}
		});
		
		logoIcon = new ImageIcon("imgs\\GasReviewsLogo.png");
		logoHeaderBtn.setIcon(scale.scaleImg(logoIcon, 221, 45));
		logoHeaderBtn.setBorderPainted(false);
		logoHeaderBtn.setBackground(Color.WHITE);
		logoHeaderBtn.setPreferredSize(new Dimension(221, 45));
		headerPanel.add(logoHeaderBtn);
		ImageIcon searchIcon = new ImageIcon("imgs\\search.png");
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		homePanel = new JPanel();
		layeredPane.add(homePanel, "name_1211808405692600");
		homePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel homeNordPanel = new JPanel();
		homeNordPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		homeNordPanel.setBackground(Color.WHITE);
		homePanel.add(homeNordPanel, BorderLayout.NORTH);
		
		accIcon = new ImageIcon("imgs\\accommodation.png");
		accIconSelected = new ImageIcon("imgs\\accommodation_selected.png");
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(70);
		flowLayout.setHgap(0);
		homeNordPanel.add(panel);
		
		restaurantBtn = new JButton("");
		restaurantBtn.setToolTipText("");
		restaurantBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				restaurantBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				restaurantBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		restaurantBtn.setBorderPainted(false);
		restaurantBtn.setBorder(new LineBorder(Color.BLACK, 3));
		restaurantBtn.setContentAreaFilled(false);
		
		restaurantBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedStructure = 1;
				
				attractionBtn.setIcon(scale.scaleImg(attIcon, 80, 80));
				restaurantBtn.setIcon(scale.scaleImg(resIconSelected, 80, 80));
				accomodationBtn.setIcon(scale.scaleImg(accIcon, 80, 80));
				
				ctrl.populateTypeComboBox(typesComboBox);
				ctrl.populateStructuresTable("", (String)countryComboBox.getSelectedItem(), "", (String)priceComboBox.getSelectedItem(), "", (String)starComboBox.getSelectedItem(), df);
			}
		});
		
		resIcon = new ImageIcon("imgs\\restaurant.png");
		resIconSelected = new ImageIcon("imgs\\restaurant_selected.png");
		restaurantBtn.setIcon(scale.scaleImg(resIcon, 80, 80));
		restaurantBtn.setIcon(scale.scaleImg(resIconSelected, 80, 80));
		restaurantBtn.setPreferredSize(new Dimension(80, 80));
		homeNordPanel.add(restaurantBtn);
		
		attractionBtn = new JButton("");
		attractionBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				attractionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				attractionBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		attractionBtn.setContentAreaFilled(false);
		attractionBtn.setBorderPainted(false);
		attractionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedStructure = 2;
				
				restaurantBtn.setIcon(scale.scaleImg(resIcon, 80, 80));
				attractionBtn.setIcon(scale.scaleImg(attIconSelected, 80, 80));
				accomodationBtn.setIcon(scale.scaleImg(accIcon, 80, 80));

				ctrl.populateTypeComboBox(typesComboBox);
				ctrl.populateStructuresTable("", (String)countryComboBox.getSelectedItem(), "", (String)priceComboBox.getSelectedItem(), "", (String)starComboBox.getSelectedItem(), df);
			}
		});
		
		accomodationBtn = new JButton("");
		accomodationBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				accomodationBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				accomodationBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		accomodationBtn.setContentAreaFilled(false);
		accomodationBtn.setBorderPainted(false);
		accomodationBtn.setBackground(Color.WHITE);
		accomodationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedStructure = 0;
				
				restaurantBtn.setIcon(scale.scaleImg(resIcon, 80, 80));
				attractionBtn.setIcon(scale.scaleImg(attIcon, 80, 80));
				accomodationBtn.setIcon(scale.scaleImg(accIconSelected, 80, 80));
				
				ctrl.populateTypeComboBox(typesComboBox);
				ctrl.populateStructuresTable("", (String)countryComboBox.getSelectedItem(), "", (String)priceComboBox.getSelectedItem(), "", (String)starComboBox.getSelectedItem(), df);
			}
		});
		
		
		JLabel label = new JLabel("                   ");
		homeNordPanel.add(label);
		accomodationBtn.setIcon(scale.scaleImg(accIcon, 80, 80));
		accomodationBtn.setPreferredSize(new Dimension(80, 80));
		homeNordPanel.add(accomodationBtn);
		
		JLabel label_1 = new JLabel("                   ");
		homeNordPanel.add(label_1);
		attractionBtn.setBackground(Color.WHITE);
		attIcon = new ImageIcon("imgs\\attraction.png");
		attIconSelected = new ImageIcon("imgs\\attraction_selected.png");
		attractionBtn.setIcon(scale.scaleImg(attIcon, 80, 80));
		attractionBtn.setPreferredSize(new Dimension(80, 80));
		homeNordPanel.add(attractionBtn);
		
		
	
		
		
		
		JButton openProfileBtn = new JButton("");
		openProfileBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				openProfileBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				openProfileBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		openProfileBtn.setContentAreaFilled(false);
		openProfileBtn.setBorderPainted(false);
		ImageIcon userIcon = new ImageIcon("imgs\\user.png");
		
		JLabel label_2 = new JLabel("                             ");
		homeNordPanel.add(label_2);
		
		searchBarTextField = new JTextField();
		searchBarTextField.setToolTipText("");
		searchBarTextField.setText("Cerca per nome o indirizzo...");
		searchBarTextField.setForeground(Color.GRAY);
		searchBarTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(searchBarTextField.getText().trim().equals("Cerca per nome o indirizzo...")) {
					searchBarTextField.setText("");
					
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(searchBarTextField.getText().trim().equals("") 
						|| searchBarTextField.getText().trim().equals("Cerca per nome o indirizzo...")){
					searchBarTextField.setText("Cerca per nome o indirizzo...");
					searchBarTextField.setForeground(Color.GRAY);
				}
			}
		});
		
		homeNordPanel.add(searchBarTextField);
		searchBarTextField.setFont(new Font("Ubuntu", Font.BOLD, 20));
		searchBarTextField.setColumns(23);
		
		JLabel label_4 = new JLabel("  ");
		homeNordPanel.add(label_4);
		
		JButton btnCerca = new JButton("");
		btnCerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCerca.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCerca.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		homeNordPanel.add(btnCerca);
		btnCerca.setIcon(scale.scaleImg(searchIcon, 40, 40));
		btnCerca.setPreferredSize(new Dimension(40, 40));
		btnCerca.setContentAreaFilled(false);
		btnCerca.setBorderPainted(false);
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				countryComboBox.setSelectedItem("Campania");
				typesComboBox.setSelectedItem("Qualsiasi");
				priceComboBox.setSelectedItem("Qualsiasi");
				starComboBox.setSelectedItem("Qualsiasi");
				
				if(!searchBarTextField.getText().equals("Cerca per nome o indirizzo...")) ctrl.populateStructuresTable(searchBarTextField.getText(), (String)countryComboBox.getSelectedItem(), "", "", searchBarTextField.getText(), "", df); 
				searchBarTextField.setText("Cerca per nome o indirizzo...");
				
			}
		});
		
		JLabel label_3 = new JLabel("                             ");
		homeNordPanel.add(label_3);
		openProfileBtn.setIcon(scale.scaleImg(userIcon, 50, 50));
		openProfileBtn.setPreferredSize(new Dimension(50, 50));
		homeNordPanel.add(openProfileBtn);
		openProfileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ctrl.openProfilePage(layeredPane, profile);
				ctrl.showProfileTab(dfReviewInReview, dfApprovedReview, dfRejectedReview, insertUsernameLabelProfile);
				
				
			}
		});
		
		JPanel homeWestPanel = new JPanel();
		homeWestPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		homeWestPanel.setBackground(Color.WHITE);
		homePanel.add(homeWestPanel, BorderLayout.WEST);
		
		JLabel countryLabel = new JLabel("Regione");
		countryLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
		
		countryComboBox = new JComboBox();
		countryComboBox.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		countryComboBox.addItem("Abruzzo");
		countryComboBox.addItem("Basilicata");
		countryComboBox.addItem("Calabria");
		countryComboBox.addItem("Campania");
		countryComboBox.addItem("Emilia-Romagna");
		countryComboBox.addItem("Friuli Venezia Giulia");
		countryComboBox.addItem("Lazio");
		countryComboBox.addItem("Liguria");
		countryComboBox.addItem("Lombardia");
		countryComboBox.addItem("Marche");
		countryComboBox.addItem("Molise");
		countryComboBox.addItem("Puglia");
		countryComboBox.addItem("Sardegna");
		countryComboBox.addItem("Sicilia");
		countryComboBox.addItem("Toscana");
		countryComboBox.addItem("Trentino-Alto Adige");
		countryComboBox.addItem("Umbria");
		countryComboBox.addItem("Valle d'Aosta");
		countryComboBox.addItem("Veneto");
		countryComboBox.setSelectedItem("Campania");
		countryComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					ctrl.populateStructuresTable("", (String)countryComboBox.getSelectedItem(), (String)typesComboBox.getSelectedItem(), (String)priceComboBox.getSelectedItem(), "", (String)starComboBox.getSelectedItem(), df);
				}
			}
		});
		
		
		
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setFont(new Font("Ubuntu", Font.BOLD, 16));
		
		typesComboBox = new JComboBox();
		typesComboBox.setFont(new Font("Ubuntu", Font.PLAIN, 13));
				typesComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						if (event.getStateChange() == ItemEvent.SELECTED) {
							ctrl.populateStructuresTable("", (String)countryComboBox.getSelectedItem(), (String)typesComboBox.getSelectedItem(), (String)priceComboBox.getSelectedItem(), "", (String)starComboBox.getSelectedItem(), df);
						}
					}
				});
		
		
		
		
		
		JLabel priceLabel = new JLabel("Costo");
		priceLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
		
		priceComboBox = new JComboBox();
		priceComboBox.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		priceComboBox.addItem("Qualsiasi");
		priceComboBox.addItem("€");
		priceComboBox.addItem("€€");
		priceComboBox.addItem("€€€");
		priceComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			     if (event.getStateChange() == ItemEvent.SELECTED) {
					ctrl.populateStructuresTable("", (String)countryComboBox.getSelectedItem(), (String)typesComboBox.getSelectedItem(), (String)priceComboBox.getSelectedItem(), "", (String)starComboBox.getSelectedItem(), df);
			     }
			}
			
		});
		
		
		
		
		JLabel starLabel = new JLabel("Media valutazioni");
		starLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
		
		starComboBox = new JComboBox();
		starComboBox.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		starComboBox.addItem("Qualsiasi");
		starComboBox.addItem("Minimo 1");
		starComboBox.addItem("Minimo 2");
		starComboBox.addItem("Minimo 3");
		starComboBox.addItem("Minimo 4");
		starComboBox.addItem("Minimo 5");
		ImageIcon reloadIcon= new ImageIcon("imgs\\reload.png");
		
		JButton reloadFiltersButton = new JButton("");
		reloadFiltersButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reloadFiltersButton.setContentAreaFilled(false);
		reloadFiltersButton.setIcon(scale.scaleImg(reloadIcon, 30, 30));
		reloadFiltersButton.setBorderPainted(false);
		reloadFiltersButton.setBackground(Color.WHITE);
		reloadFiltersButton.setPreferredSize(new Dimension(30, 30));
		reloadFiltersButton.setSelectedIcon(reloadIcon);
		reloadFiltersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				countryComboBox.setSelectedItem("Campania");
				typesComboBox.setSelectedItem("Qualsiasi");
				priceComboBox.setSelectedItem("Qualsiasi");
				starComboBox.setSelectedItem("Qualsiasi");
			}
		});
		GroupLayout gl_homeWestPanel = new GroupLayout(homeWestPanel);
		gl_homeWestPanel.setHorizontalGroup(
			gl_homeWestPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_homeWestPanel.createSequentialGroup()
					.addGap(58)
					.addComponent(countryLabel))
				.addGroup(Alignment.TRAILING, gl_homeWestPanel.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(countryComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_homeWestPanel.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(typesComboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_homeWestPanel.createSequentialGroup()
					.addContainerGap(58, Short.MAX_VALUE)
					.addComponent(lblTipologia)
					.addGap(51))
				.addGroup(Alignment.TRAILING, gl_homeWestPanel.createSequentialGroup()
					.addContainerGap(66, Short.MAX_VALUE)
					.addComponent(priceLabel)
					.addGap(64))
				.addGroup(Alignment.TRAILING, gl_homeWestPanel.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(priceComboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_homeWestPanel.createSequentialGroup()
					.addGap(26)
					.addComponent(starLabel)
					.addGap(18))
				.addGroup(Alignment.TRAILING, gl_homeWestPanel.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(starComboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_homeWestPanel.createSequentialGroup()
					.addContainerGap(70, Short.MAX_VALUE)
					.addComponent(reloadFiltersButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(65))
		);
		gl_homeWestPanel.setVerticalGroup(
			gl_homeWestPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_homeWestPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(countryLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(countryComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblTipologia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(typesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(priceLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(priceComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(starLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(starComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(reloadFiltersButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(254))
		);
		homeWestPanel.setLayout(gl_homeWestPanel);
		starComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					ctrl.populateStructuresTable( "", (String)countryComboBox.getSelectedItem(), (String)typesComboBox.getSelectedItem(), (String)priceComboBox.getSelectedItem(), "",  (String)starComboBox.getSelectedItem(), df);
				}
			}
		});
		
		JScrollPane homeCenterScrollPane = new JScrollPane();
		homeCenterScrollPane.setForeground(new Color(0, 0, 0));
		homeCenterScrollPane.setViewportBorder(null);
		homePanel.add(homeCenterScrollPane, BorderLayout.CENTER);
		
		resaltTable = new JTable();
		resaltTable.setSelectionBackground(new Color(255, 255, 255));
		resaltTable.setRowSelectionAllowed(false);
		resaltTable.setBorder(null);
		resaltTable.setShowVerticalLines(false);
		resaltTable.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		resaltTable.setRowHeight(120);
		resaltTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		DefaultTableCellRenderer ResaltCenterRenderer = new DefaultTableCellRenderer();
		ResaltCenterRenderer.setHorizontalAlignment( JLabel.CENTER );
		resaltTable.setDefaultRenderer(String.class, ResaltCenterRenderer);
		resaltTable.setDefaultRenderer(Integer.class, ResaltCenterRenderer);
		
		JTableHeader resaltHeader = resaltTable.getTableHeader();
	      resaltHeader.setBackground(Color.white);
	      resaltHeader.setForeground(new Color(0,0,0));
	      resaltHeader.setFont(new Font("Ubuntu", Font.BOLD, 16));
	      
		resaltTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = resaltTable.getSelectedRow();
				updateLayeredPane(layeredPane, structurePanel);		
				ctrl.setStructureInformation(row + 1, nameStructureLabel, insertAddrLabel, insertPriceLabel, insertStar, structureReferenceLabel, insertPhotoLabel, specificationLabel, typesLable);
				ctrl.populateReviewTable(dfReview, structureReferenceLabel.getText());
				
			}
		});
		resaltTable.setModel(df);
		homeCenterScrollPane.setViewportView(resaltTable);
		setColumnSize(resaltTable, 0, 120);
		setColumnSize(resaltTable, 3, 150);

		
		structurePanel = new JPanel();
		structurePanel.setBackground(Color.WHITE);
		layeredPane.add(structurePanel, "name_1211810175155800");
		structurePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel structureNorthPanel = new JPanel();
		structureNorthPanel.setBackground(Color.WHITE);
		structurePanel.add(structureNorthPanel, BorderLayout.NORTH);
		structureNorthPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel structureNorthPanelCenter = new JPanel();
		structureNorthPanelCenter.setBackground(Color.WHITE);
		structureNorthPanel.add(structureNorthPanelCenter, BorderLayout.NORTH);
		structureNorthPanelCenter.setLayout(new BorderLayout(0, 0));
		
		nameStructureLabel = new JLabel("");
		nameStructureLabel.setFont(new Font("Ubuntu", Font.BOLD, 28));
		nameStructureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		structureNorthPanelCenter.add(nameStructureLabel);
		
		JPanel spacePanel = new JPanel();
		spacePanel.setBackground(Color.WHITE);
		spacePanel.setPreferredSize(new Dimension(25, 10));
		structureNorthPanelCenter.add(spacePanel, BorderLayout.WEST);
		
		JPanel structureNorthPanelEast = new JPanel();
		structureNorthPanelEast.setBackground(Color.WHITE);
		structureNorthPanelCenter.add(structureNorthPanelEast, BorderLayout.EAST);
		
		JButton returnHomeBtn = new JButton("");
		returnHomeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		returnHomeBtn.setIcon(new ImageIcon("imgs\\back.png"));
		returnHomeBtn.setContentAreaFilled(false);
		returnHomeBtn.setBorderPainted(false);
		returnHomeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLayeredPane(layeredPane,homePanel);
			}
		});
		structureNorthPanelEast.add(returnHomeBtn);
		
		JLabel lblNewLabel_4 = new JLabel("     ");
		structureNorthPanelEast.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		structureNorthPanelCenter.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel label_11 = new JLabel("     ");
		panel_1.add(label_11);
		
		specificationLabel = new JLabel(" ");
		panel_1.add(specificationLabel);
		specificationLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
		
		typesLable = new JLabel(" ");
		panel_1.add(typesLable);
		typesLable.setFont(new Font("Ubuntu", Font.BOLD, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(10);
		panel_1.add(panel_2);
		
		JPanel structureInformationPanel = new JPanel();
		structureInformationPanel.setBackground(Color.WHITE);
		structureNorthPanel.add(structureInformationPanel, BorderLayout.CENTER);
		structureInformationPanel.setLayout(new BoxLayout(structureInformationPanel, BoxLayout.X_AXIS));
		
		JLabel structurInformationSpaceLabel = new JLabel("         ");
		structureInformationPanel.add(structurInformationSpaceLabel);
		
		JLabel structurInformationAddrLabel = new JLabel("Indirizzo: ");
		structurInformationAddrLabel.setPreferredSize(new Dimension(43, 20));
		structurInformationAddrLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
		structureInformationPanel.add(structurInformationAddrLabel);
		
		insertAddrLabel = new JLabel(" ");
		insertAddrLabel.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		structureInformationPanel.add(insertAddrLabel);
		
		JLabel structurInformationPriceLabel = new JLabel("      Costo: ");
		structurInformationPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		structurInformationPriceLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
		structureInformationPanel.add(structurInformationPriceLabel);
		
		insertPriceLabel = new JLabel(" ");
		insertPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		insertPriceLabel.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		structureInformationPanel.add(insertPriceLabel);
		
		JLabel structurInformationStarLabel = new JLabel("     Media valutazioni: ");
		structurInformationStarLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
		structureInformationPanel.add(structurInformationStarLabel);
		
		insertStar = new JLabel(" ");
		insertStar.setHorizontalAlignment(SwingConstants.CENTER);
		insertStar.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		structureInformationPanel.add(insertStar);
		
		JPanel structureWestPanel = new JPanel();
		structureWestPanel.setBackground(Color.WHITE);
		structurePanel.add(structureWestPanel, BorderLayout.WEST);
		
		insertPhotoLabel = new JLabel("");
		insertPhotoLabel.setBounds(10,11,350,380);
		
		JLabel writeReviewLabel = new JLabel("Scrivi recensione");
		writeReviewLabel.setFont(new Font("Ubuntu", Font.BOLD, 17));
		
		JScrollPane writeReviewScrollPane = new JScrollPane();
		writeReviewScrollPane.setPreferredSize(new Dimension(25, 25));
		writeReviewScrollPane.setMaximumSize(new Dimension(25, 25));
		writeReviewScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton publicReviewBtn = new JButton("");
		publicReviewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		publicReviewBtn.setIcon(new ImageIcon("imgs\\confirm_button.png"));
		publicReviewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.addReview((String)selectRating.getSelectedItem(), writeReviewTextArea.getText(), structureReferenceLabel.getText());
				
			}
		});
		
		structureReferenceLabel = new JLabel(" ");
		structureReferenceLabel.setVisible(false);
		
		selectRating = new JComboBox();
		selectRating.setFont(new Font("Ubuntu", Font.BOLD, 13));
		selectRating.addItem("1 Stella");
		selectRating.addItem("2 Stelle");
		selectRating.addItem("3 Stelle");
		selectRating.addItem("4 Stelle");
		selectRating.addItem("5 Stelle");
		GroupLayout gl_structureWestPanel = new GroupLayout(structureWestPanel);
		gl_structureWestPanel.setHorizontalGroup(
			gl_structureWestPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_structureWestPanel.createSequentialGroup()
					.addGroup(gl_structureWestPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_structureWestPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(publicReviewBtn, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_structureWestPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_structureWestPanel.createSequentialGroup()
								.addGap(25)
								.addComponent(insertPhotoLabel, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
							.addGroup(gl_structureWestPanel.createSequentialGroup()
								.addGap(124)
								.addComponent(structureReferenceLabel))
							.addGroup(gl_structureWestPanel.createSequentialGroup()
								.addGap(25)
								.addGroup(gl_structureWestPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(writeReviewScrollPane, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
									.addGroup(gl_structureWestPanel.createSequentialGroup()
										.addComponent(writeReviewLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(selectRating, 0, 96, Short.MAX_VALUE))))))
					.addContainerGap())
		);
		gl_structureWestPanel.setVerticalGroup(
			gl_structureWestPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_structureWestPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(insertPhotoLabel, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_structureWestPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(writeReviewLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectRating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(writeReviewScrollPane, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(publicReviewBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(structureReferenceLabel)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		
		
		
		
		writeReviewTextArea = new JTextArea();
		writeReviewTextArea.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		writeReviewTextArea.setLineWrap(true);
		writeReviewScrollPane.setViewportView(writeReviewTextArea);
		structureWestPanel.setLayout(gl_structureWestPanel);
		
		JPanel structureCenterPanel = new JPanel();
		structureCenterPanel.setBackground(Color.WHITE);
		structurePanel.add(structureCenterPanel, BorderLayout.CENTER);
		structureCenterPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel structureCenterPanelNorth = new JPanel();
		structureCenterPanelNorth.setBackground(Color.WHITE);
		structureCenterPanel.add(structureCenterPanelNorth, BorderLayout.NORTH);
		structureCenterPanelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel northSpaceStructureCenterPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) northSpaceStructureCenterPanel.getLayout();
		flowLayout_2.setVgap(11);
		northSpaceStructureCenterPanel.setBackground(Color.WHITE);
		structureCenterPanelNorth.add(northSpaceStructureCenterPanel, BorderLayout.NORTH);
		
		JScrollPane reviewScrollPane = new JScrollPane();
		structureCenterPanel.add(reviewScrollPane, BorderLayout.CENTER);
		
		reviewTabel = new JTable();
		reviewTabel.setRowHeight(40);
		reviewTabel.setFont(new Font("Ubuntu", Font.PLAIN, 15));
		
		reviewTabel.setEnabled(false);
		reviewTabel.setModel(dfReview);
		reviewScrollPane.setViewportView(reviewTabel);

		
		JTableHeader ReviewHeader = reviewTabel.getTableHeader();
		ReviewHeader.setBackground(Color.white);
		ReviewHeader.setForeground(new Color(0,0,0));
		ReviewHeader.setFont(new Font("Ubuntu", Font.BOLD, 16));
		
		

	    setColumnSize(reviewTabel, 0, 200);
		setColumnSize(reviewTabel, 2, 150);



		
		profile = new JPanel();
		profile.setBackground(new Color(255, 255, 255));
		layeredPane.add(profile, "name_1383474523747200");
		profile.setLayout(new BorderLayout(0, 0));
		
		JPanel northProfilePanel = new JPanel();
		northProfilePanel.setBackground(new Color(255, 255, 255));
		profile.add(northProfilePanel, BorderLayout.NORTH);
		northProfilePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel sxProfilePanel = new JPanel();
		sxProfilePanel.setBackground(new Color(255, 255, 255));
		northProfilePanel.add(sxProfilePanel, BorderLayout.WEST);
		sxProfilePanel.setLayout(new BoxLayout(sxProfilePanel, BoxLayout.X_AXIS));
		
		JLabel label_12 = new JLabel("     ");
		sxProfilePanel.add(label_12);
		
		JLabel salutationLabelProfile = new JLabel("Ciao,");
		salutationLabelProfile.setFont(new Font("Ubuntu", Font.BOLD, 28));
		sxProfilePanel.add(salutationLabelProfile);
		
		insertUsernameLabelProfile = new JLabel("");
		insertUsernameLabelProfile.setFont(new Font("Ubuntu", Font.PLAIN, 28));
		sxProfilePanel.add(insertUsernameLabelProfile);
		
		JButton logoutBtn = new JButton("");
		logoutBtn.setFont(new Font("Ubuntu", Font.PLAIN, 28));
		logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutBtn.setIcon(new ImageIcon("imgs\\logout.png"));
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setBorderPainted(false);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLayeredPane(layeredPane, homePanel);
				ctrl.logoutEvent();
			}
		});
		sxProfilePanel.add(logoutBtn);
		
		JPanel dxProfilePanel = new JPanel();
		dxProfilePanel.setBackground(new Color(255, 255, 255));
		northProfilePanel.add(dxProfilePanel, BorderLayout.EAST);
		
		JButton undoBtnProfile = new JButton("");
		undoBtnProfile.setIcon(new ImageIcon("imgs\\back.png"));
		undoBtnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLayeredPane(layeredPane, homePanel);
			}
		});
		
		undoBtnProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		undoBtnProfile.setContentAreaFilled(false);
		undoBtnProfile.setBorderPainted(false);
		
		
		
		dxProfilePanel.add(undoBtnProfile);
		
		JButton modificationBtnProfile = new JButton("");
		modificationBtnProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificationBtnProfile.setContentAreaFilled(false);
		modificationBtnProfile.setBorderPainted(false);
		
		ImageIcon userEditIcon = new ImageIcon("imgs\\editUser.png");
		modificationBtnProfile.setIcon(scale.scaleImg(userEditIcon, 40, 40));
		modificationBtnProfile.setPreferredSize(new Dimension(40, 40));
		modificationBtnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.openModify();
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("  ");
		dxProfilePanel.add(lblNewLabel_3);
		dxProfilePanel.add(modificationBtnProfile);
		
		JLabel label_7 = new JLabel("    ");
		dxProfilePanel.add(label_7);
		
		JButton moderationBtnProfile = new JButton("");
		moderationBtnProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moderationBtnProfile.setContentAreaFilled(false);
		moderationBtnProfile.setBorderPainted(false);
		moderationBtnProfile.setIcon(new ImageIcon("imgs\\adminIcon.png"));
		moderationBtnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ctrl.openAdminPage(dfReviewInReviewAdmin, dfApprovedReviewAdmin, dfRejectedReviewAdmin, layeredPane, adminPanel);
			}
		});
		dxProfilePanel.add(moderationBtnProfile);
		
		JTabbedPane centerProfileTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerProfileTabbedPane.setFont(new Font("Ubuntu", Font.BOLD, 18));
		profile.add(centerProfileTabbedPane, BorderLayout.CENTER);
		
		JPanel reviewsInReview = new JPanel();
		centerProfileTabbedPane.addTab("Recensioni in Revisione", null, reviewsInReview, null);
		reviewsInReview.setLayout(new BorderLayout(0, 0));
		
		JScrollPane reviewInReviewScrollPane = new JScrollPane();
		reviewsInReview.add(reviewInReviewScrollPane, BorderLayout.CENTER);
		
		reviewInReviewTable = new JTable();
		reviewInReviewTable.setRowHeight(40);
		reviewInReviewTable.setEnabled(false);
		reviewInReviewTable.setModel(dfReviewInReview);
		JTableHeader resaltHeaderProfile = reviewInReviewTable.getTableHeader();
		resaltHeaderProfile.setBackground(Color.white);
		resaltHeaderProfile.setForeground(new Color(0,0,0));
		resaltHeaderProfile.setFont(new Font("Ubuntu", Font.BOLD, 14));
		setColumnSize(reviewInReviewTable, 1, 150);
		

		reviewInReviewScrollPane.setViewportView(reviewInReviewTable);
		
		JPanel reviewsApproved = new JPanel();
		centerProfileTabbedPane.addTab("Recensioni Approvate", null, reviewsApproved, null);
		reviewsApproved.setLayout(new BorderLayout(0, 0));
		
		JScrollPane reviewsApprovedScrollPane = new JScrollPane();
		reviewsApproved.add(reviewsApprovedScrollPane, BorderLayout.CENTER);
		
		reviewsApprovedTable = new JTable();
		reviewsApprovedTable.setRowHeight(40);
		reviewsApprovedTable.setEnabled(false);
		reviewsApprovedTable.setModel(dfApprovedReview);
		JTableHeader resaltHeaderProfileApprovedReview = reviewsApprovedTable.getTableHeader();
		resaltHeaderProfileApprovedReview.setBackground(Color.white);
		resaltHeaderProfileApprovedReview.setForeground(new Color(0,0,0));
		resaltHeaderProfileApprovedReview.setFont(new Font("Ubuntu", Font.BOLD, 14));
		setColumnSize(reviewsApprovedTable, 1, 150);
		setColumnSize(reviewsApprovedTable, 2, 200);

		reviewsApprovedScrollPane.setViewportView(reviewsApprovedTable);
		
		JPanel reviewsRejected = new JPanel();
		centerProfileTabbedPane.addTab("Recensioni Rifiutate", null, reviewsRejected, null);
		reviewsRejected.setLayout(new BorderLayout(0, 0));
		
		JScrollPane reviewsRejectedScrollPane = new JScrollPane();
		reviewsRejected.add(reviewsRejectedScrollPane, BorderLayout.CENTER);
		
		reviewsRejectedTable = new JTable();
		reviewsRejectedTable.setRowHeight(40);
		reviewsRejectedTable.setEnabled(false);
		reviewsRejectedTable.setModel(dfRejectedReview);
		JTableHeader resaltHeaderProfileRejectedReview = reviewsRejectedTable.getTableHeader();
		resaltHeaderProfileRejectedReview.setBackground(Color.white);
		resaltHeaderProfileRejectedReview.setForeground(new Color(0,0,0));
		resaltHeaderProfileRejectedReview.setFont(new Font("Ubuntu", Font.BOLD, 14));
		setColumnSize(reviewsRejectedTable, 1, 150);
		setColumnSize(reviewsRejectedTable, 2, 200);

		reviewsRejectedScrollPane.setViewportView(reviewsRejectedTable);
		
		adminPanel = new JPanel();
		adminPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(adminPanel, "name_102777850567700");
		adminPanel.setLayout(new BorderLayout(5, 0));
		
		JPanel northAdminPanel = new JPanel();
		northAdminPanel.setBackground(new Color(255, 255, 255));
		adminPanel.add(northAdminPanel, BorderLayout.NORTH);
		northAdminPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel sxAdminPanel = new JPanel();
		sxAdminPanel.setBackground(new Color(255, 255, 255));
		northAdminPanel.add(sxAdminPanel, BorderLayout.WEST);
		sxAdminPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 9));
		
		JLabel adminInfoLabel = new JLabel("Pannello admin,");
		adminInfoLabel.setFont(new Font("Ubuntu", Font.BOLD, 28));
		sxAdminPanel.add(adminInfoLabel);
		
		adminInfoLabel2 = new JLabel(" controlla e approva le recensioni");
		adminInfoLabel2.setBackground(new Color(255, 255, 255));
		adminInfoLabel2.setFont(new Font("Ubuntu", Font.PLAIN, 28));
		sxAdminPanel.add(adminInfoLabel2);
		
		JPanel dxAdminPanel = new JPanel();
		dxAdminPanel.setBackground(new Color(255, 255, 255));
		northAdminPanel.add(dxAdminPanel, BorderLayout.EAST);
		
		JButton undoBtnAdmin = new JButton("");
		undoBtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateLayeredPane(layeredPane, profile);
			}
		});
		undoBtnAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		undoBtnAdmin.setContentAreaFilled(false);
		undoBtnAdmin.setBorderPainted(false);
		undoBtnAdmin.setIcon(new ImageIcon("imgs\\back.png"));
		dxAdminPanel.add(undoBtnAdmin);
		
		JButton addNewAdminBtn = new JButton("");
		addNewAdminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.addNewAdmin();
			}
		});
		addNewAdminBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addNewAdminBtn.setContentAreaFilled(false);
		addNewAdminBtn.setBorderPainted(false);
		addNewAdminBtn.setIcon(new ImageIcon("imgs\\plus.png"));
		dxAdminPanel.add(addNewAdminBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Ubuntu", Font.BOLD, 18));
		adminPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel reviewsInReviewAdmin = new JPanel();
		tabbedPane.addTab("Recensioni che devi Approvare", null, reviewsInReviewAdmin, null);
		reviewsInReviewAdmin.setLayout(new BorderLayout(0, 0));
		
		JScrollPane reviewsInReviewAdminScrollPane = new JScrollPane();
		reviewsInReviewAdmin.add(reviewsInReviewAdminScrollPane, BorderLayout.CENTER);
		
		reviewsInReviewAdminTable = new JTable();
		reviewsInReviewAdminTable.setSelectionBackground(Color.WHITE);
		reviewsInReviewAdminTable.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		reviewsInReviewAdminTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = reviewsInReviewAdminTable.getSelectedRow();
				Integer i  = (Integer) reviewsInReviewAdminTable.getModel().getValueAt(row, 3);
				ctrl.moderateEvent(i);
				ctrl.openAdminPage(dfReviewInReviewAdmin, dfApprovedReviewAdmin, dfRejectedReviewAdmin, layeredPane, adminPanel);
			}
		});
		reviewsInReviewAdminTable.setRowHeight(40);
		reviewsInReviewAdminTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reviewsInReviewAdminTable.setModel(dfReviewInReviewAdmin);
		setColumnSize(reviewsInReviewAdminTable, 3, 0);
		JTableHeader resaltHeaderAdmin = reviewsInReviewAdminTable.getTableHeader();
	      resaltHeaderAdmin.setBackground(Color.white);
	      resaltHeaderAdmin.setForeground(new Color(0,0,0));
	      resaltHeaderAdmin.setFont(new Font("Ubuntu", Font.BOLD, 14));
	      setColumnSize(reviewsInReviewAdminTable, 2, 150);
	      setColumnSize(reviewsInReviewAdminTable, 0, 200);
		
		reviewsInReviewAdminScrollPane.setViewportView(reviewsInReviewAdminTable);
		
		JPanel reviewsApprovedAdmin = new JPanel();
		tabbedPane.addTab("Recensioni che hai Approvato", null, reviewsApprovedAdmin, null);
		reviewsApprovedAdmin.setLayout(new BorderLayout(0, 0));
		
		JScrollPane reviewsApprovedAdminScrollPane = new JScrollPane();
		reviewsApprovedAdmin.add(reviewsApprovedAdminScrollPane, BorderLayout.CENTER);
		
		reviewsApprovedAdminTable = new JTable();
		reviewsApprovedAdminTable.setRowHeight(40);
		reviewsApprovedAdminTable.setEnabled(false);
		reviewsApprovedAdminTable.setModel(dfApprovedReviewAdmin);
		reviewsApprovedAdminScrollPane.setViewportView(reviewsApprovedAdminTable);
		JTableHeader resaltHeaderAdminApprovedReview = reviewsApprovedAdminTable.getTableHeader();
		resaltHeaderAdminApprovedReview.setBackground(Color.white);
		resaltHeaderAdminApprovedReview.setForeground(new Color(0,0,0));
		resaltHeaderAdminApprovedReview.setFont(new Font("Ubuntu", Font.BOLD, 14));
		setColumnSize(reviewsApprovedAdminTable, 2, 150);
		setColumnSize(reviewsApprovedAdminTable, 0, 200);
		
		JPanel reviewsRejectedAdmin = new JPanel();
		tabbedPane.addTab("Recensioni che hai Rifiutato", null, reviewsRejectedAdmin, null);
		reviewsRejectedAdmin.setLayout(new BorderLayout(0, 0));
		
		JScrollPane reviewsRejectedAdminScrollPane = new JScrollPane();
		reviewsRejectedAdmin.add(reviewsRejectedAdminScrollPane, BorderLayout.CENTER);
		
		reviewsRejectedAdminTable = new JTable();
		reviewsRejectedAdminTable.setRowHeight(40);
		reviewsRejectedAdminTable.setEnabled(false);
		reviewsRejectedAdminTable.setModel(dfRejectedReviewAdmin);
		reviewsRejectedAdminScrollPane.setViewportView(reviewsRejectedAdminTable);
		JTableHeader resaltHeaderAdminRejectedReview = reviewsRejectedAdminTable.getTableHeader();
		resaltHeaderAdminRejectedReview.setBackground(Color.white);
		resaltHeaderAdminRejectedReview.setForeground(new Color(0,0,0));
		resaltHeaderAdminRejectedReview.setFont(new Font("Ubuntu", Font.BOLD, 14));
		setColumnSize(reviewsRejectedAdminTable, 2, 150);
		setColumnSize(reviewsRejectedAdminTable, 0, 200);
	}

	public void aggRiga(Object[] Row, DefaultTableModel df) {
		df.addRow(Row);
	}


	public Integer getSelectedStructure() {
		return selectedStructure;
	}


	public void setSelectedStructure(Integer selectedStructure) {
		this.selectedStructure = selectedStructure;
	}


	public JComboBox getTypesComboBox() {
		return typesComboBox;
	}


	public void setTypesComboBox(JComboBox typesComboBox) {
		this.typesComboBox = typesComboBox;
	}
	
	public void restoreReviewDetails(){
		
		selectRating.setSelectedItem("1 Stella");
		selectRating.repaint();
		selectRating.revalidate();
		writeReviewTextArea.setText("");
		
	}
	public void clearTable(DefaultTableModel df) {
		if (df.getRowCount() > 0) {
		     for (int j = df.getRowCount() -1; j > -1; j--) {
		         df.removeRow(j);
		     }
		 }
	}
}

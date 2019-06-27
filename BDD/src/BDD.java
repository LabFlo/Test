import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BDD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8686994166092266258L;
	private JPanel contentPane;
	private JTable table;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField Pays;
	private JTextField ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BDD frame = new BDD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 bite
	/**
	 * Create the frame.
	 */
	public BDD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] options = { "Oui", "Non" };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 79, 424, 211);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Nom.setText((String) table.getValueAt(table.getSelectedRow(), 1));
				Prenom.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				Pays.setText((String) table.getValueAt(table.getSelectedRow(), 3));
				ID.setText((String) table.getValueAt(table.getSelectedRow(), 0));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane.setViewportView(table);

		/**
		 * BOUTONS
		 */

		JButton btnNewButton = new JButton("Load");
		btnNewButton.setBounds(185, 29, 97, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Insert");
		btnNewButton_1.setBounds(22, 367, 97, 25);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.setBounds(185, 367, 97, 25);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.setBounds(349, 367, 97, 25);
		contentPane.add(btnNewButton_3);

		/**
		 * EVENTS
		 */

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Connect();
				DefaultTableModel dm = new Connect().getData();
				table.setModel(dm);
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Pays.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Veuillez remplir les champs Nom, Prénom et Pays pour effectuer cette action.");
				} else if (!ID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Vous ne pouvez pas remplir le champ ID pour effectuer cette action.");
					ID.setText(null);
				} else {
					Personne personne = new Personne(Nom.getText(), Prenom.getText(), Pays.getText());
					new Insert().setData(personne);
					System.out.println("insertion de " + personne.toString());
					new Connect();
					DefaultTableModel dm = new Connect().getData();
					table.setModel(dm);
				}
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Pays.getText().isEmpty()
						|| ID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Veuillez remplir tous les champs pour effectuer cette action.");
				} else {
					Personne personne = new Personne(Nom.getText(), Prenom.getText(), Pays.getText());
					personne.setID(ID.getText());
					new Update().updateData(personne);
					System.out.println("update " + personne.toString());
					new Connect();
					DefaultTableModel dm = new Connect().getData();
					table.setModel(dm);
				}
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Nom.getText().isEmpty() || !Prenom.getText().isEmpty() || !Pays.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Seul le champ ID doit être rempli pour effectuer cette action.");
					Nom.setText(null);
					Prenom.setText(null);
					Pays.setText(null);
				} else if (ID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous devez remplir le champ ID pour effectuer cette action.");
				} else {
					int x = JOptionPane.showOptionDialog(null,
							"Êtes-vous sûr de vouloir supprimer la ligne " + ID.getText() + " ?", "Click a button",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if (x == 0) {
						System.out.println(x);
						new Delete().deleteData(ID.getText());
						System.out.println("Suppression de la ligne " + ID.getText());
						new Connect();
						DefaultTableModel dm = new Connect().getData();
						table.setModel(dm);
					}
				}
			}
		});

		/**
		 * TEXT FIELDS
		 */

		Nom = new JTextField();
		Nom.setBounds(22, 332, 116, 22);
		contentPane.add(Nom);
		Nom.setColumns(10);

		Prenom = new JTextField();
		Prenom.setBounds(150, 332, 116, 22);
		contentPane.add(Prenom);
		Prenom.setColumns(10);

		Pays = new JTextField();
		Pays.setBounds(277, 332, 116, 22);
		contentPane.add(Pays);
		Pays.setColumns(10);

		ID = new JTextField();
		ID.setBounds(405, 332, 40, 22);
		contentPane.add(ID);
		ID.setColumns(10);

		/**
		 * LABELS
		 */

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(22, 303, 56, 16);
		contentPane.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(150, 303, 56, 16);
		contentPane.add(lblPrenom);

		JLabel lblPays = new JLabel("Pays");
		lblPays.setBounds(278, 303, 56, 16);
		contentPane.add(lblPays);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(406, 303, 40, 16);
		contentPane.add(lblId);

	}
}

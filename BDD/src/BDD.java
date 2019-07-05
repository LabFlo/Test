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
		 * 
		 * Cr�ation des 4 boutons principaux
		 */

		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(185, 29, 97, 25);
		contentPane.add(btnLoad);

		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(22, 367, 97, 25);
		contentPane.add(btnInsert);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(185, 367, 97, 25);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(349, 367, 97, 25);
		contentPane.add(btnDelete);

		/**
		 * EVENTS
		 */

		/*
		 * LOAD
		 * 
		 * Simple select, fait appel � la classe Connect
		 */
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Connect();
				DefaultTableModel dm = new Connect().getData();
				table.setModel(dm);
			}
		});

		/*
		 * INSERT
		 * 
		 * V�rifie que les champs Nom Prenom et Pays sont remplis et que le champ ID est
		 * vide avant de cr�er une Personne et de faire appel � la classe Insert puis �
		 * la classe Connect pour afficher le r�sultat
		 */
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Pays.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Veuillez remplir les champs Nom, Pr�nom et Pays pour effectuer cette action.");
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

		/*
		 * UPDATE
		 * 
		 * V�rifie que tous les champs sont remplis avant de cr�er une Personne et de
		 * faire appel � la classe Update puis � la classe Connect pour afficher le
		 * r�sultat
		 */
		btnUpdate.addActionListener(new ActionListener() {
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

		/*
		 * DELETE
		 * 
		 * V�rifie que seul le champ ID est rempli avant de faire appel � la classe
		 * Delete puis � la classe Connect pur afficher le r�sultat
		 */
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Nom.getText().isEmpty() || !Prenom.getText().isEmpty() || !Pays.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Seul le champ ID doit �tre rempli pour effectuer cette action.");
					Nom.setText(null);
					Prenom.setText(null);
					Pays.setText(null);
				} else if (ID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous devez remplir le champ ID pour effectuer cette action.");
				} else {
					int x = JOptionPane.showOptionDialog(null,
							"�tes-vous s�r de vouloir supprimer la ligne " + ID.getText() + " ?", "Click a button",
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

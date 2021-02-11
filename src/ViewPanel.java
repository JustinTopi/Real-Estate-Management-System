import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ViewPanel {
	ViewController controller;
	private JFrame systemFrame;
	private JTextField searchField;
	private JTable houseTable, flatTable, branchTable;
	private ArrayList<Branch> branches;
	private User user;
	private boolean isSecretary;
	final DefaultTableModel flatModel = new DefaultTableModel();
	final DefaultTableModel houseModel = new DefaultTableModel();
	Object[] branchColumns = { "Address", "Phone Number", "Email", "Name", "Web Address" };
	Object[] houseColumns = { "Address", "Rooms", "Selling Price", "Sold Price", "Branch", "Floor", "Garage", "Garden",
			"Object" };
	Object[] flatColumns = { "Address", "Rooms", "Selling Price", "Sold Price", "Branch", "Floor", "Share Charges",
			"Object" };
	private int latestTableChosen = -1;

	public ViewPanel(ViewController controller, ArrayList<Branch> branches, User user, boolean isSecretary) {
		this.controller = controller;
		this.branches = branches;
		this.user = user;
		this.isSecretary = isSecretary;

		View();
	}

	public ViewPanel() {
		// TODO Auto-generated constructor stub
	}

	public void closeWindow() {

		systemFrame.dispose();
	}

	private void View() {

		// JFrame for both admin and secretary depending on case
		systemFrame = new JFrame(isSecretary ? "Secretary Panel" : "Admin Panel");
		systemFrame.setBounds(100, 100, 800, 700);
		systemFrame.getContentPane().setBackground(new Color(245, 245, 245));
		systemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		systemFrame.getContentPane().setLayout(null);
		systemFrame.setResizable(false);
		systemFrame.setLocationRelativeTo(null);
		systemFrame.setVisible(true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(647, 0, 147, 672);
		systemFrame.getContentPane().add(panel);
		panel.setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.blue);
		panel.setBorder(border);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// branches label
		JLabel lblBranches = new JLabel("Branches");
		lblBranches.setBackground(new Color(64, 224, 208));
		lblBranches.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBranches.setBounds(277, 80, 119, 27);
		systemFrame.getContentPane().add(lblBranches);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// add branch button
		JButton addBranchBtn = new JButton("Add Branch");
		addBranchBtn.setIcon(new ImageIcon("resources//add.png"));
		addBranchBtn.setBounds(10, 34, 127, 30);
		// if it's secretary then addbranch button is disabled
		if (isSecretary)
			addBranchBtn.setEnabled(false);

		panel.add(addBranchBtn);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// edit button
		JButton editBtn = new JButton("Edit");
		editBtn.setIcon(new ImageIcon("resources//edit.png"));
		editBtn.setBounds(10, 98, 127, 30);
		panel.add(editBtn);
		editBtn.setEnabled(false);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// add houses button
		JButton addHouses = new JButton("Add Houses");
		addHouses.setIcon(new ImageIcon("resources//add.png"));
		addHouses.setBounds(10, 165, 127, 30);
		addHouses.addActionListener(controller);
		controller.AddView(this);
		panel.add(addHouses);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//add flats button
		JButton btnAddFlats = new JButton("Add Flats");
		btnAddFlats.setIcon(new ImageIcon("resources//add.png"));
		btnAddFlats.setBounds(10, 230, 127, 30);
		panel.add(btnAddFlats);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// delete button
		JButton delBtn = new JButton("Delete");
		delBtn.setIcon(new ImageIcon("resources//del.png"));
		delBtn.setBounds(10, 292, 127, 30);
		panel.add(delBtn);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// save button
		JButton saveBtn = new JButton("Save");
		saveBtn.setIcon(new ImageIcon("resources//save.png"));
		saveBtn.setBounds(10, 352, 127, 30);
		panel.add(saveBtn);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// if it's secretary then change the admin image to secretary image
		String userImage = isSecretary ? "resources//secretary.png" : "resources//admin.png";
		JButton homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(userImage));
		homeButton.setBackground(Color.GRAY);
		homeButton.setDisabledIcon((new ImageIcon(userImage)));
		homeButton.setEnabled(false);
		homeButton.setBounds(10, 435, 127, 151);
		panel.add(homeButton);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//logout button
		JButton logOutBtn = new JButton("Logout");
		panel.add(logOutBtn);
		logOutBtn.setIcon(new ImageIcon("resources//logout.png"));
		logOutBtn.setBounds(10, 615, 127, 30);
		logOutBtn.addActionListener(controller);
		controller.AddView(this);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		searchField = new JTextField();
		searchField.setBounds(142, 42, 194, 27);
		systemFrame.getContentPane().add(searchField);
		searchField.setColumns(10);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// select box to choose between Houses or Flats to query
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "Houses", "Flats" }));
		comboBox.setBounds(333, 42, 88, 27);
		systemFrame.getContentPane().add(comboBox);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// Search button
		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(425, 42, 100, 27);
		systemFrame.getContentPane().add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = searchField.getText();
				int searchType = comboBox.getSelectedIndex();
				// 1: houses, 2: flats
				System.out.println("Searching " + search + " on " + comboBox.getSelectedItem());
				int row = getBrachesTable().getSelectedRow();
				String branchName = getBrachesTable().getValueAt(row, 3).toString();
				Branch br = null;
				for (Branch b : branches) {
					if (b.getName().equalsIgnoreCase(branchName)) {
						br = b;
						break;
					}
				}

				if (br != null) {
					switch (searchType) {
					case 1:
						ArrayList<House> temp_houses = new ArrayList<House>();
						for (House h : br.getHouses()) {
							if (h.getAddress().toLowerCase().contains(search.toLowerCase()))
								temp_houses.add(h);

						}

						for (int i = getHouseModel().getRowCount() - 1; i >= 0; i--)
							getHouseModel().removeRow(i);

						for (int i = 0; i < temp_houses.size(); i++) {
							Object[] rowData = { temp_houses.get(i).getAddress(),
									temp_houses.get(i).getNumber_of_room(), temp_houses.get(i).getSelling_price(),
									temp_houses.get(i).getSold_price(), temp_houses.get(i).getBranch_name(),
									temp_houses.get(i).getNumber_of_floors(), temp_houses.get(i).getHas_garage(),
									temp_houses.get(i).getHas_garden(), temp_houses.get(i) };
							getHouseModel().addRow(rowData);

						}

						break;
					case 2:
						ArrayList<Flat> temp_flats = new ArrayList<Flat>();
						for (Flat f : br.getFlats()) {
							if (f.getAddress().toLowerCase().contains(search.toLowerCase()))
								temp_flats.add(f);
						}

						for (int i = getFlatModel().getRowCount() - 1; i >= 0; i--)
							getFlatModel().removeRow(i);

						for (int i = 0; i < temp_flats.size(); i++) {
							Object[] rowData = { temp_flats.get(i).getAddress(), temp_flats.get(i).getNumber_of_room(),
									temp_flats.get(i).getSelling_price(), temp_flats.get(i).getSold_price(),
									temp_flats.get(i).getBranch_name(), temp_flats.get(i).getFloor(),
									temp_flats.get(i).getMonthly_charge(), temp_flats.get(i) };
							getFlatModel().addRow(rowData);
						}

						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Couldn't apply filter!");
					System.out.println("Couldn't apply filter!");
				}
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////
		JScrollPane branchScrollPane = new JScrollPane();
		branchScrollPane.setBounds(100, 115, 442, 120);
		systemFrame.getContentPane().add(branchScrollPane);

		branchTable = new JTable();

		branchTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				latestTableChosen = 0;
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		final DefaultTableModel branchModel = new DefaultTableModel();
		branchModel.setColumnIdentifiers(branchColumns);

		ObjectInputStream oisss = null;
		Branch obj = null;
		FileInputStream fisss = null;
		try {
			String fullpathss = "branch.dat";
			fisss = new FileInputStream(fullpathss);
			oisss = new ObjectInputStream(fisss);

			while ((obj = (Branch) oisss.readObject()) != null) {

				branches.add(obj);
				if (!isSecretary || obj.getSecretaryName().equalsIgnoreCase(user.getUsername())) {
					Object[] data = { obj.getAddress(), obj.getPhone_number(), obj.getEmail(), obj.getName(),
							obj.getWeb_address(), obj.getHouses(), obj.getFlats() };
					branchModel.addRow(data);
				}
			}

		} catch (EOFException e) {
			// do nothing as its expected when there are no more info
		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			if (fisss != null)
				fisss.close();
			if (oisss != null)
				oisss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		branchTable.setModel(branchModel);

		branchTable.getColumnModel().getColumn(0).setResizable(false);
		branchTable.getColumnModel().getColumn(1).setResizable(false);
		branchTable.getColumnModel().getColumn(2).setResizable(false);
		branchTable.getColumnModel().getColumn(3).setResizable(false);
		branchTable.getColumnModel().getColumn(4).setResizable(false);
		branchScrollPane.setViewportView(branchTable);
		branchTable.addMouseListener(controller);

		houseModel.setColumnIdentifiers(getArrayWithoutLastElement(houseColumns));

		flatModel.setColumnIdentifiers(getArrayWithoutLastElement(flatColumns));

		houseTable = new JTable();
		houseTable.setBackground(new Color(176, 196, 222));

		flatTable = new JTable();
		flatTable.setBackground(new Color(176, 196, 222));

		houseTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				latestTableChosen = 1;
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		flatTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				latestTableChosen = 2;
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		});

		JLabel lblHouses = new JLabel("Houses");
		lblHouses.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHouses.setBackground(new Color(64, 224, 208));
		lblHouses.setBounds(277, 246, 119, 27);
		systemFrame.getContentPane().add(lblHouses);

		JScrollPane housesScrollPane = new JScrollPane(houseTable);
		housesScrollPane.setBounds(10, 284, 627, 162);
		systemFrame.setLayout(null);
		systemFrame.getContentPane().add(housesScrollPane);

		JLabel lblFlats = new JLabel("Flats");
		lblFlats.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFlats.setBackground(new Color(64, 224, 208));
		lblFlats.setBounds(292, 460, 54, 27);
		systemFrame.getContentPane().add(lblFlats);

		JScrollPane flatScrollPane = new JScrollPane(flatTable);
		flatScrollPane.setBounds(10, 498, 627, 163);
		systemFrame.setLayout(null);
		systemFrame.getContentPane().add(flatScrollPane);

		// create an array of objects to set the row data

		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Branch related
				try {
					File f = new File("branch.dat");
					if (f.exists()) {
						boolean delete = f.delete();
						System.out.println("Deleted branch.dat " + delete);
						boolean created = f.createNewFile();
						System.out.println("Re-created new branch.dat " + created);
						JOptionPane.showMessageDialog(null, "Saved Successfully!");
					}
					FileOutputStream fis = new FileOutputStream("branch.dat", false);
					ObjectOutputStream ois = new ObjectOutputStream(fis);
					for (Branch b : branches) {
						ois.writeObject(b);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		final Object[] branchRow = new Object[5];
		addBranchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String Address = JOptionPane.showInputDialog("Please input address");
				String phone_number = JOptionPane.showInputDialog("Please input phone number");
				String email = JOptionPane.showInputDialog("Please input email");
				String name = JOptionPane.showInputDialog("Please input name");
				String web_address = JOptionPane.showInputDialog("Please input web_address");
				String secretaryName = JOptionPane.showInputDialog("Please input secretary user");
				Object[] branchRow = { Address, phone_number, email, name, web_address };
				branches.add(new Branch(Address, phone_number, email, name, web_address, user.getUsername(),
						user.getPassword(), new ArrayList<>(), new ArrayList<>(), secretaryName));
				branchModel.addRow(branchRow);
				JOptionPane.showMessageDialog(null, "Branch addded!");
				System.out.print("Branch addded!");
			}
		});

		final Object[] housesRow = new Object[8];

		addHouses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = getBrachesTable().getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "You must select a branch!", "Branch",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// asking for input where the answers will be inserted in the rows
				String branchName = getBrachesTable().getValueAt(row, 3).toString();// branch name of the house gets
																					// automatically the branch name of
																					// the selected branch
				String Address = JOptionPane.showInputDialog("Please input address");
				String rooms = JOptionPane.showInputDialog("Please input rooms number");
				String sellingprice = JOptionPane.showInputDialog("Please input selling price");
				String soldprice = JOptionPane.showInputDialog("Please input sold price");
				String floors = JOptionPane.showInputDialog("Please input floors");
				String garage = JOptionPane.showInputDialog("Please input garage (Y/N)");
				String garden = JOptionPane.showInputDialog("Please input garden (Y/N)");
				House h = new House(Address, Integer.parseInt(rooms), Double.parseDouble(sellingprice),
						Double.parseDouble(soldprice), branchName, Integer.parseInt(floors),
						"Y".equalsIgnoreCase(garage), "Y".equalsIgnoreCase(garden));
				Object[] housesRow = { Address, rooms, sellingprice, soldprice, branchName, floors,
						"Y".equalsIgnoreCase(garage), "Y".equalsIgnoreCase(garden), h };

				Branch br = null;
				for (Branch b : branches) {
					if (b.getName().equalsIgnoreCase(branchName)) {
						br = b;
						break;
					}
				}

				if (br != null) {
					br.Houses.add(h);

					// add row to the model
					houseModel.addRow(housesRow);
					JOptionPane.showMessageDialog(null, "House addded!");
					System.out.print("House addded!");
				} else {
					System.out.println("Could not add house since we didnt find the branch");
				}
			}
		});

		final Object[] flatRow = new Object[7];

		btnAddFlats.addActionListener(new ActionListener() {

			@Override
			// checking if a branch is selected before adding a new flat
			public void actionPerformed(ActionEvent e) {
				int row = getBrachesTable().getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "You must select a branch!", "Branch",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// asking for input where the answers will be inserted in the rows
				String branchName = getBrachesTable().getValueAt(row, 3).toString();// branch name of the house gets
																					// automatically the branch name of
																					// the selected branch
				String address = JOptionPane.showInputDialog("Please input address");
				String rooms = JOptionPane.showInputDialog("Please input rooms number");
				String selling_price = JOptionPane.showInputDialog("Please input selling price");
				String sold_price = JOptionPane.showInputDialog("Please input sold price");
				String floor = JOptionPane.showInputDialog("Please input floors");
				String shareCharges = JOptionPane.showInputDialog("Please input share holders");
				Flat f = new Flat(Integer.parseInt(rooms), Double.parseDouble(selling_price),
						Double.parseDouble(sold_price), branchName, address, Integer.parseInt(floor),
						Double.parseDouble(shareCharges));
				Object[] flatRow = { address, rooms, selling_price, sold_price, branchName, floor, shareCharges, f };

				Branch br = null;
				for (Branch b : branches) {
					if (b.getName().equalsIgnoreCase(branchName)) {
						br = b;
						break;
					}
				}
				// adding flat
				if (br != null) {
					br.Flats.add(f);
					// add row to the model
					flatModel.addRow(flatRow);
					JOptionPane.showMessageDialog(null, "Flat addded!");
					System.out.print("Flat addded!");
				} else {
					System.out.println("Couldn't add flat since we didn't find the branch");
				}
			}
		});

		// button remove row - Clicked on Delete Button
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// i = the index of the selected row
				int i = branchTable.getSelectedRow();
				if (i >= 0 && latestTableChosen == 0) {
					if (isSecretary) {
						JOptionPane.showMessageDialog(null, "You cannot delete a branch", "Branch error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					// remove a row from jtable
					String branchName = branchModel.getValueAt(i, 3).toString();
					int indexToRemove = -1;

					for (int j = 0; j < branches.size(); j++) {
						if (branches.get(j).getName().equalsIgnoreCase(branchName)) {
							indexToRemove = j;
							break;
						}
					}

					if (indexToRemove != -1) {
						branchModel.removeRow(i);
						branches.remove(i);

					} else {
						JOptionPane.showMessageDialog(null, "Couldn't remove the branch!");
						System.out.println("Couldn't remove the branch");
					}
				} else {

					// System.out.println("Trouble while trying to delete!");
				}
			}
		});

		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// checking if a branch is selected to delete
				if (getBrachesTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "You must select a branch!", "Branch",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// i = the index of the selected row
				int i = houseTable.getSelectedRow();
				if (i >= 0 && latestTableChosen == 1) {
					// remove a row from jtable
					House h = (House) houseTable.getValueAt(i, houseTable.getColumnCount() - 1);
					houseModel.removeRow(i);
					Branch br = getBranchesList().get(getBrachesTable().getSelectedRow());
					br.Houses.remove(h);
					JOptionPane.showMessageDialog(null, "Row Deleted!");
					System.out.println("Row Deleted!");

				} else {
					// JOptionPane.showMessageDialog(null, "Branch Deleted!");
					System.out.println("Branch Deleted!");
				}
			}
		});

		delBtn.addActionListener(new ActionListener() {

			@Override
			// checking if a branch is selected to delete
			public void actionPerformed(ActionEvent e) {
				if (getBrachesTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "You must select a branch!", "Branch",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// i = the index of the selected row
				int x = flatTable.getSelectedRow();
				if (x >= 0 && latestTableChosen == 2) {
					// remove a row from jtable
					Flat f = (Flat) flatTable.getValueAt(x, flatTable.getColumnCount() - 1);
					Branch br = getBranchesList().get(getBrachesTable().getSelectedRow());
					flatModel.removeRow(x);
					br.Flats.remove(f);
				} else {

					// System.out.println("Row Deleted!");
				}
			}
		});

		// button update row - Clicked on Update Button
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (branchTable.getSelectedRow() != -1) {
					System.out.println("Edit branch" + branchTable.getSelectedRow());

				} else if (houseTable.getSelectedRow() != -1) {
					System.out.println("Edit House");

				} else if (flatTable.getSelectedRow() != -1) {
					System.out.println("Edit Flat");

				}

			}
		});

		houseTable.setRowHeight(20);
		houseTable.setModel(houseModel);
		flatTable.setRowHeight(20);
		flatTable.setModel(flatModel);

	}

	public JTable getBrachesTable() {
		return branchTable;
	}

	public ArrayList<Branch> getBranchesList() {
		return branches;
	}

	public JTable getHousesTable() {
		return houseTable;
	}

	public Object[] getFlatsColumns() {
		return flatColumns;
	}

	public Object[] getHousesColumns() {
		return houseColumns;
	}

	public JTable getFlatsTable() {
		return flatTable;
	}

	public DefaultTableModel getFlatModel() {
		return flatModel;
	}

	public DefaultTableModel getHouseModel() {
		return houseModel;
	}

	public Object[] getBranchColumns() {
		return branchColumns;
	}

	// getting the last array
	private Object[] getArrayWithoutLastElement(Object[] arr) {
		Object[] objArr = new Object[arr.length - 1];
		System.arraycopy(arr, 0, objArr, 0, arr.length - 1);
		return objArr;
	}
}
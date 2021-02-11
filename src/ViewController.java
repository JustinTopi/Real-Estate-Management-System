import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ViewController implements ActionListener, MouseListener {
	Model model;
	ViewPanel view;

	public ViewController(Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	public void AddView(ViewPanel view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		// if logout button clicked then close the window and return to the login panel
		String action = event.getActionCommand().toString();
		if (action.equals("Logout")) {
			LoginController controller = new LoginController(model);
			LoginView loginView = new LoginView(controller);
			view.closeWindow();
		}
		if (action.equals("Add Branch")) {

		}
	}

	// clicking on branches, added houses and flats pop up in their tables
	// using a for loop to get the
	@Override
	public void mouseClicked(MouseEvent e) {

		view.getFlatModel().setColumnIdentifiers(view.getFlatsColumns());
		view.getHouseModel().setColumnIdentifiers(view.getHousesColumns());

		// TODO Auto-generated method stub
		for (int i = view.getHousesTable().getRowCount() - 1; i >= 0; i--) {
			view.getHouseModel().removeRow(i);
		}

		for (int i = view.getFlatsTable().getRowCount() - 1; i >= 0; i--) {
			view.getFlatModel().removeRow(i);
		}

		int row = view.getBrachesTable().rowAtPoint(e.getPoint());
		String newName = view.getBrachesTable().getValueAt(row, 3).toString();
		Branch br = null;
		for (int i = 0; i < view.getBranchesList().size(); i++) {
			Branch newBr = view.getBranchesList().get(i);
			if (newName.equalsIgnoreCase(newBr.getName())) {
				br = newBr;
				break;
			}
		}

		if (br != null) {
			ArrayList<House> temp_houses = br.getHouses();
			ArrayList<Flat> temp_flats = br.getFlats();
			String s = "";
			for (int i = 0; i < temp_flats.size(); i++) {
				Object[] rowData = { temp_flats.get(i).getAddress(), temp_flats.get(i).getNumber_of_room(),
						temp_flats.get(i).getSelling_price(), temp_flats.get(i).getSold_price(),
						temp_flats.get(i).getBranch_name(), temp_flats.get(i).getFloor(),
						temp_flats.get(i).getMonthly_charge(), temp_flats.get(i) };
				view.getFlatModel().addRow(rowData);
			}

			for (int i = 0; i < temp_houses.size(); i++) {
				Object[] rowData = { temp_houses.get(i).getAddress(), temp_houses.get(i).getNumber_of_room(),
						temp_houses.get(i).getSelling_price(), temp_houses.get(i).getSold_price(),
						temp_houses.get(i).getBranch_name(), temp_houses.get(i).getNumber_of_floors(),
						temp_houses.get(i).getHas_garage(), temp_houses.get(i).getHas_garden(), temp_houses.get(i) };
				view.getHouseModel().addRow(rowData);
			}

			int viewsColumnCount = view.getHousesTable().getColumnCount();
			if (view.getHousesTable().getColumnModel().getColumn(viewsColumnCount - 1).getHeaderValue().toString()
					.equalsIgnoreCase("Object")) {
				view.getHousesTable().getColumnModel().getColumn(viewsColumnCount - 1).setMinWidth(0);
				view.getHousesTable().getColumnModel().getColumn(viewsColumnCount - 1).setMaxWidth(0);
				view.getHousesTable().getColumnModel().getColumn(viewsColumnCount - 1).setWidth(0);
			}
			viewsColumnCount = view.getFlatsTable().getColumnCount();
			if (view.getFlatsTable().getColumnModel().getColumn(viewsColumnCount - 1).getHeaderValue().toString()
					.equalsIgnoreCase("Object")) {
				view.getFlatsTable().getColumnModel().getColumn(viewsColumnCount - 1).setMinWidth(0);
				view.getFlatsTable().getColumnModel().getColumn(viewsColumnCount - 1).setMaxWidth(0);
				view.getFlatsTable().getColumnModel().getColumn(viewsColumnCount - 1).setWidth(0);
			}

		} else {
			System.out.println("Couldn't print new information of branch since it was not found");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// TODO Auto-generated method stub
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

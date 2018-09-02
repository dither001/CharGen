package adapter;

import java.io.File;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Controller {
	public static JPanel errorPanel;
	public int dataRequested;
	public static int people;
	public static int members;
	public static int adults;
	public static int feasts;
	public static String[] memberDataTableHeader = {"Last Name", "First Name", "SCA Name", "Membership #", "Expiration Date", "Is An Adult"};
	public static String[] attendanceDataTableHeader = {"Last Name", "First Name", "Is An Adult", "Is A Member", "Attending Feast"};

}

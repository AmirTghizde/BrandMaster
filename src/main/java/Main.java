import Menu.Menu;
import service.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.publicMenu();
    }
}

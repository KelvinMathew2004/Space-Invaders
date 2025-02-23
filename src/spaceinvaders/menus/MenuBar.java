package spaceinvaders.menus;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar {
    public JMenu menu;

    public JMenu createMenu(String menuName) {
        menu = new JMenu(menuName);
        return menu;
    }

    public void addButton(String buttonName, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(buttonName);
        menuItem.addActionListener(action);
        menu.add(menuItem);
    }
}

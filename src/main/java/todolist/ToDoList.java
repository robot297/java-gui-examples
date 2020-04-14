package todolist;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToDoList extends JFrame {
    private JPanel rootPanel;
    private JTextField newToDoTextField;
    private JButton addToDoButton;
    private JList<String> toDoList;
    private JButton deleteSelectedButton;

    private DefaultListModel<String> listModel;

    protected ToDoList(){
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(500, 500));

        listModel = new DefaultListModel<>();

        toDoList.setModel(listModel);

        toDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addListeners();

        getRootPane().setDefaultButton(addToDoButton);

        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void deleteSelected(){
        int selectedIndex = toDoList.getSelectedIndex();

        if(selectedIndex == -1){
            JOptionPane.showMessageDialog(this, "Selecte an item to delete.");
        } else {
            String deleteItem = toDoList.getSelectedValue();
            if(JOptionPane.showConfirmDialog(this, "Delete " + deleteItem + "?", "Delete",
                    JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                listModel.remove(selectedIndex);
            }
        }
    }

    private void addListeners(){
        addToDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newToDo = newToDoTextField.getText();
                newToDo = newToDo.trim();

                if(newToDo.isEmpty()){
                    JOptionPane.showMessageDialog(ToDoList.this, "Enter a to do item.");
                } else {
                    listModel.addElement(newToDo);
                    newToDoTextField.setText("");
                }
            }
        });

        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = toDoList.getSelectedIndex();
                if (selectedIndex != -1){
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(ToDoList.this, "Please select an item.");
                }
            }
        });

        JPopupMenu rightClickMenu = new JPopupMenu();
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        rightClickMenu.add(deleteMenuItem);

        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelected();
            }
        });

        toDoList.setComponentPopupMenu(rightClickMenu);

        toDoList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selection = toDoList.locationToIndex(e.getPoint());
                toDoList.setSelectedIndex(selection);
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
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleTextEditor extends JFrame {

    JTextArea textArea;

    SimpleTextEditor() {

        setTitle("Simple Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Text Area
        textArea = new JTextArea();

        // Add text area inside JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem clearItem = new JMenuItem("Clear");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(newItem);
        fileMenu.add(clearItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem selectAllItem = new JMenuItem("Select All");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(selectAllItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        // New
        newItem.addActionListener(e -> {
            textArea.setText("");
        });

        // Clear
        clearItem.addActionListener(e -> {
            textArea.setText("");
        });

        // Exit
        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        // Edit options
        cutItem.addActionListener(e -> {
            textArea.cut();
        });

        copyItem.addActionListener(e -> {
            textArea.copy();
        });

        pasteItem.addActionListener(e -> {
            textArea.paste();
        });

        selectAllItem.addActionListener(e -> {
            textArea.selectAll();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleTextEditor();
    }
}

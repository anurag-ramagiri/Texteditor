import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class TextEditor implements ActionListener {
    // declaring properties of text editor

    JFrame frame;
    JMenuBar menubar;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile; // file menu items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;


    TextEditor(){
        // initialize frame
        frame = new JFrame();
        // initialize text area
        textArea = new JTextArea();
        // initialize menu bar
        menubar = new JMenuBar();
        // initializing menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        // initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open FIle") ;
        saveFile = new JMenuItem("Save File");
        // add action listerner to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // add menu items to menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        // initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // add action listerner to file menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // add menu items to menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        // set menus to menubar
        menubar.add(file);
        menubar.add(edit);
        // set menu bar to frame
        frame.setJMenuBar(menubar);
        // create content panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to the panel
        panel.add(textArea, BorderLayout.CENTER);
        //create scrolll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        //set dimensions of frame
        frame.setBounds(540,200,500,500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);



    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()== cut){
            textArea.cut();
        }
        if(actionEvent.getSource()== copy){
            textArea.copy();
        }
        if(actionEvent.getSource()== paste){
            textArea.paste();
        }
        if(actionEvent.getSource()== selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()== close){
            // close the editor
           System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we click on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // getting the selected file
                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filePath = file.getPath();
                try{
                    //initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // initialize buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // read the contents line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output+=intermediate+"\n";
                    }
                    // set the output string to text area
                    textArea.setText((output));
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource() == saveFile){
            // initialize a file pickerer
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            // if we click on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // crate new file with choosen dir path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize Buffered Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text Area
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }

        }
        if(actionEvent.getSource() == newFile){
            TextEditor textEditor = new TextEditor();
        }

    }
    public static void main(String[] args){
        TextEditor textEditor = new TextEditor();
    }

}
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class text_editor extends JFrame implements ActionListener {
    JTextArea text = new JTextArea(30,50);
    JButton save_as = new JButton("Save As");
    JButton save = new JButton("Save");
    JButton open = new JButton("Open");
    JButton encrypt = new JButton("Encrypt");
    JButton decrypt = new JButton("Decrypt");
    String name = "";
    FileDialog fd;
    char list[] = {'a', 'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char[] encrypted_list = new char[26]; 
    public text_editor()
    {
        super("Text editor");
        save_as.addActionListener(this);
        save.addActionListener(this);
        encrypt.addActionListener(this);
        open.addActionListener(this);
        setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        save_as.setBounds(2,2,50,10);
        p1.add(save_as);
        save.setBounds(2,2,50,10);
        p1.add(save);
        open.setBounds(13,2,50,10);
        p1.add(open);
        encrypt.setBounds(24,2,50,10);
        p1.add(encrypt);
        decrypt.setBounds(24,2,50,10);
        p1.add(decrypt);
        add(p1, BorderLayout.NORTH);
        add(text);
        fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\");
    }
    public void SaveAs() 
    {
        String file_name = JOptionPane.showInputDialog("Please input a name");
        name = file_name.trim() + ".txt";
        setTitle(file_name);
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(name,true)));
            pw.print(text.getText());
            pw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null , "Invalid File Name" ,"Error" ,JOptionPane.ERROR_MESSAGE );
        }
    }
    public void Save() 
    {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(name,false)));
            System.out.println(name);
            System.out.println(text.getText());
            pw.print(text.getText());
            pw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null , "Invalid File Name" ,"Error" ,JOptionPane.ERROR_MESSAGE );
        }
    }
    public void Encrypt()
    {
        /*String keyword = JOptionPane.showInputDialog("Please input a keyword");
        for(int i = 0; i<encrypted_list.length; i++)
        {
            if(i<=keyword.length() - 1)
                encrypted_list[i] = keyword.charAt(i);
            else
                 encrypted_list[i] = list[i - keyword.length()];
        }
        char word_in_char[] = new char[text.getText().length()];
        String alphabets = list.toString();
        String input =  text.getText();
        for(int i = 0; i<word_in_char.length; i++)
        {
            word_in_char[i] = input.charAt(i);
        }
        System.out.println(word_in_char);
        for(int i = 0; i<word_in_char.length; i++)
        {
            word_in_char[i] = encrypted_list[alphabets.indexOf(word_in_char[i])];
        }
        text.setText(word_in_char.toString());
        */
       String keyword = JOptionPane.showInputDialog("Please input a keyword");
       for(int i = 0; i<encrypted_list.length; i++)
        {
            if(i<=keyword.length() - 1)
                encrypted_list[i] = keyword.charAt(i);
            else
                 encrypted_list[i] = list[i - keyword.length()];
        }
        ArrayList<Character> word_in_char = new ArrayList<Character>();
        String alphabets = "";
        for(int i = 0; i<list.length; i++)
        {
            alphabets += list[i];
        }
        for(int i = 0; i<text.getText().length(); i++)
        {
            word_in_char.add(text.getText().charAt(i));
        }
        for(int i = 0; i<text.getText().length(); i++)
        {
            try
            {
                word_in_char.set(i,encrypted_list[alphabets.indexOf(word_in_char.get(i))]);
            }
            catch(IndexOutOfBoundsException e)
            {
                continue;
            }
        }
        String final_out = "";
        for(int i = 0; i<word_in_char.size() ; i++)
        {
            final_out += word_in_char.get(i);
        }
       text.setText(final_out);
    }
    public void decrypt()
    {
       String keyword = JOptionPane.showInputDialog("Please input a keyword");
       for(int i = 0; i<encrypted_list.length; i++)
        {
            if(i<=keyword.length() - 1)
                encrypted_list[i] = keyword.charAt(i);
            else
                 encrypted_list[i] = list[i - keyword.length()];
        }
        ArrayList<Character> word_in_char = new ArrayList<Character>();
        String alphabets = "";
        for(int i = 0; i<list.length; i++)
        {
            alphabets += list[i];
        }
        for(int i = 0; i<text.getText().length(); i++)
        {
            word_in_char.add(text.getText().charAt(i));
        }
        for(int i = 0; i<text.getText().length(); i++)
        {
            try
            {
                word_in_char.set(i,encrypted_list[alphabets.indexOf(word_in_char.get(i))]);
            }
            catch(IndexOutOfBoundsException e)
            {
                continue;
            }
        }
        String final_out = "";
        for(int i = 0; i<word_in_char.size() ; i++)
        {
            final_out += word_in_char.get(i);
        }
       text.setText(final_out);
    }
    public void Open()
    {
        fd.setVisible(true);
        String fn = fd.getFile();
        PrintWriter pw;
        BufferedReader br;
        String n = "";
        name = fn;
        String FinalText = "";
        try{
            br = new BufferedReader(new FileReader(fn));
            do{
                    n = br.readLine();
                    if(n==null)
                        break;
                    FinalText += n + "\n";
            }while(true);
            text.setText(FinalText);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null , "Invalid File Chosen" ,"Error" ,JOptionPane.ERROR_MESSAGE );
        }
    }
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == save_as)
       {       
           SaveAs();
       }
       if (e.getSource() == encrypt)
       {
           Encrypt();
       }
       if (e.getSource() == save)
       {
           Save();
       }
       if (e.getSource() == open)
       {
           Open();
       }
       if (e.getSource() == decrypt)
       {
           decrypt();
       }
    }
    public static void main(String[] args)
    {
        text_editor obj = new text_editor();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setSize(500, 1000);
        obj.setVisible(true);
    }
}   

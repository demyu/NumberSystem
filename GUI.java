import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import javax.swing.JOptionPane;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;

public class GUI extends JFrame{

    private JTextField tf0;
    private JLabel l0,l1,l2;
    private JComboBox cb0,cb1;
    private JButton b0;
    private static String[] type = {"Binary", "Decimal", "Octal", "Hexadecimal"};
    private JPanel p0,p1,p2,p3;
    private ArrayList<String> saver = new ArrayList<String>();
    public GUI(){

        tf0 = new JTextField("", 15);
            tf0.setFont(new Font("Luciana", Font.BOLD, 20));
            tf0.setHorizontalAlignment(JTextField.CENTER);
        
        l0 = new JLabel("From");
            l0.setFont(new Font("Luciana", Font.BOLD, 20));
            l0.setForeground(Color.black);

        l1 = new JLabel("To");
            l1.setFont(new Font("Luciana", Font.BOLD, 20));
            l1.setForeground(Color.black);

        l2 = new JLabel("");
            l2.setFont(new Font("Luciana", Font.BOLD, 20));
            l2.setForeground(Color.red);

        cb0 = new JComboBox(type);
            cb0.setFont(new Font("Luciana", Font.BOLD, 15));

        cb1 = new JComboBox(type);
            cb1.setFont(new Font("Luciana", Font.BOLD, 15));

        b0 = new JButton("Calculate");
            b0.setFont(new Font("Luciana", Font.BOLD, 20));
            b0.addActionListener(new calListener());

        p0 = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        setLayout(new GridLayout(4,1));
        add(p0);
            p0.add(tf0);
        add(p1);
            p1.add(l0);
            p1.add(cb0);
            p1.add(l1);
            p1.add(cb1);
        add(p2);
            p2.add(b0);
        add(p3);
            p3.add(l2);
        
    }

    public class calListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
                if(cb0.getSelectedItem()=="Binary"){
                    if(!binaryIsIllegal())
                        calculator();
                        clearArr();
                }else if(cb0.getSelectedItem()=="Octal"){
                    if(!octalIsIllegal()){
                        calculator();
                        clearArr();
                    }
                }else if(cb0.getSelectedItem()=="Decimal"){
                    if(!decimalIsIllegal()){
                        calculator();
                        clearArr();
                    }
                }else if(cb0.getSelectedItem()=="Hexadecimal"){
                    if(!hexaIsIllegal()){
                        calculator();
                        clearArr();
                    }
                }
        }
    }

    public void setupArra(){
        for(int x = 0; x<tf0.getText().length(); x++){
            saver.add(Character.toString(tf0.getText().charAt(x)));
        }
        System.out.println(saver);
    }

    public void clearArr(){
        for(int x = 0; x<saver.size(); x++){
            saver.clear();
        }
    }

    public void calculator(){
        if(cb0.getSelectedItem()== "Binary"){
            l2.setText(binary());
        }else if(cb0.getSelectedItem()== "Decimal"){
            l2.setText(Decimal());
        }else if(cb0.getSelectedItem()== "Octal"){
            l2.setText(Octal());
        }else {
            l2.setText(Hexadecimal());
        }
        
    }

    public String binary(){
        int temp = 0;
        String temp1= "", temp2="";
        switch((String)cb1.getSelectedItem()){
            case "Binary": return tf0.getText();
            case "Decimal": setupArra();
                            for(int x = saver.size()-1, y=0; x>=0; x--){
                                if(saver.get(y).equals("1")){
                                    temp+= Math.pow(2, x);
                                }
                                y++;
                            }return Integer.toString(temp);
            case "Octal": setupArra();
                          Collections.reverse(saver);
                          for(int x = 0; x<saver.size(); x++){
                                if(saver.get(x).equals("1") && x%3 == 0){
                                    temp+=1;
                                }else if(saver.get(x).equals("1") && x%3 == 1){
                                    temp+=2;
                                }else if(saver.get(x).equals("1")&& x%3 == 2 ){
                                    temp+=4;
                                    temp1+= Integer.toString(temp);
                                    temp=0;
                                }else if(saver.get(x).equals("0") && x/2!=0 && x%3==2){
                                    temp1+= Integer.toString(temp);
                                    temp=0;
                                }
                          }
                          if(temp!=0)
                            temp1+= Integer.toString(temp);
                          for(int p =temp1.length()-1; p>=0; p--){
                              temp2+=Character.toString(temp1.charAt(p));
                          }
                          return temp2;
            case "Hexadecimal": setupArra();
                                Collections.reverse(saver);
                            for(int x = 0; x<saver.size(); x++){
                                if(saver.get(x).equals("1") && x%4 == 0){
                                    temp+=1;
                                }else if(saver.get(x).equals("1") && x%4 == 1){
                                    temp+=2;
                                }else if(saver.get(x).equals("1") && x%4 == 2){
                                    temp+=4;
                                }else if(saver.get(x).equals("1")&& x%4 == 3){
                                    temp+=8;
                                    temp1+= hexaDictionary(temp);
                                    temp=0;
                                }else if(saver.get(x).equals("0") && x/3!=0 && x%4==3){
                                    temp1+= hexaDictionary(temp);
                                    temp=0; 
                                }
                                System.out.println(temp +"+"+ temp1);
                        }if(temp!=0 )
                        temp1+= hexaDictionary(temp);
                        
                        for(int x =temp1.length()-1;x>=0; x--){
                            temp2 += Character.toString(temp1.charAt(x));
                        }return temp2;
            default: return "Flag";
        }
    }

    public String Decimal(){
        int temp =0;
        String temp1="", temp2="";
        switch((String)cb1.getSelectedItem()){
            case "Binary": setupArra();
                            temp = Integer.parseInt(tf0.getText());
                            while(temp!=0){
                                temp1+=temp%2;
                                temp/=2;
                            }
                            for(int x =temp1.length()-1;x>=0; x--){
                                temp2 += Character.toString(temp1.charAt(x));
                            }return temp2;
           case "Decimal": return tf0.getText();
           case "Octal": setupArra();
                            temp = Integer.parseInt(tf0.getText());
                            while(temp!=0){
                                temp1+=temp%8;
                                temp/=8;
                            }
                            for(int x =temp1.length()-1;x>=0; x--){
                                temp2 += Character.toString(temp1.charAt(x));
                            }return temp2;
            case "Hexadecimal": setupArra();
                            temp = Integer.parseInt(tf0.getText());
                            while(temp!=0){
                                temp1+=hexaDictionary(temp%16);
                                temp/=16;
                            }
                            for(int x =temp1.length()-1;x>=0; x--){
                                temp2 += Character.toString(temp1.charAt(x));
                            }return temp2;
        }
        return "Flag";
    }

    public String Octal(){
        int temp =0;
        String temp1="", temp2="";
        switch((String)cb1.getSelectedItem()){
            case "Binary": setupArra(); 
                            for(int t=0; t<saver.size();t++){
                                temp1+=  octaDictionary(Integer.parseInt(saver.get(t)));                                
                            }System.out.print(temp1);
                            return temp1;
            case "Decimal": setupArra();
                            for(int x = saver.size()-1, y=0; x>=0; x--){
                                    temp+= Math.pow(8, x)*Integer.parseInt(saver.get(y));
                                y++;
                            }return Integer.toString(temp);
            case "Octal": return tf0.getText();
            case "Hexadecimal": setupArra(); //Converts to octal then hexa
                            for(int x = saver.size()-1, y=0; x>=0; x--){
                                temp+= Math.pow(8, x)*Integer.parseInt(saver.get(y));
                            y++;
                            }while(temp!=0){
                                temp1+=hexaDictionary(temp%16);
                                temp/=16;
                            }
                            for(int x =temp1.length()-1;x>=0; x--){
                                temp2 += Character.toString(temp1.charAt(x));
                            }return temp2;
        }

        return null;
    }

    public String Hexadecimal(){
        int temp =0;
        String temp1="", temp2="";
        switch((String)cb1.getSelectedItem()){
            case "Decimal": setupArra();
                            for(int x = saver.size()-1, y=0; x>=0; x--){
                                temp+= Math.pow(16, y)*reversedHexaDictionary(saver.get(x).toUpperCase());
                            y++;
                            }
                            return Integer.toString(temp);
            case "Binary": setupArra(); //Converts to decimal then binary
                            for(int x = saver.size()-1, y=0; x>=0; x--){
                                temp+= Math.pow(16, y)*reversedHexaDictionary(saver.get(x).toUpperCase());
                            y++;
                            }while(temp!=0){
                                temp1+=temp%2;
                                temp/=2;
                            }
                            for(int x =temp1.length()-1;x>=0; x--){
                                temp2 += Character.toString(temp1.charAt(x));
                            }return temp2;
            case "Octal": setupArra(); //Converts to decimal then octal
                            for(int x = saver.size()-1, y=0; x>=0; x--){
                                temp+= Math.pow(16, y)*reversedHexaDictionary(saver.get(x).toUpperCase());
                            y++;
                            }while(temp!=0){
                                temp1+=temp%8;
                                temp/=8;
                            }
                            for(int x =temp1.length()-1;x>=0; x--){
                                temp2 += Character.toString(temp1.charAt(x));
                            }return temp2;
            case "Hexadecimal": return tf0.getText();
        }
        return null;
    }
    

    public String hexaDictionary(int x){
        switch(x){
            case 10: return "A";
            case 11: return "B";
            case 12: return "C";
            case 13: return "D";
            case 14: return "E";
            case 15: return "F";
            default: return Integer.toString(x);
        }
    }

    public int reversedHexaDictionary(String x){
        switch(x){
            case "A": return 10;
            case "B": return 11;
            case "C": return 12;
            case "D": return 13;
            case "E": return 14;
            case "F": return 15;
            default: return Integer.parseInt(x);
        }
        
    }

    public String octaDictionary(int x){
        switch(x){
            case 0: return "000";
            case 1: return "001";
            case 2: return "010";
            case 3: return "011";
            case 4: return "100";
            case 5: return "101";
            case 6: return "110";
            case 7: return "111";
            default: return "Flag";
        }
    }
    //Checks if Binary is greater than 1, letter or a special char
    public boolean binaryIsIllegal(){
        setupArra();
        for(int x = 0; x<saver.size(); x++){
            if(Character.isLetter(saver.get(x).charAt(0)) || Character.isWhitespace(saver.get(x).charAt(0))||  isSymbol() ||
            Integer.parseInt(saver.get(x)) >1){
                JOptionPane.showMessageDialog(null, "Wait that's illegal", "Error", JOptionPane.ERROR_MESSAGE);
                clearArr();
                return true;
            }
        }
        clearArr();
        return false;

    }
    //Checks if Binary is greater than 1, letter or a special char
    public boolean hexaIsIllegal(){
        setupArra();
        for(int x = 0; x<saver.size(); x++){
            if(Character.isWhitespace(saver.get(x).charAt(0))||isSymbol() ||!isHexa()){
                JOptionPane.showMessageDialog(null, "Wait that's illegal", "Error", JOptionPane.ERROR_MESSAGE);
                clearArr();
                return true;
            }
        }
        clearArr();
        return false;

    }
    public boolean octalIsIllegal(){
        setupArra();
        for(int x = 0; x<saver.size(); x++){
            if(Character.isLetter(saver.get(x).charAt(0)) || Character.isWhitespace(saver.get(x).charAt(0))||  isSymbol()){
                JOptionPane.showMessageDialog(null, "Wait that's illegal", "Error", JOptionPane.ERROR_MESSAGE);
                clearArr();
                return true;
            }
        }
        clearArr();
        return false;

    }
    public boolean decimalIsIllegal(){
        setupArra();
        for(int x = 0; x<saver.size(); x++){
            if(Character.isLetter(saver.get(x).charAt(0)) || Character.isWhitespace(saver.get(x).charAt(0))||  isSymbol()){
                JOptionPane.showMessageDialog(null, "Wait that's illegal", "Error", JOptionPane.ERROR_MESSAGE);
                clearArr();
                return true;
            }
        }
        clearArr();
        return false;

    }

    public boolean isSymbol(){
        String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
        for (int i = 0; i < saver.size(); i++) {
            for(int x= 0; x<specialChars.length(); x++){
                if(saver.get(i).charAt(0) == specialChars.charAt(x)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isHexa(){
        String specialChars = "0123456789ABCDEF";
        for (int i = 0; i < saver.size(); i++) {
            for(int x= 0; x<specialChars.length(); x++){
                if(saver.get(i).charAt(0) == specialChars.charAt(x)){
                    return true;
                }
            }
        }
        return false;
    }

}
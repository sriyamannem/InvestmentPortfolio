3import eportfolio.*;
import java.util.*;
import java.io.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class Portfolio 
{
    static int index = 0;
    static JButton gnext;
    static JButton gprev;

    public static void main (String[] args)
    {
        ArrayList <Investment> list= new ArrayList <Investment> ();
        HashMap<String, ArrayList<Integer>> hmap = new HashMap<>();
        
        //begining of GUI
        JFrame frame=new JFrame(); 
        
        JMenuBar menubar=new JMenuBar(); 
        frame.setJMenuBar(menubar);

        JMenu labelcommands =new JMenu("Commands");
        menubar.add(labelcommands); 
       
        JMenuItem cbuy=new JMenuItem("Buy");
        cbuy.setBounds(100, 30, 75, 25);
        JMenuItem csell=new JMenuItem("Sell");
        csell.setBounds(200, 30, 75, 25);
        JMenuItem cupdate=new JMenuItem("Update"); 
        cupdate.setBounds(300, 30, 75, 25);
        JMenuItem ctotal=new JMenuItem("getGain");
        ctotal.setBounds(400, 30, 75, 25);
        JMenuItem csearch=new JMenuItem("Search"); 
        csearch.setBounds(500, 30, 75, 25);
        JMenuItem cquit=new JMenuItem("Quit"); 
        cquit.setBounds(600, 30, 75, 25);

        labelcommands.add(cbuy); 
        labelcommands.add(csell);
        labelcommands.add(cupdate); 
        labelcommands.add(ctotal); 
        labelcommands.add(csearch); 
        labelcommands.add(cquit);

        //Initial Panel 

        JLabel labelbody1 =new JLabel("Welcome to ePortfolio.");
        labelbody1.setBounds(50, 150, 600, 40);
        JLabel labelbody2 =new JLabel("Choose a command from the “Commands” menu to buy or sell" ); 
        labelbody2.setBounds(50, 200, 600, 40 );
        JLabel labelbody3 =new JLabel("an investment, update prices for all investments, get gain for the ");
        labelbody3.setBounds(50, 225, 600, 40 ); 
        JLabel labelbody4 =new JLabel("portfolio, search for relevant investments, or quit the program. "); 
        labelbody4.setBounds(50, 250, 600, 40 );

        JPanel panel1 =new JPanel(); 
        panel1.setLayout(null);

        panel1.setBounds(0,0,700,500);
        panel1.add(labelbody1); 
        panel1.add(labelbody2);
        panel1.add(labelbody3); 
        panel1.add(labelbody4); 
        frame.add(panel1);
        panel1.setVisible(true);

        //buy panel 
        String[] optionsToChoose = {"Stock", "MutualFund"};
        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose); 

        JLabel blabelbody1 =new JLabel("Buying an investment");
        blabelbody1.setBounds(50, 100, 600, 25);
        JLabel blabelbody2 =new JLabel("Type" ); 
        blabelbody2.setBounds(50, 135, 200, 30);
        JLabel blabelbody3 =new JLabel("Symbol");
        blabelbody3.setBounds(50, 170, 200, 30);
        JLabel blabelbody4 =new JLabel("Name");
        blabelbody4.setBounds(50, 205, 200, 30);
        JLabel blabelbody5 =new JLabel("Quantity"); 
        blabelbody5.setBounds(50, 240, 200, 30);
        JLabel blabelbody6 =new JLabel("Price");
        blabelbody6.setBounds(50, 275, 200, 30); 

        
        jComboBox.setBounds(250, 135, 100, 20);

        JTextField bsymboltext=new JTextField();
        bsymboltext.setBounds(250, 170, 100, 20);
    
        JTextField bnametext=new JTextField(); 
        bnametext.setBounds(250, 205, 100, 20);

        JTextField bquantitytext=new JTextField(); 
        bquantitytext.setBounds(250, 240, 100, 20);
       
        JTextField bpricetext=new JTextField();
        bpricetext.setBounds(250, 275, 100, 20);
       
        JTextArea messages1 = new JTextArea(5,30); 
        messages1.setEditable(false); 

        JScrollPane scrolledText1 = new JScrollPane(messages1);
        scrolledText1.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolledText1.setBounds(50, 300, 600, 100);
        
        JButton breset=new JButton("Reset");
        breset.setBounds(450, 150, 75, 40);
        breset.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
               jComboBox.setSelectedItem("Stock"); 
               bsymboltext.setText("");
               bnametext.setText("");
               bquantitytext.setText("");
               bpricetext.setText("");
               messages1.setText("");    
            }
        }); 


        JButton bbuy=new JButton("Buy"); 
        bbuy.setBounds(450, 250, 75, 40);
        bbuy.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String type= (String)jComboBox.getSelectedItem();
                String name=bnametext.getText(); 
                String symbol=bsymboltext.getText();
                int quanity=Integer.parseInt(bquantitytext.getText());
                int price=Integer.parseInt(bpricetext.getText());

                String empty= "";
                if( quanity<=0 || price<=0 || symbol.equals(empty) || name.equals(empty))
                {
                    messages1.setText("Not a valid entry. Please try again.");
                }
                else 
                {
                    Boolean hi=false;
                    for(int i=0; i<list.size( ); i++)
                    {
                        if(symbol.equals(list.get(i).getSymbol()))
                        {
                            hi=true;   
                            list.get(i).setQuantity(quanity); 
                            list.get(i).setPrice(price);
                            list.get(i).setBookvalue(quanity,price);
                        }
                    }
                    if(hi==false)
                    {
        
                        if (type.equalsIgnoreCase("stock"))
                        {
                            Stock s= new Stock();
                            s.setSymbol(symbol);
                            s.setName(name);
                            s.setQuantity(quanity);
                            s.setPrice(price);
                            s.setBookvalue(quanity,price);
                            list.add(s);
                        }

                        else if(type.equalsIgnoreCase("mutualfund"))
                        {
                            MutualFund s= new MutualFund();
                            s.setSymbol(symbol);
                            s.setName(name);
                            s.setQuantity(quanity);
                            s.setPrice(price);
                            s.setBookvalue(quanity,price);
                            list.add(s);
                        }       
                    } 

                        messages1.setText("Investment sucessfully bought!");
                }
            }
        }); 
    

        JPanel panel2=new JPanel(); 
        panel2.setLayout(null);
        panel2.add(scrolledText1);
        panel2.setBounds(0,0,700,500);
        panel2.add(blabelbody1); 
        panel2.add(blabelbody2);
        panel2.add(blabelbody3); 
        panel2.add(blabelbody4); 
        panel2.add(blabelbody5);
        panel2.add(blabelbody6); 
        panel2.add(jComboBox);
        panel2.add(bsymboltext);
        panel2.add(bnametext); 
        panel2.add(bquantitytext); 
        panel2.add(bpricetext);
        panel2.add(breset); 
        panel2.add(bbuy); 
        frame.add(panel2); 
        panel2.setVisible(false);

        //sell panel 

        JLabel slabelbody1 =new JLabel("Selling an investment");
        slabelbody1.setBounds(50, 100, 600, 30);
        JLabel slabelbody3 =new JLabel("Symbol");
        slabelbody3.setBounds(50, 150, 600, 30);
        JLabel slabelbody5 =new JLabel("Quantity"); 
        slabelbody5.setBounds(50, 183, 600, 30);
        JLabel slabelbody6 =new JLabel("Price");
        slabelbody6.setBounds(50, 216, 600, 30); 

        JTextField ssymboltext=new JTextField();
        ssymboltext.setBounds(250, 150, 100, 20);
        JTextField squantitytext=new JTextField(); 
        squantitytext.setBounds(250, 183, 100, 20); 
        JTextField spricetext=new JTextField();
        spricetext.setBounds(250, 216, 100, 20);

        JTextArea messages2 = new JTextArea(5,30);
        messages2.setEditable(false); 

        JScrollPane scrolledText2 = new JScrollPane(messages2);
        scrolledText2.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolledText2.setBounds(50, 300, 600, 100);

        JButton sreset=new JButton("Reset");
        sreset.setBounds(450, 125, 75, 40);
        sreset.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
               ssymboltext.setText("");
               squantitytext.setText("");
               spricetext.setText("");
               messages2.setText("");    
            }
        }); 

        JButton ssell=new JButton("Sell"); 
        ssell.setBounds(450, 200, 75, 40);
        ssell.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String symbol=ssymboltext.getText();
                int quanity=Integer.parseInt(squantitytext.getText());
                int price=Integer.parseInt(spricetext.getText());

                String empty= "";
                if( quanity<=0 || price<=0 || symbol.equals(empty))
                {
                    messages1.setText("Not a valid entry. Please try again.");
                }
                else 
                {
                    Boolean exist=false; 
                    Boolean exist1=false;
                    int j=0;

                    for(int i=0; i<list.size( ); i++)
                    {
                        if(symbol.equals(list.get(i).getSymbol()))
                        {
                            exist=true; 
                            if (quanity<=list.get(i).getQuantity())
                            {
                                exist1=true; 
                                j=i; 
                            }
                        }
                    }

                    if( (exist1==true) && (exist==true) )
                    {
                        if (quanity==list.get(j).getQuantity())
                        {
                            list.get(j).sell(quanity, price);
                            list.remove(j); 
                        }
                        else
                        {
                            list.get(j).sell(quanity, price);
                        }

                        messages2.setText("Investment sucessfully Sold!");
                    }
                    else
                    {
                         messages2.setText("Either Stock does not exist or avaliable quanity is lower than requested quantity.");
                    }    
                }
            }
        }); 

        JPanel panel6 =new JPanel(); 
        panel6.setLayout(null);

        panel6.add(scrolledText2); 

        panel6.setBounds(0,0,700,500);
        panel6.add(slabelbody1); 
        panel6.add(slabelbody3); 
        panel6.add(slabelbody5);
        panel6.add(slabelbody6); 
        panel6.add(ssymboltext);
        panel6.add(squantitytext); 
        panel6.add(spricetext);
        panel6.add(sreset); 
        panel6.add(ssell); 
        frame.add(panel6);
        panel6.setVisible(false);

        //update panel 

        JLabel glabelbody1 =new JLabel("Updating an investment");
        glabelbody1.setBounds(50, 110, 200, 30);
        JLabel glabelbody3 =new JLabel("Symbol");
        glabelbody3.setBounds(50, 150, 600, 30);
        JLabel glabelbody5 =new JLabel("Name"); 
        glabelbody5.setBounds(50, 183, 600, 30);
        JLabel glabelbody6 =new JLabel("Price");
        glabelbody6.setBounds(50, 216, 600, 30); 

        JTextField gsymboltext=new JTextField();
        gsymboltext.setBounds(250, 150, 100, 20);
        gsymboltext.setEditable(false); 
        
        JTextField gnametext=new JTextField(); 
        gnametext.setBounds(250, 183, 100, 20);
        gnametext.setEditable(false);
       
        JTextField gpricetext=new JTextField();
        gpricetext.setBounds(250, 216, 100, 20); 

        gprev=new JButton("Prev");
        gprev.setBounds(450, 130, 75, 30);

        gnext=new JButton("Next");
        gnext.setBounds(450, 170, 75, 30);


       
        gprev.addActionListener(new ActionListener() 
        {
            
            public void actionPerformed(ActionEvent e) 
            {
                index--;
                gnext.setEnabled(true); 
                if(list.size()>0)
                {
                    if (index == 0) 
                    {
                        gprev.setEnabled(false); 

                    }
                    
                        gsymboltext.setText(list.get(index).getSymbol());
                        gnametext.setText(list.get(index).getName());
                }
            }
        });
        gnext.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                index++;
                gprev.setEnabled(true); 
                if(list.size()>0)
                {
                    if (index == list.size() - 1) 
                    {
                       gnext.setEnabled(false); 
                    }
                    
                        gsymboltext.setText(list.get(index).getSymbol());
                        gnametext.setText(list.get(index).getName());
                }

            }
        });

            JButton gupdate=new JButton("Update"); 
            gupdate.setBounds(450, 210, 75, 30);

            JTextArea messages3 = new JTextArea(5,30); 
            messages3.setEditable(false);

            JScrollPane scrolledText3 = new JScrollPane(messages3);
            scrolledText3.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrolledText3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrolledText3.setBounds(50, 300, 600, 100);

        gupdate.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {

                if (list.size()>0)
                {
                    int price=Integer.parseInt(gpricetext.getText());
                    if (price>=0)
                    {
                        list.get(index).setPrice(price);
                        list.get(index).setBookvalue(list.get(index).getQuantity(), price); 
                        messages3.setText("Investment: " + list.get(index).getSymbol() + " successfully updated."); 
                    } 
                    else 
                    {
                        messages3.setText("Invaild input. Try again!");
                    }
                } 

            }
        });

        JPanel panel3 =new JPanel();
        panel3.setLayout(null);

        panel3.add(scrolledText3); 

        panel3.setBounds(0,0,700,500);
        panel3.add(glabelbody1); 
        panel3.add(glabelbody3); 
        panel3.add(glabelbody5);
        panel3.add(glabelbody6); 
        panel3.add(gsymboltext);
        panel3.add(gnametext); 
        panel3.add(gpricetext);
        panel3.add(gprev); 
        panel3.add(gnext); 
        panel3.add(gupdate);
        frame.add(panel3);
        panel3.setVisible(false);
        

        //Total gain panel  

        JLabel tlabelbody1 =new JLabel("Getting total gain");
        tlabelbody1.setBounds(50, 100, 600, 30);
        JLabel tlabelbody3 =new JLabel("Total gain");
        tlabelbody3.setBounds(50, 150, 600, 30);
    
        JTextField tgaintext=new JTextField();
        tgaintext.setBounds(250, 150, 100, 20);
        tgaintext.setEditable(false); 

        JTextArea messages4 = new JTextArea(5,30);
        messages4.setEditable(false); 

        JScrollPane scrolledText4 = new JScrollPane(messages4);
        scrolledText4.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolledText4.setBounds(50, 300, 600, 100);

        JPanel panel4 =new JPanel();
        panel4.setLayout(null);

        panel4.add(scrolledText4); 

        panel4.setBounds(0,0,700,500); 
        panel4.add(tlabelbody1); 
        panel4.add(tlabelbody3); 
        panel4.add(tgaintext);
        frame.add(panel4); 
        panel4.setVisible(false);

        // Search Panel

        JLabel sealabelbody1 =new JLabel("Searching an investment");
        sealabelbody1.setBounds(50, 100, 600, 30);
        JLabel sealabelbody3 =new JLabel("Symbol");
        sealabelbody3.setBounds(50, 150, 600, 30);
        JLabel sealabelbody5 =new JLabel("Name\n Keywords"); 
        sealabelbody5.setBounds(50, 175, 600, 30);
        JLabel sealabelbody6 =new JLabel("Low Price");
        sealabelbody6.setBounds(50, 200, 600, 30); 
        JLabel sealabelbody7 =new JLabel("High Price");
        sealabelbody7.setBounds(50, 225, 600, 30);

        JTextField seasymboltext=new JTextField();
        seasymboltext.setBounds(250, 150, 100, 20);
        JTextField seakeywords=new JTextField(); 
        seakeywords.setBounds(250, 175, 100, 20); 
        JTextField sealow=new JTextField();
        sealow.setBounds(250, 200, 100, 20);
        JTextField seahigh=new JTextField();
        seahigh.setBounds(250, 225, 100, 20);

        JTextArea messages5 = new JTextArea(5,30); 
        messages5.setEditable(false);

        JScrollPane scrolledText5 = new JScrollPane(messages5);
        scrolledText5.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolledText5.setBounds(50, 300, 600, 100);

        JButton seareset=new JButton("Reset");
        seareset.setBounds(450, 125, 75, 40);
        seareset.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
               seasymboltext.setText("");
               seakeywords.setText("");
               sealow.setText("");
               seahigh.setText("");    
            }
        });

        JButton seasearch=new JButton("Search"); 
        seasearch.setBounds(450, 200, 75, 40);
        seasearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String resultssymbols="";
                int pricehigh; 
                int pricelow; 
                if(seahigh.getText().equals(""))
                {
                    pricehigh=0; 
                }
                else
                {
                    pricehigh= Integer.parseInt(seahigh.getText()); 
                }

                if(sealow.getText().equals(""))
                {
                    pricelow=0; 
                } 
                else
                {
                    pricelow=Integer.parseInt(sealow.getText()); 
                }

                String resultprice="";
                if(pricehigh<pricehigh)
                {
                    resultprice= "Invaild input for price"; 
                }
                else
                {
                    for(int i=0; i<list.size( ); i++)
                    {
                        if(  (list.get(i).getPrice()<=pricehigh) && (list.get(i).getPrice()>=pricelow) )
                        {
                            resultprice= resultprice + "Investment symbol: " + list.get(i).getSymbol() + " Investment name: " + list.get(i).getName() + " Quantity: " + list.get(i).getQuantity() + " Price: " + list.get(i).getPrice() + "\n";
                        }
                    }
                }
               
                for(int i=0; i<list.size( ); i++)
                {
                    if(seasymboltext.getText().equalsIgnoreCase(list.get(i).getSymbol()))
                    {
                        resultssymbols= resultssymbols + "Investment symbol: " + list.get(i).getSymbol() + " Investment name: " + list.get(i).getName() + " Quantity: " + list.get(i).getQuantity() + " Price: " + list.get(i).getPrice() + "\n";
                    }
                }

                for(int i=0; i<list.size( ); i++)
                {   
                    StringTokenizer token = new StringTokenizer(list.get(i).getName());
                    
                    while (token.hasMoreTokens()) 
                    {
                       
                        String word3=token.nextToken().toLowerCase();
                        if(hmap.containsKey(word3))
                        {
                            hmap.get(word3).add(i);
                            
                        }
                        else 
                        {
                            ArrayList<Integer> indexs= new ArrayList<Integer>(); 
                            indexs.add(i);
                            hmap.put(word3, indexs ); 
                        }
                    }       

                } 

                StringTokenizer keywordstok = new StringTokenizer(seakeywords.getText());
                String result="";
                while(keywordstok.hasMoreTokens( ))
                {
                    String word5=keywordstok.nextToken().toLowerCase();

                        if (hmap.containsKey(word5)) 
                        {
                            ArrayList<Integer> hi= new ArrayList<Integer>();
                            hi=hmap.get(word5); 

                            for(int i=0; i<hi.size(); i++)
                            {
                                result= result + "Investment symbol: " + list.get(i).getSymbol() + " Investment name: " + list.get(i).getName() + " Quantity: " + list.get(i).getQuantity() + " Price: " + list.get(i).getPrice() + "\n"; 
                            }
                        }
                } 

                messages5.setText("Search for Symbols: \n" +resultssymbols + "\nSearch for Keywords:\n" + result + "\nSearch for Price:\n" + resultprice);      
            }
        }); 

 

        JPanel panel5 =new JPanel(); 
        panel5.setLayout(null);

        panel5.add(scrolledText5); 
       
        panel5.setBounds(0,0,700,500);
        panel5.add(sealabelbody1); 
        panel5.add(sealabelbody3); 
        panel5.add(sealabelbody5);
        panel5.add(sealabelbody6); 
        panel5.add(sealabelbody7); 
        panel5.add(seasymboltext);
        panel5.add(seakeywords); 
        panel5.add(seahigh);
        panel5.add(sealow);
        panel5.add(seareset); 
        panel5.add(seasearch); 
        panel5.setVisible(false);
        frame.add(panel5); 

        cbuy.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                panel1.setVisible(false);
                panel2.setVisible(true); 
                panel3.setVisible(false); 
                panel4.setVisible(false);
                panel5.setVisible(false);
                panel6.setVisible(false);
            }
        }); 

        csell.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                panel1.setVisible(false);
                panel2.setVisible(false); 
                panel3.setVisible(false); 
                panel4.setVisible(false);
                panel5.setVisible(false);
                panel6.setVisible(true);
            }
        }); 

        cupdate.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                panel1.setVisible(false);
                panel2.setVisible(false); 
                panel3.setVisible(true); 
                panel4.setVisible(false);
                panel5.setVisible(false);
                panel6.setVisible(false);
                if(list.size() == 0 || list.size() == 1){
                    gprev.setEnabled(false);
                    gnext.setEnabled(false);
                }else{
                    if(index == 0){
                        gprev.setEnabled(false);
                        gnext.setEnabled(true);
                    }else if(index == list.size() - 1){
                        gnext.setEnabled(false);
                        gprev.setEnabled(true);
                    }else{
                        gprev.setEnabled(true);
                        gnext.setEnabled(true);
                    }
                }
                if (list.size()>0)
                {
                    gsymboltext.setText(list.get(0).getSymbol()); 
                }
                if (list.size()>0)
                {
                    gnametext.setText(list.get(0).getName()); 
                } 
            }
        }); 

        ctotal.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                panel1.setVisible(false);
                panel2.setVisible(false); 
                panel3.setVisible(false); 
                panel4.setVisible(true);
                panel5.setVisible(false);
                panel6.setVisible(false);


                Double gain=0.0;
                String result="";   
                for(int i=0; i<list.size( ); i++)
                {
                    result = result + "Investment symbol: " + list.get(i).getSymbol() + "    Total gain of Investment: " + Double.toString(list.get(i).getGain()) +"\n"; 
                    gain = gain + list.get(i).getGain();
                }
                messages4.setText(result); 
                String stringofgain= String.format("%.2f", gain); 
                tgaintext.setText(stringofgain); 
            }
        }); 

        csearch.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                panel1.setVisible(false);
                panel2.setVisible(false); 
                panel3.setVisible(false); 
                panel4.setVisible(false);
                panel5.setVisible(true);
                panel6.setVisible(false);
            }
        }); 

        cquit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        }); 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(700,500);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);    
    }


}

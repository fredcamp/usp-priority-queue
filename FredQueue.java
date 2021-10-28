package FredQueue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.PriorityQueue;
import java.util.Vector;
import javax.swing.*;

public class FredQueue extends JApplet{
   
    PriorityQueue priorityQueue;
    FredHandler handler=new FredHandler();
    private FredQueue mainapp;
    private FredPanel Fpanel;
    private JButton In, Out, Exit, Back, Display;
    private JLabel Name;
    private JTextField tf;
    private Vector list = new Vector(); 
    private Vector list2 = new Vector(); 
    private Enumeration enumerate, enumerate2; 
    private String name;
    private int xpos, ypos, choice=1, customercount=0;
    private boolean kill=false, kill2=false, displaylist=false;
    private int xpoints[] = {100, 380, 450, 25}, xpoints2[] = {85, 155, 195, 20};
    private int ypoints[] = {10, 10, 100, 100}, ypoints2[] = {270, 270, 330, 330};
    private int xpoints3[] = {320, 390, 430, 265}, ypoints3[]= {270, 270, 330, 330};
    private int npoints = 4, head=0, move=0;
    private Font font, font2, font0;
            
    @Override
    public void init(){
        Fpanel = new FredPanel(this);
        this.setSize(500, 500);
        setBackground(Color.white);
        
        In = new JButton("In");
        Out = new JButton("Out");
        Out.setEnabled(false);
        Exit = new JButton("Exit");
        Back = new JButton("Back");
        Display = new JButton("Display Queue");
        Display.setEnabled(false);
        In.addActionListener(handler);
        Out.addActionListener(handler);
        Exit.addActionListener(handler);
        Back.addActionListener(handler);
        Display.addActionListener(handler);
        Back.setVisible(false);
        
        Name = new JLabel("Name:");
        tf = new JTextField(10);
        tf.setText("");
        font = new Font("Arial",Font.BOLD,28);
        font2 = new Font("Arial",Font.BOLD,16);
        font0 = new Font("Arial",Font.PLAIN,12);
        priorityQueue = new PriorityQueue(20);
        
        Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        cont.add(panel,"South");
        
        JPanel panel1=new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(Color.white);
        panel.add(panel1,"North");
        panel1.add(Name);
        panel1.add(tf);
        
        JPanel panel2=new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.white);
        panel.add(panel2,"Center");
        panel2.add(In);
        panel2.add(Out);
        panel2.add(Display);
        panel2.add(Exit);
        panel2.add(Back);
        
        this.add(Fpanel);
    }
    
    public class FredPanel extends JPanel {
        public FredPanel(FredQueue o) {mainapp = o;}
        
        @Override
        public void paint(Graphics g) {
            if(displaylist!=true){
            g.setColor(Color.black);
            g.drawRect(-1, 330, 501, 100);
            g.fillRect(10, 375, 70, 10);
            g.fillRect(110, 375, 70, 10);
            g.fillRect(210, 375, 70, 10);
            g.fillRect(310, 375, 70, 10);
            g.fillRect(410, 375, 70, 10);
            
            g.setColor(new Color(90,99,20));
            g.fillRect(0, 195, 500, 135);
            
            g.setColor(new Color(0, 50, 90));
            g.fillRect(0,0,500,200);
            
            g.setColor(new Color(100,100,50));
            g.fillPolygon(xpoints2, ypoints2, npoints);
            g.fillPolygon(xpoints3, ypoints3, npoints);
                        
            g.setColor(new Color(100,100,100));
            g.fillPolygon(xpoints, ypoints, npoints);
            g.fillRect(65, 100, 347, 170);
                        
            g.setColor(new Color(0, 50, 60));
            g.fillRoundRect(135, 50, 220, 50, 50, 60);
            g.fillRect(135, 50, 220, 50);
            g.setColor(Color.red);
            g.setFont(font);
            g.drawString("FredLhuillier", 158, 85);
            g.setFont(font0);
            g.drawRoundRect(140, 55, 210, 40, 50, 60);
            
            g.setColor(new Color(70, 30, 70));
            g.fillRect(85, 170, 70, 100);
            g.fillRect(320, 170, 70, 100);
            
            g.setColor(Color.MAGENTA);
            g.fillOval(90, 210, 10, 10);
            g.fillOval(325, 210, 10, 10);
            
            g.setColor(new Color(90,90,90));
            g.drawLine(30, 99, 448, 99);
            
            enumerate = list.elements();
            enumerate2 = list2.elements();
            if(choice==1){
                try{
            while(enumerate.hasMoreElements()){
                ((Moving)enumerate.nextElement()).paint(g);
            }
                }catch(Exception e){}
        }else{
                try{
            while(enumerate2.hasMoreElements()){
                ((Moving2)enumerate2.nextElement()).paint(g);
            }    
            }catch (Exception e){}
            }
        }else{
        int xAXIS=80, yAXIS=120;
        g.setFont(font);
        g.fillRect(0, 0, 500, 500);
        g.setColor(new Color(30,50,70));
        g.fillRect(20, 30, 450, 370);
        g.setColor(new Color(10,90,10));
        g.fillRoundRect(78, 13, 354, 54, 44, 64);
        g.setColor(Color.white);
        g.fillRoundRect(80, 15, 350, 50, 40, 60);
        g.setColor(Color.red);
        g.drawRect(45, 75, 400, 300);
        g.setColor(Color.black);
        g.drawString("Lists in Priority Queues:", 100, 50);
        g.setColor(Color.white);
        g.setFont(font2);
        for (Object object : priorityQueue) {
            String tempo=object.toString();
            g.drawString("- "+tempo,xAXIS,yAXIS);
            yAXIS+=20;
            if(yAXIS==320){
                xAXIS+=50;
                yAXIS=120;
            }
            }
        }
}
    }
     
    class FredHandler implements ActionListener{
        @Override
    public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "In":
                    String str=tf.getText();
                    if(!str.isEmpty() && !str.equals(" ")){
                    choice=1;
                    kill=false;
                    kill2=true;
                    Sarado();
                    name=str;
                    Object obj = (Object)str;
                    priorityQueue.add(obj);
                    customercount++;
                    list.add(new Moving(mainapp, 500, 355));
                    }else{
                    JOptionPane.showMessageDialog(null,"Invalid input!","ERROR",JOptionPane.ERROR_MESSAGE);    
                    }
                    break;
                case "Out":
                    choice=2;
                    move=0;
                    kill2=false;
                    kill=true;
                    Object obj2=priorityQueue.poll();
                    name=obj2.toString();
                    Sarado();
                    head++;
                    customercount--;
                    list2.add(new Moving2(mainapp, 98, 200));
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                case "Back":
                    displaylist=false;
                    In.setVisible(true);
                    Out.setVisible(true);
                    Exit.setVisible(true);
                    Back.setVisible(false);
                    Name.setVisible(true);
                    Display.setVisible(true);
                    tf.setVisible(true);
                    mainapp.repaint();
                    break;
                case "Display Queue":
                    displaylist=true;
                    In.setVisible(false);
                    Out.setVisible(false);
                    Exit.setVisible(false);
                    Back.setVisible(true);
                    Name.setVisible(false);
                    Display.setVisible(false);
                    tf.setVisible(false);
                    mainapp.repaint();
                    break;
            }
        
    }
    }
    
    class Moving implements Runnable{
    private Thread th;
    private FredQueue fredQ;
    
    public Moving(FredQueue o, int x, int y) {
        fredQ = o;
        xpos=x;
        ypos=y;
        th = new Thread(this);
        th.start();   
    }
    
    @Override
    public void run() {
       try {
       while(kill!=true){
            if((xpos>334 && xpos<501)&& ypos==355){ 
                xpos--;
            }else if((ypos>200 && ypos<356) && xpos==334){
                ypos--;
            }
            th.sleep(15); 
            fredQ.repaint();
    }
    }catch (InterruptedException ex) { }
    }
    
    public void paint(Graphics g) {
            if((ypos>201 & ypos<246)){
            g.setColor(Color.white);
            g.fillRect(320, 170, 70, 100);
           }
            g.setColor(Color.white);
            g.fillOval(xpos, ypos, 40, 40);
            g.setColor(Color.black);
            g.drawOval(xpos, ypos, 40, 40);
            g.setColor(Color.BLUE);
            g.fillOval(xpos-5, ypos-10, 49, 39);
            g.fillOval(xpos+8, ypos-15, 20, 35);
            g.setColor(Color.red);
            g.fillOval(xpos+16, ypos, 10, 10);
            g.setColor(Color.black);
            g.drawLine(xpos+20, ypos+40, xpos+20, ypos+60);
            int forfeetX=xpos+20;
            int forfeetY=ypos+60;
            int forarmY=ypos+40;
            g.drawLine(forfeetX, forarmY, forfeetX-6, forarmY+6);
            g.drawLine(forfeetX, forarmY, forfeetX+6, forarmY+6);
            
            if(move<=8){
            g.drawLine(forfeetX, forfeetY, forfeetX-9, forfeetY-10);
            g.drawLine(forfeetX, forfeetY, forfeetX+11, forfeetY+14);
            move++;
            }else if(move>=9 && move<=17){
            g.drawLine(forfeetX, forfeetY, forfeetX-11, forfeetY+14);
            g.drawLine(forfeetX, forfeetY, forfeetX+9, forfeetY-10);
            move++;
            }else{
                move=0;
            }
            g.drawString(name, (xpos+(20/(name.length())))+4, ypos-25);
            
            if(ypos==200){
            kill=true;
            move=0;
            g.setColor(Color.black);
            g.drawRect(-1, 330, 501, 100);
            g.fillRect(10, 375, 70, 10);
            g.fillRect(110, 375, 70, 10);
            g.fillRect(210, 375, 70, 10);
            g.fillRect(310, 375, 70, 10);
            g.fillRect(410, 375, 70, 10);
            g.setColor(new Color(90,99,20));
            g.fillRect(0, 195, 500, 135);
            g.setColor(new Color(0, 50, 90));
            g.fillRect(0,0,500,200);
            g.setColor(new Color(100,100,50));
            g.fillPolygon(xpoints2, ypoints2, npoints);
            g.fillPolygon(xpoints3, ypoints3, npoints);
            g.setColor(new Color(100,100,100));
            g.fillPolygon(xpoints, ypoints, npoints);
            g.fillRect(65, 100, 347, 170);
            g.setColor(new Color(0, 50, 60));
            g.fillRoundRect(135, 50, 220, 50, 50, 60);
            g.fillRect(135, 50, 220, 50);
            g.setColor(Color.red);
            g.setFont(font);
            g.drawString("FredLhuillier", 158, 85);
            g.setFont(font0);
            g.drawRoundRect(140, 55, 210, 40, 50, 60);
            g.setColor(new Color(70, 30, 70));
            g.fillRect(85, 170, 70, 100);
            g.fillRect(320, 170, 70, 100);
            g.setColor(Color.MAGENTA);
            g.fillOval(90, 210, 10, 10);
            g.fillOval(325, 210, 10, 10);
            g.setColor(new Color(90,90,90));
            g.drawLine(30, 99, 448, 99);
            Abree();
            }
    }   
    }
    
    class Moving2 implements Runnable{
    private Thread th;
    private FredQueue fredQ;
    
    public Moving2(FredQueue o, int x, int y) {
        fredQ = o;
        xpos=x;
        ypos=y;
        th = new Thread(this);
        th.start();
    }
    
    @Override
    public void run() {
         try {
       while(kill2!=true){
           if((ypos>199 && ypos<356) && xpos==98){
                ypos++;
            }else if((xpos>-60 && xpos<99) && ypos==356){
                xpos--;
            }
            th.sleep(10);
            fredQ.repaint();
    }
    }catch (InterruptedException ex) { }
    }
    
    public void paint(Graphics g) {
        try{
        if((ypos>201 & ypos<246)){
            g.setColor(Color.white);
            g.fillRect(85, 170, 70, 100);
           }
            g.setColor(Color.white);
            g.fillOval(xpos, ypos, 40, 40);
            g.setColor(Color.black);
            g.drawOval(xpos, ypos, 40, 40);
            g.setColor(Color.BLUE);
            g.fillOval(xpos-5, ypos-10, 49, 39);
            g.fillOval(xpos+9, ypos+2, 20, 35);
            g.setColor(Color.red);
            g.fillOval(xpos+16, ypos, 10, 10);
            g.setColor(Color.black);
            g.drawLine(xpos+20, ypos+40, xpos+20, ypos+60);
            int forfeetX=xpos+20;
            int forfeetY=ypos+60;
            int forarmY=ypos+40;
            g.drawLine(forfeetX, forarmY, forfeetX-6, forarmY+6);
            g.drawLine(forfeetX, forarmY, forfeetX+6, forarmY+6);
            
            if(move<=8){
            g.drawLine(forfeetX, forfeetY, forfeetX-9, forfeetY-10);
            g.drawLine(forfeetX, forfeetY, forfeetX+11, forfeetY+14);
            move++;
            }else if(move>=9 && move<=17){
            g.drawLine(forfeetX, forfeetY, forfeetX-11, forfeetY+14);
            g.drawLine(forfeetX, forfeetY, forfeetX+9, forfeetY-10);
            move++;
            }else{
                move=0;
            }
            g.drawString(name, (xpos+(20/(name.length())))+4, ypos-25);
        
        if(xpos==-60 && ypos==356){
            kill2=true;
            move=0;
            Abree();
            g.setColor(new Color(70, 30, 70));
            g.fillRect(85, 170, 70, 100);
            g.setColor(Color.MAGENTA);
            g.fillOval(90, 210, 10, 10);
        }
    }catch(Exception e){}     
    }
}
    
    public void Abree(){
        tf.setText("");
        In.setEnabled(true);
        Exit.setEnabled(true);
        tf.setEnabled(true);
        if(customercount!=0){
        Out.setEnabled(true);
        Display.setEnabled(true);
        }
    }
    public void Sarado(){
        In.setEnabled(false);
        Out.setEnabled(false);
        Exit.setEnabled(false);
        Display.setEnabled(false);
        tf.setEnabled(false);
    }
}
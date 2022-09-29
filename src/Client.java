import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import  java.net.*;
import java.io.*;

import static java.lang.System.out;


//public class Client {
public class Client implements ActionListener {
    JTextField text1;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f;

    Client() {
        f = new JFrame();
        f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        f.add(p1);


        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));

            p1.setLayout(null);
            Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel back = new JLabel(i3);
            back.setBounds(5, 20, 25, 25);
            p1.add(back);
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }
            });

        } catch (NullPointerException n) {

        }
        try {
            ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));

            //p1.setLayout(null);
            Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon i6 = new ImageIcon(i5);
            JLabel profile = new JLabel(i6);
            profile.setBounds(40, 10, 50, 50);
            p1.add(profile);
        } catch (NullPointerException n) {

        }
        try {
            ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));

            // p1.setLayout(null);
            Image i8 = i7.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon i9 = new ImageIcon(i8);
            JLabel video = new JLabel(i9);
            video.setBounds(300, 20, 40, 30);
            p1.add(video);
        } catch (NullPointerException n) {

        }
        try {
            ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));

            // p1.setLayout(null);
            Image i11 = i10.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon i12 = new ImageIcon(i11);
            JLabel phone = new JLabel(i12);
            phone.setBounds(370, 20, 40, 30);
            p1.add(phone);
        } catch (NullPointerException n) {

        }
        try {
            ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));

            // p1.setLayout(null);
            Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
            ImageIcon i15 = new ImageIcon(i14);
            JLabel morevert = new JLabel(i15);
            morevert.setBounds(430, 20, 10, 25);
            p1.add(morevert);
        } catch (NullPointerException n) {

        }
        JLabel name = new JLabel("Lajwanti");
        name.setBounds(110, 15, 100, 30);
        p1.add(name);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SARIF", Font.BOLD, 18));


        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 30);
        p1.add(status);
        status.setForeground(Color.white);
        status.setFont(new Font("SAN_SARIF", Font.BOLD, 14));

        a1 = new JPanel();
        a1.setBounds(5, 70, 440, 500);
        f.add(a1);

        text1 = new JTextField();
        text1.setBounds(5, 590, 310, 40);
        text1.setFont(new Font("SAN_SARIF", Font.PLAIN, 16));
        f.add(text1);


        JButton send = new JButton("Send");
        send.setBounds(320, 590, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.white);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SARIF", Font.PLAIN, 16));
        f.add(send);

        f.setUndecorated(true);


        f.setSize(460, 700);
        f.setLocation(800, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.white);


        f.setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        try {
            String out1 = text1.getText();


            JPanel p2 = Server.formatLabel(out1);

            a1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical, BorderLayout.PAGE_START);
            dout.writeUTF(out1);
            text1.setText("");
            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //catch (NullPointerException E){
        //}

    }

    public static JPanel formatLabel(String out1) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out1 + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        panel.add(output);
         return panel;

    }



    public static void main(String[] args){

        new Client();
        try {
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while (true) {
                a1.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = Server.formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                f.validate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}




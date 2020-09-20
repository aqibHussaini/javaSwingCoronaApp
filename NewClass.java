/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import com.google.gson.Gson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Bhatti
 */
public class NewClass {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.covid19api.com/summary")).GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
        JButton next = new JButton("Get Country");
        JFrame jf = new JFrame("Corona Data..");
        jf.setLayout(new GridLayout(2, 1));
        jf.setSize(600, 600);
        jf.setDefaultCloseOperation(3);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(5, 1));
        JLabel worlddata = new JLabel("World Data");
        JLabel totalD = new JLabel("TotalConfirmed");
        JLabel totalR = new JLabel("TotalDeaths");
        JLabel totalC = new JLabel("TotalRecovered");
        jp.add(worlddata);
        jp.add(totalD);
        jp.add(totalR);
        jp.add(totalC);
        jp.add(next);
        JPanel jp1 = new JPanel();
        ImageIcon image = new ImageIcon("C:\\Users\\Bhatti\\Documents\\NetBeansProjects\\mavenproject10\\JavaApplication6\\src\\javaapplication6\\images (1).jpg");
        JLabel pic = new JLabel(image);
        pic.setLocation(image.getIconWidth(), image.getIconHeight());
        jp1.add(pic);
        jf.add(jp1);
        Gson son = new Gson();
        CData data = son.fromJson(res.body().toString(), CData.class);
        totalC.setText("Total Confirmed " + data.getGlobal().getTotalConfirmed());
        totalD.setText("Total Deaths " + data.getGlobal().getTotalDeaths());
        totalR.setText("Total Recovered " + data.getGlobal().getTotalRecovered());
        totalC.setHorizontalAlignment(SwingConstants.CENTER);
        totalD.setHorizontalAlignment(SwingConstants.CENTER);
        totalR.setHorizontalAlignment(SwingConstants.CENTER);
        totalC.setFont(new Font("Poppins", Font.BOLD, 30));
        totalD.setFont(new Font("Poppins", Font.BOLD, 30));
        totalR.setFont(new Font("Poppins", Font.BOLD, 30));
        worlddata.setFont(new Font("Poppins", Font.BOLD, 30));
        worlddata.setHorizontalAlignment(SwingConstants.CENTER);
        next.setFont(new Font("Poppins", Font.BOLD, 30));

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jtable jt = new jtable();
                    jf.setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jf.add(jp);
        jf.setVisible(true);
    }

}

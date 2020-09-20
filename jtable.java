/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author Bhatti
 */
import com.google.gson.Gson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class jtable extends JFrame {

    public static void main(String[] a) throws Exception {

    }

    public jtable() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.covid19api.com/summary")).GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
        String[] tblHead = {"#", "country", "TotalConfirmed", "TotalDeaths", "TotalRecovered", "newConfirmed", "NewDeaths", "NewRecovered"};
        DefaultTableModel dtm = new DefaultTableModel(tblHead, 0);
        JTable jt = new JTable(dtm);

        // setting table header decoration
        jt.getTableHeader().setOpaque(false);
        jt.getTableHeader().setBackground(new Color(32, 136, 203));
        jt.getTableHeader().setForeground(new Color(255, 255, 255));
        // table body decoration
        jt.setRowHeight(35);
        jt.getTableHeader().setFont(new Font("Segoe", Font.BOLD, 14));
        jt.setFont(new Font("poppins", Font.BOLD, 14));
        Gson son = new Gson();
        CData data = son.fromJson(res.body().toString(), CData.class);
        Countries[] c = data.getCountries();
        int i = 0;
        for (Countries con : c) {
            i++;
            Object line[] = {
                i,
                con.getCountry(),
                con.getTotalConfirmed(),
                con.getTotalDeaths(),
                con.getTotalRecovered(),
                con.getNewConfirmed(),
                con.getNewDeaths(),
                con.getNewRecovered()

            };
            dtm.addRow(line);

        }
        JScrollPane sp = new JScrollPane(jt);

        this.setSize(1200, 700);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        jt.setFont(new Font("Poppins", Font.BOLD, 15));
        this.add(sp, BorderLayout.CENTER);
    }

}

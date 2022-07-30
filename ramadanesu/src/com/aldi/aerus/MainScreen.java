package com.aldi.aerus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private static final  String URL = "jdbc:mysql://localhost:3306/aerus";
    private static final String USER ="root";
    private static final String PASSWORD ="";
    private JPanel panelMain;
    private JTable jTableMahasiswa;
    private JTextField textfilter;
    private JButton filterButton;
    private JTextField textnim;
    private JTextField textnama;
    private JTextField textipk;
    private JButton ADDButton;
    private JButton btnUPDATE;
    private JButton btnDELETE;
    private JButton btnCLEAR;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        refresTable(getMahasiswa());
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textnama.getText();
                String nim = textnim.getText();
                double ipk = Double.parseDouble(textipk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                clearform();
                insertMahasiswa(mahasiswa);
                refresTable(getMahasiswa());
            }
        });
        jTableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = jTableMahasiswa.getSelectedRow();

                String nim = jTableMahasiswa.getValueAt(row,0).toString();
                String nama = jTableMahasiswa.getValueAt(row,1).toString();
                String ipk = jTableMahasiswa.getValueAt(row,2).toString();

                textnim.setText(nim);
                textnama.setText(nama);
                textipk.setText(ipk);
            }
        });
        btnUPDATE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textnama.getText();
                String nim = textnim.getText();
                double ipk = Double.parseDouble(textipk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);
                clearform();
                updateMahasiswa(mahasiswa);
                refresTable(getMahasiswa());

            }
        });
        btnDELETE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = textnim.getText();
                deleteMahasiswa(nim);
                refresTable(getMahasiswa());
            }
        });
        btnCLEAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearform();
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textfilter.getText();
                refresTable(filterMahasiswa(nama));
            }
        });
    }
private static ResultSet executeQuery(String query){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
        catch (Exception e){
            return null;
        }
}

private static void executeSql (String sql){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){

        }
}

public void refresTable(List<Mahasiswa> arraylistMahasiswa){
        Object data [][] = new Object[arraylistMahasiswa.size()][3];

    for (int i = 0; i < arraylistMahasiswa.size(); i++) {
        data [i] = new Object[]{
                arraylistMahasiswa.get(i).getNim(),
                arraylistMahasiswa.get(i).getNama(),
                arraylistMahasiswa.get(i).getIpk()
        };

    }
    defaultTableModel = new DefaultTableModel(data, new String[]{"nim","nama","ipk"});
    jTableMahasiswa.setModel(defaultTableModel);
}
private static void insertMahasiswa(Mahasiswa mahasiswa){
        String sql = "insert into mahasiswa values(" +
                "'" + mahasiswa.getNim() + "'," +
                "'" + mahasiswa.getNama() + "'," +
                "'" + mahasiswa.getIpk() + "')";
        executeSql(sql);

}
private static void updateMahasiswa(Mahasiswa mahasiswa){
        String sql = "update mahasiswa set " +
                "nama = '" + mahasiswa.getNama() + "'," +
                "ipk = '" + mahasiswa.getIpk() + "'" +
                "where nim = '" + mahasiswa.getNim() +"'";
        executeSql(sql);
}

private static void  deleteMahasiswa(String nim){
        String sql = "delete from mahasiswa " +
                "where nim = '" + nim + "'";
        executeSql(sql);
}

private static List<Mahasiswa> getMahasiswa(){
        List<Mahasiswa> arraylistMahasiswa = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from mahasiswa");

        try {
            while (resultSet.next()){
                String nim = resultSet.getString("nim");
                String nama = resultSet.getString("nama");
                double ipk = Double.parseDouble(resultSet.getString("ipk"));

                System.out.println(nama);
                System.out.println(nim);
                System.out.println(ipk);

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                arraylistMahasiswa.add(mahasiswa);
            }
        }
        catch (Exception e){
            return null;
        }
        return arraylistMahasiswa;
}

    private static List<Mahasiswa> filterMahasiswa(String filternama){
        List<Mahasiswa> arraylistMahasiswa = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from mahasiswa where nama like '%" + filternama +"%'");

        try {
            while (resultSet.next()){
                String nim = resultSet.getString("nim");
                String nama = resultSet.getString("nama");
                double ipk = Double.parseDouble(resultSet.getString("ipk"));

                System.out.println(nama);
                System.out.println(nim);
                System.out.println(ipk);

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                arraylistMahasiswa.add(mahasiswa);
            }
        }
        catch (Exception e){
            return null;
        }
        return arraylistMahasiswa;
    }

    private void clearform(){
        textnama.setText("");
        textipk.setText("");
        textnim.setText("");
    }

    public static void main(String[] args) {
       MainScreen mainScreen = new MainScreen();
       mainScreen.setVisible(true);
    }
}


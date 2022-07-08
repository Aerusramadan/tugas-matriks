package ramadanaerus;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel PanelMain;
    private JButton filterButton;
    private JTextField textFieldfilter;
    private JList jlistMahasiswa;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldIpk;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;

    private List<Mahasiswa> arraydata = new ArrayList<>();

    private DefaultListModel defaultListModel = new DefaultListModel<>();

class Mahasiswa{
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public float getIpk() {
        return ipk;
    }

    public void setIpk(float ipk) {
        this.ipk = ipk;
    }

    private String nim;
    private float ipk;
}


    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(PanelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nama = textFieldNama.getText();
                String nim = textFieldNim.getText();
                Float ipk = Float.valueOf(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                arraydata.add(mahasiswa);

                setClearButton();

                fromMahasiswaToListMode();

            }
        });

        jlistMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = jlistMahasiswa.getSelectedIndex();

                if (index <0)
                    return;

                String nama = jlistMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size() ; i++) {

                    if (arraydata.get(i).getNama().equals(nama)){
                        Mahasiswa mahasiswa =  arraydata.get(i);
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        break;
                    }
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClearButton();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jlistMahasiswa.getSelectedIndex();

                if (index <0)
                    return;

                String nama = jlistMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().equals(nama)){
                        arraydata.remove(i);
                        break;
                    }
                }
                setClearButton();
                fromMahasiswaToListMode();
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = textFieldfilter.getText();
                List<String>  filtered = new ArrayList<>();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().contains(keyWord)){
                        filtered.add(arraydata.get(i).getNama());
                    }
                }
                refreslistModel(filtered);
            }
        });
    }
    private void fromMahasiswaToListMode(){

    List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arraydata.size() ; i++) {
           listNamaMahasiswa.add(arraydata.get(i).getNama());
        }
        refreslistModel(listNamaMahasiswa);
    }

    private void setClearButton(){
    textFieldNama.setText("");
    textFieldNim.setText("");
    textFieldIpk.setText("");
    }

    private void refreslistModel(List<String> list){
    defaultListModel.clear();
    defaultListModel.addAll(list);
    jlistMahasiswa.setModel(defaultListModel);
    }



    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}

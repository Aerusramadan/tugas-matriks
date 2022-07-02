package COM;
import java.util.ArrayList;
import java.util.Scanner;
public class AERUS {

    public static void main(String[] args) {
        ArrayList<String> NAMA = new ArrayList<>();
        ArrayList<String> NIM = new ArrayList<>();
        ArrayList<String> ALAMAT = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        int PILIHAN;

        do {
            System.out.println("\nprogram data mahasiswa");
            System.out.println("---------------------------");
            System.out.println("1.tambahkan");
            System.out.println("2.tampilkan");
            System.out.println("3.cari");
            System.out.println("4.hapus");
            System.out.println("5.keluar");
            System.out.print("\npilih menu:");
            PILIHAN = input.nextInt();

            if (PILIHAN == 1) {
                System.out.print("masukan nama : ");
                String nama = input.next();
                NAMA.add(nama);

                System.out.print("masukan nim:");
                String nim = input.next();
                NIM.add(nim);

                System.out.print("masukan alamat:");
                String alamat = input.next();
                ALAMAT.add(alamat);


            } else if (PILIHAN == 2) {
                System.out.println("data mahasiswa");
                System.out.println("--------------------");

                for (int i = 0; i < NIM.size(); i++) {
                    System.out.println(i +1+ "." + NIM.get(i));
                }

                System.out.println("untuk melihat data lengkap, cari data 3");

            }else if (PILIHAN == 3){
                System.out.println("masukan data nomor berpa yang ingin di cari:");
                int cari = input.nextInt();
                if (cari == 1){
                    System.out.println("nama : "+ NAMA.get(0));
                    System.out.println("NIM : "+ NIM.get(0));
                    System.out.println("ALAMAT: "+ ALAMAT.get(0));

                }else if (cari == 2){
                    System.out.println("data mahasiswa");
                    System.out.println("------------------");

                    for (int i = 0; i < NIM.size(); i++) {
                        System.out.println(i + "." + NIM.get(i));
                    }
                    System.out.println("untuk melihat data lengkap cari data3");
                }else if (cari == 3){
                    System.out.println("masukan data yang ingin di cari:");
                    if (cari == 1){
                        System.out.println("nama : "+ NAMA.get(0));
                        System.out.println("NIM : "+ NIM.get(0));
                        System.out.println("ALAMAT: "+ ALAMAT.get(0));
                    }
                    System.out.println("------------------------");
                }if (cari == 2){
                    System.out.println("nama : "+ NAMA.get(1));
                    System.out.println("NIM : "+ NIM.get(1));
                    System.out.println("ALAMAT: "+ ALAMAT.get(1));
                }
            }else if (PILIHAN == 4){
                System.out.println("data mahasiswa");
                System.out.println("----------------------");
                for (int i = 0; i < NIM.size(); i++) {
                    System.out.println(i + "." + NIM.get(i));
                }
                System.out.println("masukan NIM YANG AKAN DI HAPUS:");
                String nim =input.next();
                NIM.remove(nim);
            }else if (PILIHAN ==5){
                System.out.println("selesai");
            }


        } while (PILIHAN != 5);
    }
}

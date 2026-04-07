package KuisUtp;

import java.util.*;

class Student {
    protected String nama;
    protected int saldo;

    public Student(String nama) {
        this.nama = nama;
        this.saldo = 0;
    }

    public String getNama() {
        return nama;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getTipe() {
        return "";
    }

    public void save(int jumlah) {
        saldo += jumlah;
    }

    public boolean take(int jumlah) {
        if (saldo >= jumlah) {
            saldo -= jumlah;
            return true;
        }
        return false;
    }
}

// Kelas REGULER
class Reguler extends Student {

    public Reguler(String nama) {
        super(nama);
    }

    @Override
    public String getTipe() {
        return "REGULER";
    }
}

// Kelas BEASISWA
class Beasiswa extends Student {

    public Beasiswa(String nama) {
        super(nama);
    }

    @Override
    public String getTipe() {
        return "BEASISWA";
    }

    @Override
public boolean take(int jumlah) {
    if (saldo >= jumlah) {
        int bayar = jumlah - 1000;
        if (bayar < 0) bayar = 0;

        saldo -= bayar;
        return true;
        }
        return false;
    }
}

public class tabungan {

    public static Student cariStudent(ArrayList<Student> list, String nama) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNama().equals(nama)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static boolean check(ArrayList<Student> list, String nama) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNama().equals(nama)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();

        ArrayList<Student> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = in.nextLine();
            String[] parts = input.split(" ");
            String perintah = parts[0];

            if (perintah.equals("CREATE")) {
                String tipe = parts[1];
                String nama = parts[2];

                if (check(list, nama)) {
                    System.out.println("Akun sudah terdaftar");
                } else {
                    if (tipe.equals("REGULER")) {
                        list.add(new Reguler(nama));
                    } else {
                        list.add(new Beasiswa(nama));
                    }
                    System.out.println(tipe + " " + nama + " berhasil dibuat");
                }

            } else if (perintah.equals("SAVE")) {
                String nama = parts[1];
                int jumlah = Integer.parseInt(parts[2]);

                Student cek = cariStudent(list, nama);

                if (cek == null) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    cek.save(jumlah);
                    System.out.println("Saldo " + nama + ": " + cek.getSaldo());
                }

            } else if (perintah.equals("TAKE")) {
                String nama = parts[1];
                int jumlah = Integer.parseInt(parts[2]);

                Student cek = cariStudent(list, nama);

                if (cek == null) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    boolean sukses = cek.take(jumlah);
                    if (!sukses) {
                        System.out.println("Saldo " + nama + " tidak cukup");
                    } else {
                        System.out.println("Saldo " + nama + ": " + cek.getSaldo());
                    }
                }

            } else if (perintah.equals("CHECK")) {
                String nama = parts[1];

                Student cek = cariStudent(list, nama);

                if (cek != null) {
                    System.out.println(nama + " | " + cek.getTipe() + " | saldo: " + cek.getSaldo());
                }
            }
        }
    }
}
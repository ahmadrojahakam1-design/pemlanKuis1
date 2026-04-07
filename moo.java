package KuisUtp;

import java.util.*;

public class moo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String nama;
        int berat = 0;
        String layanan;
        String kelas;

        while (true) {
            nama = in.nextLine();

            if (!nama.matches("[a-zA-Z]+")) {
                System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
            } else {
                break;
            }
        }

        while (true) {
            try {
                berat = Integer.parseInt(in.nextLine());

                if (berat < 1) {
                    System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            }
        }

        while (true) {
            layanan = in.nextLine();

            if (!layanan.equals("spa") &&
                !layanan.equals("potong_kuku") &&
                !layanan.equals("grooming")) {

                System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
            } else {
                break;
            }
        }

        while (true) {
            kelas = in.nextLine();

            if (!kelas.equals("reguler") && !kelas.equals("vip")) {
                System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
            } else {
                break;
            }
        }

        double harga = 0;

        if (layanan.equals("spa")) {
            harga = 8000;
        } else if (layanan.equals("potong_kuku")) {
            harga = 6000;
        } else if (layanan.equals("grooming")) {
            harga = 10000;
        }

        double biayaDasar = berat * harga;
        double diskon = 0;
        double tambahanVIP = 0;

        if (berat > 30) {
            diskon = 0.1 * biayaDasar;
        }

        if (kelas.equals("vip")) {
            tambahanVIP = 0.2 * biayaDasar;
        }

        double subtotal = biayaDasar - diskon + tambahanVIP;
        double pajak = 0.08 * subtotal;
        double total = subtotal + pajak;

        boolean gratis = nama.equals("Moo") || nama.equals("Mooo") || nama.equals("Moooo");

        if (gratis) {
            total = 0;
        }

        System.out.println("============= NOTA KLINIK SAPI =============");
        System.out.println("Nama Sapi: " + nama);
        System.out.println("Berat: " + berat + " kg");
        System.out.println("Jenis Layanan: " + layanan);
        System.out.println("Kelas: " + kelas);
        System.out.println("Biaya Dasar: Rp " + biayaDasar);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Biaya Tambahan VIP: Rp " + tambahanVIP);
        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Pajak: Rp " + pajak);
        System.out.println("Total Biaya: Rp " + total);
        System.out.println("============================================");

        if (gratis) {
            System.out.println("Terima kasih, " + nama + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + nama + " ! Semoga sapinya makin glow up.");
        }
    }
}

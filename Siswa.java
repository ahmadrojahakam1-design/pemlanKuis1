package KuisUtp;

import java.util.*;
public class Siswa {
    public static Vehicle cariVehicle(ArrayList<Vehicle> list, String kode) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKode().equals(kode)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static boolean Check(ArrayList<Vehicle> list, String kode) {
        return cariVehicle(list, kode) != null;
    }

    //Main Program
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();

        ArrayList<Vehicle> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = in.nextLine().trim();
            String[] bagian = input.split(" ");
            String perintah = bagian[0];

            if (perintah.equals("ADD")) {
                String tipe = bagian[1];
                String kode = bagian[2];
                String nama = bagian[3];
                int harga = Integer.parseInt(bagian[4]);

                if (Check(list, kode)) {
                    System.out.println("Kendaraan sudah terdaftar");
                } else {
                    if (tipe.equals("CAR")) {
                        list.add(new Car(kode, nama, harga));
                    } else if (tipe.equals("BIKE")) {
                        list.add(new Bike(kode, nama, harga));
                    }
                    System.out.println(tipe + " " + kode + " berhasil ditambahkan");
                }
            }

            else if (perintah.equals("RENT")) {
                String kode = bagian[1];
                int hari = Integer.parseInt(bagian[2]);
                boolean promo = false;
            if (bagian.length > 3) {
                if (bagian[3].equalsIgnoreCase("PROMO")) {
                    promo = true;
                }
            }

                Vehicle kendaraan = cariVehicle(list, kode);

                if (kendaraan == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else if (!kendaraan.isTersedia()) {
                    System.out.println("Kendaraan sedang disewa");
                } else {
                    int total = kendaraan.hitungSewa(hari, promo);
                    kendaraan.setTersedia(false);
                    System.out.println("Total sewa " + kode + ": " + total);
                }
            }

            else if (perintah.equals("RETURN")) {
                String kode = bagian[1];
                Vehicle v = cariVehicle(list, kode);

                if (kendaraan == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else if (kendaraan.isTersedia()) {
                    System.out.println("Kendaraan belum disewa");
                } else {
                    kendaraan.setTersedia(true);
                    System.out.println(kode + " berhasil dikembalikan");
                }
            }

            else if (perintah.equals("DETAIL")) {
                String kode = bagian[1];
                Vehicle kendaraan = cariVehicle(list, kode);

                if (kendaraan == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else {

                    String status;
                if (kendaraan.isTersedia()) {
                    status = "TERSEDIA";
                } else {
                    status = "DISEWA";
                }
                    System.out.println(
                        kendaraan.getKode() + " | " +
                        kendaraan.getTipe() + " | " +
                        kendaraan.getNama() + " | harga: " +
                        kendaraan.getHarga() +" | status: " + status
                    );
                }
            }

            else if (perintah.equals("COUNT")) {
                System.out.println("Total kendaraan: " + list.size());
            }
        }
    }
}

    //Kelas Induk
    class Vehicle {
        private String kode;
        private String nama;
        private int harga;
        private boolean tersedia;

        public Vehicle(String kode, String nama, int harga) {
            this.kode = kode;
            this.nama = nama;
            this.harga = harga;
            this.tersedia = true;
        }

        public String getKode() { 
            return kode; 
        }
        public String getNama() { 
            return nama; 
        }
        public int getHarga() { 
            return harga; 
        }
        public boolean isTersedia() { 
            return tersedia; 
        }
        public void setTersedia(boolean status) { 
            this.tersedia = status; 
        }

        public String getTipe() { 
            return ""; 
        }

        public int hitungSewa(int hari, boolean promo) {
            return 0;
        }
    }

    //Kelas Car
    class Car extends Vehicle {

        public Car(String kode, String nama, int harga) {
            super(kode, nama, harga);
        }

        @Override
        public String getTipe() {
            return "CAR";
        }

        @Override
        public int hitungSewa(int hari, boolean promo) {
            int total = getHarga() * hari;
            if (promo) {
                total -= 20000;
            }
            if (total < 0) total = 0;
                return total;
        }
    }

    //Kelas Bike
    class Bike extends Vehicle {
        public Bike(String kode, String nama, int harga) {
            super(kode, nama, harga);
        }

        @Override
        public String getTipe() {
            return "BIKE";
        }

        @Override
        public int hitungSewa(int hari, boolean promo) {
            int total = getHarga() * hari;
            if (promo) {
                total -= 10000;
            }
            if (total < 0) total = 0;
                return total;
        }
    }

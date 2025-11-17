package com.github.affandes.kuliah.pm;

import java.util.Stack;
import java.util.Scanner;
import java.util.Collections;

public class Main {

    private static Stack<String> historyStack = new Stack<>();

    /**
     * Fungsi untuk menampilkan semua history browser yang diurutkan dari yang paling baru.
     */
    public static void view() {
        System.out.println("\n--- History Browser (Terbaru ke Terlama) ---");
        if (historyStack.isEmpty()) {
            System.out.println("History kosong.");
            return;
        }

        Object[] historyArray = historyStack.toArray();
        for (int i = historyArray.length - 1; i >= 0; i--) {
            System.out.println((historyArray.length - i) + ". " + historyArray[i]);
        }
        System.out.println("--------------------------------------------");
    }

    /**
     * Fungsi untuk menambahkan website baru ke dalam daftar history.
     * @param url Website URL yang akan ditambahkan.
     */
    public static void browse(String url) {
        historyStack.push(url);
        System.out.println("-> Berhasil mengunjungi: " + url);
    }

    /**
     * Fungsi untuk kembali ke website sebelumnya dan menghapus history terakhir.
     * @return URL website sebelumnya, atau pesan jika history kosong.
     */
    public static String back() {
        if (historyStack.isEmpty()) {
            return "History kosong. Tidak bisa kembali.";
        }

        // Menghapus history terakhir (pop)
        String lastUrl = historyStack.pop();
        System.out.println("<- Kembali dari: " + lastUrl);

        if (historyStack.isEmpty()) {
            return "Anda berada di halaman awal.";
        } else {
            // Website saat ini adalah elemen teratas yang baru
            return "Sekarang berada di: " + historyStack.peek();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("Selamat datang di Browser History Simulator (Menggunakan Stack)");

        // Contoh penggunaan awal
        browse("https://google.com");
        browse("https://wikipedia.org");
        browse("https://openai.com");
        view();

        while (choice != 0) {
            System.out.println("\n============================================");
            System.out.println("Pilih Aksi:");
            System.out.println("1. Browse (Kunjungi Website Baru)");
            System.out.println("2. Back (Kembali ke Halaman Sebelumnya)");
            System.out.println("3. View (Tampilkan Semua History)");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1; // Set to invalid choice
            }

            switch (choice) {
                case 1:
                    System.out.print("Masukkan URL website: ");
                    String url = scanner.nextLine();
                    if (!url.trim().isEmpty()) {
                        browse(url);
                    } else {
                        System.out.println("URL tidak boleh kosong.");
                    }
                    break;
                case 2:
                    System.out.println(back());
                    break;
                case 3:
                    view();
                    break;
                case 0:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }
}

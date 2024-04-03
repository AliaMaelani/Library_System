package Semester2;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class LoanRecord {
    private Book book;
    private String borrowerName;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private boolean returned;

    public LoanRecord(Book book, String borrowerName, String borrowDate, String dueDate) {
        this.book = book;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returned = false;
    }
    public boolean isBookReturned() {
    return returned;
}

    public Book getBook() {
        return book;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
    public void setBorrowerName(String borrowerName2) {
    }
}

public class LibrarySystem {
    private static String fullName;
    private static String npm;
    private static String phoneNumber;
    private static String address;

    private static final int LOAN_DURATION_DAYS = 7;
    private static final int FINE_PER_DAY = 1000;
    
    private static Map<String, String> registeredUsers = new HashMap<>();
    private static Map<String, LoanRecord> loanRecords = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
    
            switch (choice) {
                case 1:
                    displayLibraryProfile();
                    break;
                case 2:
                    performRegistration();
                    break;
                case 3:
                    performLogin();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan layanan perpustakaan Ava!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║               PERPUSTAKAAN ONLINE AVA            ║");
        System.out.println("║               Jl. H.S. Ronggo Waluyo             ║");
        System.out.println("║                  Kab. Karawang                   ║");
        System.out.println("║               Telp. 021-8975451956               ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║                       MENU                       ║");
        System.out.println("║ 1. Profil Perpustakaan                           ║");
        System.out.println("║ 2. Registrasi                                    ║");
        System.out.println("║ 3. LogIn                                         ║");
        System.out.println("║ 0. Keluar                                        ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.print("Masukkan Pilihan: ");                              
        
    }
    private static void displayLibraryProfile() {
        System.out.println("Selamat datang di Perpustakaan AVA, tempat di mana kata-kata bersahabat dan ilmu berpadu menjadi simfoni pengetahuan.");
        System.out.println("Di sini, pintu gerbang ke dunia tak terbatas terbuka lebar bagi pencinta buku sejati dan penjelajah pikiran.");
        System.out.println("Dengan koleksi yang melimpah dan atmosfer yang memikat, kami menjembatani generasi,");
        System.out.println(" menghidupkan imajinasi, dan mengukir jejak keabadian di halaman-halaman sejarah. ");
        System.out.println("Bergabunglah dengan kami, dan bersama-sama kita akan menerangi masa depan dengan kebijaksanaan literasi."); 
        System.out.println("Selamat berpetualang dalam dunia kata-kata yang tak terhingga!");
    }

    private static void performRegistration() {
        System.out.println("Registrasi");
        System.out.println("------------------------------------------------------");
        System.out.println("Silahkan buat akun terlebih dahulu!");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        registeredUsers.put(username, password);

        System.out.println("------------------------------------------------------");
        System.out.println("Akun berhasil dibuat!");

        System.out.println("Silahkan isi detail registrasi untuk akun " + username);
        System.out.print("Nama Lengkap: ");
        fullName = scanner.nextLine();

        System.out.print("NPM: ");
        npm = scanner.nextLine();

        System.out.print("No HP: ");
        phoneNumber = scanner.nextLine();

        System.out.print("Alamat: ");
        address = scanner.nextLine();

        System.out.println("------------------------------------------------------");
        System.out.println("Selamat, registrasi Anda telah berhasil!");
        System.out.println("Halo " + username + ", selamat datang di perpustakaan AVA");
        System.out.println("------------------------------------------------------");

    }
    private static boolean isUserRegistered(String username, String password) {
    String registeredPassword = registeredUsers.get(username);
    return registeredPassword != null && registeredPassword.equals(password);   
    }

    private static void performLogin() {
    System.out.println("LogIn");
    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    int loginAttempts = 3;
    while (loginAttempts > 0) {
        if (isUserRegistered(username, password)) {
                    System.out.println("------------------------------------------------------");
            System.out.println("LogIn berhasil. Selamat datang " + username + " di perpustakaan AVA!");
            isLoggedIn = true;
            showMainMenu();
            return;
        } else {
            loginAttempts--;
            if (loginAttempts > 0) {
                System.out.println("LogIn gagal. Akun belum dibuat atau username dan password salah. Sisa percobaan: " + loginAttempts);
            } else {
                System.out.println("Batas percobaan LogIn habis. Program berakhir.");
                return;
            }
        }

        System.out.print("Username: ");
        username = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();
    }
}
    private static boolean isLoggedIn = false;
    private static boolean isLoggedIn() {
    return isLoggedIn;
}

    private static void showMainMenu() {
        if (!isLoggedIn()) {
        System.out.println("Anda harus login terlebih dahulu!");
        return;
    }
        System.out.println("╔═════════════════════════════════════════════════╗");
        System.out.println("║                SELAMAT DATANG DI                ║");
        System.out.println("║              PERPUSTAKAAN AVA ONLINE            ║");
        System.out.println("╠═════════════════════════════════════════════════╣");
        System.out.println("║                        MENU                     ║");
        System.out.println("║ 1. Profil Pribadi                               ║");
        System.out.println("║ 2. Daftar Buku                                  ║");
        System.out.println("║ 3. Cari Buku                                    ║");
        System.out.println("║ 4. Peminjaman Buku                              ║");
        System.out.println("║ 5. Pengembalian Buku                            ║");
        System.out.println("║ 6. Perpanjangan Masa Pinjam                     ║");
        System.out.println("║ 7. LogOut                                       ║");
        System.out.println("╠═════════════════════════════════════════════════╣");
        System.out.print("Masukkan Pilihan: ");



        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        switch (choice) {
            case 1:
                displayPersonalProfile();
                break;
            case 2:
                displayBookList();
                break;
            case 3:
                searchBook();
                break;
            case 4:
                performBookBorrow();
                break;
            case 5 :
                performBookReturn();
                break;
            case 6 :
                extendLoanPeriod();
                break;
            case 7 :
                System.out.println("Terima kasih telah menggunakan layanan perpustakaan Ava!");
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private static void displayPersonalProfile() {
    System.out.println("Nama: " + fullName);
    System.out.println("NPM: " + npm);
    System.out.println("No HP: " + phoneNumber);
    System.out.println("Alamat: " + address);
    showMainMenu();
}
    private static Book[] books = {
        new Book("Becoming", "Michelle Obama"),
        new Book("Babad Tanah Jawi Mulai dari Nabi Adam Sampai Runtuhnya Mataram", "W.L. Olthof"),
        new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin"),
        new Book("Churchill: A Life", "Martin Gilbert"),
        new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnel"),
        new Book("Educated", "Tara Westover"),
        new Book("HTML and CSS: Design and Build Websites", "Jon Duckett"),
        new Book("Kamus Inggris Indonesia", "John M. Echols, Hassan Shadily"),
        new Book("Kamus Korea – Excellent Dictionary", "Komunitas Linguasia"),
        new Book("Kamus Lengkap Bahasa Jepang", "Diramoti Benedict"),
        new Book("Laut Bercerita", "Leila S. Chudori"),
        new Book("Layangan Putus", "MOMMY ASF"),
        new Book("Majnun", "Anton Kurnia"),
        new Book("Sagaras", "Tere Liye"),
        new Book("Sapiens: Kelahiran Umat Manusia", "Yuval Noah Harari"),
        new Book("Sejarah Dunia yang Disembunyikan", "Jonathan Black"),
        new Book("Steve Jobs", "Walter Isaac"),
        new Book("You Don't Know JS Yet: Get Started", "Kyle Simpson"),
        new Book("172 Days", "Nadzira Shafa"),
        new Book("Biografi Gus Dur", "Greg Barton")
    };
     private static void searchBook() {
        System.out.print("Masukkan judul buku yang ingin dicari: ");
        String searchTitle = scanner.nextLine();

        Book book = findBookByTitle(searchTitle);
        if (book != null) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Buku ditemukan!");
            System.out.println("-----------------------------------------------------------");
            System.out.println("Nomor Buku: " + getBookNumber(book));
            System.out.println("Judul Buku: " + book.getTitle());
            System.out.println("Penulis: " + book.getAuthor());
            System.out.println("-----------------------------------------------------------");
        } else {
            System.out.println("Buku tidak ditemukan.");
        }

        System.out.println();
        showMainMenu();
    }

    private static Book findBookByTitle(String searchTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                return book;
            }
        }
        return null;
    }

    private static String getBookNumber(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == book) {
                return String.valueOf(i + 1);
            }
        }
        return "";
    }

    private static void displayBookList() {
        quickSort(books, 0, books.length - 1);

        System.out.println("DAFTAR BUKU PERPUSTAKAAN AVA");
        System.out.println("============================");

        for (int i = 0; i < books.length; i++) {
            System.out.println((i + 1) + ". Judul Buku: " + books[i].getTitle() + ", Penulis: " + books[i].getAuthor());
        }

        System.out.println();

        showMainMenu();
    }

    private static void quickSort(Book[] books, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(books, low, high);
            quickSort(books, low, pivotIndex - 1);
            quickSort(books, pivotIndex + 1, high);
        }
    }

    private static int partition(Book[] books, int low, int high) {
        Book pivot = books[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (books[j].getTitle().compareToIgnoreCase(pivot.getTitle()) < 0) {
                i++;
                swap(books, i, j);
            }
        }

        swap(books, i + 1, high);
        return i + 1;
    }

    private static void swap(Book[] books, int i, int j) {
        Book temp = books[i];
        books[i] = books[j];
        books[j] = temp;
    }

    private static void performBookBorrow() {
    System.out.println("Peminjaman Buku");

    String borrowerName;
    String choice;

        System.out.print("Nama Peminjam: ");
        borrowerName = scanner.nextLine();
    
    do {
        System.out.print("Pilih buku yang ingin dipinjam (masukkan nomor buku): ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Book book = getBookByNumber(bookNumber);
        if (book != null) {
            String borrowDate = LocalDate.now().toString();
            String dueDate = LocalDate.now().plusDays(LOAN_DURATION_DAYS).toString();
            LoanRecord loanRecord = new LoanRecord(book, borrowerName, borrowDate, dueDate);
            loanRecords.put(book.getTitle(), loanRecord);

            System.out.println("--------------------------------------------------------------------");
            System.out.println("Peminjaman berhasil!");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Detail Peminjaman:");
            System.out.println("Judul Buku: " + book.getTitle());
            System.out.println("Penulis: " + book.getAuthor());
            System.out.println("Tanggal Peminjaman: " + borrowDate);
            System.out.println("Tanggal Pengembalian: " + dueDate);
            System.out.println("--------------------------------------------------------------------");
        } else {
            System.out.println("Buku yang Anda cari saat ini tidak tersedia di dalam Perpustakaan.");
        }

        System.out.print("Tambah buku? (ya/tidak): ");
        choice = scanner.nextLine();

    } while (choice.equalsIgnoreCase("ya"));
    showMainMenu();
}

    private static Book getBookByNumber(int bookNumber) {
    // Implementasi logika untuk mendapatkan buku berdasarkan nomor buku
    // Untuk kesederhanaan, kita anggap memiliki daftar buku yang tetap dan mengembalikannya berdasarkan nomor
     quickSort(books, 0, books.length - 1); // Urutkan array books secara abjad a-z

    if (bookNumber >= 1 && bookNumber <= books.length) {
        return books[bookNumber - 1];
    } else {
        return null;
    }
}

private static void performBookReturn() {
    System.out.println("Pengembalian Buku");

    String choice;  // Initialize choice with an empty string
    NumberFormat decimalFormat = new DecimalFormat("#,##0.00");
    
    do {
        System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
        String bookTitle = scanner.nextLine();

        LoanRecord loanRecord = loanRecords.get(bookTitle);
        if (loanRecord != null && !loanRecord.isBookReturned()) {
            System.out.print("Masukkan nama peminjam: ");
            String borrowerName = scanner.nextLine();

            System.out.print("Masukkan tanggal pengembalian buku (yyyy-MM-dd): ");
            String returnDate = scanner.nextLine();

            LocalDate dueDate = LocalDate.parse(loanRecord.getDueDate());
            LocalDate actualReturnDate = LocalDate.parse(returnDate);

            long daysLate = ChronoUnit.DAYS.between(dueDate, actualReturnDate);

            if (daysLate > 0) {
                long fineAmount = daysLate * FINE_PER_DAY;
                System.out.println("Anda terlambat " + daysLate + " hari.");
                System.out.println("Denda yang harus dibayar: Rp " + fineAmount);

                double paymentAmount;
                do {
                    System.out.print("Masukkan jumlah pembayaran: Rp ");
                    paymentAmount = scanner.nextDouble();

                    if (paymentAmount < fineAmount) {
                        double remainingAmount = fineAmount - paymentAmount;
                        String remainingAmountFormatted = decimalFormat.format(remainingAmount);
                        System.out.println("Pembayaran tidak cukup. Mohon bayar sesuai total denda yang tertera.");
                        System.out.println("Kurang bayar: Rp " + remainingAmountFormatted);
                    }
                } while (paymentAmount < fineAmount);

                System.out.println("************************************************************");
                System.out.println("                      STRUK PEMBAYARAN                     ");
                System.out.println("                  PERPUSTAKAAN ONLINE AVA                  ");
                System.out.println("                  Jl. H.S. Ronggo Waluyo                   ");
                System.out.println("                      Kab. Karawang                        ");
                System.out.println("                   Telp. 021-8975451956                    ");
                System.out.println("***********************************************************");
                System.out.println(" " + returnDate);
                System.out.println("-----------------------------------------------------------");
                System.out.println("                    Detail Peminjaman:                     ");
                System.out.println("-----------------------------------------------------------");
                System.out.println("Nama peminjam: " + borrowerName);
                System.out.println("Judul Buku      : " + bookTitle);
                System.out.println("Tanggal Peminjaman Buku  : " + loanRecord.getBorrowDate());
                System.out.println("Tanggal Pengembalian Buku : " + returnDate);
                System.out.println("Tanggal pembayaran (yyyy-MM-dd): " + returnDate);
                System.out.println();
                System.out.println("-----------------------------------------------------------");
                System.out.println("                        Denda:                             ");
                System.out.println("-----------------------------------------------------------");
                System.out.println("Keterlambatan Pengembalian : " + daysLate + " hari");
                System.out.println("Tarif Denda Per Hari  : " + FINE_PER_DAY);
                String fineAmountFormatted = decimalFormat.format(fineAmount);
                System.out.println("Total denda yang harus dibayarkan: Rp " + fineAmountFormatted);
                System.out.println("-----------------------------------------------------------");
                System.out.println("Masukkan jumlah pembayaran: Rp " + paymentAmount);
                System.out.println("*************************************************************");

                if (paymentAmount == fineAmount) {
                    System.out.println("Pembayaran berhasil");
                    double change = paymentAmount - fineAmount;
                    String changeFormatted = decimalFormat.format(change);
                    System.out.println("Kembalian: Rp " + changeFormatted);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Terima kasih telah melakukan pembayaran denda perpustakaan online.\nMohon simpan struk ini sebagai bukti pembayaran.");
                    System.out.println();
                    System.out.println("Untuk informasi lebih lanjut, hubungi:");
                    System.out.println("Perpustakaan Online AVA");
                    System.out.println("Telepon: (012) 345-6789");
                    System.out.println("Email: info@perpustakaanonline.ava");
                    System.out.println("Website: www.perpustakaanonline.ava");
                    System.out.println("--------------------------------------------------------------------");
                } else {
                    double change = paymentAmount - fineAmount;
                    String changeFormatted = decimalFormat.format(change);
                    System.out.println("Pembayaran berhasil");
                    System.out.println("Kembalian: Rp " + changeFormatted);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Terima kasih telah melakukan pembayaran denda perpustakaan online.\nMohon simpan struk ini sebagai bukti pembayaran.");
                    System.out.println();
                    System.out.println("Untuk informasi lebih lanjut, hubungi:");
                    System.out.println("Perpustakaan Online AVA");
                    System.out.println("Telepon: (012) 345-6789");
                    System.out.println("Email: info@perpustakaanonline.ava");
                    System.out.println("Website: www.perpustakaanonline.ava");
                    System.out.println("--------------------------------------------------------------------");
                }

                scanner.nextLine(); // Consume the remaining newline character
            } else {
                System.out.println("Buku belum terlambat pengembalian.");
            }

            loanRecord.setReturnDate(returnDate);
            loanRecord.setReturned(true);
            loanRecord.setBorrowerName(borrowerName);

            System.out.println("------------------------------------------------------");
            System.out.println("Pengembalian berhasil!");
            System.out.println("------------------------------------------------------");
            System.out.println("Detail Pengembalian:");
            System.out.println("Nama Peminjam: " + loanRecord.getBorrowerName());
            System.out.println("Judul Buku: " + loanRecord.getBook().getTitle());
            System.out.println("Penulis: " + loanRecord.getBook().getAuthor());
            System.out.println("Tanggal Peminjaman: " + loanRecord.getBorrowDate());
            System.out.println("Tanggal Pengembalian: " + loanRecord.getReturnDate());
            System.out.println("------------------------------------------------------");

        } else {
            System.out.println("Buku dengan judul tersebut tidak sedang dipinjam atau tidak valid.");
        }

        System.out.print("Apakah Anda ingin mengembalikan buku lain? (ya/tidak): ");
        choice = scanner.nextLine();
    } while (choice.equalsIgnoreCase("ya"));

    showMainMenu();
}
        
    private static void extendLoanPeriod() {
    System.out.println("Perpanjangan Masa Pinjam");
    System.out.print("Masukkan judul buku yang ingin diperpanjang masa pinjamnya: ");
    String bookTitle = scanner.nextLine();

    LoanRecord loanRecord = loanRecords.get(bookTitle);
    if (loanRecord != null && !loanRecord.isReturned()) {
        LocalDate dueDate = LocalDate.parse(loanRecord.getDueDate());
        LocalDate extendedDueDate = dueDate.plusDays(14); // Mengubah LOAN_DURATION_DAYS menjadi 14
        loanRecord.setReturnDate(extendedDueDate.toString());

        System.out.println("------------------------------------------------------");
        System.out.println("Perpanjangan masa pinjam berhasil!");
        System.out.println("Detail Peminjaman:");
        System.out.println("Judul Buku: " + loanRecord.getBook().getTitle());
        System.out.println("Penulis: " + loanRecord.getBook().getAuthor());
        System.out.println("Tanggal Peminjaman: " + loanRecord.getBorrowDate());
        System.out.println("Tanggal Pengembalian Baru: " + loanRecord.getReturnDate());
        System.out.println("------------------------------------------------------");
    } else {
        System.out.println("Buku dengan judul tersebut tidak sedang dipinjam atau tidak valid.");
    }
    showMainMenu();
    }
}
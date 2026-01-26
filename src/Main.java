import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static class Book {
        private int id;
        private String title;
        private String author;
        private long price;

        public Book(int id, String title, String author, long price) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.price = price;
        }
        public Book() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }
        public void input() {
            Scanner x = new Scanner(System.in);

            System.out.println("Nhap ma sach");
            this.id = Integer.parseInt(x.nextLine());

            System.out.println("Nhap ten sach");
            this.title = x.nextLine();

            System.out.println("Nhap ten tac gia");
            this.author = x.nextLine();

            System.out.println("Nhap don gia");
            this.price = Long.parseLong(x.nextLine());
        }
        public void output() {
            String msg = """
                   BOOK: id= %d, title=%s, author=%s, price=%d""".formatted(id, title, author, price);
            System.out.println(msg);
        }
    }
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        String msg = """
                Chuong trinh quan ly sach
                    1. them 1 cuon sach
                    2. xoa 1 cuon sach
                    3. thay doi sach
                    4. xuat thong tin
                    5. tim sach lap trinh
                    6. lay sach toi da theo gia
                    7. tim kiem tac gia
                    0. thoat
                    Chon chuc nang:""";
        int chon = 0;
        do
        {
            System.out.println(msg);
            chon = x.nextInt();
            switch (chon)
            {
                case 1-> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                }
                case 2-> {
                    System.out.println("Nhap vao ma sach can xoa:");
                    int bookid = x.nextInt();
                    //kiem tra ma sach
                    Book find = listBook.stream().filter(p-> p.getId() == bookid).findFirst().orElseThrow();
                    listBook.remove(find);
                    System.out.println("Da xoa sach thanh cong");
                }
//                case 3-> {
//                    System.out.println("Nhap vao ma sach can dieu chinh:");
//                    int bookid = x.nextInt();
//                    Book find = listBook.stream().filter(p-> p.getId() == bookid).findFirst().orElseThrow();
//                }
                case 3-> {
                    x.nextLine();

                    System.out.println("Nhap vao ma sach can dieu chinh:");
                    int bookid = Integer.parseInt((x.nextLine()));

                    Book find = listBook.stream().filter(p-> p.getId() == bookid).findFirst().orElse(null);

                    if (find == null) {
                        System.out.println("Khong tim thay sach");
                        break;
                    }
                    System.out.println("Nhap ten sach moi: ");
                    find.setTitle(x.nextLine());

                    System.out.println("Nhap ten tac gia moi: ");
                    find.setAuthor(x.nextLine());

                    System.out.println("Nhap don gia moi: ");
                    find.setPrice(x.nextLong());

                    System.out.println("Cap Nhat Thanh Cong!");
                }
                case 4-> {
                    System.out.println("\n Xuat thong tin danh sach ");
                    listBook.forEach(p-> p.output());
                }
//                case 5-> {
//
//                    List<Book> list5 = listBook.stream().filter(u -> u.getTitle().toLowerCase().contains("lap trinh")).toList();
//                    if (list5.isEmpty()) {
//                        System.out.println("Khong tim thay sach lap trinh");
//                    } else {
//                        list5.forEach(Book::output);
//                    }
//                }
                case 5 -> {
                    String keyword = "Lap Trinh";
                    listBook.stream().filter(b -> b.getTitle() != null && b.getTitle().toLowerCase().contains(keyword.toLowerCase())).forEach(Book::output);
                }
                case 6 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sach rong");
                        break;
                    }
                    long maxPrice = listBook.stream().mapToLong(Book::getPrice).max().orElse(0);

                    System.out.println("Sach co gia cao nhat");
                    listBook.stream().filter(b -> b.getPrice() == maxPrice).forEach(Book::output);
                }
//                case 7 -> {
//                    Set<String> authorSet = Set.of(
//                            "Reacee James",
//                            "Tran Van B",
//                            "Cole Palmer"
//                    )
//                }
            }
        }while (chon!=0);
    }
}
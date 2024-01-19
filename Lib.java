import java.util.*;
class Book {
    int bookid;
    String title;
    String author;
    Book(int bookid, String title, String author) {
        this.bookid = bookid;
        this.title = title;
        this.author = author;
    }
}

class Member {
    String name;
    int id;
    HashMap<Integer, Book> borrowed;
    public Member(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowed = new HashMap<>();
    }
    //to add book into user list
    public void addBook(int id, Book b) {
        this.borrowed.put(id, b);
        System.out.println("Your borrowed books are:");
        for (Book book : this.borrowed.values()) {
            System.out.println(book.bookid + " " + book.title);
        }
    }
    //to remove book from user's list
    public void removeBook(int id){
        this.borrowed.remove(id);
        if(borrowed.size()==0){
            System.out.println("You have successfully returned all your books...!\n");
        }
        else{
            System.out.println("You have still : ");
            for (Book book : this.borrowed.values()) {
                System.out.println(book.bookid + " " + book.title);
            }
        }
    }
    //information about user
    public void info() {
        System.out.println("Name: " + this.name);
        System.out.println("LibID: " + this.id);
        if (this.borrowed.size() == 0) {
            System.out.println("You haven't borrowed any books.\n");
        } 
        else {
            System.out.println("Your borrowed books are:");
            for (Book book : this.borrowed.values()) {
                System.out.println(book.bookid + " " + book.title);
            }
        }
    }
}

public class Lib {
    static HashMap<Integer, Book> booklist = new HashMap<>();
    static ArrayList<Member> memberlist = new ArrayList<>();

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        Book b = new Book(1, "DSA", "ABC");
        booklist.put(b.bookid, b);
        b = new Book(2, "C", "Ansi");
        booklist.put(b.bookid, b);
        b = new Book(3, "Java", "Seven");
        booklist.put(b.bookid, b);
        b = new Book(4, "Python", "w3");
        booklist.put(b.bookid, b);
        b = new Book(5, "SQL", "Balaram");
        booklist.put(b.bookid, b);

        int id;
        loop: while (true) {
            System.out.println("\n--------Welcome to the library--------");
            System.out.println("---CHOOSE---");
            System.out.println("1. Need Membership \n2. Already a member \n3. Exit from the library");
            int choice = read.nextInt();

            switch (choice) {
                //Create membership
                case 1:
                    System.out.print("Enter your name: ");
                    read.nextLine();
                    String name = read.nextLine();
                    id = memberlist.size() + 1;
                    System.out.println("Your LibID is: " + id);
                    memberlist.add(new Member(name, id));
                    System.out.println("...Now You have membership\n");
                    break;
                //Login member
                case 2:
                    System.out.print("Enter your LibID: ");
                    id = read.nextInt();
                    if (id <= memberlist.size()) {
                        memberlist.get(id - 1).info();
                    } 
                    else {
                        System.out.println("You don't have membership in this library.\n");
                        break;
                    }
                    loops: while (true) {
                    System.out.println("\n-----CHOOSE-----");
                    System.out.println("\n1. Available books \n2. Borrow Books \n3. Return Books \n4. Check Availability \n5. Exit \n");
                    int choice2 = read.nextInt();

                    switch (choice2) {
                        //available booka
                        case 1:
                            System.out.println("ID Book_name");
                            System.out.println("-----------");
                            for (Book book : booklist.values()) {
                                System.out.println(book.bookid + " " + book.title);
                            }
                            break;
                        //Borrow book
                        case 2:
                            System.out.print("Enter the ID of the book you want: ");
                            int aaa = read.nextInt();
                            
                            if (booklist.containsKey(aaa)) {
                                memberlist.get(id - 1).addBook(aaa, booklist.get(aaa));
                                booklist.remove(aaa);
                            } 
                            else {
                                System.out.println("Book not available.\n");
                            }
                         break;
                        //Return Book
                        case 3:
                            System.out.print("Enter the ID of the book you want to return: ");
                            int bbb = read.nextInt();
                            if(!memberlist.get(id-1).borrowed.containsKey(bbb)){
                                System.out.println("You haven't took this book.\n");
                                break;
                            }
                            booklist.put(bbb,memberlist.get(id-1).borrowed.get(bbb));
                            memberlist.get(id-1).removeBook(bbb);
                            break;
                        //Check availability
                        case 4:
                            System.out.print("Enter the ID of the book to check availability: ");
                            int availId = read.nextInt();
                            if (booklist.containsKey(availId)) {
                                System.out.println("Book is available.\n");
                            } 
                            else {
                                System.out.println("Book is not available.\n");
                            }
                            break;
                        //exit
                        case 5:
                            break loops;
                        default:
                        System.out.println("Invalid input");
                    }
            }
                    break;
                case 3:
                    break loop;
                default:
                    System.out.println("Invalid Input");
            }

            
        }
    }
}

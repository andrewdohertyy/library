package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static <searchTerm> void main(String[] args) throws IOException {

        String path = "/Users/AndrewDoherty/Desktop/books.csv";
        String searchTerm;
        List<Book> searchBooks;

        ArrayList<User> allUsers = new ArrayList<User>();

        String firstName;
        String lastName;
        int age = 0;
        String location;

        File file = new File(path);
        Scanner readFile = new Scanner(file);
        StringTokenizer token = null;

        ArrayList<Book> allBooks = new ArrayList<Book>();
        ArrayList<List<Book>> rentedBooks = new ArrayList<>();

        String number;
        String title;
        String author;
        String genre;
        String subGenre;
        String publisher;


        while (readFile.hasNextLine()) {
            token = new StringTokenizer(readFile.nextLine(), " , ");

            number = token.nextToken();
            title = token.nextToken();
            author = token.nextToken();
            genre = token.nextToken();
            subGenre = token.nextToken();
            publisher = token.nextToken();

            Book book = new Book(number, title, author, genre, subGenre, publisher);
            allBooks.add(book);
//            System.out.println(book.toString());
        }


        System.out.println("Welcome to the library");
        System.out.println("What would you like to do today?");
        System.out.println("--------------------------------");
        System.out.println("1. See all available books");
        System.out.println("2. Search and withdraw a book");
        System.out.println("3. Donate a book");
        System.out.println("4. Register as a user");
        System.out.println("5. If you're an admin");
        System.out.println("6. Shut Down");

        Scanner scanner = new Scanner(System.in);
        scanner.next();


        if (scanner.next().equals("1")) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(reader.readLine());
//                        requestComplete = true;
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (scanner.next().equals("2")) {
            System.out.println("Enter the name of the book you would like to search for...");
            scanner.nextLine();
            searchTerm = scanner.nextLine();
            searchBooks = allBooks.stream().filter(book -> Objects.equals(book.getTitle(), searchTerm)).collect(Collectors.toList());
            System.out.println(searchBooks);
            System.out.println("Do you want to withdraw this book? ");
            System.out.println("Select 1 for yes, 2 for no");
            scanner.nextLine();
            String option = scanner.nextLine();
            if (option.equals("1")) {
                rentedBooks.add(searchBooks);
                System.out.println(rentedBooks);
            } else if (option.equals("2")) {
                System.out.println("Logging off");
            }


        } else if (scanner.next().equals("3")) {
            System.out.println("please enter the book details");
            System.out.println("Enter in the format  number,  title,  author,  genre,  subGenre,  publisher separated by commas");
            System.out.println("------------------------------------------------------------");
            scanner.nextLine();
            String newBook = scanner.nextLine();
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/AndrewDoherty/Desktop/donated.csv"));
            writer.write(newBook);
            writer.close();

        } else if (scanner.next().equals("4")) {
//            System.out.println("Enter your first and last name");
//            String newUser = scanner.nextLine();
//            System.out.println("Thank you, you will be returned to the main menu");
//            allUsers.add(newUser);
//            System.out.println(allUsers);
            System.out.println("Enter your firstname, lastname, age and location");
            scanner.nextLine();
            token = new StringTokenizer(scanner.nextLine(), " , ");

            firstName = token.nextToken();
            lastName = token.nextToken();
            age = Integer.parseInt(token.nextToken());
            location = token.nextToken();

            User user = new User(firstName, lastName, age, location);
            allUsers.add(user);
            System.out.println(allUsers);


        } else if (scanner.next().equals("5")) {
            System.out.println("What would you like to do?");
            System.out.println("1. See all rented books");
            System.out.println("2. See all users");
            scanner.nextLine();
            if (scanner.nextLine().equals("1")) {
                System.out.println(rentedBooks);
            } else if (scanner.nextLine().equals("2")) {
                System.out.println(allUsers);
            }

        } else if (scanner.next().equals("6")) {
            System.out.println("Shutting down");
        }
    }

}


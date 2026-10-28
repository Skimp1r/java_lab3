import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Класс Book, реализующий Serializable для сохранения состояния объекта
class Book implements Serializable {
    private static final long serialVersionUID = 1L;
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

    @Override
    public String toString() {
        return title + " (" + author + ")";
    }
}

// Класс Library, инкапсулирующий работу со списком книг и файловой системой
class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Библиотека пуста.");
            return;
        }
        System.out.println("Список книг в библиотеке:");
        for (Book b : books) {
            System.out.println(" - " + b);
        }
    }

    // Сохранение списка книг в бинарный файл через сериализацию
    public void save(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(books);
            System.out.println("Библиотека успешно сохранена в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Загрузка списка книг из файла через десериализацию
    @SuppressWarnings("unchecked")
    public void load(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            books = (List<Book>) in.readObject();
            System.out.println("Библиотека успешно загружена из файла: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        String dataFile = "library.dat";

        System.out.println("=== 1. Создание исходной библиотеки и добавление книг ===");
        Library lib = new Library();
        lib.addBook(new Book("Java. Руководство", "Шилдт"));
        lib.addBook(new Book("Философия Java", "Эккель"));
        lib.addBook(new Book("Чистый код", "Роберт Мартин"));
        lib.showBooks();

        System.out.println("\n=== 2. Сохранение данных в файл ===");
        lib.save(dataFile);

        System.out.println("\n=== 3. Создание новой библиотеки и восстановление данных из файла ===");
        Library newLib = new Library();
        newLib.load(dataFile);
        newLib.showBooks();
    }
}

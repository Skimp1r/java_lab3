import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFile {
    private String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    // Запись строки в файл (параметр append определяет: дозапись или перезапись)
    public void writeLine(String line, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(line + "\n");
            System.out.println("Строка успешно записана в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    // Чтение содержимого файла
    public void readContent() {
        System.out.println("--- Содержимое файла " + fileName + " ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("----------------------------------------");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TextFile textFile = new TextFile("sample.txt");

        // Запись строк
        textFile.writeLine("Первая строка текста.", false);
        textFile.writeLine("Вторая строка: изучение работы с файлами в Java.", true);
        textFile.writeLine("Третья строка: успешное выполнение простого задания.", true);

        // Чтение и вывод на экран
        textFile.readContent();
    }
}

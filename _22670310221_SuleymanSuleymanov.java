import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class _22670310221_SuleymanSuleymanov {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Kuyruk.txt"))) {
            int capacity = Integer.parseInt(reader.readLine().trim());
            Kuyruk kuyruk = new Kuyruk(capacity);

            List<String> izinVerilerTipler = Arrays.asList(reader.readLine().split(" "));

            String okunanSatir;
            while ((okunanSatir = reader.readLine()) != null) {
                String[] parcalar = okunanSatir.split(" ");
                String komut = parcalar[0];
                String hamVeri;
                if (komut.equals("ekle")) {
                    hamVeri = parcalar[1];
                    Object islenmisVeri = veriHazirla(hamVeri, izinVerilerTipler);
                    kuyruk.enqueue(islenmisVeri);
                }
                if (komut.equals("çıkar")) {
                    kuyruk.dequeue();
                }
                if (komut.equals("yazdır")) {
                    kuyruk.print();
                }
                if (komut.equals("peek")) {
                    // TODO - peek
                }
                if (komut.equals("boşMu")) {
                    // TODO - isEmpty
                }
                if (komut.equals("doluMu")) {
                    // TODO - isFull
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya Bulunamadi!");
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public static Object veriHazirla(String hamVeri, List<String> izinVerilenTipler) {
        if (izinVerilenTipler.contains("int")) {
            try {
                return Integer.parseInt(hamVeri);
            } catch (NumberFormatException e) {

            }
        }
        if (izinVerilenTipler.contains("double")) {
            try {
                return Double.parseDouble(hamVeri);
            } catch (NumberFormatException e) {

            }
        }
        if (izinVerilenTipler.contains("char")) {
            if (hamVeri.length() <= 3 && hamVeri.charAt(0) == '\'' && hamVeri.charAt(2) == '\'') {
                return hamVeri.charAt(1);
            }
        }
        if (izinVerilenTipler.contains("String")) {
            return hamVeri;
        }
        return null;
    }

    public static class Kuyruk {
        int capacity;
        Object[] array;
        int size;
        int front;
        int rear;

        public Kuyruk(int capacity) {
            this.capacity = capacity;
            this.array = new Object[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        public void enqueue(Object eklenecekVeri) {
            if (size == capacity) {
                // TODO - resize
            }
            rear = (rear + 1) % capacity;
            array[rear] = eklenecekVeri;
            size++;
            System.out.println("+-> " + eklenecekVeri + " Kuyruğa eklendi");
            print();
        }

        public void dequeue() {
            if (size == 0) {
                return;
            }
            Object silinecekVeri = array[front];
            array[front] = null;
            front = (front + 1) % capacity;

            System.out.println("x-> " + silinecekVeri + "Kuyruktan çıktı");
            print();

            if (size < capacity / 2) {
                // TODO - resize implement
            }
            size--;
        }

        public void print() {
            System.out.print("Kapasite: " + capacity + " |Eleman Sayısı: " + size + " |Başı: ");

            for (int i = 0; i < capacity; i++) {
                Object ekranaYazdirilacakElaman = (rear + i) % capacity;
                System.out.print(ekranaYazdirilacakElaman);
                if (i <= capacity - 2) {
                    System.out.print(", ");
                }
            }
            System.out.print(" :Sonu|");
            System.out.println(" ");
        }
    }
}
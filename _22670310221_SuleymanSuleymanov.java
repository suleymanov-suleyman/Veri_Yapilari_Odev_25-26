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
                    kuyruk.kuyruguYazdir();
                }
                if (komut.equals("peek")) {
                    Object peekResult = kuyruk.peek();
                    System.out.println("p-> " + peekResult + " Peek sonucu");
                }
                if (komut.equals("boşMu")) {
                    if (kuyruk.isEmpty()) {
                        System.out.println("e-+> Kuyruk şu an boş!");
                    } else {
                        System.out.println("e-x> Kuyruk boş değil!");
                    }
                }
                if (komut.equals("doluMu")) {
                    if (kuyruk.isFull()) {
                        System.out.println("f-+> Kuyruk şu an dolu!");
                    } else {
                        System.out.println("f-x> Kuyruk şu an dolu değil!");
                    }
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
                return hamVeri;
            }
        }
        if (izinVerilenTipler.contains("String")) {
            return hamVeri;
        }
        return null;
    }

    public static class Kuyruk {
        int capacity;
        Object[] mainArray;
        int size;
        int front;
        int rear;

        public Kuyruk(int capacity) {
            this.capacity = capacity;
            this.mainArray = new Object[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        public void resize(int resizeValue) {
            if (capacity < 2) {
                return;
            }
            Object[] tempArray = new Object[resizeValue];

            for (int i = 0; i < size; i++) {
                front = (front + i) % capacity;
                tempArray[i] = mainArray[front];
            }
            mainArray = tempArray;
            capacity = resizeValue;
            front = 0;
        }

        public void enqueue(Object eklenecekVeri) {
            if (size == capacity) {
                resize(capacity * 2);
            }
            rear = (rear + 1) % capacity;
            mainArray[rear] = eklenecekVeri;
            size++;
            System.out.println("+--> " + eklenecekVeri + " Kuyruğa eklendi");
            kuyruguYazdir();
        }

        public void dequeue() {
            if (size == 0) {
                return;
            }
            Object silinecekVeri = mainArray[front];
            mainArray[front] = null;
            front = (front + 1) % capacity;

            System.out.println("x--> " + silinecekVeri + " Kuyruktan çıktı");

            size--;
            if (size < capacity / 2) {
                if (capacity > 2) {
                    resize(capacity / 2);
                }
            }
            kuyruguYazdir();
        }

        public void kuyruguYazdir() {
            System.out.print("Kapasite: " + capacity + " |Eleman Sayısı: " + size + " |Başı: ");

            for (int i = 0; i < capacity; i++) {
                int ekranaYazdirilacakElamanIndexi = (front + i) % capacity;

                System.out.print(mainArray[ekranaYazdirilacakElamanIndexi]);
                if (i <= capacity - 2) {
                    System.out.print(", ");
                }
            }
            System.out.print(" :Sonu|");
            System.out.println(" ");
        }

        public Object peek() {
            return mainArray[front];
        }

        public boolean isEmpty() {
            if (size == 0) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isFull() {
            if (size == capacity) {
                return true;
            } else {
                return false;
            }
        }
    }
}
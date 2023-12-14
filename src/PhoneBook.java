import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
        private static HashMap<String, String> pb = new HashMap<String, String>();

        //addPB - добавляет запись по заданным номеру телефона и фамилии
        private static void addPB(String phone, String name) {
            pb.put(phone, name);
        }

        //delPB - удаляет запись по номеру телефона
        private static void delPB(String phone) {
            pb.remove(phone);
        }

        //savePB - сохраняет БД в текстовом файле phone.txt
        private static void savePB() throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("phone.txt")));
            for (Map.Entry<String, String> k : pb.entrySet()) {
                writer.write(k.getKey() + " " + k.getValue() + System.lineSeparator());
            }
            writer.close();
        }

        //loadPB - загружает БД из текстового файла phone.txt
        //производит проверку на наличие файла
        public static void loadPB() throws IOException {
            File file = new File("phone.txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(new File("phone.txt")));
                String act;
                while ((act = reader.readLine()) != null) {
                    String[] dat = act.split(" ");
                    pb.put(dat[0], dat[1]);
                }
                reader.close();
            }
        }

        //PrintPhonebook - выводит на экран все записи БД
        public static void PrintPhonebook() {
            System.out.println("Телефонный справочник: ");
            for (Map.Entry<String, String> k : pb.entrySet()) {
                System.out.println(k.getValue() + ": " + k.getKey());
            }
        }

        //FindSurname - производит поиск фамилии по номеру телефона заданому в качестве аргумента
        //возвращает строку
        public static String FindSurname(String number) {
            String result = pb.get(number);
            if (result == null) return "абонент с таким номером не найдей";
            return result;
        }

        //FindNumberPhone - производит поиск списка номеров по фамилии заданой в качестве аргумента
        //возвращает массив строк
        public static String[] FindNumberPhone(String surname) {
            List<String> result = new ArrayList<String>();
            for (Map.Entry entry : pb.entrySet()) {
                if (surname.equalsIgnoreCase((String) entry.getValue())) {
                    result.add((String) entry.getKey());
                }
            }
            if (result.size() == 0) result.add("абонент с такой фамилией не найден");
            return result.toArray(new String[0]);
        }

        public static void main(String[] args) throws IOException {
            //переменная описывает вызываемое действие
            String act;

            //загрузка БД
            loadPB();
            //вывод записей на ''экран'
            PrintPhonebook();

            //вывод на экран описания возможных действий с указанием команд
            System.out.println("выбор действия: ");
            System.out.println("1.добавить данные");
            System.out.println("2.удалить данные");
            System.out.println("3.найти номера по фамилии");
            System.out.println("4.найти фамилию");
            System.out.println("5.сохранить");
            System.out.println("6.выход");

            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            act = bf.readLine();
            while (!act.equals("6")) {
                //добавление записи
                if (act.equals("1")) {
                    System.out.println("Введите фамилию:");
                    String name = bf.readLine();
                    System.out.println("Введите телефон:");
                    String phone = bf.readLine();
                    addPB(phone, name);
                } else {
                    //удаление записи
                    if (act.equals("2")) {
                        System.out.println("Введите телефон:");
                        String phone = bf.readLine();
                        delPB(phone);
                    } else {
                        //поиск номеров по фамилии
                        if (act.equals("3")) {
                            System.out.println("Введите фамилию:");
                            String surname = bf.readLine();
                            String[] numbers = FindNumberPhone(surname);
                            for (String number : numbers) {
                                System.out.println(number);
                            }
                        } else {
                            //поиск фамилии по номеру
                            if (act.equals("4")) {
                                System.out.println("Введите номер:");
                                String number = bf.readLine();
                                System.out.println(FindSurname(number));
                            } else {
                                //сохранение БД в файл
                                if (act.equals("5")) {
                                    savePB();
                                }
                            }
                        }
                    }
                }
                //запрос на следующее действие
                System.out.println("выбор действия: ");
                System.out.println("1.добавить данные");
                System.out.println("2.удалить данные");
                System.out.println("3.найти номера по фамилии");
                System.out.println("4.найти фамилию");
                System.out.println("5.сохранить");
                System.out.println("6.выход");
                act = bf.readLine();
            }
        }
    }


package org.InfTh.lab2;
import org.InfTh.LetterInfo;
import java.util.*;
import static org.InfTh.Analyzer.*;

/*
chcp 1251
java "-javaagent:D:\JetBrains\IntelliJ IDEA 2022.2\lib\idea_rt.jar=56541:D:\JetBrains\IntelliJ IDEA 2022.2\bin" -Dfile.encoding=windows-1251 -classpath D:\Projects\Java\Information_Theory_laboratory\target\classes;C:\Users\litov\.m2\repository\org\apache\pdfbox\pdfbox\2.0.24\pdfbox-2.0.24.jar;C:\Users\litov\.m2\repository\org\apache\pdfbox\fontbox\2.0.24\fontbox-2.0.24.jar;C:\Users\litov\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Users\litov\.m2\repository\commons-io\commons-io\2.11.0\commons-io-2.11.0.jar org.InfTh.lab2.Lab_2
*/

//а 0.06 б 0.08 в 0.3 г 0.12 д 0.14 е 0.09 є 0.03 ж 0.06 з 0.08 й 0.04

public class Lab_2 {
    public static void main(String[] args) {
        System.out.println("Виконала:\nСтудентка КН-201\nЛітовська Олена");
        var letters = sortToList(input());
        System.out.printf("\nСума ймовірностей: %.2f\n", sumProbability(letters));
        System.out.println("Посортовані букви:");
        printList(letters, false);
        Shannon_Fano(letters, (b)->{});
        System.out.println("\nБукви, їх ймовірності та коди, сформовані методом Шеннона—Фано:");
        printList(letters, true);
        System.out.print("\nСередня довжина кодового слова: " + averageWordLenght(letters));
        System.out.print("\nМінімальна довжина кодового слова: " + minimumWordLenght(letters));
    }

    static Map<Character, LetterInfo> input(){
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("\nВведіть кількість букв: ");
        }while (!scan.hasNextInt());
        int lenght = scan.nextInt();
        Map<Character, LetterInfo> letters = new LinkedHashMap<>(lenght);
        System.out.print("Введіть букви та їх ймовірності: ");
        for (int i = 0; i < lenght; i++) {
            char c = scan.next().charAt(0);
            LetterInfo letterInfo = new LetterInfo();
            letterInfo.setProbability(Float.parseFloat(scan.next()));
            letters.put(c,letterInfo);
        }
        return letters;
    }
    static List<Map.Entry<Character, LetterInfo>> sortToList(Map<Character, LetterInfo> map){
        return map.entrySet()
                .stream()
                .sorted(Map.Entry
                        .comparingByValue(Comparator.reverseOrder()))
                .toList();
    }
    static void printList(List<Map.Entry<Character, LetterInfo>> list, boolean withCode){
        list.forEach(e -> {
            System.out.printf("\t%c - %.2f%s\n",
                e.getKey(), e.getValue().getProbability()
                , withCode ? (" : " + e.getValue().getCode()) : "");
        });
    }
}
/*
10

*/

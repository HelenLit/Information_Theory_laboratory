package org.InfTh.lab2;

import org.InfTh.LetterInfo;

import java.util.*;

import static org.InfTh.Analyzer.*;


//� 0.06 � 0.08 � 0.3 � 0.12 � 0.14 � 0.09 � 0.03 � 0.06 � 0.08 � 0.04

public class Lab_2 {
    public static void main(String[] args) {
        var letters = sortToList(input());
        System.out.printf("\n���� �����������: %.2f\n", sumProbability(letters));
        System.out.println("���������� �����:");
        printList(letters, false);
        Shannon_Fano(letters);
        System.out.println("\n�����, �� ��������� �� ����, ��������� ������� ������������:");
        printList(letters, true);
        System.out.print("\n������� ������� �������� �����: " + averageWordLenght(letters));
        System.out.print("\n̳������� ������� �������� �����: " + minimumWordLenght(letters));
    }

    static Map<Character, LetterInfo> input(){
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("\n������ ������� ����: ");
        }while (!scan.hasNextInt());
        int lenght = scan.nextInt();
        Map<Character, LetterInfo> letters = new LinkedHashMap<>(lenght);
        System.out.print("������ ����� �� �� ���������: ");
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

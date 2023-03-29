package org.InfTh;

import java.util.Scanner;

public class DataComprService {
    private static Analyzer analyzer;

    public static void input(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Обреріть мову алфавіту(1 - en, 2 - ukr): ");
        int res = Integer.parseInt(scan.next());
        if(res == 1){
            analyzer = new Analyzer(Alphabet.ENG);
        }else
            analyzer = new Analyzer(Alphabet.UKR);

        PrepareTxt prepTxt = new PrepareTxt();
        do {
            System.out.print("Джерело надходження тексту(1 - консоль, 2 - файл): ");
            res = Integer.parseInt(scan.next());
            if (res == 1) {
                prepTxt.setInputToConsole();
                System.out.println("Кінець введення - пустий рядок\nВведіть текст для аналізу:");
            } else {
                System.out.print("Введіть назву файлу: ");
                prepTxt.setInputToFile(scan.nextLine().trim());
            }
            char answ;
            System.out.print("Вивести введені дані?(y - так, n - ні): ");
            answ = scan.nextLine().charAt(0);
            if(answ == 'y'){
                prepTxt.outputText();
            }
        }


        System.out.print("Enter path to the book you want to analyze: ");
        PrepareTxt.extractText(scan.next());
        scan.nextLine();
        analyzer.analyzeText(PrepareTxt.getFile());
        var listOfLetters = analyzer.sort();
        printAlphabet(listOfLetters);
        printAmountOfInfoLetter(listOfLetters);
        System.out.print("\nEnter your full name: "); //Litovska Olena
        String name = scan.nextLine();
        System.out.println("Amount of information in \"" + name + "\": " + getAmountOfInfo(name, analyzer.getAlphabetTable()));
        printEntropy(listOfLetters);
    }
}

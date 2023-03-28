package org.TI_lab;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
//D:\Books\Rich-Dad-Poor-Dad-eBook.pdf
//D:\Books\Schildt_H-Java_The_Complete_Reference_12th_edition_-_2021.pdf
public class Lab_1 {
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter path of the book you want to analyze: ");
        prepTxt.extractText(scan.next());
        scan.nextLine();
        analyzer.analyzeText(prepTxt.getFile());
        var listOfLetters = analyzer.sort();
        printAlphabet(listOfLetters);
        printAmountOfInfoLetter(listOfLetters);
        System.out.print("\nEnter your full name: "); //Litovska Olena
        String name = scan.nextLine();
        System.out.println("Amount of information in \"" + name + "\": " + getAmountOfInfo(name, analyzer.getAlphabetTable()));
        printEntropy(listOfLetters);

        System.out.print("\nEnter your N and k: ");
        int N = scan.nextInt();
        int k = scan.nextInt();
        float sysentr = printEntropyOfSystems(N);
        printPartialCondEntropy(k);
        float fce = printFullCondEntropy(k, N);
        float ce = printCompatEntropy(k, N);
        mutualEntropy(sysentr, fce, ce, N, k);

    }
    private static String line = "-----------------------------";
    public static void printAlphabet(List<Map.Entry<Character, LetterInfo>> list){
        System.out.println("\nSorted list of letters and their probabilities:");
        System.out.println(line);
        System.out.print("|\tLetter\t|\t\tpi\t\t|\n");
        System.out.print(line);
        list.forEach(e -> System.out.printf("\n|\t%-8c|\t%f\t|",e.getKey(), e.getValue().getProbability()));
        System.out.println("\n"+line);
    }

    public static void printAmountOfInfoLetter(List<Map.Entry<Character, LetterInfo>> list){
        System.out.println("\nLetters and their amounts of information:");
        System.out.println(line);
        System.out.print("|\tLetter\t|\t\tI\t\t|\n");
        System.out.print(line);
        list.forEach(e -> System.out.printf("\n|\t%-8c|\t%f\t|",e.getKey(), e.getValue().countAmountOfInfo()));
        System.out.println("\n"+line);
    }

    public static double getAmountOfInfo(String message, Map<Character, LetterInfo> table){
        double result = 0.0;
        for (Character ch: message.toCharArray()) {
            try{
                result += table.get(ch).getAmountOfInfo();
            }catch (NullPointerException e){
                continue;
            }
        }
        return result;
    }

    public static void printEntropy(List<Map.Entry<Character, LetterInfo>> list){
        System.out.println("\nEntropy for each letter:");
        System.out.println(line);
        System.out.print("|\tLetter\t|\t\tH(X)\t|\n");
        System.out.print(line);
        double sum = 0.0;
        for (var e: list) {
            float entropy = e.getValue().countEntropy();
            System.out.printf("\n|\t%-8c|\t%f\t|",e.getKey(), entropy);
            sum += entropy;
        }
        System.out.println("\n"+line);
        System.out.println("\nSum: " + sum);
    }
    private static String longLine = "----------------------------------------------------------------------";
    public static float printEntropyOfSystems(int N){
        N += 2;
        float prob = (float) (1.0/N);
        float entropy = prob * (float)(-(Math.log(prob)/Math.log(2)) * N);
        System.out.println(longLine);
        System.out.println("Entropy of system X H(X): " + entropy);
        System.out.println(longLine);
        System.out.println("Entropy of system Y H(Y): " + entropy);
        System.out.println(longLine);
        return entropy;
    }

    public static float printPartialCondEntropy(int k){
        float prob = (float)k;
        while (prob >= 1.0){
            prob /= 10;
        }
        float partConEntr = (float) -(prob*(Math.log(prob)/Math.log(2)) + (1.0-prob)*(Math.log(1.0-prob)/Math.log(2)));
        System.out.println("Partial conditional entropy H(Y/x2): " + partConEntr);
        System.out.println(longLine);
        return partConEntr;
    }

    public static float printFullCondEntropy(int k, int N){
        N += 2;
        double prob1 = (float)k;
        while (prob1 >= 1.0){
            prob1 /= 10;
        }
        float prob2 = (float) ((1.0/N)*prob1);
        float fullConEntr = (float) -(N*2*(prob2*(Math.log(prob1)/Math.log(2))));
        System.out.println("Full conditional entropy H(Y/X): " + fullConEntr);
        System.out.println(longLine);
        return (float) fullConEntr;
    }

    public static float printCompatEntropy(int k, int N){
        N += 2;
        float prob1 = (float)k;
        while (prob1 >= 1.0){
            prob1 /= 10;
        }
        float prob2 = (float) ((1.0/N)*prob1);
        float compEntr = (float) -(N*2*(prob2*(Math.log(prob2)/Math.log(2))));
        System.out.println("Compatible entropy H(Y,X): " + compEntr);
        System.out.println(longLine);
        return (float) compEntr;
    }
    public static void mutualEntropy(float sysentr, float fce, float ce, int N, int k){
        N += 2;
        float prob1 = (float)k;
        while (prob1 >= 1.0){
            prob1 /= 10;
        }
        float prob2 = (float) ((1.0/N)*prob1);
        System.out.println("Mutual entropy H(Y<->X) #1: " + (float)(N*-2*prob2*(Math.log(prob2/(prob1*prob1))/Math.log(2))));
        System.out.println(longLine);
        System.out.println("Mutual entropy H(Y<->X) #2: " + (sysentr-fce));
        System.out.println(longLine);
        System.out.println("Mutual entropy H(Y<->X) #3: " + (2*sysentr-ce));
        System.out.println(longLine);
    }
}
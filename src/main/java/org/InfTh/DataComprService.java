package org.InfTh;
import java.util.Scanner;
//D:\Books\Rich-Dad-Poor-Dad-eBook.pdf
public class DataComprService {
    private static Analyzer analyzer;

    public static void input(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Обреріть мову алфавіту(1 - en, 2 - ukr): ");
        int res = Integer.parseInt(scan.nextLine());
        if(res == 1){
            analyzer = new Analyzer(Alphabet.ENG);
        }else
            analyzer = new Analyzer(Alphabet.UKR);

        PrepareTxt prepTxt = new PrepareTxt();
        char answ;
        do {
            System.out.print("Джерело надходження тексту(1 - консоль, 2 - файл): ");
            res = Integer.parseInt(scan.nextLine());
            if (res == 1) {
                prepTxt.setInputToConsole();
                System.out.println("Кінець введення - пустий рядок\nВведіть текст для аналізу:");
            } else {
                System.out.print("Введіть назву файлу: ");
                prepTxt.setInputToFile(scan.nextLine().trim());
            }
            prepTxt.extractText();
            System.out.print("Вивести введені дані?(y - так, n - ні): ");
            answ = scan.nextLine().charAt(0);
            if(answ == 'y'){
                prepTxt.outputText();
            }
            System.out.print("Продовжити введення тексту?(y - так, n - ні): ");
            answ = scan.nextLine().charAt(0);
        }while (answ == 'y');

        analyzer.analyzeText(prepTxt.getFile());
        analyzer.sort();
    }
    private static String line = "-----------------------------";
    public static void printAlphabet(boolean withCode){
        if(!withCode) System.out.println(line);
        System.out.print("|\tSymbol\t|\t\tpi\t\t|" + (withCode ? ("\tCode:"):"\n"));
        if(!withCode) System.out.print(line);
        analyzer.getListOfLetters().forEach(e -> {
            System.out.printf("\n|\t%4c\t|\t%f\t|\t%-10s",
                    e.getKey(), e.getValue().getProbability()
                    , (withCode ? (e.getValue().getCode()) : ""));
        });
        if(!withCode) System.out.println("\n"+line);

    }
    public static void printEntropy(){
        System.out.println(line);
        System.out.print("|\tLetter\t|\t\tH(X)\t|\n");
        System.out.print(line);
        double sum = 0.0;
        for (var e: analyzer.getListOfLetters()) {
            float entropy = e.getValue().getEntropy();
            System.out.printf("\n|\t%4c\t|\t%-8.8s\t|",e.getKey(), (entropy == 0.0f) ? "----": Float.toString(entropy));
            sum += entropy;
        }
        System.out.println("\n"+line);

    }
    public static float getProbSum(){
        return Analyzer.sumProbability(analyzer.getListOfLetters());
    }
    public static float getEntropybSum(){
        return Analyzer.sumEntropy(analyzer.getListOfLetters());
    }
    public static void printAmountOfInfoLetter(){
        System.out.println(line);
        System.out.print("|\tSymbol\t|\t\tI\t\t|\n");
        System.out.print(line);
        analyzer.getListOfLetters().forEach(e -> {
            float value = e.getValue().getAmountOfInfo();
            System.out.printf("\n|\t%4c\t|\t%-8.8s\t|", e.getKey(), (value == 0.0f) ? "----" : Float.toString(value));
        });
        System.out.println("\n"+line);
    }

    public static void completeShannonFano(){
        Analyzer.Shannon_Fano(analyzer.getListOfLetters());
    }
    public static double countMessageAmountOfInfo(String message){
        double result = 0.0;
        for (Character ch: message.toCharArray()) {
            try{
                result += analyzer.getAlphabetTable().get(ch).getAmountOfInfo();
            }catch (NullPointerException e){
                continue;
            }
        }
        return result;
    }

    public static float getAverageWordLenght(){
        return Analyzer.averageWordLenght(analyzer.getListOfLetters());
    }

    public static float getMinimumWordLenght(){
        return Analyzer.minimumWordLenght(analyzer.getListOfLetters());
    }
}

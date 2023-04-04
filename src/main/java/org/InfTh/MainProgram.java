package org.InfTh;
//D:\Books\Rich-Dad-Poor-Dad-eBook.pdf
//C:\Users\litov\OneDrive\Робочий стіл\Теорія інформації\Shevchenko_Taras_Kobzar.pdf
public class MainProgram {
    public static void main(String[] args) {
        System.out.println("Виконала:\nСтудентка КН-201\nЛітовська Олена\n");
        DataComprService.input();
        System.out.println("\nСписок символів, впорядкований за спаданням ймовірності:");
        DataComprService.printAlphabet(false);
        System.out.println("\nСумарна ймовірність: " + DataComprService.getProbSum());
        System.out.println("\nСимволи та їх кількості інформації:");
        DataComprService.printAmountOfInfoLetter();
        System.out.println("\nСимволи та їх ентропії:");
        DataComprService.printEntropy();
        System.out.println("\nСумарна ентропія: " + DataComprService.getEntropybSum());
        System.out.println("\nАлгоритом Шеннона-Фанно:");
        DataComprService.completeShannonFano();
        DataComprService.printAlphabet(true);
        System.out.println("\n\nСередня довжина кодового слова: " + DataComprService.getAverageWordLenght());
        System.out.println("Мінімальна довжина кодового слова: " + DataComprService.getMinimumWordLenght());
    }
}

/*
chcp 1251
java "-javaagent:D:\JetBrains\IntelliJ IDEA 2022.2\lib\idea_rt.jar=62383:D:\JetBrains\IntelliJ IDEA 2022.2\bin" -Dfile.encoding=windows-1251 -classpath D:\Projects\Java\Information_Theory_laboratory\target\classes;C:\Users\litov\.m2\repository\org\apache\pdfbox\pdfbox\2.0.24\pdfbox-2.0.24.jar;C:\Users\litov\.m2\repository\org\apache\pdfbox\fontbox\2.0.24\fontbox-2.0.24.jar;C:\Users\litov\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Users\litov\.m2\repository\commons-io\commons-io\2.11.0\commons-io-2.11.0.jar org.InfTh.lab2.Lab_2

* */

package org.InfTh;

public class MainProgram {
    public static void main(String[] args) {
        DataComprService.input();
        System.out.println("\nСписок символів, впорядкований за спаданням ймовірності:");
        DataComprService.printAlphabet(false);
        System.out.println("Сумарна ймовірність: " + DataComprService.getProbSum());
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

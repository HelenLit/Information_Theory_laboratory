package org.InfTh;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Analyzer {

    private int totalQuantity = 0;
    private final Map<Character, LetterInfo> alphabetTable;
    private List<Map.Entry<Character, LetterInfo>> listOfLetters;

    public Analyzer(Character[] array) {
        if(array == null)
            throw new NullPointerException();
        alphabetTable = new  HashMap<>(array.length);
        Arrays.stream(array).toList().forEach(l -> alphabetTable.put(l, new LetterInfo()));
    }

    public void incrementTotalQuantity(){
        totalQuantity++;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public Map<Character, LetterInfo> getAlphabetTable() {
        return alphabetTable;
    }

    public void analyzeText(File file){
        int character=0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((character = reader.read()) != -1) {
                try{
                if(Character.isLetter(character)) {
                    alphabetTable.get((char) character).incrementQuantity();
                }
                }catch (NullPointerException e){
                   // System.out.println("Could not find this letter in alphabet - " + (char)character);
                    continue;
                }
                incrementTotalQuantity();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error file was not found");
        } catch (IOException e) {
            System.out.println("Error while reading file");
        }
    }

    public void setProbabilityToAll(){
        alphabetTable.forEach((key, value) -> value.countProbability(totalQuantity));
    }
    public List<Map.Entry<Character, LetterInfo>> sort(){
        setProbabilityToAll();
        return listOfLetters = alphabetTable.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).toList();
    }
    public static float sumEntropy(List<Map.Entry<Character, LetterInfo>> list){
        return (float) list.stream()
                .mapToDouble(e -> e.getValue().getEntropy())
                .sum();
    }
    public static float sumProbability(List<Map.Entry<Character, LetterInfo>> list){
        return (float) list.stream()
                .mapToDouble(e -> e.getValue().getProbability())
                .sum();
    }
    public static void Shannon_Fano(List<Map.Entry<Character, LetterInfo>> list, Consumer<Boolean> print){
        if(list.size() == 1) return;
        float leftSum = -1.0f, rightSum = 0.0f;
        List<Map.Entry<Character, LetterInfo>> leftSublist = null, rightSublist = null;
        int mid = (int)(list.size()/2.0 + 0.5); //ceil
        for (int i = 0; leftSum < rightSum && i < mid; i++){
            leftSublist = list.subList(0,i+1);
            leftSum = sumProbability(leftSublist);
            rightSublist = list.subList(i+1,list.size());
            rightSum = sumProbability(rightSublist);
        }
        leftSublist.forEach(e -> e.getValue().concatCode("0"));
        rightSublist.forEach(e -> e.getValue().concatCode("1"));
        print.accept(true);
        Shannon_Fano(leftSublist,print);
        Shannon_Fano(rightSublist,print);
    }
    public static float averageWordLenght(List<Map.Entry<Character, LetterInfo>> list){
        return (float) list.stream()
                .mapToDouble(e -> e.getValue()
                            .getProbability() * e.getValue()
                                .getCode()
                                .length())
                .sum();
    }
    public static float minimumWordLenght(List<Map.Entry<Character, LetterInfo>> list){
        return (float) list.stream()
                .mapToDouble(e -> e.getValue()
                        .getEntropy())
                .sum();
    }

    public List<Map.Entry<Character, LetterInfo>> getListOfLetters() {
        return listOfLetters;
    }
}

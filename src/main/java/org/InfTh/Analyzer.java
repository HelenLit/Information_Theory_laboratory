package org.InfTh;

import java.io.*;
import java.util.*;

public class Analyzer {
    private int totalQuantity = 0;
     private Map<Character, LetterInfo> alphabetTable = new LinkedHashMap<>(52);
    {
        Character[] Letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Arrays.stream(Letters).toList().forEach(l -> alphabetTable.put(l, new LetterInfo()));
    }

    public void incrementTotalQuantity(){
        totalQuantity++;
    }

    public Map<Character, LetterInfo> getAlphabetTable() {
        return alphabetTable;
    }

    public void analyzeText(File file){
        int character=0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((character = reader.read()) != -1) {
                if(Character.isLetter(character)) {
                    alphabetTable.get((char) character).incrementQuantity();
                    incrementTotalQuantity();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error file was not found");
        } catch (IOException e) {
            System.out.println("Error while reading file");
        }catch (NullPointerException e){
            System.out.println("Last character - " + (char)character);
        }
    }

    public void setProbabilityToAll(){
        alphabetTable.forEach((key, value) -> value.countProbability(totalQuantity));
    }
    public List<Map.Entry<Character, LetterInfo>> sort(){
        setProbabilityToAll();
        return alphabetTable.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).toList();
    }

    public static float sumProbability(List<Map.Entry<Character, LetterInfo>> list){
        return (float) list.stream()
                .mapToDouble(e -> e.getValue().getProbability())
                .sum();
    }
    public static void Shannon_Fano(List<Map.Entry<Character, LetterInfo>> list){
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
        Shannon_Fano(leftSublist);
        Shannon_Fano(rightSublist);
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
}

package org.TI_lab;

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
}

package org.TI_lab;

public class LetterInfo implements Comparable{
    private int quantity = 0;
    private float probability = 0;
    private float amountOfInfo = 0;

    private float entropy = 0;
    public void incrementQuantity(){
        quantity++;
    }

    public float countProbability(int totalQuantity){
        return probability = (float)getQuantity()/totalQuantity;
    }

    public float countAmountOfInfo(){
        return amountOfInfo = (float) -(Math.log(getProbability())/Math.log(2));
    }

    public float countEntropy(){
        return amountOfInfo = probability*amountOfInfo;
    }
    public int getQuantity() {
        return quantity;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public int compareTo(Object li) {
        return Float.compare(probability, ((LetterInfo)li).getProbability());
    }

    public float getAmountOfInfo() {
        return amountOfInfo;
    }

    public float getEntropy() {
        return entropy;
    }
}

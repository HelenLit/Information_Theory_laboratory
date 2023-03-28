package org.InfTh;

public class LetterInfo implements Comparable {
    private int quantity = 0;
    private float probability = -1.0f;
    private float amountOfInfo = -1.0f;
    private float entropy = -1.0f;
    private String code = "";

    public void incrementQuantity() {
        quantity++;
    }

    public float countProbability(int totalQuantity) {
        return probability = (float) getQuantity() / totalQuantity;
    }

    private float countAmountOfInfo() {
        return amountOfInfo = (float) -(Math.log(getProbability()) / Math.log(2));
    }
    public float getAmountOfInfo() {
        if(amountOfInfo < 0){
            countAmountOfInfo();
        }
        return amountOfInfo;
    }

    private float countEntropy() {
        return entropy = probability * getAmountOfInfo();
    }

    public int getQuantity() {
        return quantity;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public int compareTo(Object li) {
        return Float.compare(probability, ((LetterInfo) li).getProbability());
    }

    public void setProbability(float probability) {
            this.probability = probability;
    }

    public float getEntropy() {
        if(entropy < 0){
            countEntropy();
        }
        return entropy;
    }

    public String getCode() {
        return code;
    }

    public void concatCode(String add){
        code += add;
    }

}

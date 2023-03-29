package org.InfTh;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.commons.io.FileUtils;

//D:\Books\Rich-Dad-Poor-Dad-eBook.pdf
public class PrepareTxt {
    private File file = new File("extracted.txt");
    private boolean inputTypeCons;
    private String sourceName;

    public void extractText() {
        file.deleteOnExit();
        if(inputTypeCons)
            extractFromConsole();
        else
            extractFromFile();
    }
    private void extractFromConsole(){
        Scanner scan = new Scanner(System.in);
        String line;
        try {
            while(!(line = scan.nextLine()).isBlank()){
                FileUtils.writeStringToFile(
                        file
                        ,line
                        ,Charset.defaultCharset().name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void extractFromFile(){
        try (PDDocument document = PDDocument.load(new File(sourceName)))
        {
            AccessPermission ap = document.getCurrentAccessPermission();
            if (!ap.canExtractContent())
                throw new IOException("You do not have permission to extract text");

            PDFTextStripper stripper = new PDFTextStripper();
            FileUtils.writeStringToFile(
                    file
                    ,stripper.getText(document).trim()
                    ,Charset.defaultCharset().name());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInputToConsole(){
        inputTypeCons = true;
    }

    public void setInputToFile(String src){
        inputTypeCons = false;
        setSourceName(src);
    }
    public  void setInputToFile(){
        inputTypeCons = false;
    }

    public void outputText(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("fight.json"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("??????? ????????.\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("??????? ????????.\n");
            e.printStackTrace();
        }
    }

    public  File getFile() {
        return file;
    }

    public  void setSourceName(String sourceName) {
        if(sourceName == null || sourceName.isBlank())
            throw new IllegalArgumentException();
        this.sourceName = sourceName;
    }
}

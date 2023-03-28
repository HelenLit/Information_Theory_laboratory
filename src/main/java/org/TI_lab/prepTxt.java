package org.TI_lab;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.commons.io.FileUtils;

//D:\Books\Rich-Dad-Poor-Dad-eBook.pdf
public class prepTxt {
    private static File file = new File("extracted.txt");

    public static void extractText(String sourceName) {
        file.deleteOnExit();
        try (PDDocument document = PDDocument.load(new File(sourceName)))
        {
            AccessPermission ap = document.getCurrentAccessPermission();
            if (!ap.canExtractContent())
                throw new IOException("You do not have permission to extract text");

            PDFTextStripper stripper = new PDFTextStripper();
            FileUtils.writeStringToFile(file,stripper.getText(document).trim(), Charset.defaultCharset().name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFile() {
        return file;
    }
}

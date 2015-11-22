/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class File {
    
    public PrintWriter writer;
    public ArrayList<Integer> array;
    private String fileName;
    
    public File() throws IOException {
        array = new ArrayList<>();
        fileName = "record.txt";
        readFile();
    }
    
    public boolean writeFile(int pFishStricken, int pFishMissed) throws FileNotFoundException, UnsupportedEncodingException {
        boolean result = false;
        writer = new PrintWriter(fileName, "UTF-8");
        this.writer.println(""+pFishStricken);
        this.writer.println(""+pFishMissed);
        this.writer.close();
        return result;
    }
    
    private void readFile() throws IOException {
        // The name of the file to open.
        int i = 0;

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                array.add(Integer.parseInt(line));
                i++;
            }
            // Always close files.
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        System.out.println(line);
    }
    
//    public ArrayList getData() throws IOException {
//        readFile();
//        //return array;
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class FileOperation {
    
    public PrintWriter writer;
    public ArrayList<Integer> array;
    private File file;
    private String fileName;
    private StringWriter stringWriter;
    private FileWriter fileWrite;
    private int strickenFish, missedFish;
    
    public FileOperation() {
        array = new ArrayList<>();
        fileName = "record.txt";
        file = new File(fileName);
    }
    
    public void saveData(int pPoints) {
        if(this.file.exists()) {
            stringWriter = new StringWriter();
            try {
                fileWrite = new FileWriter(fileName);
                stringWriter.write("" + pPoints);
                fileWrite.write(stringWriter.toString());
                fileWrite.close();
            } catch (IOException ex) {
                Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            saveData(pPoints);
        }
    }
    
    private int readFile() throws IOException {
        int points = -1;

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                points = Integer.parseInt(line);
            }
            // Always close files.
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return points;
    }
    
    public int getData() {
        int points = -1;
        try {
            points = readFile();
        } catch (IOException ex) {
            Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return points;
    }
}

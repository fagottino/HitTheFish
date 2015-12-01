/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class File {
    
    public PrintWriter writer;
    public ArrayList<Integer> array;
    private String fileName;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWrite;
    
    public File() {
        array = new ArrayList<>();
        fileName = "record.txt";
    }
    
    public void writeFile(int pFishStricken, int pFishMissed) {
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        bufferedWrite = new BufferedWriter(fileWriter);
        try {
            bufferedWrite.write(""+pFishStricken);
            bufferedWrite.write(""+pFishMissed);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bufferedWrite.flush();
            bufferedWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
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

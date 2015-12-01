/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
    private StringWriter stringWriter;
    private BufferedWriter bufferedWrite;
    private int strickenFish, missedFish;
    
    public File() {
        array = new ArrayList<>();
        fileName = "record.txt";
    }
    
    public void saveData(int pStrickenFish, int pMissedFish) {
        stringWriter = new StringWriter();
        bufferedWrite = new BufferedWriter(stringWriter);
        try {
            //bufferedWrite.write(""+pFishStricken);
            //bufferedWrite.write(""+pFishMissed);
            bufferedWrite.write("vsdavdf");
            bufferedWrite.write("2222222");
            bufferedWrite.flush();
            bufferedWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    private void readFile() throws IOException {
//        // The name of the file to open.
//        int i = 0;
//
//        // This will reference one line at a time
//        String line = null;
//
//        try {
//            // FileReader reads text files in the default encoding.
//            FileReader fileReader = new FileReader(fileName);
//
//            // Always wrap FileReader in BufferedReader.
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            while((line = bufferedReader.readLine()) != null) {
//                array.add(Integer.parseInt(line));
//                i++;
//            }
//            // Always close files.
//            bufferedReader.close();
//        } catch(FileNotFoundException ex) {
//            System.out.println("Unable to open file '" + fileName + "'");
//        } catch(IOException ex) {
//            System.out.println("Error reading file '" + fileName + "'");
//        }
//        System.out.println(line);
//    }
    
//    public ArrayList getData() throws IOException {
//        readFile();
//        //return array;
//    }
}

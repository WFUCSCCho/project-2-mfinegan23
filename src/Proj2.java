/******************************************************************************
 * @file: Proj2.java
 * @description: This program reads Job data from a file, processes it,
 *               and performs insertion and search operations on BSTs and AVL trees.
 *               It measures and records the time taken for these operations.
 * @author: Max Finegan
 * @date: October 23, 2024
 ******************************************************************************/

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;


public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.out.println(args[0]);
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

	// FINISH ME

        ArrayList<Job> jobs = new ArrayList<>();

        // All variables for a job object
        Integer workYear;
        String jobTitle;
        String jobCategory;
        String salaryCurrency;
        Integer salary;
        Integer salaryInUSD;
        String employeeResidence;
        String experienceLevel;
        String employmentType;
        String workSetting;
        String companyLocation;
        String companySize;

        String currLine;
        String[] parts;

        // Loop through the csv and make a new Job object out of each line
        for (int i = 0; i < numLines; i++) {
            currLine = inputFileNameScanner.nextLine(); // Get the current line

            parts = currLine.split(","); // Split the current line by commas

                // Create a new Job object with parsed data
                workYear = Integer.parseInt(parts[0]);
                jobTitle = parts[1];
                jobCategory = parts[2];
                salaryCurrency = parts[3];
                salary = Integer.parseInt(parts[4]);
                salaryInUSD = Integer.parseInt(parts[5]);
                employeeResidence = parts[6];
                experienceLevel = parts[7];
                employmentType = parts[8];
                workSetting = parts[9];
                companyLocation = parts[10];
                companySize = parts[11];

                // Add the Job to the ArrayList
                jobs.add(new Job(workYear, jobTitle, jobCategory, salaryCurrency, salary, salaryInUSD,
                        employeeResidence, experienceLevel, employmentType, workSetting, companyLocation, companySize));

        }

        // Sort the ArrayList
        Collections.sort(jobs);
        ArrayList<Job> sortedJobs = jobs;

        // Shuffle the ArrayList
        Collections.shuffle(jobs);
        ArrayList<Job> shuffledJobs = jobs;

        // Declare and initialize the sorted BST
        BST<Job> mybstSorted = new BST<>();
        // Declare and initialize the shuffled BST
        BST<Job> mybstShuffled = new BST<>();
        // Declare and initialize the sorted AVL Tree
        AvlTree<Job> myavlSorted = new AvlTree<>();
        // Declare and initialize the shuffled AVL Tree
        AvlTree<Job> myavlShuffled = new AvlTree<>();

        long startTime;
        long endTime;

        // Insert
        double elapsedMillis_SortedBST_Insert;
        double elapsedMillis_ShuffledBST_Insert;
        double elapsedMillis_SortedAVL_Insert;
        double elapsedMillis_ShuffledAVL_Insert;

        //Search
        double elapsedMillis_SortedBST_Search;
        double elapsedMillis_ShuffledBST_Search;
        double elapsedMillis_SortedAVL_Search;
        double elapsedMillis_ShuffledAVL_Search;


        // Do the inserts

        System.out.println();
        System.out.println("------------------------------Inserting Nodes into the four different trees------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Insert data into mybstSorted
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            mybstSorted.insert(sortedJobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedBST_Insert = (endTime - startTime) / 1e6;
        System.out.println("-----Sorted BST of " + numLines + " lines takes " + elapsedMillis_SortedBST_Insert + " milliseconds to insert all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Insert data into mybstShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            mybstShuffled.insert(shuffledJobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledBST_Insert = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled BST of " + numLines + " lines takes " + elapsedMillis_ShuffledBST_Insert + " milliseconds to insert all nodes.----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Insert data into myavlSorted
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            myavlSorted.insert(sortedJobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedAVL_Insert = (endTime - startTime) / 1_000_000.0;
        System.out.println("-----Sorted AVL Tree of " + numLines + " lines takes " + elapsedMillis_SortedAVL_Insert + " milliseconds to insert all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Insert data into myavlShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            myavlShuffled.insert(shuffledJobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledAVL_Insert = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled AVL Tree of " + numLines + " lines takes " + elapsedMillis_ShuffledAVL_Insert + " milliseconds to insert all nodes.----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Do the searches

        System.out.println();
        System.out.println("------------------------------Searching the four different trees------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Search data in mybstSorted
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            mybstSorted.search(jobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedBST_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("-----Sorted BST of " + numLines + " lines takes " + elapsedMillis_SortedBST_Search + " milliseconds to search all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Search data in mybstShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            mybstShuffled.search(jobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledBST_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled BST of " + numLines + " lines takes " + elapsedMillis_ShuffledBST_Search + " milliseconds to search all nodes.----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Search data in myavlSorted
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            myavlSorted.contains(jobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedAVL_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("-----Sorted AVL Tree of " + numLines + " lines takes " + elapsedMillis_SortedAVL_Search + " milliseconds to search all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Search data in myavlShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++){
            myavlShuffled.contains(jobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledAVL_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled AVL Tree of " + numLines + " lines takes " + elapsedMillis_ShuffledAVL_Search + " milliseconds to search all nodes.----");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Write the results to the output.txt file
        writeToFile( numLines + ", " + elapsedMillis_SortedBST_Insert + ", " + elapsedMillis_ShuffledBST_Insert + ", " +
                elapsedMillis_SortedAVL_Insert + ", " + elapsedMillis_ShuffledAVL_Insert + ", " + elapsedMillis_SortedBST_Search + ", " +
                elapsedMillis_ShuffledBST_Search + ", " + elapsedMillis_SortedAVL_Search + ", " + elapsedMillis_ShuffledAVL_Insert + "\n", "./src/output.txt");


    }

    // Generate the result file
    public static void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(content);
            //writer.newLine();
        }
        catch (IOException e){ // Error handling
            e.printStackTrace();
        }
    }
}

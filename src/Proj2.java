/******************************************************************************
 * @file: Proj2.java
 * @description: This program reads Job data from a file, processes it,
 *               and performs insertion and search operations on BSTs and AVL trees.
 *               It measures and records the time taken for these operations in the console and CSV format.
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
        ArrayList<Job> sortedJobs = new ArrayList<>(jobs);
        Collections.sort(sortedJobs);

        // Shuffle the ArrayList
        ArrayList<Job> shuffledJobs = new ArrayList<>(jobs);
        Collections.shuffle(shuffledJobs);

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
        double elapsedSeconds_SortedBST_Insert;
        double elapsedMilis_SortedBST_Insert;
        double elapsedSeconds_ShuffledBST_Insert;
        double elapsedSeconds_SortedAVL_Insert;
        double elapsedSeconds_ShuffledAVL_Insert;

        // Search
        double elapsedSeconds_SortedBST_Search;
        double elapsedSeconds_ShuffledBST_Search;
        double elapsedSeconds_SortedAVL_Search;
        double elapsedSeconds_ShuffledAVL_Search;


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
        elapsedSeconds_SortedBST_Insert = (endTime - startTime) / 1_000_000_000.0; // divide to get time in seconds
        System.out.println("-----Sorted BST of " + numLines + " lines takes " + elapsedSeconds_SortedBST_Insert + " seconds to insert all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Insert data into mybstShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            mybstShuffled.insert(shuffledJobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedSeconds_ShuffledBST_Insert = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("----Shuffled BST of " + numLines + " lines takes " + elapsedSeconds_ShuffledBST_Insert + " seconds to insert all nodes.----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Insert data into myavlSorted
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            myavlSorted.insert(sortedJobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedSeconds_SortedAVL_Insert = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("-----Sorted AVL Tree of " + numLines + " lines takes " + elapsedSeconds_SortedAVL_Insert + " seconds to insert all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Insert data into myavlShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            myavlShuffled.insert(shuffledJobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedSeconds_ShuffledAVL_Insert = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("----Shuffled AVL Tree of " + numLines + " lines takes " + elapsedSeconds_ShuffledAVL_Insert + " seconds to insert all nodes.----");
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
        elapsedSeconds_SortedBST_Search = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("-----Sorted BST of " + numLines + " lines takes " + elapsedSeconds_SortedBST_Search + " seconds to search all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Search data in mybstShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            mybstShuffled.search(jobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedSeconds_ShuffledBST_Search = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("----Shuffled BST of " + numLines + " lines takes " + elapsedSeconds_ShuffledBST_Search + " seconds to search all nodes.----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Search data in myavlSorted
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++) {
            myavlSorted.contains(jobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedSeconds_SortedAVL_Search = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("-----Sorted AVL Tree of " + numLines + " lines takes " + elapsedSeconds_SortedAVL_Search + " seconds to search all nodes.-----");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        // Search data in myavlShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < jobs.size(); i++){
            myavlShuffled.contains(jobs.get(i));
        }
        endTime = System.nanoTime();
        elapsedSeconds_ShuffledAVL_Search = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("----Shuffled AVL Tree of " + numLines + " lines takes " + elapsedSeconds_ShuffledAVL_Search + " seconds to search all nodes.----");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Write the results to the output.txt file
        writeToFile(numLines + ", " + elapsedSeconds_SortedBST_Insert + ", " + elapsedSeconds_ShuffledBST_Insert + ", " +
                elapsedSeconds_SortedAVL_Insert + ", " + elapsedSeconds_ShuffledAVL_Insert + ", " + elapsedSeconds_SortedBST_Search + ", " +
                elapsedSeconds_ShuffledBST_Search + ", " + elapsedSeconds_SortedAVL_Search + ", " + elapsedSeconds_ShuffledAVL_Search + "\n", "./src/output.txt");

        //System.out.println(sortedJobs);
        //System.out.println(shuffledJobs);

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

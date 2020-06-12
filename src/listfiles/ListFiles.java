package listfiles;

import java.io.File;
/**
 * @author agataporwit
 * Assignment 6
 * CSD 335 Spring 2020
 * Main to run the program
 * Do not use java.nio.file.Files for this assignment.
 * If you do use it, you can pretty much count on receiving no credit for the assignment.*/

public class ListFiles {
    public static void main(String[] args) {


        File folder;
        folder = new File("/Users/.../fileTreeWalk");
        System.out.println("Say no, no to java.nio.file - can pretty much count on receiving BIG FAT ZERO ");
        fileTree walkingTree;
        walkingTree = new fileTree(folder);
        walkingTree.allTheFiles();
        walkingTree.findFiles("museum.docx");

        System.out.println(walkingTree);

    }
}
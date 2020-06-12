package listfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 * @author agataporwit
 * Assignment 6
 * CSD 335 Spring 2020
 *  traverses the file system recursively
 *  In addition to a constructor, the class provides two public methods
 *  for traversing the file system, and a public toString method for displaying the traversal results.
 * */

public class fileTree {
//files
    class fileInfo {
        String fileType;
        int oneUp;
        File whereIsIt;
        String giveName;
    }

    private File pathname;
    private Collection<fileInfo> fileHierarchy;
    private Collection<fileInfo> filesFound;
    private int[] runner;
//pathname is a starting point for the traversal of the file system
    // File tree constructor
    public fileTree(File filePath) {
        this.pathname = filePath;
        if (pathname.isFile()) {
            throw new IllegalArgumentException("That's not right");
        }
        runner = new int[2];
        filesFound = new ArrayList<>();
        fileHierarchy = new ArrayList<>();
    }
// all files
    public void allTheFiles() {
        runner[0] = 1;
        if (runner[1] == 0) {
            allTheFiles(pathname, 0);
        }
    }

//Start directory and going down the tree
    /**
     * This method outputs a string representation of the tree structure of the file system starting at the pathname
     * specified in the constructor. Provided the argument to the constructor is the string,
     * “C:\\Users\BigJoe\Documents” --> windows, Users/../Documents/fileTreeWalk --> Mac
     * */
    private void allTheFiles(File folder, int oneUp) {
        File[] fileNames = folder.listFiles();
        oneUp++;
        for (int i = 0, fileNamesLength = fileNames.length; i < fileNamesLength; i++) {
            File file = fileNames[i];
            if (!file.isDirectory()) {
            } else {
                fileHierarchy.add(addFile("dir", file, oneUp));
                allTheFiles(file, oneUp);
            }
            if (file.isFile()) fileHierarchy.add(addFile("filez", file, oneUp));
        }
    }

    private fileInfo addFile(String type, File file, int level) {

        fileInfo fileDir = new fileInfo();
        fileDir.fileType = type;
        fileDir.oneUp = level;
        fileDir.whereIsIt = file;
        fileDir.giveName = file.getName();
        return fileDir;
    }
    //Going through fileTree,checks if any file/directory matches  fileName String
    public void findFiles(String fileName) {
        runner[1] = 1;
        if (runner[0] == 0) {
            allTheFiles(pathname, 0);
        }
        for (Iterator<fileInfo> iterator = fileHierarchy.iterator(); iterator.hasNext(); ) {
            fileInfo fileDir = iterator.next();
            if (fileName.equalsIgnoreCase(fileDir.giveName)) filesFound.add(fileDir);
        }
    }
    /**
     * This method takes a string argument that specifies a file to search for.
     * During the traversal if a file or subdirectory with the same name as the file name parameter is encountered,
     * a string is added to the output that includes the full path name, file name, and extension.*/

    public String toString() {
        StringBuilder outTheFileTree = new StringBuilder();
        outTheFileTree.append("Searched Directory: " + pathname + "\n");
        // Prints the fileTree from the input directory
        if (runner[0] == 1) {
            for (Iterator<fileInfo> iterator = fileHierarchy.iterator(); iterator.hasNext(); ) {
                fileInfo fileDir = iterator.next();
                for (int i = 0; i < fileDir.oneUp; i++) {
                    outTheFileTree.append("\t");
                }
                outTheFileTree.append(fileDir.giveName + "\n");
            }
        }
        // Prints all the files
        if (1 != runner[1]) {
        } else {
            if (filesFound.isEmpty()) {
                outTheFileTree.append("Oppps not there ");
            }
            for (Iterator<fileInfo> iterator = filesFound.iterator(); iterator.hasNext(); ) {
                fileInfo fileDir = iterator.next();
                outTheFileTree.append("\n Winner: " + fileDir.whereIsIt);
            }
        }
        runner[0] = 0;
        runner[1] = 0;
        return outTheFileTree.toString();
    }
}


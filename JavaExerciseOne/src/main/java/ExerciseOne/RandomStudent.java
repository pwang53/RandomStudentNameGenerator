package ExerciseOne;

import java.io.*;
import java.util.Random;

public class RandomStudent {
    // Pre-create a size of 10 string array to store the name
    private String[] nameArray = new String[10];

    // Declare the number of line for dynamically using
    private int numberOfLines = 0;

    // The name randomly chosen;
    private String randomName = null;

    // Flag to check if the name has been all selected
    public boolean flag = false;

    // Getter for randomName
    public String getRandomName() {
        return this.randomName;
    }

    public int getNumberOfLines() {return this.numberOfLines;}

    // Choose a random name
    public void chooseRandomName() {
        if (nameArray[0] == null) {
            return;
        }

        if (nameArray[1] == null) {
            flag = true;
        }

        // Get the random prepared for choosing a random name
        Random rand = new Random();

        // Get the random number
        int random = rand.nextInt(numberOfLines--);

        // Get the name
        String nameBeChosen = nameArray[random];

        // Display the Student Name that got selected
        System.out.println("The student: *** " + nameBeChosen + " *** is chosen");

        this.randomName = nameBeChosen;

        removeNameInArray(random);
    }

    // Read from file and store the name into array
    public void setUpNameArray() throws Exception {
        // Get the file
        //File file = new File("src/main/resources/NameList.txt");

        // If use File directly to read, then it will cause problem if .jar not in the same folder with resource file
        // So use InputStream to deal with this problem
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("NameList.txt");

        // Create buffered reader to read the file
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Declare the line
        String line;

        // Get the number of line
        while ((line = br.readLine()) != null) {
            // Dynamically create nameList Array
            // If the length of array is smaller than the numberlines
            // which means it will out of bound
            // add array length
            if (numberOfLines >= nameArray.length - 1) {
                addLengthArray(nameArray);
            }

            // Store the name into array
            nameArray[numberOfLines++] = line;
        }
    }

    // Used for adding size to array
    private void addLengthArray(String[] array) {
        String[] newArray = new String[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.nameArray = newArray;
    }

    private void removeNameInArray(int index) {
        String[] anotherArray = new String[nameArray.length - 1];
        for (int i = 0, k = 0; i < nameArray.length; i++) {
            if (i == index) continue;
            anotherArray[k++] = nameArray[i];
        }

        nameArray = anotherArray;
    }
}

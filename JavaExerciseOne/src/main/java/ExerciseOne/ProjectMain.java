/**
 * Contributor: Peiqi Wang
 * Github: https://github.com/pwang53
 * Date: 06/28/2020
 *
 * Description:
 *      It is created to generate a random student name from a list of students.
 *
 * How To Use:
 *      Click the run button, it will pop out a window and click generate button
 *      to generate student name. It can be done multiple times. Click Exit button
 *      to exit the application.
 *
 * Instruction:
 *      1. Create an Array with all student names in the class
 *      2. Randomly select one element from the list
 *      3. Display the Student Name that got selected
 *      4. Display the name of student in a pop-up box or dialog box (Need to add libraries for this)
 *      5. Push your code in Git
 *      6. Create a executable .jar file
 *      Expectation: User will click on a Jar file and it should throw a pop up with the random student name
 *      Extra: How would you make the Students list dynamic without changing the code - Array should be dynamic
 **/

package ExerciseOne;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Main class of the project
public class ProjectMain extends Application {
    // Variables need to be used later
    private static String message = "Please click button to generate random student name";
    private static RandomStudent student;
    private Scene scene;
    private BorderPane root;
    private Button btn_generate;
    private Button btn_exit;
    private Text txt_display;
    private HBox hBox_message;
    private HBox hBox_button;
    private VBox vBox_display;

    // Main function
    public static void main(String[] args) {
        student = new RandomStudent();
        try {
            student.setUpNameArray();
        } catch (Exception e) {
            message = "Unable to open the file, something went wrong";
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setUpWindow(primaryStage);

        buttonClick(primaryStage);
    }

    // Generate a random name
    public void generateName() {
        student.chooseRandomName();
        String name = student.getRandomName();
        message = "*** " + name + " *** is chosen. Left: " + student.getNumberOfLines();
    }

    // Set up the window
    private void setUpWindow(Stage primaryStage) {
        primaryStage.setTitle("Random choose student name");
        root = new BorderPane();
        btn_generate = new Button("Generate");
        btn_generate.setMinWidth(80);
        btn_exit = new Button("Exit");
        btn_exit.setMinWidth(80);
        txt_display = new Text("Please click generate to get\n a random student name");
        hBox_message = new HBox(10, txt_display);
        hBox_message.setAlignment(Pos.CENTER);
        hBox_button = new HBox(20, btn_generate,btn_exit);
        hBox_button.setAlignment(Pos.CENTER);
        vBox_display = new VBox(40, hBox_message,hBox_button);
        vBox_display.setAlignment(Pos.CENTER);
        root.setCenter(vBox_display);
        scene = new Scene(root, 400,220);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Set up click action for buttons
    private void buttonClick(final Stage primaryStage) {
        btn_generate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (student.flag) {
                    btn_generate.setDisable(true);
                    message = "All people in the class has been selected";
                } else {
                    generateName();
                }
                txt_display.setText(message);
            }
        });

        btn_exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                primaryStage.close();
            }
        });
    }
}

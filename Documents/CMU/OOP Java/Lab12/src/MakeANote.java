import java.util.ArrayList;
import java.util.Scanner;
import NotePackage.*;

import static NotePackage.NoteCollection.createNote;
import static NotePackage.NoteDisplayer.*;

// Driver for Lab9
public class MakeANote {

    // Keyboard scanner for user input
    private static Scanner keyboard = new Scanner(System.in);

    // String values for the main menu - title first
    private static final String[] mainMenu = {"Main Menu", "Create a new Note",
            "Display existing Note(s)", "Quit"};

    // String  values for the create sub-menu - title first
    private static final String[] createMenu = {"Note Creation", "Create a Memo",
            "Create a Timed Memo", "Create a Polite Memo", "Return to previous menu"};

    // String values for the display sub-menu - title first
    private static final String[] displayMenu = {"Display Options", "Display all Notes",
            "Display Note by Number", "Display Notes by Name", "Sort by Name", "Sort by Number", "Sort by Size", "Return to previous menu"};

    // getMenuChoice(String[] menu)
    //      Displays menu[]
    //      Prompts the user for a choice
    //      Returns choice without error checking
    private static int getMenuChoice(String[] menu) {
        int choice;
        System.out.println(menu[0]);
        // Display the menu, whatever its size is
        for (int i = 1; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
        System.out.print("Enter your choice: ");
        choice = Integer.parseInt(keyboard.nextLine());
        return choice;
    }

    // main()
    //      Display the main menu, get a choice
    //      Create: get information for a new Memo
    //      Submenus: tbd
    public static void main(String[] args) {

        // Create an empty NoteCollection
        NoteCollection noteCollection = new NoteCollection();
        // Menu choice
        int choice;
        // Memo values
        String name = null, body = null, from = null, to = null;
        // Note object to be new-ed up below
        Note note = null;
        // Submenu choice
        int subchoice;

        // Loop until the user chooses quit
        do {

            // Get the main menu choice
            choice = getMenuChoice(mainMenu);

            // What did they choose?
            switch (choice) {

                // Create a new Note
                case 1:
                    
                    // Loop until the user chooses quit
                    do {

                        // Get the create submenu choice
                        subchoice = getMenuChoice(createMenu);

                        // For now, create only a Memo
                        if (subchoice == 1 || subchoice == 2 || subchoice == 3) {
                            System.out.print("Enter memo name: ");
                            name = keyboard.nextLine();
                            System.out.print("Enter memo body: ");
                            body = keyboard.nextLine();
                            System.out.print("Enter who this is from: ");
                            from = keyboard.nextLine();
                            System.out.print("Enter who this is to: ");
                            to = keyboard.nextLine();
                        }
                        switch (subchoice) {
                            // Create a Memo
                            case 1:
                                // Your code here: create a Memo object and add
                                // it to the Notecollection
                                note = createNote("normal", name, body, from, to);
                                NoteDisplayer.displayNote(note);
                                break;

                            // Create a TimedMemo
                            // TBD in part 2
                            case 2:
                                note = createNote("timed", name, body, from, to);
                                NoteDisplayer.displayNote(note);
                                break;

                            // Create a PoliteTimedMemo
                            // TBD in part 2
                            case 3:
                                note = createNote("polite", name, body, from, to);
                                NoteDisplayer.displayNote(note);
                                break;

                            // Quit
                            case 4:
                                break;
                        }
                    } while (subchoice != 4);

                // Display Notes
                case 2:
                    // Loop until the user chooses quit
                    do {

                        // Get the display submenu choice
                        subchoice = getMenuChoice(displayMenu);
                        switch (subchoice) {

                            // Display all notes
                            case 1:
                                // Your code here
                                for(Note i:noteCollection.getAllNotes())
                                    NoteDisplayer.displayNoteFancy(i);
                                break;

                            // Display a note by number
                            case 2:
                                System.out.println("Enter the note number: ");
                                Note foundNote = noteCollection.getNoteByNumber(Integer.parseInt(keyboard.nextLine()));
                                if (foundNote == null)
                                    NoteDisplayer.displayErrorMessage("None found");
                                else
                                    NoteDisplayer.displayNote(foundNote);
                                break;

                            // Display a note by name
                            case 3:
                                System.out.println("Enter name of note: ");
                                ArrayList<Note> foundList = noteCollection.getNoteByName(keyboard.nextLine());
                                if (foundList.isEmpty())
                                    NoteDisplayer.displayErrorMessage("None found");
                                else
                                    for (Note i:foundList)
                                        NoteDisplayer.displayNote(i);
                                break;

                            // Sorts note list by name
                            case 4:
                                noteCollection.sortByName();
                                System.out.println("Notes are sorted by name");
                                break;

                            // Sorts note list by number
                            case 5:
                                noteCollection.sortByNumber();
                                System.out.println("Notes are sorted by number");
                                break;

                            // Sorts note list by number
                            case 6:
                                noteCollection.sortBySize();
                                System.out.println("Notes are sorted by note size");
                                break;

                            // Quit
                            case 7:
                                System.out.println("Returning to main menu");
                                break;
                        }
                    } while (subchoice != 7);
            }

        } while (choice != 3);
    }
}

package NotePackage;

public class NoteDisplayer {

    public static void displayNote(Note note)   {
        System.out.println(note);
    }

    public static void displayNoteFancy(Note note) {
        String date = "";
        if (note instanceof TimedMemo) {
            TimedMemo timedNote = (TimedMemo) note;
            date = "* Date: " + timedNote.getToday() + "\n";
        }

        String number = "Number: " + note.getNoteNumber();
        String name = "Name: " + note.name;
        String from = "From: " + note.getFrom();
        String to = "To: " + note.getTo();
        String body = "Body:\n" + note.getBody();

        // Determine max length for the upper and lower borders based on content width
        int maxLength = Math.max(Math.max(number.length(), name.length()), Math.max(from.length(), to.length()));

        // Border Creation
        String border = "*".repeat(maxLength + 4);

        System.out.println(border);
        System.out.printf("* %-"+ (maxLength) +"s *\n", number);
        System.out.printf("* %-"+ (maxLength) +"s *\n", name);
        System.out.printf("* %-"+ (maxLength) +"s *\n", from);
        System.out.printf("* %-"+ (maxLength) +"s *\n", to);
        System.out.println(border);
        System.out.print(date);
        System.out.println("*\n" + body);
    }


    public static void displayErrorMessage(String errorMessage)   {
        System.err.println("Error: " + errorMessage);
    }
}

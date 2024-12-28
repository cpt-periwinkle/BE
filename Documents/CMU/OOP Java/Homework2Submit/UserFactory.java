package UserPackage;

public class UserFactory {
    // Factory method to create a user based on the userType
    public static User createUser(int id, String userType, String name, String other) {
        User user = null;

        switch (userType.toUpperCase()) {
            case "ADMINISTRATOR":
                user = new Administrator(id, name, other);  // Department is passed as 'other'
                break;
            case "OFFICER":
                // Parse the rank from the string and create an Officer
                try {
                    Officer.Rank rank = Officer.Rank.valueOf(other.toUpperCase());
                    user = new Officer(id, name, rank);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid rank: " + other);
                }
                break;
            case "COURTOFFICIAL":
                user = new CourtOfficial(id, name, other);  // Title is passed as 'other'
                break;
            default:
                System.err.println("Invalid user type: " + userType);
        }

        return user;
    }
}

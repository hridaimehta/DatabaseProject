import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ProjectPart2 {

    // Global variables
    private static boolean isQuit = false;
    private static boolean isValidInput = true;
    private static boolean inCategories = true;
    private static boolean insideCategory = false;
    private static boolean inQuery = false;
    private static int inputInt = -1;
    private static String line = "";
    private static String[] tokens = null;

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String line = "";
        while (!(line.equals("y") || line.equals("n"))) {
            System.out.println("\nDo you want to populate the database again or want to use already populated database?");
            System.out.print("Press 'y' to populate the database again and 'n' to use already populated database:");
            line = console.nextLine();
            if (!(line.equals("y") || line.equals("n"))) {
                System.out.println("\nInvalid input, choose again!");
            }
        }

        MyDatabase db = null;

        if (line.equals("y")) {
            db = new MyDatabase("project.sql", "authUnpopulated.cfg");
        } else {
            db = new MyDatabase("project.sql", "authPopulated.cfg");
        }

        runConsole(db);

        System.out.println("\n------------------------------------------------");
        System.out.println("Exiting...");
        System.out.println("------------------------------------------------");
        
        db.shutdown();
    }

    public static void runConsole(MyDatabase db) {
        Scanner console = new Scanner(System.in);
        System.out.println("\n------------------------------------------------");
        System.out.println("Welcome!");
        System.out.println("------------------------------------------------");
        printCategories();
        line = console.nextLine();
        String result = "";

        int category = -1;
        int queryNum = -1;

        while (line != null && !isQuit) {
            tokens = line.split("\\s+");
            if (inCategories) {
                isValidInt();
                if (isValidInput) {
                    if (inputInt < 1 || inputInt > 7) {
                        isValidInput = false;
                    }
                }
                if (isValidInput) {
                    category = inputInt;
                    if (category == 7) {
                        isQuit = true;
                    }
                    goToInsideCategory(category);
                }
            } else if (insideCategory) {
                isValidInt();
                if (isValidInput) {
                    inQuery = true;
                    insideCategory = false;
                    queryNum = inputInt;

                    // Category 1: Users -------------------------------------------------------------
                    if (category == 1) {
                        if (queryNum == 1) {
                            result = db.getAllUsers();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 2) {
                            result = db.usersAreNotChannelOwners();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 3) {
                            goToCategories();
                        } else if (queryNum > 3 || queryNum < 1) {
                            isValidInput = false;
                        }
                    }

                    // Category 2: Videos -------------------------------------------------------------
                    else if (category == 2) {
                        if (queryNum == 1) {
                            result = db.getAllVideos();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 2) {
                            System.out.print("Enter the VideoId: ");
                        } else if (queryNum == 3) {
                            System.out.print("Enter the VideoId: ");
                        } else if (queryNum == 4) {
                            result = db.likeDislikeRatio();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 5) {
                            result = db.videoWithMinLikes();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 6) {
                            System.out.print("Enter the VideoTitle: ");
                        } else if (queryNum == 7) {
                            result = db.relationshipBetweenLikesAndAdRevenue();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 8) {
                            goToCategories();
                        } else if (queryNum > 8 || queryNum < 1) {
                            isValidInput = false;
                        }
                    }
                    // Category 3: Channels -------------------------------------------------------------
                    else if (category == 3) {
                        if (queryNum == 1) {
                            result = db.getAllChannels();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 2) {
                            System.out.print("Enter the ChannelId: ");
                        } else if (queryNum == 3) {
                            System.out.print("Enter the ChannelId: ");
                        } else if (queryNum == 4) {
                            result = db.adRevenueForEachChannel();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 5) {
                            result = db.channelWithNoVideos();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 6) {
                            result = db.mostRevenueChannelType();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 7) {
                            goToCategories();
                        } else if (queryNum > 7 || queryNum < 1) {
                            isValidInput = false;
                        }
                    }
                    // Category 4: Advertisements -------------------------------------------------------------
                    else if (category == 4) {
                        if (queryNum == 1) {
                            result = db.getAllAds();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 2) {
                            result = db.getMostAdShown();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 3) {
                            goToCategories();
                        } else if (queryNum > 3 || queryNum < 1) {
                            isValidInput = false;
                        }
                    }
                    // Category 5: Companies -------------------------------------------------------------
                    else if (category == 5) {
                        if (queryNum == 1) {
                            result = db.getAllComapanies();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 2) {
                            result = db.adRevenueFromAllCompType();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 3) {
                            goToCategories();
                        } else if (queryNum > 3 || queryNum < 1) {
                            isValidInput = false;
                        }
                    }
                    // Category 6: Countries -------------------------------------------------------------
                    else if (category == 6) {
                        if (queryNum == 1) {
                            result = db.getAllCountryName();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 2) {
                            result = db.viewersInEachCountry();
                            checkResult(result);
                            goToInsideCategory(category);
                        } else if (queryNum == 3) {
                            goToCategories();
                        } else if (queryNum > 3 || queryNum < 1) {
                            isValidInput = false;
                        }
                    }
                }
            } else if (inQuery) {
                // Category 1: Users -------------------------------------------------------------
                if (category == 1) {

                }
                // Category 2: Videos -------------------------------------------------------------
                else if (category == 2) {
                    if (queryNum == 2) {
                        isValidInt();
                        if (isValidInput) {
                            result = db.findChannelId(inputInt);
                            checkResult(result);
                            goToInsideCategory(category);
                        }
                    } else if (queryNum == 3) {
                        isValidInt();
                        if (isValidInput) {
                            result = db.numberOfLikesOnVideo(inputInt);
                            checkResult(result);
                            goToInsideCategory(category);
                        }
                    } else if (queryNum == 6) {
                        if (isValidInput) {
                            result = db.searchKeyWordForVideoTitle(line);
                            checkResult(result);
                            goToInsideCategory(category);
                        }
                    }
                }
                // Category 3: Channels -------------------------------------------------------------
                else if (category == 3) {
                    if (queryNum == 2) {
                        isValidInt();
                        if (isValidInput) {
                            result = db.allVideoByChannel(inputInt);
                            checkResult(result);
                            goToInsideCategory(category);
                        }
                    } else if (queryNum == 3) {
                        isValidInt();
                        if (isValidInput) {
                            result = db.totalLikesOnChannel(inputInt);
                            checkResult(result);
                            goToInsideCategory(category);
                        }
                    }
                }
                // Category 4: Advertisements -------------------------------------------------------------
                else if (category == 4) {

                }
                // Category 5: Companies -------------------------------------------------------------
                else if (category == 5) {

                }
                // Category 6: Countries -------------------------------------------------------------
                else if (category == 6) {

                }
            }

            if (!isValidInput) {
                System.out.println("\nInvalid Input!");
                if (inCategories) {
                    printCategories();
                } else if (insideCategory || inQuery) {
                    printQueries(category);
                    insideCategory = true;
                }
            }

            if (!isQuit) {
                line = console.nextLine();
                isValidInput = true;
            }
        }

        console.close();

    } // runConsole

    private static void goToCategories() {
        printCategories();
        inCategories = true;
        insideCategory = false;
        inQuery = false;
    }

    private static void goToInsideCategory(int category) {
        printQueries(category);
        insideCategory = true;
        inQuery = false;
        inCategories = false;
    }

    private static void isValidInt() {
        if (tokens.length != 1) {
            isValidInput = false;
        } else {
            try {
                inputInt = Integer.parseInt(tokens[0]);
            } catch (NumberFormatException nfe) {
                isValidInput = false;
            }
        }
    }

    private static void checkResult(String result) {
        if (result.equals("")) {
            printEmptyMessage();
        } else {
            System.out.println("\nResult:");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print(result);
            System.out.println("---------------------------------------------------------------------------");
        }
    }

    private static void printEmptyMessage() {
        System.out.println("\nEmpty Result!");
    }

    private static void printCategories() {
        System.out.println("\nSelect one of the following Categories:");
        System.out.println("1) Users [Contains 2 Queries]");
        System.out.println("2) Videos [Contains 7 Queries]");
        System.out.println("3) Channels [Contains 6 Queries]");
        System.out.println("4) Advertisements [Contains 2 Queries]");
        System.out.println("5) Companies [Contains 2 Queries]");
        System.out.println("6) Countries [Contains 2 Queries]");
        System.out.println("7) To Quit the Program");
        System.out.print("Enter Number: ");
    }

    private static void printQueries(int category) {
        if (category == 1) {
            printUsersQueries();
        } else if (category == 2) {
            printVideosQueries();
        } else if (category == 3) {
            printChannelsQueries();
        } else if (category == 4) {
            printAdvertisementsQueries();
        } else if (category == 5) {
            printCompaniesQueries();
        } else if (category == 6) {
            printCountriesQueries();
        }
    }

    // Category 1
    private static void printUsersQueries() {
        System.out.println("\nSelect one of the following Queries for Users:");
        System.out.println("1) Get all the users.");
        System.out.println("2) Get users that don't own a channel.");
        System.out.println("3) To go back to Categories.");
        System.out.print("Enter Number: ");
    }

    // Category 2
    private static void printVideosQueries() {
        System.out.println("\nSelect one of the following Queries for Videos:");
        System.out.println("1) Get all the videos.");
        System.out.println("2) Get the ChannelId given a VideoId.");
        System.out.println("3) Get number of likes given a VideoId.");
        System.out.println("4) Like/Dislike Ratio for all videos.");
        System.out.println("5) Video with minimum likes.");
        System.out.println("6) Search for the given VideoTitle.");
        System.out.println("7) To find the correlation between number of likes and Ad revenue earned per video.");
        System.out.println("8) To go back to Categories.");
        System.out.print("Enter Number: ");
    }

    // Category 3
    private static void printChannelsQueries() {
        System.out.println("\nSelect one of the following Queries for Channels:");
        System.out.println("1) Get all the channels.");
        System.out.println("2) Get All Videos created by a given ChannelId.");
        System.out.println("3) Get Total number of likes of the given ChannelId.");
        System.out.println("4) Get Ad revenue for each channel.");
        System.out.println("5) Get channels with no videos.");
        System.out.println("6) Ad revenue earned by each channel type in descending order.");
        System.out.println("7) To go back to Categories.");
        System.out.print("Enter Number: ");
    }

    // Category 4
    private static void printAdvertisementsQueries() {
        System.out.println("\nSelect one of the following Queries for Advertisements:");
        System.out.println("1) Get AdvertId, AdvertMoney, Duration for each Advertisement.");
        System.out.println("2) Get AdvertId, Number of times shown for the top 5 most showed ads.");
        System.out.println("3) To go back to Categories.");
        System.out.print("Enter Number: ");
    }

    // Category 5
    private static void printCompaniesQueries() {
        System.out.println("\nSelect one of the following Queries for Companies:");
        System.out.println("1) Get all the companies.");
        System.out.println("2) Get Ad Revenue from all company types.");
        System.out.println("3) To go back to Categories.");
        System.out.print("Enter Number: ");
    }

    // Category 6
    private static void printCountriesQueries() {
        System.out.println("\nSelect one of the following Queries for Countries:");
        System.out.println("1) Get all the countries.");
        System.out.println("2) Get number of viewers in each country.");
        System.out.println("3) To go back to Categories.");
        System.out.print("Enter Number: ");
    }

}//ProjectPart2

/*
Database class
 */
class MyDatabase {
    private Connection connection;

    public MyDatabase(String initScript, String conf) {
        Properties prop = new Properties();
        String fileName = conf;
        try {
            FileInputStream configFile = new FileInputStream(fileName);
            prop.load(configFile);
            configFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find config file.");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error reading config file.");
            System.exit(1);
        }
        String username = (prop.getProperty("username"));
        String password = (prop.getProperty("password"));

        if (username == null || password == null) {
            System.out.println("Username or password not provided.");
            System.exit(1);
        }

        String connectionUrl =
                "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;"
                        + "database=cs3380;"
                        + "user=" + username + ";"
                        + "password=" + password + ";"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        if (conf.equals("authPopulated.cfg")) {
            try {
                // create a connection to the database
                connection = DriverManager.getConnection(connectionUrl);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
                System.exit(1);
            }
        } else {
            try {
                // create a connection to the database
                connection = DriverManager.getConnection(connectionUrl);
                if (initScript != null)
                    this.loadData(initScript);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
                System.exit(1);
            } catch (IOException fnf) {
                System.out.println(fnf.getMessage());
                System.exit(2);
            }
        }
    }//Mydatabase method

    public void loadData(String script) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(script));
        String line = reader.readLine();
        // assumes each query is its own line
        while (line != null) {
            System.out.println(line);
            this.connection.createStatement().execute(line);
            line = reader.readLine();
        }
    }

    public void shutdown() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Queries ------------------------------------------------------------------------------------------

    // Category 1: Users -------------------------------------------------------------

    // Query 13 : C1Q1
    public String getAllUsers() {

        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select UserCreatedInCountry.UserId, UserCreatedInCountry.UserName from " +
                    "UserCreatedInCountry ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next() && resultSet.getString("UserName") != null) {
                retString += resultSet.getInt("userID") + " " + resultSet.getString("UserName") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//getAllUsers

    // Query 7 : C1Q2
    public String usersAreNotChannelOwners() {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select UserCreatedInCountry.UserId, UserCreatedInCountry.UserName from " +
                    "UserCreatedInCountry " +
                    "left join UserCreatesChannel on UserCreatesChannel.UserId = UserCreatedInCountry.UserId " +
                    "WHERE ChannelId is NULL;";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                output += resultSet.getInt("userID") + " " + resultSet.getString("UserName") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    // Category 2: Videos -------------------------------------------------------------

    // Query 15 : C2Q1
    public String getAllVideos() {

        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select videoId, VideoTitle from " +
                    "ChannelCreatesVideos ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                retString += resultSet.getInt("VideoId") + " " + resultSet.getString("VideoTitle") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//getAllVideos

    // Query 4 : C2Q2
    public String findChannelId(int videoId) {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select ChannelId FROM " +
                    "ChannelCreatesVideos " +
                    "where VideoId = " + videoId + ";";
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                output += "Channel ID = " + resultSet.getInt(1) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    // Query 5 : C2Q3
    public String numberOfLikesOnVideo(int newVideoId) {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select ChannelCreatesVideos.videoId, count(VideosLikedBy.userId)as numLikes  from " +
                    "ChannelCreatesVideos " +
                    "left join VideosLikedBy on ChannelCreatesVideos.VideoId = VideosLikedBy.VideoId " +
                    "group by ChannelCreatesVideos.VideoId " +
                    "having ChannelCreatesVideos.videoId=" + newVideoId + ";";
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                output += "Number of likes on VideoID " + newVideoId + " is " + resultSet.getInt(2) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    // Query 7 : C2Q4
    public String likeDislikeRatio() {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "with " +
                    "  NumOfLikes " +
                    "  as " +
                    "  ( " +
                    "    select ChannelCreatesVideos.VideoId,  count(VideosLikedBy.UserId)/1.0 as NumLikes " +
                    "    from ChannelCreatesVideos " +
                    "      left join VideosLikedBy on ChannelCreatesVideos.VideoId = VideosLikedBy.VideoId " +
                    "    group by ChannelCreatesVideos.VideoId " +
                    "  ), " +
                    "  NumOfDislikes " +
                    "  as " +
                    "  ( " +
                    "    select ChannelCreatesVideos.VideoId,  count(VideosDislikedBy.UserId)/1.0 as NumDisLikes " +
                    "    from ChannelCreatesVideos " +
                    "      left join VideosDislikedBy on ChannelCreatesVideos.VideoId = VideosDislikedBy.VideoId " +
                    "    group by ChannelCreatesVideos.VideoId " +
                    "  ) " +
                    "select ChannelCreatesVideos.VideoId, ChannelCreatesVideos.VideoTitle, Round((NumOfLikes.NumLikes/NumOfDislikes.NumDisLikes), 2) as LikeDislikeRatio " +
                    "from ChannelCreatesVideos " +
                    "join NumOfLikes on ChannelCreatesVideos.VideoId = NumOfLikes.VideoId " +
                    "join NumOfDislikes on ChannelCreatesVideos.VideoId = NumOfDislikes.VideoId " +
                    ";";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                output += resultSet.getInt("VideoId") + " " + resultSet.getString("VideoTitle") + " " + resultSet.getFloat("LikeDislikeRatio") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    // Query 11 : C2Q5
    public String videoWithMinLikes() {
        String output = "";
        try {

            // Create and execute a SELECT SQL statement.
            String selectSql = "select top 1 VideosLikedBy.VideoId, newVideoTitle = CAST(videoTitle AS varchar(max)), count(VideosLikedBy.UserId) as numLikes " +
                    "from " +
                    "VideosLikedBy " +
                    "JOIN ChannelCreatesVideos on ChannelCreatesVideos.VideoId = VideosLikedBy.VideoId " +
                    "group by VideosLikedBy.VideoId, CAST(videoTitle AS varchar(max)) " +
                    "having count(VideosLikedBy.UserId) != 0 " +
                    "order by numLikes " +
                    ";";
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                output += "Video Id: " + resultSet.getInt("VideoId") + " Video Title: " + resultSet.getString("newVideoTitle") + ", with likes=" + resultSet.getString("numLikes") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }// videoWithMinLikes

    // Query 12 : C2Q6
    public String searchKeyWordForVideoTitle(String name) {
        String output = "";
        try {
            String sql = "SELECT VideoID, videoTitle from ChannelCreatesVideos where videoTitle like" + " '%" + name + "%';";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = null;
            resultSet = statement.executeQuery();


            while (resultSet.next()) {


                output += resultSet.getInt("VideoId") + " " + resultSet.getString("VideoTitle") + " "
                        + "\n";

            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return output;
    }//searchKeyWord

    // Query 21 : C2Q7
    public String relationshipBetweenLikesAndAdRevenue() {

        String retString = "";

        try {

            // Create and execute a SELECT SQL statement.
            String selectSql = "Select top 10 VideosLikedBy.VideoId, count(VideosLikedBy.userId) as numLikes, AdvertPayedByCompany.AdvertMoney " +
                    "from VideosLikedBy " +
                    "left join VideosShowAdvert on VideosShowAdvert.videoId = VideosLikedBy.videoId " +
                    "left join AdvertPayedByCompany on AdvertPayedByCompany.AdvertId = VideosShowAdvert.AdvertId " +
                    "group by VideosLikedBy.VideoId, AdvertPayedByCompany.AdvertMoney " +
                    "order by numLikes desc;";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            retString += "10 most liked video\nVideoId, numLikes, AdvertMoney\n";
            while (resultSet.next()) {
                retString += "    " + resultSet.getInt("VideoId") + ", " + resultSet.getInt("numLikes") + ", " + resultSet.getInt("AdvertMoney") + "\n";
            }

            // Create and execute a SELECT SQL statement.
            selectSql = "Select top 10 VideosLikedBy.VideoId, count(VideosLikedBy.userId) as numLikes, AdvertPayedByCompany.AdvertMoney " +
                    "from VideosLikedBy " +
                    "left join VideosShowAdvert on VideosShowAdvert.videoId = VideosLikedBy.videoId " +
                    "left join AdvertPayedByCompany on AdvertPayedByCompany.AdvertId = VideosShowAdvert.AdvertId " +
                    "group by VideosLikedBy.VideoId, AdvertPayedByCompany.AdvertMoney " +
                    "order by numLikes;"
            ;

            resultSet = null;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            retString += "\n\n10 Least liked Videos\nVideoId, numLikes, AdvertMoney\n";

            while (resultSet.next()) {
                retString += "    " + resultSet.getInt("VideoId") + ", " + resultSet.getInt("numLikes") + ", " + resultSet.getInt("AdvertMoney") + "\n";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//relationshipBetweenLikesAndAdRevenue

    // Category 3: Channels -------------------------------------------------------------

    // Query 14 : C3Q1
    public String getAllChannels() {
        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "Select UserCreatesChannel.channelID, UserCreatesChannel.ChannelName from UserCreatesChannel; ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                retString += resultSet.getInt("ChannelId") + " " + resultSet.getString("ChannelName") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//getAllChannels

    // Query 2 : C3Q2
    public String allVideoByChannel(int channelID) {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select ChannelId, VideoId , VideoTitle " +
                    "from ChannelCreatesVideos " +
                    "where ChannelId =" + channelID + ";";
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            //System.out.println("All the videos from channel ID "+channelID+" are as follows");
            while (resultSet.next()) {
                output += "Video ID - " + resultSet.getInt("VideoId") + " Video Title: " + resultSet.getString("VideoTitle") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }//allVideoByChannel

    // Query 3 : C3Q3
    public String totalLikesOnChannel(int channelID) {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select ChannelCreatesVideos.ChannelId, count(VideosLikedBy.userId)as numLikes  from " +
                    "ChannelCreatesVideos " +
                    "left join VideosLikedBy on ChannelCreatesVideos.VideoId = VideosLikedBy.VideoId " +
                    "group by ChannelCreatesVideos.ChannelId " +
                    "having ChannelCreatesVideos.ChannelId=" + channelID + ";";
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                output += resultSet.getInt("numLikes") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }//totalLikesOnChannel

    // Query 8 : C3Q4
    public String adRevenueForEachChannel() {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select UserCreatesChannel.ChannelId, newUserCreatesChannel = CAST(UserCreatesChannel.ChannelName AS varchar(max)), sum(AdvertPayedByCompany.AdvertMoney) as AdRevenue " +
                    "from UserCreatesChannel " +
                    "  left join ChannelCreatesVideos on UserCreatesChannel.ChannelId = ChannelCreatesVideos.ChannelId " +
                    "  left join VideosShowAdvert on ChannelCreatesVideos.VideoId = VideosShowAdvert.VideoId " +
                    "  left join AdvertPayedByCompany on VideosShowAdvert.AdvertId = AdvertPayedByCompany.AdvertId " +
                    "group by UserCreatesChannel.ChannelId, CAST(UserCreatesChannel.ChannelName AS varchar(max));";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                output += resultSet.getInt("ChannelId") + " " + resultSet.getString("newUserCreatesChannel") + " " + resultSet.getInt("AdRevenue") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    // Query 10 : C3Q5
    public String channelWithNoVideos() {
        String output = "";
        try {

            // Create and execute a SELECT SQL statement.
            String selectSql = "select UserCreatesChannel.ChannelId, UserCreatesChannel.ChannelName " +
                    "from " +
                    "UserCreatesChannel " +
                    "left join ChannelCreatesVideos on ChannelCreatesVideos.ChannelId = UserCreatesChannel.ChannelId " +
                    "WHERE UserCreatesChannel.ChannelId not in( " +
                    " Select DISTINCT ChannelId " +
                    " from ChannelCreatesVideos " +
                    ");";
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                output += "Channel Id: " + resultSet.getInt("ChannelId") + " name: " + resultSet.getString("ChannelName") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }//channelWithNoVideos

    // Query 18 : C3Q6
    public String mostRevenueChannelType() {

        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT UserCreatesChannel.ChannelId, newChannelName = CAST(UserCreatesChannel.ChannelName as varchar(max)), sum(AdvertPayedByCompany.AdvertMoney) as revenue\n" +
                    "from UserCreatesChannel " +
                    "LEFT JOIN ChannelCreatesVideos on ChannelCreatesVideos.ChannelId = UserCreatesChannel.ChannelId " +
                    "LEFT JOIN VideosShowAdvert on VideosShowAdvert.VideoId = ChannelCreatesVideos.VideoId " +
                    "LEFT JOIN AdvertPayedByCompany on AdvertPayedByCompany.AdvertId = VideosShowAdvert.AdvertId " +
                    "GROUP by UserCreatesChannel.ChannelId, CAST(UserCreatesChannel.ChannelName as varchar(max)) " +
                    "ORDER by revenue DESC;";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                retString += resultSet.getInt("ChannelId") + " - " + resultSet.getString("newChannelName") + " $" + resultSet.getInt("revenue") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//mostRevenueChannelType

    // Category 4: Advertisements -------------------------------------------------------------

    // Query 19 : C4Q1
    public String getAllAds() {

        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "Select AdvertId, AdvertMoney, Duration from AdvertPayedByCompany ; ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                retString += resultSet.getInt("AdvertId") + ", $" + resultSet.getInt("AdvertMoney") + ", " + resultSet.getInt("Duration") + " seconds \n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//getALlAdsInfo

    // Query 20 : C4Q2
    public String getMostAdShown() {

        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "Select top 5 AdvertId, count(AdvertId) as NumberOfTimesShown " +
                    "from VideosShowAdvert " +
                    "group by AdvertId " +
                    "order by NumberOfTimesShown desc ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                retString += resultSet.getInt("AdvertId") + ", " + resultSet.getInt("NumberOfTimesShown") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//getMostAdsShown

    // Category 5: Companies -------------------------------------------------------------

    // Query 16 : C5Q1
    public String getAllComapanies() {
        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "Select CompanyId, CompanyName from Company ; ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                retString += resultSet.getInt("CompanyId") + " " + resultSet.getString("CompanyName") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//getAllCompanies

    // Query 17 : C5Q2
    public String adRevenueFromAllCompType() {

        String retString = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select newCompanyType = CAST(CompanyType as varchar(max)), sum(AdvertPayedByCompany.AdvertMoney) as MoneySpentonAds " +
                    "from Company " +
                    "\tleft join AdvertPayedByCompany on Company.CompanyId = AdvertPayedByCompany.CompanyId " +
                    "\tleft join VideosShowAdvert on AdvertPayedByCompany.AdvertId = VideosShowAdvert.AdvertId " +
                    "group by CAST(CompanyType as varchar(max)) " +
                    "; ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                retString += resultSet.getString("newCompanyType") + " $" + resultSet.getInt("MoneySpentonAds") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retString;
    }//adRevenue

    // Category 6: Countries -------------------------------------------------------------

    // Query 1 : C1Q1
    public String getAllCountryName() {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "Select CountryId, CountryName from Country";
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                output += "Country Id-" + resultSet.getInt(1) + " Name- " + resultSet.getString(2) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }//getAllCountryName

    // Query 9 : C6Q2
    public String viewersInEachCountry() {
        String output = "";
        try {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select Country.CountryId, newCountryName = CAST(Country.CountryName AS varchar(max)), count(UserCreatedInCountry.UserId) as noOfUsers " +
                    "from Country " +
                    "  left join UserCreatedInCountry on Country.CountryId = UserCreatedInCountry.CountryId " +
                    "  left join VideosViewedBy on UserCreatedInCountry.UserId = VideosViewedBy.UserId " +
                    "group by Country.CountryId, CAST(Country.CountryName AS varchar(max)); ";

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                output += resultSet.getInt("CountryId") + " " + resultSet.getString("newCountryName") + " " + resultSet.getInt("noOfUsers") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
}//MyDatabase class


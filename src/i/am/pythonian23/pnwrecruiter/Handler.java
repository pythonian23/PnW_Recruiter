package i.am.pythonian23.pnwrecruiter;

import io.github.adorableskullmaster.pw4j.*;
import io.github.adorableskullmaster.pw4j.domains.subdomains.SNationContainer;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Handler {
    private static PoliticsAndWar pnw;
    private static int latest_send;
    private static String key;
    private static Sender sender;
    private static String[] titles;
    private static String body;

    public static void init() {
        readSavedData();
        getTitles();
        getBody();
        pnw = new PoliticsAndWarBuilder().setApiKey(key).build();
        sender = new Sender(key, "242692", titles[new Random().nextInt(titles.length)], body);
    }

    private static void readSavedData() { ;
        File file = new File(System.getProperty("user.dir") + "/savedData.txt");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder contents = new StringBuilder();
            while (scanner.hasNextLine()) {
                contents.append(scanner.nextLine());
                contents.append("\n");
            }
            String[] temp = contents.toString().split("\n");
            key = temp[0];
            latest_send = Integer.parseInt(temp[1]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void saveSavedData() {
        File file = new File(System.getProperty("user.dir") + "/savedData.txt");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(key);
            out.newLine();
            out.write(Integer.toString(latest_send));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getTitles() {
        File file = new File(System.getProperty("user.dir") + "/title.txt");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder contents = new StringBuilder();
            while (scanner.hasNextLine()) {
                contents.append(scanner.nextLine());
                contents.append("\n");
            }
            titles = contents.toString().split("\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            titles = new String[] {"Join!"};
        }
    }

    private static void getBody() {
        File file = new File(System.getProperty("user.dir") + "/message.html");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder contents = new StringBuilder();
            while (scanner.hasNextLine()) {
                contents.append(scanner.nextLine());
                contents.append("\n");
            }
            body = contents.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            body = "Join today!";
        }
    }

    public static void getNewestNations() {
        List<SNationContainer> new_nations = pnw.getNationsByAlliance(false, 0).getNationsContainer();
        int max = latest_send;
        int count = 0;
        for (SNationContainer nation: new_nations) {
            int id = nation.getNationId();
            if (id > latest_send) {
                if (id > max) {
                    max = id;
                }
                sender.newDest(Integer.toString(id));
                sender.subject = titles[new Random().nextInt(titles.length)];
                count++;
                try {
                    sender.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        latest_send = max;
        saveSavedData();
        System.out.println(count + " nations messaged.");
    }
}

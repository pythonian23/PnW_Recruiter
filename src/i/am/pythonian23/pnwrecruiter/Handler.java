package i.am.pythonian23.pnwrecruiter;

import io.github.adorableskullmaster.pw4j.*;
import io.github.adorableskullmaster.pw4j.domains.subdomains.SNationContainer;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Handler {
    private static PoliticsAndWar pnw;
    private static int latest_send;
    private static String key;
    private static Sender sender;

    public static void init() {
        readSavedData();
        pnw = new PoliticsAndWarBuilder().setApiKey(key).build();
        sender = new Sender(key, "242692", "Join Atlantian Council!", "Join!\n");
    }

    private static void readSavedData() { ;
        File file = new File(System.getProperty("user.dir") + "\\savedData.txt");
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
        File file = new File(System.getProperty("user.dir") + "\\savedData.txt");
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
        System.out.println(Integer.toString(count) + " nations messaged.");
    }
}

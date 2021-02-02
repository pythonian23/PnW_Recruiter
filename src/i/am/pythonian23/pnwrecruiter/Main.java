package i.am.pythonian23.pnwrecruiter;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

        Handler.init();

        System.out.println("This is going to take a while. Please wait...");
        Handler.getNewestNations();

        System.out.println("Press Enter key to exit...");
        try {
            System.out.println(System.in.read());
        }
        catch(Exception ignored) {}
    }
}

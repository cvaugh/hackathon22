package ver1;

public class Main {
    public static void main(String[] args) {
        if(args.length > 0 && args[0].equalsIgnoreCase("cli")) {
            new Console(System.in).start();
        } else {
            // TODO show GUI
        }
    }
}

package ver1;

import java.io.InputStream;
import java.util.Scanner;

public class Console {
    private InputStream in;
    private Scanner scanner;

    public Console(InputStream in) {
        this.in = in;
    }

    public void start() {
        scanner = new Scanner(in);
        System.out.print("> ");
        while(scanner.hasNextLine()) {
            parseCommand(scanner.nextLine());
            System.out.print("\n> ");
        }
        stop();
    }

    public void stop() {
        scanner.close();
        System.exit(0);
    }

    private void parseCommand(String input) {
        String[] split = input.split(" ", 2);
        if(split.length == 0) return;
        String cmd = split[0];
        String args = split.length > 1 ? split[1] : "";

        switch(cmd) {
        case "exit" -> {
            stop();
        }
        // TODO
        default -> {
            System.out.println("Unknown command: " + cmd);
        }
        }
    }
}

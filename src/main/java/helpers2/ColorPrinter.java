package helpers2;

import org.apache.logging.log4j.Logger;

import static helpers2.ConsoleColors.*;

public class ColorPrinter {

    public static void printMessageInYellow(String message){
        System.out.println(YELLOW_BOLD + message + RESET);
    }

    public static void printColorMessage(String message, Logger logger, Level level){
        switch (level){
            case INFO:
                logger.info(GREEN + message + RESET);
                break;
            case DEBUG:
                logger.debug(BLUE + message + RESET);
                break;
            case ERROR:
                logger.error(RED + message + RESET);
                break;
        }
    }

}

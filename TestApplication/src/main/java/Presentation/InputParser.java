package Presentation;

public class InputParser {


    /**
     * Maybe it would be better to use a parser class to handle user input
     *
     */
    public InputParser() {
    }

    public void parse(String input){

        if(input.equals("q") || input.equals("quit")){
            System.exit(1);
        }


    }
}

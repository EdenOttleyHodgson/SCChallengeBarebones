import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Interpreter {
    private List<variable> theVariables;
    private BufferedReader theFileReader;

    private int increment(variable currentVar) {
        return currentVar.getValue() + 1;
    }
    private int decrement(variable currentVar) {
        return currentVar.getValue() - 1;
    }

    public Interpreter(String fileName) throws IOException {
        theVariables = new ArrayList<variable>();
        theFileReader = new BufferedReader(new FileReader(fileName));
        String currentString = "";
        int lineCount = 0;
        while (currentString != null) {
            currentString = theFileReader.readLine();
            lineCount++;

            interpretLine(currentString, lineCount);
        }

    }
    private void interpretLine(String currentString, int lineCount){

        String[] splitString = currentString.split(" ");
        if (splitString[0] == "while" ) {
            try {
                beginLoop(lineCount, splitString[1]);

            } catch (IOException e) {}
        } else {
            String command = splitString[0];
            variable currentVariable = processVariable(splitString[1]);
            switch (command) {
                case "clear":
                    currentVariable.setValue(0);
                case "decr":
                    currentVariable.setValue(decrement(currentVariable));
                case "incr":
                    currentVariable.setValue(increment(currentVariable));

            }
        }

    }
    private variable processVariable(String varIdentifier){
        for (variable var:theVariables) {
            if (varIdentifier == var.getIdentifier()) {
                return var;
            } else {
                theVariables.add(new variable(varIdentifier));
            }

        }
        return theVariables.get(theVariables.size());


    }
    private void beginLoop(int beginningLine, String loopVarIdentifier) throws IOException{
        boolean endLineFound = false;
        int currentLine = beginningLine;
        List<String> loopLines = new ArrayList<String>();

        variable loopVar = processVariable(loopVarIdentifier);
        while( endLineFound = false) {
            loopLines.add(theFileReader.readLine());
            if (theFileReader.readLine() == "end") {
                endLineFound= true;
            }
        }

        while(loopVar.getValue() != 0) {
            for (String line:loopLines) {
                currentLine++;
                interpretLine(line, currentLine);

            }
            currentLine = beginningLine;
        }

    }


}

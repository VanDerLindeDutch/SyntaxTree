import utils.SyntaxTree;
import utils.Utils;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        //"a+(4*(5+2))"
        //"(b+a)+((10*3)/2)"
        //"(10*5)* (((2+2) * 3) +(10+3))"
        //x+((10*5)*(((2+2) * 3) +(10+3)))
        if (args.length == 0) {
            System.out.println("Enter the expression in arguments\nExamples in Main class comments");
            return;
        }
        String expression = args[0];
        List<String> exprList = Utils.deleteSpaces(expression);
        SyntaxTree syntaxTree = new SyntaxTree();
        for (String value : exprList) {
            syntaxTree.insertValue(value);
        }

        syntaxTree.read();
        System.out.println("Result: " + syntaxTree.calculate());

    }


}

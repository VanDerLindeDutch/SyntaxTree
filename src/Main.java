import utils.SyntaxTree;
import utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        String expression = "(10*5)* (((2+2) * 3) +(10+3))";
//        String expression = "a+(4*(5+2))";
        String expression = "(a*(10+5))+((2+2)*3)";
        List<String> exprList = Utils.deleteSpaces(expression);
        System.out.println(exprList);
        String[] operators = new String[]{"*", "/", "+", "-"};
        SyntaxTree syntaxTree = new SyntaxTree();
        for(String value: exprList){
            syntaxTree.insertValue(value);
        }

        syntaxTree.read();
        System.out.println(syntaxTree.calculate());

    }


}

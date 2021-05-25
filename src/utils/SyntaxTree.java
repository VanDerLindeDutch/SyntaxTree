package utils;

import java.util.HashMap;

interface Operator {
    int execute(int x, int y);
}

public class SyntaxTree {

    private static final HashMap<String, Operator> OPERATORS = new HashMap<>();

    static {
        OPERATORS.put("+", Integer::sum);
        OPERATORS.put("-", (x, y) -> x - y);
        OPERATORS.put("*", (x, y) -> x * y);
        OPERATORS.put("/", (x, y) -> x / y);
    }

    private Node root;
    private Node currentNode;

    public SyntaxTree() {
        this.root = new Node();
        this.currentNode = this.root;
    }

    public void insertValue(String value) {
        try {
            Integer integer = Integer.parseInt(value);
            currentNode.value = String.valueOf(integer);
            if (currentNode.parent == null) {
                Node newParNode = new Node();
                root = newParNode;
                currentNode.parent = newParNode;
                newParNode.leftNode = currentNode;
            }
            currentNode = currentNode.parent;
        } catch (NumberFormatException numberFormatException) {
            Node newNode = new Node();

            if (value.equals("(")) {
                newNode.parent = currentNode;
                currentNode.leftNode = newNode;
                currentNode = currentNode.leftNode;
            } else if (OPERATORS.containsKey(value)) {
                if(currentNode.value!=null){
                    
                }
                currentNode.value = value;
                newNode.parent = currentNode;
                currentNode.rightNode = newNode;
                currentNode = currentNode.rightNode;
            } else if (value.equals(")")) {
                if (currentNode.parent == null) {
                    Node newParNode = new Node();
                    root = newParNode;
                    currentNode.parent = newParNode;
                    newParNode.leftNode = currentNode;
                }
                currentNode = currentNode.parent;
            }
            else{
                currentNode.value = value;
                if(currentNode.parent==null){
                    Node newParNode = new Node();
                    root = newParNode;
                    currentNode.parent = newParNode;
                    newParNode.leftNode = currentNode;
                }
                currentNode = currentNode.parent;
            }
        }

    }


    public String calculate() {
        StringBuilder stringBuilder = new StringBuilder();
        if (root.value == null) {
            root = root.leftNode;
        }
        stringBuilder.append(calculate(root, stringBuilder));
        return stringBuilder.toString();
    }


    private int calculate(Node node, StringBuilder string) {
        Node left = node.leftNode;
        Node right = node.rightNode;
        if (left != null && right != null) {
            Operator operation = OPERATORS.get(node.value);
            int res = operation.execute(calculate(left, string), calculate(right, string));
//            string.append(res);
            return res;
        } else {
            try {
                return Integer.parseInt(node.value);
            }
            catch (NumberFormatException e){
                string.append(node.value).append(" ").append(node.parent.value).append(" ");
                return 0;
            }
        }
    }

    public void read() {
        if (root.value == null) {
            root = root.leftNode;
        }
        readNode(root, 0);
    }

    private void readNode(Node node, int level) {
        if (node != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println(node.value);
        } else {
            return;
        }
        if (node.leftNode != null) {
            readNode(node.leftNode, level + 1);
        }
        if (node.rightNode != null) {
            readNode(node.rightNode, level + 1);
        }
    }


    private static class Node {
        private String value;
        private Node parent;
        private Node leftNode;
        private Node rightNode;

        public Node(String value) {
            this.value = value;
        }

        public Node(String value, Node leftNode, Node rightNode) {
            this.parent = null;
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public Node(String value, Node parent, Node leftNode, Node rightNode) {
            this.value = value;
            this.parent = parent;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public Node() {
        }

    }
}

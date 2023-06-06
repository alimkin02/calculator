package com.example.calculator_laba4;

import java.util.Stack;

public class Calculator {

    public static String compute(String expression) {
        String rpn = ExpressionToRPN(prepareExpression(expression));
        if (RPNToAnswer(rpn).equals("NaN") || RPNToAnswer(rpn).equals("Infinity")) {
            return "Ошибка";
        }
        return RPNToAnswer(rpn);
    }

    private static String prepareExpression(String expression) {
        String preparedExpression = "";
        for (int token = 0; token < expression.length(); token++) {
            char sumbol = expression.charAt(token);
            if (sumbol == '-') {
                if (token == 0)
                    preparedExpression += '0';
                else if(expression.charAt(token - 2) == '(')
                    preparedExpression += '0';
            }
            preparedExpression += sumbol;

        }

        return preparedExpression;
    }

    private static String ExpressionToRPN(String Exp) {
        String current = "";
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i = 0; i < Exp.length(); i++) {
            priority = getPriority(Exp.charAt(i));

            if (priority == 0) current += Exp.charAt(i);
            if (priority ==  1) stack.push(Exp.charAt(i));
            if (priority > 1) {
                current += " ";
                while (!stack.isEmpty()) {
                    if (getPriority(stack.peek()) >= priority) current += stack.pop();
                    else break;
                }
                stack.push(Exp.charAt(i));
            }
            if (priority == -1) {
                current += " ";
                while (getPriority(stack.peek()) != 1)
                    current += stack.pop();
                stack.pop();
            }
        }

        while (!stack.isEmpty())
            current += stack.pop();

        System.out.println(current);
        return current;



    }


    private static String RPNToAnswer(String rpn) {

        String operand = "";
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
             if (rpn.charAt(i) == ' ') continue;
             if (getPriority(rpn.charAt(i)) == 0) {
                 while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                     operand += rpn.charAt(i++);
                     if (i == rpn.length()) break;
                 }
                 stack.push(Double.parseDouble(operand));
                 operand = "";
             }

             if (getPriority(rpn.charAt(i)) > 1) {
                 double a = stack.pop(), b = stack.pop();
                 if (rpn.charAt(i) == '+') stack.push(b+a);
                 if (rpn.charAt(i) == '-') stack.push(b-a);
                 if (rpn.charAt(i) == '*') stack.push(b*a);
                 if (rpn.charAt(i) == '/') stack.push(b/a);
                 if (rpn.charAt(i) == '^') stack.push(Math.pow(b, a));
             }
        }

        return Double.toString(stack.pop());
    }

    private static int getPriority(char token) {
        if (token == '^') return 4;
        else if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;

    }
}

package com.arctest.calc.service;

import com.arctest.calc.exception.CalculationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.Stack;

@Service
@Log4j2
public class CalculationService {

    public String calculate(@NonNull final String formula) {

        final Stack<Character> operations = new Stack<>();
        final Stack<Integer> values = new Stack<>();
        final char[] chars = formula.replaceAll(" ", "").toCharArray();

        simplifyExpression(operations, values, chars);
        try {
            while (!operations.isEmpty()) {
                values.push(applyOperation(operations.pop(), values.pop(), values.pop()));
            }
            return values.pop().toString();
        } catch (final EmptyStackException exception) {
            final String message = "Formula is not correct";
            log.error(message);
            throw new CalculationException(message, exception);
        }
    }

    private void simplifyExpression(@NonNull final Stack<Character> operations,
                                    @NonNull final Stack<Integer> values,
                                    @NonNull final char[] chars) {

        for (int i = 0; i < chars.length; i++) {

            checkPrecedence(chars, i, operations, values);

            switch (chars[i]) {
                case '(':
                case '+':
                case '-':
                case '*':
                case '/':
                    operations.push(chars[i]);
                    break;
                case ')':
                    while (operations.peek() != '(')
                        values.push(applyOperation(operations.pop(), values.pop(), values.pop()));
                    operations.pop();
                    break;
                default:
                    StringBuilder sb = new StringBuilder();

                    while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
                        sb.append(chars[i++]);
                    }
                    values.push(Integer.parseInt(sb.toString()));
                    i--;
                    break;
            }
        }
    }

    private void checkPrecedence(@NonNull final char[] chars,
                                 @NonNull final int index,
                                 @NonNull final Stack<Character> operations,
                                 @NonNull final Stack<Integer> values) {

        if (chars[index] == '+' || chars[index] == '-' || chars[index] == '*' || chars[index] == '/') {

            while (!operations.empty() && hasPrecedence(chars[index], operations.peek())) {
                values.push(applyOperation(operations.pop(), values.pop(), values.pop()));
            }
        }
    }

    private boolean hasPrecedence(@NonNull final char op1,
                                  @NonNull final char op2) {

        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') ||
                (op2 != '+' && op2 != '-');
    }

    private int applyOperation(@NonNull final char operation,
                               @NonNull final int b,
                               @NonNull final int a) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Can't be divided by zero");
                }
                return a / b;
        }
        return 0;
    }
}
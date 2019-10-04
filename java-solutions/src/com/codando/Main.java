package com.codando;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Character> ClosedBrackets = new ArrayList<>();

    private static ArrayList<Character> OpenBrackets = new ArrayList<>();

    private static boolean CheckClose(char charOpen, char charClosed)
    {
        boolean isClosed = false;
        switch (charOpen)
        {
            case '{':
                isClosed = charClosed == '}';
                break;
            case '(':
                isClosed = charClosed == ')';
                break;
            case '[':
                isClosed = charClosed == ']';
                break;
        }

        return isClosed;
    }

    static String isBalanced(String s)
    {
        ClosedBrackets.add('}'); ClosedBrackets.add(']'); ClosedBrackets.add(')');
        OpenBrackets.add('{'); OpenBrackets.add('['); OpenBrackets.add('(');
        String balanceReturn = "NO";
        ArrayList<Character> openedBrackets = new ArrayList<>();
        if(s.length() == 2){
            if(CheckClose(s.charAt(0), s.charAt(1)))
                balanceReturn = "YES";
        }
        else if (s.length() % 2 == 0)
        {
            if(!ClosedBrackets.contains(s.charAt(0))) {
                for (int i = 0; i < s.length() - 1; i++) {
                    if (OpenBrackets.contains(s.charAt(i)))
                        openedBrackets.add(s.charAt(i));

                    if (openedBrackets.size() > 0) {
                        if (CheckClose(openedBrackets.get(openedBrackets.size() - 1), s.charAt(i + 1))) {
                            openedBrackets.remove(openedBrackets.size() - 1);
                        } else {
                            if (!ClosedBrackets.contains(s.charAt(i + 1))) continue;
                            balanceReturn = "NO";
                            break;
                        }
                    }
                }

                if (openedBrackets.size() == 0)
                    balanceReturn = "YES";
            }
        }
        return balanceReturn;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

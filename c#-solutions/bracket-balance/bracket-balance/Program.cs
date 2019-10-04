using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;

namespace bracket_balance
{
    class Program
    {
        private static readonly char[] ClosedBrackets = {'}', ')', ']'};

        private static readonly char[] OpenBrackets = {'{', '(', '['};
        // Complete the isBalanced function below.

        private static bool CheckClose(char charOpen, char charClosed)
        {
            bool isClosed = false;
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
        
        static string isBalanced(string s)
        {
            string balanceReturn = "NO";
            List<Char> openedBrackets = new List<char>();
            if (s.Length % 2 == 0)
            {
                for (int i = 0; i < s.Length - 1; i++)
                {
                    if(OpenBrackets.Contains(s[i]))
                        openedBrackets.Add(s[i]);
                    
                    if (openedBrackets.Count > 0)
                    {
                        if (CheckClose(openedBrackets[openedBrackets.Count - 1], s[i + 1]))
                        {
                            openedBrackets.RemoveAt(openedBrackets.Count - 1);
                        }
                        else
                        {
                            if (!ClosedBrackets.Contains(s[i + 1])) continue;
                            balanceReturn = "NO";
                            break;
                        }
                    }
                }

                if (openedBrackets.Count == 0)
                    balanceReturn = "YES";
            }
            
            return balanceReturn;
        }

        static void Main(string[] args) {
            TextWriter textWriter = new StreamWriter("test.txt", true);

            int t = Convert.ToInt32(Console.ReadLine());

            for (int tItr = 0; tItr < t; tItr++)
            {
                string s =
                    "[{}[{({})}()]][{}]([()])[]()()[{({[[({[{()}]})][{}{}{}[{}]][][(){}]][]}{}[]()(([]){}()())())([()[()]{{}[](([()]({{[{()[]()}{({}[](()()){})[]}[]]}}([[]])([()]{}))[{{}{}}[([{[{}]}])[]](()([]))][]))}([])]())}]{[]{{([[{(([((([])))]))[]}][{(()())()([{}[{}][][]][[][(())()[{[{[]}]{{{}}}(({[]}){}[(()[])({}{[][([{}])[{[[]{}]}]{{({}[[]({[{}]})]{})}()}]{{{{}}}{[][([])[{[[[]]{{}({([][][]{}())}[]{[]})([[]][{}([{()[()]}([([]{})()]){}])][][{}()])(({}){[]{}})}([{}[{([{[[]{}{}]{()}{}{}}[[(()[]{})[(([[][]](([][]){{}({{}([]){}}[]([()()[(((){}){()[{}][{}]})]]){}(){}{{(){}}()})}{{}({[[]]})})))]]]])}]])]}]]}}})])}]]])}]])}}}";

                    string result = isBalanced(s);

                textWriter.WriteLine(result);
            }

            textWriter.Flush();
            textWriter.Close();
        }
    }
}
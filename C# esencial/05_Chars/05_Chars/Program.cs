using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _05_Chars
{
    class Program
    {
        static void Main(string[] args)
        {
            //Declaración de variable tipo C#
            char c1 = 'a';
            Console.WriteLine("The value of c1 is: " + c1);

            //Uso de Unicode
            char c2 = '\u0062';
            Console.WriteLine("The value of c2 is: " + c2);

            //Declaración de variable tipo .NET
            //char symbol = '-';
            //char symbol = '1';
            char symbol = 'a';
            Console.WriteLine("Is number or letter? " + Char.IsLetterOrDigit(symbol));


            Console.ReadKey();

        }
    }
}

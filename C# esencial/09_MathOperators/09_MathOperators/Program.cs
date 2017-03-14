using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _09_MathOperators
{
    class Program
    {
        static void Main(string[] args)
        {
            //Variable
            double value1 = 42;
            double value2 = 12;

            //Operadores matematicos
            Console.WriteLine("Add: " + (value1 + value2));
            Console.WriteLine("Substract: " + (value1 - value2));
            Console.WriteLine("Divide: " + (value1 / value2));
            Console.WriteLine("Multiply: " + (value1 * value2));
            Console.WriteLine("Remainder: " + (value1 % value2));

            //Operadores unarios

            Console.WriteLine("Increment: " + value1++);
            Console.WriteLine("Decrement: " + value2--);
            Console.WriteLine("Increment inverse: " + (++value1));
            Console.WriteLine("Decrement inverse: " + (--value2)); 
            Console.WriteLine("Add: " + (value1 += 5));
            Console.WriteLine("Substract: " + (value2 -= 5));



            Console.ReadKey();
            
            
        }
    }
}

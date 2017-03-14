using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _06_Strings
{
    class Program
    {
        static void Main(string[] args)
        {
            //Array de caracteres
            char[] charArray = { 'h', 'e', 'l', 'l', 'o' };
            Console.WriteLine("The value of charArray is: " + charArray);

            //Declaración de una variable
            string hello = new String(charArray);
            Console.WriteLine("The value of hello is: " + hello);

            //Metodos de String
            string helloUpper = hello.ToUpper();
            Console.WriteLine("The value of helloUpper is: " + helloUpper);

            //Dividir cadena
            string sub = hello.Substring(3,2);
            Console.WriteLine("The value of sub is: " + sub);

            //Asignación de texto
            hello += " world";
            Console.WriteLine("The value of hello is: " + hello);

            Console.ReadKey();
        }
    }
}

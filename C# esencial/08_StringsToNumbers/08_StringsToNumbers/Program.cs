using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _08_StringsToNumbers
{
    class Program
    {
        static void Main(string[] args)
        {
            //Parse de String a Numeros
            string s = "255";
            int intFromString = Int32.Parse(s);
            Console.WriteLine("Value of intFromString: " + intFromString);

            int doubled = intFromString * 2;
            Console.WriteLine("Value of intFromString: " + doubled);

            //string s2 = "19.99xxx"; //-> Causa una excepcion
            string s2 = "19.99";
            double doubleFromString = Double.Parse(s2);
            Console.WriteLine("Value of doubleFromString: " + doubleFromString);

            //Verificar si puede convertir a un double
            string s3 = "19.99xxx";
            double doubleTryParse;
            if (Double.TryParse(s3,out doubleFromString))
            {
                Console.WriteLine("Value of doubleFromString: " + doubleFromString);
            }
            else
            {
                Console.WriteLine("Couldn´t parse the value!");
            }

            Console.ReadKey();
        }
    }
}

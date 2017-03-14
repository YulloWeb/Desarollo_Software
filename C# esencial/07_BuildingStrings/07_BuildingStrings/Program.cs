using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _07_BuildingStrings
{
    class Program
    {
        static void Main(string[] args)
        {
            //Forma no recomendada para construir Strings
            string hello = "Hello";
            hello += " World!";
            Console.WriteLine(hello);

            //Es recomendado usar StringBuilder
            StringBuilder builder = new StringBuilder();
            builder.Append("Hello");
            Console.WriteLine(builder.ToString());
            builder.Append(", ").Append("World").Append("!");
            Console.WriteLine(builder.ToString());

            Console.ReadKey();

        }
    }
}

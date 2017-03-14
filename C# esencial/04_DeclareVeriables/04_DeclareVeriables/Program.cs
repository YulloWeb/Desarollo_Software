using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04_DeclareVeriables
{
    class Program
    {
        static void Main(string[] args)
        {
            /*
                Declaración de variables tipo C#
                1.- Tipo de dato
                2.- Nombre del identificador 
                3.- Asignación del operador igual
                4.- Asignación del valor
            */

            int i = 42;
            Console.WriteLine("The value is: " + i);

            /*
                Declarar e inicializar mediante clases o estructuras
            */

            //Tipo de declaración .NET
            int j = new Int32(); //--> Tipo explicito
            Console.WriteLine("The value is: " + j);

            
            var implicitValue = 56;//--> Tipo implicito
            Console.WriteLine("The value is: " + implicitValue);

            //Mostrar el tipo de datos de la declaración implicita
            Console.WriteLine("The variable's type is: " + implicitValue.GetType().ToString());

            //Los tipos implicitos se pueden agragar sufijos, por ejemplo,
            var sufix = 12L;//--> L significa que es de tipo Long
            Console.WriteLine("The variable's type is: " + sufix.GetType().ToString());

            //Exceder el valor de una variable
            //byte de 0 a 255
            byte by = 255;
            Console.WriteLine("The byte value is: " + by);
            by++;
            Console.WriteLine("The new byte value is: " + by);

            Console.ReadKey();

           
        }
    }
}

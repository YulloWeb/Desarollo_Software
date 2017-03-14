using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace _03_HelloWorldASPNET
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            //Obtener el valor del TexBox1
            string value = TextBox1.Text;

            //Asignar value al Label1
            Label1.Text = value;
        }
    }
}
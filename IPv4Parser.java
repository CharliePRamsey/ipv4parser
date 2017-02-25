import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class IPv4Parser
{
   public static void main(String[] args) throws Exception
   {
      File file1 = new File(args[0]); //This is the input file, containing a list of IPv4 addresses
      Scanner inFile = new Scanner(file1); //Making a Scanner to read in the list
      ArrayList<String> addrs = new ArrayList<String>(); //Making an ArrayLIst to contain the list
      PrintWriter pw = new PrintWriter(args[1], "UTF-8"); //Used to write output
      
      while(inFile.hasNext()) //Reading all addresses into an ArrayList
      {
         addrs.add(inFile.nextLine());
      }
      
      
      for(int i = 0; i < addrs.size(); i++) //This loop cycles through and deals with the addresses one by one
      {
         String out = ""; //This string is what is printed to the out file.
         String[] currentIP = addrs.get(i).split("[./]"); //Current IP address, split by periods and / to separate mask
         int mask = Integer.parseInt(currentIP[currentIP.length-1]);
         
         for(int j = 0; j < currentIP.length-1; j++) //This loop cycles through and deals with each segment of the address
         { 
            int segment = Integer.parseInt(currentIP[j]);
            String[] segString = new String[8];
            for(int m = 7; m > -1; m--)  //This loop utilizes the binary conversion algorithm to convert the segment to binary, adding them in backwards as the algorithm dictates
            {
               segString[m] = "" + segment%2;
               segment = segment / 2;
            }
            
            for(int n = 0; n < segString.length; n++) //This loop adds the binary digits individually to the out string
            {
               if (mask == 0) //This, in addition to the mask-- below, is used to gauge when the mask has been entered completely.
                  out += "-";
                  
               out += segString[n];
               mask--;   
            }
         }
         
         pw.println(out);
      }
      
      pw.close();
      inFile.close();
   }
}

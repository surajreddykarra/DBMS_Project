import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main 
{

    public static void f(String prefix, String chars, ArrayList<String> result) 
    {
        for (int i = 0; i < chars.length(); i++) 
        {
            result.add(prefix + chars.charAt(i));
            f(prefix + chars.charAt(i), chars.substring(i + 1), result);
        }
    }

    public static ArrayList<String> getCombinations(ArrayList<String> chars) 
    {
        ArrayList<String> result = new ArrayList<String>();
        String str = new String();
        for (String c : chars) 
        {
            str = str + c;
        }
        Main.f("", str, result);
        return result;
    }

    public static boolean checkIncompleteSubstring(String a, String b)
    {
        //System.out.println("new funcyion for patial");
        if(a.length()==b.length()) return false;
        
        /*int i = 0;
        int j = 0;
        for (i=0; i<=a.length()-b.length(); i++)
        {
            for (j = 0; j<b.length(); j++)
            {
               if(b.charAt(j) != a.charAt(i+j))
                  break;
            }

            if (j == b.length())
               return true;
        }*/
        int found_prime=0;
        int found_all=1;
        for(char g: a.toCharArray())
        {
            int flag=0;
            for(char h: b.toCharArray())
            {
                if(h==g)
                {
                    found_prime=1;
                    flag=1;
                }
            }
            if(flag==0)
            {
                found_all=0;
            }
        }
        int found_non_prime=0;
        int dlag=0;
        for(char c: b.toCharArray())
        {
            dlag=0;
            for(char d: a.toCharArray())
            {
                if(c==d)
                {
                    dlag=1;
                    break;
                }
            }
            if(dlag==0)
            {
                found_non_prime=1;
            }
        }
        if(found_non_prime==1)
        {
            //System.out.println("since found a non-prime attr"+a+" "+b);
            return false;
        }
        if(found_prime==1&&found_all==0)
        {
            //System.out.println("found only a few"+a+" "+b);
            return true;
        }
        if(found_prime==1&&found_all==1)
        {
            //System.out.println("found all attrs"+a+" "+b);
            return false;
        }
        if(found_prime==0)
        {
            //System.out.println("found none"+a+" "+b);
            return false;
        }
        return false;
    }
    public static boolean checkSubstring(String a, String b)
    {
        /*int i = 0;
        int j = 0;
        for (i=0; i<=a.length()-b.length(); i++)
        { 
            for (j = 0; j<b.length(); j++)
            {
               if(b.charAt(j) != a.charAt(i+j))
                  break;
            }

            if (j == b.length())
               return true;
        }

        return false;*/
        //System.out.println("new funcyion"); 
        for(char g: a.toCharArray())
        {
            int flag=0;
            for(char h: b.toCharArray())
            {
                if(h==g)
                {
                    
                    flag=1;
                }
            }
            if(flag==0)
            {
                return false;
            }
        }
        return true;
    }

    public static HashSet<String> closure(HashSet<String> attributes, Map<HashSet<String>, HashSet<String>> dependencies) 
    {
        HashSet<String> closureSet = new HashSet<String>(attributes);

        for (Entry<HashSet<String>, HashSet<String>> dependency : dependencies.entrySet()) 
        {
            if (closureSet.containsAll(dependency.getKey()) && !closureSet.containsAll(dependency.getValue())) 
            {
                closureSet.addAll(dependency.getValue());
            }
        }
        return closureSet;
    }

    public static void main(String[] args) 
    {
        try 
        {

            FindKey FK = new FindKey();
            Scanner commandLine = new Scanner(System.in);
            System.out.println("Program is being run");

            do 
            {
                System.out.print("Enter the attributes as: A,B,... or Type Exit to quit the program : ");
                String attributes = commandLine.next();
                if (attributes.equalsIgnoreCase("exit")) 
                {
                    break;
                }
                FK.addAttributes(attributes);

                System.out.print("Enter the FDs as: A->B,AC->B,... or Type Exit to quit the program : ");
                String fd = commandLine.next();
                if (fd.equalsIgnoreCase("exit")) 
                {
                    break;
                }

                FK.addFD(fd);
                System.out.println(" \n\n ==> The candidate keys are: " + FK.getCandidateKeys() + "\n\n");

                HashSet<String> keys = new HashSet<String>();
                for(String i : FK.getCandidateKeys())
                {
                    keys.add(i);
                }

                NormalForm NF = new NormalForm(keys,FK);
                NF.printNormalForm(NF);

                FK = new FindKey();
                NF = new NormalForm(keys,FK);

            } while (true);
        } 

        catch (Exception E) 
        {
            System.out.println(" Something went wrong, please check your input, here is the Exception:");
            E.printStackTrace();
        }
    }
}

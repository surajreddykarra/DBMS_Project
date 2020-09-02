import java.util.*;

public class NormalForm
{
    public int NF = 1;
    HashMap <HashMap<String, String>, Integer> individualNormalForms = new HashMap<HashMap<String, String>, Integer>();

    public NormalForm(HashSet<String> keys, FindKey FK)
    {
        for(HashMap.Entry<String, String> entry : FK.functionalDependencyMap.entrySet())
        {
            HashMap<String, String> currentFunctionalDependency = new HashMap<String, String>();
            currentFunctionalDependency.put(entry.getKey(), entry.getValue());
            individualNormalForms.put(currentFunctionalDependency, 1);
            int fla=0;
            for(String j : keys)
            {
                //System.out.println("Main.checkIncompleteSubstring-"+j+" "+entry.getKey()+" "+Main.checkIncompleteSubstring(j,entry.getKey()));
                if( Main.checkIncompleteSubstring(j, entry.getKey())&&!isPrimeAttribute(entry.getValue(), keys))
                {
                    fla=1;
                    break; 
                }                    
            }
            if(fla==1)
                continue;
            if(isSuperKey(entry.getKey(),keys)==false&&isPrimeAttribute(entry.getValue(),keys)==false)
            {
                individualNormalForms.replace(currentFunctionalDependency,2);
                continue;
            }
            if(isSuperKey(entry.getKey(),keys)==false)
            {
                individualNormalForms.replace(currentFunctionalDependency,3);
                continue;
            }
            individualNormalForms.replace(currentFunctionalDependency,4);
            /*for(char i : entry.getKey().toCharArray())
            {
                if(isSuperKey(Character.toString(i),keys)==false)
                {
                    flag=1;
                    individualNormalForms.replace(currentFunctionalDependency,3);
                    continue;
                }
            }
            if(flag==1) 
                continue;
            else
            {
                individualNormalForms.replace(currentFunctionalDependency,4);
                continue;
            }*/
            
        }
        //System.out.println("even this\n");
        int totalMinimumFD = 4;
        ArrayList<HashMap<String, String>> minimumFDs = new ArrayList<HashMap<String, String>>();

        for(HashMap.Entry<HashMap<String, String>, Integer> entry : individualNormalForms.entrySet())
        {
            //System.out.println(entry.getKey()+" - "+entry.getValue());
            if(entry.getValue() < totalMinimumFD) totalMinimumFD = entry.getValue();
        }

        for(HashMap.Entry<HashMap<String, String>, Integer> entry : individualNormalForms.entrySet())
        {
            if(entry.getValue()==totalMinimumFD) minimumFDs.add(entry.getKey());
        }

        this.NF = totalMinimumFD;
        if(this.NF!=4)
        {
            System.out.println("Create new tables with the following functional dependencies : \n");
            for(HashMap<String, String> i : minimumFDs)
            { 
                //System.out.println("entered");

                for(HashMap.Entry<String, String> entry : i.entrySet())
                {
                    String lhs,rhs;
                    rhs = "";
                    lhs = entry.getKey();
                    if(this.NF!=3){
                        for(char j : entry.getValue().toCharArray())
                        {
                            if(!isPrimeAttribute(String.valueOf(j),keys)) 
                            {
                                rhs += String.valueOf(j);
                                //System.out.println("adding "+j);
                            }
                        }
                    }
                    else
                    {                        
                        rhs=entry.getValue();
                    }
                    System.out.println(lhs + "->" + rhs + "\n");
                }
            }
        }
    }

    public boolean isSuperKey(String i,HashSet<String> keys)
    {
        int flag1=0;
        for(String j : keys)
        {
            if(Main.checkSubstring(j,i)==true)
            {
                flag1=1;
                break;
            }
        }
        if(flag1==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isPrimeAttribute(String i, HashSet<String> keys)
    {
        int flag1=0;
        for(char j : i.toCharArray())
        {
            int flag2=0;
            for(String k : keys)
            {
                if(Main.checkSubstring(Character.toString(j),k)==true) 
                {
                    flag2=1;
                    break;
                }
            }
            if(flag2==0)
            {
                flag1=1;
                break;
            }
        }
        //System.out.println("")
        if(flag1==1) return false;
        else return true;
    }

    public void printNormalForm(NormalForm NF)
    {
        System.out.println("The highest Normal Form for this database model is : " + NF.NF);
    }
}

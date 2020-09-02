public class Directory {
    Bucket[] directory= new Bucket[16];
    int gd = 2;    
    Directory()
    {
        for(int i=0;i<16;i++)
        {
            directory[i]=null;
        }
        directory[0] = new Bucket();
        directory[1] = new Bucket();
        directory[2]=directory[0];
        directory[3]=directory[1];
        directory[0].arr[3]=0;
        directory[1].arr[3]=1;
        System.out.println("directory successffully created");
    }
    public int gd_inc(Directory dir)
    {
        if(dir.gd==4)
        {
            return -1;
        }
        int curr_dir_size=1;
            int new_dir_size;
            for(int i=0;i<dir.gd;i++)
            {
                curr_dir_size*=2;
            }
            new_dir_size=curr_dir_size*2;
            for(int i=curr_dir_size;i<new_dir_size;i++)
            {
                dir.directory[i]=dir.directory[i-curr_dir_size];
            }
            (dir.gd)++;
            return 1;
    }
    public int insert_dir(int hash_val,int key,Directory dir)
    {
        int ret;
        int current_size = 1;
        for(int i=0;i<dir.gd;i++)
        {
            current_size*=2;
        }
        ret = dir.directory[(hash_val%current_size)].insert(key,dir.gd,dir);
        System.out.println("returned to orig");
        dir.print(dir);
        return ret;
    }
    public void print(Directory dir)
    {
        int current_size=1;
        for(int i=0;i<dir.gd;i++)
        {
            current_size*=2;
        }
        for(int i=0;i<current_size;i++)
        {
            System.out.print("Directory "+i+" : ");
            if(dir.directory[i].a_full==true)
            {
                System.out.print(dir.directory[i].a);
                System.out.print(" ");
                if(dir.directory[i].b_full==true)
                {
                    System.out.print(dir.directory[i].b);
                    System.out.print(" ");
                    if(dir.directory[i].c_full==true)
                    System.out.print(dir.directory[i].c);
                }
            }
            System.out.print("\n");
        }
    }
    public int search(Directory dir,int key)
    {
        int mod;
        boolean ans;
        int l=1;
        for(int i=0;i<gd;i++)
        {
            l*=2;
        }
        mod=key%l;
        ans=dir.directory[mod].sear(key,dir);
        System.out.println("elemnt is "+ans);
        if(ans==true)
        {
            return mod;
        }
        else
        {
            return -1;
        }
    }
}

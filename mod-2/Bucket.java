public class Bucket {
    int a,b,c,ld;
    int[] arr;
    boolean a_full,b_full,c_full;
    Bucket()
    {
        System.out.println("bucket started");
        a_full=false;
        b_full=false;
        c_full=false;
        a=b=c=0;
        ld=1;
        arr =new int[4];
        arr[0]=-1;
        arr[1]=-1;
        arr[2]=-1;
        arr[3]=-1;
        System.out.println("bucket successful");
    }
    int insert(int key,int gd,Directory dir)
    {
        if(a_full==false)
        {
            a=key;
            a_full=true;
        }
        else if(b_full==false)
        {
            b=key;
            b_full=true;
        }
        else if(c_full==false)
        {
            c=key;
            c_full=true;
        }
        else if(gd>ld)
        {
            int ret = ld_inc(dir,key);
            return ret;
        }
        else
        {
            int ret;
            ret = dir.gd_inc(dir);
            if(ret==1){
            System.out.println("gd is done");
            ld_inc(dir,key);
            System.out.println("ld is called");
            return 1;
            }
            else
            {
                return -1;
            }
        }
        return 1;
    }
    int ld_inc(Directory dir,int key)
    {
        //System.out.println("bucket called");
        Bucket buc=new Bucket();
        //System.out.println("returned");
            int a1,b1,c1,temp,k=0;
            if(this.arr[2]==-1)
            {
                this.arr[2]=0;
                for(int i=0;i<4;i++)
                {
                    buc.arr[i]=this.arr[i];
                }
                buc.arr[2]=1;
                this.ld=2;
                buc.ld=2;
            }
            else if(this.arr[1]==-1)
            {
                this.arr[1]=0;
                for(int i=0;i<4;i++)
                {
                    buc.arr[i]=this.arr[i];
                }
                buc.arr[1]=1;
                this.ld=3;
                buc.ld=3;
            }
            else
            {
                this.arr[0]=0;
                for(int i=0;i<4;i++)
                {
                    buc.arr[i]=this.arr[i];
                }
                buc.arr[0]=1;
                this.ld=4;
                buc.ld=4;
            }
            int i=3;
            int num=0;
            int count=1;
            while(i>=0&&buc.arr[i]!=-1)
            {
                num=(count*buc.arr[i])+num;
                i--;
                if(i<0)
                    break;
                count*=2;
            }
            dir.directory[num]=buc;
            System.out.println("bucket assgmt done");
            a1=a;
            b1=b;
            c1=c;
            a_full=false;
            b_full=false;
            c_full=false;
            temp=a1%16;
            
            for(int j=0;j<this.ld;j++)
            {
                k=temp%2;
                temp=temp/2;
            }
            if(k==0)
            {
                this.insert(a1,dir.gd,dir);
            }
            else
            {
                buc.insert(a1, dir.gd,dir);
            }
            temp=b1;
            for(int j=0;j<this.ld;j++)
            {
                k=temp%2;
                temp=temp/2;
            }
            if(k==0)
            {
                this.insert(b1,dir.gd,dir);
            }
            else
            {
                buc.insert(b1, dir.gd,dir);
            }
            temp=c1;
            for(int j=0;j<this.ld;j++)
            {
                k=temp%2;
                temp=temp/2;
            }
            if(k==0)
            {
                this.insert(c1,dir.gd,dir);
            }
            else
            {
                buc.insert(c1, dir.gd,dir);
            }
            temp=key;
            System.out.println("key inserion call made");
            for(int j=0;j<this.ld;j++)
            {
                k=temp%2;
                temp=temp/2;
            }
            if(k==0)
            {
                if(this.c_full==true)
                {
                    return -1;
                }
                this.insert(key,dir.gd,dir);
            }
            else
            {
                if(this.c_full==true)
                {
                    return -1;
                }
                buc.insert(key,dir.gd,dir);
            }
            return 1;
    }
    boolean sear(int key,Directory dir)
    {
        if(a_full==true)
        {
            if(a==key)
            {
                return true;
            }
        }
        if(b_full==true)
        {
            if(b==key)
            {
                return true;
            }
        }
        if(c_full==true)
        {
            if(c==key)
            {
                return true;
            }
        }
        return false;
    }
}

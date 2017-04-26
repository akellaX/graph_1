package graph;

import java.util.ArrayList;

/**
 * Created by User on 21.04.2017.
 */
public class Bucket {

    int M;
    int tops;
    int[] B;
    int[] FW;
    int[] BK;
    public Bucket(int size,int t)
    {
        M=size;
        tops=t;
        B=new int[M];
        FW=new int[tops];
        BK=new int[tops];
        for(int i=0;i<M;i++)
            B[i]=-1;

    }
    public int GET(int k)
    {
        int i=B[k];
        if(i!=-1)
            B[k]=FW[i];
        return i;
    }
    public void INSERT(int i,int k)
    {
        int j=B[k];
        FW[i]=j;
        if(j!=-1)
            BK[j]=i;
        B[k]=i;
    }
    public void REMOVE(int i,int k)
    {
        int fi=FW[i];
        int bi=BK[i];
        if(i==B[k])
            B[k]=fi;
        else
        {
            FW[bi]=fi;
            if(fi!=-1)
                BK[fi]=bi;
        }
    }



}

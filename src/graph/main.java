package graph;


/**
 * Created by User on 10.03.2017.
 */
public class main {

    public static void main(String args[])
    {
        Conteiner gr=new Conteiner("second.txt");
//        gr.writing();
//        gr.drawing();
//        gr.kraskal();
//        gr.dijkstra(0);
//        gr.union_find();
//        System.out.println();
//        gr.Blema_Ford(0);
        int sum=gr.dijkstra(0);
        int num=0;
        for(int i=1;i<gr.tops-1;i++)
        {
            if (sum>gr.dijkstra(i))
            {
                sum=gr.dijkstra(i);
                num=i;
            }
        }
        System.out.print(num);



    }

}

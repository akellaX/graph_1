package graph;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Conteiner {

    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();
    ArrayList<Integer> head = new ArrayList<>();
    ArrayList<Integer> L = new ArrayList<>();
    ArrayList<Integer> weight = new ArrayList<>();
    Integer tops;
    Integer arcs;

    public Conteiner(String text) {
        reading(text);
        for (int i = 0; i < arcs; i++)
            L.add(-1);
        for (int i = 0; i < tops; i++)
            head.add(-1);
        fullH();
    }

    public void delete(int a, int b) {

        for (int k = head.get(a); k != -1; k = L.get(k)) {
            if (right.get(k) == b) {
                left.remove(k);
                right.remove(k);
            }

            arcs--;


        }
        fullH();

    }

    public void add(int a, int b) {
        left.add(a);
        right.add(b);
        L.add(-1);
        head.add(-1);
        fullH();

    }

    private void reading(String filename) {

        try {
            Scanner in = new Scanner(new File(filename));
            arcs = in.nextInt();
            tops = in.nextInt();
            for (int i = 0; i < arcs; i++) {
                left.add(in.nextInt());
                right.add(in.nextInt());
                weight.add(in.nextInt());
            }
            in.close();


        } catch (IOException ex) {
            System.err.println("File not found");
        }


    }

    public void writing() {
        for (int i = 0; i < left.size(); i++) {
            System.out.println(left.get(i) + " " + right.get(i));

        }

    }

    private void fullH() {
        for (int i = 0; i < arcs; i++) {
            if (head.get(left.get(i)) == -1) {
                head.set(left.get(i), i);
            } else {
                L.set(i, head.get(left.get(i)));
                head.set(left.get(i), i);
            }
        }
    }

    public void drawing() {
        DirectedSparseGraph g = new DirectedSparseGraph();
        for (int i = 0; i < tops; i++)
            g.addVertex(i);
        for (int i = 0; i < arcs; i++)
            g.addEdge(i, left.get(i), right.get(i));
        VisualizationImageServer vs =
                new VisualizationImageServer(
                        new CircleLayout(g), new Dimension(200, 200));
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vs.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);


        JFrame frame = new JFrame();
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void sort(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> w) {
        for (int i = w.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (w.get(j) > w.get(j + 1)) {
                    int t = w.get(j);
                    w.set(j, w.get(j + 1));
                    w.set(j + 1, t);
                    t=a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, t);
                    t=b.get(j);
                    b.set(j, b.get(j + 1));
                    b.set(j + 1, t);
                }
            }
        }


    }

    public void kraskal()
    {
        ArrayList<Integer> a=left;
        ArrayList<Integer> b=right;
        ArrayList<Integer> w=weight;
        sort(a,b,w);
        ArrayList<Integer> colors=new ArrayList<>();
        ArrayList<Integer> res_l=new ArrayList<>();
        ArrayList<Integer> res_r=new ArrayList<>();
        for(int i=0;i<tops;i++)
            colors.add(i);
        int sum=0;
        for(int i=0;i<arcs;i++)
        {
           if(colors.get(a.get(i))!=colors.get(b.get(i)))
           {
               sum+=w.get(i);
               res_l.add(a.get(i));
               res_r.add(b.get(i));
               int old_id=colors.get(b.get(i)), new_id=colors.get(a.get(i));
               for(int j=0;j<tops;j++)
                   if(colors.get(j)==old_id)
                       colors.set(j,new_id);
           }


        }

        for(int i=0;i<res_l.size();i++)
        {
            System.out.println(res_l.get(i)+" "+res_r.get(i));
        }
        System.out.println(sum);


    }
}

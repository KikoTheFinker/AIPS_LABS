package Lab8;
//Дадено  е бинарно дрво. Потоа дадена е вредноста на некој јазол во дрвото. Испечатете го збирот на елементите во неговото лево поддрво кои се помали од него и збирот на елементите во неговото десно поддрво кои се поголеми од него.
//
//-----------
//You are given a binary tree and a node value in the tree. Print the sum of the elements of the node's left subtree that are lower than the given node value and the sum of the elements of the node's right subtree that are greater than the given node value.
//
//For example:
//
//Input	Result
//10
//0 10 ROOT
//1 19 LEFT 0
//2 8 LEFT 1
//3 7 LEFT 2
//4 60 RIGHT 2
//5 5 RIGHT 1
//6 4 RIGHT 0
//7 13 RIGHT 6
//8 2 LEFT 7
//9 1 RIGHT 7
//10

//20 13
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinaryTreeSum {

    public static BNode<Integer> najdiJazol(BNode<Integer> p,int BV)
    {
        if(p==null)
        {
            return null;
        }
        if(p.info==BV)
        {
            return p;
        }
        BNode<Integer> tmp=najdiJazol(p.left,BV);
        if(tmp!=null)
            return tmp;
        else
            return najdiJazol(p.right,BV);
    }


    public static int pomali(BNode<Integer> p,int BV)
    {
        if(p==null)
        {
            return 0;
        }

        if(p.info<BV)
            return p.info+pomali(p.left,BV)+pomali(p.right,BV);
        else return 0+pomali(p.left,BV)+pomali(p.right,BV);
    }

    public static int pogolemi(BNode<Integer> p,int BV)
    {
        if(p==null)
        {
            return 0;
        }

        if(p.info>BV)
            return p.info+pogolemi(p.left,BV)+pogolemi(p.right,BV);
        else return 0+pogolemi(p.left,BV)+pogolemi(p.right,BV);
    }


    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        @SuppressWarnings("unchecked")
        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i=0;i<N;i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        int baranaVrednost=Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod ovde

        BNode<Integer> p=najdiJazol(tree.root,baranaVrednost);
        int pom=pomali(p.left,baranaVrednost);
        int pog=pogolemi(p.right,baranaVrednost);
        System.out.println(pom+" "+pog);
    }
}


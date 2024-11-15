package Lab8;
//Потребно е да имплементирате Windows Explorer со помош на дрво. Јазлите треба да ви бидат фолдери/датотеки. Почетно ќе имате само еден фолдер c: и тој ви е тековен фолдер. Ќе ви биде дадена низа од команди што можат да бидат во еден од следните типови:
//
//CREATE [име на фолдер/датотека] - треба да креирате нов фолдер/датотека во тековниот. Треба да внимавате при додавањето, новиот фолдер/датотека треба да се смести на онаа позиција така што сите фолдери/датотеки во тековниот фолдер ќе бидат подредени лексикографски.
//
//OPEN [име на фолдер/датотека] - го отварате фолдерот во тековниот фолдер и се менува тековниот фолдер
//
//DELETE [име на фолдер/датотека] - го бришете фолдерот/датотеката
//
//BACK - се враќате назад во претходниот фолдер
//
//PATH - се печати патеката на тековниот фолдер на пример: c:\\users\darko\mydocuments
//
//PRINT - се печати целата структура на датотечниот систем така што секој фолдер/датотека се печати во еден ред со онолку празни места колку што е нивото на тој фолдер/датотека
//
//Забелешка: Имињата на фолдерите/датотеките ќе бидат составени само од еден збор што содржи мали латинични букви. Сите операции ќе бидат валидни.
//
//---------
//
//
//You should implement Windows Explorer by using a tree. Nodes are directories/files. Initially you have only one directory c: and it is your current directory. You will be given an array of commands where each command is one of the following:
//
//CREATE [name of directory/file] - you should create new directory/file in the current directory. Be careful when adding new directory/file, it should be put in a position such that all directories/files in the current folder will be sorted lexicographically.
//
//OPEN [name of directory/file] - you open the directory in the current folder and the current folder is changed.
//
//DELETE [name of directory/file] - you delete the folder/file.
//
//BACK - you are back in the previous directory.
//
//PATH - prints the path of the current directory, for example c:\\users\darko\mydocuments.
//
//PRINT - prints the whole file system structure such that each directory/file is printed in a new line with spaces equal to level of that directory/file.
//
//Note: The names of the directories/files contain only one word composed of lowercase Latin letters.
//
//Class name: WindowsExplorer
//
//
//
//
//For example:
//
//Input	Result
//15
//CREATE a
//OPEN a
//CREATE b
//CREATE d
//CREATE c
//PATH
//OPEN c
//PATH
//CREATE u
//CREATE g
//CREATE h
//PATH
//BACK
//PATH
//PRINT

//c:\a\
//c:\a\c\
//c:\a\c\
//c:\a\
//c:
// a
//  b
//  c
//   g
//   h
//   u
//  d

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}

class SLLTree<E> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i=0;i<level;i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>)node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level+1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

}

class SLLNode<P> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
}

public class WindowsExplorer {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (i=0;i<N;i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");
        SLLNode<String> current=tree.root;
        // vasiot kod stoi ovde
        for (i=0;i<N;i++){
            String commandSplit[]=commands[i].split(" ");
            if (commandSplit.length==1){
                if (commandSplit[0].equals("PATH")){
                    SLLNode<String> tmp=current;
                    String path="";
                    while (tmp!=null){
                        path=tmp.element+"\\"+path;
                        tmp=tmp.parent;
                    }
                    System.out.println(path);
                }else
                if (commandSplit[0].equals("PRINT")){
                    tree.printTree();
                }else
                if (commandSplit[0].equals("BACK")){
                    current=current.parent;
                }

            } else {
                if (commandSplit[0].equals("CREATE")){
                    if (current.firstChild==null){
                        current.firstChild=new SLLNode<String>(commandSplit[1]);
                        current.firstChild.parent=current;
                        continue;
                    }
                    SLLNode<String> tmp=current.firstChild;
                    if(tmp.element.compareTo(commandSplit[1])>=1){
                        current.firstChild=new SLLNode<String>(commandSplit[1]);
                        current.firstChild.parent=current;
                        current.firstChild.sibling=tmp;
                        continue;
                    }
                    while(tmp.sibling!=null){
                        if (tmp.sibling.element.compareTo(commandSplit[1])>=1)
                            break;
                        tmp=tmp.sibling;
                    }
                    SLLNode<String> tmp2=tmp.sibling;
                    tmp.sibling=new SLLNode<String>(commandSplit[1]);
                    tmp.sibling.parent=current;
                    tmp.sibling.sibling=tmp2;
                    tmp=current.firstChild;
                }else
                if (commandSplit[0].equals("OPEN")){
                    SLLNode<String> tmp=current.firstChild;
                    while(tmp!=null&&!tmp.element.equals(commandSplit[1])){
                        tmp=tmp.sibling;
                    }
                    current=tmp;

                }else
                if (commandSplit[0].equals("DELETE")){
                    SLLNode<String> tmp=current.firstChild;
                    if (tmp==null)
                        continue;
                    while(tmp.sibling!=null){
                        if (tmp.element.equals(commandSplit[1]))
                            break;
                        tmp=tmp.sibling;
                    }
                    tree.remove(tmp);

                }
            }
        }


    }

}


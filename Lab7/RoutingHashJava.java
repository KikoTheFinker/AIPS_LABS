package Lab7;
//Потребно е да се симулира рутирање преку хеш табела. Секој рутер претставува една кофичка од хеш табелата и притоа пакетите на влез ги прима преку еден интерфејс. Бидејќи рутерот, рутирањето на даден пакет го врши користејќи ги статичките рути што тој ги знае, кога ќе добие пакет преку влезниот интерфејс, тој треба да даде одговор дали може да го рутира пакетот до дадениот уред во таа мрежа (postoi или nepostoi). Важно е тоа што сите адреси имаат мрежна маска /24, што значи дека последните 8 бита се наменети за адресирање. Претпоставуваме дека сите адреси се зафатени во таа мрежа, така што до било кој уред од таа мрежа, доколку ја има во рутирачката табела, може да се достави пакетот. Така што доколку во рутирачката табела има 10.10.10.0, тоа значи дека рутерот може да го проследи пакетот до  сите уреди во таа мрежа (10.10.10.1- 10.10.10.254).
//
//
//
//На влез најпрвин се внесува бројот на рутери, потоа најизменично IP адресата на влезниот интерфејс, па во следниот ред IP адресите на мрежите до кој рутерот има статички рути. Потоа се внесува бројот на обиди за рутирање на пакети. Во следните редови најизменично се внесува влезен интерфејс и IP адреса на уред за која треба да се даде одговор дали тој рутер ја познава или не.
//
//Име на класта :RoutingHashJava
//
//--------------
//
//You should simulate routing by using a hash table. Every router is one bucket in the hash table and it receives the input packets only through one interface. Since the router performs the routing of the packet by using the static routes it knows, when it receives a packet it should tell if it is possible to route the packet in the network (postoi or nepostoi). It is important that all addresses have network mask /24 which means that the last 8 bits are allocated for addressing. We assume that all addresses are busy and the packet can be transferred to any device in a network if the network exists in the routing table. For example, if the address 10.10.10.0 can be found in the routing table, it means that the router can transfer the packet to all devices in that network (10.10.10.1- 10.10.10.254).
//
//The number of the routers is given in the first input line. In the next lines for each router the IP address of the interface and IP addresses of the networks to which it has static routes are given. Then the number of routing attempts are entered. In the next lines for each attempt an input interface and device IP address are given. The router should answer if it knows the address or not.
//
//Class Name: RoutingHashJava
//
//
//For example:
//
//Input	Result
//2
//23.3.3.3
//10.10.10.0
//192.168.1.1
//20.2.2.0
//2
//192.168.1.1
//20.2.2.1
//13.13.3.3
//192.2.2.2

//postoi
//ne postoi

import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }


    @Override
    public String toString() {
        return element.toString();
    }
}



class CBHT<K extends Comparable<K>, E> {
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }



    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}



public class RoutingHashJava {
    public static void main (String[] args)  {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        CBHT<String,String[]> hashmap = new CBHT<>(20);
        for (int i = 0; i < n; i++){
            String key = sc.nextLine();
            String[] value = sc.nextLine().split(",");
            for (int j = 0; j < value.length; j++){
                value[j] = value[j].substring(0, value[j].lastIndexOf("."));
            }
            hashmap.insert(key, value);
        }
        int num = Integer.parseInt(sc.nextLine());
        String [] ip;
        for (int i = 0; i < num; i++){
            String key = sc.nextLine();
            String value = sc.nextLine();
            SLLNode<MapEntry<String, String[]>> node = hashmap.search(key);

            if (node == null){
                System.out.println("ne postoi");continue;
            }

            ip = node.element.value;
            boolean flag = false;
            for (int j = 0; j < ip.length; j++){
                if (ip[j].equals(value.substring(0, value.lastIndexOf(".")))){
                    flag = true;
                }
            }
            System.out.println(flag ? "postoi" : "ne postoi");
        }

    }
}

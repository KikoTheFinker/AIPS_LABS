package Lab6;
//Луѓето доаѓаат наутро во МВР за да извадат еден или повеќе документи.
//
//Документите може да бидат:
//1. Лична карта
//2. Пасош
//3. Возачка дозвола
//
//Кога се отвора шалтерот прво се услужуваат луѓето кои чекаат за лична карта, па потоа оние за пасош и на крај оние за возачка дозвола.
//Секој човек кога ќе дојде си застанува во редицата за соодветната исправа која ја вади (т.е. или во редицата за лични карти или во редицата за пасоши или во редицата за возачки дозволи). Доколку еден човек има повеќе документи за вадење прво вади лична карта, па пасош и на крај возачка. Така ако еден човек треба да вади и лична карта и возачка дозвола прво застанува во редицата за лични карти и откако ќе заврши таму оди на крајот на редицата за возачки дозволи.
//
//Влез: Првиот ред означува колку луѓе вкупно дошле во МВР. Потоа за секој човек се внесуваат четири реда, во првиот е името и презимето на човекот, а во останатите три реда се кажува кој документ соодветно (лична карта, пасош и возачка) треба да се земе, притоа 1 значи дека треба да се земе тој документ, 0 значи дека не треба да се земе.
//
//На пример:
//
//Aleksandar Aleksandrovski
//1
//0
//1
//означува дека Александар Александровски ќе вади и лична карта и возачка дозвола, но нема да вади пасош.
//Излез: Ги печати имињата на луѓето по редоследот по кој завршуваат со вадење на документи.
//
//----------------
//People come to the Ministry of the Interior in the morning to retrieve one or more documents.
//
//The documents can be:
//1. Identity card
//2. Passport
//3. Driver's license
//
//When the counter opens, people waiting for ID cards are served first, then those for passports, and finally those for driver's licenses.
//
//When each person arrives, they stand in the queue for the appropriate document that they need (ie either in the queue for ID cards or in the queue for passports or in the queue for driver's licenses). If a person needs several documents, first they get an ID card, then a passport and finally a driver's license. Thus, if a person needs to get both an ID card and a driver's license, he first stands in the queue for ID cards, and after he finishes there, he goes to the end of the queue for driver's licenses.
//
//Input: The first row indicates how many people came to the Ministry of Interior in total. Then four lines are entered for each person, in the first is the name and surname of the person, and in the remaining three lines it is said which document (identity card, passport and driver's license) is needed, where 1 means that the document is needed, 0 means it is not.
//
//For example:
//
//Aleksandar Aleksandrovski
//1
//0
//1
//
//indicates that Aleksandar Aleksandrovski will get an ID card and driver's license, but will not get a passport.
//
//Output: Prints the names of the people in the order in which they finish extracting documents.
//
//For example:
//
//Input	Result
//4
//Aleksandar Aleksandrovski
//0
//0
//1
//Petre Petreski
//1
//0
//0
//Branka Brankovska
//0
//0
//1
//Jana Janevska
//0
//1
//0


//Petre Petreski
//Jana Janevska
//Aleksandar Aleksandrovski
//Branka Brankovska

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Gragjanin{
    protected String Ime;
    protected int lkarta;
    protected int pasos;
    protected int vozacka;

    public Gragjanin(String ime, int lkarta, int pasos, int vozacka) {
        Ime = ime;
        this.lkarta = lkarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }
}
public class MVR {
    static void redosled(Gragjanin[] covek){
        Queue<Gragjanin> LicniKarti = new LinkedList<Gragjanin>();
        Queue<Gragjanin> Pasoshi = new LinkedList<Gragjanin>();
        Queue<Gragjanin> VozackiDozvoli = new LinkedList<Gragjanin>();

        for (int i = 0; i < covek.length; i++){
            if (covek[i].lkarta == 1){
                LicniKarti.add(covek[i]);
            }
            else if (covek[i].pasos == 1){
                Pasoshi.add(covek[i]);
            }
            else if (covek[i].vozacka == 1){
                VozackiDozvoli.add(covek[i]);
            }
        }
        while (!LicniKarti.isEmpty()){
            Gragjanin p = LicniKarti.remove();
            if (p.pasos == 1){
                Pasoshi.add(p);
            }
            else if (p.vozacka == 1){
                VozackiDozvoli.add(p);
            }
            else if (p.pasos == 0 && p.vozacka == 0) {
                System.out.println(p.Ime);
            }
        }
        while (!Pasoshi.isEmpty()){
            Gragjanin p = Pasoshi.remove();
            if (p.vozacka == 1){
                VozackiDozvoli.add(p);
            }
            else {
                System.out.println(p.Ime);
            }
        }
        while (!VozackiDozvoli.isEmpty()){
            Gragjanin p = VozackiDozvoli.remove();
            System.out.println(p.Ime);
        }
    }
    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        int N = Integer.parseInt(br.nextLine());
        Gragjanin [] covek = new Gragjanin[N];
        for (int i = 0; i < N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            covek[i] = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
        }
        redosled(covek);
    }
}

package Lab7;
//Потребно е да се направи компјутерска апликација со која ќе се забрза работењето на една аптека. Притоа апликацијата треба да му овозможи на корисникот (фармацевтот) брзо да пребарува низ огромното множество со лекови кои се внесени во системот. Начинот на кој тој треба да пребарува е следен: доволно е да ги внесе првите 3 букви од името на лекот за да може да му се излиста листа од лекови кои ги има во системот. Работата на фармацевтот е да провери дали внесениот лек го има во системот и да му даде информација на клиентот. Информацијата што треба да му ја даде на клиентот е дали лекот се наоѓа на позитивната листа на лекови, која е цената и колку парчиња од лекот има на залиха. Доколку лекот постои клиентот го нарачува со што кажува колку парчиња ќе купи. Оваа акција фармацевтот треба да ја евидентира на системот (односно да ја намали залихата на лекови за онолку парчиња колку што му издал на клиентот). Доколку нарачката на клиентот е поголема од залихата на лекот што ја има во системот, не се презема никаква акција.
//
//Влез: Од стандарден влез прво се дава број N кој претставува број на лекови кои ќе бидат внесени во системот. Во наредните N реда се дадени имињата на лековите, дали ги има на позитивната листа (1/0), цената и број на парчиња, сите разделени со по едно празно место. Потоа се даваат редови со имиња на лекови и број на парчиња нарачани од клиентот. За означување на крај се дава зборот KRAJ.
//
//Излез: На стандарден излез треба да се испечати за секој од влезовите следната информација: IME POZ/NEG CENA BR_LEKOVI. Доколку лекот не е најден се печати Nema takov lek. Доколку нарачката на клиентот е поголема од залихата се печати Nema dovolno lekovi инаку Napravena naracka.
//
//Забелешка: Задачата да се реши со хeш табела. Функцијата со која се врши мапирање на имињата на лековите во број е следна: h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780 каде зборот w=c1c2c3c4c5…. е составен од сите големи букви.
//
//Исто така за лековите да се направи посебна класа која како атрибути ќе ги има наведените карактеристики на лекот во системот.
//
//Име на класата: Apteka.
//
//-----------
//
//It is necessary to make a computer application that will speed up the operation of a pharmacy. The application should enable the user (pharmacist) to quickly search through the huge set of drugs entered into the system. The way he should search is as follows: it is enough to enter the first 3 letters of the name of the drug so that a list of the drugs available in the system can be displayed. The job of the pharmacist is to check if the drug is in the system and to give information to the client. The information he should give to the customer is whether the drug is on the positive list of drugs, what is the price and how many pieces of the drug are in stock. If the drug exists, the customer orders it, stating how many pieces he will buy. The pharmacist should record this action on the system (that is, reduce the stock of drugs by as many pieces as he dispensed to the client). If the customer's order is greater than the drug stock in the system, no action is taken.
//
//Input: From the standard input, a number N is first given which represents the number of drugs that will be entered into the system. In the next N lines are given the names of the drugs, whether they are on the positive list (1/0), the price and number of pieces, all separated by a space. Lines are then given with drug names and number of pieces ordered by the customer. The word is given to indicate the end KRAJ.
//
//Output: The following information should be printed on the standard output for each of the inputs: IME POZ/NEG CENA BR_LEKOVI. If the drug is not found, Nema takov lek. is printed. If the customer's order is larger than the stock, it is printed  Nema dovolno lekovi, otherwise Napravena naracka.
//
//Note: The problem should be solved with a hash table. The function that maps drug names to numbers is as follows: h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780 where the word w=c1c2c3c4c5…. is composed of all capital letters.
//
//Also, for the drugs, a separate class should be made which will have the specified characteristics of the drug in the system as attributes.
//
//Class name: Apteka.
//
//
//For example:
//
//Input	Result
//20
//ACEROLA 0 100 1000
//ACIKLOVIR 1 1650 87
//ACIPAN 1 300 25
//ADIMICIN 0 500 0
//VENTOR 1 0 25
//VALSACOR 1 1090 10
//TYVERB 0 62696 1
//ULCODIN 1 47 100
//TRICAL 0 0 0
//RUBENS 0 2315 0
//IBALGIN 1 0 100
//HYDROCYKLIN 0 55 10
//GENTAMICIN 1 152 90
//FORTEO 1 0 0
//FORVITAC 1 0 150
//CHIROCAINE 1 0 10
//BRONLES 1 0 0
//BELOGENT 0 143 30
//BEDOXIN 1 0 100
//HYDROCYKLIN20 0 113 20
//hydroCyklinn
//2
//hydroCyklin20
//2
//KRAJ

//Nema takov lek
//HYDROCYKLIN20
//NEG
//113
//20
//Napravena naracka

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class Lek{
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public String getIme() {                return ime;                 }
    public void setIme(String ime) {        this.ime = ime;	            }
    public int getCena() {      		    return cena;	            }
    public void setCena(int cena) {		    this.cena = cena;           }
    public int getKolicina() {  		    return kolicina;	        }
    public void setKolicina(int kolicina) { this.kolicina = kolicina;	}
    public int getPozLista() {      		return pozLista;        	}

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime.toUpperCase();
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        if(pozLista==1) return ime+"\n"+"POZ\n"+cena+"\n"+kolicina;
        else return ime+"\n"+"NEG\n"+cena+"\n"+kolicina;
    }
}

class LekKluch implements Comparable<LekKluch> {
    String ime;
    public LekKluch(String ime) {
        this.ime = ime.toUpperCase();
    }

    @Override
    public int hashCode() {
        int first = ime.charAt(0);
        int second = ime.charAt(1);
        int third = ime.charAt(2);
        return (29*(29*(29*0+first)+second)+third)%102780;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LekKluch lekKluch = (LekKluch) o;
        return Objects.equals(ime, lekKluch.ime);
    }

    @Override
    public int compareTo(LekKluch o) {
        return 0;
    }
}

public class Apteka {
    public static void main(String[] args) throws  IOException {
        Scanner sc = new Scanner(System.in);
        CBHT<LekKluch, Lek> hashmap = new CBHT<>(127);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++){
            String p = sc.nextLine();
            String [] parts = p.split("\\s+");
            hashmap.insert(new LekKluch(parts[0]), new Lek(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
        }
        LekKluch lek;
        int amount;
        while (true){
            lek = new LekKluch(sc.nextLine());
            if (lek.ime.equals("KRAJ")){
                break;
            }
            SLLNode<MapEntry<LekKluch, Lek>> element = hashmap.search(lek);
            amount = Integer.parseInt(sc.nextLine());
            if (element == null){
                System.out.println("Nema takov lek");
                continue;
            }
            System.out.println(element.element.value);
            if (element.element.value.ime.equals(lek.ime)){
                if (element.element.value.kolicina-amount >=0){
                    element.element.value.kolicina-=amount;
                    System.out.println("Napravena naracka");
                }
                else {
                    System.out.println("Nema dovolno lekovi");
                }
            }else {
                System.out.println("Nema takov lek");
            }
        }
    }

}


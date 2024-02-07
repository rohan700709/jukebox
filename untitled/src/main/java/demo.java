import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class demo {

    public static void main(String [] args)
    {
        int number;
        int factorial=1;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number");
        number= scanner.nextInt();

        for(int i =1 ; i<=number;i++)
        {
            factorial=factorial*i;
        }
        System.out.println(factorial);
    }

}

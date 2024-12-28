// Lab2
// Your name: Shlok Kalekar
// Your Andrew id: skalekar

import java.util.Scanner;

// class StringStuff
// Tests a few issues of String equality

public class StringStuff {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int j = 15;
        double x = 1.004;
        String s1 = "gopher", s2;
        s2 = s1;

        System.out.println(j==15);
        System.out.println(x==1.004);
        System.out.println(s1=="gopher");
        System.out.println(s1 == s2);

        s2 = "gopher";
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.print("Enter a string: ");
        s2 = scanner.next();
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));

        String s3 = "The name of my pet ";
        System.out.println(s3);
        System.out.println(s3.toUpperCase());
        System.out.println(s3);
        s2 = s3 + " " + s1;
        System.out.println(s2);
        s2 += " is Fluffy Face";
        System.out.println(s2.toLowerCase());
        System.out.println(s2.replace("e","XYZ"));
        System.out.println(s2);

        String s4 = "Barrett,Sales,#44132,8/22/2018";
        System.out.println(s4);
        String[] slist = s4.split(",");
        for (String str:slist) {
            System.out.println(str);
        }
        String[] date = slist[3].split("/");
        for (String str:date) {
            System.out.println(str);
        }
		String s5 = "";
        for(int i = 0; i < slist.length; i++)    {
            s5  = s5 + slist[i];
        }
		System.out.println(s5);

		long startTime = System.currentTimeMillis();
		for (int i = 0; i<10000; i++)	{
			s1 += s2;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		StringBuilder sb = new StringBuilder(s1);
		startTime = System.currentTimeMillis();
		for (int i = 0; i<10000; i++)	{
			sb.append(s2);
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
    }
}

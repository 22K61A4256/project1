import java.io.*;
public class JSBDExample
{
public static void main(String[] args)
{
StringBuffer sb1=new StringBuffer("Hello World");
sb1.delete(0,6);
System.out.println(sb1);
StringBuffer sb2=new StringBuffer("Some Content");
System.out.println(sb2);
sb2.delete(0,sb2.length());
StringBuffer sb3=new StringBuffer("Hello World");JSBDExample
sb3.deleteCharAt(0);
System.out.println(sb3);
}
}
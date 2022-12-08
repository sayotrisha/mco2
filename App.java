import java.util.Scanner;

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(System.in);
    String DNA;
    int kmer;
    int choice = 0;

    System.out.println("DNA string:");
    DNA = scan.nextLine();

    System.out.println("\nSubstrings of length k:");
    kmer = scan.nextInt();

    System.out.println("\nChoose the hash function to be used");
    System.out.println("[1] FNV1A");
    System.out.println("[2] CRC32");
    System.out.println("Choice:");
    choice = scan.nextInt();

    if (choice == 1) {
      long StartHTTime = System.nanoTime();
      FNV1A.computeHash32(DNA, kmer);
      ;
      long EndHTTime = System.nanoTime();
      // output func
      System.out.println("Hash Table (FNV1a)");
      System.out.println("Time: " + (EndHTTime - StartHTTime) + "ns");
      System.out.println("");
    } else if (choice == 2) {
      long StartHTTime = System.nanoTime();
      CRC32.computeHash32(DNA, kmer);
      long EndHTTime = System.nanoTime();
      System.out.println("Hash Table (CRC32)\n" + "Time: " + (EndHTTime - StartHTTime) + "ns");
      System.out.println("");
    } else
      System.out.println("Invalid input!");

       

    
  }
}
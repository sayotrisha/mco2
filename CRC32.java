import java.util.Scanner;
import java.util.BitSet;
import java.util.ArrayList;

public class CRC32 {
    public static int hash32(byte[] data) {
        BitSet bitSet = BitSet.valueOf(data);
        int crc32 = 0xFFFFFFFF; 
        for (int i = 0; i < data.length * 8; i++) {
            if (((crc32 >>> 31) & 1) != (bitSet.get(i) ? 1 : 0))
                crc32 = (crc32 << 1) ^ 0x04C11DB7;  
            else
                crc32 = (crc32 << 1);
        }
        crc32 = Integer.reverse(crc32);  
        if(crc32 < 0)
            return crc32*-1;
        else 
            return crc32;      
    }

    public static int crc32(String k){
        return hash32(k.getBytes());
    }

     //This is where everything is computed using the second hash function.

    //compute
    public static void computeHash32(String DNA, int kmer){
        String[] hash = new String[DNA.length()]; 
        int collissionNo = 0;
        String current = DNA;

        while (current.length() >= kmer) {
            //Divides into substring to enter the HT
            StringBuffer input = new StringBuffer(current);
            input.setLength(kmer);
            String mer = input.toString();
            
            //Collision resolution via linear probing
                if (hash[(crc32(mer))%hash.length] != null) {
                    collissionNo++;
                    int j = (crc32(mer)%hash.length);
                    for (int i = 1; hash[j] != null && hash[j] != mer && i < hash.length; i++) {
                        j = (j + i) % hash.length;
                        if (hash[j] == null)
                        hash[j] = mer;
                    }
                } else
                hash[crc32(mer)%hash.length] = mer;
          
            //Gets the next string to divide
            StringBuffer next = new StringBuffer(current);
            next.deleteCharAt(0);
            current = next.toString();
    }

         //Output
         System.out.println("Collisions: " + collissionNo + " Size of table: " + hash.length);
         System.out.println(" " + kmer + "-mer   | no. of occurences");
         ArrayList<String> calculated = new ArrayList<String>();
         for (int x = 0; x < hash.length; x++) {
             if (calculated.contains(hash[x]) == false && hash[x] != null) {
                 System.out.print(" " + hash[x] + "   | ");
                 int occurCnt = 0;
                 for (int y = 0; y < hash.length; y++) {
                     if (hash[x].equals(hash[y]) == true) {
                         occurCnt++;
                     }
                 }
                 System.out.println(occurCnt);
                 calculated.add(hash[x]);
             }                                                                                  
        }
    }
}

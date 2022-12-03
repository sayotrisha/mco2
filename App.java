import java.util.HashMap;
public class App {
 
 public static void main(String[] args) {
  String kmer;
   // Input string
   String dna = "taccaccaccatag";
   
   // Initialize a HashMap
   HashMap<String, Integer> kmerDist = new HashMap<String, Integer>();
   
   // Iterate through the DNA string
   for (int i = 0; i < dna.length() - 5; i++) {
     // Compute the hash value of the k-mer
     kmer = dna.substring(i, i + 6);
     int hash1 = FNV1A.hash32(kmer);
     int hash2 = CRC32.crc32(kmer);
     
     // Insert the k-mer into the HashMap
     if (!kmerDist.containsKey(hash1)) {
       kmerDist.put(kmer, 1);
     } else {
       kmerDist.put(kmer, kmerDist.get(hash1) + 1);
     }
     
     if (!kmerDist.containsKey(hash2)) {
       kmerDist.put(kmer, 1);
     } else {
       kmerDist.put(kmer, kmerDist.get(hash2) + 1);
     }
   }
   
   // Output the k-mer distribution
   for (String kmerd : kmerDist.keySet()) {
     System.out.println(kmerd + " : " + kmerDist.get(kmerd));
   }
 }
}
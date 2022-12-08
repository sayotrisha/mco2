import java.util.ArrayList;

public final class FNV1A {

    private static final long FNV_64_INIT = 0xcbf29ce484222325L;
    private static final long FNV_64_PRIME = 0x100000001b3L;

    private static final int FNV_32_INIT = 0x811c9dc5;
    private static final int FNV_32_PRIME = 0x01000193;

    public FNV1A() {}
    public static int hash32(String input) {
        byte[] inBytes = input.getBytes();
        int rv = FNV_32_INIT;
        final int len = inBytes.length;
        for(int i = 0; i < len; i++) {
            rv ^= inBytes[i];
            rv *= FNV_32_PRIME;
        }
        
        if(rv <0)
            return rv*-1;
        else 
            return rv;
    }

    public static long hash64(final byte[] k) {
        long rv = FNV_64_INIT;
        final int len = k.length;
        for(int i = 0; i < len; i++) {
            rv ^= k[i];
            rv *= FNV_64_PRIME;
        }
        return rv;
    }

    public static long hash64(final String k) {
        long rv = FNV_64_INIT;
        final int len = k.length();
        for(int i = 0; i < len; i++) {
            rv ^= k.charAt(i);
            rv *= FNV_64_PRIME;
        }
        return rv;
    }

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
                if (hash[(hash32(mer))%hash.length] != null) {
                    collissionNo++;
                    int j = (hash32(mer)%hash.length);
                    for (int i = 1; hash[j] != null && hash[j] != mer && i < hash.length; i++) {
                        j = (j + i) % hash.length;
                        if (hash[j] == null)
                        hash[j] = mer;
                    }
                } else
                hash[hash32(mer)%hash.length] = mer;
          
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
import java.util.Scanner;
import java.util.BitSet;

public class CRC32 {
    public static int convert_crc32(byte[] data) {
        BitSet bitSet = BitSet.valueOf(data);
        int crc32 = 0xFFFFFFFF; 
        for (int i = 0; i < data.length * 8; i++) {
            if (((crc32 >>> 31) & 1) != (bitSet.get(i) ? 1 : 0))
                crc32 = (crc32 << 1) ^ 0x04C11DB7;  
            else
                crc32 = (crc32 << 1);
        }
        crc32 = Integer.reverse(crc32);  
        return crc32 ^ 0xFFFFFFFF;  
    }

    public static int crc32(String k){
        return convert_crc32(k.getBytes());
    }
  }

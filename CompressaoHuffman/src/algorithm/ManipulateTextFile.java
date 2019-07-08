package algorithm;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.BitSet;

public class ManipulateTextFile {
	
	public static void saveInText(String name, String text) {
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name + ".txt"), "UTF-8"));
			
			writer.write(text);
			
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void saveInBinary(String name, String text) throws IOException {
		FileOutputStream out = new FileOutputStream(name + ".dat");
		
        out.write(encodeToBinary(text));
	
        out.close();
	}
	
	public static byte[] encodeToBinary(String string) {
	    
		BitSet bitSet = new BitSet(string.length());
		int bitcounter = 0;
		for(Character c : string.toCharArray()) {
		    if(c.equals('1')) {
		        bitSet.set(bitcounter);
		    }
		    bitcounter++;
		}
	    
	    return bitSet.toByteArray();
	}

}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CPU {
	
	public static String finalOutput = ""; //write this to a file.


	/*
	 * output to look like the project + all stage outputs
	 */
	
	public static void main(String[] args) {
		
		InstructionFetch IF = new InstructionFetch();
		
		while(!IF.done()) { //TODO: handle branching! (setPC should take care of that)
			
			//test cases:
			//make sure all added instructions are indeed added and PC is corrected.
			//program:
			/*
			 * 000000 01001 01010 01000 00000 100000 add 
			 * 000000 01000 00011 00010 00000 100010 sub 
			 * 000100 01011 01101 0000000000010000 beq
			 * 100011 00001 00011 0000000000000100 lw
			 * 101011 00001 00011 0000000000000100 sw
			 *TODO: ADD these: and, or, slt 
			 */
			IF.InstFetch(IF.getPC());
			
		}
		
		writefile("final_output.txt", finalOutput);
		
	}

	
	public static void writefile(String name, String data) {
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
}

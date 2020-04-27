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
			 * 10000000 00001000 00100100 01000000 add 
			 * 10001000 00000010 00011010 00000000 sub 
			 * 00000000 00010000 01101010 11000100 beq
			 * 00000000 00000100 00011000 01100011 lw
			 * 00000000 00000100 00011000 01101011 sw
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

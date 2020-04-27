import java.util.Hashtable;

public class InstructionDecode {
	
	
	public Hashtable<String,Object> InstDecode(String instructionFetched){
		/*
		 * input: the instruction
		 * output: control signals, readdata1 (as int), readdata2 (as int), PC passed on from instruction fetched (getter)
		 */
	}
	
	
	
	
	//helpers
	
	//but this one is called independently
	public String SignExtend(String immNotExtend) {
		/**input 16 bit immiedaite
		 * output: sign extend
		 */
		
	}
	
	public Hashtable<String,String> ContUnit(String opcode){
		/*
		 * input: opcode from the instdecode
		 * output: all 8 ALU control signals
		 * Shouldn’t the ALUSrc be set to 0 in case of a branch as the ALU will 
		 * perform the desired check on both registers and not an immediate value? It was set to 0 in the example output.
		 */
	}
}

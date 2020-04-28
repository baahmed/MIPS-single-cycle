import java.util.Hashtable;

public class InstructionDecode {
	
	
	public Hashtable<String,Object> InstDecode(String instructionFetched, int PC){
		/*
		 * input: the instruction
		 * output: control signals, readdata1 (as int), readdata2 (as int), PC passed on from instruction fetched (getter)
		 */
		
		//first step: decode all parts. 
		String opcode = instructionFetched.substring(0,6);
		String rs = instructionFetched.substring(6,11);
		String rt = instructionFetched.substring(11,16);
		String rd = instructionFetched.substring(16,21);
		String shamt = instructionFetched.substring(21,26);
		String funct = instructionFetched.substring(26,32);
		String immNotExtend = instructionFetched.substring(16,32);
		
		
		Hashtable<String,String> signals = ContUnit(opcode);
		
		
		
		
	}
	
	
	
	
	//helpers
	
	//but this one is called independently
	public String SignExtend(String immNotExtend) {
		/**input 16 bit immiedaite
		 * output: sign extend
		 */
		String extended = (immNotExtend.charAt(0)=='0')?("0000000000000000"+immNotExtend) : ("1111111111111111"+immNotExtend) ;
		
		return extended;
	}
	
	public Hashtable<String,String> ContUnit(String opcode){
		/*
		 * input: opcode from the instdecode
		 * output: all 8 ALU control signals
		 */
		
		Hashtable<String,String> signals = new Hashtable<String,String>();
		
		switch(opcode) {
		    //R TYPE
			case "000000": 
				signals.put("RegDst","1");
				signals.put("Branch","0");
				signals.put("MemRead","0");
				signals.put("MemtoReg","0");
				signals.put("ALUOp","10");
				signals.put("MemWrite","0");
				signals.put("ALUSrc","0");
				signals.put("RegWrite","1");
				break;
			
			//LW
			case "100011":
				signals.put("RegDst","0");
				signals.put("Branch","0");
				signals.put("MemRead","1");
				signals.put("MemtoReg","1");
				signals.put("ALUOp","00");
				signals.put("MemWrite","0");
				signals.put("ALUSrc","1");
				signals.put("RegWrite","1");
				break; 
			
			//SW
			case "101011": 
				signals.put("RegDst","x");
				signals.put("Branch","0");
				signals.put("MemRead","0");
				signals.put("MemtoReg","x");
				signals.put("ALUOp","00");
				signals.put("MemWrite","1");
				signals.put("ALUSrc","1");
				signals.put("RegWrite","0");
				break;
				
			//BEQ
			case "000100": 
				signals.put("RegDst","1");
				signals.put("Branch","x");
				signals.put("MemRead","0");
				signals.put("MemtoReg","x");
				signals.put("ALUOp","10");
				signals.put("MemWrite","0");
				signals.put("ALUSrc","0");
				signals.put("RegWrite","0");
				break;
			
			default: CPU.finalOutput+="invalid opcode"; System.out.println("invalid opcode");return null;
		}
		
		return signals;
		
	}
	
	public static void main(String [] args) {
	}
}

import java.util.Hashtable;

public class MemoryAccess {
	/*
	 * Beq
If R(rs)==R(rt)
Pc=Pc+4+BranchAddr where branchadr is signextend


You have to let the control signals decide if it's gonna use the memory or not.

For example, if MemWrite and MemRead are false then you don't call the memory stage.
	 */
	
	
	/*
	 * This is actually the behaviour of the Memory. So, in order to get to know the address we are willing to access in the memory, 
	 * we use the result of adding the base address to the offset which is generated by the ALU. The readdata2 (taken as input) is 
	 * the data we are interested in when we are writing to the memory 
	 * and this value is the output of the method InstDecode. Finally, we need to set the corresponding signals for memory accessing.
	 */
	
	DataMemory DM;
	InstructionFetch IF;
	
	public MemoryAccess(InstructionFetch IF) {
		DM = new DataMemory(256);
		DM.loadValues();
		this.IF = IF;
	}
	
	

	public Hashtable<String, Integer> MemAccess(int ALUResult, int readData2, String SignExtend, int ZeroFlag, int branchAddressResult, 
			String MemWrite, String MemRead, String Branch){

		
		//signals i have: memread, memwrite, PCsrc.
		System.out.println("Memory Access Stage:");
		CPU.finalOutput+="Memory Access Stage: \n";
		String display = "";

		//outputs defined:
		int ALUres = ALUResult;
		int readdata = -1;
		
		//first of all: deal with the PC setting.
		if((ZeroFlag==1)&&Branch.equals("1")) {
			IF.setPC(branchAddressResult);
			//and else leave it as it is.
		}
		
		//now, deal with memory.
		
		String readWrite = MemRead+ MemWrite;
		
		switch(readWrite) {
			//the only 2 cases i care about 
		
			//sw
			case "01": DM.writeData(readData2, ALUres); display+="No data was read from memory but data was written.\n";break;
			
			//lw
			case "10": readdata = DM.readData(ALUres);  display+="No data was written to memory but data was read.\n";break;
			
			default:  display+="No data was read from or written to memory.\n";break;
		}
		
		Hashtable<String, Integer> ret = new Hashtable<String,Integer>();
		ret.put("ALUresult",ALUres);
		ret.put("ReadDataFromMem", readdata);
		
		
		display+=("Inputs of MemAccess: (A) ALUResult: " + ALUResult + " | (B) readData2: " +  readData2 + " | (C) SignExtend: " + SignExtend + 
				" | (D) ZeroFlag: " + ZeroFlag + " | (E) branchAddressResult: " + branchAddressResult + " | (F) MemWrite: " + MemWrite + 
				" | (G) MemRead: " + MemRead + " | (H) Branch: " + Branch + "\nOutputs of MemAccess: (A) ALUresult: " + ALUres + " | (B) readDataFromMem: "
				+readdata+"\n-------------------------------------------------------------------------------");		
		
		System.out.println(display);
		CPU.finalOutput+=(display+"\n");
		
		return ret;
		//output ALUresult and readdata2 to see in WB which will be written back to register.
	}

}

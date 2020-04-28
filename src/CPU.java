import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class CPU {
	
	public static String finalOutput = ""; //write this to a file.

	//TODO all outputs in binary
	//TODO single cycle implementation explanation + justify path
	//TODO explain format
	//TODO add 3 examples with branching
	//TODO: getters and PC?
	//TODO: explain output
	//TODO: PC setting in the constructor, i use a getter to set the PC variable in every stage as needed, is this ok?

	/*
	 * output to look like the project + all stage outputs
	 */
	
	public static void main(String[] args) {
		
		InstructionFetch IF = new InstructionFetch();
		InstructionDecode ID = new InstructionDecode();
		Execute EXEC = new Execute();
		MemoryAccess M = new MemoryAccess(IF);
		WriteBack WB = new WriteBack(ID);
		
		int i = 0;
		
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
			
			
			
			//clock cycle?
			System.out.println("Clock Cycle " + i);
			i++;
			
			
			
			
			
			//instruction fetch.
			Hashtable<String, Object> IFresult = IF.InstFetch(IF.getPC());
			//outputs
			String instruction = (String) IFresult.get("Instruction");
			int IF_PC = (Integer) IFresult.get("PCby4");
			
			
			
			
			
			
			//instruction decode.
			Hashtable<String,Object> IDresult = ID.InstDecode(instruction,IF_PC);
			//outputs
			Hashtable<String,String> signals = (Hashtable)IDresult.get("signals");
			int readdata1 = (Integer)IDresult.get("readdata1");
			int readdata2 =(Integer)IDresult.get("readdata2");
			int ID_PC   =(Integer)IDresult.get("PCby4"); 
			String funct = (String)IDresult.get("funct"); 
			String extended = (String)IDresult.get("extended");
			String rt = (String)IDresult.get("rt");
			String rd = (String)IDresult.get("rd");
			//discrete signals
			String regDst = (String) signals.get("RegDst");
			String Branch = (String)signals.get("Branch");
			String MemRead = (String)signals.get("MemRead");
			String MemtoReg = (String)signals.get("MemtoReg");
			String ALUOp = (String)signals.get("ALUOp");
			String MemWrite = (String)signals.get("MemWrite");
			String ALUSrc = (String)signals.get("ALUSrc");
			String RegWrite = (String)signals.get("RegWrite");
			
			
			
			
			
			//execute.
			Hashtable<String,Object> EXECresult = EXEC.Execute(ALUOp,ALUSrc,readdata1,readdata2,extended, ID_PC,funct);
			//outputs
			int ALUresult = (Integer) EXECresult.get("ALUresult");
			int ZFlag = (Integer) EXECresult.get("ZFlag");
			int branchAddressResult = (Integer) EXECresult.get("branchAddressResult");
			int EXEC_PC = (Integer) EXECresult.get("PCby4");
			
			
			
			
			
			
			
			//Memory Access.
			Hashtable<String, Integer> Mresult = M.MemAccess(ALUresult, readdata2, extended, ZFlag, branchAddressResult, 
					 MemWrite,  MemRead,  Branch);
			//outputs
			ALUresult = (Integer) Mresult.get("ALUresult");
			int ReadDataFromMem = (Integer) Mresult.get("ReadDataFromMem");
			
			
			
			
			
			
			
			//Write Back.
			int writtenData = WB.writeBack(ALUresult, ReadDataFromMem,  MemtoReg, regDst, RegWrite, rt, rd);
			
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

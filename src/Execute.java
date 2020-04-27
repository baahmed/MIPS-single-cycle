import java.util.Hashtable;

public class Execute {
	
	/*
	 * I'm not really sure where to add the pc+4 to the branch address, in the memory stage or the execution stage? Since I 
	 * take the branch address as input to the MemAccess method ,do I update the pc to pc+4+branch address here according to 
	 * branch and zero flag and take the pc+4 as input to MemAccess as well or is this wrong?? ccorrect
	 */

	//1) You should multiply by 4 the sign extended value.

	//2) You use the sign extended value in the execute. 
	
	//TAKE CARE: JAVA DOES NOT SUPPORT 2S COMPLEMENT!
	
	/*
	 * By looking at the data-path figure shown above, the PC+4 is the output from the instruction fetch stage and you will need to use this value 
	 * in the execute stage in order to add it to the Branch Address (The adder found above the ALU in the execute stage). 
	 * In the execute method, you will handle the implementation of the ALU as well as the Branch Address which needs the PC+4 (required as an input).
	 * 
	 *  do method ALU control in Execute class ?


	 */
	
	//find the ALU 4 bits operation here

	
	
	public Hashtable<String, Object> Execute(String ALUop, String ALUsrc, int readData1, int readData2, String signExtend, int PCby4, String funct){
		/*
		 * output: ALU result, zero flag, branchAddressResult, readdata2, PCby4
		 * 
		 */
		
	}
}

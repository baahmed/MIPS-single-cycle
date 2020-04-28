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
		Hashtable<String, Object> ret = new Hashtable<String, Object>();
		
		//take it part by part
		
		//first of all: calculating the branch address.
		/*
		 * 
		 */
		
		//deal with the immediate
		String unsigned = signExtend.substring(17,32);
		int number = Integer.parseInt(unsigned,2);
				
		if(signExtend.charAt(0)=='1') {
			number=number*-1;
		}
		
		int ALUnum = number; //to be used as immediate for ALU.
		
		//in case of branch address - we need not worry about the 32nd bit. Java's range is signed, just like our application. Please not that
		//java uses 2s complement.
		int branchAdd = number << 2; //shift the number obtained by 2 bits.
		
		
		
		
		int operand1 = readData1;
		int operand2 = (ALUsrc=="0")? readData2 : ALUnum;
		
		
		//do ALUControl!
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return ret;
	}
	
	public static void main(String[] args) {
		
		String binaryString = Integer.toBinaryString(2);
		String withLeadingZeros = String.format("%8s", binaryString).replace(' ', '0');
		//System.out.println(withLeadingZeros);
		
		int num = -2147483647;
		num = num <<2;
		
		
		System.out.println(Integer.toBinaryString(-2147483647));

	}
}

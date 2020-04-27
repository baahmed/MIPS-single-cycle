import java.lang.reflect.Array;
import java.util.Arrays;

public class InstructionMemory {

	/* @author: Basant Mounir
	 * 
	 * description of this item:
	 * (1) it is READ ONLY!
	 * (2) we should be allowed to load 32 BIT INSTRUCTIONS to it, as strings of 0s and 1s.
	 * (3) with each fetch, PC+=4
	 * (4) there are 3 permissible formats. We do it through decoding anyway
	 */
	
	private int PC; //program counter. Tells me the address of the instruction to be executed.
	private String[] memoryArray; //array to keep the actual instructions - all strings of 0s and 1s of length 32.
	private int lastFreeByte; //to start writing instructions into the last free space
	
	
	
	
	public InstructionMemory(int memorySize) {
		
		//I take the size of the memory IN WORDS as a variable input since it was not specified in the description.
		memoryArray = new String[memorySize*4];
		
		//assumed I start at the very first instruction.
		PC = 0;
		
		lastFreeByte = 0;
		
		
	}
	
	
	//to be able to add instructions to the memory.
	public boolean addInstruction(String instr) {
	
		
		if(instr.length() != 32) {
			System.out.println("Incorrect instruction size!");
			CPU.finalOutput+="Incorrect instruction size!\n";
			return false;
		}
		
		else {
			
			for (int i = 0; i<4;i++) {
				memoryArray[lastFreeByte] = instr.substring(i*8, (i*8)+8);
				lastFreeByte++;
			}
//			System.out.println("Instruction successfully stored.");
//			System.out.println("Memory state:");
//			System.out.println(Arrays.toString(memoryArray));
//			System.out.println("-------------------------------------------------------------------------------");
			return true;
		}
	}
	
	//to retrieve an instruction @ the PC.
	public String getInstruction() {
		
		if(memoryArray[PC]==null) {
			return null;
		}
	
		String ret =  "";
		
		for (int i = 0; i<4;i++) {
			ret += memoryArray[PC+i];
		}
		
//		System.out.println("Instruction successfully read.");
//		System.out.println("Memory state:");
//		System.out.println(Arrays.toString(memoryArray));
//		System.out.println("-------------------------------------------------------------------------------");
		
		return ret;
	}
	
	
	//to make sure we update it with each fetch.
	public void setPC(int PC) {
		this.PC = PC;
	}
	
	public int getPC() {
		return PC;
	}
	
	
	public void loadProgram() {
		//step 1: load the instruction memory.
		//NOTE: we read the instruction from right to left, as per the task and http://www-inst.eecs.berkeley.edu/~cs61c/resources/MIPS_Green_Sheet.pdf
		///to decode it.
		
		/* load an add
		 * example: 000000 01001 01010 01000 00000 100000
		 * should be: add $8, $9, $10
		 * add contents of 9 and 10 and store in 8.
		 * source: https://en.wikibooks.org/wiki/MIPS_Assembly/MIPS_Details
		 */
		
		this.addInstruction("10000000000010000101001001000000");
		
		
		/*load a subtract
		/*example: 000000 01000 00011 00010 00000 100010
		 *should be: SUB $2, $8, $3
		 *Subtract the contents of $3 from $8 and store it in $2
		 *source: https://www3.ntu.edu.sg/home/smitha/FYP_Gerald/instruction.html
		 */
		
		addInstruction("10001000000000100001101000000000");
	
		
		/*load a beq
		 * example: 000100 01011 01101 0000000000010000
		 * should be: BEQ $11, 13, address16
		 * should check for contents of regs and if equal go to: byte address: 16*4 = 64 + current address + 4
		 * if not equal: PC becomes PC+4
		 * source: https://stackoverflow.com/questions/21802457/mips-calculating-beq-into-hexadecimal-machine-code 
		 */
		
		addInstruction("00000000000100000110101011000100");

		
		/*load a lw
		 * example: 100011 00001 00011 0000000000000100
		 * should be: LW $3, 4($1)
		 * so take content of fourth plus address in $1, then load to reg 3.
		 * source: PA5
		 */
		
		addInstruction("00000000000001000001100001100011");
		
		
		/*load a sw
		 *example: 101011 00001 00011 0000000000000100
		 *should be: SW $3, 4($1)
		 *so take content of $3, and store it in the address within $1 plus 4.
		 *source: PA5
		 */
		
		addInstruction("00000000000001000001100001101011");
		
		 //TODO: ADD these: and, or, slt 
		
		//to verify
		displayState();
		
	}
	
	public void displayState() {
		
		System.out.println("Memory state:");
		System.out.println(Arrays.toString(memoryArray));
		System.out.println("-------------------------------------------------------------------------------");
		CPU.finalOutput+=("Memory state: " +"\n"+Arrays.toString(memoryArray) + "\n----------"
				+ "---------------------------------------------------------------------\n");
	}
	
	public boolean done() {
		boolean ret;
		ret = (getInstruction()==null)? true : false;
		return ret;
	}
	
	
}

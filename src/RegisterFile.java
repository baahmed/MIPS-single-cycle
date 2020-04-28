import java.util.Arrays;

public class RegisterFile {
	
	int rs; //25-21 source
	int rt; //20-16 source or destination
	int rd; //15-11 destination, for writes
	int [] registers; //to keep track of 32 registers and their data
	boolean writeControl;
	
	
	public RegisterFile() {
		registers = new int[32]; //initialize array of registers
		//by default, all constituents are 0.
	}
	
	
	public void loadValueToRegister(int value, int register) {
		
		if (register!=0) {
			registers[register] = value;
		}
		
		else {
			System.out.println("Cannot write to register 0.");
		}
	}
	
	public int readValueAt(int value) {
		return registers[value];
	}
	
	public void showState() {
		System.out.println(Arrays.toString(registers));
		System.out.println("-------------------------------------------------------------------------------");

		CPU.finalOutput+= (Arrays.toString(registers)+ "\n-------------------------------------------------------------------------------\n");
	}
	
	public int[] readValues(int rs, int rt, int rd) {
		int[] ret = new int[2];
		ret[0] = readValueAt(rs);
		ret[1] = readValueAt(rt);
		return ret;
	}
	
}

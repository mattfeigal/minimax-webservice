package minimax.server;

import java.util.Arrays;

public class Board implements Cloneable {
	private int size;
	private int turn;
	private Integer[] state;
	
	// LV int cannot be null. Converted to Integer. 
	public Board(int size, int turn, Integer[] state) {
		this.size = size;
		this.turn = turn;
		this.state = state;
	}
	
	public String getHash() {
		return Arrays.toString(state); 
	}
	
	// MF Not sure why String getHash is needed, try to replace it with simple int[] getState.
	// LV Is needed to use as a key for a dictionary to enable caching. Each hash represents a unique board state.
	public Integer[] getState() {
		return state;
	}
	
	// MF If an error, this returns NULL. I cast it to an integer for now, not sure if that is the right way to go....  will that just be 0, and does 0 mean something else? 
	// LV Yeah, well. Let's just say JavaScript is less picky about these things. I'll write a wrapper func.
	// LV Also, let's just let the damn thing return an Integer.
	public Integer getCell(int r, int c) {
		if (r >= 0 && c >= 0 && r < size && c < size) {
			try { return (state[(r*size)+c]); } catch(Exception err) { return null; }
		} else { return Minimax.OFF_BOARD; }
	}
	
	// LV Yeah, well. Kinda ugly. But it's 22.19, I've had half a bottle of wiskey and the karaoke is just about starting to get interesting. Feel free to help this sorry piece of code out of its misery.
	public Integer getNNCell(int r, int c) {
		Integer ret = getCell(r,c);
		if (ret == null) {
			return 3;
		}
		return ret;
	}

	public boolean playColumn(int c) {
		if (this.getCell(0,c) == null) {
			int i = this.size - 1;
			while (this.getCell(i,c) != null) {
				i--;
			}
			this.state[i*this.size+c] = this.turn;
		
			this.turn = Math.abs(this.turn-1);
			return true;
		} else { return false; }
	};
	
	public boolean isFull() {
		for (int n = 0; n < (size^2); ++n) {
			if (state[n]==null) { return false; }
		}
		return true;
	}

	public int getTurn() {
		return turn;
	}
	
	public int getSize() {
		return size;
	}
	
	public Board clone() {
        return new Board(size, turn, state.clone());
    } 
}

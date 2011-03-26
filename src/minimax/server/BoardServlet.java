package minimax.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardServlet extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// parse parameters.
		int size = Integer.parseInt(req.getParameter("size"));
		int turn = Integer.parseInt(req.getParameter("turn"));
		String[] stateString = req.getParameter("state").split(",");
		Integer[] state = new Integer[(int) Math.pow(size,2)];
		for (int i = 0; i < Math.pow(size,2); i++) {
			if (stateString[i] == null || stateString[i].isEmpty()) {
				state[i] = null; 
			} else { 
				state[i] = Integer.parseInt(stateString[i]);
			} 
		}
		
		// LV Create a board based on the input.
		Board b = new Board(size, turn, state);
		// LV Run Minimax on that board.
		Integer[] m = Minimax.minMax(turn, b, 6);
		
		// MF Simple so far - just want to give a response
		// LV Changed this to return next move only. Client can figure out how this affects the board state.
		String message = "["+m[0]+","+m[1]+"]";
		
		resp.setContentType("text/plain");
        resp.setContentLength(message.length());
        PrintWriter out = resp.getWriter();
        out.println(message);
        out.close();
        out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
}

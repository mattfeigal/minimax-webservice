package minimax.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface BoardServiceAsync {
	void boardServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}

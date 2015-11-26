package server;

/*Interface for default server*/
public interface DefaultServerInterface {
	public void start();
	public boolean openConnection();
	public void handleSession();
	public void closeSession();
}

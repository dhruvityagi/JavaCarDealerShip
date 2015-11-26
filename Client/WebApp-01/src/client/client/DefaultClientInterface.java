package client.client;

public interface DefaultClientInterface {
	public boolean openConnection();
	public ReturnTypeVariety handleSession(String id, String state, String car);
    public void closeSession();
}

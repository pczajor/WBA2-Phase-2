package grizzlyserver;


import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyServerFactory;

public class server {
public static void main(String[] args) throws Exception {

String url = (args.length > 0) ? args[0] : "http://localhost:4434";

final SelectorThread srv = GrizzlyServerFactory.create(url);

Runtime.getRuntime().addShutdownHook(new Thread() {
@Override
public void run() {
srv.stopEndpoint();
}
});

}
}
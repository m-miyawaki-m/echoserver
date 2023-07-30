package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class EchoServerJava {
	public static void main(String[] args) throws Exception {
		// ログディレクトリのパスを指定します
		String logDirectory = EchoServerProperty.LOG_DIRECTORY;
		// LogWriterのインスタンスを作成します
		LogWriter logWriter = new LogWriter(logDirectory);

		// サーバーインスタンスを作成し、ポート番号 18888 でリスニングします
		Server server = new Server(18888);
		server.setHandler(new EchoHandler(logWriter));

		System.out.println("start http listening :18888");
		// サーバーを起動し、リクエストを待ち受けます
		server.start();
		// サーバーが終了するまで待機します
		server.join();
	}

	public static class EchoHandler extends AbstractHandler {
		private final LogWriter logWriter;

		public EchoHandler(LogWriter logWriter) {
			this.logWriter = logWriter;
		}

		@Override
		public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {
		    // リクエストをダンプしてコンソールに表示します
		    byte[] requestDump = getDumpRequest(request);
		    System.out.println(new String(requestDump));

		    // レスポンスを設定して、リクエストの内容をそのまま返します
		    response.setContentType("text/plain");
		    response.setStatus(HttpServletResponse.SC_OK);
		    response.getWriter().write(new String(requestDump));

		    // リクエストが処理されたことをマークします
		    baseRequest.setHandled(true);

		    // リクエストとレスポンスをログファイルに出力します
		    byte[] responseDump = getResponseDump(response);
		    logWriter.logRequestAndResponse(requestDump, responseDump);

		}

		// リクエストをダンプするためのヘルパーメソッド
		private byte[] getDumpRequest(HttpServletRequest req) throws IOException {
			StringBuilder builder = new StringBuilder();
			builder.append(req.getMethod()).append(" ").append(req.getRequestURI()).append(" ")
					.append(req.getProtocol()).append("\n");

			// リクエストヘッダーをダンプします
			Enumeration<String> headerNames = req.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				builder.append(name).append(": ").append(req.getHeader(name)).append("\n");
			}

			builder.append("\n");

			// リクエストボディをダンプします
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line).append("\n");
			}

			// 文字列をバイト配列に変換して返します
			return builder.toString().getBytes();
		}
		
		// レスポンスをダンプするためのヘルパーメソッド
		private byte[] getResponseDump(HttpServletResponse res) throws IOException {
		    StringBuilder builder = new StringBuilder();
		    builder.append(res.getStatus()).append(" ").append(res.getContentType()).append("\n");

		    // レスポンスヘッダーをダンプします
		    Collection<String> headerNames = res.getHeaderNames();
		    for (String name : headerNames) {
		        List<String> values = (List<String>) res.getHeaders(name);
		        for (String value : values) {
		            builder.append(name).append(": ").append(value).append("\n");
		        }
		    }

		    builder.append("\n");

		    // レスポンスボディをダンプします
		    PrintWriter writer = res.getWriter();
		    writer.flush(); // バッファをフラッシュしてからダンプ
		    return builder.toString().getBytes();
		}
	}

}

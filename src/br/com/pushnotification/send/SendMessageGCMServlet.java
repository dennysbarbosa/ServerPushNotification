package br.com.pushnotification.send;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pushnotification.persistence.DeviceRepository;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.sun.xml.internal.ws.client.SenderException;



@WebServlet("/SendMessageGCMServlet")
	public class SendMessageGCMServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException,
				IOException {
			String serverDir = getServletContext().getRealPath("/");
			String message = request.getParameter("message");
			HashMap<String, String> devices = DeviceRepository
					.getDevices(serverDir);
			PrintWriter out = response.getWriter();
			out.print("<html><body><h1>Mensagens enviadas:</h1>");
			Set<String> deviceKeys = devices.keySet();
			for (String key : deviceKeys) {
				Sender sender = new Sender(
						"AIzaSyC-Na5Ep_wFpZftJntzYHCfNVdjv1eEjRk");
				Message gcmMessage = new Message.Builder().addData("mensagem",
						message).build();
				Result result = sender.send(gcmMessage, devices.get(key), 5);
				String msgId = result.getMessageId();
				if (msgId != null) {
					out.print("<br/>Mensagem enviada para: " + devices.get(key));
				} else {
					out.print("<br/>Erro ao enviar mensagem para: "
							+ devices.get(key) + "<br>"
							+ result.getErrorCodeName());
				}
			}
			out.print("</body></html>");
		}
	}



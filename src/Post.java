import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Post
  extends HttpServlet
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String ReqDate = "";
  String TicketNumber = "";
  String TWNLDIMSI = "";
  String TWNLDMSISDN = "";
  String S2TIMSI = "";
  String VLNCountry = "";
  String GPRSStatus = "";
  String IPP = "";
  String ReqStatus = "";
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter pw = response.getWriter();
    try
    {
      this.ReqDate = request.getParameter("Req_DateTime");
      this.TWNLDIMSI = request.getParameter("TWNLD_IMSI");
      this.TWNLDMSISDN = request.getParameter("TWNLD_MSISDN");
      this.ReqStatus = request.getParameter("Req_Status");
      this.IPP = request.getParameter("IPP");
      URL url = new URL("http://" + this.IPP + "/CHTMOrder/QueryOrder");
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setRequestMethod("POST");
      conn.setUseCaches(false);
      conn.setAllowUserInteraction(true);
      HttpURLConnection.setFollowRedirects(true);
      conn.setInstanceFollowRedirects(true);
      String msg = "<?xml version='1.0' encoding='UTF-8'?><ActivationRsp>";
      
      msg = msg + "<Req_DateTime>" + this.ReqDate + "</Req_DateTime>";
      msg = msg + "<TWNLD_IMSI>" + this.TWNLDIMSI + "</TWNLD_IMSI>";
      msg = msg + "<TWNLD_MSISDN>" + this.TWNLDMSISDN + "</TWNLD_MSISDN>";
      msg = msg + "<Req_Status>" + this.ReqStatus + "</Req_Status>";
      msg = msg + "</ActivationRsp>";
      
      DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
      
      dos.writeBytes(msg);
      
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
      String line;
      while ((line = rd.readLine()) != null) {
        pw.println(line);
      }
      rd.close();
      pw.flush();
    }
    finally
    {
      pw.close();
    }
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }
  
  public String getServletInfo()
  {
    return "Short description";
  }
}

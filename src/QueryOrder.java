import ProcessS2T.PS2T;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class QueryOrder
  extends HttpServlet
{
  public Statement Tempsm = null;
  public PS2T s2t = new PS2T();
  static ResultSet Temprs = null;
  static ResultSet TempRtA = null;
  static String cFileName = "";
  static String c910SEQ = "";
  static String cFileID = "";
  static String cVLNCountryOLD = "";
  static String cVLNSGPOLD = "";
  static String cVLNTHAOLD = "";
  static String cWorkOrderNBR = "";
  static String cServiceOrderNBR = "";
  static String sMNOName = "";
  static String sXML = "";
  static String cRCode = "";
  static String sSubCode = "";
  static String sStepNo = "";
  static String sDATE = "";
  static String sCount = "";
  static String sMap = "";
  static String sCMHKLOGID = "";
  static String sTypeCode = "";
  static String sDataType = "";
  static String sValue = "";
  static String sSql = "";
  static String Sdate = "";
  static String sSTATUS = "";
  static String cMSISDNOLD = "";
  static String sMNOSubCode = "950";
  static String cM205OT = "";
  static String cMVLNOLD = "";
  static String cMVLN = "";
  static String sWSFStatus = "";
  static String sWSFDStatus = "";
  static String dReqDate = "";
  static String cS2TIMSI = "";
  static String cReqStatus = "";
  static String cVLNCountry = "";
  static String cVLNSGP = "";
  static String cVLNTHA = "";
  static String cTicketNumber = "";
  static String cTWNLDIMSI = "";
  static String cTWNLDMSISDN = "";
  static String Sparam = "";
  static String Se = "";
  static String smsi = "";
  static String sError = "";
  static String sVln = "";
  static String cS2TMSISDN = "";
  static String cCountryName = "";
  static String cVLNc = "";
  static String cGPRSStatus = "";
  static String csta = "";
  static String desc = "";
  static String cGPRS = "";
  static String cOldTWNLDMSISDN = "";
  static String cOLDS2TMSISDN = "";
  static Vector<String> vln = new Vector();
  static Vector<String> Tmpvln = new Vector();
  static FileWriter fw = null;
  static BufferedWriter bw = null;
  static boolean ba;
  static Runtime runtime = Runtime.getRuntime();
  int i;
  int n = 0;
  int y;
  int l;
  int f;
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Exception
  {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    

    int iError = 0;
    ResultSet Trt = null;
    
    ResultSet TempRt = null;
    String sAllVln = "";String sMd = "";String cSubscrId = "";String sFile = "";String VARREALMSG = "";
    
    dReqDate = "";
    cTicketNumber = "";
    cTWNLDIMSI = "";
    cTWNLDMSISDN = "";
    cS2TMSISDN = "";
    cS2TIMSI = "";
    cReqStatus = "";
    cVLNCountry = "";
    cGPRSStatus = "";
    sWSFStatus = "";
    sWSFDStatus = "";
    VARREALMSG = "";
    sError = "";
    cOldTWNLDMSISDN = "";
    cOLDS2TMSISDN = "";
    
    out.println("<?xml version='1.0' encoding='UTF-8'?>");
    out.println("<ActivationRsp>");
    try
    {
      sFile = getServletContext().getRealPath("/") + this.s2t.Date_Format() + this.s2t.Date_Format_Time() + ".txt";
      fw = new FileWriter(sFile, true);
      
      bw = new BufferedWriter(fw);
      Sparam = "";
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = dbf.newDocumentBuilder();
      Document doc = builder.parse(new InputSource(request.getReader()));
      doc.normalize();
      for (this.i = 0; this.i < doc.getDocumentElement().getChildNodes().getLength(); this.i += 1)
      {
        String TagName = doc.getDocumentElement().getChildNodes().item(this.i).getNodeName();
        if (TagName.equals("Req_DateTime"))
        {
          NodeList nodes = doc.getElementsByTagName("Req_DateTime");
          if (nodes.item(0).getFirstChild() != null) {
            dReqDate = nodes.item(0).getFirstChild().getNodeValue();
          }
          bw.write("TagName:Req_DateTime=" + dReqDate);
          bw.newLine();
          Sparam = nodes.item(0).getNodeName() + "=" + dReqDate;
        }
        else if (TagName.equals("TWNLD_IMSI"))
        {
          NodeList nodes = doc.getElementsByTagName("TWNLD_IMSI");
          if (nodes.item(0).getFirstChild() != null) {
            cTWNLDIMSI = nodes.item(0).getFirstChild().getNodeValue();
          }
          bw.write("TagName:TWNLD_IMSI=" + cTWNLDIMSI);
          bw.newLine();
          Sparam = Sparam + "," + nodes.item(0).getNodeName() + "=" + cTWNLDIMSI;
        }
        else if (TagName.equals("TWNLD_MSISDN"))
        {
          NodeList nodes = doc.getElementsByTagName("TWNLD_MSISDN");
          if (nodes.item(0).getFirstChild() != null) {
            cTWNLDMSISDN = nodes.item(0).getFirstChild().getNodeValue();
          }
          bw.write("TagName:TWNLD_MSISDN=" + cTWNLDMSISDN);
          bw.newLine();
          Sparam = Sparam + "," + nodes.item(0).getNodeName() + "=" + cTWNLDMSISDN;
        }
        else if (TagName.equals("Req_Status"))
        {
          NodeList nodes = doc.getElementsByTagName("Req_Status");
          if (nodes.item(0).getFirstChild() != null) {
            cReqStatus = nodes.item(0).getFirstChild().getNodeValue();
          }
          bw.write("TagName:Req_Status=" + cReqStatus);
          bw.newLine();
          Sparam = Sparam + "," + nodes.item(0).getNodeName() + "=" + cReqStatus;
        }
      }
      ba = ConnDB();
      bw.write("Connect Database:" + ba);
      bw.newLine();
      if (ba == true)
      {
        if (!dReqDate.equals(""))
        {
          Sdate = dReqDate.substring(4, 6) + "/" + dReqDate.substring(6, 8) + "/" + dReqDate.substring(0, 4) + " " + dReqDate.substring(8, 10) + ":" + dReqDate.substring(10, 12) + ":" + dReqDate.substring(12, 14);
          
          vln.removeAllElements();
          if (cReqStatus.equals("97")) {
            Query_TWNLD_IMSI(out);
          } else if (cReqStatus.equals("98")) {
            Query_TWNLD_MSISDM(out);
          } else {
            iError = 1;
          }
          if ((iError == 0) && (cRCode.equals("000")))
          {
            out.println("<GPRS_Status>");
            out.println(cGPRS);
            out.println("</GPRS_Status>");
            out.println("<VLN>");
            if (vln.size() > 0)
            {
              sAllVln = "";
              vln.firstElement();
              for (this.n = 0; this.n < vln.size(); this.n += 1)
              {
                sVln = (String)vln.get(this.n);
                this.y = sVln.indexOf(",");
                sVln = sVln.substring(this.y + 1, sVln.length());
                this.y = sVln.indexOf(",");
                cVLNc = sVln.substring(0, this.y);
                sMd = sVln.substring(sVln.length() - 1, sVln.length());
                sVln = sVln.substring(this.y + 1, sVln.length() - 2);
                sAllVln = sAllVln + sVln + cVLNc + ",";
              }
              sAllVln = sAllVln.substring(0, sAllVln.length() - 1);
              out.println(sAllVln);
            }
            out.println("</VLN>");
            out.println("<Addon_Service>");
            out.println("</Addon_Service>");
          }
          if ((iError != 0) && (!cRCode.equals("000")))
          {
            out.println("<GPRS_Status>");
            out.println(cGPRS);
            out.println("</GPRS_Status>");
            out.println("<VLN>");
            out.println("</VLN>");
            out.println("<Addon_Service>");
            out.println("</Addon_Service>");
          }
        }
        else
        {
          All_Ok(out, "110");
        }
      }
      else
      {
        out.println("<Return_Code>");
        out.println("</Return_Code>");
        out.println("<Return_DateTime>");
        out.println(this.s2t.Date_Format() + this.s2t.Date_Format_Time());
        out.println("</Return_DateTime>");
        desc = "Connection False";
      }
    }
    catch (SQLException ex)
    {
      StringWriter s = new StringWriter();
      ex.printStackTrace(new PrintWriter(s));
      bw.write("JAVA Error:" + s.toString());
      bw.newLine();
      

      All_Ok(out, "600");
    }
    catch (Exception ex)
    {
      StringWriter s = new StringWriter();
      ex.printStackTrace(new PrintWriter(s));
      bw.write("JAVA Error:" + s.toString());
      bw.newLine();
      

      All_Ok(out, "600");
    }
    finally
    {
      if (ba == true) {
        this.s2t.Close();
      }
      bw.close();
      out.println("<Return_MSG>");
      out.println(desc);
      out.println("</Return_MSG>");
      out.println("</ActivationRsp>");
      out.close();
      vln.clear();
      Tmpvln.clear();
    }
  }
  
  public boolean ConnDB()
    throws SQLException, ClassNotFoundException
  {
    this.s2t.IP = "10.42.1.80";
    this.s2t.PORT = "1521";
    this.s2t.SID = "S2TBS1";
    this.s2t.un = "s2tbsadm";
    this.s2t.pw = "s2tbsadm";
    boolean bconn = this.s2t.ConnDB();
    if (bconn == true) {
      return true;
    }
    return false;
  }
  
  public void All_Ok(PrintWriter outA, String rcode)
    throws SQLException, InterruptedException, Exception
  {
    bw.write("Return_Code:" + rcode);
    bw.newLine();
    cRCode = rcode;
    outA.println("<Return_Code>");
    outA.println(rcode);
    outA.println("</Return_Code>");
    outA.println("<Return_DateTime>");
    outA.println(this.s2t.Date_Format() + this.s2t.Date_Format_Time());
    outA.println("</Return_DateTime>");
    desc = Change_Result(outA, rcode);
  }
  
  public String Change_Result(PrintWriter outC, String sDecs)
    throws SQLException
  {
    String sD = "";
    Temprs = null;
    sSql = "Select describe from S2T_TB_RESULT where RESULT_FLAG ='" + sDecs + "'";
    

    Temprs = this.s2t.Query(sSql);
    while (Temprs.next()) {
      sD = Temprs.getString("describe");
    }
    return sD;
  }
  
  public void PROVLOG()
    throws Exception
  {
    int iCount = 0;
    sDATE = this.s2t.Date_Format();
    sCount = "";
    int j = 0;
    TempRtA = null;
    sCMHKLOGID = "";
    sSql = "select mnologid.nextval as ab from dual";
    TempRtA = this.s2t.Query(sSql);
    while (TempRtA.next()) {
      sCMHKLOGID = TempRtA.getString("ab");
    }
    TempRtA.close();
    sSql = "insert into PROVLOG(LOGID,MNOSUBCODE,MNOTICKETNO,REQTIME,CONTENT,STEP) values(" + sCMHKLOGID + ",'" + sMNOSubCode + "','" + cTicketNumber + "',sysdate" + ",'" + Sparam + "','')";
    

    this.s2t.Inster(sSql);
    Sparam = "";
    TempRtA = null;
    
    sSql = "select currentseq,count(currentseq) as ab from seqrec where MNOSUBCODE='" + sMNOSubCode + "' and currentdate='" + sDATE + "' group by currentseq";
    TempRtA = this.s2t.Query(sSql);
    while (TempRtA.next())
    {
      sCount = TempRtA.getString("currentseq");
      j = TempRtA.getInt("ab");
    }
    TempRtA.close();
    if (j > 0)
    {
      iCount = Integer.parseInt(sCount);
      iCount++;
      sSql = "update seqrec set currentseq=" + Integer.toString(iCount) + " where MNOSUBCODE='" + sMNOSubCode + "'";
      this.s2t.Update(sSql);
    }
    else
    {
      iCount = 1;
      sSql = "delete seqrec where MNOSUBCODE='" + sMNOSubCode + "'";
      this.s2t.Delete(sSql);
      sSql = "insert into seqrec(MNOSUBCODE,currentdate,currentseq) values('" + sMNOSubCode + "','" + sDATE + "'," + Integer.toString(iCount) + ")";
      this.s2t.Inster(sSql);
      sCount = Integer.toString(iCount);
    }
    for (this.i = sCount.length(); this.i < 3; this.i += 1) {
      sCount = "0" + sCount;
    }
  }
  
  public void WO_SYNC_FILE(PrintWriter outA, String sSFStatus)
    throws SQLException, Exception
  {
    sDATE = this.s2t.Date_Format();
    c910SEQ = sDATE + sCount;
    cFileName = "S2TCI" + c910SEQ + ".950";
    cFileID = "";
    Temprs = null;
    sSql = "select S2T_SQ_FILE_CNTRL.NEXTVAL as ab from dual";
    Temprs = this.s2t.Query(sSql);
    while (Temprs.next()) {
      cFileID = Temprs.getString("ab");
    }
    sSql = "INSERT INTO S2T_TB_TYPEB_WO_SYNC_FILE (FILE_ID,FILE_NAME,FILE_SEND_DATE,FILE_SEQ,CMCC_BRANCH_ID,FILE_CREATE_DATE,STATUS) VALUES (" + cFileID + ",'" + cFileName + "','" + dReqDate.substring(0, 8) + "','" + c910SEQ.substring(8, 11) + "','950',sysdate,'" + sSFStatus + "')";
    



    bw.write("WO_SYNC_FILE:" + sSql);
    bw.newLine();
    this.s2t.Inster(sSql);
  }
  
  public void WO_SYNC_FILE_DTL(PrintWriter outA, String sSFDStatus)
    throws SQLException, IOException
  {
    int ix = 0;
    String sVl = "";
    cWorkOrderNBR = "";
    Temprs = this.s2t.Query("select S2T_SQ_WORK_ORDER.nextval as ab from dual");
    while (Temprs.next()) {
      cWorkOrderNBR = Temprs.getString("ab");
    }
    Temprs = null;
    cServiceOrderNBR = "";
    Temprs = this.s2t.Query("select S2T_SQ_SERVICE_ORDER.nextval as ab from dual");
    while (Temprs.next()) {
      cServiceOrderNBR = Temprs.getString("ab");
    }
    sSql = "INSERT INTO S2T_TB_TYPB_WO_SYNC_FILE_DTL (WORK_ORDER_NBR,WORK_TYPE, FILE_ID, SEQ_NO, CMCC_OPERATIONDATE, ORIGINAL_CMCC_IMSI,ORIGINAL_CMCC_MSISDN, S2T_IMSI, S2T_MSISDN, FORWARD_TO_HOME_NO, FORWARD_TO_S2T_NO_1, IMSI_FLAG, STATUS, SERVICE_ORDER_NBR, SUBSCR_ID) VALUES (" + cWorkOrderNBR + ",'" + cReqStatus + "'," + cFileID + ",'" + c910SEQ + "',to_date('" + Sdate + "','MM/dd/yyyy HH24:mi:ss'),'" + cTWNLDIMSI + "','+" + cTWNLDMSISDN + "','" + cS2TIMSI + "','" + cS2TMSISDN + "','+" + cTWNLDMSISDN + "','" + cTWNLDMSISDN + "', '2', '" + sSFDStatus + "','" + cServiceOrderNBR + "','" + cTicketNumber + "')";
    






    bw.write("WO_SYNC_FILE_DTL:" + sSql);
    bw.newLine();
    this.s2t.Inster(sSql);
  }
  
  public void Query_TWNLD_IMSI(PrintWriter outA)
    throws SQLException, InterruptedException, Exception
  {
    String cStatus = "";String cVln = "";String cCountryinit = "";String cCountryname = "";
    vln.removeAllElements();
    Temprs = null;
    sSql = "SELECT b.homeimsi as homeimsi, b.imsi as imsi,CASE a.status WHEN '1' THEN '1' WHEN '3' THEN '0' WHEN '4' THEN '2' WHEN '10' THEN '2' END as status,a.servicecode as ab FROM service a,IMSI b WHERE a.serviceid = (SELECT MAX(Serviceid) FROM imsi WHERE homeimsi ='" + cTWNLDIMSI + "')" + " AND a.serviceid=b.serviceid and (a.status=1 or a.status=3)";
    




    bw.write("Query_TWNLD_IMSI:" + sSql);
    bw.newLine();
    Temprs = this.s2t.Query(sSql);
    while (Temprs.next())
    {
      cTWNLDIMSI = Temprs.getString("homeimsi");
      cS2TIMSI = Temprs.getString("imsi");
      cS2TMSISDN = Temprs.getString("ab");
      cStatus = Temprs.getString("status");
    }
    if (!cS2TMSISDN.equals(""))
    {
      Temprs = null;
      sSql = "SELECT  followmenumber FROM followmedata WHERE serviceid = (SELECT MAX(Serviceid) FROM imsi WHERE homeimsi ='" + cTWNLDIMSI + "')";
      
      Temprs = this.s2t.Query(sSql);
      while (Temprs.next()) {
        cTWNLDMSISDN = Temprs.getString("followmenumber");
      }
      Temprs = null;
      sSql = "SELECT b.countryname||','||a.vln||','||b.countryinit||',N' as ab  "
      		+ "FROM vlnnumber a, COUNTRYINITIAL b "
      		+ "WHERE a.vplmnid=b.vplmnid "
      		+ "AND a.serviceid = (SELECT MAX(Serviceid) "
      		+ "						FROM imsi "
      		+ "						WHERE homeimsi ='" + cTWNLDIMSI + "') "
      		+ "AND a.status=1 ";//20160728 add
      


      Temprs = this.s2t.Query(sSql);
      while (Temprs.next())
      {
        cVln = Temprs.getString("ab");
        vln.addElement(cVln);
      }
      Find_GPRS();
      All_Ok(outA, "000");
    }
    else
    {
      cS2TMSISDN = "";
      All_Ok(outA, "211");
    }
    outA.println("<TWNLD_IMSI>");
    outA.println(cTWNLDIMSI);
    outA.println("</TWNLD_IMSI>");
    outA.println("<TWNLD_MSISDN>");
    outA.println(cTWNLDMSISDN);
    outA.println("</TWNLD_MSISDN>");
    outA.println("<S2T_IMSI>");
    outA.println(cS2TIMSI);
    outA.println("</S2T_IMSI>");
    outA.println("<S2T_MSISDN>");
    outA.println(cS2TMSISDN);
    outA.println("</S2T_MSISDN>");
    outA.println("<Status>");
    outA.println(cStatus);
    outA.println("</Status>");
  }
  
  public void Find_GPRS()
    throws IOException, SQLException
  {
    String sG = "";
    cGPRS = "";
    Temprs = null;
    sSql = "SELECT nvl(PDPSUBSID,0) as ab FROM basicprofile WHERE msisdn = '" + cS2TMSISDN + "'";
    bw.write("Find_GPRS:" + sSql);
    bw.newLine();
    Temprs = this.s2t.Query(sSql);
    while (Temprs.next()) {
      sG = Temprs.getString("ab");
    }
    bw.write("GPRS_Values:" + sG);
    bw.newLine();
    if ((sG.equals("0")) || (sG.equals(""))) {
      cGPRS = "0";
    } else {
      cGPRS = "1";
    }
  }
  
  public void Query_TWNLD_MSISDM(PrintWriter outA)
    throws SQLException, InterruptedException, Exception
  {
    String cStatus = "";String cVln = "";String cCountryname = "";String cCountryinit = "";
    vln.removeAllElements();
    Temprs = null;
    sSql = "SELECT b.homeimsi as homeimsi, b.imsi as imsi,CASE a.status WHEN '1' THEN '1' WHEN '3' THEN '0' WHEN '4' THEN '2'  WHEN '10' THEN '2' END as status,a.servicecode as ab FROM service a,IMSI b WHERE a.serviceid = (SELECT Serviceid FROM followmedata WHERE followmenumber ='" + cTWNLDMSISDN + "')" + " AND a.serviceid=b.serviceid and (a.status=1 or a.status=3)";
    





    bw.write("Query_TWNLD_MSISDM:" + sSql);
    bw.newLine();
    Temprs = this.s2t.Query(sSql);
    while (Temprs.next())
    {
      cTWNLDIMSI = Temprs.getString("homeimsi");
      cS2TIMSI = Temprs.getString("imsi");
      cS2TMSISDN = Temprs.getString("ab");
      cStatus = Temprs.getString("status");
    }
    if (!cS2TMSISDN.equals(""))
    {
      Temprs = null;
      sSql = "SELECT b.countryname||','||a.vln||','||b.countryinit||',N' as ab "
      		+ "FROM vlnnumber a, COUNTRYINITIAL b "
      		+ "WHERE a.vplmnid=b.vplmnid "
      		+ "AND a.serviceid = (	SELECT Serviceid "
      		+ "						FROM followmedata "
      		+ "						WHERE followmenumber = '" + cTWNLDMSISDN + "') "
      		+ "AND a.status=1 ";//20160728 add
      

      Temprs = this.s2t.Query(sSql);
      while (Temprs.next())
      {
        cVln = Temprs.getString("ab");
        vln.add(cVln);
      }
      Find_GPRS();
      All_Ok(outA, "000");
    }
    else
    {
      cS2TMSISDN = "";
      All_Ok(outA, "211");
    }
    outA.println("<TWNLD_IMSI>");
    outA.println(cTWNLDIMSI);
    outA.println("</TWNLD_IMSI>");
    outA.println("<TWNLD_MSISDN>");
    outA.println(cTWNLDMSISDN);
    outA.println("</TWNLD_MSISDN>");
    outA.println("<S2T_MSISDN>");
    outA.println(cS2TMSISDN);
    outA.println("</S2T_MSISDN>");
    outA.println("<S2T_IMSI>");
    outA.println(cS2TIMSI);
    outA.println("</S2T_IMSI>");
    outA.println("<Status>");
    outA.println(cStatus);
    outA.println("</Status>");
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try
    {
      processRequest(request, response);
    }
    catch (Exception ex)
    {
      Logger.getLogger(QueryOrder.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try
    {
      processRequest(request, response);
    }
    catch (Exception ex)
    {
      Logger.getLogger(QueryOrder.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public String getServletInfo()
  {
    return "Short description";
  }
}

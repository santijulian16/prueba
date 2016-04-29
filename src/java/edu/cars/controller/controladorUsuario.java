/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cars.controller;

import edu.cars.entity.Datospersonales;
import edu.cars.entity.Rol;
import edu.cars.entity.Usuario;
import edu.cars.facade.DatospersonalesFacade;
import edu.cars.facade.RolFacade;
import edu.cars.facade.UsuarioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author JoseLuis
 */
@Named(value = "controladorUsuario")
@SessionScoped
public class controladorUsuario implements Serializable {

    @Inject
    DatospersonalesFacade datospersonalesFacade;
    @Inject
    UsuarioFacade usuarioFacade;
    @Inject
    RolFacade rolFacade;

    Datospersonales usuarioLogin;
    Usuario miUsuarioLogin;
    private Integer datid;
    private String datnombre;
    private String datapellido;
    private String datipoid;
    private String datnumeroid;
    private String datelefono;
    private String datcorreo;
    private String usulogin;
    private String usupassword;
    private Integer rolid;
    private int estado;
    JasperPrint jasperPrint;
    private List lista1;

    /**
     * Creates a new instance of controladorUsuario
     */
    public controladorUsuario() {
    }

    public Usuario getMiUsuarioLogin() {
        return miUsuarioLogin;
    }

    public void setMiUsuario(Usuario miUsuarioLogin) {
        this.miUsuarioLogin = miUsuarioLogin;
    }

    public Integer getDatid() {
        return datid;
    }

    public void setDatid(Integer datid) {
        this.datid = datid;
    }

    public String getDatnombre() {
        return datnombre;
    }

    public void setDatnombre(String datnombre) {
        this.datnombre = datnombre;
    }

    public String getDatapellido() {
        return datapellido;
    }

    public void setDatapellido(String datapellido) {
        this.datapellido = datapellido;
    }

    public String getDatipoid() {
        return datipoid;
    }

    public void setDatipoid(String datipoid) {
        this.datipoid = datipoid;
    }

    public String getDatnumeroid() {
        return datnumeroid;
    }

    public void setDatnumeroid(String datnumeroid) {
        this.datnumeroid = datnumeroid;
    }

    public String getDatelefono() {
        return datelefono;
    }

    public void setDatelefono(String datelefono) {
        this.datelefono = datelefono;
    }

    public String getDatcorreo() {
        return datcorreo;
    }

    public void setDatcorreo(String datcorreo) {
        this.datcorreo = datcorreo;
    }

    public String getUsulogin() {
        return usulogin;
    }

    public void setUsulogin(String usulogin) {
        this.usulogin = usulogin;
    }

    public String getUsupassword() {
        return usupassword;
    }

    public void setUsupassword(String usupassword) {
        this.usupassword = usupassword;
    }

    public Integer getRolid() {
        return rolid;
    }

    public void setRolid(Integer rolid) {
        this.rolid = rolid;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Datospersonales getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Datospersonales usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String newUser() {
        if (usuarioFacade.consultarDocumento(datnumeroid)) {
            estado = 1;
            return "registeration";
        }

        ArrayList<Rol> miArrayRol = new ArrayList<>();
        Rol miRol = new Rol();
        miRol.setRolid(1);
        miArrayRol.add(miRol);

        Usuario miUsuario = new Usuario();
        miUsuario.setRolCollection(miArrayRol);
        miUsuario.setUsulogin(usulogin);
        miUsuario.setUsupassword(usupassword);
        usuarioFacade.create(miUsuario);

        Datospersonales misDatos = new Datospersonales();
        misDatos.setDatnombre(datnombre);
        misDatos.setDatapellido(datapellido);
        misDatos.setDatipoid(datipoid);
        misDatos.setDatnumeroid(datnumeroid);
        misDatos.setDatelefono(datelefono);
        misDatos.setDatcorreo(datcorreo);
        misDatos.setUsuid(miUsuario);
        datospersonalesFacade.create(misDatos);
        estado = 2;
        return "login";
    }

    public String newUser1() {
        if (usuarioFacade.consultarDocumento(datnumeroid)) {
            estado = 1;
            return "newUser";
        }

        ArrayList<Rol> miArrayRol = new ArrayList<>();
        Rol miRol = new Rol();
        miRol.setRolid(1);
        miArrayRol.add(miRol);

        Usuario miUsuario = new Usuario();
        miUsuario.setRolCollection(miArrayRol);
        miUsuario.setUsulogin(usulogin);
        miUsuario.setUsupassword(usupassword);
        usuarioFacade.create(miUsuario);

        Datospersonales misDatos = new Datospersonales();
        misDatos.setDatnombre(datnombre);
        misDatos.setDatapellido(datapellido);
        misDatos.setDatipoid(datipoid);
        misDatos.setDatnumeroid(datnumeroid);
        misDatos.setDatelefono(datelefono);
        misDatos.setDatcorreo(datcorreo);
        misDatos.setUsuid(miUsuario);
        datospersonalesFacade.create(misDatos);
        estado = 2;
        return "index";
    }

    public String login() throws IOException {

        Datospersonales misDatos = usuarioFacade.login(usulogin, usupassword);

        if (misDatos.getDatnombre().equals("Vacio")) {
            estado = 3;
            return "registeration";
        } else {

            //Redirecion
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            FacesContext.getCurrentInstance().
                    responseComplete();
            miUsuarioLogin = usuarioFacade.find(misDatos.getDatid());

            //Sesion
            HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                    .getRequest();
            miSesion.getSession().setAttribute("usulogin", misDatos);
            miSesion.getSession().setAttribute("usuariologin", miUsuarioLogin);
            response.sendRedirect("Administrator/index.xhtml");

        }
        return "null";
    }

    public void miraSession() throws IOException {

        HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuarioLogin = (Datospersonales) miSesion.getSession().getAttribute("usulogin");

        datid = 0;
        datnombre = "";
        datapellido = "";
        datipoid = "";
        datnumeroid = "";
        datelefono = "";
        datcorreo = "";
        usulogin = "";
        usupassword = "";
        estado = 0;

        if (!(usuarioLogin != null)) {

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            FacesContext.getCurrentInstance().
                    responseComplete();

            response.sendRedirect("login.xhtml");
        }

    }

    public void traerDatosPersonales() throws IOException {

        HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuarioLogin = (Datospersonales) miSesion.getSession().getAttribute("usulogin");
        datid = usuarioLogin.getDatid();
        datnombre = usuarioLogin.getDatnombre();
        datapellido = usuarioLogin.getDatapellido();
        datipoid = usuarioLogin.getDatipoid();
        datnumeroid = usuarioLogin.getDatnumeroid();
        datelefono = usuarioLogin.getDatelefono();
        datcorreo = usuarioLogin.getDatcorreo();

        if (!(usuarioLogin != null)) {

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            FacesContext.getCurrentInstance().
                    responseComplete();

            response.sendRedirect("login.xhtml");
        }
    }

    public String actualizarDatosPersonales() {

        ArrayList<Rol> miArrayRol = new ArrayList<>();
        Rol miRol = new Rol();
        miRol.setRolid(1);
        miArrayRol.add(miRol);

        Usuario miUsuario = new Usuario();
        miUsuario.setRolCollection(miArrayRol);
        miUsuario.setUsuid(datid);
        miUsuario.setUsulogin(usulogin);
        miUsuario.setUsupassword(usupassword);
        usuarioFacade.edit(miUsuario);

        Datospersonales misDatos = new Datospersonales();
        misDatos.setDatid(datid);
        misDatos.setDatnombre(datnombre);
        misDatos.setDatapellido(datapellido);
        misDatos.setDatipoid(datipoid);
        misDatos.setDatnumeroid(datnumeroid);
        misDatos.setDatelefono(datelefono);
        misDatos.setDatcorreo(datcorreo);
        misDatos.setUsuid(miUsuario);
        datospersonalesFacade.edit(misDatos);

        HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        miSesion.getSession().setAttribute("usulogin", misDatos);
        miSesion.getSession().setAttribute("usuariologin", miUsuarioLogin);

        estado = 2;

        return "misDatos";
    }

    public String contarUsuarios() {
        return "" + datospersonalesFacade.count();
    }

    public List<Datospersonales> consulTodos() {
        List<Datospersonales> misDatosPersonales;
        misDatosPersonales = datospersonalesFacade.findAll();
        return misDatosPersonales;
    }

    public String pasarNombreUsuario(Integer usu) {
        Usuario obj = usuarioFacade.find(usu);
        return obj.getUsulogin();
    }

    /**
     *
     * @param obj
     * @return
     */
    public String eliminarUsuario(Datospersonales obj) {
        datospersonalesFacade.remove(obj);
        usuarioFacade.remove(obj.getUsuid());
        estado = 3;
        return "index";
    }

    public boolean miraUsuarioLog(String Entrada) {

        return !Entrada.equals(miUsuarioLogin.getUsulogin());

    }

    public String actualizarUsuarios(Datospersonales misDatos) {

        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faces.getExternalContext();
        Map<String, String> params = (Map<String, String>) externalContext.getRequestParameterMap();

        ArrayList<Rol> miArrayRol = new ArrayList<>();
        Rol miRol = new Rol();
        miRol.setRolid(1);
        miArrayRol.add(miRol);
        String formularioAct=(params.get("formulario"));
        
        Usuario miUsuario = new Usuario();
        miUsuario.setRolCollection(miArrayRol);
        miUsuario.setUsuid(misDatos.getUsuid().getUsuid());
        miUsuario.setUsulogin(params.get(formularioAct+":actUsuario"));
        miUsuario.setUsupassword(params.get(formularioAct+":actClave"));
        usuarioFacade.edit(miUsuario);

        misDatos.setDatid(misDatos.getUsuid().getUsuid());
        misDatos.setDatnombre(params.get(formularioAct+":nombre"));
        misDatos.setDatapellido(params.get(formularioAct+":apellido"));
        misDatos.setDatipoid(params.get(formularioAct+":tipoid"));
        misDatos.setDatnumeroid(params.get(formularioAct+":numeroid"));
        misDatos.setDatelefono(params.get(formularioAct+":telefono"));
        misDatos.setDatcorreo(params.get(formularioAct+":correo"));
        misDatos.setUsuid(miUsuario);
        datospersonalesFacade.edit(misDatos);

        estado = 1;

        return "index";
    }
    
    //reporte
    
        public void init() throws JRException {
        this.lista1=this.datospersonalesFacade.findAll();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(this.lista1);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/Reporte"); // Sustituye "/" por el directorio ej: "/upload"
        realPath+="/report1.jasper"; 
        jasperPrint = JasperFillManager.fillReport(realPath, new HashMap(), beanCollectionDataSource);
    }
    public void PDF() throws JRException, IOException {
        init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
      ServletOutputStream servletOutputStream;
        servletOutputStream = httpServletResponse.getOutputStream();
       JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

    }

    public void DOCX() throws JRException, IOException {
        init();
           HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
       ServletOutputStream servletOutputStream;
        servletOutputStream = httpServletResponse.getOutputStream();
       JRDocxExporter docxExporter=new JRDocxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
    }

    public void XLSX() throws JRException, IOException {
       init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
    }

    public void ODT() throws JRException, IOException {
        init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.odt");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JROdtExporter docxExporter=new JROdtExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
    
       public void PPT() throws JRException, IOException{
       init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pptx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRPptxExporter docxExporter=new JRPptxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }

    public List getLista1() {
        return lista1;
    }

    public void setLista1(List lista1) {
        this.lista1 = lista1;
    }


}

<%-- 
    Document   : index
    Created on : 23-ene-2015, 8:57:07
    Author     : rober
--%>
<%@page import="hibernate.Foto"%>
<%@page import="java.util.List"%>
<%@page import="hibernate.Inmueble"%>
<% Inmueble inmueble = (Inmueble) request.getAttribute("inmueble");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir Foto</title>
    </head>
    <body>
        <h1>Añadir Foto</h1>
        <form action="subida" method="post" enctype="multipart/form-data">
            <input type="file" name="file" />
            <input type="hidden" name="target" value="foto" />
            <input type="hidden" name="op" value="insert" />
            <input type="hidden" name="action" value="op" />
            <input type="hidden" name="id" value="<%= inmueble.getId()%>" />
            <input type="submit" value="Subir"/>
        </form>

        <%
            List<Foto> lista = (List<Foto>) request.getAttribute("datos");
            for (Foto p : lista) {
        %>
        <table>
            <tr>
                <td><img src="<%= p.getRuta()%>" width="200" height="200"/></td>
                <td><a href="subida?target=foto&op=delete&action=op&id=<%= p.getId()%>">borrar</a></td>
            </tr>
        </table>
        <%
            }
        %>
    </body>
</html>

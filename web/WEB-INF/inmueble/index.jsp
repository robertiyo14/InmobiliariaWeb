<%@page import="java.util.List"%>
<%@page import="hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INDEX</h1>
        <h2>
            <a href="control?target=inmueble&op=insert&action=view">insertar registro</a>
        </h2>
        <table border="1">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Tipo</th>
                    <th>Localidad</th>
                    <th>Direccion</th>
                    <th>Precio</th>
                    <th>Usuario</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Inmueble> lista = (List<Inmueble>) request.getAttribute("datos");
                    for (Inmueble i : lista) {
                %>
                <tr>
                    <td><%= i.getId()%></td>
                    <td><%= i.getTipo()%></td>
                    <td><%= i.getDireccion()%></td>
                    <td><%= i.getLocalidad()%></td>
                    <td><%= i.getPrecio()%></td>
                    <td><%= i.getUsuario()%></td>
                    <td><a href="control?target=inmueble&op=delete&action=op&id=<%= i.getId()%>">Borrar</a></td>
                    <td><a href="control?target=inmueble&op=edit&action=view&id=<%= i.getId()%>">Editar</a></td>
                    <td><a href="subida?target=foto&op=insert&action=view&id=<%= i.getId()%>">Añadir foto</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
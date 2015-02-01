<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insert</h1>
        <form action="control" method="POST">
            Tipo: <input type="text" name="tipo" value="" />
            <br>
            Direccio: <input type="text" name="direccion" value="" />
            <br>
            Localidad: <input type="text" name="localidad" value="" />
            <br>
            Precio: <input type="text" name="precio" value="" />
            <br>
            Usuario: <input type="text" name="usuario" value="" />
            <br>
            
            <input type="hidden" name="target" value="inmueble" />
            <input type="hidden" name="op" value="insert" />
            <input type="hidden" name="action" value="op" />
            <input type="submit" value="insertar" />
        </form>
    </body>
</html>
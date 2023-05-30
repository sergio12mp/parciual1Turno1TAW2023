<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.ClienteEntity" %>
<%@ page import="es.taw.sampletaw.entity.CuentaEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 30/05/2023
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
    List<CuentaEntity> listaCuentasActivas = (List<CuentaEntity>) request.getAttribute("listaCuentasActivas");


%>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td><%="Nombre: " + cliente.getApellidos() + ", " + cliente.getNombre()%>
        </td>
        <td><%="Nombre: " + cliente.getApellidos() + ", " + cliente.getNombre()%>
        </td>
        <td><%="NIE: " + cliente.getNie()%>
        </td>
        <td><%="TELEFONO: " + cliente.getTelefono() %>
        </td>
        <td><%="CUENTA: " + cuenta.getIban() %>
        </td>
    </tr>
</table>


<h1>Realizar transferencia entre cuentas</h1>
<%--@elvariable id="Filtro" type=""--%>
<form:form action="/autorizarTransferencia" method="post" modelAttribute="Filtro">
    <form:hidden  path="FIBANORIGEN" value="<%=cuenta.getIban()%>"></form:hidden>
    Destinatario <form:select path="FIBANDESTINO" items="${listaCuentasActivas}" itemLabel="iban"></form:select><br>
    Descripcion<form:input path="FDescripcion"></form:input><br>
    Cantidad<form:input path="FCantidad"></form:input><br>

    <form:button>Enviar</form:button>
</form:form>


</body>
</html>

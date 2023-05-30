<%@ page import="es.taw.sampletaw.entity.CuentaEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 30/05/2023
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<CuentaEntity> listaCuentas = (List<CuentaEntity>) request.getAttribute("listaCuentas");

%>

<head>
    <title>Title</title>
</head>
<body>
<h1>LISTA DE CUENTAS DE CLIENTES</h1>
<table border="2">
    <tr>
        <th>CLIENTE</th>
        <th>NIE</th>
        <th>IBAN</th>
        <th>SALDO</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (CuentaEntity c : listaCuentas) {
    %>
    <tr>
        <td><%=c.getClienteByTitular().getNombre() + " " + c.getClienteByTitular().getApellidos()%>
        </td>
        <td><%=c.getClienteByTitular().getNie()%>
        </td>
        <td><%=c.getIban()%>
        </td>
        <td><%=c.getSaldoactual()%>
        </td>
        <td><a href="cambiarEstado?idCuenta=<%=c.getCuentaid()%>"><%= (c.getActiva() == 0 ? "ACTIVA" : "DESACTIVADA")%>
        </a></td><td><a href="realizarTransferencia?idCuenta=<%=c.getCuentaid()%>">Realizar transferencia
        </a></td>

    </tr>
    <% }%>

</table>

</body>
</html>

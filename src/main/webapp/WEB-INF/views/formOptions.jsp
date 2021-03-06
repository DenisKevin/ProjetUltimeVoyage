<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/views/resourceIncludes.jsp"%>

<title>Insert title here</title>

<script>
	function car(choixCar) {
		if (choixCar.checked) {
			//le code qui affiche la partie de ton formulaire masqu�e
			document.getElementById('choixVoiture').style.visibility = 'visible';
			document.getElementById('choixVoiture').style.display = 'block';
		} else {
			//le code qui le cache
			document.getElementById('choixVoiture').style.visibility = 'hidden ';
			document.getElementById('choixVoiture').style.display = 'none'
		}
	}
</script>

<script>
	function assur(choixAssur) {
		if (choixAssur.checked) {
			//le code qui affiche la partie de ton formulaire masqu�e
			document.getElementById('choixAssurance').style.visibility = 'visible';
			document.getElementById('choixAssurance').style.display = 'block';
		} else {
			//le code qui le cache
			document.getElementById('choixAssurance').style.visibility = 'hidden ';
			document.getElementById('choixAssurance').style.display = 'none'
		}
	}
</script>

</head>
<body>
	<div id="agentHeader">
		<%@ include file="headerAgent.jsp"%>
	</div>

	<div style="position: absolute; left:10%; top:20%; width: 50%; background-color: rgba(255, 255, 255, 0.8);  border: medium; border-color: #D60D6B; border-style: solid;">
	<form:form method="POST"
		action="${pageContext.request.contextPath}/formDossierOptions"
		modelAttribute="dossierOptions">

	<h3 style="margin: 10px; color: #D60D6B">Votre voyage :</h3>
	
		<div style="visibility: hidden; display: none">
			<form:input path="voyage.id" type="text"
				value="${dossierOptions.voyage.id}" />
		</div>

		<label> D�part <fmt:formatDate type="time" pattern="dd/MM/yyyy"
			value="${dossierOptions.voyage.dateDepart}" /> <br />
			Retour <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="${dossierOptions.voyage.dateRetour}"
			/>
		</label>

		<br />

		<label> Destination <input type="text"
			value="${dossierOptions.voyage.pays}" disabled />
		</label>

		<br />

		<label> Formule <input type="text"
			value="${dossierOptions.voyage.formule}" disabled />
		</label>
		<br />

		<div class="form-group">
			<label class="col-sm-2 control-label">Hebergement</label>
			<div class="col-sm-7">
				<form:select path="voyage.hebergement" items="${listHebergement}">
					<c:forEach items="${listHebergement} " var="key">
						<form:option value="${key}">${key}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>


		<br />
	Souhaitez-vous louer un v�hicule ?
	<input onclick="car(this)" type="checkbox" id="choixCar" value="yes" />
		<br />
		<div class="form-group" id="choixVoiture"
			style="visibility: hidden; display: none">
			<form:label path="voiture.id" class="col-sm-0 control-label" />
			<div class="col-sm-4">
				<form:select class="form-control" path="voiture.id">
					<form:option value="0">Selectionner un v�hicule</form:option>
					<form:options items="${listeVoitures}" itemLabel="modele"
						itemValue="id"></form:options>
				</form:select>
			</div>
		</div>
		<br /> Souhaitez-vous souscrire � une assurance ?
	<input onclick="assur(this)" type="checkbox" id="choixAssur"
			value="yes" />
		<br />
		<div class="form-group" id="choixAssurance"
			style="visibility: hidden; display: none">
			<form:label path="assurance.id" class="col-sm-0 control-label" />
			<div class="col-sm-4">
				<form:select class="form-control" path="assurance.id">
					<form:options items="${listeAssurances}" itemLabel="type"
						itemValue="id"></form:options>
				</form:select>
			</div>
		</div>



		<br />
	Combien de personnes seront du voyage (hormis vous) ?
	<form:input path="nbAccompagnants" />
		<form:errors path="nbAccompagnants" cssStyle="color:red" />


		<br />
		<input class="btn-warning" type="submit" value="Passer � l'�tape suivante" style="margin: 20px">



	</form:form>
	</div>
</body>
</html>
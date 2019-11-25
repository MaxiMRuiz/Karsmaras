let contador = 0;
let numPolicies = 0;

function hideAlerts() {
	 //console.log("hideAlerts");
	 $('#alertPolicy').hide();
}

function acceptFunction(prUpdate) {	
	//console.log("acceptFunction");
	
	if (prUpdate) {
		var id = document.getElementById("idPreregistry").value;
	}
	
	let basepath = document.getElementById("basepath");
	let statement = document.getElementById("statement");
	let responsible = document.getElementById("responsible");
	let uuaa = document.getElementById("uuaa");
	
	basepath = basepath.value.replace("|", " ");
	statement = statement.value.replace("|", " ");
	responsible = responsible.value.replace("|", " ");
	uuaa = uuaa.value.replace("|", " ");
	
	let nameParamv = null;
	let valuesParam = null;
	let idPolicy = null;
	let idOculto = null;
	let cadenaFinal = null;
	
	if (prUpdate) {
		cadenaFinal = basepath + '|' + statement + '|' + responsible + '|' + uuaa + '|' + id;		
	} else {
		cadenaFinal = basepath + '|' + statement + '|' + responsible + '|' + uuaa;
	}
	
	let separator = '#';
	
	for (let i = 0; i < contador; i++) {
		//console.log("Estamos en el bucle" + cadenaFinal);
		idOculto = document.getElementById("idOculto" + i);
		
		if (idOculto != null) {		
			idOculto = document.getElementById("idOculto" + i).value;
			idPolicy = document.getElementById("idOcultoPolicy" + i).value;
			nameParamv = document.getElementById("nameParam" + i);
			valuesParam = document.getElementById("value" + i);
			
			console.log("valuesParam" + valuesParam);
			nameParamv = nameParamv.value.replace("|", " ");
			valuesParam = valuesParam.value.replace("|", " ");		
			cadenaFinal += separator + nameParamv + '|' + idOculto + '|' + valuesParam + '|' + idPolicy;
		}
	}
	
	document.getElementById("politicasOcultas").value = cadenaFinal;
	console.log("El final es:" + document.getElementById("politicasOcultas").value);
}

function window_onload() {
	//console.log("window_onload");
	let option = 1;
	let cabecera = 0;
	let churro = document.getElementById("churroUpdate").value;
	//policyname, id:parametro1=descripcion,tipo,valores#parametro2=descripcion2,tipo2,valores2@policyname2:parametro1=descripcion,tipo,valores#parametro2=descripcion2,tipo2,valores2
	//console.log("churro: " + churro);		
	let churroPolicyList = churro.split("@");
	let nombrePolicy = [];
	
	hideAlerts();

	for (let i = 0; i < churroPolicyList.length; i++) {
		//console.log("churroPolicyList[" + i + "]: " + churroPolicyList[i]);
		let churroPolicy = churroPolicyList[i].split(":");
		//console.log("churroPolicy: " + churroPolicy);
		
		let churroNombrePolicy = churroPolicy[0].split(",");
		nombrePolicy[i] = churroNombrePolicy[0];
		//console.log("churroNombrePolicy: " + churroNombrePolicy[0]);
		//console.log("nombrePolicy[" + i + "]: " + nombrePolicy[i]);
		//console.log("nombrePolicy[" + (i - 1) + "]: " + nombrePolicy[i - 1]);
		
		let churroIdpolicy = churroNombrePolicy[1];
		//console.log("churroIdpolicy: " + churroIdpolicy);
		
		let churroParameter = churroPolicy[1].split("#");
		//console.log("churroParameter: " + churroParameter);
		
		if (nombrePolicy[i] != nombrePolicy[i - 1]) {
			cabecera = 1;
		}
		
		for (let j = 0; j < churroParameter.length; j++) {
			let churroParameterSplit = churroParameter[j].split("=");	
			//console.log("churroParameterSplit: " + churroParameterSplit);				
			mostrarParametros(churroParameterSplit, nombrePolicy[i], churroIdpolicy, option, cabecera);
			cabecera = 0;
		}
	}
}

function selectionPolicy(selectObject) {
	//console.log("selectionPolicy");
	let option = 0;
	let cabecera = 0;
	let value = selectObject.options[selectObject.selectedIndex].value;
	console.log("value: " + value);
	let churro = document.getElementById("churroOculto").value;
	//policyname, id:parametro1=descripcion,tipo,valores#parametro2=descripcion2,tipo2,valores2@policyname2:parametro1=descripcion,tipo,valores#parametro2=descripcion2,tipo2,valores2
	//console.log("churroOculto:  " + churro);
	
	//churroPolicyList: Obtenemos las políticas			
	let churroPolicyList = churro.split("@");
	let nombrePolicy = [];
			
	for (let i = 0; i < churroPolicyList.length; i++) {
		//console.log("churroPolicyList[" + i + "]: " + churroPolicyList[i]);

		let churroPolicy = churroPolicyList[i].split(":");
		//console.log("churroPolicy: " + churroPolicy);
		 
		let churroNombrePolicy = churroPolicy[0].split(",");
		nombrePolicy[i] = churroNombrePolicy[0];
		
		//console.log("churroNombrePolicy: " + churroNombrePolicy[0]);
		//console.log("value: " + value);
		//console.log("nombrePolicy[" + i + "]: " + nombrePolicy[i]);
		//console.log("nombrePolicy[" + (i - 1) + "]: " + nombrePolicy[i - 1]);
		
		let churroIdpolicy = churroNombrePolicy[1];
		//console.log("churroIdpolicy: " + churroIdpolicy);
		
		//churroPolicy[0] es el nombre la política				
		if (nombrePolicy[i] == value) {
			let politicasYaPuestas = document.getElementById(value);
			//console.log("politicasYaPuestas: " + politicasYaPuestas);
			
			if (politicasYaPuestas == null) {				
				if (nombrePolicy[i] != nombrePolicy[i - 1]) {
					cabecera = 1;
				}
				
				let churroParameter = churroPolicy[1].split("#");
				console.log("churroParameter: " + churroParameter);
				
				for (let j = 0; j < churroParameter.length; j++) {
					let churroParameterSplit = churroParameter[j].split("=");	
					console.log("churroParameterSplit: " + churroParameterSplit);				
					mostrarParametros(churroParameterSplit, nombrePolicy[i], churroIdpolicy, option, cabecera);
					cabecera = 0;
				}
				
			} else {
				//alert("This policy is not allowed because has been previously selected");
				$('#alertPolicy').show();
				window.scrollTo(0, 0);
				console.log("show alert");
				setTimeout(function(){
					hideAlerts();
				},2000);
			}
			
			selectObject.selectedIndex = "0";
		}
	}

	if (numPolicies > 0) {
		acceptButton.disabled = false;
	}
}

function borrarPolicy(selectObject){
	//console.log("borrarPolicy(" + selectObject.value + ")");
	//console.log("selectObject:  " + selectObject.value);
	let idpolicyDelete = document.getElementById('idOcultoPolicy' + selectObject.id).value;
	let borrado = 0;
	//console.log("idOcultoPolicy:  " + 'idOcultoPolicy'+selectObject.id);
	//console.log("idOcultoPolicy:  " + idpolicyDelete);
	
	for (let i = 0; i < contador; i++) {
		let existe = document.getElementById('idOcultoPolicy' + i); 
		
		if (existe != null) {
			let valorIdPolicy = document.getElementById('idOcultoPolicy' + i).value;
			//console.log("valorIdPolicy:  " + valorIdPolicy);
			
			if (valorIdPolicy == idpolicyDelete && numPolicies > 1) {
				let borrarLabel = document.getElementById('divPolicy' + i);
				let borrar = document.getElementById('divPrincipal' + i);
				borrar.parentNode.removeChild(borrar);
				borrado = 1;
				
				if (borrarLabel != null) {
					borrarLabel.parentNode.removeChild(borrarLabel);
				}
			}
		}
	}

	if (borrado) {
		numPolicies--;
		//console.log("numPolicies after delete: ", numPolicies);
	}
}

function mostrarParametros(churroParameterSplit, nombrePolicy, churroIdpolicy, option, cabecera) {
	//console.log("mostrarParametros");
	let nombreParametro = churroParameterSplit[0];						
	let churroRestoParameter = churroParameterSplit[1].split(",");		
	
	let idParametro = churroRestoParameter[0];
	let descripcionParametro = churroRestoParameter[1];
	let FieldTypeParametro = churroRestoParameter[2];
	let PosibleValueParametro = churroRestoParameter[3];
	let valueParametro;
	let typeParametro;

	let divPolicy = document.createElement('div');
	divPolicy.setAttribute('id','divPolicy' + contador);

	if (cabecera) {
		let htmlPolicy = "";
		
		htmlPolicy +=	"<div class='form-group row' id='divPolicyName'" + contador + " style='margin-top:35px'>";			
		htmlPolicy +=	"<div class='col-md-2 mb-8 align-middle'>" + 
							"<span id=label" + nombrePolicy + ">" +
								"<strong>" + nombrePolicy + "</strong>" +
							"</span>" +
						"</div>" +
						"<div class='col-md-1 mb-8'>" +
							"<button type='reset' id=" + contador + 
								" class='btn btn-outline-danger' value='Eliminar' onclick='borrarPolicy(this)'" + 
								" style='width: 100%'>" + "Delete" + 
							"</button>" + 
						"</div>";
		htmlPolicy +=	"</div>";
		htmlPolicy +=	"<div class='form-group row' id='divParam'" + contador + ">";			
		htmlPolicy +=	"<div class='col-md-2 mb-8'>Parameter type:</div>" +
						"<div class='col-md-2 mb-8'>Parameter:</div>" +
						"<div class='col-md-2 mb-8'>Parameter description:</div>" +
						"<div class='col-md-1 mb-8'>Field type:</div>" +
						"<div class='col-md-2 mb-8'>Posible values:</div>" +
						"<div class='col-md-1 mb-8'>Value:</div>";							
		htmlPolicy +=	"</div>";

		divPolicy.innerHTML = htmlPolicy;						
		divparametercosas.appendChild(divPolicy);
		numPolicies++;
		console.log("numPolicies after cabecera: ", numPolicies);
	}

	if (option) {
		valueParametro = churroRestoParameter[4];
		typeParametro = churroRestoParameter[5];
	} else {
		typeParametro = churroRestoParameter[4];
	}
								
	//Creamos el div con los parámetros de las políticas.
	let divPrincipal = document.createElement('div');
	divPrincipal.setAttribute('class','form-group row');
	divPrincipal.setAttribute('id','divPrincipal' + contador);
	
	let divSpan = document.createElement('div');
	let spanParameter = document.createElement('span');
	spanParameter.setAttribute('id', nombrePolicy);
	spanParameter.setAttribute('type','hidden');
	divSpan.appendChild(spanParameter);
					
	let divParameterType = document.createElement('div');
	divParameterType.setAttribute('class','col-md-2 mb-8');	
	
	let inputParameterType =  document.createElement('input');
	inputParameterType.setAttribute('type','text');
	inputParameterType.setAttribute('class','form-control');
	inputParameterType.setAttribute('id','typeParam' + contador);
	inputParameterType.setAttribute('name','typeParam' + contador);
	inputParameterType.setAttribute('value',typeParametro); 
	inputParameterType.setAttribute('disabled','disabled'); 
	
	divParameterType.appendChild(inputParameterType);
	
	let divParameter = document.createElement('div');
	divParameter.setAttribute('class','col-md-2 mb-8');

	let inputParameter =  document.createElement('input');
	inputParameter.setAttribute('type','text');
	inputParameter.setAttribute('class','form-control');
	inputParameter.setAttribute('id','nameParam' + contador);
	inputParameter.setAttribute('name','nameParam' + contador);
	inputParameter.setAttribute('value',nombreParametro); 
	inputParameter.setAttribute('disabled','disabled'); 
	
	divParameter.appendChild(inputParameter);	
	
	let divDescription = document.createElement('div');
	divDescription.setAttribute('class','col-md-2 mb-8');

	let inputDescription =  document.createElement('input');
	inputDescription.setAttribute('type','text');
	inputDescription.setAttribute('class','form-control');
	inputDescription.setAttribute('id','descriptionParam' + contador);
	inputDescription.setAttribute('name','descriptionParam' + contador);
	inputDescription.setAttribute('value',descripcionParametro);
	inputDescription.setAttribute('disabled','disabled'); 
	
	divDescription.appendChild(inputDescription);
		
	let divFieldType = document.createElement('div');
	divFieldType.setAttribute('class','col-md-1 mb-8');

	let inputFieldType =  document.createElement('input');
	inputFieldType.setAttribute('type','text');
	inputFieldType.setAttribute('class','form-control');
	inputFieldType.setAttribute('id','fieldType' + contador);
	inputFieldType.setAttribute('name','fieldType' + contador);
	inputFieldType.setAttribute('value',FieldTypeParametro);
	inputFieldType.setAttribute('disabled','disabled'); 
						 	
	divFieldType.appendChild(inputFieldType);	
				
	let divPossibleValue = document.createElement('div');
	divPossibleValue.setAttribute('class','col-md-2 mb-8');

	let inputPossibleValue =  document.createElement('input');
	inputPossibleValue.setAttribute('type','text');
	inputPossibleValue.setAttribute('class','form-control');
	inputPossibleValue.setAttribute('id','possibleValues' + contador);
	inputPossibleValue.setAttribute('name','possibleValues' + contador);	
	inputPossibleValue.setAttribute('value', PosibleValueParametro);
	inputPossibleValue.setAttribute('disabled','disabled'); 
	
	divPossibleValue.appendChild(inputPossibleValue);	
	
	let divValue = document.createElement('div');
	divValue.setAttribute('class','col-md-2 mb-8');

	let inputValue =  document.createElement('input');
	inputValue.setAttribute('type','text');
	inputValue.setAttribute('class','form-control');
	inputValue.setAttribute('id','value' + contador);
	inputValue.setAttribute('name','value' + contador);

	if (option) {
		inputValue.setAttribute('value',valueParametro);		 
	} else {
		inputValue.setAttribute('value',FieldTypeParametro);		 
	}
						 	
	divValue.appendChild(inputValue);	

	//<input type="hidden" class="form-control" id="politicasOcultas" name="politicasOcultas">
	let inputID =  document.createElement('input');
	inputID.setAttribute('type','hidden');
	inputID.setAttribute('class','form-control');
	inputID.setAttribute('id','idOculto' + contador);
	inputID.setAttribute('name','idOculto' + contador);
	inputID.setAttribute('value',idParametro);
	
	let inputIDPolicy =  document.createElement('input');
	inputIDPolicy.setAttribute('type','hidden');
	inputIDPolicy.setAttribute('class','form-control');
	inputIDPolicy.setAttribute('id','idOcultoPolicy' + contador);
	inputIDPolicy.setAttribute('name','idOcultoPolicy' + contador);
	inputIDPolicy.setAttribute('value',churroIdpolicy);
	
	divPrincipal.appendChild(inputID);
	divPrincipal.appendChild(inputIDPolicy);
	
	divPrincipal.appendChild(divSpan);
	divPrincipal.appendChild(divParameterType);
	divPrincipal.appendChild(divParameter);
	divPrincipal.appendChild(divDescription);
	divPrincipal.appendChild(divFieldType);
	divPrincipal.appendChild(divPossibleValue);
	divPrincipal.appendChild(divValue);
	//divPrincipal.appendChild(divBotonEliminar);	
	
	divparametercosas.appendChild(divPrincipal);
				
	contador++;		
}


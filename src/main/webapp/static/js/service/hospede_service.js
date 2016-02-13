'use strict';

App.factory('HospedeService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllHospedes: function() {
					return $http.get('http://localhost:8080/Spring4AngularJS/hospede/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Erro ao buscar hospede');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createHospede: function(hospede){
					return $http.post('http://localhost:8080/Spring4AngularJS/hospede/', hospede)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Erro ao criar hospede');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateHospede: function(hospede, id){
					return $http.put('http://localhost:8080/Spring4AngularJS/hospede/'+id, hospede)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Erro ao atualizar hospede');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteHospede: function(id){
					return $http.delete('http://localhost:8080/Spring4AngularJS/hospede/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Erro ao deletar hospede');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);

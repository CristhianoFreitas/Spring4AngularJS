'use strict';

App.controller('HospedeController', ['$scope', 'HospedeService', function($scope, HospedeService) {
          var self = this;
          self.hospede={id:null,nome:'',endereco:'',email:'',cpf:''};
          self.hospedes=[];
              
          self.fetchAllHospedes = function(){
        	  HospedeService.fetchAllHospedes()
                  .then(
      					       function(d) {
      						        self.hospedes = d;
      					       },
            					function(errResponse){
            						console.error('Erro na listagem.');
            					}
      			       );
          };
           
          self.createHospede = function(hospede){
              HospedeService.createHospede(hospede)
		              .then(
                      self.fetchAllHospedes, 
				              function(errResponse){
					               console.error('Erro ao criar.');
				              }	
                  );
          };

         self.updateHospede = function(hospede, id){
        	 HospedeService.updateHospede(hospede, id)
		              .then(
				              self.fetchAllHospedes, 
				              function(errResponse){
					               console.error('Erro ao atualizar.');
				              }	
                  );
          };

         self.deleteHospede = function(id){
        	 HospedeService.deleteHospede(id)
		              .then(
				              self.fetchAllHospedes, 
				              function(errResponse){
					               console.error('Erro ao deletar.');
				              }	
                  );
          };

          self.fetchAllHospedes();

          self.submit = function() {
              if(self.hospede.id==null){
                  console.log('Criando novo hospede', self.hospede);    
                  self.createHospede(self.hospede);
              }else{
                  self.updateHospede(self.hospede, self.hospede.id);
                  console.log('Atualizando hospede de id', self.hospede.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id que sera editado', id);
              for(var i = 0; i < self.hospedes.length; i++){
                  if(self.hospedes[i].id == id) {
                     self.hospede = angular.copy(self.hospedes[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id que sera deletado ', id);
              for(var i = 0; i < self.hospedes.length; i++){
                  if(self.hospedes[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteHospede(id);
          };

          
          self.reset = function(){
              self.hospede={id:null,nome:'',endereco:'',email:'',cpf:''};
              $scope.myForm.$setPristine(); //limpando form
          };

      }]);

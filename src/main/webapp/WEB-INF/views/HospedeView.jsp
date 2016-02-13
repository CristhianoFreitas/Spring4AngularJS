<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
      .nome.ng-valid {
          background-color: lightgreen;
      }
      .nome.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .nome.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="HospedeController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Registro de Hospede Form</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.hospede.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Nome</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.hospede.nome" name="uname" class="nome form-control input-sm" placeholder="Digite seu nome." required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uname.$error.required">Campo Obrigatório</span>
                                      <span ng-show="myForm.uname.$error.minlength">Mínimo de 3 carácteres</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                        
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Endereco</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.hospede.endereco" class="form-control input-sm" placeholder="Digite seu endereco."/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Email</label>
                              <div class="col-md-7">
                                  <input type="email" ng-model="ctrl.hospede.email" name="email" class="email form-control input-sm" placeholder="Digite seu Email" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">Campo Obrigatório</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">CPF</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.hospede.cpf" class="form-control input-sm" placeholder="Digite seu cpf"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.hospede.id ? 'Incluir' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Limpar</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">Lista de Hospedes </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Nome</th>
                              <th>Endereco</th>
                              <th>Email</th>
                              <th>CPF</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.hospedes">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.nome"></span></td>
                              <td><span ng-bind="u.endereco"></span></td>
                              <td><span ng-bind="u.email"></span></td>
                              <td><span ng-bind="u.cpf"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Editar</button>  
                              <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remover</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/hospede_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/hospede_controller.js' />"></script>
  </body>
</html>
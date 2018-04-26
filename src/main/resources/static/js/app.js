angular.module('rfmApp', [])
    .controller('MainController', function($scope,$http) {

        $scope.parseResponse = function(data){

            $scope.dataSet = [];

          for(var i =0;i<data.length;i++){
              var txnObj = [
                  data[i].created,data[i].type,data[i].amount,data[i].description,data[i].type,data[i].beneficiary
              ]
              $scope.dataSet.push(txnObj);

          }

        }
        $scope.getTransactions = function(){
            $http({
                      method: 'GET',
                      url: 'http://41.185.27.50:1985/transaction/assembly/1/2018-04-20/2018-04-29'
                  }).then(function successCallback(response) {

                      if(response.data){
                          $scope.parseResponse(response.data)
                      }

                $('#example').DataTable( {
                                             data: $scope.dataSet,
                                             columns: [
                                                 { title: "Date" },
                                                 { title: "Type" },
                                                 { title: "Amount" },
                                                 { title: "Description." },
                                                 { title: "Type" },
                                                 { title: "Beneficiary" }
                                             ]
                                         } );
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        }

        $scope.getTransactions();
    });
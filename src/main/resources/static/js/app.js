angular.module('rfmApp', [])
    .controller('MainController', function($scope,$http) {
        $scope.urlBase = 'http://41.185.27.50:1985/transaction/assembly';
        $scope.url = '';
        $scope.dataSet = [];
        $scope.totalIncome = 0.00;
        $scope.totalExpenses = 0.00;
        $scope.netIncome = 0.00;

        $scope.assemblyList = angular.copy(assembliesArr);

        $(function() {

            var start = moment().subtract(29, 'days');
            var end = moment();

            function cb(start, end) {
                $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                $scope.startDate = start.format('YYYY-MM-DD');
                $scope.endDate = end.format('YYYY-MM-DD');
                $scope.submitForm();
            }

            $('#reportrange').daterangepicker({
                  startDate: start,
                  endDate: end,
                  ranges: {
                      'Today': [moment(), moment()],
                      'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                      'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                      'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                      'This Month': [moment().startOf('month'), moment().endOf('month')],
                      'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                  }
              }, cb);

            cb(start, end);

        });

        $scope.parseResponse = function(data){
            var respArray = data.transactionList;

          for(var i =0;i<respArray.length;i++){
              var txnObj = [
                  respArray[i].created,respArray[i].type,respArray[i].amount,respArray[i].description,respArray[i].type,respArray[i].beneficiary
              ]
              $scope.dataSet.push(txnObj);

          }
          $scope.totalIncome = data.totalIncome;
          $scope.totalExpenses = data.totalExpenses;
          $scope.netIncome = data.netIncome;

        }

        $scope.submitForm = function(){
            var computerResponse = getRandomInt(1, 2);
            var type = ""
            if(computerResponse == 1){
                type = "Income";
            }else{
                type = "Expenditure";
            }

            if($scope.selectedAssembly != null && $scope.datesSet()){
                $scope.url = $scope.urlBase+"/"+$scope.selectedAssembly.id+"/"+$scope.startDate+"/"+$scope.endDate+"/"+type;
                $scope.getTransactions();
            }

        }
        function getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1) + min);
        }


        $scope.downloadCSV = function(){
            if($scope.dataSet.length < 1){
                return;
            }
            var rows = $scope.dataSet;
            var csvContent = "data:text/csv;charset=utf-8,";
            rows.forEach(function(rowArray){
                var row = rowArray.join(",");
                csvContent += row + "\r\n";
            });
            var encodedUri = encodeURI(csvContent);
            var link = document.createElement("a");
            link.setAttribute("href", encodedUri);
            link.setAttribute("download", "finance_report.csv");
            link.innerHTML= "Click Here to download";
            document.body.appendChild(link); // Required for FF

            link.click();
        }
        $scope.getTransactions = function(){
            //var url = 'http://41.185.27.50:1985/transaction/assembly/6/2017-04-20/2019-12-29';
            $scope.dataSet = [];

            $http({
                      method: 'GET',
                      url: $scope.url
                  }).then(function successCallback(response) {

                      if(response.data){
                          $scope.onSuccess = true;
                          $scope.onError = false;
                          $scope.parseResponse(response.data);
                          $scope.successMessage ="Search returned "+$scope.dataSet.length+" results";
                          dataTable.clear().rows.add($scope.dataSet).draw();
                      }


            }, function errorCallback(response) {
                $scope.onSuccess = false;
                $scope.onError = true;
                $scope.errorMessage ="Search failed. Please try again";
            });
        }

        $scope.datesSet = function(){
            if($scope.startDate != null && $scope.endDate != null){
                return true;
            }
            return false;
        }

        var dataTable = $('#example').DataTable( {
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
    });
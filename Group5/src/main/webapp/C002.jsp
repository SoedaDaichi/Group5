

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>C002</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@4.4.9/dist/chart.umd.min.js"></script>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid">
		<p class="fs-1 fw-bold mt-4">ダッシュボード</p>


		<div class="row py-3">
			<div class="col-md-6">
				<div class="card shadow-sm border-0 bg-white">
					<div class="card-body text-center">
						<h5 class="card-title text-muted">📊月間総売り上げ</h5>
						<p class="card-text fs-3 fw-bold text-primary">${monthSales}円</p>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card shadow-sm border-0 bg-white">
					<div class="card-body text-center">
						<h5 class="card-title text-muted">📊年間総売り上げ</h5>
						<p class="card-text fs-3 fw-bold text-success">${nennkannSales}
							円</p>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card shadow-sm border-0 bg-white">
					<div class="card-body text-center">
						<h5 class="card-title text-muted">📊カテゴリー別売り上げ</h5>
						
                        <div style="max-width: 600px; height: 400px; margin: 0 auto;">
                        <canvas id="categorySalesChart" style="display: block; margin: 0 auto;"></canvas>
                        </div>

<!--          <p>カテゴリー売上JSON: ${categorySalesJson}</p>-->
          
						</p>
					</div>
				</div>
			</div>
		</div>


		<script>

  
     const categorySalesJsonStr = '${categorySalesJson}';
     const categorySales = JSON.parse(categorySalesJsonStr);
        
  
  const labels = Object.keys(categorySales);
  const data = Object.values(categorySales);

  const ctx = document.getElementById('categorySalesChart').getContext('2d');
  new Chart(ctx, {
    type: 'pie',
    data: {
      labels: labels,
      datasets: [{
        data: data,
        backgroundColor: [
          'rgba(255, 99, 132, 0.7)',
          'rgba(54, 162, 235, 0.7)',
          'rgba(255, 206, 86, 0.7)',
          'rgba(75, 192, 192, 0.7)',
          'rgba(153, 102, 255, 0.7)',
          'rgba(255, 159, 64, 0.7)'
        ],
        borderColor: 'white',
        borderWidth: 2
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { position: 'right' },
        tooltip: {
          callbacks: {
            label: function(context) {
              return context.label + ': ' + context.parsed.toLocaleString() + ' 円';
            }
          }
        }
      }
    }
  });

  
</script>










		<!--		<div class="row justify-content-center mt-5">-->
		<!--			<div class="col-lg-5 d-flex justify-content-center">-->
		<!--				<div class="card shadow p-4 g-3" style="width: 500px;">-->
		<!--					<div class="card-body">-->
		<!--						<canvas class="mx-auto" id="polarChart"></canvas>-->
		<!--					</div>-->
		<!--				</div>-->
		<!--			</div>-->
		<!--			<div class="col-lg-5 d-flex justify-content-center">-->
		<!--				<div class="card shadow p-4 g-3 align-self-end" style="width: 500px;">-->
		<!--					<div class="card-body">-->
		<!--						<canvas class="mx-auto" id="polarChart2" width="3

00" height="180"></canvas>-->
		<!--					</div>-->
		<!--				</div>-->
		<!--			</div>-->
		<!--		</div>-->
		<!--	</div>-->

		<!--	<script>-->
		<!--		let polarCtx = document.getElementById("polarChart");-->
		<!--		let polarConfig = {-->
		<!--			type : 'polarArea',-->
		<!--			data : {-->
		<!--				labels : [ 'April', 'May', 'June', 'July', 'August' ],-->
		<!--				datasets : [ {-->
		<!--					label : '月別結果',-->
		<!--					data : [ 7, 10, 9, 4, 5 ],-->
		<!--					backgroundColor : [ '#ff0000', '#0000ff', '#ffff00',-->
		<!--							'#008000', '#ffa500', ]-->
		<!--				} ]-->
		<!--			},-->
		<!--		};-->
		<!--		let polarChart = new Chart(polarCtx, polarConfig);-->
		<!--	</script>-->
		<!--	<script>-->
		<!--		let polarCtx = document.getElementById("polarChart2");-->
		<!--		let polarConfig = {-->
		<!--			type : 'polarArea',-->
		<!--			data : {-->
		<!--				labels : [ 'April', 'May', -->
		<!--					'June', 'July', 'August' ],-->
		<!--				datasets : [ {-->
		<!--					label : '月別結果',-->
		<!--					data : [ 7, 10, 9, 4, 5 ],-->
		<!--					backgroundColor : [ '#ff0000', '#0000ff', '#ffff00',-->
		<!--							'#008000', '#ffa500', ]-->
		<!--				} ]-->
		<!--			},-->
		<!--		};-->
		<!--		let polarChart = new Chart(polarCtx, polarConfig);-->
		<!--	</script>-->
		<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
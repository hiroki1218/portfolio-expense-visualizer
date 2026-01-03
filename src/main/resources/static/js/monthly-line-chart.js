'use strict';

document.addEventListener('DOMContentLoaded', () =>{
	fetch('/api/graphs/monthly-amount')
		.then(res => {
			if(!res.ok) throw new Error(`HTTP ${res.status}`);
			return res.json();
		})
		.then(data => {
			const labels = data.map(d => d.month.substring(0, 7));
			const amount = data.map(d => d.totalAmount);
			const ctx = document.getElementById('monthlyLineChart');
			new Chart(ctx, {
				type: 'line',
				data: {
					labels,
					datasets:[{
						label:'月次支出推移',
						data: amount,
						fill:false,
						tension:0.1
					}],
				},
				options: {
					maintainAspectRatio: false,
					responsive: true,
					animation: false,
					scales: {
						y: { beginAtZero: true}
					}
				}
			});
		})
	.catch(err => console.error('折れ線グラフ描画失敗', err));
})
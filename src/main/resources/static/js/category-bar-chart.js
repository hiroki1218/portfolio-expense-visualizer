'use strict';

document.addEventListener('DOMContentLoaded', () => {
	fetch('/api/graphs/category-compare')
		//レスポンスされたHTTP情報をチェック
		.then(res => {
			if(!res.ok) throw new Error(`HTTP ${res.status}`);
			return res.json();
		})
		//jsonに変換されたデータを操作
		.then(data => {
			const labels = data.map(d => d.categoryName);
			const previousTotalAmount = data.map(d => d.previousTotalAmount);
			const currentTotalAmount = data.map(d => d.currentTotalAmount);
			const ctx = document.getElementById('categoryBarChart');
			//Chart.jsのグラフを作成
			new Chart(ctx, {
				type: 'bar',
				data: {
					labels,
					datasets: [
						{label: '前月',data:  previousTotalAmount},
						{label: '当月',data: currentTotalAmount}
					]
				},
				options: {
					responsive: false,
					animation: false,
					scales: {
						y: {
							beginAtZero: true
						}
					},
					plugins: {
						legend: {
							position: 'top'
						}
					}
				}
			});
		})
	.catch(err => {
		console.error('カテゴリ別棒グラフの取得/描画に失敗', err);
	})
})
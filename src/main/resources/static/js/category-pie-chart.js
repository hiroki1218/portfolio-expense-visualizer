'use strict';

//Chartのインスタンスを入れる変数
let categoryPieChart = null;

document.addEventListener('DOMContentLoaded',() => {
	fetch('/api/graphs/category-amounts')
		//レスポンスされたHTTP情報をチェック
		.then(res => {
			if(!res.ok) throw new Error(`HTTP ${res.status}`);
			return res.json();
		})
		//jsonに変換されたデータを操作
		.then(data => {
			const labels = data.map(d => d.categoryName);
			const values = data.map(d => d.totalAmount);
			const ctx = document.getElementById('categoryPieChart');
			//Chart.jsのグラフを新規作成
			categoryPieChart = new Chart(ctx, {
				type: 'pie', //円グラフを指定
				data: { labels, datasets: [{ data: values}] },
				options: {
					responsive: false,
					animation: false,
					plugins: {
						legend: { position: 'right'} //凡例カテゴリ
					}
				}
			});
		})
	.catch(err => console.error('円グラフの取得/描画に失敗', err));
});


package vending_machine;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ManageCoins {

	// コインの管理
	private Map<CoinType, List<Coin>> coinContainers = new TreeMap<CoinType, List<Coin>>(new Comparator<CoinType>() {
		@Override
		public int compare(CoinType o1, CoinType o2) {
			return o2.getValue() - o1.getValue();
		}
	});

	// コインコンテナのゲッター
	public Map<CoinType, List<Coin>> getCoinContainers() {
		return this.coinContainers;
	}

	// コインの数を取得
	public int getCount(CoinType coinType) {
		return this.coinContainers.get(coinType).size();
	}

	// コインを増やす
	public void addCoin(Coin coin) {
		// コンテナにあるタイプの場合
		if (this.coinContainers.containsKey(coin.getType())) {
			this.coinContainers.get(coin.getType()).add(coin);
			return;
		}
		// コンテナにないタイプの場合
		LinkedList<Coin> coinList = new LinkedList<Coin>();
		coinList.add(coin);
		this.coinContainers.put(coin.getType(), coinList);
	}

	// コインを複数追加
	public void addCoins(List<Coin> coins) {
		for (Coin coin : coins) {
			this.addCoin(coin);
		}
	}

	// コインを取り出す
	// 在庫が0の場合、例外発生
	public Coin removeCoin(CoinType coinType) {
		return this.coinContainers.get(coinType).remove(0);
	}

	// 管理しているコインの総額を取得
	public int getAmount() {
		int amount = 0;
		for (CoinType coinType : this.coinContainers.keySet()) {
			amount += coinType.getValue() * this.getCount(coinType);
		}
		return amount;
	}

	// タイプ一覧を返す
	public List<CoinType> getCoinTypes() {
		List<CoinType> itemLabels = new LinkedList<CoinType>(this.coinContainers.keySet());
		return itemLabels;
	}

	// コンテナのコイン枚数を出力
	public void outputContainersInfo() {
		for(CoinType coinType :this.getCoinTypes()) {
			System.out.println(
					coinType.getValue() +
					coinType.getUnit() +
					" " +
					this.getCount(coinType) +
					"枚"
			);
		}
	}
}

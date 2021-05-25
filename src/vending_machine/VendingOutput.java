package vending_machine;

import java.util.List;
import java.util.Objects;

// 自動販売機からのアウトプット
public class VendingOutput {

	// 商品
	private Item item;
	// 返却されたコインを管理
	private ManageCoins manageCoins = new ManageCoins();

	// 商品が購入できた場合
	public VendingOutput(Item item, List<Coin> coins) {
		this.item = item;
		this.manageCoins.addCoins(coins);
	}

	// 商品が購入できなかった場合
	public VendingOutput(List<Coin> coins) {
		this(null, coins);
	}

	// 商品ゲッター
	public Item getItem() {
		return this.item;
	}

	// 情報出力
	public void outputInfo() {
		// 商品がnullの場合、払い戻し金を表示
		if (Objects.equals(this.item, null)) {
			System.out.println("払い戻し金");
			this.manageCoins.outputContainersInfo();
			return;
		}
		// 商品がある場合、購入品とお釣りを表示
		System.out.println("購入品 " + this.item.getName());
		System.out.println("お釣り");
		this.manageCoins.outputContainersInfo();
	}
}

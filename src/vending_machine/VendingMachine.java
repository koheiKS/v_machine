package vending_machine;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

	// メンバ変数
	ManageItems manageItems = new ManageItems();
	ManageCoins manageCoins = new ManageCoins();

	// 商品の初期化
	public void initializeItems() {
		int count = 10;
		for (int i = 0; i < count; i++) {
			for (ItemLabel itemLabel : ItemLabelTemplate.DRINKS) {
				this.manageItems.addItem(itemLabel);
			}
		}
		for (int i = 0; i < count; i++) {
			for (ItemLabel itemLabel : SaleItemLabelTemplate.DRINKS) {
				this.manageItems.addItem(itemLabel);
			}
		}
	}

	// コインの初期化
	public void initializeCoins() {
		// countで指定した数だけ、日本円のコインを追加
		int count = 10;
		for (int i = 0; i < count; i++) {
			for (CoinType coinType : CoinTypeTemplate.YEN_TYPES) {
				Coin coin = new Coin(coinType);
				this.manageCoins.addCoin(coin);
			}
		}
	}

	// 自動販売メソッド
	public VendingOutput vending(Scanner scanner) {

		// 商品を表示する
		this.showItems();

		// 買う商品を選んでもらう
		ItemLabel selectItemLabel = this.getSelectItemLabel(scanner);

		// お金を投入してもらう
		List<Coin> insertCoins = new LinkedList<Coin>();
		while (true) {
			insertCoins.addAll(this.getInsertCoins(scanner));
			if (this.getChangeAmount(selectItemLabel, insertCoins) >= 0) {
				break;
			}
			System.out.println("投入金が足りません");
		}

		// お釣りが払えるかを確かめる
		boolean canChange = this.canChange(selectItemLabel, insertCoins);

		// お釣りが払えるかつ在庫がある場合、購入、お釣りを出す
		if (canChange && this.manageItems.getCount(selectItemLabel) != 0) {
			Item item = this.purchaseItem(selectItemLabel);
			List<Coin> changeCoins = this.getChangeCoins(selectItemLabel, insertCoins);
			return new VendingOutput(item, changeCoins);
		}

		// 投入金を払い戻し
		List<Coin> refundCoins = this.refundCoins(insertCoins);
		return new VendingOutput(refundCoins);
	}

	// 商品の表示
	public void showItems() {

		// ラベルの数だけループ
		for (ItemLabel itemLabel : this.manageItems.getItemLabels()) {

			// 商品ごとのメッセージ
			String message = itemLabel.getId() + " " +
					itemLabel.getName() + " " +
					itemLabel.getPrice() + itemLabel.getPriceUnit();
			// 売り切れの場合、メッセージを追加
			if (this.manageItems.getCount(itemLabel) == 0) {
				message += " 売り切れ";
			}
			System.out.println(message);
		}
	}

	// お釣りの金額を計算する
	private int getChangeAmount(ItemLabel itemLabel, List<Coin> insertCoins) {
		return this.getCoinsAmount(insertCoins) - itemLabel.getPrice();
	}

	// コイン配列の総金額を計算する
	public int getCoinsAmount(List<Coin> coins) {
		int amount = 0;
		for (Coin coin : coins) {
			amount += coin.getType().getValue();
		}
		return amount;
	}

	// お釣りが払えるか
	private boolean canChange(ItemLabel itemLabel, List<Coin> insertCoins) {

		// 投入されたコインをコインコンテナへ
		this.manageCoins.addCoins(insertCoins);

		// お釣りの金額を取得
		int changeAmount = this.getChangeAmount(itemLabel, insertCoins);

		for (CoinType coinType : this.manageCoins.getCoinTypes()) {
			// お釣りとして払えるコインの枚数を取得
			int countOfCoin = Math.min(changeAmount / coinType.getValue(), this.manageCoins.getCount(coinType));
			// 残っているお釣りを計算
			changeAmount -= countOfCoin * coinType.getValue();
		}
		// お釣りを払い切れる場合、true
		if (changeAmount == 0) {
			return true;
		}
		return false;
	}

	// お釣りを返す
	private List<Coin> getChangeCoins(ItemLabel itemLabel, List<Coin> insertCoins) {

		List<Coin> changeCoins = new LinkedList<Coin>();

		int changeAmount = this.getChangeAmount(itemLabel, insertCoins);

		for (CoinType coinType : this.manageCoins.getCoinTypes()) {
			// コインの枚数を取得
			int countOfCoin = Math.min(changeAmount / coinType.getValue(), this.manageCoins.getCount(coinType));
			while (countOfCoin > 0) {
				// コインを取り出す
				changeCoins.add(this.manageCoins.removeCoin(coinType));
				countOfCoin--;
				changeAmount -= coinType.getValue();
			}
		}
		return changeCoins;
	}

	// 投入金を払戻す
	private List<Coin> refundCoins(List<Coin> insertCoins) {

		List<Coin> refundCoins = new LinkedList<Coin>();
		for (Coin coin : insertCoins) {
			refundCoins.add(this.manageCoins.removeCoin(coin.getType()));
		}
		return refundCoins;
	}

	// 商品購入
	private Item purchaseItem(ItemLabel itemLabel) {
		return this.manageItems.removeItem(itemLabel);
	}

	// 商品を選んでもらう
	private ItemLabel getSelectItemLabel(Scanner scanner) {

		int selectItemId;

		// ユーザーから商品ラベルidを入力してもらう
		while (true) {
			System.out.println("商品番号を選んで入力して下さい");
			selectItemId = scanner.nextInt();
			// 自販機にある商品ラベルidの場合、ループ脱出
			if (this.manageItems.getItemLabelIds().contains(selectItemId)) {
				break;
			}
		}
		return this.manageItems.getSelectItemLabel(selectItemId);
	}

	// お金を投入してもらう
	private List<Coin> getInsertCoins(Scanner scanner) {
		// お金の数値
		int money;
		// 1円玉がないので、1桁が0である数値を入力されるまでループ
		while (true) {
			System.out.println("投入するお金を入力して下さい。お釣りが払えないので1の位は0にして下さい");
			money = scanner.nextInt();
			if (money % 10 == 0) {
				break;
			}
		}
		// 入力されたお金をコインに変換し、配列に格納
		List<Coin> insertCoins = new LinkedList<Coin>();
		for (CoinType coinType : CoinTypeTemplate.YEN_TYPES) {

			int countOfCoin = money / coinType.getValue();
			for (int i = 0; i < countOfCoin; i++) {
				Coin coin = new Coin(coinType);
				insertCoins.add(coin);
				money -= coinType.getValue();
			}
		}
		return insertCoins;
	}
}

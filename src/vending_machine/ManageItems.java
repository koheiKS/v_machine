package vending_machine;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// 商品のラベルと実体を管理
public class ManageItems {

	// 商品の管理
	private Map<ItemLabel, List<Item>> itemContainers = new TreeMap<ItemLabel, List<Item>>(new Comparator<ItemLabel>() {
		@Override
		public int compare(ItemLabel itemLabel1, ItemLabel itemLabel) {
			return itemLabel1.getId() - itemLabel.getId();
		}
	});

	// 商品の数を取得
	public int getCount(ItemLabel itemLabel) {
		return this.itemContainers.get(itemLabel).size();
	}

	// 商品を増やす
	public void addItem(ItemLabel itemLabel) {

		// 商品を生成
		Item item = new Item(itemLabel);

		// コンテナにあるラベルの場合
		if (this.itemContainers.containsKey(itemLabel)) {
			this.itemContainers.get(itemLabel).add(item);
			return;
		}

		// コンテナにないラベルの場合
		LinkedList<Item> itemList = new LinkedList<Item>();
		itemList.add(item);
		this.itemContainers.put(itemLabel, itemList);
	}

	// 商品を取り出す
	// 在庫が0の場合、例外発生
	public Item removeItem(ItemLabel itemLabel) {
		return this.itemContainers.get(itemLabel).remove(0);
	}

	// ラベル一覧を返す
	public List<ItemLabel> getItemLabels() {
		List<ItemLabel> itemLabels = new LinkedList<ItemLabel>(this.itemContainers.keySet());
		return itemLabels;
	}

	// ラベルのIDセットを返す
	public Set<Integer> getItemLabelIds() {
		Set<Integer> itemLabelIds = new HashSet<Integer>();
		for (ItemLabel itemLabel : this.getItemLabels()) {
			itemLabelIds.add(itemLabel.getId());
		}
		return itemLabelIds;
	}

	// 指定された商品ラベルidからラベルを取得
	// 存在しないidを指定した場合、例外発生
	public ItemLabel getSelectItemLabel(int id) {
		for (ItemLabel itemLabel : this.getItemLabels()) {
			if (itemLabel.getId() == id) {
				return itemLabel;
			}
		}
		throw new NullPointerException();
	}

}

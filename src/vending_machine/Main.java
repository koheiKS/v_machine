package vending_machine;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// 自動販売機初期化
		VendingMachine vm = new VendingMachine();
		vm.initializeItems();
		vm.initializeCoins();

		// 販売メソッド
		VendingOutput vendingOutput = vm.vending(scanner);
		// 結果を出力
		vendingOutput.outputInfo();

		scanner.close();

	}

}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    
    static class Product {
        int now;
        int after;
        int value;
    }

    static public void print(ArrayList<Product> arrayList) {
        for (Product product: arrayList) {
            System.out.println(product.now + " -- " + product.after + " === " + product.value);
        }
    }

    static class SortValue implements Comparator<Product> {
		public int compare(Product c1, Product c2) {
			return c1.value - c2.value;
		}
	}

    public static void main(String[] args) {
        int n, items;
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        items = s.nextInt();
        ArrayList<Product> products = new ArrayList<Product>();
        for (int i = 0; i < n; i++) {
            Product aux = new Product();
            aux.now = s.nextInt();
            products.add(aux);
        }
        for (int i = 0; i < n; i++) {
            products.get(i).after = s.nextInt();
            products.get(i).value = products.get(i).now - products.get(i).after;
        }
        System.out.println(n + " " + items);
        print(products);
        Collections.sort(products, new SortValue());
        System.out.println("*********************");
        print(products);
        int cnt = 0, money = 0;
        for (int i = 0; i < n; i++) {
            if (products.get(i).value <= 0) {
                money += products.get(i).now;
                cnt++;
            } else {
                if (cnt < items) {
                    money += products.get(i).now;
                    cnt++;
                } else {
                    break;
                }
            }
        }
        for (int i = cnt; i < n; i++) {
            money += products.get(i).after;
        }
        System.out.println(money);

    }

}